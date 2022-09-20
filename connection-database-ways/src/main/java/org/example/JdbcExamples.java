package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcExamples {
    public static void main(String[] args) throws SQLException {
        insertUser();
    }

    public static List<User> getUserByName(String name) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5438/postgres", "postgres", "postgres")) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> userList = new ArrayList<>();
            while (resultSet.next()) {
                userList.add(new User(resultSet.getInt("user_id"), resultSet.getString("name")));
            }

            System.out.println(userList);
            return userList;
        }
    }

    public static void insertUser() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5438/postgres", "postgres", "postgres")) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (user_id, name) values (?, ?)");
            preparedStatement.setInt(1, 1111111);
            preparedStatement.setString(2, "User 1111111");
            preparedStatement.executeUpdate();
            getUserByName("User 1111111");
        }
    }
}
