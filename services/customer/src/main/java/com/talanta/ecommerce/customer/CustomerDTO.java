package com.talanta.ecommerce.customer;


public record CustomerDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
