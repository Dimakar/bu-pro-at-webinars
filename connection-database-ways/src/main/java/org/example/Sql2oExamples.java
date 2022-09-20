package org.example;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.Map;

public class Sql2oExamples {
    static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5438/postgres", "postgres", "postgres");

    public static void main(String[] args) {
        insertUser();
    }

    public static List<User> getUserByName(String name) {
        sql2o.setDefaultColumnMappings(Map.of("user_id", "userId"));
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("select * from users where name = :name")
                    .addParameter("name", name)
                    .executeAndFetch(User.class);
        }
    }

    public static void insertUser() {
        try (Connection connection = sql2o.beginTransaction()) {
            connection.createQuery("insert into users (user_id, name) values (:userId, :name)")
                    .bind(new User(101010101, "User101010101"))
                    .executeUpdate();
            connection.commit();
            System.out.println(getUserByName("User101010101"));
        }
    }
}
