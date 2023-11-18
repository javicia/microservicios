package com.user.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.service.model.User;
import com.user.service.models.Coche;
import com.user.service.models.Moto;
import com.user.service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> listarUsuarios(){
		List<User> users =userService.getAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> obtenerUsuario(@PathVariable("id") int id){
		 User user = userService.getUserById(id);
		 if (user == null) {
			 return ResponseEntity.notFound().build();
		}
		 return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<User> guardarUsuario(@RequestBody User user){
		User newUser = userService.save(user);
		return ResponseEntity.ok(newUser);
	}
	
	@GetMapping("/car/{userId}")
	public ResponseEntity<List<Coche>> listarCoche (@PathVariable("userId") int id){
	 User user = userService.getUserById(id);
	 if (user == null) {
			return ResponseEntity.notFound().build();
	}
	List<Coche> car = userService.getCar(id);
	return ResponseEntity.ok(car);
	}
	
	@GetMapping("/bike/{userId}")
	public ResponseEntity<List<Moto>> listarMoto (@PathVariable("userId") int id){
	 User user = userService.getUserById(id);
	 if (user == null) {
			return ResponseEntity.notFound().build();
	}
	List<Moto> bike = userService.getBike(id);
	return ResponseEntity.ok(bike);
	}
	
	@PostMapping("/car/{userId}")
	public ResponseEntity<Coche> saveCar(@PathVariable("userId") int userId, @RequestBody Coche coche){
		Coche newCar = userService.saveCoche(userId, coche);
		return ResponseEntity.ok(newCar);
	}
	
	@PostMapping("/bike/{userId}")
	public ResponseEntity<Moto> saveBike(@PathVariable("userId") int userId, @RequestBody Moto moto){
		Moto newBike = userService.saveMoto(userId, moto);
		return ResponseEntity.ok(newBike);
	}
	
	@GetMapping("/all/{userId}")
	public ResponseEntity<Map<String, Object>> getAllVehicle(@PathVariable("userId") int userId){
		Map<String, Object> result = userService.getUserAndVehicle(userId);
		return ResponseEntity.ok(result);
	}
	
}
