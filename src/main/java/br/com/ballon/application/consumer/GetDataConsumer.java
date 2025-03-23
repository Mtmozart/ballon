package br.com.ballon.application.consumer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GetDataConsumer(

        @NotBlank(message = "O nome deve ser obrigatório")
        @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
        String name,
        @NotBlank(message = "O e-mail deve ser obrigatório")
        @Email(message = "E-mail inválido")
        String email,
        @NotBlank(message = "A senha é obrigatporia")
        @Size(min = 5, max = 30, message = "O nome deve ter entre 5 e 30 caracteres")
        String password

) {
}
