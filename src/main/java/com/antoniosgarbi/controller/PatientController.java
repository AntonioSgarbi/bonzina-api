package com.antoniosgarbi.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.service.PatientService;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
	
	@Autowired
	private PatientService service;
	
	@GetMapping
	public ResponseEntity<Page<PatientDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@GetMapping(value = "/quantity")
	public ResponseEntity<Long> count() {
		return ResponseEntity.ok(service.count());
	}
	
	@PostMapping
	public ResponseEntity<PatientDTO> insert(@RequestBody PatientDTO dto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(service.insert(dto));
	}
	
	@PutMapping
	public ResponseEntity<PatientDTO> update(@RequestBody PatientDTO dto) {
		return ResponseEntity.ok(service.update(dto));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		return ResponseEntity.ok(service.delete(id));
	}
	
}
