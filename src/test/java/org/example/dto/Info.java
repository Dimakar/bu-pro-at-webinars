package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Info {
    private String os;
    private String color;
    private String displayResolution;
    private String weight;
    private String cpu;
    private String photo;
    private String internalMemory;
    private String displaySize;
    private String displayType;
    private int price;
    private String name;
    private String camera;
    private String dimensions;
    private String ram;
    private String batery;
}