package org.example.entities;

import java.util.Date;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
	
	 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String message;
	 private String typeNotification;
	 @Temporal(TemporalType.DATE)
	 private Date dateNotification;
	 private boolean readMessage;
	 
	 @ManyToOne 
	 private Ministere ministere;
	 
	 @ManyToOne 
	 private Citoyen citoyen;
	 
	 public String Recommandation() {
		 return "Bonjour ,vous avez contacté un citoyen qui a été testé positif au covid19"
		 		+ "Veuillez rester chez-vous et éviter tout contact avec les autres!";
	 }
	 
	 public String Avertissement() {
		 return "Ils existent des persones qui ont été testé positif au Covid-19 dans votre région "
		 		+ "Veuillez faire attention!";
	 }
}
