package br.com.ballon.application.expenses;

import br.com.ballon.utils.ExpenseMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public void update(@PathVariable UUID id, @Valid @RequestBody Object data) {
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable UUID id) {

    }

    @GetMapping("/{id}")
    public void findOne(@PathVariable UUID id) {

    }

    @GetMapping("/user/{id}")
    public void findAll(@PathVariable UUID id) {

    }
}
