import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * La classe SudokuView représente l'interface graphique pour une grille de
 * Sudoku.
 */
public class SudokuView extends JPanel {
    private SudokuModel model;
    private SudokuController controller;

    /**
     * Constructeur pour créer une nouvelle vue de Sudoku.
     * 
     * @param model      Le modèle de Sudoku utilisé.
     * @param controller Le contrôleur de Sudoku utilisé.
     */
    public SudokuView(SudokuModel model, SudokuController controller) {
        super();

        this.model = model;
        this.controller = controller;

        setLayout(new GridLayout(3, 3, -1, -1));
        setBorder(new LineBorder(Color.BLACK, 4));

        paintnombres();
    }

    /**
     * Méthode qui ajoute les nombres et les zones sélections.
     */
    private void paintnombres() {
        int[][] tab = model.getBoard();
        JPanel[][] bigCases = new JPanel[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bigCases[i][j] = new JPanel();
                bigCases[i][j].setLayout(new GridLayout(3, 3));
                bigCases[i][j].setBorder(new LineBorder(Color.BLACK, 2));
                add(bigCases[i][j]);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tab[i][j] != 0) {
                    JLabel numero = new JLabel("" + tab[i][j], SwingConstants.CENTER);
                    numero.setFont(new Font("Serif", Font.BOLD, 40));
                    numero.setBorder(new LineBorder(Color.BLACK, 2));
                    numero.setVisible(true);
                    bigCases[(i / 3)][j / 3].add(numero, BorderLayout.CENTER);
                } else {
                    bigCases[(i / 3)][j / 3].add(new NumberController(0).getView(), BorderLayout.CENTER);
                }
            }
        }
    }

    /**
     * Obtient la grille de Sudoku actuelle proposé par le joueur sur la view.
     * 
     * @return Une copie de la grille de Sudoku actuelle.
     */
    public int[][] getBoard() {
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JPanel panel = (JPanel) getComponent((i / 3) + ((j / 3) * 3));
                Component component = panel.getComponent(i % 3 + ((j % 3) * 3));
                if (component instanceof NumberView) {
                    board[j][i] = ((NumberView) component).getModel().getValue();
                } else {
                    board[j][i] = Integer.valueOf(((JLabel) component).getText());
                }
            }
        }
        return board;
    }

    // TODO: Effacer
    public void erasedNumbers(){
        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JPanel panel = (JPanel) getComponent((i / 3) + ((j / 3) * 3));
                Component component = panel.getComponent(i % 3 + ((j % 3) * 3));
                if (component instanceof NumberView) {
                    ((NumberView) component).getController().setValue(0);
                }
            }
        }
    }
}