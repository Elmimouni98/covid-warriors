package org.example.entities;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
@Entity
public class Zone {
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean etat;
	private int nbCasContaminé;
	@ManyToOne 
	private Ministere ministere;
	 @JsonIgnore
	 @ManyToMany(cascade = {CascadeType.ALL},mappedBy = "zones" )
	 private Collection<Citoyen> citoyens;

}
