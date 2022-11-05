package com.api.services;

public interface ServiceBase<T,ID> {

	public T save(T pEntity);
	
	public T update(ID pID, T pEntity);

}
