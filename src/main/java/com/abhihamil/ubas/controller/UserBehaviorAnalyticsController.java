package com.abhihamil.ubas.controller;

import com.abhihamil.ubas.collection.UserBehaviorAnalytics;
import com.abhihamil.ubas.service.UserBehaviorAnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ubas/data")
public class UserBehaviorAnalyticsController {
    @Autowired
    private UserBehaviorAnalyticsService userBehaviorAnalyticsService;

    @PostMapping
    public List<UserBehaviorAnalytics> saveUserBehavior(@RequestBody List<UserBehaviorAnalytics> userBehaviorAnalyticsList) {
        return userBehaviorAnalyticsService.saveAllUserBehavior(userBehaviorAnalyticsList);
    }

    @GetMapping
    public List<UserBehaviorAnalytics> getAllUserBehavior() {
        return userBehaviorAnalyticsService.getAllUserBehavior();
    }

    @GetMapping("/activeUsers")
    public Long getUniqueActiveUsersCount(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        System.out.println("inside getUniqueActiveUsersCount");
        return userBehaviorAnalyticsService.getUniqueActiveUsersCount(startTime, endTime);
    }

    @GetMapping("/topProductsSold")
    public List<String> getTopProductsSold(
            @RequestParam("limit") int limit) {
        System.out.println("inside getTopProductsSold");

        return userBehaviorAnalyticsService.getTopProductsSold(limit);
    }

    @GetMapping("/totalRevenue")
    public Double getTotalRevenue(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        System.out.println("inside getTotalRevenue");

        return userBehaviorAnalyticsService.getTotalRevenue(startTime, endTime);
    }

}
