package org.example.dao;


import org.example.entities.Ministere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MinistereRepository extends JpaRepository<Ministere, String>{
	@Query("SELECT m FROM Ministere m where m.id = :id and m.password = :password")
	public Ministere getAccount(@Param("id")String id,@Param("password")String password);
	@Query("SELECT m FROM Ministere m where m.id = :id")
	public Ministere getId(@Param("id")String id);

}
