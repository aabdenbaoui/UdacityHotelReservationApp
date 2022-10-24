package com.aabdenbaoui.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class CustomerTest {

    @Test
    public void testCustomerEmailException(){

//        assertEquals(customer.getEmail(), "username@domain.com");
        assertThrows(IllegalArgumentException.class, new Executable() {

            @Override
            public void execute() throws Throwable {
                Customer customer = new Customer("Aniss", "Abdenbaoui", "domain.com");

            }
        });
        Customer customer01 = new Customer("Aniss", "Abdenbaoui", "aabdenba@domain.com");
        assertNotNull(customer01);
        assertEquals("Aniss", customer01.getFirstName());
        assertEquals("Abdenbaoui", customer01.getLastName());
        assertEquals("aabdenba@domain.com", customer01.getEmail());

    }


}