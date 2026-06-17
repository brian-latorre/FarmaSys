package isw.farmasysbackend.service;

import isw.farmasysbackend.model.Cliente;
import isw.farmasysbackend.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }
}
