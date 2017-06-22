package com.sultanmyrza.booking.controller;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }

    @RequestMapping(value = "/customer/id/{customerId}/bookings", method = RequestMethod.GET)
    public List<Booking> getCustomerBookings(@PathVariable Integer customerId) {

        return customerService.getCustomerBookings(customerId);
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public Customer addCustomer(@RequestBody Customer newCustomer) {

        // TODO: add logic for error handling
        return customerService.addCustomer(newCustomer);

    }

    @RequestMapping(value = "/customer/id/{customerId}/update", method = RequestMethod.PUT)
    public Customer updateCustomer(@PathVariable Integer customerId,
                                                 @RequestBody Customer updatedCustomer) {

        // TODO: add logic for error handling
        return customerService.updateCustomer(customerId, updatedCustomer);
    }


    @RequestMapping(value = "/customer/id/{customerId}/delete", method = RequestMethod.DELETE)
    public Customer deleteCustomer(@PathVariable Integer customerId) {

        // TODO: add logic for error handling for example non-existing id
        return customerService.deleteCustomer(customerId);
    }
}
