package biagioCota.biagio.configuration;

import biagioCota.biagio.entities.userSubclasses.Admin;
import biagioCota.biagio.entities.userSubclasses.BusinessOwner;
import biagioCota.biagio.entities.userSubclasses.Visitor;
import biagioCota.biagio.enums.Nazionalita;
import biagioCota.biagio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            userRepository.save(new Admin("Mario", "Rossi", "mario@example.com", passwordEncoder.encode("pass123"),
                    "Admin di Gargano", "3301234567",
                    "avatar-url",
                    LocalDate.of(2024, 1, 15)));
            userRepository.save(new Admin(
                    "Anna", "Bianchi", "anna@example.com", passwordEncoder.encode("pass123"),
                    "Gestione contenuti", "3309876543",
                    "avatar-url",
                    LocalDate.of(2024, 2, 20)
            ));

            BusinessOwner owner1 = new BusinessOwner(
                    "Giuseppe", "Verdi", "giuseppe.verdi@example.com", passwordEncoder.encode("pass123"),
                    "Ristoratore appassionato di cucina locale", "3312345678",
                    "avatar-url-owner1"
            );
            userRepository.save(owner1);

            BusinessOwner owner2 = new BusinessOwner(
                    "Isabella", "Ferrari", "isabella.ferrari@example.com", passwordEncoder.encode("pass123"),
                    "Gestore di strutture ricettive", "3323456789",
                    "avatar-url-owner2"
            );
            userRepository.save(owner2);

            BusinessOwner owner3 = new BusinessOwner(
                    "Paolo", "Moretti", "paolo.moretti@example.com", passwordEncoder.encode("pass123"),
                    "Imprenditore turistico a Gargano", "3334567890",
                    "avatar-url-owner3"
            );
            userRepository.save(owner3);

            Visitor visitor1 = new Visitor(
                    "Luca", "Conti", "luca.conti@example.com", passwordEncoder.encode("pass123"),
                    "Turista appassionato di gastronomia", "3401234567",
                    "avatar-visitor-1",
                    Nazionalita.IT  // ← adatta all'enum
            );
            userRepository.save(visitor1);

            Visitor visitor2 = new Visitor(
                    "Anna", "Schmidt", "anna.schmidt@example.de", passwordEncoder.encode("pass123"),
                    "Amante di natura e spiagge", "3402345678",
                    "avatar-visitor-2",
                    Nazionalita.DE
            );
            userRepository.save(visitor2);

            Visitor visitor3 = new Visitor(
                    "Marie", "Dupont", "marie.dupont@example.fr", passwordEncoder.encode("pass123"),
                    "Esploratore di destinazioni nuove", "3403456789",
                    "avatar-visitor-3",
                    Nazionalita.FR
            );
            userRepository.save(visitor3);

            Visitor visitor4 = new Visitor(
                    "Carlos", "Rodriguez", "carlos.rodriguez@example.es", passwordEncoder.encode("pass123"),
                    "Cercatore di esperienze autentiche", "3404567890",
                    "avatar-visitor-4",
                    Nazionalita.ES
            );
            userRepository.save(visitor4);

            Visitor visitor5 = new Visitor(
                    "Lisa", "Mueller", "lisa.mueller@example.at", passwordEncoder.encode("pass123"),
                    "Vacanziera in cerca di relax", "3405678901",
                    "avatar-visitor-5",
                    Nazionalita.AT
            );
            userRepository.save(visitor5);

            Visitor visitor6 = new Visitor(
                    "Giovanni", "Bianchi", "giovanni.bianchi@example.com", passwordEncoder.encode("pass123"),
                    "Escursionista e fotografo", "3406789012",
                    "avatar-visitor-6",
                    Nazionalita.IT
            );
            userRepository.save(visitor6);

            Visitor visitor7 = new Visitor(
                    "Sophie", "Martin", "sophie.martin@example.ch", passwordEncoder.encode("pass123"),
                    "Viaggiatrice avventurosa", "3407890123",
                    "avatar-visitor-7",
                    Nazionalita.CH
            );
            userRepository.save(visitor7);

            Visitor visitor8 = new Visitor(
                    "Marco", "Ferrari", "marco.ferrari@example.it", passwordEncoder.encode("pass123"),
                    "Local che scopre il proprio territorio", "3408901234",
                    "avatar-visitor-8",
                    Nazionalita.IT
            );
            userRepository.save(visitor8);

            Visitor visitor9 = new Visitor(
                    "Elena", "Rossi", "elena.rossi@example.com", passwordEncoder.encode("pass123"),
                    "Blogger di viaggi e lifestyle", "3409012345",
                    "avatar-visitor-9",
                    Nazionalita.IT
            );
            userRepository.save(visitor9);

            Visitor visitor10 = new Visitor(
                    "Klaus", "Wagner", "klaus.wagner@example.de", passwordEncoder.encode("pass123"),
                    "Esperto di turismo sostenibile", "3410123456",
                    "avatar-visitor-10",
                    Nazionalita.DE
            );
            userRepository.save(visitor10);
        }
    }
}
