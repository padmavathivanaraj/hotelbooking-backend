package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Hotel;
import com.example.hotelbooking.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@CrossOrigin(origins = "http://localhost:5173")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // GET /api/hotels  -> all hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // GET /api/hotels/{id}  -> single hotel by id
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Hotel hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    // 🔥 POST /api/hotels  -> create new hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
      Hotel saved = hotelService.createHotel(hotel);
      return ResponseEntity.ok(saved);
    }

    // GET /api/hotels/search?city=Chennai
    @GetMapping("/search")
    public List<Hotel> searchByCity(@RequestParam String city) {
        return hotelService.searchByCity(city);
    }
}
