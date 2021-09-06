package com.antoniosgarbi.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antoniosgarbi.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {	
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE Patient SET name = :name, phone = :phone, email = :mail,"
			+ "address = :address, birthdate = :birthdate WHERE id = :id")
	void update(
			@Param("name") String name, @Param("phone") String phone, 
			@Param("mail") String mail, @Param("address") String address,
			@Param("birthdate") LocalDate birthdate,  @Param("id") Integer id
			);

	Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
