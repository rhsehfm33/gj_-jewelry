package com.lms.gj_jewelry.application;

import com.lms.gj_jewelry.domain.Item;
import com.lms.gj_jewelry.domain.ItemRepository;
import com.lms.gj_jewelry.enumclass.ItemCategory;
import com.lms.gj_jewelry.exception.ItemIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> getItemsByCategory(ItemCategory itemCategory) {
        return itemRepository.findByCategory(itemCategory);
    }

    public List<Item> getItemsByManufacturer_Name(String manufacturerName) {
        return itemRepository.findByManufacturer_Name(manufacturerName);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemIdNotFoundException(id));
    }

    // TODO: Need Authentication & Authorize
    public Item updateItem(Item item) {
        if (itemRepository.findById(item.getId()).isPresent()) {
            return itemRepository.save(item);
        }
        else {
            throw new ItemIdNotFoundException(item.getId());
        }
    }

    // TODO: Need Authentication & Authorize
    public void deleteItem(Long id) {
        Item deletedItem = itemRepository.findById(id)
                .orElseThrow(() -> new ItemIdNotFoundException(id));

        deletedItem.setDeleted(true);
        deletedItem.setDeletedAt(LocalDate.now());
    }

}
