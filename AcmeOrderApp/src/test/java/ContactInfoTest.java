import com.example.model.ContactInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactInfoTest {

    @Test
    public void testGetId() {
        Long id = 1L;
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(id);

        assertEquals(id, contactInfo.getId());
    }

    @Test
    public void testGetEmail() {
        String email = "example@example.com";
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setEmail(email);

        assertEquals(email, contactInfo.getEmail());
    }

    @Test
    public void testGetPhone() {
        String phone = "1234567890";
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPhone(phone);

        assertEquals(phone, contactInfo.getPhone());
    }
    
    // Additional test cases for setters can be added if needed
}
