package biagioCota.biagio.entities.strutturaSubclasses;

import biagioCota.biagio.entities.RangePrezzo;
import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.enums.TipologiaCucina;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Ristorante")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ristorante extends Struttura {

    @Column(nullable = true)
    private String specialita;

    @Column(nullable = false)
    private RangePrezzo fasciaPrezzoMedio;

    @Column(nullable = false)
    private TipologiaCucina tipologiaCucina;

    @Column(nullable = false)
    private Boolean prenotazioniOnline;

    @Column(nullable = false)
    private Boolean delivery;
}
