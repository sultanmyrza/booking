package com.sultanmyrza.booking.service.statePattern.states;

import com.sultanmyrza.booking.service.statePattern.BookingStateMachine;
import com.sultanmyrza.booking.service.statePattern.BookingState;

import java.util.HashMap;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */
public class BookedState implements BookingState {

    private BookingStateMachine bookingStateMachine;
    private HashMap<String, String> response;

    public BookedState(BookingStateMachine bookingStateMachine) {
        this.bookingStateMachine = bookingStateMachine;
        response = new HashMap<>();
    }

    @Override
    public HashMap<String, String> book() {

        response.put("status", "error");
        response.put("info", "Can't book! Already booked");

        return response;
    }

    @Override
    public HashMap<String, String> cancel() {

        response.put("status", "success");
        response.put("info", "Can cancel");
        bookingStateMachine.setCurrentState(bookingStateMachine.getCanceledState());

        return response;
    }
}
