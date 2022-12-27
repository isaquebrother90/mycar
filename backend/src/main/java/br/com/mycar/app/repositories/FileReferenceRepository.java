package br.com.mycar.app.repositories;

import br.com.mycar.app.entities.FileReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FileReferenceRepository extends JpaRepository<FileReference, UUID> {
}
