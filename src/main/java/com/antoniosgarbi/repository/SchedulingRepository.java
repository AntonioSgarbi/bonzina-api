package com.antoniosgarbi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antoniosgarbi.entities.Scheduling;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Integer> {

}
