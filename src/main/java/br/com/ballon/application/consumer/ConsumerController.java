package br.com.ballon.application.consumer;

import br.com.ballon.utils.ConsumerMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {
    private final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
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

    @GetMapping("/{id}")
    public ResponseEntity<DataConsumer> profile(@PathVariable UUID id) {
        return ResponseEntity.ok(consumerService.findById(id));
    }
}
