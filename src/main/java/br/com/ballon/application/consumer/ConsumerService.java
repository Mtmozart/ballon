package br.com.ballon.application.consumer;

import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.domain.user.Consumer;
import br.com.ballon.infra.user.ConsumerEntity;
import br.com.ballon.infra.user.ConsumerEntityRepository;
import br.com.ballon.utils.ConsumerMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class ConsumerService implements IConsumer<Consumer, UUID, GetDataConsumer, DataConsumer> {
    private final ConsumerEntityRepository consumerEntityRepository;

    public ConsumerService(ConsumerEntityRepository consumerEntityRepository) {
        this.consumerEntityRepository = consumerEntityRepository;
    }


    @Override
    public DataConsumer create(Consumer consumer) {
        ConsumerEntity consumerEntity = this.consumerEntityRepository.save(ConsumerMapper.toEntity(consumer));
        return ConsumerMapper.toDto(this.consumerEntityRepository.save(consumerEntity));
    }

    @Override
    public DataConsumer update(UUID id, GetDataConsumer dataUpdateConsumer) {
        this.consumerEntityRepository
                .findById(id).orElseThrow(() -> new BallonException("Usuário não encontrado"));
        this.consumerEntityRepository.updatesEntity(
                dataUpdateConsumer.name(),
                dataUpdateConsumer.email(),
                id,
                Instant.now()
        );
        return ConsumerMapper.fromGetDataConsumerToDataConsumerUpdate(id, dataUpdateConsumer);
    }

    @Override
    public void delete(UUID id) {
        ConsumerEntity consumerEntity = this.consumerEntityRepository
                .findById(id).orElseThrow(() -> new BallonException("Usuário não encontrado"));
        this.consumerEntityRepository.delete(consumerEntity);
    }

    @Override
    public DataConsumer findById(UUID id) {
        return ConsumerMapper.toDto(this.consumerEntityRepository
                .findById(id).orElseThrow(() -> new BallonException("Usuário não encontrado")));
    }
}
