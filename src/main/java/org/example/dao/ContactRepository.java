package org.example.dao;

import java.util.List;

import org.example.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ContactRepository extends JpaRepository<Contact, Long>{
	@Query("select c from Contact c  where c.id_user LIKE :id and DATEDIFF(CURDATE(),c.dateContact) < 15")
	public Page<Contact> chercher(@Param("id")String id,Pageable pageable);
	@Query("select c from Contact c")
	public List<Contact> getAll();

}
