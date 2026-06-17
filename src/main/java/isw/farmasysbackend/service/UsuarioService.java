package isw.farmasysbackend.service;

import isw.farmasysbackend.dto.UsuarioResponse;
import isw.farmasysbackend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<UsuarioResponse> getUsuarios() {
        return UsuarioResponse.fromEntities(usuarioRepository.findAll());
    }
}
