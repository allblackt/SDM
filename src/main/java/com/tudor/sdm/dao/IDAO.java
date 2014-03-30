package com.tudor.sdm.dao;

import java.util.List;

public interface IDAO<T, ID> {
	public T add(T item);
	public T save(T item);
	public T getById(ID id);
	public List<T> getAll();
    public List<T> getAll(String orderByColumn, Boolean asc);
}
