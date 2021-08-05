package com.antoniosgarbi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.antoniosgarbi.entities.Doctor;
import com.antoniosgarbi.entities.enums.Clinic;
import com.antoniosgarbi.entities.enums.Period;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE Doctor SET name = :name, phone = :phone, email = :mail,"
			+ "register = :register, speciality = :speciality, clinic = :clinic,"
			+ "period = :period WHERE id = :id")
	void update(
			@Param("name") String name, @Param("phone") String phone, 
			@Param("mail") String mail, @Param("register") Integer register,
			@Param("speciality") String speciality, @Param("clinic") Clinic clinic,
			@Param("period") Period period, @Param("id") Integer id
			);
}
