package com.example.demo.services.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCityRequest {
    private long id;
    private String name;
    private String zipCode;
}
