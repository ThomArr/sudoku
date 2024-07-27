/**
 * La classe NumberModel représente le modèle pour un numéro dans le Sudoku.
 * Elle gère la valeur du numéro ainsi que les hypothèses associées.
 */
public class NumberModel {
    private int value;
    private boolean[] hypothesis;
    static boolean upperCase;

    /**
     * Constructeur pour créer un modèle de numéro avec une valeur initiale.
     * 
     * @param value La valeur initiale du numéro.
     */
    public NumberModel(int value) {
        this.value = value;
        hypothesis = new boolean[9];
        for (int i = 0; i < 9; i++) {
            hypothesis[i] = false;
        }
        upperCase = true;
    }

    /**
     * Définit la valeur de la case et réinitialise toutes les hypothèses.
     * 
     * @param value La valeur à définir.
     */
    public void setValue(int value) {
        for (int i = 0; i < 9; i++) {
            hypothesis[i] = false;
        }
        this.value = value;
    }

    /**
     * Supprime la valeur actuelle de la case.
     */
    public void removeValue() {
        setValue(0);
    }

    /**
     * Ajoute une hypothèse pour un numéro donné.
     * 
     * @param i L'indice de l'hypothèse à ajouter (1 à 9).
     */
    public void addHypothesis(int i) {
        this.hypothesis[i - 1] = true;
    }

    /**
     * Supprime une hypothèse pour un numéro donné.
     * 
     * @param i L'indice de l'hypothèse à supprimer (1 à 9).
     */
    public void removeHypothesis(int i) {
        this.hypothesis[i - 1] = false;
    }

    // Getters

    public int getValue() {
        return value;
    }

    public boolean[] getHypothesis() {
        return hypothesis;
    }
}
