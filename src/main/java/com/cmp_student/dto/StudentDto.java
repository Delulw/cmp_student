package com.cmp_student.dto;

import com.cmp_student.constraints.SanitizedHtml;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {

    private Integer id;
    // 2. Anotación para validar
    @NotBlank(message = "Name no debe estar vacío")
    private String name;
    @SanitizedHtml(message = "Country contiene código malicioso")
    private String jersey;
    // 2. Anotación "Custom" para sanitizar entradas -> ver código de constraints
    @SanitizedHtml(message = "Potentially dangerous HTML code was detected in the biography.")
    private String country;
    private String position;

}
