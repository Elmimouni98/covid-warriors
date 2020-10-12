
package org.example.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.example.dao.CitoyenRepository;
import org.example.dao.NotificationRepository;
import org.example.entities.Citoyen;
import org.example.entities.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class CitoyenController {
	@Autowired
	private CitoyenRepository citoyenRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	
	@PostMapping(value = "/register")
	public Citoyen addCitoyen(@RequestBody RegistrationForm data)
	{
		String id = data.getId();
		String password = data.getPassword();
		Citoyen citoyen = citoyenRepository.getId(id);
		if(citoyen != null ) throw new RuntimeException("Citoyen est déja existé,essayer avec un nouveau CIN ou username !");
		String repassword = data.getRepassword();
		if(!password.equals(repassword)) throw new RuntimeException("Vous devez confirmer le mot de passe !");
		else {
			return citoyenRepository.save(new Citoyen(id,password,data.getNom(),data.getPrenom(),data.getVille()
												,data.getAdresse(),data.getTel(),false,null,null));
		}

	}
	
	@GetMapping(value = "/villes")
	public List<String> getVilles()
	{
		List<String> villes = citoyenRepository.getVilles();
		List<String> listVilles = new ArrayList<String>();
		for(String ville : villes)
		{
			if(!listVilles.contains(ville))
			{
				listVilles.add(ville);
			}
		}
		return listVilles;
	}	
	@GetMapping(value = "/notifications")
	public Page<Notification> getNotifications(@RequestParam(name="id",defaultValue = "")String id,
			  @RequestParam(name="page",defaultValue = "0")int page,
			  @RequestParam(name="size",defaultValue = "2")int size)
	{
		Citoyen c = citoyenRepository.getId(id);
		Collection<Notification> liste = c.getNotifications();
		for(Notification n: liste)
		{
			n.setReadMessage(true);
			notificationRepository.save(n);
		}
		
		Pageable paging =  PageRequest.of(page,size);
		return notificationRepository.getNotifications(id, paging);
	}	
}
