package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.moto.service.model.Moto;
import com.moto.service.service.MotoService;


@RestController
@RequestMapping("/bike")
public class MotoController{

	
	@Autowired
	private MotoService motoService;
	
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos() {
		List<Moto> bike = motoService.getAll();
		if (bike.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(bike);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto (@PathVariable("id") int id){
		 Moto bike = motoService.getMotoById(id);
		 if (bike == null) {
			 return ResponseEntity.notFound().build();
		}
		 return ResponseEntity.ok(bike);
	}
	
	@PostMapping
	public ResponseEntity<Moto> guardarCoche(@RequestBody Moto moto){
		Moto newMoto = motoService.save(moto);
		return ResponseEntity.ok(newMoto);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Moto>> listarMotoPorUsuarioId(@PathVariable("userId") int id){
		List<Moto> bike = motoService.byUserId(id);
		if (bike.isEmpty()) {
			 return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(bike);
	}
	}

