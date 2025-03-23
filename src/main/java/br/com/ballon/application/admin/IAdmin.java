package br.com.ballon.application.admin;

public interface IAdmin <T, ID, S, R>{
    R create(T t);

    R update(ID id, S s);

    void delete(ID id);

    R findById(ID id);
}
