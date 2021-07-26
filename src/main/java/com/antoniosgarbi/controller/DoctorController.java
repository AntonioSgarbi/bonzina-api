package com.antoniosgarbi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.antoniosgarbi.dto.DoctorDTO;
import com.antoniosgarbi.service.DoctorService;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService service;
	
	@GetMapping(value = "/get")
	public ResponseEntity<List<DoctorDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value = "/get/count")
	public ResponseEntity<Long> count() {
		return ResponseEntity.ok(service.count());
	}
	
	@PostMapping(value = "/post")
	public ResponseEntity<DoctorDTO> insert(@RequestBody DoctorDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<DoctorDTO> update(@RequestBody DoctorDTO dto) {
		return ResponseEntity.ok().body(service.update(dto));
	}
	
	@PutMapping(value = "/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		Boolean response = service.delete(id);
		return ResponseEntity.ok().body(response);
	}	
	
}
