package com.nuvu.users.service.Imp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nuvu.users.dto.ResponseDTO;
import com.nuvu.users.enums.ErrorEnum;
import com.nuvu.users.enums.RolesEnum;
import com.nuvu.users.exceptions.CustomException;
import com.nuvu.users.exceptions.NotFoundException;
import com.nuvu.users.helper.ConvertMD5;
import com.nuvu.users.model.User;
import com.nuvu.users.repository.IUserRepository;
import com.nuvu.users.security.JWTValidator;
import com.nuvu.users.service.IUserService;

@Service
public class UserServiceImp implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	ConvertMD5 md5 = new ConvertMD5();

	@Override
	public User registerUser(User user) {
		boolean isRoleValid = false;

		// Valido si ya existe un usuario con el número de identificación solicitado
		Optional<User> checkUser = userRepository.findByIdentification(user.getIdentification());
		if (checkUser.isPresent()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, ErrorEnum.USER_ALREADY_REGISTERED);
		}
		// Se valida el Role de Usuario
		for (RolesEnum role : RolesEnum.values()) {
			if (role.getRoleId() == user.getRoleId()) {
				isRoleValid = true;
			}
		}

		if (!isRoleValid) {
			throw new CustomException(HttpStatus.BAD_REQUEST, ErrorEnum.INVALID_ROLE);

		}

		user.setPassword(md5.convertToMd5(user.getPassword()));
		user.setRegistration_date(new Date());
		return userRepository.save(user);
	}

	@Override
	public Optional<User> login(User data) {
		ConvertMD5 md5 = new ConvertMD5();
		checkUser(data.getIdentification());
		Optional<User> checkUser = userRepository.findByIdentificationAndPassword(data.getIdentification(),
				md5.convertToMd5(data.getPassword()));
		if (!checkUser.isPresent()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, ErrorEnum.PASSWORD_INCORRECT);
		}
		return Optional.of(checkUser.get());
	}

	public void checkUser(Long identification) {
		Optional<User> checkUser = userRepository.findByIdentification(identification);
		if (!checkUser.isPresent()) {
			throw new NotFoundException(ErrorEnum.USER_NOT_FOUND, String.valueOf(identification));
		}
	}

	@Override
	public List<User> listUsers(String token) {
		JWTValidator.validateRoleAdmin(token);
		return userRepository.findAll();
	}

	@Override
	public User userById(Integer idUser, String token) {
		//JWTValidator.validateRoleAdmin(token);
		Optional<User> user = userRepository.findById(idUser);
		if (!user.isPresent()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, ErrorEnum.USER_NOT_REGISTERED);
		}
		return user.get();
	}

	@Override
	public ResponseDTO updateUser(Long identification, String token, User user) {
		JWTValidator.validateRoleAdmin(token);
		Optional<User> checkUser = userRepository.findByIdentification(identification);
		if (!checkUser.isPresent()) {
			throw new CustomException(HttpStatus.BAD_REQUEST, ErrorEnum.USER_NOT_REGISTERED);
		}
		checkUser.get().setEmail(user.getEmail());
		checkUser.get().setPhone(user.getPhone());
		userRepository.save(checkUser.get());
		
		return new ResponseDTO("Actualización correcta", true);
	}

}
