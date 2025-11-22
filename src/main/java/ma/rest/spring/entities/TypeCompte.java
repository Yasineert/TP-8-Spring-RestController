package ma.rest.spring.entities;

/**
 * Enumération des types de comptes bancaires gérés par le service.  Les valeurs
 * {@link #COURANT} et {@link #EPARGNE} représentent respectivement un compte
 * courant et un compte d'épargne.  L'annotation {@code EnumType.STRING} appliquée
 * dans l'entité {@link Compte} permettra de persister ces valeurs sous forme de
 * chaînes de caractères dans la base de données, améliorant la lisibilité des
 * enregistrements.
 */
public enum TypeCompte {
    /**
     * Compte courant classique permettant des dépôts, retraits et virements à tout moment.
     */
    COURANT,

    /**
     * Compte d'épargne conçu pour stocker de l'argent sur une durée plus longue et
     * bénéficier d'intérêts.
     */
    EPARGNE
}