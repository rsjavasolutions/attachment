package com.rsjava.attachment.car;

import com.rsjava.attachment.attachement.AttachmentEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Setter(AccessLevel.NONE)
    private String uuid;
    private String brand;
    private String model;
    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDateTime;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AttachmentEntity> attachments = new HashSet<>();

    public void addAttachment(AttachmentEntity attachmentEntity){
        attachments.add(attachmentEntity);
        attachmentEntity.setCar(this);
    }

    public CarEntity(String brand,
                     String model) {
        this.uuid = UUID.randomUUID().toString();
        this.brand = brand;
        this.model = model;
        this.creationDateTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarEntity carEntity = (CarEntity) o;
        return Objects.equals(uuid, carEntity.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}

