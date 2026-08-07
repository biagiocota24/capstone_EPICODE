package biagioCota.biagio.payloads;

import biagioCota.biagio.entities.Struttura;
import biagioCota.biagio.enums.TipologiaStruttura;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StrutturaResponse {

    private UUID id;
    private String name;
    private String descrizione;
    private TipologiaStruttura tipologia;
    private UUID cittaId;
    private String cittaNome;
    private String via;
    private String numeroCivico;
    private String telefono;
    private String email;
    private String sitoWebURL;
    private Boolean accessoDisabili;
    private List<String> fotoUrls;

    public static StrutturaResponse fromEntity(Struttura s) {
        StrutturaResponse r = new StrutturaResponse();
        r.id = s.getId();
        r.name = s.getName();
        r.descrizione = s.getDescrizione();
        r.tipologia = s.getTipologia();
        r.telefono = s.getTelefono();
        r.email = s.getEmail();
        r.sitoWebURL = s.getSitoWebURL();
        r.accessoDisabili = s.getAccessoDisabili();

        if (s.getCittà() != null) {
            r.cittaId = s.getCittà().getId();
            r.cittaNome = s.getCittà().getName();
        }

        if (s.getIndirizzo() != null) {
            r.via = s.getIndirizzo().getVia();
            r.numeroCivico = s.getIndirizzo().getNumeroCivico();
        }

        r.fotoUrls = s.getRaccoltaFoto().stream()
                .filter(f -> Boolean.TRUE.equals(f.getVisibile()))
                .map(f -> f.getUrlFoto())
                .toList();

        return r;
    }
}
