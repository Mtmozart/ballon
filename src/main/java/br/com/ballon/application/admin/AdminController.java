package br.com.ballon.application.admin;

import br.com.ballon.utils.AdminMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("admin online");
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataAdmin> create(@RequestBody @Valid GetDataAdmin data) {
        return ResponseEntity.status(201).body(this.adminService.create(AdminMapper.toDomainByRegisterDto(data)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DataAdmin> update(@PathVariable UUID id, @RequestBody @Valid GetDataAdmin data) {
        return ResponseEntity.ok(this.adminService.update(id, data));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.adminService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataAdmin> profile(@PathVariable UUID id) {
        return ResponseEntity.ok(this.adminService.findById(id));
    }
}
