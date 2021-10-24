package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.ManufacturerRepository;
import com.lms.gj_jewelry.exception.ManufacturerIdNotFoundException;
import com.lms.gj_jewelry.exception.ManufacturerNameNotFoundException;
import com.lms.gj_jewelry.domain.Manufacturer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.lms.gj_jewelry.test.random.RandomManufacturerGenerator.generateRandomManufacturer;
import static com.lms.gj_jewelry.test.random.RandomManufacturerGenerator.generateRandomManufacturerList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ManufacturerServiceTests {

    private ManufacturerService manufacturerService;

    @Mock
    private ManufacturerRepository manufacturerRepository;

    private Manufacturer testManufacturer;

    @Before()
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        manufacturerService = new ManufacturerService(manufacturerRepository);
        testManufacturer = generateRandomManufacturer();
    }

    @Test
    public void testCreateManufacture() {
        given(manufacturerRepository.save(any())).willReturn(testManufacturer);

        Manufacturer newManufacturer = manufacturerService.createManufacturer(testManufacturer);

        assertThat(newManufacturer.equals(testManufacturer), is(true));
    }

    @Test
    public void testGetManufacturers() {
        List<Manufacturer> manufacturerList = generateRandomManufacturerList(5);
        given(manufacturerRepository.findAll()).willReturn(manufacturerList);

        List<Manufacturer> insertedManufacturerList = manufacturerService.getManufacturers();

        for (int i = 0; i < manufacturerList.size(); ++i) {
            assertThat(insertedManufacturerList.get(i).equals(manufacturerList.get(i)), is(true));
        }
    }

    @Test(expected = ManufacturerNameNotFoundException.class)
    public void testGetManufacturerByEmailIfNotExists() {
        given(manufacturerRepository.findByName(any())).willReturn(Optional.empty());

        manufacturerService.getManufacturerByName("NotExists");
    }

    @Test(expected = ManufacturerIdNotFoundException.class)
    public void testGetManufacturerByIdIfNotExists() {
        given(manufacturerRepository.findById(any())).willReturn(Optional.empty());

        manufacturerService.getManufacturerById(2L);
    }

    @Test
    public void testGetManufactureByIdIfExists() {
        given(manufacturerRepository.findById(testManufacturer.getId())).willReturn(Optional.of(testManufacturer));

        Manufacturer searchedManufacturer = manufacturerService.getManufacturerById(testManufacturer.getId());

        assertThat(searchedManufacturer.equals(testManufacturer), is(true));
    }

    @Test
    public void testGetManufactureByNameIfExists() {
        given(manufacturerRepository.findByName(testManufacturer.getName())).willReturn(Optional.of(testManufacturer));

        Manufacturer searchedManufacturer = manufacturerService.getManufacturerByName(testManufacturer.getName());

        assertThat(searchedManufacturer.equals(testManufacturer), is(true));
    }

    @Test
    public void testDeleteManufacturer() {
        given(manufacturerRepository.findById(testManufacturer.getId())).willReturn(Optional.of(testManufacturer));

        manufacturerService.deleteManufacturer(testManufacturer.getId());

        assertThat(testManufacturer.isDeleted(), is(true));
    }

}
