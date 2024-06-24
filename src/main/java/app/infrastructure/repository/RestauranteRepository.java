package app.infrastructure.repository;

import app.domain.RestauranteDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RestauranteRepository extends ReactiveCrudRepository<RestauranteDto, Integer> {
}
