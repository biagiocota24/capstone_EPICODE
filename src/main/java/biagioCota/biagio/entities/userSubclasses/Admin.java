package biagioCota.biagio.entities.userSubclasses;

import biagioCota.biagio.entities.LogAzione;
import biagioCota.biagio.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {

    @Column(nullable = true)
    private LocalDate dataAssunsione;

    @OneToMany(mappedBy = "admin")
    private List<LogAzione> azioniEseguite;

    public Admin(String name, String surname, String email, String password, String biografy, String telephone, String avatar, LocalDate dataAssunsione) {
        super(name, surname, email, password, biografy, telephone, avatar);
        this.dataAssunsione = dataAssunsione;
        this.azioniEseguite = new ArrayList<>();
    }
}
