import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

/**
 * La classe Sudoku permet de créer, résoudre et manipuler des grilles de
 * Sudoku.
 */
public class SudokuModel {
    private int[][] board;
    private boolean solve;

    private SudokuController controller;

    /**
     * Génère une grille avec entre cardinal_reveal_number et
     * cardinal_reveal_number+interval valeurs connues.
     * 
     * @param cardinal_reveal_number Le nombre minimal de valeurs révélées.
     * @param interval               L'intervalle supplémentaire de valeurs
     *                               révélées.
     */
    public SudokuModel(SudokuController controller, int cardinal_reveal_number, int interval) {
        this.controller = controller;

        boolean sucess = false;
        while (!sucess) {
            generateNewBoard();
            sucess = hideNumbersUntilThereAreNLeft(cardinal_reveal_number) <= interval;
        }
    }

    /**
     * Génère une grille avec au moins 17 valeurs connues.
     */
    public SudokuModel(SudokuController controller) {
        this.controller = controller;
        this.generateNewBoard();
        this.hideNumbersUntilThereAreNLeft(17);
    }

    /**
     * Génère une grille à partir d'un tableau et la résout.
     * 
     * @param board Le tableau de la grille initiale.
     */
    public SudokuModel(SudokuController controller, int[][] board) {
        this.controller = controller;
        this.board = board;
        printBoard();
        System.out.println("Nombre de solutions : " + getNombreSolutionBoard());
        solveBoard();
        printBoard();
    }

