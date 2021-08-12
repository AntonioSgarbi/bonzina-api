package com.antoniosgarbi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.antoniosgarbi.dto.DoctorDTO;
import com.antoniosgarbi.service.DoctorService;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
	
	private final DoctorService service;

	public DoctorController(DoctorService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<DoctorDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/quantity")
	public ResponseEntity<Long> count() {
		return ResponseEntity.ok(service.count());
	}
	
	@PostMapping
	public ResponseEntity<DoctorDTO> insert(@RequestBody DoctorDTO dto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(service.insert(dto));
	}
	
	@PutMapping
	public ResponseEntity<DoctorDTO> update(@RequestBody DoctorDTO dto) {
		return ResponseEntity.ok().body(service.update(dto));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}	
	
}
