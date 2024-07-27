/**
 * La classe SudokuController gère les interactions entre le modèle et la vue du
 * Sudoku.
 */
public class SudokuController {

    private final SudokuModel model;
    private final SudokuView view;

    /**
     * Constructeur pour créer un nouveau contrôleur de Sudoku avec un nombre
     * spécifié de cases révélées et un intervalle.
     * 
     * @param cardinal_reveal_number Le nombre minimal de valeurs révélées.
     * @param interval               L'intervalle supplémentaire de valeurs
     *                               révélées.
     */
    public SudokuController(int cardinal_reveal_number, int interval) {
        model = new SudokuModel(this, cardinal_reveal_number, interval);
        view = new SudokuView(model, this);
    }

    /**
     * Constructeur par défaut pour créer un nouveau contrôleur de Sudoku avec au
     * moins 17 cases révélées.
     */
    public SudokuController() {
        model = new SudokuModel(this);
        view = new SudokuView(model, this);
    }

    /**
     * Vérifie si la grille affichée dans la vue est résolue correctement.
     * 
     * @return true si la grille est correctement résolue, false sinon.
     */
    public boolean check_resolved() {
        model.solveBoard();
        return model.isSameBoard(view.getBoard());
    }

    // TODO: Effacer
    public void erasedNumbers(){
        view.erasedNumbers();
    }

    // TODO: Historique
    // public void replaceByLastHistory(){
    // }

    // Getters

    public SudokuView getView() {
        return view;
    }

    public SudokuModel getModel() {
        return model;
    }
}
