import com.example.model.ContactInfo;
import com.example.model.Customer;
import com.example.model.Order;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testGetId() {
        Long id = 1L;
        Customer customer = new Customer();
        customer.setId(id);

        assertEquals(id, customer.getId());
    }

    @Test
    public void testGetName() {
        String name = "John Doe";
        Customer customer = new Customer();
        customer.setName(name);

        assertEquals(name, customer.getName());
    }

    @Test
    public void testGetContactInfo() {
        ContactInfo contactInfo = new ContactInfo();
        Customer customer = new Customer();
        customer.setContactInfo(contactInfo);

        assertEquals(contactInfo, customer.getContactInfo());
    }

    @Test
    @Disabled
    public void testGetOrder() {
        Customer customer = new Customer();

        assertNotNull(customer.getOrder());
        assertTrue(customer.getOrder().isEmpty());
    }

    @Test
    public void testSetOrder_WithNull() {
        Customer customer = new Customer();
        customer.setOrder(null);

        assertNull(customer.getOrder());
    }

    // Additional test cases for setters can be added if needed
}
