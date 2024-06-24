package app.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("restaurante")
public class RestauranteDto {
    @Id
    private Integer id;
    private String nombre;
    private String tipoCocina;
    private String direccion;
    private String telefono;
    private String horarioFuncionamiento;
    private String categoria;
    private Integer gestorId;
    private String estado;
}
