package org.example.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoModel {

    @JsonProperty("os")
    private String os;

    @JsonProperty("color")
    private String color;

    @JsonProperty("displayResolution")
    private String displayResolution;

    @JsonProperty("weight")
    private String weight;

    @JsonProperty("cpu")
    private String cpu;

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("internalMemory")
    private String internalMemory;

    @JsonProperty("displaySize")
    private String displaySize;

    @JsonProperty("displayType")
    private String displayType;

    @JsonProperty("price")
    private double price;

    @JsonProperty("name")
    private String name;

    @JsonProperty("camera")
    private String camera;

    @JsonProperty("dimensions")
    private String dimensions;

    @JsonProperty("ram")
    private String ram;

    @JsonProperty("batery")
    private String batery;
}