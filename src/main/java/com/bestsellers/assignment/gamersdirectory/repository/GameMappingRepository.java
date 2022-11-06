package com.bestsellers.assignment.gamersdirectory.repository;

import com.bestsellers.assignment.gamersdirectory.entity.GameMapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameMappingRepository extends CrudRepository<GameMapping, Long> {
    Optional<GameMapping> findById(Long id);
}
