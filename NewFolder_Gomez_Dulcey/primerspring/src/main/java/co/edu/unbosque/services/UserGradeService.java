package co.edu.unbosque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.UserGrade;
import co.edu.unbosque.repository.UserGradeRepository;
import co.edu.unbosque.util.AESUtil;


@Service
public class UserGradeService implements CRUDOperations<UserGrade>{
	
	@Autowired
	private UserGradeRepository userGradeRepo;
	
	public UserGradeService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int create(UserGrade data) {

		if (findNameAlreadyTaken(data)) {
			return 1;
		} else {
			data.setName(AESUtil.encrypt(data.getName()));
			data.setGrade1(AESUtil.encrypt(data.getGrade1()));
			data.setGrade2(AESUtil.encrypt(data.getGrade2()));
			data.setGrade3(AESUtil.encrypt(data.getGrade3()));
			userGradeRepo.save(data);
			return 0;

		}
	}

	@Override
	public List<UserGrade> getAll() {
		List<UserGrade> aux = new ArrayList<UserGrade>();
		userGradeRepo.findAll().forEach(u -> aux.add(u));
		for (UserGrade u : aux) {
			u.setName(AESUtil.decrypt(u.getName()));
			u.setGrade1(AESUtil.decrypt(u.getGrade1()));
			u.setGrade2(AESUtil.decrypt(u.getGrade2()));
			u.setGrade3(AESUtil.decrypt(u.getGrade3()));
		}

		return aux;
	}

	@Override
	public int deleteById(Long id) {
		Optional<UserGrade> found = userGradeRepo.findById(id);

		if (found.isPresent()) {
			userGradeRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int updateById(Long id, UserGrade newData) {

		Optional<UserGrade> found = userGradeRepo.findById(id);
		Optional<UserGrade> newFound = userGradeRepo.findByName(AESUtil.encrypt(newData.getName()));

		if (found.isPresent() && !newFound.isPresent()) {

			UserGrade temp = found.get();
			temp.setName(newData.getName());
			temp.setGrade1(AESUtil.encrypt(temp.getGrade1()));
			temp.setGrade2(AESUtil.encrypt(temp.getGrade2()));
			temp.setGrade3(AESUtil.encrypt(temp.getGrade3()));
			userGradeRepo.save(temp);
			return 0;
		} else if (found.isPresent() && newFound.isPresent()) {
			return 1;
		} else if (!found.isPresent()) {
			return 2;
		} else {
			return 3;
		}

	}

	@Override
	public long count() {
		return userGradeRepo.count();
	}

	@Override
	public boolean exists(Long id) {
		return userGradeRepo.existsById(id) ? true : false;
	}

	public boolean findNameAlreadyTaken(UserGrade newUser) {

		Optional<UserGrade> found = userGradeRepo.findByName(AESUtil.encrypt(newUser.getName()));

		return found.isPresent() ? true : false;
	}

}
