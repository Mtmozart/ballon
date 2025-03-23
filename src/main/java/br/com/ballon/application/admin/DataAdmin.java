package br.com.ballon.application.admin;

import java.util.UUID;

public record DataAdmin(
        UUID id,
        String name,
        String email
) {
}
