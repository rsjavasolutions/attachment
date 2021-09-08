package com.rsjava.attachment.attachement;

import com.rsjava.attachment.car.CarEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attachment")
public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    @Setter(AccessLevel.NONE)
    private String uuid;
    private String fileName;
    private String fileType;
    @Column(columnDefinition = "bytea")
    private byte[] data;
    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDateTime;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    public AttachmentEntity(String fileName,
                            String fileType,
                            byte[] data,
                            CarEntity car) {
        this.uuid = UUID.randomUUID().toString();
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.creationDateTime = LocalDateTime.now();
        this.car = car;
        car.getAttachments().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttachmentEntity that = (AttachmentEntity) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
