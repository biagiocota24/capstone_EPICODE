package biagioCota.biagio.services;

import biagioCota.biagio.entities.Citta;
import biagioCota.biagio.entities.PostCommunity;
import biagioCota.biagio.entities.User;
import biagioCota.biagio.payloads.PostCommunityPayload;
import biagioCota.biagio.repositories.PostCommunityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostCommunityService {

    private final PostCommunityRepository postCommunityRepository;
    private final UserService userService;
    private final CittaService cittaService;

    public PostCommunityService(PostCommunityRepository postCommunityRepository,
                                UserService userService,
                                CittaService cittaService) {
        this.postCommunityRepository = postCommunityRepository;
        this.userService = userService;
        this.cittaService = cittaService;
    }

    public List<PostCommunity> findAll() {
        return postCommunityRepository.findAll();
    }

    public PostCommunity findById(Long id) {
        return postCommunityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post non trovato con id: " + id));
    }

    public PostCommunity save(PostCommunityPayload payload) {
        User autore = userService.findById(payload.getAutoreId());
        Citta citta = cittaService.findById(payload.getCittaId());

        PostCommunity post = new PostCommunity();
        post.setAutore(autore);
        post.setCitta(citta);
        post.setTitolo(payload.getTitolo());
        post.setContenuto(payload.getContenuto());
        post.setDataCreazione(LocalDateTime.now());

        return postCommunityRepository.save(post);
    }

    public PostCommunity update(Long id, PostCommunityPayload payload) {
        PostCommunity esistente = findById(id);
        esistente.setTitolo(payload.getTitolo());
        esistente.setContenuto(payload.getContenuto());
        return postCommunityRepository.save(esistente);
    }

    public void delete(Long id) {
        findById(id);
        postCommunityRepository.deleteById(id);
    }

    public List<PostCommunity> findByCitta(Citta citta) {
        return postCommunityRepository.findByCitta(citta);
    }

    public List<PostCommunity> findByCittaId(UUID cittaId) {
        return postCommunityRepository.findByCittaId(cittaId);
    }

    public List<PostCommunity> findByAutore(User autore) {
        return postCommunityRepository.findByAutore(autore);
    }

    public List<PostCommunity> findByAutoreId(UUID autoreId) {
        return postCommunityRepository.findByAutoreId(autoreId);
    }

    public List<PostCommunity> searchByTitolo(String titolo) {
        return postCommunityRepository.findByTitoloContainingIgnoreCase(titolo);
    }

    public List<PostCommunity> searchByContenuto(String parola) {
        return postCommunityRepository.findByContenutoContainingIgnoreCase(parola);
    }

    public List<PostCommunity> findRecenti(LocalDateTime from) {
        return postCommunityRepository.findByDataCreazioneAfter(from);
    }

    public List<PostCommunity> findFra(LocalDateTime from, LocalDateTime to) {
        return postCommunityRepository.findByDataCreazioneBetween(from, to);
    }

    public List<PostCommunity> findByCittaOrdinati(UUID cittaId) {
        return postCommunityRepository.findByCittaIdOrderByDataCreazioneDesc(cittaId);
    }

    public long countByCitta(UUID cittaId) {
        return postCommunityRepository.countByCittaId(cittaId);
    }

    public long countByAutore(UUID autoreId) {
        return postCommunityRepository.countByAutoreId(autoreId);
    }
}
