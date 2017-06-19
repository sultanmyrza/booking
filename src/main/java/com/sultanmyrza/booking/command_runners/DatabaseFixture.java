package com.sultanmyrza.booking.command_runners;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.repository.BookingRepository;
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
    private BookingRepository bookingRepository;

    // pre-populating db
    @Override
    public void run(String... strings) throws Exception {

        List<Booking> bookings = new ArrayList<>();
        Booking newBooking;

        for (int i = 0; i < 3; i++) {
            newBooking = new Booking();
            newBooking.setState("booked");
            bookings.add(newBooking);
        }

        bookingRepository.save(bookings);
    }
}
