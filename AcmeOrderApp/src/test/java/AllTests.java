import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CustomerControllerTest.class,
        OrderControllerTest.class,
        CustomerTest.class,
        OrderTest.class,
        CustomerServiceTest.class,
        OrderServiceTest.class
})
public class AllTests {
    // This class doesn't need any code implementation
}
