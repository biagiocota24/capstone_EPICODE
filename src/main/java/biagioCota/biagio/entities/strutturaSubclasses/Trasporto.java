package biagioCota.biagio.entities.strutturaSubclasses;

import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.enums.TipoTrasporto;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Trasporto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Trasporto extends Struttura {

    @Column(nullable = false)
    private TipoTrasporto tipoTrasporto;

    @Column(nullable = false)
    private Boolean pagamentoDigitale;

}
