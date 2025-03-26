package br.com.ballon.application.expenses;

import java.util.List;

public interface IExpense<T, ID, S, R, L, B> {

    R create(T t, ID UserId, L categoryId);

    B update(ID id, T t, L categoryId);

    void delete(ID id);

    R findById(ID id);

    List<R> findAllByUser(ID UserId);
}
