package com.example.springgreetingapp.repository;
import com.example.springgreetingapp.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
}


