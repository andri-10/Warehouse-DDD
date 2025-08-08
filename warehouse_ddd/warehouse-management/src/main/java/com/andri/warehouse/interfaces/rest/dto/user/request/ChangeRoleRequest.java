package com.andri.warehouse.interfaces.rest.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ChangeRoleRequest {

    @NotBlank(message = "Role cannot be blank")
    @Pattern(regexp = "CLIENT|WAREHOUSE_MANAGER|SYSTEM_ADMIN",
            message = "Role must be CLIENT, WAREHOUSE_MANAGER, or SYSTEM_ADMIN")
    private String role;

    public ChangeRoleRequest() {
    }

    public ChangeRoleRequest(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
