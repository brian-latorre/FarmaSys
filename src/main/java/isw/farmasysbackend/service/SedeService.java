package isw.farmasysbackend.service;

import isw.farmasysbackend.model.Sede;
import isw.farmasysbackend.repository.SedeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SedeService {

    private final SedeRepository sedeRepository;

    @Transactional(readOnly = true)
    public List<Sede> getSedes() {
        return sedeRepository.findAll();
    }
}
