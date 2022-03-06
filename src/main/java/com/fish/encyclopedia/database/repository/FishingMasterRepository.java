package com.fish.encyclopedia.database.repository;

import com.fish.encyclopedia.database.entity.FishingMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingMasterRepository extends JpaRepository<FishingMaster, Long> {
}
