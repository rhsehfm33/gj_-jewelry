package com.lms.gj_jewelry.domain;

import com.lms.gj_jewelry.enumclass.ItemCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static com.lms.gj_jewelry.test.random.RandomItemGenerator.generateRandomItem;
import static com.lms.gj_jewelry.test.random.RandomManufacturerGenerator.generateRandomManufacturerList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class ItemRepositoryTests {

    final int INSERTED_ITEMS = 5;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ManufacturerRepository manufacturerRepository;

    List<Manufacturer> dummyManufactureList;

    List<Item> dummyItemList;

    @Before
    public void setUp() {
        dummyManufactureList = manufacturerRepository.saveAll(generateRandomManufacturerList(5));;

        dummyItemList = new ArrayList<>();
        for (int i = 0; i < INSERTED_ITEMS; ++i) {
            dummyItemList.add(itemRepository.save(generateRandomItem(dummyManufactureList)));
        }
    }

    @Test
    public void testFindAll() {
        List<Item> newItemList = itemRepository.findAll();
        assertThat(newItemList.size(), is(INSERTED_ITEMS));
    }

    @Test
    public void testFindAllByName() {
        String targetItemName = dummyItemList.get(0).getName();
        assertFalse(itemRepository.findByName(targetItemName).isEmpty());
    }

    @Test
    public void testFindAllByCategory() {
        ItemCategory targetItemCategory = dummyItemList.get(0).getCategory();
        assertFalse(itemRepository.findByCategory(targetItemCategory).isEmpty());
    }

    @Test
    public void testFindAllByManufacturer() {
        String targetItemManufacturerName = dummyItemList.get(0).getManufacturer().getName();
        assertFalse(itemRepository.findByManufacturer_Name(targetItemManufacturerName).isEmpty());
    }

    @Test
    public void testFindById() {
        long targetItemId = itemRepository.findAll().get(0).getId();
        assertTrue(itemRepository.findById(targetItemId).isPresent());
    }

}
