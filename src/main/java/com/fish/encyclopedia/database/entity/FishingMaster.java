package com.fish.encyclopedia.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "tbl_fishing_master")
public class FishingMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String tel;

    @Column
    private String address;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column
    private String mainSpecies;

    @Column
    private String usageFee;
}
