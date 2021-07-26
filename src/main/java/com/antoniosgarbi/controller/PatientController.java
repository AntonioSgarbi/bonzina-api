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

import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.service.PatientService;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
	
	@Autowired
	private PatientService service;
	
	@GetMapping(value = "/get")
	public ResponseEntity<List<PatientDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value = "/get/count")
	public ResponseEntity<Long> count() {
		return ResponseEntity.ok(service.count());
	}
	
	@PostMapping(value = "/post")
	public ResponseEntity<PatientDTO> insert(@RequestBody PatientDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO dto) {
		return ResponseEntity.ok().body(service.update(dto));
	}
	
	@PutMapping(value = "/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		return ResponseEntity.ok().body(service.delete(id));
	}
	
}
