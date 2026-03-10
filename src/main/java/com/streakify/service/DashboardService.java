package com.streakify.service;

import com.streakify.dto.DashboardResponseDTO;

public interface DashboardService {

    DashboardResponseDTO getDashboard(Long userId);
}