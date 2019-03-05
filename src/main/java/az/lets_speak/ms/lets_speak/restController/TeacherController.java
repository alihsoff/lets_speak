package az.lets_speak.ms.lets_speak.restController;

import az.lets_speak.ms.lets_speak.dto.TeacherDTO;
import az.lets_speak.ms.lets_speak.service.TeacherService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(value = "/api", description = "Операции с профилем")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/student/get/teacher/{id}")
    public TeacherDTO getTeacherByStudentId(@PathVariable int id){
        return teacherService.getTeacherByStudentId(id);
    }
}
