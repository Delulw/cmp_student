package com.cmp_student.dto;

import com.cmp_student.constraints.SanitizedHtml;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerDto {

    private Integer id;
    private String name;
    private String jersey;
    @SanitizedHtml(message = "Potentially dangerous HTML code was detected in the biography.")
    private String country;
    private String position;

}
