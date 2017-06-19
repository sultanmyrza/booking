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


    @RequestMapping(value = "/booking/add", method = RequestMethod.POST)
    public HashMap<String, String> addBooking(@RequestBody Booking newBooking) {

        HashMap<String, String> response;
        response = bookingService.addBooking(newBooking);

        return response;
    }


    @RequestMapping(value = "/booking/update/{bookingId}", method = RequestMethod.PUT)
    public HashMap<String, String> updateBooking(@PathVariable Integer bookingId,
                                                 @RequestBody Booking updatedBooking) {

        HashMap<String, String> response;
        response = bookingService.updateBooking(bookingId, updatedBooking);

        return response;
    }


    @RequestMapping(value = "/booking/delete/{bookingId}", method = RequestMethod.DELETE)
    public HashMap<String, String> deleteBooking(@PathVariable Integer bookingId) {

        HashMap<String, String> response;
        response = bookingService.deleteBooking(bookingId);

        return response;
    }

    @RequestMapping(value = "/bookings/state/{bookingState}", method = RequestMethod.GET)
    public List<Booking> filterByState(@PathVariable String bookingState) {

        return bookingService.findByState(bookingState);
    }
}
