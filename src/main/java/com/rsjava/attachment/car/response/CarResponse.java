package com.rsjava.attachment.car.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CarResponse {
    private final String uuid;
    private final long id;
    private final String band;
    private final String model;
    private final LocalDateTime creationDateTime;
}
