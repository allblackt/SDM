package com.tudor.sdm.dao;

import java.util.List;

public interface IDAO<T, ID> {
	public T add(T item);
	public T save(T item);
	public T get(ID id);
	public List<T> getAll();
}
