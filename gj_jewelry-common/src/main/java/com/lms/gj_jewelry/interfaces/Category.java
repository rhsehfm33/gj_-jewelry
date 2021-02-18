package com.lms.gj_jewelry.interfaces;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"itemList"})
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String type;

    private String description;

    @CreatedDate
    @NotNull
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updateAt;

    // category : Item =  1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Item> itemList;
}
