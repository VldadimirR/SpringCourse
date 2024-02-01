package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
        List<Task> findByStatus(Status status);

        @Query("SELECT t FROM Task t LEFT JOIN FETCH t.workers WHERE t.id = :id")
        Task findByIdWithWorkers(@Param("id") int id);
}
