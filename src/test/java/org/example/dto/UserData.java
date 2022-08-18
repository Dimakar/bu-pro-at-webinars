package org.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserData {
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
    private String address;
}
