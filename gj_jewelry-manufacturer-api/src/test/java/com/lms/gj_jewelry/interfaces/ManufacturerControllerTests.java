package com.lms.gj_jewelry.interfaces;

import com.lms.gj_jewelry.GjJewelryManufacturerApiApplication;
import com.lms.gj_jewelry.application.ManufacturerService;
import com.lms.gj_jewelry.domain.Manufacturer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.lms.gj_jewelry.test.data_check.JsonObjectConverter.convertObjectToJson;
import static com.lms.gj_jewelry.test.data_check.MvcResultChecker.isMvcResultEqualTo;
import static com.lms.gj_jewelry.test.random.RandomManufacturerGenerator.generateRandomManufacturer;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GjJewelryManufacturerApiApplication.class)
@WebMvcTest(ManufacturerController.class)
public class ManufacturerControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ManufacturerService manufacturerService;

    private Manufacturer testManufacturer;

    @Before
    public void setUp() {
        testManufacturer = generateRandomManufacturer();
    }

    @Test
    public void testCreateManufacturer() throws Exception {
        given(manufacturerService.createManufacturer(any())).willReturn(testManufacturer);

        MvcResult result = mvc.perform(post("/manufacturer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(testManufacturer)))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(isMvcResultEqualTo(testManufacturer, result, Manufacturer.class), is(true));
    }

    @Test
    public void testGetManufacturerById() throws Exception {
        given(manufacturerService.getManufacturerById(any())).willReturn(testManufacturer);

        MvcResult result = mvc.perform(get("/manufacturer/id/" + testManufacturer.getId()))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(testManufacturer, result, Manufacturer.class), is(true));
    }

    @Test
    public void testGetManufacturerByName() throws Exception {
        given(manufacturerService.getManufacturerByName(any())).willReturn(testManufacturer);

        MvcResult result = mvc.perform((get("/manufacturer/name/" + testManufacturer.getName())))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(testManufacturer, result, Manufacturer.class), is(true));
    }

    @Test
    public void testUpdateManufacturer() throws Exception {
        given(manufacturerService.updateManufacturer(any())).willReturn(testManufacturer);

        MvcResult result = mvc.perform(patch("/manufacturer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(testManufacturer)))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(isMvcResultEqualTo(testManufacturer, result, Manufacturer.class), is(true));
    }

    @Test
    public void testDeleteManufacturer() throws Exception {
        mvc.perform(delete("/manufacturer/id/" + testManufacturer.getId()))
                .andExpect(status().isOk());

        verify(manufacturerService).deleteManufacturer(testManufacturer.getId());
    }

}
