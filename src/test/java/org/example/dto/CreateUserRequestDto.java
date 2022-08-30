package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequestDto {
    @JsonProperty("username")
    private String userName;
    private String password;
    @JsonProperty("phone")
    private String phoneNumber;
    private String email;
    private String address;
}
