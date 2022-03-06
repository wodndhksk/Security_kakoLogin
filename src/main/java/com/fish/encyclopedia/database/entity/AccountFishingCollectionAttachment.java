package com.fish.encyclopedia.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "tbl_account_fishing_collection_attachment")
public class AccountFishingCollectionAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_fishing_collection_id", referencedColumnName = "id")
    private AccountFishingCollection accountFishingCollection;

    @Column
    @NotBlank
    private String url;

    @Column
    private String thumbnailUrl;

    @Column
    @NotBlank
    private String type;

    @Column
    @NotBlank
    private String name;
}
