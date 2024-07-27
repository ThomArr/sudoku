import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * La classe Main initialise et lance l'application Sudoku.
 * Elle gère le menu principal et la scène de jeu.
 */
public class Main {
    public static JFrame f;

    public static void main(String[] args) {
        f = new JFrame("Sudoku");
        initMenu();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setAlwaysOnTop(false);
        f.setVisible(true);
    }

    /**
     * Initialise le menu principal de l'application avec les boutons de séléction
     * de difficulté.
     */
    public static void initMenu() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(1, 4));

        menuPanel.add(initButton("Test", Color.gray, 80, 0));

        menuPanel.add(initButton("Random", null, 0, 0));

        menuPanel.add(initButton("Facile", Color.green, 30, 6));

        menuPanel.add(initButton("Moyen", Color.yellow, 27, 2));

        menuPanel.add(initButton("Difficile", Color.orange, 24, 2));

        menuPanel.add(initButton("Supérieur", Color.red, 21, 2));

        menuPanel.add(initButton("Impossible", Color.magenta, 17, 3));

        f.setSize(800, 200);
        f.setLocationRelativeTo(null);

        f.setContentPane(menuPanel);
    }

    /**
     * Crée un bouton pour initialiser une partie avec la difficulté souhaité.
     *
     * @param name                   Le texte du bouton.
     * @param color                  La couleur de fond du bouton.
     * @param cardinalRevealNumber   Le nombre de cases révélées au début.
     * @param interval               L'intervalle de cases révélées au début.
     * @return Un JButton configuré.
     */
    private static JButton initButton(String name, Color color, int cardinalReavealNumber, int interval) {
        JButton button = new JButton();
        button.addActionListener(e -> {
            if (cardinalReavealNumber + interval < 17) {
                // Aucun grille valide avec moins de 17 nombres révélées.
                initGame(new SudokuController());
            } else {
                initGame(new SudokuController(cardinalReavealNumber, interval));
            }
        });
        button.setText(name);
        button.setBackground(color);
        return button;
    }

    /**
     * Initialise une nouvelle partie de Sudoku.
     *
     * @param sudoku L'objet Sudoku représentant le jeu de Sudoku.
     */
    private static void initGame(SudokuController sudoku) {
        f.getContentPane().removeAll();
        Scene scene = new Scene(sudoku);

        f.setSize(900,1000);
        f.setLocationRelativeTo(null);

        f.setContentPane(scene);
    }
}