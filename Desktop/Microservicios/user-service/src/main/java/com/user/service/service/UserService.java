package com.user.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.feignclients.CocheFeignClient;
import com.user.service.feignclients.MotoFeignClients;
import com.user.service.model.User;
import com.user.service.models.Coche;
import com.user.service.models.Moto;
import com.user.service.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private CocheFeignClient cocheFeignClient;

	@Autowired
	private MotoFeignClients motoFeignClient;

	// Obtenemos todos los usuarios
	public List<User> getAll() {
		return userRepository.findAll();
	}

	// Obtenemos todos los usuarios por id
	public User getUserById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	// Guadar usuario
	public User save(User user) {
		User newUser = userRepository.save(user);
		return newUser;
	}

	// Obtenemos los coches de un usuario
	public List<Coche> getCar(int userId) {
		List<Coche> car = restTemplate.getForObject("http://localhost:8002/car/user/" + userId, List.class);
		return car;
	}

	// Obtenemos las motos de un usuario
	public List<Moto> getBike(int userId) {
		List<Moto> bike = restTemplate.getForObject("http://localhost:8003/bike/user/" + userId, List.class);
		return bike;
	}

	// Guardar coches en feignClient
	public Coche saveCoche(int userId, Coche coche) {
		coche.setUserId(userId);
		Coche newCar = cocheFeignClient.save(coche);
		return newCar;
	}

	// Guardar moto en feignClient
	public Moto saveMoto(int userId, Moto moto) {
		moto.setUserId(userId);
		Moto newBike = motoFeignClient.save(moto);
		return newBike;
	}
	
	//obtenemos vehículos por id
	public Map<String, Object> getUserAndVehicle(int userId){
		Map<String, Object> result = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			result.put("Mensaje", "El usuario no existe");
			return result;
		}
		result.put("Usuario", user);
		List<Coche> coche = cocheFeignClient.getCarById(userId);
		if (coche.isEmpty()) {
			result.put("Coches", "El usuario no tiene coches");
		}else {
			result.put("Coches", coche);
		}
		List<Moto> moto = motoFeignClient.getMotos(userId);
		if (moto.isEmpty()) {
			result.put("Motos", "El usuario no tiene coches");
		}else {
			result.put("Motos", moto);
		}
		return result;
	}
}
