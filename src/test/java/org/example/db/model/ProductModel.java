package org.example.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel {

    @JsonProperty("_id")
    private ObjectId id;

    @JsonProperty("info")
    private InfoModel info;

    @JsonProperty("tags")
    private TagsModel tags;
}