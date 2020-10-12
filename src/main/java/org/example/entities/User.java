package org.example.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor @AllArgsConstructor
@Entity
public class User {
	@Id   
	@Column(name = "id", unique = true, length = 7)
	private String id;
	private String password;
	private String role;
}
