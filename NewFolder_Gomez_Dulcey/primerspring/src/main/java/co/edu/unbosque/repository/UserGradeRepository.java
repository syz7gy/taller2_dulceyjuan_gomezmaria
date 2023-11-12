package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.UserGrade;

public interface UserGradeRepository extends JpaRepository<UserGrade, Long>{
	
	public Optional<UserGrade> findByName(String name);

}
