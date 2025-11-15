package com.example.hotelbooking.controller;

import com.example.hotelbooking.dto.BookingRequest;
import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // 🔥 JSON-based booking endpoint
    @PostMapping("/bookings")
    public ResponseEntity<Map<String, Object>> createBooking(
            @RequestBody BookingRequest request
    ) {
        // use values from JSON body
        Booking booking = bookingService.createBooking(
                request.getUserId(),
                request.getRoomId(),
                request.getCheckIn(),
                request.getCheckOut()
        );

        Map<String, Object> body = new HashMap<>();
        body.put("id", booking.getId());
        body.put("userId", booking.getUser().getId());
        body.put("roomId", booking.getRoom().getId());
        body.put("checkIn", booking.getCheckIn());
        body.put("checkOut", booking.getCheckOut());
        body.put("amount", booking.getAmount());
        body.put("status", booking.getStatus());

        return ResponseEntity.ok(body);
    }
}
