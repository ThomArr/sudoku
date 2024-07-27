import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * La classe Scene représente l'interface graphique principale pour le jeu de
 * Sudoku.
 */
public class Scene extends JPanel {
    private SudokuController sudokuController;
    private JPanel utilsBar;

    private int counterSeconds = 0;
    private int counterMinutes = 0;
    private int counterHours = 0;
    private Timer timer;

    /**
     * Constructeur de la classe Scene.
     * 
     * @param sudokuController Le contrôleur de la grille de Sudoku que l'on veut
     *                         afficher.
     */
    public Scene(SudokuController sudokuController) {
        setLayout(new BorderLayout(1, 1));

        this.sudokuController = sudokuController;
        add(sudokuController.getView(), BorderLayout.CENTER);

        utilsBar = new JPanel();
        utilsBar.setLayout(new GridLayout(1, 6, 10, 10));
        utilsBar.setBorder(new EmptyBorder(5, 5, 5, 5));

        paintButtonOptions();
        paintTimer();

        add(utilsBar, BorderLayout.SOUTH);
    }

    /**
     * Initialise un bouton avec une icône redimensionnée.
     * 
     * @param icon        Le chemin de l'image de l'icône.
     * @param pressedIcon Le chemin de l'image de l'icône lorsque le bouton est
     *                    pressé.
     * @param width       La largeur souhaitée pour l'icône.
     * @param height      La hauteur souhaitée pour l'icône.
     * @return Un bouton avec une icône redimensionnée.
     */
    private JButton initButtonWithIcon(String icon, String pressedIcon, int width, int height) {
        JButton button = new JButton();
        button.setBorder(new EmptyBorder(0, 0, 0, 0));
        ImageIcon imageIcon = UtilsIcon.createResizedIcon(icon, width, height);
        button.setIcon(imageIcon);
        imageIcon = UtilsIcon.createResizedIcon(pressedIcon, width, height);
        button.setPressedIcon(imageIcon);
        return button;
    }

    /**
     * Méthode qui ajoute les boutons d'options à la barre d'outils.
     */
    private void paintButtonOptions() {
        JButton validateButton = initButtonWithIcon("./Images/validate.png", "./Images/validate (2).png", 100, 75);
        validateButton.setOpaque(false);
        validateButton.setBackground(Color.white);
        validateButton.addActionListener(e -> {
            if (utilsBar.getBackground().equals(Color.GREEN)) {
                Main.initMenu();
                return;
            }
            if (sudokuController.check_resolved()) {
                timer.stop();
                utilsBar.setBackground(Color.GREEN);
            } else {
                int delay = 1000; // Intervalle de 1000 ms (1 seconde)

                ActionListener taskPerformer = new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        utilsBar.setBackground(null);
                        ((Timer) evt.getSource()).stop();
                    }
                };
                utilsBar.setBackground(Color.red);
                Timer redTimer;
                redTimer = new Timer(delay, taskPerformer);
                redTimer.start();
            }
        });
        utilsBar.add(validateButton);

        // TODO: Effacer
        JButton deleteButton = initButtonWithIcon("./Images/trash.png",
                "./Images/trash (2).png", 100, 75);
        deleteButton.setOpaque(false);
        deleteButton.setBackground(Color.white);
        deleteButton.addActionListener(e -> sudokuController.erasedNumbers());
        utilsBar.add(deleteButton);

        // TODO: Historique
        // JButton backButton = initButtonWithIcon("./Images/backarrow.png",
        // "./Images/backarrow (2).png",
        // 100, 75);
        // backButton.addActionListener(e -> sudokuController.replaceByLastHistory());
        // utilsBar.add(backButton);

        JCheckBox checkBoxUpperCase = new JCheckBox("Notes");
        checkBoxUpperCase.setFont(new Font("Serif", Font.BOLD, 20));
        checkBoxUpperCase.setOpaque(false);
        checkBoxUpperCase.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    NumberModel.upperCase = false;
                } else {
                    NumberModel.upperCase = true;
                }
            }
        });
        utilsBar.add(checkBoxUpperCase);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        utilsBar.add(panel);
    }

    /**
     * Méthode qui initialise et démarre le timer pour le jeu de Sudoku.
     */
    private void paintTimer() {
        JLabel timerLabel = new JLabel("000:00:00");

        timerLabel.setFont(new Font("Serif", Font.BOLD, 20));
        utilsBar.add(timerLabel);

        int delay = 1000; // Intervalle de 1000 ms (1 seconde)

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                counterSeconds++;
                if (counterSeconds == 60) {
                    counterSeconds = 0;
                    counterMinutes++;
                }
                if (counterMinutes == 60) {
                    counterMinutes = 0;
                    counterHours++;
                }

                String time = String.format("%03d:%02d:%02d", counterHours, counterMinutes, counterSeconds);
                timerLabel.setText(time);
            }
        };

        timer = new Timer(delay, taskPerformer);
        timer.start();
    }
}
