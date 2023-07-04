import com.example.model.Customer;
import com.example.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class OrderTest {

    @Test
    public void testGetOrderNumber() {
        Order order = new Order();
        order.setOrderNumber("ORD123");
        Assertions.assertEquals("ORD123", order.getOrderNumber());
    }

    @Test
    public void testGetOrderDate() {
        Order order = new Order();
        Date orderDate = new Date();
        order.setOrderDate(orderDate);
        Assertions.assertEquals(orderDate, order.getOrderDate());
    }

    @Test
    public void testGetTotalAmount() {
        Order order = new Order();
        order.setTotalAmount(100.0f);
        Assertions.assertEquals(100.0f, order.getTotalAmount());
    }

    @Test
    public void testGetCustomer() {
        Order order = new Order();
        Customer customer = new Customer();
        order.setCustomer(customer);
        Assertions.assertEquals(customer, order.getCustomer());
    }
    
    @Test
    public void testSetCustomer() {
        Order order = new Order();
        Customer customer = new Customer();
        order.setCustomer(customer);
        Assertions.assertEquals(customer, order.getCustomer());
    }
}
