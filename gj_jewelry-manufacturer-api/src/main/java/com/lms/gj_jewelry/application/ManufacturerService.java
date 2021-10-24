package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.ManufacturerRepository;
import com.lms.gj_jewelry.exception.ManufacturerIdNotFoundException;
import com.lms.gj_jewelry.exception.ManufacturerNameNotFoundException;
import com.lms.gj_jewelry.domain.Manufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ManufacturerService {

    private ManufacturerRepository manufacturerRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    public Manufacturer createManufacturer(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }

    // TODO: Need to change this as pagination
    public List<Manufacturer> getManufacturers() {
        return manufacturerRepository.findAll();
    }

    public Manufacturer getManufacturerById(Long id) {
        return manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerIdNotFoundException(id));
    }

    public Manufacturer getManufacturerByName(String name) {
        return manufacturerRepository.findByName(name)
                .orElseThrow(() -> new ManufacturerNameNotFoundException(name));
    }

    public Manufacturer updateManufacturer(Manufacturer manufacturer) {
        Manufacturer newManufacturer = manufacturerRepository.findById(manufacturer.getId())
                .orElseThrow(() -> new ManufacturerIdNotFoundException(manufacturer.getId()));

        manufacturerRepository.save(manufacturer);

        return manufacturer;
    }

    public void deleteManufacturer(Long id) {
        Manufacturer deletedManufacturer = manufacturerRepository.findById(id)
                .orElseThrow(() -> new ManufacturerIdNotFoundException(id));

        deletedManufacturer.setDeleted(true);
        deletedManufacturer.setDeletedAt(LocalDate.now());
    }
}
