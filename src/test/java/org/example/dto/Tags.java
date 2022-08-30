package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tags {
    private String color;
    private String os;
    private String displayResolution;
    private String cpu;
    private String internalMemory;
    private String camera;
    private String priceRange;
    private String brand;
    private String displaySize;
    private String ram;
}