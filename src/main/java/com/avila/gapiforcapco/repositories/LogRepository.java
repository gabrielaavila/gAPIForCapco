package com.avila.gapiforcapco.repositories;

import com.avila.gapiforcapco.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Override
    List<Log> findAll();
}
