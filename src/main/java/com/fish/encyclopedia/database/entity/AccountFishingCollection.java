package com.fish.encyclopedia.database.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "tbl_account_fishing_collection")
public class AccountFishingCollection extends TimeDefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "fishing_master_id", referencedColumnName = "id")
    private FishingMaster fishingMaster;

    @Column
    private LocalDateTime beginAt;

    @Column
    private LocalDateTime endAt;

    @Column
    private String content;

    @Column
    private String fishSpecies;

    @Column
    private Integer fishLength;

    @Column
    private Integer fishWeight;

    @OneToMany(mappedBy = "accountFishingCollection")
    private List<AccountFishingCollectionAttachment> accountFishingCollectionAttachments = new ArrayList<>();
}
