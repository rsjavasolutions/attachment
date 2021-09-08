package com.rsjava.attachment.attachement.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AttachmentResponse extends AttachmentBasicResponse {

    private final byte[] data;

    public AttachmentResponse(long id, String uuid, String fileName, String fileType, LocalDateTime creationDateTime, byte[] data) {
        super(id, uuid, fileName, fileType, creationDateTime);
        this.data = data;
    }
}

