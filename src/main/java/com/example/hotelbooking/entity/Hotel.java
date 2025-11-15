package com.example.hotelbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(length = 1000)
    private String description;

    private Double rating;

    @Column(nullable = false)
    private Double price;  // 🔥 NEW

    public Hotel() {}

    // constructor that includes price (and maybe rating)
    public Hotel(String name, String city, String address,
                 String description, Double rating, Double price) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.price = price;
    }

    // getters / setters (add for price too)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // (rest of getters/setters already there)
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
