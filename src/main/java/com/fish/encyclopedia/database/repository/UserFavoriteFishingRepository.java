package com.fish.encyclopedia.database.repository;

import com.fish.encyclopedia.database.entity.UserFavoriteFishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteFishingRepository extends JpaRepository<UserFavoriteFishing, Long> {
}
