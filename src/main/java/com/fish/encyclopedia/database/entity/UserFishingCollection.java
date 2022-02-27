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
@Table(name = "tbl_fishing_collection")
public class UserFishingCollection extends TimeDefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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

    @OneToMany(mappedBy = "userFishingCollection")
    private List<UserFishingCollectionAttachment> userFishingCollectionAttachments = new ArrayList<>();
}
