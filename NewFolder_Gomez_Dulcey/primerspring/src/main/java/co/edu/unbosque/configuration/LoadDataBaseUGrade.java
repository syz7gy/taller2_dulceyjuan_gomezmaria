package co.edu.unbosque.configuration;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import co.edu.unbosque.model.UserGrade;
import co.edu.unbosque.repository.UserGradeRepository;
import co.edu.unbosque.util.AESUtil;

@Configuration
public class LoadDataBaseUGrade {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoadDataBaseUGrade.class);
	
	CommandLineRunner initDataBase(UserGradeRepository userGradeRepo) {
		return args -> {
			Optional<UserGrade> found = userGradeRepo.findByName(AESUtil.encrypt("admin"));
			if(found.isPresent()) {
				LOG.info("Admin already exists. Skipping admin creation");
			} else {
				String encrypted = AESUtil.encrypt("admin");
				String g1encrypted = AESUtil.encrypt("5.0");
				String g2encrypted = AESUtil.encrypt("5.0");
				String g3encrypted = AESUtil.encrypt("5.0");
				userGradeRepo.save(new UserGrade(encrypted, g1encrypted, g2encrypted, g3encrypted));
				LOG.info("Pre-loading admin information.");
			}
		};
	}
	

}
