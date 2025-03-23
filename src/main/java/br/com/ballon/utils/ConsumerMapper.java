package br.com.ballon.utils;

import br.com.ballon.application.consumer.DataConsumer;
import br.com.ballon.application.consumer.GetDataConsumer;
import br.com.ballon.domain.user.Consumer;
import br.com.ballon.domain.user.Profile;
import br.com.ballon.infra.user.ConsumerEntity;

import java.time.Instant;
import java.util.UUID;

public class ConsumerMapper {

    public static Consumer toDomainByRegisterDto(GetDataConsumer data) {
        return new Consumer.ConsumerBuilder()
                .withName(data.name())
                .withEmail(data.email())
                .withPassword(data.password())
                .withProfile(Profile.CONSUMER)
                .build();
    }

    public static ConsumerEntity toEntity(Consumer consumer) {
        return new ConsumerEntity(
                consumer.getId(),
                consumer.getName(),
                consumer.getEmail(),
                consumer.getPassword(),
                consumer.getProfile(),
                Instant.now(),
                null,
                null
        );
    }

    public static DataConsumer toDto(ConsumerEntity entity) {
        return new DataConsumer(entity.getId(), entity.getFullName(), entity.getEmail());
    }

    public static DataConsumer fromGetDataConsumerToDataConsumerUpdate(UUID id, GetDataConsumer data) {
        return new DataConsumer(id, data.name(), data.email());
    }
}
