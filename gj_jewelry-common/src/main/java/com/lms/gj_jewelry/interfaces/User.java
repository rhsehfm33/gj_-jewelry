package com.lms.gj_jewelry.interfaces;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode
@ToString
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String account;

    @NotEmpty
    private String password;

    @NotEmpty
    private String status;

    @Column(unique = true)
    private String email;

    @NotEmpty
    private String phoneNumber;

    @NotNull
    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deletedAt;

}
