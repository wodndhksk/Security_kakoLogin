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
@Table(name = "tbl_account")
public class Account extends TimeDefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @NotBlank
    private String username;

    @Column
    @NotBlank
    private String phone;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String email;

    @Column
    @NotBlank
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(length = 2050)
    private String thumbnailImageUrl;

    @Column(length = 2050)
    private String ProfileImageUrl;
}
