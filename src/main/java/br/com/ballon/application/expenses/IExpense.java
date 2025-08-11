package br.com.ballon.application.expenses;

import java.util.List;

public interface IExpense<T, ID, S, R, B, M, Y, D, E, P, Q> {

    R create(T t, ID UserId, S categoryId);

    B update(ID id, T t, S categoryId);

    void delete(ID id);

    R findById(ID id);

    Q findAllByUser(ID UserId, P pageable);

    List<R> createRecurringExpenses(T t, ID UserId, S categoryId, S repeat);

    D getStaticsByMonth(ID UserId, M month);

    D getStaticsByCategory(ID UserId, S categoryId);

    D getStaticsByYear(ID UserId, Y year);

    List<E> getStaticsByMonthAndCategoryAndYear(ID UserId, M month, Y year);


}
