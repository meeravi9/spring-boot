import com.example.model.Order;
import com.example.repository.OrderRepository;
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

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        // Arrange
        Order order1 = new Order();
        Order order2 = new Order();
        List<Order> expectedOrders = Arrays.asList(order1, order2);

        when(orderRepository.findAll()).thenReturn(expectedOrders);

        // Act
        List<Order> actualOrders = orderService.getAllOrders();

        // Assert
        assertEquals(expectedOrders, actualOrders);
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testGetOrderById_ExistingId() {
        // Arrange
        Long orderId = 1L;
        Order expectedOrder = new Order();
        expectedOrder.setId(orderId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Act
        Optional<Order> actualOrder = orderService.getOrderById(orderId);

        // Assert
        assertTrue(actualOrder.isPresent());
        assertEquals(expectedOrder, actualOrder.get());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void testGetOrderById_NonExistingId() {
        // Arrange
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act
        Optional<Order> actualOrder = orderService.getOrderById(orderId);

        // Assert
        assertFalse(actualOrder.isPresent());
        verify(orderRepository, times(1)).findById(orderId);
    }

    @Test
    public void testSaveOrder() {
        // Arrange
        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Order savedOrder = orderService.saveOrder(order);

        // Assert
        assertNotNull(savedOrder);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testDeleteOrder() {
        // Arrange
        Long orderId = 1L;

        // Act
        orderService.deleteOrder(orderId);

        // Assert
        verify(orderRepository, times(1)).deleteById(orderId);
    }
}
