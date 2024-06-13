package com.jorge.curso.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories.IClientRepository;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories.IInvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	IClientRepository clientRepository;

	@Autowired
	IInvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		OneToMany();
	}

	@Transactional
	public void OneToMany(){
		
		Client client= new Client("Fran","Moras");
		clientRepository.save(client);

		Address address1= new Address("el verjel", 5151);
		Address address2= new Address("sn sn", 433);

		client.getAddresses().add(address2);
		client.getAddresses().add(address1);

		clientRepository.save(client);

		System.out.println(client);

	}


	@Transactional
	public void manyToOne(){

		Client client= new Client("John","Doe");
		clientRepository.save(client);

		Invoice invoice= new Invoice("Compras de oficina", 2000L);
		invoice.setClient(client);

		invoiceRepository.save(invoice);
		System.out.println(invoice);

	}

	@Transactional
	public void manyToOnefindById(){

		Optional<Client> optionalClient= clientRepository.findById(1L);
		
			if (optionalClient.isPresent()){

				Client client= optionalClient.orElseThrow();

				Invoice invoice= new Invoice("Compras de oficina", 2000L);
				invoice.setClient(client);
				Invoice invoiceDB=invoiceRepository.save(invoice);
				System.out.println(invoiceDB);

				}	
		

	}


}
