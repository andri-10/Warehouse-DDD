package com.andri.warehouse.interfaces.rest.controller;

import com.andri.warehouse.application.order.OrderApplicationService;
import com.andri.warehouse.application.order.command.*;
import com.andri.warehouse.application.order.dto.OrderDetailsDTO;
import com.andri.warehouse.application.order.dto.OrderSummaryDTO;
import com.andri.warehouse.application.order.exception.OrderNotFoundException;
import com.andri.warehouse.application.order.query.OrderListQuery;
import com.andri.warehouse.domain.order.OrderId;
import com.andri.warehouse.domain.user.UserId;
import com.andri.warehouse.interfaces.rest.dto.order.request.AddItemRequest;
import com.andri.warehouse.interfaces.rest.dto.order.request.CreateOrderRequest;
import com.andri.warehouse.interfaces.rest.dto.order.request.DeclineOrderRequest;
import com.andri.warehouse.interfaces.rest.dto.order.request.UpdateItemQuantityRequest;
import com.andri.warehouse.interfaces.rest.dto.order.response.CreateOrderResponse;
import com.andri.warehouse.interfaces.rest.dto.order.response.OrderDetailsResponse;
import com.andri.warehouse.interfaces.rest.dto.order.response.OrderSummaryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    private final OrderApplicationService orderService;

    public OrderController(OrderApplicationService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        try{
            CreateOrderCommand command = new CreateOrderCommand(
                    UserId.of(request.getCustomerId()),
                    LocalDateTime.parse(request.getDeadlineDate())
            );

            OrderId orderId = orderService.createOrder(command);

            CreateOrderResponse response = new CreateOrderResponse(orderId.getOrderId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{orderId}/submit")
    public ResponseEntity<Void> submitOrder(@PathVariable String orderId){
        try{
            SubmitOrderCommand command = new SubmitOrderCommand(OrderId.of(orderId));
            orderService.submitOrder(command);
            return ResponseEntity.ok().build();
        } catch (OrderNotFoundException o){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<Void> addItem(@PathVariable String orderId, @Valid @RequestBody AddItemRequest request) {
        try{
            AddItemCommand command = new AddItemCommand(
                    OrderId.of(orderId),
                    request.getItemId(),
                    request.getQuantity()
            );
            orderService.addItemToOrder(command);
            return ResponseEntity.ok().build();
        } catch (OrderNotFoundException o){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException | IllegalStateException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{orderId}/approve")
    public ResponseEntity<Void> approveOrder(@PathVariable String orderId){
        try {
            ApproveOrderCommand command = new ApproveOrderCommand(OrderId.of(orderId));
            orderService.approveOrder(command);
            return ResponseEntity.ok().build();
        }catch (OrderNotFoundException o){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/{orderId}/decline")
    public ResponseEntity<Void> declineOrder(@PathVariable String orderId, @RequestBody(required = false) DeclineOrderRequest request){
        try{
            String reason = (request != null) ? request.getReason() : null;
            DeclineOrderCommand command = new DeclineOrderCommand(
                    OrderId.of(orderId),
                    reason
            );

            orderService.declineOrder(command);
            return ResponseEntity.ok().build();
        }catch (OrderNotFoundException o){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailsResponse> getOrderDetails(@PathVariable String orderId){
        try{
            OrderId orderIdObj = OrderId.of(orderId);

            OrderDetailsDTO orderDetails = orderService.getOrderDetails(orderIdObj);

            OrderDetailsResponse response = OrderDetailsResponse.from(orderDetails);

            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (OrderNotFoundException o){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderSummaryResponse>> getOrders(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String status){
        try{
            OrderListQuery query = new OrderListQuery(customerId, status);
            List<OrderSummaryDTO> orders = orderService.getOrders(query);

            List<OrderSummaryResponse> response = orders.stream()
                    .map(OrderSummaryResponse::from)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(response);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


    @DeleteMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Void> removeItem(@PathVariable String orderId, @PathVariable String itemId){
        try{
            OrderId orderIdObj = OrderId.of(orderId);
            RemoveItemCommand command = new RemoveItemCommand(orderIdObj, itemId);
            orderService.removeItemFromOrder(command);

            return ResponseEntity.ok().build();
        }catch(OrderNotFoundException o){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{orderId}/items/{itemId}")
    public ResponseEntity<Void> updateItemQuantity(@PathVariable String orderId, @PathVariable String itemId, @RequestBody UpdateItemQuantityRequest request){
        try{
            OrderId orderIdObj = OrderId.of(orderId);
            UpdateItemQuantityCommand command = new UpdateItemQuantityCommand(
                    orderIdObj,
                    itemId,
                    request.getQuantity()
            );

            orderService.updateItemQuantity(command);
            return ResponseEntity.ok().build();
        }catch(OrderNotFoundException o){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable String orderId){
        try {
            OrderId orderIdObj = OrderId.of(orderId);
            CancelOrderCommand command = new CancelOrderCommand(orderIdObj);
            orderService.cancelOrder(command);
            return ResponseEntity.ok().build();
        }catch (OrderNotFoundException o){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }


}
