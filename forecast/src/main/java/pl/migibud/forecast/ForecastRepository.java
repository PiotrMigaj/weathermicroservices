package pl.migibud.forecast;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface ForecastRepository {
    <S extends Forecast> S save(S entity);
    List<Forecast> findByForecastDateAndLocationId(LocalDate forecastDate, Long locationId);
    Optional<Forecast> findByCreateDateAndForecastDateAndLocationId(LocalDate createDate, LocalDate forecastDate, Long locationId);
}

interface SqlForecastRepository extends ForecastRepository, JpaRepository<Forecast,Long>{
}
