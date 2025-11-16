package com.example.hotelbooking.controller;

import com.example.hotelbooking.dto.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/pay")   // 🔥 important: /api/pay
public class PaymentController {

    @Value("${razorpay.key-id}")
    private String razorpayKeyId;

    @PostMapping("/create-order")   // 🔥 final path = /api/pay/create-order
    public ResponseEntity<Map<String, Object>> createOrder(
            @RequestBody PaymentRequest request) {

        long amountInPaise = Math.round(request.getAmount() * 100);

        String fakeOrderId = "order_" + request.getBookingId()
                + "_" + System.currentTimeMillis();

        Map<String, Object> body = new HashMap<>();
        body.put("id", fakeOrderId);        // order_id: data.id
        body.put("amount", amountInPaise);  // amount: data.amount
        body.put("currency", "INR");
        body.put("key", razorpayKeyId);     // key: data.key

        return ResponseEntity.ok(body);
    }
}