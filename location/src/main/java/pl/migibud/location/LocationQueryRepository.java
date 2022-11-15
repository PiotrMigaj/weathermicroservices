package pl.migibud.location;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.migibud.projection.LocationView;

import java.util.Optional;

public interface LocationQueryRepository {
    Page<LocationView> findAllBy(Pageable pageable);
    Optional<LocationView> findAllById(Long id);
}

interface SqlLocationQueryRepository extends LocationQueryRepository, JpaRepository<Location,Long>{
}
