package com.coche.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coche.service.model.Coche;
import com.coche.service.repository.ICocheRepository;


@Service
public class CocheService {

	@Autowired
	private ICocheRepository cocheRepository;
	
	
	//obtenemos todos los coches
		public List<Coche> getAll(){
			return cocheRepository.findAll();
		}
		
		//Obtenemos todos los coches por id
		public Coche getCocheById(int id) {
			return cocheRepository.findById(id).orElse(null);
		}
		
		//Guadar coche
		public Coche save(Coche coche) {
			Coche newCoche = cocheRepository.save(coche);
			return newCoche;
		}
		
		//obetener coches de un usuario
		public List<Coche> byUserId( int userId){
			return cocheRepository.findByUserId(userId);
		}
}
