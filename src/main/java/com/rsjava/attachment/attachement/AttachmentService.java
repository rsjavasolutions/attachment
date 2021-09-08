package com.rsjava.attachment.attachement;

import com.rsjava.attachment.attachement.exception.AttachmentNotFoundException;
import com.rsjava.attachment.attachement.mapper.AttachmentMapper;
import com.rsjava.attachment.attachement.response.AttachmentResponse;
import com.rsjava.attachment.attachement.response.AttachmentBasicResponse;
import com.rsjava.attachment.car.CarEntity;
import com.rsjava.attachment.car.CarRepository;
import com.rsjava.attachment.car.exception.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final CarRepository carRepository;

    public Set<String> storeFiles(MultipartFile[] files, String carUUid) {
        return Arrays.stream(files)
                .map(file -> storeFile(file, carUUid))
                .collect(Collectors.toSet());
    }

    private String storeFile(MultipartFile file, String carUuid) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        log.info("File saved: " + fileName);

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file " + fileName);
            }
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            CarEntity carEntity = carRepository.findByUuid(carUuid).orElseThrow(() -> new CarNotFoundException(carUuid));

            return attachmentRepository.save(AttachmentMapper.mapToEntity(fileName, file, carEntity)).getUuid();

        } catch (Exception e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again! \n"
                    + e.getMessage());
        }
    }

    public AttachmentResponse getFile(String fileUuid) {
        AttachmentEntity attachment = attachmentRepository.findByUuid(fileUuid).orElseThrow(() -> new AttachmentNotFoundException(fileUuid));

        return AttachmentMapper.mapToResponse(attachment);
    }

    public Set<AttachmentBasicResponse> getFilesByCarUuid(String carUuid) {
        Set<AttachmentEntity> attachments = attachmentRepository.findByCarUuid(carUuid);

        return attachments
                .stream()
                .map(AttachmentMapper::mapToSlimResponse)
                .collect(Collectors.toSet());
    }

    public void deleteFile(String fileUuid) {
        attachmentRepository.deleteByUuid(fileUuid);
    }
}

