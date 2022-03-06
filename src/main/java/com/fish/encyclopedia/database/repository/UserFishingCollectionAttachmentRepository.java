package com.fish.encyclopedia.database.repository;

import com.fish.encyclopedia.database.entity.UserFishingCollectionAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFishingCollectionAttachmentRepository extends JpaRepository<UserFishingCollectionAttachment, Long> {
}
