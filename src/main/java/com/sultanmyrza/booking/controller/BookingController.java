package com.sultanmyrza.booking.controller;

import com.sultanmyrza.booking.model.Booking;
import com.sultanmyrza.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public List<Booking> getAllBookings() {

        return bookingService.getAllBookings();
    }


    @RequestMapping(value = "customer/id/{customerId}/booking/add", method = RequestMethod.POST)
    public Booking addBooking(@PathVariable Integer customerId,
                                              @RequestBody Booking newBooking) {

        // TODO: add logic for error handling
        return bookingService.addBooking(customerId, newBooking);
    }


    @RequestMapping(value = "/booking/id/{bookingId}/update", method = RequestMethod.PUT)
    public Booking updateBooking(@PathVariable Integer bookingId,
                                 @RequestBody Booking updatedBooking) {
        /**
         * return null if can not update
         * must return error message that can not update
         */
        // TODO: add logic for error handling
        // TODO: add response for error
        updatedBooking = bookingService.updateBooking(bookingId, updatedBooking);
        return updatedBooking;
    }


    @RequestMapping(value = "/booking/id/{bookingId}/delete", method = RequestMethod.DELETE)
    public Booking deleteBooking(@PathVariable Integer bookingId) {

        //  TODO: return proper response when delete same booking 2 times
        Booking deletedBooking = bookingService.deleteBooking(bookingId);

        // TODO: use adapter and return booking with customer (normally customer is @json ignored)
        return deletedBooking;
    }

    @RequestMapping(value = "/bookings/state/{bookingState}", method = RequestMethod.GET)
    public List<Booking> filterByState(@PathVariable String bookingState) {

        return bookingService.findByState(bookingState);
    }
}
