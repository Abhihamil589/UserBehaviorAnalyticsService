package com.abhihamil.ubas.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "user_interactions")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBehaviorAnalytics {
    @Id
    private String id;
    private Date timestamp;
    private String userId;
    private String pageVisited;
    private String action;
    private String productId;
    private double price;
}
