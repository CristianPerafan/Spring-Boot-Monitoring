package com.cristian.monitoringback.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Data
@Getter
@Setter
public class ProductDTO {
    @NotEmpty(message = "Name is required")
    @Size(max = 255)
    private String name;
    @NotEmpty(message = "Description is required")
    @Size(max = 255)
    private String description;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;
}
