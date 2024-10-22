package se.harr.mapper;

import org.mapstruct.Mapper;
import se.harr.domain.Customer;
import se.harr.dto.CustomerDTO;

@Mapper(componentModel = "cdi")
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
