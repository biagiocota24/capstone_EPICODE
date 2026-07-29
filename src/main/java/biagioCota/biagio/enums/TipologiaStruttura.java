package biagioCota.biagio.enums;

public enum TipologiaStruttura {

    SPIAGGIA("Spiaggia", "Spiaggia e zona balneare"),
    RISTORANTE("Ristorante", "Ristorante e strutture ristorative"),
    HOTEL("Hotel", "Albergo e struttura ricettiva"),
    TRASPORTO("Trasporto", "Servizio di trasporto e noleggio"),
    NEGOZIO("Negozio", "Negozio e attività commerciale"),
    MUSEO("Museo", "Museo e luogo culturale"),
    SPIAGGIA_ATTREZZATA("Spiaggia Attrezzata", "Spiaggia con servizi"),
    AREA_NATURALE("Area Naturale", "Parco, riserva naturale, sentiero"),
    ATTIVITA_ACQUA("Attività Acqua", "Centro diving, windsurf, sport acquatici"),
    BAR_CAFFE("Bar e Caffè", "Bar, caffetteria, gelateria"),
    AGRITURISMO("Agriturismo", "Struttura agraria con ristorazione");

    private final String label;
    private final String descrizione;

    TipologiaStruttura(String label, String descrizione) {
        this.label = label;
        this.descrizione = descrizione;
    }

    public String getLabel() {
        return label;
    }

    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Restituisce l'enum dal label (es. "Spiaggia" → SPIAGGIA)
     */
    public static TipologiaStruttura fromLabel(String label) {
        for (TipologiaStruttura tipo : TipologiaStruttura.values()) {
            if (tipo.label.equalsIgnoreCase(label)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipologia non trovata: " + label);
    }
}
