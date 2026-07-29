package biagioCota.biagio.services;

import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.entities.Valutazione;
import biagioCota.biagio.entities.userSubclasses.Visitor;
import biagioCota.biagio.payloads.ValutazionePayload;
import biagioCota.biagio.repositories.ValutazioneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ValutazioneService {

    private final ValutazioneRepository valutazioneRepository;
    private final VisitorService visitorService;
    private final StrutturaService strutturaService;

    public ValutazioneService(ValutazioneRepository valutazioneRepository,
                              VisitorService visitorService,
                              StrutturaService strutturaService) {
        this.valutazioneRepository = valutazioneRepository;
        this.visitorService = visitorService;
        this.strutturaService = strutturaService;
    }

    public List<Valutazione> findAll() {
        return valutazioneRepository.findAll();
    }

    public Valutazione findById(UUID id) {
        return valutazioneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Valutazione non trovata con id: " + id));
    }

    public Valutazione save(ValutazionePayload payload) {
        Visitor autore = visitorService.findById(payload.getAutoreId());
        Struttura struttura = strutturaService.findById(payload.getStrutturaId());

        if (valutazioneRepository.existsByAutoreAndStruttura(autore, struttura)) {
            throw new RuntimeException("Hai già valutato questa struttura");
        }

        Valutazione valutazione = new Valutazione();
        valutazione.setAutore(autore);
        valutazione.setStruttura(struttura);
        valutazione.setStelle(payload.getStelle());
        valutazione.setTitolo(payload.getTitolo());
        valutazione.setCommento(payload.getCommento());
        valutazione.setDataCreazione(LocalDateTime.now());
        valutazione.setUtileCount(0);

        return valutazioneRepository.save(valutazione);
    }

    public Valutazione update(UUID id, ValutazionePayload payload) {
        Valutazione esistente = findById(id);
        esistente.setStelle(payload.getStelle());
        esistente.setTitolo(payload.getTitolo());
        esistente.setCommento(payload.getCommento());
        return valutazioneRepository.save(esistente);
    }

    public void delete(UUID id) {
        findById(id);
        valutazioneRepository.deleteById(id);
    }

    public List<Valutazione> findByAutore(Visitor autore) {
        return valutazioneRepository.findByAutore(autore);
    }

    public List<Valutazione> findByAutoreId(UUID autoreId) {
        return valutazioneRepository.findByAutoreId(autoreId);
    }

    public List<Valutazione> findByStruttura(Struttura struttura) {
        return valutazioneRepository.findByStruttura(struttura);
    }

    public List<Valutazione> findByStrutturaId(UUID strutturaId) {
        return valutazioneRepository.findByStrutturaId(strutturaId);
    }

    public List<Valutazione> findByStelle(Integer stelle) {
        return valutazioneRepository.findByStelle(stelle);
    }

    public List<Valutazione> findByStelleMinime(Integer stelle) {
        return valutazioneRepository.findByStelleGreaterThanEqual(stelle);
    }

    public List<Valutazione> findByStrutturaOrdinatePerStelle(UUID strutturaId) {
        return valutazioneRepository.findByStrutturaIdOrderByStelleDesc(strutturaId);
    }

    public List<Valutazione> findByStrutturaOrdinatePerData(UUID strutturaId) {
        return valutazioneRepository.findByStrutturaIdOrderByDataCreazioneDesc(strutturaId);
    }

    public boolean hasValutato(Visitor autore, Struttura struttura) {
        return valutazioneRepository.existsByAutoreAndStruttura(autore, struttura);
    }

    public long countByStruttura(UUID strutturaId) {
        return valutazioneRepository.countByStrutturaId(strutturaId);
    }
}
