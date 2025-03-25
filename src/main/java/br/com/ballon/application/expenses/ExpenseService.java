package br.com.ballon.application.expenses;

import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.domain.expenses.Expense;
import br.com.ballon.infra.expense.*;
import br.com.ballon.infra.user.ConsumerEntity;
import br.com.ballon.infra.user.ConsumerEntityRepository;
import br.com.ballon.utils.ExpenseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService implements IExpense<Expense, UUID, GetDataExpense, DataExpense, Long>{

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
    public DataExpense update(UUID uuid, GetDataExpense getDataExpense) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public DataExpense findById(UUID uuid) {
        return null;
    }

    @Override
    public List<DataExpense> findAllByUser(UUID UserId) {
        return List.of();
    }
}
