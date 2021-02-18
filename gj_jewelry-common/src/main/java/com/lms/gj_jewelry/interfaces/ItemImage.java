package com.lms.gj_jewelry.interfaces;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"item"})
public class ItemImage {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String fileName;

    @NotEmpty
    private String fileOriName;

    @NotEmpty
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;
}
