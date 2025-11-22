package ma.rest.spring;

import ma.rest.spring.entities.Compte;
import ma.rest.spring.entities.TypeCompte;
import ma.rest.spring.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * Point d'entrée principal de l'application Spring Boot pour la gestion des comptes bancaires.
 *
 * La classe est annotée avec {@code @SpringBootApplication}, ce qui active la configuration
 * automatique de Spring Boot, la recherche de composants et la configuration du contexte
 * d'application.  Elle définit également un {@link CommandLineRunner} utilisé pour
 * pré‑initialiser quelques comptes d'exemple dans la base de données en mémoire H2 au
 * démarrage de l'application.
 */
@SpringBootApplication
public class MsBanqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBanqueApplication.class, args);
    }

    /**
     * Initialise la base de données H2 avec quelques comptes factices dès le démarrage
     * de l'application.  Ce bean est exécuté automatiquement par Spring Boot.
     *
     * @param compteRepository le dépôt JPA pour persister et récupérer des entités Compte
     * @return un runner qui enregistre et affiche quelques comptes d'exemple
     */
    @Bean
    CommandLineRunner start(CompteRepository compteRepository) {
        return args -> {
            // Création de quelques comptes aléatoires pour initialiser la base
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.COURANT));
            compteRepository.save(new Compte(null, Math.random() * 9000, new Date(), TypeCompte.EPARGNE));

            // Affiche les comptes dans la console pour vérification
            compteRepository.findAll().forEach(compte -> {
                System.out.println(compte);
            });
        };
    }
}