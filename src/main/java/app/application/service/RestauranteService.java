package app.application.service;

import app.domain.RestauranteDto;
import app.infrastructure.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Autowired
    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Flux<RestauranteDto> obtenerTodos() {
        return restauranteRepository.findAll();
    }

    public Mono<RestauranteDto> crearRestaurante(RestauranteDto restauranteDto) {
        return restauranteRepository.save(restauranteDto);
    }

    public Mono<RestauranteDto> editarRestaurante(Integer id, RestauranteDto restauranteDto) {
        return restauranteRepository.findById(id)
                .flatMap(existingRestaurante -> {
                    restauranteDto.setId(existingRestaurante.getId());
                    return restauranteRepository.save(restauranteDto);
                });
    }

    public Mono<RestauranteDto> eliminarLogicoRestaurante(Integer id) {
        return restauranteRepository.findById(id)
                .flatMap(existingRestaurante -> {
                    existingRestaurante.setEstado("Inactivo");
                    return restauranteRepository.save(existingRestaurante);
                });
    }

    public Mono<RestauranteDto> restaurarRestaurante(Integer id) {
        return restauranteRepository.findById(id)
                .flatMap(existingRestaurante -> {
                    existingRestaurante.setEstado("Activo");
                    return restauranteRepository.save(existingRestaurante);
                });
    }
}
