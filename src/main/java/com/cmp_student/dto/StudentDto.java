package com.cmp_student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.ObjectMapper;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Integer id;
    private String name;
    private String lastName;
    private String phone;
    private String eMail;

    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }

}
