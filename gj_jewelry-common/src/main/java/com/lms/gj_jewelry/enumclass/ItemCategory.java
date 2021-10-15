package com.lms.gj_jewelry.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemCategory {

    BRACELET(0, "팔찌"),
    RING(1, "반지"),
    NECKLACE(2, "목거리"),
    EARRING(3, "귀걸이"),
    ANKLET(4, "발찌"),
    BADGE(5, "배찌"),
    HAIR_ACCESSORY(6, "헤어 액세서리");

    private Integer id;
    
    private String title;

}
