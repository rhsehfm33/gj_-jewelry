package com.lms.gj_jewelry.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"itemList"})
public class Manufacturer {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String account;

    @NotEmpty
    private String password;

    @Column(unique = true)
    private String email;

    @NotEmpty
    @Column(unique = true)
    private String phoneNumber;

    @NotEmpty
    @Column(unique = true)
    private String name;

    private String country;

    @Value("0")
    private Long likes;

    private String description;

    private LocalDate foundingDate;

    @ColumnDefault("0")
    private boolean deleted;

    private LocalDate deletedAt;

    // Manufacturer : Item = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
    private List<Item> itemList;

}
