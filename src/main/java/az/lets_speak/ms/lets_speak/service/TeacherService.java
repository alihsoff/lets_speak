package az.lets_speak.ms.lets_speak.service;

import az.lets_speak.ms.lets_speak.dto.TeacherDTO;
import az.lets_speak.ms.lets_speak.model.Teacher;
import az.lets_speak.ms.lets_speak.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    private TeacherDTO dto = new TeacherDTO();

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher getTeacherById(int id){
        return teacherRepository.getOne(id);
    }


    public TeacherDTO getTeacherByStudentId(int id){
        dto.setBirthDate(teacherRepository.getTeacherByStudentId(id).getBirthDate());
        dto.setName(teacherRepository.getTeacherByStudentId(id).getName());
        dto.setSurname(teacherRepository.getTeacherByStudentId(id).getSurname());
        dto.setEmail(teacherRepository.getTeacherByStudentId(id).getEmail());
        dto.setSkype(teacherRepository.getTeacherByStudentId(id).getSkype());
        dto.setPhoneNumber(teacherRepository.getTeacherByStudentId(id).getPhoneNumber());
        return dto;
    }
}
