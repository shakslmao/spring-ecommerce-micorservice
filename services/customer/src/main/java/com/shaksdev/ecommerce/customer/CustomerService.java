package com.shaksdev.ecommerce.customer;

import com.shaksdev.ecommerce.exceptions.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.save(customerMapper.toCustomer(customerRequest));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {
        var customer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id %s not found", customerRequest.id())));
        mergeCustomer(customer, customerRequest);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstname())) {
            customer.setFirstname(customerRequest.firstname());
        }

        if (StringUtils.isNotBlank(customerRequest.lastname())) {
            customer.setFirstname(customerRequest.lastname());
        }

        if (StringUtils.isNotBlank(customerRequest.email())) {
            customer.setFirstname(customerRequest.email());
        }

        if (customerRequest.address() != null) {
            customer.setAddress(customerRequest.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean customerExistsById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No Customer was found with the id %s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
