package ma.rest.spring.web;

import ma.rest.spring.entities.Compte;
import ma.rest.spring.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Contrôleur REST exposant des endpoints pour gérer les comptes bancaires.  Les
 * méthodes de ce contrôleur répondent aux opérations CRUD classiques (lecture,
 * création, mise à jour et suppression).  Chaque endpoint spécifie explicitement
 * les types MIME supportés pour la sérialisation/desérialisation afin d'offrir
 * aux clients la possibilité d'utiliser JSON ou XML selon leurs besoins.
 */
@RestController
@RequestMapping("/banque")
public class CompteController {

    @Autowired
    private CompteRepository compteRepository;

    /**
     * Récupère l'ensemble des comptes disponibles dans la base.
     *
     * @return une liste de comptes sérialisée en JSON ou XML selon l'en-tête
     *         "Accept" de la requête
     */
    @GetMapping(value = "/comptes", produces = {"application/json", "application/xml"})
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    /**
     * Récupère un compte spécifique à partir de son identifiant.
     *
     * @param id l'identifiant du compte à rechercher
     * @return la réponse HTTP contenant le compte en cas de succès, ou un
     *         statut 404 si le compte n'existe pas
     */
    @GetMapping(value = "/comptes/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
        return compteRepository.findById(id)
                .map(compte -> ResponseEntity.ok().body(compte))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouveau compte à partir de l'objet fourni dans le corps de la
     * requête.  Le compte est sérialisé en JSON ou XML en fonction de l'en-tête
     * "Content-Type".
     *
     * @param compte l'objet compte à persister
     * @return la réponse HTTP contenant le compte sauvegardé ainsi qu'un en-tête
     *         "Location" pointant vers la nouvelle ressource
     */
    @PostMapping(value = "/comptes", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
        Compte savedCompte = compteRepository.save(compte);
        // Construire l'URI de la ressource créée
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCompte.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedCompte);
    }

    /**
     * Met à jour un compte existant.  Seuls le solde, la date de création
     * et le type de compte sont mis à jour.
     *
     * @param id            l'identifiant du compte à mettre à jour
     * @param compteDetails le compte contenant les nouvelles valeurs
     * @return la réponse HTTP avec le compte mis à jour en cas de succès, ou
     *         un statut 404 si le compte n'existe pas
     */
    @PutMapping(value = "/comptes/{id}", consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compteDetails) {
        return compteRepository.findById(id)
                .map(compte -> {
                    compte.setSolde(compteDetails.getSolde());
                    compte.setDateCreation(compteDetails.getDateCreation());
                    compte.setType(compteDetails.getType());
                    Compte updatedCompte = compteRepository.save(compte);
                    return ResponseEntity.ok().body(updatedCompte);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Supprime un compte existant.
     *
     * @param id l'identifiant du compte à supprimer
     * @return une réponse HTTP 200 en cas de réussite, ou 404 si le compte
     *         n'existe pas
     */
    @DeleteMapping("/comptes/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        return compteRepository.findById(id)
                .map(compte -> {
                    compteRepository.delete(compte);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}