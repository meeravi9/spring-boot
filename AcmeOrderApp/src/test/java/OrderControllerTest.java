import com.example.controller.OrderController;
import com.example.model.Order;
import com.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> orders = Arrays.asList(order1, order2);

        when(orderService.getAllOrders()).thenReturn(orders);

        List<Order> result = orderController.getAllOrders();

        assertEquals(2, result.size());
        assertEquals(order1, result.get(0));
        assertEquals(order2, result.get(1));

        verify(orderService, times(1)).getAllOrders();
    }

    @Test
    public void testGetOrderById() {
        long orderId = 1L;
        Order order = new Order();

        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(order));

        Optional<Order> result = orderController.getOrderById(orderId);

        assertTrue(result.isPresent());
        assertEquals(order, result.get());

        verify(orderService, times(1)).getOrderById(orderId);
    }

    @Test
    public void testGetOrderById_NotFound() {
        long orderId = 1L;

        when(orderService.getOrderById(orderId)).thenReturn(Optional.empty());

        Optional<Order> result = orderController.getOrderById(orderId);

        assertFalse(result.isPresent());

        verify(orderService, times(1)).getOrderById(orderId);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();

        when(orderService.saveOrder(order)).thenReturn(order);

        Order result = orderController.createOrder(order);

        assertEquals(order, result);

        verify(orderService, times(1)).saveOrder(order);
    }

    @Test
    public void testUpdateOrder() {
        long orderId = 1L;
        Order existingOrder = new Order();
        Order updatedOrder = new Order();

        when(orderService.getOrderById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderService.saveOrder(updatedOrder)).thenReturn(updatedOrder);

        Order result = orderController.updateOrder(orderId, updatedOrder);

        assertEquals(updatedOrder, result);

        verify(orderService, times(1)).getOrderById(orderId);
        verify(orderService, times(1)).saveOrder(updatedOrder);
    }

    @Test
    public void testUpdateOrder_NotFound() {
        long orderId = 1L;
        Order updatedOrder = new Order();

        when(orderService.getOrderById(orderId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                orderController.updateOrder(orderId, updatedOrder)
        );

        assertEquals("Order not found with id: " + orderId, exception.getMessage());

        verify(orderService, times(1)).getOrderById(orderId);
        verify(orderService, times(0)).saveOrder(updatedOrder);
    }

    @Test
    public void testDeleteOrder() {
        long orderId = 1L;

        assertDoesNotThrow(() ->
                orderController.deleteOrder(orderId)
        );

        verify(orderService, times(1)).deleteOrder(orderId);
    }
}
