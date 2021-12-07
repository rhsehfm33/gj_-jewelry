package com.lms.gj_jewelry.interfaces;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lms.gj_jewelry.GjJewelryAdminApiApplication;
import com.lms.gj_jewelry.application.ItemService;
import com.lms.gj_jewelry.domain.Item;
import com.lms.gj_jewelry.domain.Manufacturer;
import com.lms.gj_jewelry.enumclass.ItemCategory;
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

import java.util.List;

import static com.lms.gj_jewelry.test.data_check.JsonObjectConverter.convertObjectToJson;
import static com.lms.gj_jewelry.test.data_check.MvcResultChecker.isMvcResultEqualTo;
import static com.lms.gj_jewelry.test.random.RandomItemGenerator.generateRandomItemList;
import static com.lms.gj_jewelry.test.random.RandomManufacturerGenerator.generateRandomManufacturerList;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GjJewelryAdminApiApplication.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ItemService itemService;

    private List<Item> testItemList;

    @Before
    public void setUp() {
        List<Manufacturer> testManufacturerList = generateRandomManufacturerList(5);
        testItemList = generateRandomItemList(testManufacturerList, 5);
    }

    @Test
    public void testCreateItem() throws Exception {
        Item targetItem = testItemList.get(0);
        given(itemService.createItem(any())).willReturn(targetItem);

        MvcResult result = mvc.perform(post("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(targetItem)))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(isMvcResultEqualTo(targetItem, result, Item.class));
    }

    @Test
    public void testGetItems() throws Exception {
        given(itemService.getItems()).willReturn(testItemList);

        MvcResult result = mvc.perform(get("/item/all"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(isMvcResultEqualTo(testItemList, result, new TypeReference<List<Item>>(){}));
    }

    @Test
    public void testGetItemByName() throws Exception {
        String targetItemName = testItemList.get(0).getName();
        given(itemService.getItemsByName(targetItemName)).willReturn(testItemList);

        MvcResult result = mvc.perform(get("/item/name/" + targetItemName))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(isMvcResultEqualTo(testItemList, result, new TypeReference<List<Item>>(){}));
    }

    @Test
    public void testGetItemByCategory() throws Exception {
        ItemCategory targetItemCategory = testItemList.get(0).getCategory();
        given(itemService.getItemsByCategory(targetItemCategory)).willReturn(testItemList);

        MvcResult result = mvc.perform(get("/item/category/" + targetItemCategory))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(isMvcResultEqualTo(testItemList, result, new TypeReference<List<Item>>(){}));
    }

    @Test
    public void testGetItemByManufacturer_Name() throws Exception {
        String targetManufacturerName = testItemList.get(0).getManufacturer().getName();
        given(itemService.getItemsByManufacturer_Name(targetManufacturerName)).willReturn(testItemList);

        MvcResult result = mvc.perform(get("/item/manufacturer/name/" + targetManufacturerName))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(isMvcResultEqualTo(testItemList, result, new TypeReference<List<Item>>(){}));
    }

    @Test
    public void testGetItemById() throws Exception {
        Item targetItem = testItemList.get(0);
        Long targetItemId = targetItem.getId();
        given(itemService.getItemById(targetItemId)).willReturn(targetItem);

        MvcResult result = mvc.perform(get("/item/id/" + targetItemId))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(isMvcResultEqualTo(targetItem, result, Item.class));
    }

    @Test
    public void testUpdateItem() throws Exception {
        Item originalItem = testItemList.get(0);
        Item updatedItem = testItemList.get(1);
        given(itemService.updateItem(originalItem)).willReturn(updatedItem);

        MvcResult result = mvc.perform(patch("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJson(originalItem)))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(isMvcResultEqualTo(updatedItem, result, Item.class));
    }

    @Test
    public void testDeleteItem() throws Exception {
        Item targetItem = testItemList.get(0);
        Long targetItemId = targetItem.getId();

        mvc.perform(delete("/item/id/" + targetItemId))
                .andExpect(status().isOk());

        verify(itemService).deleteItem(targetItemId);
    }

}
