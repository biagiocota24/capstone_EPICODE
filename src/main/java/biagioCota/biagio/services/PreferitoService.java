package biagioCota.biagio.services;

import biagioCota.biagio.entities.Preferito;
import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.entities.userSubclasses.Visitor;
import biagioCota.biagio.payloads.PreferitoPayload;
import biagioCota.biagio.repositories.PreferitoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PreferitoService {

    private final PreferitoRepository preferitoRepository;
    private final VisitorService visitorService;
    private final StrutturaService strutturaService;

    public PreferitoService(PreferitoRepository preferitoRepository,
                            VisitorService visitorService,
                            StrutturaService strutturaService) {
        this.preferitoRepository = preferitoRepository;
        this.visitorService = visitorService;
        this.strutturaService = strutturaService;
    }

    public List<Preferito> findAll() {
        return preferitoRepository.findAll();
    }

    public Preferito findById(UUID id) {
        return preferitoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Preferito non trovato con id: " + id));
    }

    public Preferito save(PreferitoPayload payload) {
        Visitor autore = visitorService.findById(payload.getAutoreId());
        Struttura struttura = strutturaService.findById(payload.getStrutturaId());

        if (preferitoRepository.existsByAutoreIdAndStrutturaId(autore.getId(), struttura.getId())) {
            throw new RuntimeException("Struttura già nei preferiti");
        }

        Preferito preferito = new Preferito();
        preferito.setAutore(autore);
        preferito.setStruttura(struttura);
        preferito.setNota(payload.getNota());
        preferito.setVisitato(payload.getVisitato());
        preferito.setDataSalvataggio(LocalDateTime.now());

        return preferitoRepository.save(preferito);
    }

    public Preferito update(UUID id, PreferitoPayload payload) {
        Preferito esistente = findById(id);
        esistente.setNota(payload.getNota());
        esistente.setVisitato(payload.getVisitato());
        return preferitoRepository.save(esistente);
    }

    public void delete(UUID id) {
        findById(id);
        preferitoRepository.deleteById(id);
    }

    public List<Preferito> findByAutore(Visitor autore) {
        return preferitoRepository.findByAutore(autore);
    }

    public List<Preferito> findByAutoreId(UUID autoreId) {
        return preferitoRepository.findByAutoreId(autoreId);
    }

    public List<Preferito> findByStruttura(Struttura struttura) {
        return preferitoRepository.findByStruttura(struttura);
    }

    public List<Preferito> findByStrutturaId(UUID strutturaId) {
        return preferitoRepository.findByStrutturaId(strutturaId);
    }

    public List<Preferito> findByAutoreEVisitato(UUID autoreId, Boolean visitato) {
        return preferitoRepository.findByAutoreIdAndVisitato(autoreId, visitato);
    }

    public boolean isPreferito(UUID autoreId, UUID strutturaId) {
        return preferitoRepository.existsByAutoreIdAndStrutturaId(autoreId, strutturaId);
    }

    public long countByAutore(UUID autoreId) {
        return preferitoRepository.countByAutoreId(autoreId);
    }
}
