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

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(CustomerResource.class)
public class CustomerResourceCreateSuccessfulTest {

    @InjectMock
    CustomerRepository customerRepository;

    @InjectMock
    CustomerMapper customerMapper;

    Long ID = 1L;
    String NAME = "Stuart";
    String EMAIL = "Stuart@little.com";
    String ADDRESS = "Little House";
    String TELEPHONE = "1234567890";

    CustomerDTO customerDTO = CustomerDTO.builder().name(NAME).email(EMAIL).address(ADDRESS).telephone(TELEPHONE).build();

    @BeforeEach
    public void setup() {
        Mockito.when(customerMapper.customerDTOToCustomer(Mockito.any())).thenReturn(Customer.builder().id(1L).build());
    }

    @Test
    void testCreateCustomer() {
        given()
                .contentType(ContentType.JSON)
                .body(customerDTO)
                .when()
                .post("/")
                .then()
                .statusCode(201)
                .header("Location", is("http://localhost:8081/customer/" + ID));
    }
}
