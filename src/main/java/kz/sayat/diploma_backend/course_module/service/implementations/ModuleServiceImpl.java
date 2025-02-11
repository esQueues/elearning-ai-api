package kz.sayat.diploma_backend.course_module.service.implementations;


import kz.sayat.diploma_backend.course_module.dto.LectureDto;
import kz.sayat.diploma_backend.course_module.dto.ModuleDto;
import kz.sayat.diploma_backend.course_module.dto.QuizSummaryDto;
import kz.sayat.diploma_backend.course_module.mapper.ModuleMapper;
import kz.sayat.diploma_backend.course_module.models.Course;
import kz.sayat.diploma_backend.course_module.models.Module;
import kz.sayat.diploma_backend.course_module.repository.CourseRepository;
import kz.sayat.diploma_backend.course_module.repository.ModuleRepository;
import kz.sayat.diploma_backend.course_module.service.ModuleService;
import kz.sayat.diploma_backend.quiz_module.service.implementation.QuizServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper mapper;
    private final CourseRepository courseRepository;
    private final QuizServiceImpl quizService;
    private final LectureServiceImpl lectureService;

    @Override
    public Module createModule(ModuleDto dto) {
        Course course = courseRepository.findById(dto.getCourseId())
            .orElseThrow(() -> new NoSuchElementException("Course with ID " + dto.getCourseId() + " not found"));
        Module module = mapper.toModule(dto);
        module.setCourse(course);
        return moduleRepository.save(module);
    }

    @Override
    public ModuleDto findModuleById(int id) {
        Module module= moduleRepository.findById(id).orElseThrow(()
            -> new NoSuchElementException("Module with ID " + id + " not found"));

        List<QuizSummaryDto> quizzes = quizService.findAllQuizByModuleId(id);
        List<LectureDto> lectures = lectureService.findAllLecturesByModuleId(id);

        ModuleDto dto = mapper.toModuleDto(module);
        dto.setQuizzes(quizzes);
        dto.setLectures(lectures);
        return dto;
    }
}
