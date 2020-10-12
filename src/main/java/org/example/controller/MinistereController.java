
package org.example.controller;


import java.util.Collection;
import java.util.Date;

import java.util.List;

import org.example.dao.CitoyenRepository;
import org.example.dao.ContactRepository;
import org.example.dao.MinistereRepository;
import org.example.dao.NotificationRepository;
import org.example.entities.Citoyen;
import org.example.entities.Contact;
import org.example.entities.Ministere;
import org.example.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class MinistereController {
	
	@Autowired
	MinistereRepository ministereRepository;
	@Autowired
	CitoyenRepository citoyenRepository;
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	ContactRepository contactRepository;
	
	//Notifier un citoyen par un message en indiquant le type de ce message
	@GetMapping(value = "/notifier/{idM}/{id}/{type}/{message}")
	public void NotifierCitoyen(@PathVariable(name = "idM") String idM,@PathVariable(name = "id") String id,
			@PathVariable(name = "type") String type,@PathVariable(name = "message") String message)
	{
		Citoyen citoyen = citoyenRepository.getId(id);
		Ministere ministere = ministereRepository.getOne(idM);
		if(citoyen == null) throw new RuntimeException("Citoyen n'existe pas !");
		else
		{
			notificationRepository.save(new Notification(null,message,type,new Date(),false,ministere,citoyen));
		}
		
	}
	
	//Recommander un citoyen lorsqu'il a contacté un autre citoyen qui prend le virus
	@GetMapping(value = "/recommander/{idM}/{id}")
	public void RecommanderCitoyen(@PathVariable(name = "idM") String idM,@PathVariable(name = "id") String id)
	{
		Citoyen citoyen = citoyenRepository.getId(id);
		Ministere ministere = ministereRepository.getOne(idM);
		Notification notification = new Notification();
		String message = notification.Recommandation();
		if(citoyen == null) throw new RuntimeException("Citoyen n'existe pas !");
		else
		{
			notificationRepository.save(new Notification(null,message,"RECOMMANDATION",new Date(),false,ministere,citoyen));
		}
		
	}	
	//Avertir un citoyen lorsque le virus est propagé dans leur ville
	@GetMapping(value = "/avertir/{idM}/{id}")
	public void AvertirCitoyen(@PathVariable(name = "idM") String idM,@PathVariable(name = "id") String id)
	{
		Citoyen citoyen = citoyenRepository.getId(id);
		Ministere ministere = ministereRepository.getOne(idM);
		Notification notification = new Notification();
		String message = notification.Avertissement();
		if(citoyen == null) throw new RuntimeException("Citoyen n'existe pas !");
		else
		{
			notificationRepository.save(new Notification(null,message,"AVERTISSEMENT",new Date(),false,ministere,citoyen));
		}
		
	}
	
	//Notification par ville
	@GetMapping(value = "/notifierGroup/{idM}/{ville}/{type}/{message}")
	public void NotifierCitoyens(@PathVariable(name = "idM") String idM,@PathVariable(name = "ville") String ville,
			@PathVariable(name = "type") String type,@PathVariable(name = "message") String message)
	{
		List<Citoyen> citoyens = citoyenRepository.getByCity(ville);
		Ministere ministere = ministereRepository.getOne(idM);
		if(citoyens == null) throw new RuntimeException("Aucun citoyen ne trouve dans cette ville !");
		else
		{
			for(Citoyen citoyen : citoyens) 
			{
				notificationRepository.save(new Notification(null,message,type,new Date(),false,ministere,citoyen));
			}
		}
		
	}
	
	//Recommandation par ville
	@GetMapping(value = "/recommanderGroup/{idM}/{ville}")
	public void RecommanderCitoyens(@PathVariable(name = "idM") String idM,@PathVariable(name = "ville") String ville)
	{
		List<Citoyen> citoyens = citoyenRepository.getByCity(ville);
		Ministere ministere = ministereRepository.getOne(idM);
		Notification notification = new Notification();
		String message = notification.Recommandation();
		if(citoyens == null) throw new RuntimeException("Aucun citoyen ne trouve dans cette ville !");
		else
		{
			for(Citoyen citoyen : citoyens) 
			{
				notificationRepository.save(new Notification(null,message,"RECOMMANDATION",new Date(),false,ministere,citoyen));
			}
		}
	}
	
	//Avertissement par ville
	@GetMapping(value = "/avertirGroup/{idM}/{ville}")
	public void AvertirCitoyens(@PathVariable(name = "idM") String idM,@PathVariable(name = "ville") String ville)
	{
		List<Citoyen> citoyens = citoyenRepository.getByCity(ville);
		Ministere ministere = ministereRepository.getOne(idM);
		Notification notification = new Notification();
		String message = notification.Avertissement();
		if(citoyens == null) throw new RuntimeException("Aucun citoyen ne trouve dans cette ville !");
		else
		{
			for(Citoyen citoyen : citoyens) 
			{
				notificationRepository.save(new Notification(null,message,"AVERTISSEMENT",new Date(),false,ministere,citoyen));
			}
		}
	}
	
	@RequestMapping(value = "/listContact",method = RequestMethod.GET)
	public List<Contact> listeContact()
	{
		return contactRepository.getAll();
	}
	
	//Notify by list
	@GetMapping(value = "/recommanderAll/{id}")
	public void recommanderAll(@PathVariable(name = "id") String id)
	{
		Notification notification = new Notification();
		String message = notification.Recommandation();
		for(Contact contact : listeContact())
		{
			if(contact.getId_user().equals(id))
			{
				notificationRepository.save(new Notification(null,message,"RECOMMANDATION",new Date(),false,contact.getMinistere(),contact.getCitoyen()));
			}
		}
	}
	//Compte number of unread message
	@GetMapping(value = "/numberMsg/{id}")
	public int count(@PathVariable(name = "id") String id)
	{
		int number = 0;
		Citoyen c = citoyenRepository.getId(id);
		Collection<Notification> liste = c.getNotifications();
		for(Notification f : liste)
		{
			if(!f.isReadMessage())
			{
				number = number +1;
			}
		}
		return number;
	}
	
 
}
