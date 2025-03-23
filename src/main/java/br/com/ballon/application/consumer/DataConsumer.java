package br.com.ballon.application.consumer;

import java.util.UUID;

public record DataConsumer(
        UUID id,
        String name,
        String email
) {
}
