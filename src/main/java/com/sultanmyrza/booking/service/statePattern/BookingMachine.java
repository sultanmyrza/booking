package com.sultanmyrza.booking.service.statePattern;

import com.sultanmyrza.booking.service.statePattern.states.BookedState;
import com.sultanmyrza.booking.service.statePattern.states.CanceledState;

import java.util.HashMap;

/**
 * Created by sultanmyrza on 17. 5. 30.
 */
public class BookingMachine {

    private BookingState bookedState;
    private BookingState canceledState;
    private BookingState currentState;

    private HashMap<String, String> response;

    public BookingMachine(String initialState) {

        bookedState = new BookedState(this);
        canceledState = new CanceledState(this);

        if (initialState.equals("booked")) {
            currentState = bookedState;
        }
        if (initialState.equals("canceled")){
            currentState = canceledState;
        }
    }

    public BookingState getBookedState() {
        return bookedState;
    }

    public BookingState getCanceledState() {
        return canceledState;
    }

    public void setCurrentState(BookingState currentState) {
        this.currentState = currentState;
    }

    public HashMap<String, String> book() {

        System.out.println("BookingMachine.book");
        response = currentState.book();
        return response;
    }

    public HashMap<String, String> cancel() {

        System.out.println("BookingMachine.cancel");
        response = currentState.cancel();
        return response;
    }
}
