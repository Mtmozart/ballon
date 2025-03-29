package br.com.ballon.application.expenses;

import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.domain.expenses.Expense;
import br.com.ballon.infra.expense.*;
import br.com.ballon.infra.user.ConsumerEntity;
import br.com.ballon.infra.user.ConsumerEntityRepository;
import br.com.ballon.utils.ExpenseMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExpenseService implements IExpense<Expense, UUID, Long, DataExpense, Long, Boolean> {

    public final ExpenseEntityRepository entityRepository;
    private final ConsumerEntityRepository consumerEntityRepository;
    private final CategoryEntityRepository categoryEntityRepository;
    private final SubCategoryEntityRepository subCategoryEntityRepository;

    public ExpenseService(ExpenseEntityRepository entityRepository, ConsumerEntityRepository consumerEntityRepository, CategoryEntityRepository categoryEntityRepository, SubCategoryEntityRepository subCategoryEntityRepository) {
        this.entityRepository = entityRepository;
        this.consumerEntityRepository = consumerEntityRepository;
        this.categoryEntityRepository = categoryEntityRepository;
        this.subCategoryEntityRepository = subCategoryEntityRepository;
    }


    @Override
    public DataExpense create(Expense expense, UUID userId, Long categoryId) {
        ConsumerEntity consumerEntity = this.consumerEntityRepository.findById(userId).orElseThrow(() -> new BallonException("Usuário não encontrado."));
        CategoryEntity category = this.categoryEntityRepository.findById(categoryId).orElseThrow(() -> new BallonException("Categoria não encontrada."));
        return ExpenseMapper.toDataResponse(this.entityRepository.save(ExpenseMapper.toExpenseEntityByExepenseConsumerCategory(expense, consumerEntity, category)));
    }


    @Override
    public Boolean update(UUID id, Expense expense, Long categoryId) {
        CategoryEntity category = this.categoryEntityRepository.findById(categoryId).orElseThrow(() -> new BallonException("Categoria não encontrada."));
        this.entityRepository.updateExpense(
                expense.getMonth(),
                expense.getYear(),
                expense.getTitle(),
                expense.getValue(),
                category.getId(),
                Instant.now(),
                id
        ).orElseThrow(() -> new BallonException("Erro ao atualizar o usuário."));
        return true;

    }


    @Override
    public void delete(UUID id) {
        this.entityRepository.findById(id).orElseThrow(() -> new BallonException("Gasto não encontrado."));
        this.entityRepository.deleteById(id);
    }

    @Override
    public DataExpense findById(UUID id) {
        return ExpenseMapper.toDataResponse(this.entityRepository.findById(id).orElseThrow(() -> new BallonException("Gasto não encontrado.")));
    }

    @Override
    public List<DataExpense> findAllByUser(UUID userId) {
        return this.entityRepository.getAllByUser(userId).orElseThrow(() -> new BallonException("Gastos não encontrado."))
                .stream().map(
                        ExpenseMapper::toDataResponse
                ).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<DataExpense> createRecurringExpenses(Expense expense, UUID userId, Long categoryId, Long repeat) {
        List<ExpenseEntity> expensesEntityList = new ArrayList<ExpenseEntity>();
        ConsumerEntity consumerEntity = this.consumerEntityRepository.findById(userId).orElseThrow(() -> new BallonException("Usuário não encontrado."));
        CategoryEntity category = this.categoryEntityRepository.findById(categoryId).orElseThrow(() -> new BallonException("Categoria não encontrada."));
        Month currentMonth = expense.getMonth();
        for (int i = 0; i < repeat; i++) {
            Month nextMonth = currentMonth.plus(i);
            expense.updateMonthAndIdForRecurrentExpense(nextMonth);
            if (nextMonth.equals(currentMonth) && i > 0) {
                expense.updateYearAndIdForRecurrentExpense(expense.getYear().plusYears(1));
            }
            ExpenseEntity expenseEntity = ExpenseMapper.toExpenseEntityByExepenseConsumerCategory(expense, consumerEntity, category);
            expensesEntityList.add(expenseEntity);
        }
        return this.entityRepository.saveAll(expensesEntityList).stream().map(
                ExpenseMapper::toDataResponse
        ).collect(Collectors.toUnmodifiableList());
    }
}
