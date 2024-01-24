package com.project.emailend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.emailend.entities.Email;

/**
 * EmailRepository
 */
public interface EmailRepository extends JpaRepository<Email, Long>{

    
}