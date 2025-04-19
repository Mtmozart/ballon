package br.com.ballon.application.consumer.Validates.create;

import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.domain.user.Consumer;
import br.com.ballon.infra.user.ConsumerEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidateIfConsumerAlreadyExists implements ConsumerValidation{

    private final ConsumerEntityRepository repository;

    public ValidateIfConsumerAlreadyExists(ConsumerEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Consumer data) {
        var consumer = this.repository.findByEmail(data.getEmail());
        if(consumer.isPresent()){
            throw new BallonException("Usuário já cadastrado.");
        }
    }
}
