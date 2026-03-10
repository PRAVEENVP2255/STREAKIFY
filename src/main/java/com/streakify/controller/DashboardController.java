package com.streakify.controller;

import com.streakify.dto.DashboardResponseDTO;
import com.streakify.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/users/{userId}/dashboard")
    public DashboardResponseDTO getDashboard(@PathVariable Long userId) {
        return dashboardService.getDashboard(userId);
    }
}