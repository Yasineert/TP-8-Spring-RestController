package ma.rest.spring.repositories;

import ma.rest.spring.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dépôt JPA pour l'entité {@link Compte}.  Étendre {@link JpaRepository}
 * permet de bénéficier de méthodes de persistance prêtes à l'emploi telles que
 * {@code findAll()}, {@code save()}, {@code deleteById()}, etc., sans avoir
 * à écrire de code d'implémentation.  L'annotation {@link Repository}
 * marque cette interface comme composant Spring injectable.
 */
@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    // aucune méthode supplémentaire n'est nécessaire pour les opérations CRUD de base
}