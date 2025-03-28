package br.com.ballon.application.expenses;

import br.com.ballon.utils.ExpenseMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expense")
public class ExpensesController {

    private final ExpenseService service;

    public ExpensesController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("online");
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataExpense> create(@RequestBody @Valid GetDataExpense data) {
        return ResponseEntity.ok().body(this.service.create(ExpenseMapper.toDomainByRegisterDto(data), data.consumerId(), data.categoriaId()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Boolean> update(@PathVariable UUID id, @Valid @RequestBody GetDataExpenseUpdate data) {
        return ResponseEntity.ok().body(this.service.update(id, ExpenseMapper.toDomainByUpdateDto(data), data.categoriaId()));
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable UUID id) {
        this.service.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataExpense> findOne(@PathVariable UUID id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<DataExpense>> findAll(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.service.findAllByUser(id));
    }
}
