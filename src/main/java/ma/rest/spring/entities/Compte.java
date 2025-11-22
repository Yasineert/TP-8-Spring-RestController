package ma.rest.spring.entities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Représente un compte bancaire dans le système. Cette classe est une entité JPA
 * persistée dans une table de base de données.  Les annotations Lombok
 * {@link Data}, {@link NoArgsConstructor} et {@link AllArgsConstructor} génèrent
 * automatiquement les getters, setters, constructeurs et méthodes utilitaires.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "compte")
public class Compte {

    /**
     * Identifiant unique du compte. Généré automatiquement par la base de données.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Solde actuel du compte.
     */
    private double solde;

    /**
     * Date de création du compte.  L'annotation {@code @Temporal} précise le type
     * de précision pour la valeur de date.
     */
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    /**
     * Type du compte, exprimé sous forme d'énumération.  La valeur est stockée
     * dans la base en tant que chaîne via {@code EnumType.STRING} pour une
     * meilleure lisibilité.
     */
    @Enumerated(EnumType.STRING)
    private TypeCompte type;
}