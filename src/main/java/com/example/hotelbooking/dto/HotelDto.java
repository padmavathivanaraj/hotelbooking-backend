package com.example.hotelbooking.dto;

public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String description;
    private Double rating;

    public HotelDto() {}

    public HotelDto(Long id, String name, String city, String address, String description, Double rating) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.description = description;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}

