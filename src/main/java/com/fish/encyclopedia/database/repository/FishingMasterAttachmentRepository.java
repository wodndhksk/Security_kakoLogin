package com.fish.encyclopedia.database.repository;

import com.fish.encyclopedia.database.entity.FishingMasterAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingMasterAttachmentRepository extends JpaRepository<FishingMasterAttachment, Long> {
}
