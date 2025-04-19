package br.com.ballon.application.consumer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record GetDataConsumer(


        @NotBlank(message = "{name.required}")
        @Size(min = 3, max = 60, message = "{name.size}")
        @Pattern(regexp = "^[\\p{L} ]+$", message = "{nome.invalido}")
        String name,

        @NotBlank(message = "{email.required}")
        @Email(message = "{email.invalid}")
        String email,

        @NotBlank(message = "{password.required}")
        @Size(min = 8, max = 30, message = "{password.size}")
        String password
) {
}
