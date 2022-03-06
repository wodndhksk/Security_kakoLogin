package com.fish.encyclopedia.database.repository;

import com.fish.encyclopedia.database.entity.AccountFishingCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountFishingCollectionRepository extends JpaRepository<AccountFishingCollection, Long> {
}
