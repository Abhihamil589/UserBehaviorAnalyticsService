package com.abhihamil.ubas.service;

import com.abhihamil.ubas.collection.UserBehaviorAnalytics;

import java.time.LocalDateTime;
import java.util.List;

public interface UserBehaviorAnalyticsService {
    List<UserBehaviorAnalytics> saveAllUserBehavior(List<UserBehaviorAnalytics> userBehaviorAnalyticsList);

    List<UserBehaviorAnalytics> getAllUserBehavior();

    Long getUniqueActiveUsersCount(LocalDateTime startTime, LocalDateTime endTime);

    List<String> getTopProductsSold(int limit);

    Double getTotalRevenue(LocalDateTime startTime, LocalDateTime endTime);
}
