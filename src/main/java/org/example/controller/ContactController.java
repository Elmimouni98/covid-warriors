package org.example.controller;



import org.example.dao.ContactRepository;
import org.example.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;
	
	
	@RequestMapping(value = "/chercher",method = RequestMethod.GET)
	public Page<Contact> chercher(@RequestParam(name="id",defaultValue = "")String id,
								  @RequestParam(name="page",defaultValue = "0")int page,
								  @RequestParam(name="size",defaultValue = "5")int size)
	{
	
		Pageable paging =  PageRequest.of(page,size);
		return contactRepository.chercher(id,paging);
	}


}
