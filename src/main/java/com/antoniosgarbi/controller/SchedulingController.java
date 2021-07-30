package com.antoniosgarbi.controller;

import com.antoniosgarbi.dto.PatientDTO;
import com.antoniosgarbi.dto.SchedulingDTO;
import com.antoniosgarbi.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value ="/scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService service;

    @PostMapping
    public ResponseEntity<SchedulingDTO> insert(@RequestBody SchedulingDTO dto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(service.insert(dto));
    }

    @PutMapping
    public ResponseEntity<SchedulingDTO> update(@RequestBody SchedulingDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}
