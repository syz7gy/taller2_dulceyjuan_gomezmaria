package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.UserGrade;
import co.edu.unbosque.services.UserGradeService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/UserGrade")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class UserGradeController {

	@Autowired
	private UserGradeService userGradeServ;

	public UserGradeController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createusergradejson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewUserGradeWithJson(@RequestBody UserGrade newUserGrade) {
		int status = userGradeServ.create(newUserGrade);

		if (status == 0) {
			return new ResponseEntity<String>("User grades created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the user. Maybe the name already exists.",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@GetMapping(path = "/getall")
	public ResponseEntity<List<UserGrade>> getAll(){
		List<UserGrade> usersGrades = userGradeServ.getAll();
		
		if(usersGrades.isEmpty()) {
			return new ResponseEntity<List<UserGrade>>(usersGrades, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<UserGrade>>(usersGrades, HttpStatus.ACCEPTED);
		}
	}

}
