package org.example.testdata;

import com.mongodb.client.model.Filters;
import org.example.dto.CreateUserRequestDto;
import org.example.endpoints.ApiUserRegisterEndpoint;

import static org.example.db.MongoConnector.getDataBase;
import static org.example.testdata.TestDataGenerator.generateUserDto;

public class TestDataManager {
    public static User createNewUser() {
        CreateUserRequestDto requestDto = generateUserDto();
        User user = User.builder()
                .username(requestDto.getUserName())
                .password(requestDto.getPassword())
                .build();
        user.setToken(new ApiUserRegisterEndpoint().registerUser(requestDto).getToken());
        return user;
    }

    public static void deleteUser(User user) {
        deleteUser(user.getUsername());
    }

    public static void deleteUser(String userName) {
        getDataBase().getCollection("users")
                .deleteOne(Filters.eq("username", userName));
    }
}
