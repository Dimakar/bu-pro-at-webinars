package org.example.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartModel {

    @JsonProperty("__v")
    private int V;

    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("user")
    private String user;

    @JsonProperty("items")
    private List<ItemsModel> items;
}