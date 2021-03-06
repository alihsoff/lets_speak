package az.speak.ms.lets_speak.service;

import az.speak.ms.lets_speak.model.TaskEntity;
import az.speak.ms.lets_speak.model.TeacherEntity;
import az.speak.ms.lets_speak.repository.StudentRepository;
import az.speak.ms.lets_speak.repository.TaskRepository;
import az.speak.ms.lets_speak.repository.TeacherRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class FileUploadService {
    private final TeacherRepository teacherRepository;
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;
    private int i = 10;
    public FileUploadService(TeacherRepository teacherRepository, TaskRepository taskRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.taskRepository = taskRepository;
        this.studentRepository = studentRepository;
    }



    public String setCvForTeacher(Integer id, MultipartFile file){
        String name = file.getOriginalFilename();
        int lastIndexOf = name.lastIndexOf(".");

        File upload = new File("src\\main\\resources\\static\\uploads\\cv\\cv-"+
                id+"-" + i + name.substring(lastIndexOf));
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(upload));
                stream.write(bytes);
                stream.close();
                TeacherEntity entity = teacherRepository.getOne(id);
                entity.setCv("/uploads/cv/cv-"+ id+"-"+i+name.substring(lastIndexOf));
                teacherRepository.save(entity);

                i++;
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }

    public String taskUpload(Integer studentId, Integer teacherId, String taskName, MultipartFile file) {
        String name = file.getOriginalFilename();
        int lastIndexOf = name.lastIndexOf(".");

        File upload = new File("src\\main\\resources\\static\\uploads\\tasks\\task-"+
                studentId+"-" + teacherId+ "-" + i + name.substring(lastIndexOf));
        if (!file.isEmpty()&& name.substring(lastIndexOf).equals(".pdf")) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(upload));
                stream.write(bytes);
                stream.close();
                TaskEntity task = new TaskEntity();

                task.setUrl("/uploads/tasks/task-" + studentId + "-" + teacherId + "-" + i + name.substring(lastIndexOf));
                task.setUrl("/uploads/tasks/task-" + studentId + "-" + teacherId + "-" + i + "-" + taskName + name.substring(lastIndexOf));
                task.setName(taskName);
                task.setStudent(studentRepository.getOne(studentId));
                task.setTeacher(teacherRepository.getOne(teacherId));
                task.setCreatedDate(LocalDate.now());
                task.setExpirationDate(LocalDate.now().plusDays(10L));
                taskRepository.save(task);
                this.i++;
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else if (!name.substring(lastIndexOf).equals(".pdf")){
            return "Формат файла не поддерживается";
        }
        else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }
}
