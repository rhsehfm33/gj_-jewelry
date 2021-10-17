package com.lms.gj_jewelry.interfaces;

import com.lms.gj_jewelry.enumclass.ItemCategory;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"orderDetailList", "ItemImageList", "manufacturer"})
@Where(clause = "deleted = false")
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String status;

    @NotEmpty
    private String name;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private ItemCategory category;

    private BigDecimal price;

    @CreatedDate
    @NotNull LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    @ColumnDefault("0")
    private boolean deleted;

    private LocalDate deletedAt;

    // Item : OrderDetail =  1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

    // Item : ItemImage =  1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<ItemImage> itemImageList;

    // Item : Manufacturer = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    private Manufacturer manufacturer;

}
