package org.example.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @JsonProperty("password")
    private String password;

    @JsonProperty("__v")
    private int V;

    @JsonProperty("orders")
    private List<OrdersModel> orders;

    @JsonProperty("_id")
    private Object id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("token")
    private String token;
}