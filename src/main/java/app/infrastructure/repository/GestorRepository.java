package app.infrastructure.repository;

import app.domain.GestorDto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GestorRepository extends ReactiveCrudRepository<GestorDto,Integer> {
}
