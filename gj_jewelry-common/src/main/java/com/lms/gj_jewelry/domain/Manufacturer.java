package com.lms.gj_jewelry.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode
@Entity
@Data
@Builder
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

    // Manufacturer : Item = 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manufacturer")
    private List<Item> itemList;

}
