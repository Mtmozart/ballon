package br.com.ballon.application.expenses;

import br.com.ballon.utils.ExpenseMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expenses")
@SecurityRequirement(name="bearer-key")
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

    @GetMapping("/users/{id}")
    public ResponseEntity<List<DataExpense>> findAll(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.service.findAllByUser(id));
    }

    @PostMapping("/recurring")
    @Transactional
    public ResponseEntity<List<DataExpense>> createRecurringExpenses(@Valid @RequestBody GetDataRecurringExpenses data) {
        return ResponseEntity.ok().body(this.service.createRecurringExpenses(ExpenseMapper.toDomainByRegisterRecurringDto(data), data.consumerId(), data.categoriaId(), data.recurring()));
    }

    @GetMapping("/users/{userId}/category/{categoryId}")
    public ResponseEntity<StaticsResults> staticsByCategory(@PathVariable UUID userId, @PathVariable Long categoryId) {
        return ResponseEntity.ok().body(this.service.getStaticsByCategory(userId, categoryId));
    }

    @GetMapping("/users/{userId}/month/{month}")
    public ResponseEntity<StaticsResults> staticsByMonth(@PathVariable UUID userId, @PathVariable Month month) {
        return ResponseEntity.ok().body(this.service.getStaticsByMonth(userId, month));
    }

    @GetMapping("/users/{userId}/year/{year}")
    public ResponseEntity<StaticsResults> staticsByYear(@PathVariable UUID userId, @PathVariable Year year) {
        return ResponseEntity.ok().body(this.service.getStaticsByYear(userId, year));
    }

    @GetMapping("/users/{consumerId}/month/{month}/year/{year}")
    public ResponseEntity<List<StaticsAllCategoryResults>> staticsByYear(@PathVariable UUID consumerId, @PathVariable Month month, @PathVariable Year year) {
        return ResponseEntity.ok().body(this.service.getStaticsByMonthAndCategoryAndYear(consumerId, month, year));
    }
}
