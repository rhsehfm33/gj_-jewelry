package com.lms.gj_jewelry.interfaces;

import com.lms.gj_jewelry.application.ItemService;
import com.lms.gj_jewelry.domain.Item;
import com.lms.gj_jewelry.enumclass.ItemCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/item", produces = "application/json;charset=utf-8")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(
            @RequestBody @Valid Item item
    ) {
        return itemService.createItem(item);
    }

    @GetMapping("/all")
    public List<Item> getItems() {
        return itemService.getItems();
    }

    @GetMapping("/name/{name}")
    public List<Item> getItemByName(@PathVariable String name) {
        return itemService.getItemsByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Item> getItemByCategory(@PathVariable ItemCategory category) {
        return itemService.getItemsByCategory(category);
    }

    @GetMapping("/manufacturer/name/{name}")
    public List<Item> getItemByManufacturer_Name(@PathVariable String name) {
        return itemService.getItemsByManufacturer_Name(name);
    }

    @GetMapping("/id/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PatchMapping("")
    public Item updateItem(
            @RequestBody @Valid Item item
    ) {
        return itemService.updateItem(item);
    }

    @DeleteMapping("id/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

}
