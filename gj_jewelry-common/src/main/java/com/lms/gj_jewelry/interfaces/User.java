package com.lms.gj_jewelry.interfaces;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
@Where(clause = "deleted = false")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    // TODO: make these as enumerated values
    @NotEmpty
    @Column(unique = true)
    private String account;

    @NotEmpty
    private String password;

    @NotEmpty
    private String status;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    @NotEmpty
    private String phoneNumber;

    @CreatedDate
    @NotNull
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    @ColumnDefault("0")
    private boolean deleted;

    private LocalDate deletedAt;

    // TODO : make User : OrderGroup = 1 : N

    public User updateInformation(User user) {
        this.id = user.getId();
        this.account = user.getAccount();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
        this.deletedAt = user.getDeletedAt();

        return this;
    }
}
