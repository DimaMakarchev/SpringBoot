package com.example.servingwebcontent.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Messages,Integer> {

     List<Messages>findByName(String name);
}
