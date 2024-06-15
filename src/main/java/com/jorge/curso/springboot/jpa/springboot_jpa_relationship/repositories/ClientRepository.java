package com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;


public interface ClientRepository extends CrudRepository<Client, Long>{
    @Query("select c from Client c join fetch c.addresses")
    Optional<Client> findOne(Long id);
}
