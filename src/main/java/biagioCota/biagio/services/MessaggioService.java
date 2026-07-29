package biagioCota.biagio.services;

import biagioCota.biagio.entities.Citta;
import biagioCota.biagio.entities.Messaggio;
import biagioCota.biagio.entities.User;
import biagioCota.biagio.enums.StatoMessaggio;
import biagioCota.biagio.payloads.MessaggioPayload;
import biagioCota.biagio.repositories.MessaggioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MessaggioService {

    private final MessaggioRepository messaggioRepository;
    private final UserService userService;
    private final CittaService cittaService;

    public MessaggioService(MessaggioRepository messaggioRepository,
                            UserService userService,
                            CittaService cittaService) {
        this.messaggioRepository = messaggioRepository;
        this.userService = userService;
        this.cittaService = cittaService;
    }

    public List<Messaggio> findAll() {
        return messaggioRepository.findAll();
    }

    public Messaggio findById(Long id) {
        return messaggioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Messaggio non trovato con id: " + id));
    }

    public Messaggio save(MessaggioPayload payload) {
        User autore = userService.findById(payload.getAutoreId());
        Citta citta = cittaService.findById(payload.getCittaId());

        Messaggio messaggio = new Messaggio();
        messaggio.setAutore(autore);
        messaggio.setCitta(citta);
        messaggio.setContenuto(payload.getContenuto());
        messaggio.setFotoUrl(payload.getFotoUrl());
        messaggio.setDataCreazione(LocalDateTime.now());
        messaggio.setContatoreMiPiace(0);
        messaggio.setStato(StatoMessaggio.PUBBLICATO);

        if (payload.getRispostaAId() != null) {
            Messaggio padre = findById(payload.getRispostaAId());
            messaggio.setRispostaA(padre);
        }

        return messaggioRepository.save(messaggio);
    }

    public Messaggio update(Long id, MessaggioPayload payload) {
        Messaggio esistente = findById(id);
        esistente.setContenuto(payload.getContenuto());
        esistente.setFotoUrl(payload.getFotoUrl());
        esistente.setDataModifica(LocalDateTime.now());
        return messaggioRepository.save(esistente);
    }

    public void delete(Long id) {
        findById(id);
        messaggioRepository.deleteById(id);
    }

    public List<Messaggio> findByCitta(Citta citta) {
        return messaggioRepository.findByCitta(citta);
    }

    public List<Messaggio> findByCittaId(UUID cittaId) {
        return messaggioRepository.findByCittaId(cittaId);
    }

    public List<Messaggio> findByAutore(User autore) {
        return messaggioRepository.findByAutore(autore);
    }

    public List<Messaggio> findByAutoreId(UUID autoreId) {
        return messaggioRepository.findByAutoreId(autoreId);
    }

    public List<Messaggio> findByStato(StatoMessaggio stato) {
        return messaggioRepository.findByStato(stato);
    }

    public List<Messaggio> findByCittaEStato(UUID cittaId, StatoMessaggio stato) {
        return messaggioRepository.findByCittaIdAndStato(cittaId, stato);
    }

    public List<Messaggio> findPrincipaliDiCitta(UUID cittaId) {
        return messaggioRepository.findByRispostaAIsNullAndCittaId(cittaId);
    }

    public List<Messaggio> findRisposteAId(Long messaggioId) {
        return messaggioRepository.findByRispostaAId(messaggioId);
    }

    public List<Messaggio> findRecenti(LocalDateTime from) {
        return messaggioRepository.findByDataCreazioneAfter(from);
    }

    public List<Messaggio> findByCittaOrdinati(UUID cittaId) {
        return messaggioRepository.findByCittaIdOrderByDataCreazioneDesc(cittaId);
    }

    public long countByCitta(UUID cittaId) {
        return messaggioRepository.countByCittaId(cittaId);
    }
}
