package se.harr.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import se.harr.domain.Customer;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
    public List<Customer> findAllCustomersPaginated(int page, int size) {
        return findAll().page(Page.of(page, size)).stream().toList();
    }
}