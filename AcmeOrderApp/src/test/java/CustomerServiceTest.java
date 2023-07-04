import com.example.model.Customer;
import com.example.repository.CustomerRepository;
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

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCustomers() {
        // Arrange
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        List<Customer> expectedCustomers = Arrays.asList(customer1, customer2);

        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        // Act
        List<Customer> actualCustomers = customerService.getAllCustomers();

        // Assert
        assertEquals(expectedCustomers, actualCustomers);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void testGetCustomerById_ExistingId() {
        // Arrange
        Long customerId = 1L;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setId(customerId);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(expectedCustomer));

        // Act
        Optional<Customer> actualCustomer = customerService.getCustomerById(customerId);

        // Assert
        assertTrue(actualCustomer.isPresent());
        assertEquals(expectedCustomer, actualCustomer.get());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetCustomerById_NonExistingId() {
        // Arrange
        Long customerId = 1L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        // Act
        Optional<Customer> actualCustomer = customerService.getCustomerById(customerId);

        // Assert
        assertFalse(actualCustomer.isPresent());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testSaveCustomer() {
        // Arrange
        Customer customer = new Customer();
        when(customerRepository.save(customer)).thenReturn(customer);

        // Act
        Customer savedCustomer = customerService.saveCustomer(customer);

        // Assert
        assertNotNull(savedCustomer);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testDeleteCustomer() {
        // Arrange
        Long customerId = 1L;

        // Act
        customerService.deleteCustomer(customerId);

        // Assert
        verify(customerRepository, times(1)).deleteById(customerId);
    }
}
