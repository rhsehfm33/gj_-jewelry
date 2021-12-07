package com.lms.gj_jewelry.test.random;

import com.lms.gj_jewelry.domain.Item;
import com.lms.gj_jewelry.domain.Manufacturer;
import com.lms.gj_jewelry.enumclass.ItemCategory;

import java.util.ArrayList;
import java.util.List;

import static com.lms.gj_jewelry.test.random.RandomStringGenerator.generateRandomString;
import static com.lms.gj_jewelry.test.random.RandomStringGenerator.random;

public class RandomItemGenerator {

    static final int MAX_NAME_LENGTH = 100;
    static final int MAX_TITLE_LENGTH = 100;
    static final int MAX_CONTENT_LENGTH = 200;
    static final int ITEM_CATEGORY_SIZE = ItemCategory.values().length;

    public static Item generateRandomItem(List<Manufacturer> manufacturerList) {
        long id = random.nextLong();
        String newName = generateRandomString(MAX_NAME_LENGTH, false);
        String newTitle = generateRandomString(MAX_TITLE_LENGTH, false);
        String newContent = generateRandomString(MAX_CONTENT_LENGTH, false);
        ItemCategory newItemCategory = ItemCategory.values()[random.nextInt(ITEM_CATEGORY_SIZE)];

        Item randomItem = Item.builder()
                .id(id)
                .name(newName)
                .title(newTitle)
                .content(newContent)
                .category(newItemCategory)
                .manufacturer(manufacturerList.get(random.nextInt(manufacturerList.size())))
                .build();

        return randomItem;
    }

    public static List<Item> generateRandomItemList(List<Manufacturer> manufacturerList, int neededManufacturers) {
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < neededManufacturers; ++i) {
            itemList.add(generateRandomItem(manufacturerList));
        }

        return itemList;
    }

}
