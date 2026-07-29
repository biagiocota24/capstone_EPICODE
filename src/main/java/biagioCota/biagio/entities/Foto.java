package biagioCota.biagio.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "struttura_id", nullable = false)
    private Struttura struttura;

    @Column(nullable = false)
    private String urlFoto;  // URL Cloudinary

    private Integer posizione;  // Per ordinamento

    @Column(name = "data_caricamento")
    private LocalDateTime dataCa​ricamento = LocalDateTime.now();

    @Column(name = "caricata_da_user_id")
    private UUID caricataDaUserId;  // Chi ha caricato

    private Boolean visibile = true;  // Soft delete / nascondi

    private Integer numeroVisite = 0;  // Analytics
}
