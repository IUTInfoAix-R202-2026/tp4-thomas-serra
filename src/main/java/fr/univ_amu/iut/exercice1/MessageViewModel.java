package fr.univ_amu.iut.exercice1;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel de l'exercice 1.
 *
 * <p>Le ViewModel est le cœur du pattern MVVM. Il expose l'état de l'interface sous forme de
 * <b>propriétés observables</b> ({@link StringProperty}, etc.) et fait le pont avec le {@link
 * Message modèle}. Point capital : il n'importe AUCUN composant JavaFX d'interface (pas de {@code
 * Label}, pas de {@code TextField}). Il est donc testable avec un simple test JUnit, sans lancer
 * d'application graphique.
 *
 * <p>Ici, le ViewModel :
 *
 * <ul>
 *   <li>expose {@code texte} (lecture/écriture) que la vue liera à un champ de saisie ;
 *   <li>maintient le modèle synchronisé : toute modification de la propriété est répercutée dans le
 *       {@link Message} ;
 *   <li>expose {@code apercu} (lecture seule), une propriété DÉRIVÉE qui se recalcule toute seule.
 * </ul>
 */
public class MessageViewModel {

  private final Message message;

  private final StringProperty texte = new SimpleStringProperty();
  private final StringProperty apercu = new SimpleStringProperty();

  public MessageViewModel(Message message) {
    this.message = message;

    // 1. initialiser texte avec la valeur du modèle
    texte.set(message.getTexte());

    // 2. garder le modèle synchronisé
    texte.addListener((obs, oldVal, newVal) -> message.setTexte(newVal));

    // 3. apercu dérivé
    apercu.bind(Bindings.concat("Aperçu : ", texte));
  }

  public StringProperty texteProperty() {
    return texte;
  }

  public StringProperty apercuProperty() {
    return apercu;
  }
}
