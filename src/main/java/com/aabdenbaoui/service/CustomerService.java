package com.aabdenbaoui.service;

import com.aabdenbaoui.model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CustomerService {
        private static CustomerService customerService = null;

        private static HashMap<String, Customer> customers = new HashMap<>();
        private CustomerService(){

        }
        public  void addCustomer(String email, String firstName, String lastName){
                customers.put(email, new Customer(email, firstName, lastName));
        }

        public  Collection<Customer> getCustomers() {
                return customers.values();
        }
        public  Customer getCustomer(String customerEmail){
                return customers.get(customerEmail);
        }

        public static CustomerService getInstance(){
                if (customerService == null)
                        customerService = new CustomerService();

                return customerService;
        }
}