    /**
     * Affiche la grille de Sudoku dans la console.
     */
    public final void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.board[i][j] != 0) {
                    System.out.print(this.board[i][j] + " ");
                } else {
                    System.out.print("  ");
                }
                if (j == 2 || j == 5) {
                    System.out.print("| ");
                }
            }
            if (i == 2 || i == 5) {
                System.out.println("\n---------------------");
            } else {
                System.out.println();
            }
        }
        System.out.println("=====================");
    }

    /**
     * Mélange une ArrayList.
     * 
     * @param <E>  Le type des éléments de la liste.
     * @param list La liste à mélanger.
     * @return La liste mélangée.
     */
    public static <E> ArrayList<E> shuffle(ArrayList<E> list) {
        Random random = new Random();
        int length = list.size();
        int index;
        E save;
        for (int x = 0; x < length; x++) {
            index = random.nextInt(length);
            save = list.get(x);
            list.set(x, list.get(index));
            list.set(index, save);
        }
        return list;
    }

    /**
     * Complète toutes les cases vides avec une solution valide à partir de la case
     * (i, j) dans une grille de Sudoku valide.
     * Si la grille n'admet pas de solution, ne fait rien.
     * 
     * @param i La ligne de départ.
     * @param j La colonne de départ.
     */
    private void completeAllCaseFromThisCase(int i, int j) {
        if (board[i][j] != 0) {
            this.completeAllCaseFromNextCase(i, j);
        } else {
            ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            list = shuffle(list);

            for (int a : list) {
                if (checkCanPlaceValAtThisCoord(i, j, a)) {
                    board[i][j] = a;
                    this.completeAllCaseFromNextCase(i, j);
                    if (!this.solve) {
                        this.board[i][j] = 0;
                    }
                }
            }
        }
    }

    /**
     * Passe à la case suivante pour compléter la grille.
     * Fonction auxiliaire de completeAllCaseFromThisCase.
     * 
     * @param i La ligne actuelle.
     * @param j La colonne actuelle.
     */
    private void completeAllCaseFromNextCase(int i, int j) {
        if (j == 8) {
            if (i == 8) {
                this.solve = true;
            } else {
                this.completeAllCaseFromThisCase(i + 1, 0);
            }
        } else {
            this.completeAllCaseFromThisCase(i, j + 1);
        }
    }

    /**
     * Génère une grille complète.
     */
    private void generateNewBoard() {
        solve = false;
        board = new int[9][9];
        completeAllCaseFromThisCase(0, 0);
    }

    /**
     * Résout une grille incomplète si elle admet au moins une solution.
     * S'il y a plusieurs solutions, il en choisit une au hasard.
     */
    public final void solveBoard() {
        solve = false;
        completeAllCaseFromThisCase(0, 0);
    }

    /**
     * Calcule le nombre de solutions de la grille à partir de la ligne i et la
     * colonne j.
     * 
     * @param i             La ligne de départ.
     * @param j             La colonne de départ.
     * @param nbr_solutions Le nombre de solutions déjà calculé.
     * @return Le nombre de solutions pour cette grille à partir des coordonnées
     *         indiquées.
     */
    private int nbrSolutionsFromThisCase(int i, int j, int nbr_solutions) {
        if (board[i][j] != 0) {
            return nbrSolutionFromNextCase(i, j, nbr_solutions);
        } else {
            for (int a = 1; a < 10; a++) {
                if (checkCanPlaceValAtThisCoord(i, j, a)) {
                    board[i][j] = a;
                    nbr_solutions += nbrSolutionFromNextCase(i, j, 0);
                    board[i][j] = 0;
                }
            }
            return nbr_solutions;
        }
    }

    /**
     * Passe à la case suivante pour calculer le nombre de solutions de la grille.
     * Fonction auxiliaire de nbrSolutionsFromThisCase.
     * 
     * @param i             La ligne actuelle.
     * @param j             La colonne actuelle.
     * @param nbr_solutions Le nombre de solutions calculé jusqu'à présent.
     * @return Le nombre de solutions pour la grille.
     */
    private int nbrSolutionFromNextCase(int i, int j, int nbr_solutions) {
        if (j + 1 == 9) {
            if (i + 1 == 9) {
                return nbr_solutions + 1;
            } else {
                return this.nbrSolutionsFromThisCase(i + 1, 0, nbr_solutions);
            }
        } else {
            return this.nbrSolutionsFromThisCase(i, j + 1, nbr_solutions);
        }
    }

    /**
     * Calcule le nombre de solutions qu'une grille a.
     * 
     * @return Le nombre de solutions de la grille.
     */
    private int getNombreSolutionBoard() {
        return nbrSolutionsFromThisCase(0, 0, 0);
    }

    /**
     * Vérifie si on peut poser le nombre val aux coordonnées i, j de la grille.
     * 
     * @param i   La ligne.
     * @param j   La colonne.
     * @param val La valeur à vérifier.
     * @return true si la valeur peut être posée, false sinon.
     */
    private boolean checkCanPlaceValAtThisCoord(int i, int j, int val) {
        if (val < 1 || val > 9) {
            return false;
        }
        // verifie la collone
        for (int c = 0; c < 9; c++) {
            if (val == board[i][c]) {
                return false;
            }
        }
        // verifie la ligne
        for (int l = 0; l < 9; l++) {
            if (val == board[l][j]) {
                return false;
            }
        }
        // verifie le carré
        HashSet<Integer> a = new HashSet<>();
        for (int l = i - (i % 3); l < i - (i % 3) + 3; l++) {
            for (int c = j - (j % 3); c < j - (j % 3) + 3; c++) {
                if (board[l][c] != 0) {
                    a.add(board[l][c]);
                }
            }
        }
        return !a.contains(val);
    }

    /**
     * Classe utilisée pour obtenir un ordre lors de l'effacement des cases de la
     * grille.
     */
    private class Coordinate {
        final int line;
        final int column;

        Coordinate(int line, int column) {
            this.line = line;
            this.column = column;
        }
    }

    /**
     * Génère un ordre aléatoire pour cacher les cases de la grille.
     * 
     * @return Une liste des coordonnées dans un ordre aléatoire.
     */
    private ArrayList<Coordinate> generateOrderToHide() {
        ArrayList<Coordinate> order = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                order.add(new Coordinate(i, j));
            }
        }
        return shuffle(order);
    }

    /**
     * Cache les cases de la grille en suivant un ordre donné.
     * Utilisation de Backtracking pour essayer de trouver soit le plus de cases
     * à cachées possible, soit laisser au moins cardinal_hiden_number cases
     * visibles.
     * 
     * @param order                 L'ordre des cases à cacher.
     * @param indice                L'indice actuel dans l'ordre.
     * @param cardinal_hiden_number Le nombre de cases à laisser visibles.
     * @return Le nombre de cases à cacher qui n'ont pas pu l'être.
     */
    private int generateHidenCaseFromThisCase(ArrayList<Coordinate> order, int indice,
            int cardinal_hiden_number) {
        Coordinate coord = order.get(indice);
        int save = board[coord.line][coord.column];
        board[coord.line][coord.column] = 0;
        if (getNombreSolutionBoard() == 1) {
            int res = this.generateHidenCaseFromNextCase(order, indice, cardinal_hiden_number - 1);
            if (!this.solve) {
                this.board[coord.line][coord.column] = save;
                res = this.generateHidenCaseFromNextCase(order, indice, cardinal_hiden_number);
            }
            return res;
        } else {
            this.board[coord.line][coord.column] = save;
            return this.generateHidenCaseFromNextCase(order, indice, cardinal_hiden_number);
        }
    }

    /**
     * Passe à la case suivante pour cacher les cases de la grille.
     * Fonction auxiliaire de generateHidenCaseFromThisCase.
     * 
     * @param order                 L'ordre des cases à cacher.
     * @param indice                L'indice actuel dans l'ordre.
     * @param cardinal_hiden_number Le nombre de cases à laisser visibles.
     * @return Le nombre de cases à cacher qui n'ont pas pu l'être.
     */
    private int generateHidenCaseFromNextCase(ArrayList<Coordinate> order, int indice,
            int cardinal_hiden_number) {
        if (indice == 80 || cardinal_hiden_number <= 0) {
            this.solve = true;
            return cardinal_hiden_number;
        } else {
            return this.generateHidenCaseFromThisCase(order, indice + 1, cardinal_hiden_number);
        }
    }

    /**
     * Cache les numéros de la grille jusqu'à ce qu'il n'en reste plus que n, ou que
     * l'on ait enlevé le plus possible de numéros de telle sorte que la grille
     * n'ait
     * toujours qu'un seul résultat.
     * 
     * @param n Le nombre de cases à laisser visibles.
     * @return Le nombre de cases à cacher qui n'ont pas pu l'être.
     */
    private int hideNumbersUntilThereAreNLeft(int n) {
        solve = false;
        ArrayList<Coordinate> order = generateOrderToHide();
        return generateHidenCaseFromThisCase(order, 0, 81 - n);
    }

    /**
     * Vérifie si la grille actuelle est identique à une autre grille.
     * 
     * @param board La grille à comparer.
     * @return true si les grilles sont identiques, false sinon.
     */
    public boolean isSameBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != this.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Copie la grille de Sudoku.
     * 
     * @return Une copie de la grille.
     */
    private int[][] copyBoard() {
        int[][] copy = new int[board.length][board[0].length];
        for (int line = 0; line < board.length; line++) {
            System.arraycopy(board[line], 0, copy[line], 0, board[line].length);
        }
        return copy;
    }

    // Getter

    /**
     * Obtient la grille de Sudoku.
     * 
     * @return Une copie de la grille de Sudoku.
     */

    public int[][] getBoard() {
        return copyBoard();
    }
}