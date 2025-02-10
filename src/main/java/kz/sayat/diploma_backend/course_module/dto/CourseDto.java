package kz.sayat.diploma_backend.course_module.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private int id;
    private String title;
    private String description;
    private List<ModuleDto> modules;
}
