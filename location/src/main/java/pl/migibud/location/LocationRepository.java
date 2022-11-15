package pl.migibud.location;

import org.springframework.data.jpa.repository.JpaRepository;

interface LocationRepository {
    <S extends Location> S save(S entity);
    long count();

}

interface SqlLocationRepository extends LocationRepository, JpaRepository<Location,Long>{
}
