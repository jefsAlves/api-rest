package com.ibm.application.spb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.application.spb.domain.State;

public interface StateRepository extends JpaRepository<State, Long>{

}
