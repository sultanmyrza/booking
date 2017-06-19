package com.sultanmyrza.booking.service.statePattern.states;

import com.sultanmyrza.booking.service.statePattern.BookingMachine;
import com.sultanmyrza.booking.service.statePattern.BookingState;

import java.util.HashMap;

/**
 * Created by sultanmyrza on 17. 5. 30.
 */
public class CanceledState implements BookingState {

    BookingMachine bookingMachine;
    private HashMap<String, String> response;

    public CanceledState(BookingMachine bookingMachine) {
        this.bookingMachine = bookingMachine;
        response = new HashMap<>();
    }

    @Override
    public HashMap<String, String> book() {

        System.out.println("CanceledState.book");
        response.put("status", "success");
        response.put("info", "Can book.");
        // TODO: 17. 5. 30 Change state to booked [DONE]
        bookingMachine.setCurrentState(bookingMachine.getBookedState());

        return response;
    }

    @Override
    public HashMap<String, String> cancel() {

        System.out.println("CanceledState.cancel");
        response.put("status", "error");
        response.put("info", "Can not cancel. Already canceled.");

        return response;
    }
}
