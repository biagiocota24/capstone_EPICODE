package biagioCota.biagio.entities.strutturaSubclasses;

import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.enums.TipoSpiaggia;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Stabilimento_balneare")
@Getter
@Setter
@NoArgsConstructor
public class StabilimentoBalneare extends Struttura {

    @Column(nullable = false)
    private TipoSpiaggia tipoSpiaggia;

    @Column(nullable = false)
    private double prezzoOmbrellone;

    @Column(nullable = false)
    private Boolean docciaPresente;

    @Column(nullable = false)
    private boolean barPresente;

    @Column(nullable = false)
    private boolean ristorazionePresente;
}
