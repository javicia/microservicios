package com.user.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.service.models.Coche;

@FeignClient(name="coche-service", url ="http://localhost:8002", path="/car")
public interface CocheFeignClient {

	@PostMapping()
	public Coche save(@RequestBody Coche coche);
	
	@GetMapping("/user/{userId}")
	public List<Coche> getCarById(@PathVariable("userId") int userId);
}
