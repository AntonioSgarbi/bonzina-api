package com.antoniosgarbi.controller;

import com.antoniosgarbi.dto.CounterScheduleDTO;
import com.antoniosgarbi.dto.SchedulingDTO;
import com.antoniosgarbi.service.SchedulingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value ="/scheduling")
public class SchedulingController {

    private final SchedulingService service;

    public SchedulingController(SchedulingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<SchedulingDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping(value = "/bypatient/{name}")
    public ResponseEntity<Page<SchedulingDTO>> findByPatientName(@PathVariable String name, Pageable pageable) {
        return ResponseEntity.ok(service.findByPatientName(name, pageable));
    }

    @GetMapping(value = "/bydate/{dateString}")
    public ResponseEntity<Page<SchedulingDTO>> findByDateBetween(@PathVariable String dateString, Pageable pageable) {
        return ResponseEntity.ok(service.findByDate(dateString, pageable));
    }

    @GetMapping(value = "/bydate/today")
    public ResponseEntity<Page<SchedulingDTO>> findByDateToday(Pageable pageable) {
        return ResponseEntity.ok(service.findByDateToday(pageable));
    }

    @GetMapping(value = "/bydate/week")
    public ResponseEntity<Page<SchedulingDTO>> findByDateWeek(Pageable pageable) {
        return ResponseEntity.ok(service.findByDateThisWeek(pageable));
    }

    @GetMapping(value = "/bydate/month")
    public ResponseEntity<Page<SchedulingDTO>> findByDateMonth(Pageable pageable) {
        return ResponseEntity.ok(service.findByDateThisMonth(pageable));
    }

    @GetMapping(value = "/quantity")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(service.count());
    }

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
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/quantitybydate")
    public ResponseEntity<CounterScheduleDTO> countByDate() {
        return ResponseEntity.ok(service.countTotal());
    }
}
