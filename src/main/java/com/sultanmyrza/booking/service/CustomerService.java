package com.sultanmyrza.booking.service;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private HashMap<String, String> response = new HashMap<>();

    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public HashMap<String, String> addCustomer(Customer newCustomer) {

        try {

            customerRepository.save(newCustomer);
            response.put("status", "success");
            response.put("info", "succesfully added new customer");
        }
        catch (Exception e) {

            response.put("status", "error");
            response.put("info", e.toString());
        }
        finally {

            return response;
        }
    }

    public HashMap<String, String> updateCustomer(Integer customerId, Customer updatedCustomer) {

        try {
            Customer customer = customerRepository.findOne(customerId);

            customer.setFirstName(updatedCustomer.getFirstName());
            customer.setLastName(updatedCustomer.getLastName());
            customerRepository.save(customer);
            response.put("info", "customer successfully updated");
        }
        catch (Exception e) {

            response.put("status", "error");
            response.put("info", e.toString());
        }
        finally {

            return response;
        }
    }

    public HashMap<String, String> deleteCustomer(Integer customerId) {


        try {

            customerRepository.delete(customerId);
            response.put("status", "success");
            response.put("info", "customer deleted id: " + customerId);
        }
        catch (Exception e) {

            response.put("status", "error");
            response.put("info", e.toString());
        }
        finally {

            return response;
        }
    }

    public List<Booking> getCustomerBookings(Integer customerId) {

        // TODO: wrap to try catch ask best practices Christopher
        Customer customer = customerRepository.findOne(customerId);

        return customer.getBookings();
    }
}
