package se.harr.controllers;

import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.harr.domain.Customer;
import se.harr.dto.CustomerDTO;
import se.harr.mapper.CustomerMapper;
import se.harr.repository.CustomerRepository;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static se.harr.CustomerTestingConstants.*;

@QuarkusTest
@TestHTTPEndpoint(CustomerResource.class)
public class CustomerResourceTest {

    @InjectMock
    CustomerRepository customerRepository;

    @InjectMock
    CustomerMapper customerMapper;

    int pageIndex = 1;
    int pageSize = 1;

    List<Customer> customers = List.of(Customer.builder().id(ID).name(NAME).email(EMAIL).address(ADDRESS).telephone(TELEPHONE).build());
    List<CustomerDTO> customerDTOs = List.of(CustomerDTO.builder().name(NAME).email(EMAIL).address(ADDRESS).telephone(TELEPHONE).build());

    @BeforeEach
    public void setup() {
        Mockito.when(customerRepository.findAllCustomersPaginated(pageIndex, pageSize)).thenReturn(customers);
        Mockito.when(customerMapper.customerToCustomerDTO(customers.getFirst())).thenReturn(customerDTOs.getFirst());
    }

    @Test
    void testGetOneCustomerFromPageOne() {
        given().when().get("?page=" + pageIndex + "&size=" + pageSize)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("[0].name", is(NAME))
                .body("[0].address", is(ADDRESS))
                .body("[0].email", is(EMAIL))
                .body("[0].telephone", is(TELEPHONE))
                .body("$", hasSize(1));
    }
}
