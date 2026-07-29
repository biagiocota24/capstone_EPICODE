package biagioCota.biagio.entities.strutturaSubclasses;

import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.enums.TipoMerce;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("Negozio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Negozio extends Struttura {

    @Column(nullable = false)
    private List<TipoMerce> tipoMerce;

    @Column(nullable = false)
    private Boolean spedizioni;

}
