package fr.gcontact;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.gcontact.dao.ContactRepository;
import fr.gcontact.entities.Contact;

@SpringBootApplication
public class Demo1Application implements CommandLineRunner{
	@Autowired
	private ContactRepository contactRepository;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		contactRepository.save(new Contact("DUPON", "jean", df.parse("12/05/2000"), "jdupon@gmail.com", 0651235222, "dupon.png"));
		contactRepository.save(new Contact("Hamid", "moussa", df.parse("10/01/1986"), "mhamid@gmail.com", 0543456743, "Hamid.png"));
		contactRepository.save(new Contact("KANE", "Hary", df.parse("31/11/1999"), "hkane@gmail.com", 0134567456, "kane.png"));
		contactRepository.findAll().forEach(c ->{
			System.out.println(c.getNom());
		});
	}
}
