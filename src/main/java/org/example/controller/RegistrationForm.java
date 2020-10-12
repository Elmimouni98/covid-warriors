package org.example.controller;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class RegistrationForm {
	
	private String id;
	private String nom;
	private String prenom;
	private String ville;
	private String adresse;
	private String tel;
	private String password;
	private String repassword;

}
