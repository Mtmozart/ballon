package br.com.ballon.application.expenses;

import java.util.List;

public interface IExpense<T, ID, S, R, L> {

    R create(T t, ID UserId, L categoryID);

    R update(ID id, S s);

    void delete(ID id);

    R findById(ID id);

    List<R> findAllByUser(ID UserId);
}
