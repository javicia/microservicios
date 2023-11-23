package com.coche.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coche.service.model.Coche;
import com.coche.service.service.CocheService;


@RestController
@RequestMapping("/car")
public class CocheController {

	
	@Autowired
	private CocheService cocheService;
	;

	@GetMapping
	public ResponseEntity<List<Coche>> listarCoches(){
		List<Coche> car =cocheService.getAll();
		if (car.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(car);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Coche> obtenerCoche(@PathVariable("id") int id){
		 Coche car = cocheService.getCocheById(id);
		 if (car == null) {
			 return ResponseEntity.notFound().build();
		}
		 return ResponseEntity.ok(car);
	}
	
	@PostMapping
	public ResponseEntity<Coche> guardarCoche(@RequestBody Coche coche){
		Coche newCar = cocheService.save(coche);
		return ResponseEntity.ok(newCar);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Coche>> listarCochesPorUsuarioId(@PathVariable("userId") int id){
		List<Coche> car = cocheService.byUserId(id);
		if (car.isEmpty()) {
			 return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(car);
	}
}
