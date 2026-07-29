package biagioCota.biagio.entities.strutturaSubclasses;

import biagioCota.biagio.entities.Struttura;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("Hotel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel extends Struttura {

    @Column(nullable = false)
    private int stelle;

    @Column(nullable = true)
    private double prezzoMedioNotte;

    @Column(nullable = false)
    private boolean wifi;

    @Column(nullable = false)
    private boolean parcheggioPrivato;

    @Column(nullable = false)
    private boolean piscina;

    @Column(nullable = false)
    private boolean animaliAmmessi;

}
