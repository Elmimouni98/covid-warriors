package org.example.controller;

import org.example.dao.CitoyenRepository;
import org.example.dao.MinistereRepository;
import org.example.entities.Citoyen;
import org.example.entities.Ministere;
import org.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	
	@Autowired
	private CitoyenRepository citoyenRepository;
	@Autowired
	private MinistereRepository ministereRepository;
	
	@GetMapping(value="/login")
	public User getUsernamePassword(@RequestParam(name="id") String id,@RequestParam(name="password") String password) {

		Citoyen citoyen = citoyenRepository.getId(id);
		if(citoyen == null) 
		{
			Ministere ministere = ministereRepository.getId(id);
			if(ministere == null)
			{
				throw new RuntimeException("Identifiant n'existe pas !");
			}
			else
			{
				if(!ministere.getPassword().equals(password)) 
				{
					throw new RuntimeException("Mot de passe ne corresond pas!");
				}
				else
				{
					return ministereRepository.getOne(id);
				}
			}
		}
		else 
		{
			if(!citoyen.getPassword().equals(password)) 
			{
				throw new RuntimeException("Mot de passe ne corresond pas!");
			}
			else
			{
				return citoyenRepository.getOne(id);
			}
		}

	}

}
