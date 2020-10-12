package org.example.dao;


import org.example.entities.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface NotificationRepository extends JpaRepository<Notification, Long>{
	@Query("select n from Notification n inner join n.citoyen c  where c.id LIKE :id order by n.dateNotification desc")
	public Page<Notification> getNotifications(@Param("id")String id,Pageable pageable);

}
