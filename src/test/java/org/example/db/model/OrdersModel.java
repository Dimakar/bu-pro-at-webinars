package org.example.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdersModel {

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("dateCreated")
    private double dateCreated;

    @JsonProperty("price")
    private int price;

    @JsonProperty("name")
    private String name;
}