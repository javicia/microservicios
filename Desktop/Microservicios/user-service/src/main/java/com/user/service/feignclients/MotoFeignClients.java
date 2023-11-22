package com.user.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.user.service.models.Moto;

@FeignClient(name="moto-service", url ="http://localhost:8003", path="/bike")
public interface MotoFeignClients {

	@PostMapping()
	public Moto save(@RequestBody Moto moto);
	
	@GetMapping("/user/{userId}")
	public List<Moto> getMotos(@PathVariable("userId") int userId);
}
