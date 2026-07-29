package biagioCota.biagio.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class RangePrezzo {
    @Column(name = "prezzo_minimo", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoMin;

    @Column(name = "prezzo_massimo", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoMax;

    // ========== CONSTRUCTORS ==========

    public RangePrezzo() {
    }

    public RangePrezzo(BigDecimal prezzoMin, BigDecimal prezzoMax) {
        if (prezzoMin.compareTo(prezzoMax) > 0) {
            throw new IllegalArgumentException("Prezzo minimo non può essere > prezzo massimo");
        }
        this.prezzoMin = prezzoMin;
        this.prezzoMax = prezzoMax;
    }
}
