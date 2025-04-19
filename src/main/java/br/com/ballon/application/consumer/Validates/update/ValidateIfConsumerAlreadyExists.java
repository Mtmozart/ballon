package br.com.ballon.application.consumer.Validates.update;

import br.com.ballon.application.consumer.GetDataConsumer;
import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.infra.user.ConsumerEntityRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("updateConsumerValidator")
public class ValidateIfConsumerAlreadyExists implements ConsumerValidationUpdate {

    private final ConsumerEntityRepository repository;

    public ValidateIfConsumerAlreadyExists(ConsumerEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(GetDataConsumer update, UUID id) {
        repository.findByEmailToValidate(update.email())
                .filter(otherConsumer -> !otherConsumer.getId().equals(id))
                .ifPresent(otherConsumer -> {
                    throw new BallonException("E-mail cadastrado por outro usuário.");
                });
    }
}
