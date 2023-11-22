package com.moto.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.model.Moto;
import com.moto.service.repository.IMotoReposirory;



@Service
public class MotoService {

	@Autowired
	private IMotoReposirory motoRepository;
	
	
	//obtenemos todos las motos
		public List<Moto> getAll(){
			return motoRepository.findAll();
		}
		
		//Obtenemos todos los coches por id
		public Moto getMotoById(int id) {
			return motoRepository.findById(id).orElse(null);
		}
		
		//Guadar coche
		public Moto save(Moto moto) {
			Moto newMoto = motoRepository.save(moto);
			return newMoto;
		}
		
		//obetener coches de un usuario
		public List<Moto> byUserId( int userId){
			return motoRepository.findByUserId(userId);
		}
}
