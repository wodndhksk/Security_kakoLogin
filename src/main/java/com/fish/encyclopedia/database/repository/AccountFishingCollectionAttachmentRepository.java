package com.fish.encyclopedia.database.repository;

import com.fish.encyclopedia.database.entity.AccountFishingCollectionAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountFishingCollectionAttachmentRepository extends JpaRepository<AccountFishingCollectionAttachment, Long> {
}
