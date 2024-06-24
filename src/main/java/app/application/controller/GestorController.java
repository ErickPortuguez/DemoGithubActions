package app.application.controller;

import app.domain.GestorDto;
import app.infrastructure.repository.GestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/gestor")
public class GestorController {

    @Autowired
    private GestorRepository gestorRepository;

    @GetMapping("/obtener")
    public Flux<GestorDto> obtenerGestor() {
        return gestorRepository.findAll();
    }

    @GetMapping("/obtener/{id}")
    public Mono<GestorDto> obtenerGestorPorId(@PathVariable Integer id) {
        return gestorRepository.findById(id);
    }

    @PostMapping("/crear")
    public Mono<GestorDto> crearGestor(@RequestBody GestorDto gestorDto) {
        gestorDto.setEstado("A");
        return gestorRepository.save(gestorDto);
    }

    @PutMapping("/editar/{id}")
    public Mono<ResponseEntity<GestorDto>> editarGestorPorId(
            @PathVariable Integer id,
            @RequestBody GestorDto gestorDtoInput
    ) {
        return gestorRepository.findById(id)
                .flatMap(existingGestor -> {
                    existingGestor.setDni(gestorDtoInput.getDni());
                    existingGestor.setUsuario(gestorDtoInput.getUsuario());
                    existingGestor.setDireccion(gestorDtoInput.getDireccion());
                    existingGestor.setNombreEmpresa(gestorDtoInput.getNombreEmpresa());
                    existingGestor.setRuc(gestorDtoInput.getRuc());
                    existingGestor.setRazonSocial(gestorDtoInput.getRazonSocial());

                    return gestorRepository.save(existingGestor)
                            .map(updatedGestor -> ResponseEntity.ok(updatedGestor));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Nueva implementación para prevenir SSRF demo
    @GetMapping("/buscar-restaurante/{id}")
    public Mono<String> buscarRestaurante(@PathVariable Integer id) {
        // Simulación de construcción de la URL del restaurante
        String url = "https://api.example.com/restaurante/" + id; // URL válida
        if (validarURL(url)) {
            return hacerSolicitudHttp(url);
        } else {
            return Mono.error(new RuntimeException("URL no válida"));
        }
    }

    // Método para validar la URL
    private boolean validarURL(String url) {
        // Validar la URL utilizando una expresión regular
        String regex = "^(https?)://.*$"; // Solo se permiten protocolos HTTP y HTTPS
        return url.matches(regex);
    }

    // Método para simular la solicitud HTTP
    private Mono<String> hacerSolicitudHttp(String url) {

        String restauranteData = "Datos del restaurante con ID " + obtenerIdDesdeUrl(url);
        return Mono.just(restauranteData);
    }

    // Método para obtener el ID del restaurante a partir de la URL
    private Integer obtenerIdDesdeUrl(String url) {
        // En esta simulación, simplemente extraeremos el último segmento de la URL como ID
        String[] segments = url.split("/");
        return Integer.parseInt(segments[segments.length - 1]);
    }
}
