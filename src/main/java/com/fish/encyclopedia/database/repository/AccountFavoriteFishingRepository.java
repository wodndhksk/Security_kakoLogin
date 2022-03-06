package com.fish.encyclopedia.database.repository;

import com.fish.encyclopedia.database.entity.AccountFavoriteFishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountFavoriteFishingRepository extends JpaRepository<AccountFavoriteFishing, Long> {
}
