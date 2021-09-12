package com.antoniosgarbi.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.service.PatientService;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
	
	private final PatientService service;

	public PatientController(PatientService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Page<PatientDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@GetMapping(value = "/{name}")
	public ResponseEntity<Page<PatientDTO>> findByName(@PathVariable String name, Pageable pageable) {
		return ResponseEntity.ok(service.findByName(name, pageable));
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
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}