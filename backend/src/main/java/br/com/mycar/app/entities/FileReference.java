package br.com.mycar.app.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder
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

    /*@Builder.Default
    private boolean temp = true;*/

    @Enumerated(EnumType.STRING)
    private Type type;

    protected FileReference() {
    }

    public FileReference(UUID id, OffsetDateTime createdAt, String name, String contentType,
                         Long contentLength, Type type) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(type);

        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.type = type;
    }

    public String getPath() {
        return this.id + "/" + this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Getter
    @AllArgsConstructor
    public enum Type {
        DOCUMENT(false),
        IMAGE(true);

        private final boolean publicAcessible;
    }

    public boolean isPublicAccessible() {
        return this.type.isPublicAcessible();
    }

}
