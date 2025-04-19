package br.com.ballon.application.consumer.Validates.update;

import br.com.ballon.application.consumer.GetDataConsumer;
import br.com.ballon.domain.user.Consumer;
import br.com.ballon.infra.user.ConsumerEntity;

import java.util.Optional;
import java.util.UUID;

public interface ConsumerValidationUpdate {
    void validate (GetDataConsumer update, UUID id);
}
