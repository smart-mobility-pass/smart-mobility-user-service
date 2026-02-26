package com.mobilitypass.user_mobility.controller;

import com.mobilitypass.user_mobility.beans.MobilityPass;
import com.mobilitypass.user_mobility.service.PassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passes")
public class PassController {
    @Autowired
    private PassService passService;

    @PostMapping("/activate/{userId}")
    public ResponseEntity<MobilityPass> activate(@PathVariable Long userId) {
        return ResponseEntity.ok(passService.activatePass(userId));
    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long userId, @RequestParam String status) {
        passService.changePassStatus(userId, status);
        return ResponseEntity.noContent().build();
    }


}
