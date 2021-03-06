package az.speak.ms.lets_speak.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "students")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private String email;

    private String skype;

    private String phoneNumber;

    private LocalDate birthDate;

    private Double rank = 0.0;

    private String photo;

    @ManyToMany(mappedBy = "students")
    @JsonBackReference
    private Set<TeacherEntity> teachers;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private Set<TaskEntity> tasks;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private Set<OrderEntity> orders;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    @JsonManagedReference
    private Set<ScheduleEntity> schedules;

}
