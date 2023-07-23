package com.sbip.mc.models;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@ToString
public class Course {

    private Long id;
    private String name;
    private String category;

    @Min(value = 1, message = "A course should have a minimum of 1 rating")
    @Max(value = 5, message = "A course should have a maximum of 5 rating")
    private Integer rating;


}
