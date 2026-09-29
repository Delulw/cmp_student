package com.comp_alumno.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
		@NotBlank @Size(max = 120) String nombre,
		@NotBlank @Email @Size(max = 254) String email) {
}
