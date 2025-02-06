package kz.sayat.diploma_backend.controller;

import kz.sayat.diploma_backend.dto.LectureDto;
import kz.sayat.diploma_backend.dto.ModuleDto;
import kz.sayat.diploma_backend.models.Lecture;
import kz.sayat.diploma_backend.models.Module;
import kz.sayat.diploma_backend.service.LectureService;
import kz.sayat.diploma_backend.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;
    private final LectureService lectureService;

    @PostMapping("/{courseId}/modules")
    public ResponseEntity<ModuleDto> createModule(@RequestBody ModuleDto dto,
                                  @PathVariable(name = "courseId")int courseId) {
        dto.setCourseId(courseId);
        Module savedModule = moduleService.createModule(dto);
        dto.setId(savedModule.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/modules/{moduleId}")
    public ResponseEntity<ModuleDto> getModule(@PathVariable("moduleId") int id) {
        return ResponseEntity.ok().body(moduleService.findModuleById(id));
    }

    @GetMapping("/modules/{moduleId}/lectures")
    public ResponseEntity<List<LectureDto>> getAllLectures(@PathVariable("moduleId") int moduleId) {
        List<LectureDto> lecturesDto = lectureService.findAllLecturesByModuleId(moduleId);
        return ResponseEntity.ok().body(lecturesDto);
    }

}
