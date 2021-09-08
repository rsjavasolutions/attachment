package com.rsjava.attachment.attachement.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AttachmentBasicResponse {
    private final long id;
    private final String uuid;
    private final String fileName;
    private final String fileType;
    private final LocalDateTime creationDateTime;
}
