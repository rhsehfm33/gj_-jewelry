package com.lms.gj_jewelry.interfaces;

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
@ToString(exclude = {"item"})
public class Manufacturer {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Column(unique = true)
    private String account;

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    private String country;

    @Value("0")
    private Long likes;

    private String description;

    @OneToOne()
    private LocalDate foundingDate;

    // Manufacturer : Item = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

}
