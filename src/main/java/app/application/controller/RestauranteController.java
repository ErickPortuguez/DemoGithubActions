package app.application.controller;

import app.domain.RestauranteDto;
import app.application.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/restaurants/v1")
public class RestauranteController {

    private final RestauranteService restauranteService;

    @Autowired
    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping("/listar")
    public Flux<RestauranteDto> listarRestaurantes() {
        return restauranteService.obtenerTodos();
    }

    @Transactional
    @PostMapping("/crear")
    public Mono<ResponseEntity<RestauranteDto>> crearRestaurante(@RequestBody RestauranteDto restauranteDto) {
        return restauranteService.crearRestaurante(restauranteDto)
                .map(savedRestaurante -> ResponseEntity.ok(savedRestaurante));
    }

    @Transactional
    @PutMapping("/editar/{id}")
    public Mono<ResponseEntity<RestauranteDto>> editarRestaurante(@PathVariable Integer id, @RequestBody RestauranteDto restauranteDto) {
        return restauranteService.editarRestaurante(id, restauranteDto)
                .map(updatedRestaurante -> ResponseEntity.ok(updatedRestaurante))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("/desactivar/{id}")
    public Mono<ResponseEntity<RestauranteDto>> desactivarRestaurante(@PathVariable Integer id) {
        return restauranteService.eliminarLogicoRestaurante(id)
                .map(updatedRestaurante -> ResponseEntity.ok(updatedRestaurante))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Transactional
    @PutMapping("/restaurar/{id}")
    public Mono<ResponseEntity<RestauranteDto>> restaurarRestaurante(@PathVariable Integer id) {
        return restauranteService.restaurarRestaurante(id)
                .map(updatedRestaurante -> ResponseEntity.ok(updatedRestaurante))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
