package com.lms.gj_jewelry.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;

@EqualsAndHashCode
@Entity
@Data
@Builder
@NoArgsConstructor
@ToString(exclude = {"user", "item"})
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String status;

    @NotEmpty
    private String paymentType;

    @NotEmpty
    private String arrivalName;

    @NotNull
    private LocalDate arrivalDate;

    @NotEmpty
    private String arrivalAddress;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigInteger totalPrice;

    @CreatedDate
    @NotNull
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    // OrderDetail : User = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // OrderDetail : Item = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
}
