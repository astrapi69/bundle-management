package de.alpharogroup.bundlemanagement.jpa.repository;

import de.alpharogroup.db.entity.uniqueable.UUIDEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface UUIDEntityRepository<T extends UUIDEntity> extends JpaRepository<T, UUID>
{
    Optional<T> findByUuid(UUID uuid);
}