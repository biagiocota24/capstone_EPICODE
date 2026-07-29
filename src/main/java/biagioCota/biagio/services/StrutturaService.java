package biagioCota.biagio.services;

import biagioCota.biagio.entities.Citta;
import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.entities.userSubclasses.BusinessOwner;
import biagioCota.biagio.enums.TipologiaStruttura;
import biagioCota.biagio.repositories.StrutturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StrutturaService {

    private final StrutturaRepository strutturaRepository;

    public StrutturaService(StrutturaRepository strutturaRepository) {
        this.strutturaRepository = strutturaRepository;
    }

    public List<Struttura> findAll() {
        return strutturaRepository.findAll();
    }

    public Struttura findById(UUID id) {
        return strutturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Struttura non trovata con id: " + id));
    }

    public Struttura save(Struttura struttura) {
        return strutturaRepository.save(struttura);
    }

    public void delete(UUID id) {
        findById(id);
        strutturaRepository.deleteById(id);
    }

    public List<Struttura> findByTipologia(TipologiaStruttura tipologia) {
        return strutturaRepository.findByTipologia(tipologia);
    }

    public List<Struttura> findByCitta(Citta citta) {
        return strutturaRepository.findByCittà(citta);
    }

    public List<Struttura> findByCittaId(UUID cittaId) {
        return strutturaRepository.findByCittàId(cittaId);
    }

    public List<Struttura> findByTipologiaAndCitta(TipologiaStruttura tipologia, Citta citta) {
        return strutturaRepository.findByTipologiaAndCittà(tipologia, citta);
    }

    public List<Struttura> searchByName(String name) {
        return strutturaRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Struttura> findAccessibili(Boolean accessoDisabili) {
        return strutturaRepository.findByAccessoDisabili(accessoDisabili);
    }

    public List<Struttura> findByBusinessOwner(BusinessOwner businessOwner) {
        return strutturaRepository.findByBusinessOwner(businessOwner);
    }

    public List<Struttura> findByBusinessOwnerId(UUID businessOwnerId) {
        return strutturaRepository.findByBusinessOwnerId(businessOwnerId);
    }

    public boolean existsByNameAndCitta(String name, Citta citta) {
        return strutturaRepository.existsByNameAndCittà(name, citta);
    }
}
