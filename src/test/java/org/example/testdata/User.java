package org.example.testdata;

import io.restassured.http.Header;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String username;
    private String password;
    private String token;

    public Header getTokenHeader() {
        return new Header("Authorization", "Bearer " + this.getToken());
    }


}
