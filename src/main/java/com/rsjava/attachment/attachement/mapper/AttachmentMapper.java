package com.rsjava.attachment.attachement.mapper;

import com.rsjava.attachment.attachement.AttachmentEntity;
import com.rsjava.attachment.attachement.response.AttachmentBasicResponse;
import com.rsjava.attachment.attachement.response.AttachmentResponse;
import com.rsjava.attachment.car.CarEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class AttachmentMapper {

    public static AttachmentEntity mapToEntity(String fileName, MultipartFile file, CarEntity carEntity) {
        AttachmentEntity attachmentEntity = null;

        try {
            attachmentEntity = new AttachmentEntity(
                    fileName,
                    file.getContentType(),
                    file.getBytes(),
                    carEntity
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return attachmentEntity;
    }

    public static AttachmentResponse mapToResponse(AttachmentEntity entity) {
        return new AttachmentResponse(
                entity.getId(),
                entity.getUuid(),
                entity.getFileName(),
                entity.getFileType(),
                entity.getCreationDateTime(),
                entity.getData()
        );
    }

    public static AttachmentBasicResponse mapToSlimResponse(AttachmentEntity entity) {
        return new AttachmentBasicResponse(
                entity.getId(),
                entity.getUuid(),
                entity.getFileName(),
                entity.getFileType(),
                entity.getCreationDateTime()
        );
    }
}
