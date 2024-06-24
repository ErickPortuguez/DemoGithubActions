package app.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "gestor")
public class GestorDto {
    @Id
    private Integer id;
    private String dni;
    private String usuario;
    private String direccion;
    private String nombreEmpresa;
    private String ruc;
    private String razonSocial;
    private String estado;
}
