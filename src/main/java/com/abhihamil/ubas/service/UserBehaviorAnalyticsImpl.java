package com.abhihamil.ubas.service;

import com.abhihamil.ubas.collection.UserBehaviorAnalytics;
import com.abhihamil.ubas.repository.UserBehaviorAnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserBehaviorAnalyticsImpl implements UserBehaviorAnalyticsService {

    @Autowired
    private UserBehaviorAnalyticsRepository userBehaviorAnalyticsRepository;

    @Override
    public List<UserBehaviorAnalytics> saveAllUserBehavior(List<UserBehaviorAnalytics> userBehaviorAnalyticsList) {
        return userBehaviorAnalyticsRepository.saveAll(userBehaviorAnalyticsList);
    }

    @Override
    public List<UserBehaviorAnalytics> getAllUserBehavior() {
        return userBehaviorAnalyticsRepository.findAll();
    }

    @Override
    public Long getUniqueActiveUsersCount(LocalDateTime startTime, LocalDateTime endTime) {
        List<UserBehaviorAnalytics> interactions = userBehaviorAnalyticsRepository.findByTimestampBetween(startTime, endTime);

        Set<String> activeUserIds = new HashSet<>();
        for (UserBehaviorAnalytics interaction : interactions) {
            activeUserIds.add(interaction.getUserId());
        }
        return (long) activeUserIds.size();
    }

    @Override
    public List<String> getTopProductsSold(int limit) {
        List<UserBehaviorAnalytics> interactions = userBehaviorAnalyticsRepository.findAll();

        Map<String, Long> productSalesCount = interactions.stream()
                .filter(interaction -> interaction.getAction().equalsIgnoreCase("purchase"))
                .collect(Collectors.groupingBy(UserBehaviorAnalytics::getProductId, Collectors.counting()));

        return productSalesCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalRevenue(LocalDateTime startTime, LocalDateTime endTime) {
        List<UserBehaviorAnalytics> interactions = userBehaviorAnalyticsRepository.findByTimestampBetween(startTime, endTime);

        return interactions.stream()
                .filter(interaction -> interaction.getAction().equalsIgnoreCase("purchase"))
                .mapToDouble(UserBehaviorAnalytics::getPrice)
                .sum();
    }
}
