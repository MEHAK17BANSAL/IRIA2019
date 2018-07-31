package com.brandappz.dhfl.event.database.dao;

public interface DAO<T> {
	long insert(String[] data);
	void update(T data);
	void remove(long id);
	T get(long id);
	T[] getAll();
}
