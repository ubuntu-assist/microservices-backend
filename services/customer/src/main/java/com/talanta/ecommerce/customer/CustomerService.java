package com.talanta.ecommerce.customer;

import com.talanta.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(String customerId, CustomerRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("No customer found with the provided ID:: %s".formatted(customerId)));

        mergeCustomer(customer, request);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName()))
            customer.setFirstName(request.firstName());

        if(StringUtils.isNotBlank(request.lastName()))
            customer.setLastName(request.lastName());

        if(StringUtils.isNotBlank(request.email()))
            customer.setLastName(request.email());

        if(request.address() != null)
            customer.setAddress(request.address());
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    public CustomerDTO getSingleCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No customer found with the provided ID:: %s".formatted(customerId)));
    }

    public void deleteCustomer(String customerId) {
        boolean exists = customerRepository.existsById(customerId);

        if(!exists)
            throw new CustomerNotFoundException("No customer found with the provided ID:: %s".formatted(customerId));

        customerRepository.deleteById(customerId);
    }
}
