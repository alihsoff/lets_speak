package az.speak.ms.lets_speak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String courseName;

    private String tariffName;

    private String studentName;

    private String teacherName;

    private BigDecimal price;
}