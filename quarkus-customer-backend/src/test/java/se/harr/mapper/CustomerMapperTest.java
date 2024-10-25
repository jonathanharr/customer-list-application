package se.harr.mapper;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import se.harr.domain.Customer;
import se.harr.dto.CustomerDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class CustomerMapperTest {

    CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @Test
    public void testCustomerToCustomerDTO() {
        var customer = Customer.builder().name("Spongebob").email("spongebob@bikinibottom.com").address("123 Pineapple St").telephone("1234567890").build();

        var customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customer.getName(), customerDTO.getName());
        assertEquals(customer.getEmail(), customerDTO.getEmail());
        assertEquals(customer.getAddress(), customerDTO.getAddress());
        assertEquals(customer.getTelephone(), customerDTO.getTelephone());
    }

    @Test
    public void testCustomerDTOToCustomer() {
        CustomerDTO customerDTO = CustomerDTO.builder().name("Squidward").email("squidward@bikinibottom.com").address("456 Easter Island Head").telephone("0987654321").build();

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        assertNull(customer.getId());
        assertEquals(customerDTO.getName(), customer.getName());
        assertEquals(customerDTO.getEmail(), customer.getEmail());
        assertEquals(customerDTO.getAddress(), customer.getAddress());
        assertEquals(customerDTO.getTelephone(), customer.getTelephone());
    }
}
