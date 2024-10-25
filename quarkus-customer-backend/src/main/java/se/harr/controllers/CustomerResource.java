package se.harr.controllers;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.harr.domain.Customer;
import se.harr.dto.CustomerDTO;
import se.harr.mapper.CustomerMapper;
import se.harr.repository.CustomerRepository;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    CustomerRepository customerRepository;
    CustomerMapper customerMapper;

    public CustomerResource(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersPaginated(@QueryParam("page") @DefaultValue("0") int page,
                                          @QueryParam("size") @DefaultValue("10") int size) {
        List<Customer> customerPage = customerRepository.findAllCustomersPaginated(page, size);

        if (customerPage.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<CustomerDTO> customerDTOs = customerPage.stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());

        return Response.ok(customerDTOs).build();
    }

    @POST
    @Transactional
    public Response create(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customerRepository.persist(customer);

        return Response.created(URI.create("/customer/" + customer.getId()))
                .build();
    }
}
