package com.rsjava.attachment.attachement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface AttachmentRepository extends JpaRepository<AttachmentEntity, Long> {

    Optional<AttachmentEntity> findByUuid(String fileUuid);

    Set<AttachmentEntity> findByCarUuid(String carUuid);

    void deleteByUuid(String fileUuid);
}
