package br.com.ballon.application.consumer;

import br.com.ballon.infra.security.TokenService;
import br.com.ballon.utils.ConsumerMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {
    private final ConsumerService consumerService;
    private final TokenService tokenService;
    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    public ConsumerController(ConsumerService consumerService, TokenService tokenService) {
        this.consumerService = consumerService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("online");
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataConsumer> create(@RequestBody @Valid GetDataConsumer data) {
        return ResponseEntity.status(201).body(consumerService.create(ConsumerMapper.toDomainByRegisterDto(data)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataConsumer> update(@PathVariable UUID id, @Valid @RequestBody GetDataConsumer data) {
        return ResponseEntity.ok(consumerService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable UUID id) {
        consumerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    @Transactional
    public ResponseEntity<DataConsumer> profile(HttpServletRequest request) {
        String token = tokenService.recoverToken(request);
        String idStr = this.tokenService.getId(token);
        UUID id = UUID.fromString(idStr);
        return ResponseEntity.ok(consumerService.findById(id));
    }
}
