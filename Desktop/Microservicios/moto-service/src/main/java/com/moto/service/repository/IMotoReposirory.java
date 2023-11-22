package com.moto.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moto.service.model.Moto;

@Repository
public interface IMotoReposirory extends JpaRepository<Moto, Integer> {

	List<Moto> findByUserId (int userId);
}
