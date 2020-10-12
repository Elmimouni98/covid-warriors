package org.example.entities;

import java.util.Collection;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "ministere")
public class Ministere extends User{
	
	@JsonIgnore
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "ministere" )
	private Collection<Notification> notifications;
	
	@JsonIgnore
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "ministere" )
	private Collection<Contact> contacts;
	
	@JsonIgnore
	@OneToMany(cascade = {CascadeType.ALL},mappedBy = "ministere" )
	private Collection<Zone> zones;
	
	public Ministere(String id,String password,Collection<Notification> notifications,Collection<Contact> contacts) {
		super(id,password,"responsable");
		this.notifications = notifications;
		this.contacts = contacts;
	}
}

