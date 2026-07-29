package biagioCota.biagio.entities.strutturaSubclasses;

import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.enums.TipoAttrazione;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Attrazione principale")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttrazionePrincipale extends Struttura {

    @Column(nullable = false)
    private TipoAttrazione tipoAttrazione;

    @Column(nullable = true)
    private double bigliettoEntrata;
}
