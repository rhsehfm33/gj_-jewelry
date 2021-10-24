package com.lms.gj_jewelry.domain;

import com.lms.gj_jewelry.exception.ManufacturerIdNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static com.lms.gj_jewelry.test.random.RandomManufacturerGenerator.generateRandomManufacturer;
import static com.lms.gj_jewelry.test.random.RandomManufacturerGenerator.generateRandomManufacturerList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class ManufacturerTests {

    private static Validator validator;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    private static Manufacturer randomManufacturer;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        randomManufacturer = manufacturerRepository.save(generateRandomManufacturer());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testManufacturerNameDuplication() {
        List<Manufacturer> manufacturers = generateRandomManufacturerList(2);
        manufacturerRepository.saveAndFlush(manufacturers.get(0));

        manufacturers.get(1).setName(manufacturers.get(0).getName());
        manufacturerRepository.saveAndFlush(manufacturers.get(1));
    }

    @Test
    public void testManufacturerNotEmptyFieldsEmpty() {
        Manufacturer manufacturer = Manufacturer.builder().build();
        Set<ConstraintViolation<Manufacturer>> constraintViolations = validator.validate(manufacturer);
        assertThat(constraintViolations.size(), is(4));

        for (ConstraintViolation<Manufacturer> constraintViolation : constraintViolations) {
            log.debug("violation error message : {}", constraintViolation.getMessage());
        }
    }

    @Test
    public void testUpdateManufacturer() {
        Manufacturer insertedManufacturer = manufacturerRepository.findById(randomManufacturer.getId())
                .orElseThrow(() -> new ManufacturerIdNotFoundException(randomManufacturer.getId()));

        insertedManufacturer.setName("TEST--");
        manufacturerRepository.save(insertedManufacturer);

        Manufacturer updatedManufacturer = manufacturerRepository.findById(insertedManufacturer.getId())
                .orElseThrow(() -> new ManufacturerIdNotFoundException(insertedManufacturer.getId()));

        assertEquals(updatedManufacturer.getName(), "TEST--");
    }

}
