package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.Item;
import com.lms.gj_jewelry.domain.ItemRepository;
import com.lms.gj_jewelry.domain.Manufacturer;
import com.lms.gj_jewelry.enumclass.ItemCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.lms.gj_jewelry.test.random.RandomItemGenerator.generateRandomItemList;
import static com.lms.gj_jewelry.test.random.RandomManufacturerGenerator.generateRandomManufacturerList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Slf4j
public class ItemServiceTests {

    @Mock
    private ItemRepository itemRepository;

    private ItemService itemService;

    private List<Item> testItemList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Manufacturer> manufacturerList = generateRandomManufacturerList(2);
        itemService = new ItemService(itemRepository);
        testItemList = generateRandomItemList(manufacturerList, 5);
    }

    @Test
    public void testCreateTestItem() {
        Item targetItem = testItemList.get(0);
        given(itemRepository.save(any())).willReturn(targetItem);
        Item newItem = itemService.createItem(targetItem);
        assertThat(newItem, is(targetItem));
    }

    @Test
    public void testGetItems() {
        given(itemRepository.findAll()).willReturn(testItemList);
        List<Item> searchedItemList = itemService.getItems();
        assertThat(searchedItemList.size(), is(testItemList.size()));
    }

    @Test
    public void testGetItemsByName() {
        String targetName = testItemList.get(0).getName();
        given(itemRepository.findByName(targetName)).willReturn(testItemList);
        List<Item> searchedItemList = itemService.getItemsByName(targetName);
        assertThat(searchedItemList.size(), is(testItemList.size()));
    }

    @Test
    public void testGetItemsByCategory() {
        ItemCategory targetItemCategory = testItemList.get(0).getCategory();
        given(itemRepository.findByCategory(targetItemCategory)).willReturn(testItemList);
        List<Item> searchedItemList = itemService.getItemsByCategory(targetItemCategory);
        assertThat(searchedItemList.size(), is(testItemList.size()));
    }

    @Test
    public void testGetItemsByManufacturer() {
        String targetManufacturerName = testItemList.get(0).getManufacturer().getName();
        given(itemRepository.findByManufacturer_Name(targetManufacturerName)).willReturn(testItemList);
        List<Item> searchedItemList = itemService.getItemsByManufacturer_Name(targetManufacturerName);
        assertThat(searchedItemList.size(), is(testItemList.size()));
    }

    @Test
    public void testGetItemById() {
        Item targetItem = testItemList.get(0);
        Long targetItemId = targetItem.getId();
        given(itemRepository.findById(targetItemId)).willReturn(Optional.of(targetItem));
        Item searchedItem = itemService.getItemById(targetItemId);
        assertThat(searchedItem, is(targetItem));
    }

}
