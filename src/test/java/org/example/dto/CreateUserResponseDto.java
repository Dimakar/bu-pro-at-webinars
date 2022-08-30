package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserResponseDto {
    private String password;
    private String address;
    private String phone;
    private List<Order> orders;
    private String id;
    private String email;
    private String username;
    private String token;
}
