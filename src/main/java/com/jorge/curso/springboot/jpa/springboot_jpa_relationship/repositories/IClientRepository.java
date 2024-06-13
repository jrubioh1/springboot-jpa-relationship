package com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;

public interface IClientRepository extends CrudRepository<Client, Long>{

    

}
