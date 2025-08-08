package com.andri.warehouse.application.user;

import com.andri.warehouse.application.user.command.ChangePasswordCommand;
import com.andri.warehouse.application.user.command.CreateUserCommand;
import com.andri.warehouse.application.user.command.DeleteUserCommand;
import com.andri.warehouse.application.user.command.UpdateUserCommand;
import com.andri.warehouse.application.user.dto.UserDetailsDTO;
import com.andri.warehouse.application.user.dto.UserSummaryDTO;
import com.andri.warehouse.application.user.exception.UserNotFoundException;
import com.andri.warehouse.domain.user.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class UserApplicationService {

    private final UserRepository userRepository;

    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User findUserOrThrow(UserId userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Transactional
    public UserId createUser(CreateUserCommand command) {
        Username usernameObj = Username.of(command.getUsername());
        Email emailObj = Email.of(command.getEmail());

        if(userRepository.existsByUsername(usernameObj)) {
            throw new IllegalArgumentException("Username already exists: " + usernameObj);
        }

        if(userRepository.existsByEmail(emailObj)) {
            throw new IllegalArgumentException("Email already exists: " + emailObj);
        }

        Password passwordObj = Password.of(command.getPassword());
        UserRole roleObj = new UserRole(command.getRole());

        User user = User.createUser(usernameObj, emailObj, passwordObj, roleObj);

        userRepository.save(user);

        return user.getUserId();
    }

    public UserDetailsDTO getUserDetails(UserId userId) {
        User user = findUserOrThrow(userId);
        return UserDetailsDTO.from(user);
    }

    @Transactional
    public void updateUser(UpdateUserCommand command) {
        User user = findUserOrThrow(command.getUserId());

        if(command.hasEmail()){
            Email newEmailObj = Email.of(command.getEmail());

            if(userRepository.existsByEmail(newEmailObj)) {
                Optional<User> existingUser = userRepository.findByEmail(newEmailObj);
                if(existingUser.isPresent() && !existingUser.get().getUserId().equals(user.getUserId())) {
                    throw new IllegalArgumentException("Email already exists: " + newEmailObj);
                }
            }
            user.updateEmail(newEmailObj);
        }

        if(command.hasRole()){
            UserRole newRoleObj = new UserRole(command.getRole());
            user.changeRole(newRoleObj);
        }

        userRepository.save(user);
    }

    @Transactional
    public void changePassword(ChangePasswordCommand command) {
        User user = findUserOrThrow(command.getUserId());
        user.changePassword(command.getCurrentPassword(), command.getNewPassword());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(DeleteUserCommand command) {
        if(!userRepository.existsById(command.getUserId())) {
            throw new UserNotFoundException(command.getUserId());
        }
        userRepository.delete(command.getUserId());
    }


    public List<UserSummaryDTO> getAllUsers(){
        List<User> users = userRepository.findAllUsers();

        return users.stream()
                .map(UserSummaryDTO::from)
                .collect(Collectors.toList());

    }

    public Optional<UserDetailsDTO> findByUsername(String username){
        Username usernameObj = Username.of(username);

        return userRepository.findByUsername(usernameObj)
                .map(UserDetailsDTO::from);

    }

    public List<UserSummaryDTO> getUsersByRole(String role){
        UserRole roleObj = new UserRole(role);
        List<User> users = userRepository.findByRole(roleObj);

        return users.stream()
                .map(UserSummaryDTO::from)
                .collect(Collectors.toList());
    }


}
