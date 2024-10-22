package se.harr.repository;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import se.harr.domain.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@QuarkusTest
public class CustomerRepositoryTest {

    @InjectMock
    CustomerRepository mockCustomerRepository;

    @Test
    void testFindAllCustomersPaginated() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Customer 1");

        List<Customer> customerList = List.of(customer1);
        when(mockCustomerRepository.findAllCustomersPaginated(0, 1)).thenReturn(customerList);

        List<Customer> result = mockCustomerRepository.findAllCustomersPaginated(0, 1);

        assertEquals(1, result.size());
        assertEquals("Customer 1", result.getFirst().getName());
    }
}
