package com.abhihamil.ubas.repository;

import com.abhihamil.ubas.collection.UserBehaviorAnalytics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserBehaviorAnalyticsRepository extends MongoRepository<UserBehaviorAnalytics, String> {
    List<UserBehaviorAnalytics> findByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);
}
