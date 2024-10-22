package se.harr.controllers;

import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import se.harr.mapper.CustomerMapper;
import se.harr.repository.CustomerRepository;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(CustomerResource.class)
public class CustomerResourceNoMorePagesTest {
    @InjectMock
    CustomerRepository customerRepository;

    @InjectMock
    CustomerMapper customerMapper;

    int pageIndex = 1000;
    int pageSize = 10;

    @BeforeEach
    public void setup() {
        Mockito.when(customerRepository.findAllCustomersPaginated(pageIndex, pageSize)).thenReturn(List.of());
    }

    @Test
    void testNoMoreCustomerPages() {
        given().when().get("?page=" + pageIndex + "&size=" + pageSize)
                .then()
                .statusCode(404);
    }
}
