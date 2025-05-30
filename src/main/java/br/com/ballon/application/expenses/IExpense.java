package br.com.ballon.application.expenses;

import java.util.List;

public interface IExpense<T, ID, S, R, B, M, Y, D> {

    R create(T t, ID UserId, S categoryId);

    B update(ID id, T t, S categoryId);

    void delete(ID id);

    R findById(ID id);

    List<R> findAllByUser(ID UserId);

    List<R> createRecurringExpenses(T t, ID UserId, S categoryId, S repeat);

    D getStaticsByMonth(ID UserId, M month);

    D getStaticsByCategory(ID UserId, S categoryId);

    D getStaticsByYear(ID UserId, Y year);


}
