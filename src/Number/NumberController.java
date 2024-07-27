/**
 * La classe NumberController gère la logique entre le modèle et la vue.
 * Elle permet de manipuler les valeurs et les hypothèses des numéros du Sudoku.
 */
public class NumberController {
    NumberModel model;
    NumberView view;

    /**
     * Constructeur pour créer un contrôleur de numéro avec une valeur initiale.
     * 
     * @param i La valeur initiale du numéro.
     */
    NumberController(int i) {
        model = new NumberModel(i);
        view = new NumberView(this, model);
    }

    /**
     * Supprime la valeur actuelle de la case et met à jour la vue.
     */
    public void removeValue() {
        model.removeValue();
        view.removeValue();
    }

    /**
     * Définit la valeur de la case et met à jour la vue.
     * 
     * @param i La nouvelle valeur à définir.
     */
    public void setValue(int i) {
        model.setValue(i);
        view.setValue(i);
    }

    /**
     * Ajoute une hypothèse pour un numéro donné et met à jour la vue.
     * 
     * @param i L'hypothèse à ajouter (1 à 9).
     */
    public void addHypothesis(int i) {
        model.addHypothesis(i);
        view.addHypothesis(i);
    }

    /**
     * Supprime une hypothèse pour un numéro donné et met à jour la vue.
     * 
     * @param i L'hypothèse à supprimer (1 à 9).
     */
    public void removeHypothesis(int i) {
        model.removeHypothesis(i);
        view.removeHypothesis(i);
    }

    // Getter
    public NumberView getView() {
        return view;
    }
}
