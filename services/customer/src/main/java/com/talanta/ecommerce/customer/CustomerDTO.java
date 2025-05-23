package com.talanta.ecommerce.customer;

public record CustomerDTO(
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
