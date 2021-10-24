package com.lms.gj_jewelry.interfaces;

import com.lms.gj_jewelry.application.ManufacturerService;
import com.lms.gj_jewelry.domain.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/manufacturer", produces = "application/json;charset=utf-8")
@RestController
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Manufacturer createManufacturer(
            @RequestBody @Valid Manufacturer manufacturer
    ) {
        return manufacturerService.createManufacturer(manufacturer);
    }

    @GetMapping("/all")
    public List<Manufacturer> getManufacturers() {
        return manufacturerService.getManufacturers();
    }

    @GetMapping("/id/{id}")
    public Manufacturer getManufacturerById(@PathVariable Long id) {
        return manufacturerService.getManufacturerById(id);
    }

    @GetMapping("/name/{name}")
    public Manufacturer getManufacturerByName(@PathVariable String name) {
        return manufacturerService.getManufacturerByName(name);
    }

    @PatchMapping("")
    public Manufacturer updateManufacturer(
            @RequestBody @Valid Manufacturer manufacturer
    ) {
        return manufacturerService.updateManufacturer(manufacturer);
    }

    @DeleteMapping("/id/{id}")
    public void deleteManufacturer(@PathVariable Long id) {
        manufacturerService.deleteManufacturer(id);
    }

}
