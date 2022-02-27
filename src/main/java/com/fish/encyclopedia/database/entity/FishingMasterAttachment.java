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
@Table(name = "tbl_fishing_master_attachment")
public class FishingMasterAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fishing_master_id", referencedColumnName = "id")
    private FishingMaster fishingMaster;

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
