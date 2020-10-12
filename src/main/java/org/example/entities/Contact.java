package org.example.entities;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
public class Contact {
	
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idContact;
	 @Temporal(TemporalType.DATE)
	 private Date dateContact;
	 private String id_user;
	 @OneToOne
	 @JoinColumn(name = "CitoyenContaminéID")
	 private Citoyen citoyen;
	 @ManyToOne 
	 private Ministere ministere;
	 
}
