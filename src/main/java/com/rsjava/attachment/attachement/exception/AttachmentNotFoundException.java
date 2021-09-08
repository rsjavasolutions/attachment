package com.rsjava.attachment.attachement.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AttachmentNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Can't find attachment with uuid: %s";

    public AttachmentNotFoundException(String uuid) {
        super(String.format(MESSAGE, uuid));
        log.debug(String.format(MESSAGE, uuid));
    }
}
