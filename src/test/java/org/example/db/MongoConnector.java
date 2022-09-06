package org.example.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.example.dto.PhoneDto;

public class MongoConnector {
    private static MongoClient mongoClient;

    private static MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create("mongodb://root:password@192.168.235.13:27017/");
        }
        return mongoClient;
    }

    public static MongoDatabase getDataBase() {
        CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        return getMongoClient().getDatabase("mobileShop").withCodecRegistry(codecRegistry);
    }


    public static void main(String[] args) {
        System.out.println(getDataBase().getCollection("products", PhoneDto.class)
                .find(Filters.eq("info.name", "Apple iPhone X"))
                .first());
    }
}
