package br.com.mycar.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Entity
public class FileReference {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id")
    private UUID id;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private String name;

    private String contentType;

    private Long contentLength;

    @Enumerated(EnumType.STRING)
    private Type type;

    public FileReference() {
    }

    public FileReference(String name, String contentType,
                         Long contentLength, Type type) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(contentType);
        Objects.requireNonNull(contentLength);
        Objects.requireNonNull(type);

        this.name = name;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.type = type;
    }

    public String getPath() {
        return this.id + "/" + this.name + "." + this.contentType.substring(6);
    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        DOCUMENT,
        IMAGE;

    }
}
