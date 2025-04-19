package br.com.ballon.application.consumer;

import br.com.ballon.application.consumer.Validates.create.ConsumerValidation;
import br.com.ballon.application.consumer.Validates.update.ConsumerValidationUpdate;
import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.domain.user.Consumer;
import br.com.ballon.infra.security.EncryptPassword;
import br.com.ballon.infra.user.ConsumerEntity;
import br.com.ballon.infra.user.ConsumerEntityRepository;
import br.com.ballon.infra.user.ProfileEntity;
import br.com.ballon.infra.user.ProfileEntityRepository;
import br.com.ballon.utils.ConsumerMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ConsumerService implements IConsumer<Consumer, UUID, GetDataConsumer, DataConsumer> {
    private final ConsumerEntityRepository consumerEntityRepository;
    private final EncryptPassword encryptPassword;
    private final ProfileEntityRepository profileEntityRepository;
    private final List<ConsumerValidation> validations;
    private final List<ConsumerValidationUpdate> validationUpdates;

    public ConsumerService(ConsumerEntityRepository consumerEntityRepository, EncryptPassword encryptPassword, ProfileEntityRepository profileEntityRepository, List<ConsumerValidation> validations, List<ConsumerValidationUpdate> validationUpdates) {
        this.consumerEntityRepository = consumerEntityRepository;
        this.encryptPassword = encryptPassword;
        this.profileEntityRepository = profileEntityRepository;
        this.validations = validations;
        this.validationUpdates = validationUpdates;
    }


    @Override
    public DataConsumer create(Consumer consumer) {
        validations.forEach(v -> v.validate(consumer));
        ConsumerEntity consumerEntity = this.consumerEntityRepository.save(ConsumerMapper.toEntity(consumer));
        consumerEntity.setPassword(this.encryptPassword.encrypt(consumerEntity.getPassword()));
        ProfileEntity profileEntity = profileEntityRepository
                .findByType(consumer.getProfile())
                .orElseThrow(() -> new BallonException("Perfil não encontrado"));
        consumerEntity.addProfile(profileEntity);
        return ConsumerMapper.toDto(this.consumerEntityRepository.save(consumerEntity));
    }

    @Override
    public DataConsumer update(UUID id, GetDataConsumer dataUpdateConsumer) {
        validationUpdates.forEach(v -> v.validate(dataUpdateConsumer, id));
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
