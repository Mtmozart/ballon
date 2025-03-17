package br.com.ballon.infra.expense;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseEntityRepository extends JpaRepository<ExpenseEntity, Long> {
}
