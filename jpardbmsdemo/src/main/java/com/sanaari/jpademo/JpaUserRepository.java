package com.sanaari.jpademo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<Users, Integer>{
	public Users findById(int id);
	List<Users> findAll();
	void deleteById(int id);
}
