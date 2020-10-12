package org.example.entities;

import java.util.Collection;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
public class Citoyen extends User{
	
	 private String nom;
	 private String prenom;
	 private String ville;
	 private String adresse;
	 private String tel;
	 private boolean etat;
	 @JsonIgnore
	 @OneToMany(cascade = {CascadeType.ALL},mappedBy = "citoyen" )
	 private Collection<Notification> notifications;

	 @JsonIgnore
	 @OneToMany(cascade = {CascadeType.ALL},mappedBy = "citoyen" )
	 private Collection<Contact> contacts;
	 
	 @ManyToMany
	 private Collection<Zone> zones;

	 
	 public Citoyen(String id ,String password,String nom, String prenom, String ville, String adresse, String tel,boolean etat,
			 Collection<Contact> contacts ,Collection<Notification> notifications) {
			super(id,password,"user");
			this.nom = nom;
			this.prenom = prenom;
			this.ville = ville;
			this.adresse = adresse;
			this.tel = tel;
			this.etat = etat;
			this.contacts = contacts;
			this.notifications = notifications;
		}
	 

}
