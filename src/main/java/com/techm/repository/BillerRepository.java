package com.techm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techm.entity.Biller;

@Repository
public interface BillerRepository extends JpaRepository<Biller, Long>{

	Biller findByEmailAndPassword(String email, String password);

	Biller findByEmail(String email);
}
