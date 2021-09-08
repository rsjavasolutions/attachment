package com.rsjava.attachment.car.mapper;

import com.rsjava.attachment.car.CarEntity;
import com.rsjava.attachment.car.request.CarRequest;
import com.rsjava.attachment.car.response.CarResponse;

public class CarMapper {

    public static CarEntity mapToEntity(CarRequest request) {
        return new CarEntity(
                request.getBrand(),
                request.getModel());
    }

    public static CarResponse mapToResponse(CarEntity entity) {
        return new CarResponse(
                entity.getUuid(),
                entity.getId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getCreationDateTime());

    }
}
