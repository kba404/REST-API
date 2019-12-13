package fr.gcontact.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.gcontact.dao.ContactRepository;
import fr.gcontact.entities.Contact;
/*
 * @Author khaled
 * 
 * 
 */
@RestController
@RequestMapping("/service/contact")
@CrossOrigin("*")
public class ContactRestService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@RequestMapping(value = "/listContacts", method = RequestMethod.GET)
	public List<Contact> getContacts(){
		return contactRepository.findAll();
		
	}
	@RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
	public Optional<Contact> gestContact(@PathVariable Long id) {
		return contactRepository.findById(id);
		
	}
	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
	public Contact save(@RequestBody Contact contact) {
		return contactRepository.save(contact);
		
	}
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.DELETE)
	public boolean supprimer(@PathVariable Long id){
		contactRepository.deleteById(id);
		return true;
	}
	
	@RequestMapping(value = "/contacts/{id}", method = RequestMethod.PUT)
	public Contact updat(@PathVariable Long id, @RequestBody Contact contact) {
		contact.setId(id);
		return contactRepository.save(contact);
		
	}
	
	@RequestMapping(value = "/chercherContact", method = RequestMethod.GET)
	public Page<Contact> chercher(
			@RequestParam(name = "mc", defaultValue = "") String mc, 
			@RequestParam(name = "page", defaultValue = "0")int page,
			@RequestParam(name = "size", defaultValue = "5")int size) {
		return contactRepository.chercher("%"+mc+"%", new PageRequest(page, size));
		
	}


}
