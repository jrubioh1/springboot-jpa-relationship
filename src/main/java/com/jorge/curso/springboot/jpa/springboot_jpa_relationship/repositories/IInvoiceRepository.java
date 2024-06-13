package com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;

public interface IInvoiceRepository extends CrudRepository<Invoice, Long>{



}
