package com.andri.warehouse.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("OrderStatus Tests")
public class OrderStatusTest {

    @Test
    @DisplayName("Should Create OrderStatus with valid status")
    void shouldCreateOrderStatusWithValidStatus() {
        //Given
        OrderStatus.Status validStatus = OrderStatus.Status.CREATED;

        //When
        OrderStatus orderStatus = new OrderStatus(validStatus);

        //Then
        assertEquals(validStatus, orderStatus.getStatus());
    }

    @Test
    @DisplayName("Should handle case insensitive string input")
    void shouldHandleCaseInsensitiveStringInput() {
        //When
        OrderStatus lowerCase = new OrderStatus("created");
        OrderStatus mixedCase = new OrderStatus("cReatED");

        //Then
        assertEquals(OrderStatus.Status.CREATED, lowerCase.getStatus());
        assertEquals(OrderStatus.Status.CREATED, mixedCase.getStatus());
    }

    @Test
    @DisplayName("Should handle string with white space")
    void shouldHandleStringWithWhiteSpace() {
        //When
        OrderStatus whiteSpace = new OrderStatus(" CREATED   ");

        //Then
        assertEquals(OrderStatus.Status.CREATED, whiteSpace.getStatus());
    }

    @Test
    @DisplayName("Should throw exception for invalid string")
    void shouldThrowExceptionForInvalidString() {
        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new OrderStatus("INVALID_STATUS"));

        assertTrue(exception.getMessage().contains("Invalid order status: INVALID_STATUS"));
    }

    @Test
    @DisplayName("Should throw exception for null string")
    void shouldThrowExceptionForNullString() {
        //When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new OrderStatus((String) null));

        assertEquals("Status String cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("SHould throw exception for empty string")
    void shouldThrowExceptionForEmptyString() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new OrderStatus((String) ""));
        assertEquals("Status String cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("CREATED status should be modifiable")
    void createdStatusShouldBeModifiable() {

        //Given
        OrderStatus status = OrderStatus.created();

        //When & Then
        assertTrue(status.isModifiable());

    }

    @Test
    @DisplayName("AWAITING_APPROVAL status should be approvable")
    void awaitingApprovalStatusShouldBeApprovable() {
        //Given
        OrderStatus status = OrderStatus.awaitingApproval();

        //When&Then
        assertTrue(status.isApprovable());
    }

    @Test
    @DisplayName("DECLINED status should be modifiable")
    void declinedStatusShouldBeModifiable() {
        // Given
        OrderStatus status = OrderStatus.declined();

        // When & Then
        assertTrue(status.isModifiable());
    }

    @Test
    @DisplayName("FULFILLED status should not be modifiable")
    void fulfilledStatusShouldNotBeModifiable() {
        // Given
        OrderStatus status = OrderStatus.fulfilled();

        // When & Then
        assertFalse(status.isModifiable());
    }

    @Test
    @DisplayName("AWAITING_APPROVAL status should not be modifiable")
    void awaitingApprovalStatusShouldNotBeModifiable() {
        // Given
        OrderStatus status = OrderStatus.awaitingApproval();

        // When & Then
        assertFalse(status.isModifiable());
    }

        @Test
    @DisplayName("CREATED status should not be approvable")
    void createdStatusShouldNotBeApprovable() {
        // Given
        OrderStatus status = OrderStatus.created();

        // When & Then
        assertFalse(status.isApprovable());
    }

    @Test
    @DisplayName("APPROVED status should not be approvable")
    void approvedStatusShouldNotBeApprovable() {
        // Given
        OrderStatus status = OrderStatus.approved();

        // When & Then
        assertFalse(status.isApprovable());
    }

    @Test
    @DisplayName("CREATED status should be cancellable")
    void createdStatusShouldBeCancellable() {
        // Given
        OrderStatus status = OrderStatus.created();

        // When & Then
        assertTrue(status.isCancellable());
    }

    @Test
    @DisplayName("FULFILLED status should not be cancellable")
    void fulfilledStatusShouldNotBeCancellable() {
        // Given
        OrderStatus status = OrderStatus.fulfilled();

        // When & Then
        assertFalse(status.isCancellable());
    }

    @Test
    @DisplayName("UNDER_DELIVERY status should be cancellable")
    void underDeliveryStatusShouldBeCancellable() {
        // Given
        OrderStatus status = OrderStatus.underDelivery();

        // When & Then
        assertTrue(status.isCancellable());
    }

    @Test
    @DisplayName("APPROVED status should be deliverable")
    void approvedStatusShouldBeDeliverable() {
        // Given
        OrderStatus status = OrderStatus.approved();

        // When & Then
        assertTrue(status.isDeliverable());
    }

    @Test
    @DisplayName("CREATED status should not be deliverable")
    void createdStatusShouldNotBeDeliverable() {
        // Given
        OrderStatus status = OrderStatus.created();

        // When & Then
        assertFalse(status.isDeliverable());
    }

    @Test
    @DisplayName("FULFILLED status should not be deliverable")
    void fulfilledStatusShouldNotBeDeliverable() {
        // Given
        OrderStatus status = OrderStatus.fulfilled();

        // When & Then
        assertFalse(status.isDeliverable());
    }

    @Test
    @DisplayName("Two OrderStatus with same value should be equal")
    void twoOrderStatusWithSameNameShouldBeEqual() {
        //Given
        OrderStatus status1 = OrderStatus.created();
        OrderStatus status2 = OrderStatus.created();

        //When & Then
        assertEquals(status1, status2);
        assertEquals(status1.hashCode(), status2.hashCode());
    }

    @Test
    @DisplayName("Two OrderStatus with different value should not be equal")
    void twoOrderStatusWithDifferentNameShouldNotBeEqual() {
        OrderStatus status1 = OrderStatus.created();
        OrderStatus status2 = OrderStatus.created();

        //When & Then
        assertNotEquals(status1, status2);
    }

    @Test
    @DisplayName("OrderStatus should not equal null")
    void orderStatusShouldNotBeNull() {
        //Given
        OrderStatus status = OrderStatus.created();

        //When & Then
        assertNotEquals(null, status);
    }

}

