package com.sultanmyrza.booking.command_runners;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.model.Customer;
import com.sultanmyrza.booking.repository.BookingRepository;
import com.sultanmyrza.booking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sultanmyrza on 17. 6. 10.
 */

/**
 * after beans created at the last point spring
 * run every class that implements CommandLineRunner
 * we can order execution by annotating them with @Order
 */

@Component
public class DatabaseFixture implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // pre-populating db
    @Override
    public void run(String... strings) throws Exception {

        List<Customer> customers = new ArrayList<>();
        Customer newCustomer;

        newCustomer = new Customer();
        newCustomer.setFirstName("Dante");
        newCustomer.setLastName("Perlman");
        customers.add(newCustomer);

        newCustomer = new Customer();
        newCustomer.setFirstName("Leo");
        newCustomer.setLastName("Steenberg");
        customers.add(newCustomer);

        newCustomer = new Customer();
        newCustomer.setFirstName("Kasey");
        newCustomer.setLastName("Hildebrand");
        customers.add(newCustomer);

        customerRepository.save(customers);

        // add 3 bookings to last user
        List<Booking> bookings = new ArrayList<>();
        Booking newBooking;

        for (int i = 0; i < 3; i++) {
            newBooking = new Booking();
            newBooking.setState("booked");
            newBooking.setCustomer(newCustomer);
            bookings.add(newBooking);
        }

        bookingRepository.save(bookings);

    }
}
