package biagioCota.biagio.entities.strutturaSubclasses;


import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.enums.TipoServizio;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Servizio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servizio extends Struttura {

    @Column(nullable = false)
    private TipoServizio tipoServizio;

    @Column(nullable = false)
    private Boolean h24;


}
