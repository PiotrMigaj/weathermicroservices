package pl.migibud.forecast;

import org.springframework.data.jpa.repository.JpaRepository;

interface ForecastQueryRepository {
}

interface SqlForecastQueryRepository extends ForecastQueryRepository, JpaRepository<Forecast,Long>{
}