package com.jorge.curso.springboot.jpa.springboot_jpa_relationship;


import java.util.HashSet;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.jorge.curso.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;


@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;

	@Autowired
	private StudentRepository studentRepository; 

	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		manyToManyBidireccional();
	}


	@Transactional
	public void manyToManyBidireccional(){

		Student student1= new Student("Jano", "pura");
		Student student2= new Student("Erbra", "Doa");

		Course course1= new Course("Curso de java", "Andres");
		Course course2= new Course("Curso de Python", "Andres");

		student1.addCourse(course1);
		student1.addCourse(course2);

		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
		
		
	}

	@Transactional
	public void manyToManyRemove(){

		Optional<Student> optionalStudent1= studentRepository.findById(1L);
		Optional<Student> optinionalStudent2= studentRepository.findById(2L);


		Student student1= optionalStudent1.get();
		Student student2= optinionalStudent2.get();

		Course course1= courseRepository.findById(1L).get();
		Course course2= courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1,course2));

		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> studientOptionalDb= studentRepository.findOneWithCourses(3L);
		if(studientOptionalDb.isPresent()){
			Student studentDb= studientOptionalDb.orElseThrow();
			Optional<Course> courseOptionalDb= courseRepository.findById(1L);

			courseOptionalDb.ifPresent(course->{
				Course courseDb= courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);

			});}
		
		
	}

	@Transactional
	public void manyToManyRemoveFind(){

		Optional<Student> optionalStudent1= studentRepository.findById(1L);
		Optional<Student> optinionalStudent2= studentRepository.findById(2L);


		Student student1= optionalStudent1.get();
		Student student2= optinionalStudent2.get();

		Course course1= courseRepository.findById(1L).get();
		Course course2= courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1,course2));

		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
		

		Optional<Student> studientOptionalDb= studentRepository.findOneWithCourses(1L);
		if(studientOptionalDb.isPresent()){
			Student studentDb= studientOptionalDb.orElseThrow();
			Optional<Course> courseOptionalDb= courseRepository.findById(2L);

			courseOptionalDb.ifPresent(course->{
				Course courseDb= courseOptionalDb.get();
				studentDb.getCourses().remove(courseDb);

				studentRepository.save(studentDb);

			});

		} 
		
	}

	@Transactional
	public void manyToManyFind(){

		Optional<Student> optionalStudent1= studentRepository.findById(1L);
		Optional<Student> optinionalStudent2= studentRepository.findById(2L);


		Student student1= optionalStudent1.get();
		Student student2= optinionalStudent2.get();

		Course course1= courseRepository.findById(1L).get();
		Course course2= courseRepository.findById(2L).get();

		student1.setCourses(Set.of(course1,course2));

		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
		
		
	}

	@Transactional
	public void manyToMany(){

		Student student1= new Student("Jano", "pura");
		Student student2= new Student("Erbra", "Doa");

		Course course1= new Course("Curso de java", "Andres");
		Course course2= new Course("Curso de Python", "Andres");

		student1.setCourses(Set.of(course1,course2));

		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
		
		
	}

	@Transactional
	public void OneToOneBideraccional(){

		Client client= new Client("Erba", "Pura");
		

		ClientDetails clientDetails= new ClientDetails(true, 5000);
		

		client.setClientDetails(clientDetails);
		clientDetails.setClient(client);

		clientRepository.save(client);

		System.out.println(client);

	}

	@Transactional
	public void OneToOneFindById(){
		ClientDetails clientDetails= new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Optional <Client> optionalClient= clientRepository.findOne(2L);

		optionalClient.ifPresent(client->{
			client.setClientDetails(clientDetails);
			clientRepository.save(client);
			System.out.println(client);
		});


	}


	@Transactional
	public void OneToOne(){
		ClientDetails clientDetails= new ClientDetails(true, 5000);
		clientDetailsRepository.save(clientDetails);

		Client client= new Client("Erba", "Pura");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);

	

		System.out.println(client);

	}

	@Transactional
	public void RemoveOneToManyBidireccionalFindById() {
		Optional<Client> optionalClient= clientRepository.findOne(1L);

		optionalClient.ifPresent((client)->{
			
			Invoice invoice1= new Invoice("Compras de la casa", 5000L);
			Invoice invoice2= new Invoice("Compras de oficina", 8000L);
		
			client.addInvoice(invoice1).addInvoice(invoice2);
		

			clientRepository.save(client);

			System.out.println(client);

		});

		Optional<Client> optionalClientDb= clientRepository.findOne(1L);
		optionalClientDb.ifPresent(client->{


			Optional<Invoice> invoiceOptional= invoiceRepository.findById(2L);

			invoiceOptional.ifPresent(invoice->{

				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});


		});
	}


	@Transactional
	public void oneToManyBidireccionalFindById() {
		Optional<Client> optionalClient= clientRepository.findOne(1L);

		optionalClient.ifPresent((client)->{
			
			Invoice invoice1= new Invoice("Compras de la casa", 5000L);
			Invoice invoice2= new Invoice("Compras de oficina", 8000L);
		
			client.addInvoice(invoice1).addInvoice(invoice2);
		

			clientRepository.save(client);

			System.out.println(client);

		});
		
	
	}

	@Transactional
	public void oneToManyBidireccional() {
		Client client = new Client("Fran", "Moras");
		Invoice invoice1= new Invoice("Compras de la casa", 5000L);
		Invoice invoice2= new Invoice("Compras de oficina", 8000L);
	
		client.addInvoice(invoice1).addInvoice(invoice2);
	

		clientRepository.save(client);

		System.out.println(client);
	
	}


	@Transactional
	public void removeAddressFindById() {
		Optional<Client> optionalClient = clientRepository.findOneWithInvoices(1L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);
			
			Set<Address> addresses= new HashSet<>();
			addresses.add(address2);
			addresses.add(address1);
			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddress(2L);
			optionalClient2.ifPresent(c -> {
				c.getAddresses().remove(address2);
				clientRepository.save(c);
				System.out.println(c);
			});
		});

	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findById(3L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	@Transactional
	public void oneToManyFindById() {
		Optional<Client> optionalClient = clientRepository.findById(2L);
		optionalClient.ifPresent(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 9875);

			Set<Address> addresses= new HashSet<>();
			addresses.add(address2);
			addresses.add(address1);
			client.setAddresses(addresses);

			clientRepository.save(client);

			System.out.println(client);
		});

	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 9875);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);

		System.out.println(client);
	}

	@Transactional
	public void manyToOne() {

		Client client = new Client("John", "Doe");
		clientRepository.save(client);

		Invoice invoice = new Invoice("compras de oficina", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient() {

		Optional<Client> optionalClient = clientRepository.findById(1L);

		if (optionalClient.isPresent()) {
			Client client = optionalClient.orElseThrow();

			Invoice invoice = new Invoice("compras de oficina", 2000L);
			invoice.setClient(client);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);

		}
	}
}
