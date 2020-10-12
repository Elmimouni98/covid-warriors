package org.example.dao;

import java.util.List;

import org.example.entities.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CitoyenRepository extends JpaRepository<Citoyen, String>{
	
	@Query("SELECT c FROM Citoyen c where c.ville = :ville")
	public List<Citoyen> getByCity(@Param("ville")String ville);
	@Query("SELECT c FROM Citoyen c where c.id = :id and c.password = :password")
	public Citoyen getCitoyen(@Param("id")String id,@Param("password")String password);
	@Query("SELECT c FROM Citoyen c where c.id = :id")
	public Citoyen getId(@Param("id")String id);
	@Query("SELECT ville FROM Citoyen")
	public List<String> getVilles();

}
