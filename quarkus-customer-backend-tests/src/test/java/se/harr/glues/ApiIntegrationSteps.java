package se.harr.glues;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;

import jakarta.ws.rs.core.Response;
import se.harr.models.CustomerDTO;

import java.util.List;
import java.util.Map;

@QuarkusTest
public class ApiIntegrationSteps {

    @Inject
    @RestClient
    QuarkusCustomerClient quarkusCustomerClient;

    private Response response;
    private List<CustomerDTO> customerList;

    private Response healthResponse;

    @Then("I should receive a successful response")
    public void i_should_receive_a_successful_response() {
        Assertions.assertEquals(200, response.getStatus());
    }

    @Then("I should receive a created response")
    public void iShouldReceiveACreatedResponse() {
        Assertions.assertEquals(201, response.getStatus());
    }

    @Given("I have a REST client for customers")
    public void iHaveARESTClientForCustomers() {
        Assertions.assertNotNull(quarkusCustomerClient);
    }

    @When("I request {int} customers in {int} page")
    public void iRequestCustomersInPage(int pageSize, int pageIndex) {
        response = quarkusCustomerClient.getCustomerPage(pageIndex, pageSize);
        customerList = response.readEntity(List.class);
    }

    @And("the customers should exist")
    public void theCustomersShouldExist() {
        Assertions.assertNotNull(customerList);
        Assertions.assertFalse(customerList.isEmpty());
    }

    @And("the customers should contain {int} customers")
    public void theCustomersShouldContainCustomers(int arg0) {
        Assertions.assertEquals(arg0, customerList.size());
    }

    @When("I request the health check")
    public void iRequestTheHealthCheck() {
        healthResponse = quarkusCustomerClient.getHealth();
    }

    @And("the health check should exist")
    public void theHealthCheckShouldExist() {
        Assertions.assertNotNull(healthResponse);
    }

    @And("the health check should contain {string}")
    public void theHealthCheckShouldContain(String arg0) {
        String health = healthResponse.readEntity(String.class);
        Assertions.assertTrue(health.contains(arg0));
    }
    @When("I create a customer with the following data:")
    public void i_create_a_customer_with_the_following_data(DataTable dataTable) {
        List<Map<String, String>> customerData = dataTable.asMaps(String.class, String.class);

        Map<String, String> customer = customerData.getFirst();

        String name = customer.get("name");
        String email = customer.get("email");
        String phoneNumber = customer.get("phone_number");
        String address = customer.get("address");

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(name);
        customerDTO.setEmail(email);
        customerDTO.setTelephone(phoneNumber);
        customerDTO.setAddress(address);

        response = quarkusCustomerClient.createCustomer(customerDTO);
    }
}
