package com.fish.encyclopedia.database.repository;

import com.fish.encyclopedia.database.entity.UserFishingCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFishingCollectionRepository extends JpaRepository<UserFishingCollection, Long> {
}
