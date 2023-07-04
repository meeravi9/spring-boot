import com.example.controller.CustomerController;
import com.example.model.ContactInfo;
import com.example.model.Customer;
import com.example.service.CustomerService;
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

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsAlive() {
        String result = customerController.IsAlive();
        assertEquals("Success", result);
    }

    @Test
    public void testGetAllCustomers() {
        Customer customer1 = new Customer(1L, "Ravi");
        Customer customer2 = new Customer(2L, "Kumar");
        List<Customer> customers = Arrays.asList(customer1, customer2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        List<Customer> result = customerController.getAllCustomers();

        assertEquals(2, result.size());
        assertEquals(customer1, result.get(0));
        assertEquals(customer2, result.get(1));

        verify(customerService, times(1)).getAllCustomers();
    }

    @Test
    public void testGetCustomerById() {
        long customerId = 1L;
        Customer customer = new Customer(customerId, "Ravi");
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerService.getCustomerById(customerId)).thenReturn(optionalCustomer);

        Optional<Customer> result = customerController.getCustomerById(customerId);

        assertTrue(result.isPresent());
        assertEquals(customer, result.get());

        verify(customerService, times(1)).getCustomerById(customerId);
    }

    @Test
    public void testGetCustomerById_NotFound() {
        long customerId = 1L;
        Optional<Customer> optionalCustomer = Optional.empty();

        when(customerService.getCustomerById(customerId)).thenReturn(optionalCustomer);

        Optional<Customer> result = customerController.getCustomerById(customerId);

        assertFalse(result.isPresent());

        verify(customerService, times(1)).getCustomerById(customerId);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer(1L, "Ravi");

        when(customerService.saveCustomer(customer)).thenReturn(customer);

        Customer result = customerController.createCustomer(customer);

        assertEquals(customer, result);

        verify(customerService, times(1)).saveCustomer(customer);
    }

    @Test
    public void testUpdateCustomer() {
        long customerId = 1L;
        Customer existingCustomer = new Customer(customerId, "Ravi");
        Customer updatedCustomer = new Customer(customerId, "Kumar");

        when(customerService.getCustomerById(customerId)).thenReturn(Optional.of(existingCustomer));
        when(customerService.saveCustomer(updatedCustomer)).thenReturn(updatedCustomer);

        Customer result = customerController.updateCustomer(customerId, updatedCustomer);

        assertEquals(updatedCustomer, result);

        verify(customerService, times(1)).getCustomerById(customerId);
        verify(customerService, times(1)).saveCustomer(updatedCustomer);
    }

    @Test
    public void testUpdateCustomer_NotFound() {
        long customerId = 1L;
        Customer updatedCustomer = new Customer(customerId, "Kumar");

        when(customerService.getCustomerById(customerId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                customerController.updateCustomer(customerId, updatedCustomer)
        );

        assertEquals("Customer not found with id: " + customerId, exception.getMessage());

        verify(customerService, times(1)).getCustomerById(customerId);
        verify(customerService, times(0)).saveCustomer(updatedCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        long customerId = 1L;

        assertDoesNotThrow(() ->
                customerController.deleteCustomer(customerId)
        );

        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}
