package com.sultanmyrza.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sultanmyrza on 17. 5. 29.
 */

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private List<Booking> bookings = new ArrayList<>();

    @ManyToMany(mappedBy = "customers")
    private List<Community> communities = new ArrayList<>();

    @ManyToMany
    private List<Community> test = new ArrayList<>();

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonIgnore
    public List<Booking> getBookings() {
        return bookings;
    }

    @JsonIgnore
    public List<Community> getCommunities() {
        return communities;
    }

    public void addCommunity(Community newCommunity) {
        this.communities.add(newCommunity);
    }
}
