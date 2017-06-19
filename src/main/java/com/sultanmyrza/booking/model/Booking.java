package com.sultanmyrza.booking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@Entity
public class Booking {

    // by marking field with @Id we tell spring that
    // we will use it as primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String state;

    public Booking() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
