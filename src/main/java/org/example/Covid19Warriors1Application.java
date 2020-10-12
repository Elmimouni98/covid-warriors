
package org.example;

import org.example.dao.CitoyenRepository;

import org.example.dao.MinistereRepository;
import org.example.entities.Citoyen;
import org.example.entities.Ministere;
import org.example.entities.Notification;
import org.example.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class Covid19Warriors1Application implements CommandLineRunner {

	@Autowired
	private CitoyenRepository citoyenRepository;
	@Autowired
	private MinistereRepository ministereRepository;
	@Autowired
	private RepositoryRestConfiguration configurationSource;
	public static void main(String[] args) {
		SpringApplication.run(Covid19Warriors1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		configurationSource.exposeIdsFor(Citoyen.class,Notification.class,User.class,Ministere.class);
		ministereRepository.save(new Ministere("1231","covid",null,null));
		citoyenRepository.save(new Citoyen("AG77690","azerty","elmimouni","mohamed","agadir","salam","0671990829",false,null,null));
		citoyenRepository.save(new Citoyen("CA12345","1234","laarousi","yassine","casablanca","ain dyab","0671990829",false,null,null));
		citoyenRepository.save(new Citoyen("RA23142","1234","chakir","ahmed","rabat","agdal","0671990829",false,null,null));
		citoyenRepository.save(new Citoyen("AG22523","azerty","amrani","hamza","agadir","hoda","0671990829",false,null,null));
		citoyenRepository.save(new Citoyen("RA42312","1234","hamdaoui","aziz","rabat","ryad","0671990829",false,null,null));
		citoyenRepository.save(new Citoyen("AG62340","1234","chafiq","karim","agadir","khyam","0671996529",false,null,null));

	}

}

