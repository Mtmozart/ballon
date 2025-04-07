package br.com.ballon.application.authenctication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(
        @Email()
        String email,

        @NotBlank
        String password

) {
}
