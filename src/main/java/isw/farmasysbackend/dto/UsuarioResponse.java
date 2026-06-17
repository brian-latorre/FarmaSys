package isw.farmasysbackend.dto;

import isw.farmasysbackend.model.Rol;
import isw.farmasysbackend.model.Sede;
import isw.farmasysbackend.model.Usuario;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsuarioResponse {

    private Integer id;
    private String username;
    private String estado;
    private Integer rolId;
    private String rolNombre;
    private Integer sedeId;
    private String sedeNroLocal;
    private String sedeDireccion;

    public static UsuarioResponse fromEntity(Usuario usuario) {
        Rol rol = usuario.getRol();
        Sede sede = usuario.getSede();

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .username(usuario.getUsername())
                .estado(usuario.getEstado())
                .rolId(rol != null ? rol.getId() : null)
                .rolNombre(rol != null ? rol.getNombreRol() : null)
                .sedeId(sede != null ? sede.getId() : null)
                .sedeNroLocal(sede != null ? sede.getNroLocal() : null)
                .sedeDireccion(sede != null ? sede.getDireccion() : null)
                .build();
    }

    public static List<UsuarioResponse> fromEntities(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioResponse::fromEntity)
                .toList();
    }
}
