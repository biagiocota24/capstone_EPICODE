package biagioCota.biagio.services;

import biagioCota.biagio.entities.Commento;
import biagioCota.biagio.entities.PostCommunity;
import biagioCota.biagio.entities.userSubclasses.Visitor;
import biagioCota.biagio.payloads.CommentoPayload;
import biagioCota.biagio.repositories.CommentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CommentoService {

    private final CommentoRepository commentoRepository;
    private final VisitorService visitorService;
    private final PostCommunityService postCommunityService;

    public CommentoService(CommentoRepository commentoRepository,
                           VisitorService visitorService,
                           PostCommunityService postCommunityService) {
        this.commentoRepository = commentoRepository;
        this.visitorService = visitorService;
        this.postCommunityService = postCommunityService;
    }

    public List<Commento> findAll() {
        return commentoRepository.findAll();
    }

    public Commento findById(UUID id) {
        return commentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commento non trovato con id: " + id));
    }

    public Commento save(CommentoPayload payload) {
        Visitor autore = visitorService.findById(payload.getAutoreId());
        PostCommunity post = postCommunityService.findById(payload.getPostId());

        Commento commento = new Commento();
        commento.setAutore(autore);
        commento.setPost(post);
        commento.setContenuto(payload.getContenuto());
        commento.setDataCreazione(LocalDateTime.now());
        commento.setMiPiace(0);

        return commentoRepository.save(commento);
    }

    public Commento update(UUID id, CommentoPayload payload) {
        Commento esistente = findById(id);
        esistente.setContenuto(payload.getContenuto());
        return commentoRepository.save(esistente);
    }

    public void delete(UUID id) {
        findById(id);
        commentoRepository.deleteById(id);
    }

    public List<Commento> findByAutore(Visitor autore) {
        return commentoRepository.findByAutore(autore);
    }

    public List<Commento> findByAutoreId(UUID autoreId) {
        return commentoRepository.findByAutoreId(autoreId);
    }

    public List<Commento> findByPost(PostCommunity post) {
        return commentoRepository.findByPost(post);
    }

    public List<Commento> findByPostId(Long postId) {
        return commentoRepository.findByPostId(postId);
    }

    public List<Commento> findByPostIdOrdinati(Long postId) {
        return commentoRepository.findByPostIdOrderByDataCreazioneDesc(postId);
    }

    public List<Commento> findPopulari(Integer minMiPiace) {
        return commentoRepository.findByMiPiaceGreaterThan(minMiPiace);
    }

    public long countByPost(Long postId) {
        return commentoRepository.countByPostId(postId);
    }
}
