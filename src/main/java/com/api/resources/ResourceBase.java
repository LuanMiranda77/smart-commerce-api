package com.api.resources;

import java.awt.print.Pageable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;




public interface ResourceBase<T,ID> {
	

	public ResponseEntity<T> save(@Valid @RequestBody T pEntity, HttpServletResponse response);
	
	public ResponseEntity<T> update(@Valid ID pID, @RequestBody T pEntity);
	
	public void delete(@PathVariable ID pID);
	
	public ResponseEntity<T>findById(@PathVariable ID pID);
	
	public Page<T> findAllPage(@RequestBody T pFilter, Pageable pPage);
	
	public List<T> findAllList();

}
