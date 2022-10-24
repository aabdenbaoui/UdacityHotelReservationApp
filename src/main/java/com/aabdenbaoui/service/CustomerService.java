package com.aabdenbaoui.service;

import com.aabdenbaoui.model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CustomerService {
        private static HashMap<String, Customer> customers = new HashMap<>();
        public static void addCustomer(String email, String firstName, String lastName){
                customers.put(email, new Customer(email, firstName, lastName));
        }

        public static Collection<Customer> getCustomers() {
                return customers.values();
        }
        public static Customer getCustomer(String customerEmail){
                return customers.get(customerEmail);
        }
}
