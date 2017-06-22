package com.sultanmyrza.booking.service;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.repository.BookingRepository;
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

    @Autowired
    private BookingRepository bookingRepository;

    private HashMap<String, String> response = new HashMap<>();

    public List<Customer> getAllCustomers() {

        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public Customer addCustomer(Customer newCustomer) {

        // TODO: add error handling
        customerRepository.save(newCustomer);
        return newCustomer;
    }

    public Customer updateCustomer(Integer customerId, Customer updatedCustomer) {

        Customer customer = customerRepository.findOne(customerId);

        customer.setFirstName(updatedCustomer.getFirstName());
        customer.setLastName(updatedCustomer.getLastName());

        customerRepository.save(customer);

        return  customer;
    }

    public Customer deleteCustomer(Integer customerId) {

        // TODO: return customer with bookings
        Customer customer = customerRepository.findOne(customerId);
        List<Booking> customerBookings = customer.getBookings();
        for (Booking booking : customerBookings) {
            bookingRepository.delete(booking);
        }
        // TODO-ASK: add transactional here? if during deletion of customer bookings something goes wrong
        customerRepository.delete(customerId);

        return customer;
    }

    public List<Booking> getCustomerBookings(Integer customerId) {

        // TODO: wrap to try catch ask best practices Christopher
        // TODO-ASK: What can we unittest here?
        Customer customer = customerRepository.findOne(customerId);

        return customer.getBookings();
    }
}
