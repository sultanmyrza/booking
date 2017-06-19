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

/**
 * By wrapping with @RestController spring do a lot of things for us
 * for example it automatically converts responses to JSON so
 * new Booking(id=13, title="test")  will be converted to
 * {"id": 13, "title": "test"}  by spring
 *
 * Our controller must contain url mapping logic
 * If it fetch data it must not know where it takes it from
 * by this our controller become flexible and it will work fine
 * if we change datasource because getting data implemented in
 * service layer. Controller only tells what it needs and service layer
 * handle getting data from (DB, or other microservice etc)
 */

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /**
     * @RequestMapping can handle ["GET", "POST", "PUT", "DELETE"]
     * by default @RequestMapping uses "GET" but we specify it by
     * giving method param
     * here is the convention for Request methods that spring follows
     * https://spring.io/understanding/REST#http-methods
     */
    @RequestMapping(value = "/bookings", method = RequestMethod.GET)
    public List<Booking> getAllBookings() {

        System.out.println("BookingController.getAllBookings");

        return bookingService.getAllBookings();
    }


    @RequestMapping(value = "/booking/add", method = RequestMethod.POST)
    public HashMap<String, String> addBooking(@RequestBody Booking newBooking) {

        /**
         * Here is the magic of spring comes
         * when we POST request with body={"id":3, "title": "new title"}
         * since this wrapped as RestController spring will convert it
         * to new Booking(id=3, "title"="new title") and we can use it here
         */
        System.out.println("BookingController.addBooking");

        HashMap<String, String> response;
        response = bookingService.addBooking(newBooking);

        return response;
    }


    @RequestMapping(value = "/booking/update/{bookingId}", method = RequestMethod.PUT)
    public HashMap<String, String> updateBooking(@PathVariable Integer bookingId, @RequestBody Booking updatedBooking) {

        /**
         * The way CrudRepository.save() works is
         * if booking with same id exist it will be updated
         * otherwise new one will be created so
         *
         * here we can use id of booking to update booking
         * but for preinternship it would work fine I hope :)
         * so I don't really use bookginId but it can be usefull later
         */
        System.out.println("BookingController.updateBooking");

        HashMap<String, String> response;
        response = bookingService.updateBooking(bookingId, updatedBooking);

        return response;
    }


    @RequestMapping(value = "/booking/delete/{bookingId}", method = RequestMethod.DELETE)
    public HashMap<String, String> deleteBooking(@PathVariable Integer bookingId) {

        System.out.println("BookingController.deleteBooking");

        HashMap<String, String> response;
        response = bookingService.deleteBooking(bookingId);

        return response;
    }

    @RequestMapping(value = "/bookings/state/{bookingState}", method = RequestMethod.GET)
    public List<Booking> filterByState(@PathVariable String bookingState) {

        System.out.println("BookingController.filterByState");

        return bookingService.findByState(bookingState);
    }
}
