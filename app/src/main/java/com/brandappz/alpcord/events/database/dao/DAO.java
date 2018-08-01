package com.brandappz.alpcord.events.database.dao;

public interface DAO<T> {
	long insert(String[] data);
	void update(T data);
	void remove(long id);
	T get(long id);
	T[] getAll();
}
