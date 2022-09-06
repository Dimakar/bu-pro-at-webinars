package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

// TODO: 06.09.2022 divide to different models (db and api)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhoneDto {
    @JsonProperty("_id")
    private ObjectId id;
    private Info info;
    private Tags tags;
}