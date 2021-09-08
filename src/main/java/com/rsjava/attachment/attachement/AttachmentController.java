package com.rsjava.attachment.attachement;

import com.rsjava.attachment.attachement.response.AttachmentResponse;
import com.rsjava.attachment.attachement.response.AttachmentBasicResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService service;

    @PostMapping("files")
    public Set<String> uploadFiles(@RequestParam("file") MultipartFile[] file,
                                   @RequestParam String carUuid) {

        return service.storeFiles(file, carUuid);
    }

    @GetMapping("files/{fileUuid}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileUuid) {

        AttachmentResponse file = service.getFile(fileUuid);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getData());
    }

    @GetMapping("cars/{carUuid}/files")
    public Set<AttachmentBasicResponse> getFilesByCarUuid(@PathVariable String carUuid) {

      return service.getFilesByCarUuid(carUuid);
    }

    @DeleteMapping("files/{fileUuid}")
    public void deleteFile(@PathVariable String fileId) {
        service.deleteFile(fileId);
    }
}

