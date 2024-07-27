import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 * La classe NumberView représente l'affichage graphique d'un numéro dans le
 * Sudoku.
 * Elle permet de choisir, afficher et gérer les numéros et hypothèses.
 */
public class NumberView extends JPanel {

    private NumberController controller;
    private NumberModel model;
    /**
     * Label de tout les petit numéro. Pour l'affichage du choix.
     */
    private JLabel[] choiseLowerNumbersLabel;
    /**
     * Label de tout les petit numéro. Pour l'affichage de ceux qui ont été
     * séléctionnés.
     */
    private JLabel[] selectedLowerNumberLabel;
    /**
     * Label pour les numéros affichés en gros.
     */
    private JLabel[] upperNumbersLabel;
    /**
     * Label vide utilisé pour l'affichage par défaut.
     */
    private JLabel emptyLabel;
    /**
     * Panel de selection qui contient les 9 petits numéros.
     */
    private JPanel choiseNumberPanel;
    /**
     * Panel de selection qui contient les petits numéros séléctionnés.
     */
    private JPanel selectedLowerNumberPanel;

    /**
     * Constructeur pour créer une nouvelle vue de numéro avec le contrôleur et le
     * modèle spécifiés.
     * 
     * @param controller Le contrôleur associé à cette vue.
     * @param model      Le modèle de numéro associé à cette vue.
     */
    public NumberView(NumberController controller, NumberModel model) {
        super();
        this.controller = controller;
        this.model = model;

        setOpaque(false);
        setLayout(new GridLayout());
        setBorder(new LineBorder(Color.BLACK, 2));

        choiseNumberPanel = new JPanel();
        choiseNumberPanel.setLayout(new GridLayout(3, 3));
        choiseNumberPanel.setOpaque(false);

        selectedLowerNumberPanel = new JPanel();
        selectedLowerNumberPanel.setLayout(new GridLayout(3, 3));
        selectedLowerNumberPanel.setOpaque(false);

        emptyLabel = new JLabel();

        choiseLowerNumbersLabel = new JLabel[10];
        choiseLowerNumbersLabel[0] = emptyLabel;

        selectedLowerNumberLabel = new JLabel[10];
        selectedLowerNumberLabel[0] = emptyLabel;

        upperNumbersLabel = new JLabel[10];
        upperNumbersLabel[0] = emptyLabel;

        for (int i = 1; i < 10; i++) {

            upperNumbersLabel[i] = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            upperNumbersLabel[i].setFont(new Font("Serif", Font.BOLD, 40));
            upperNumbersLabel[i].setForeground(Color.gray);
            upperNumbersLabel[i].setOpaque(false);

            upperNumbersLabel[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    controller.removeValue();
                    repaint();
                }
            });

            choiseLowerNumbersLabel[i] = new JLabel(String.valueOf(i), SwingConstants.CENTER);
            choiseLowerNumbersLabel[i].setForeground(Color.blue);
            choiseLowerNumbersLabel[i].setFont(new Font("Serif", Font.BOLD, 15));
            final int number = i;
            choiseLowerNumbersLabel[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    choiseLowerNumbersLabel[number].setForeground(Color.gray);
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    choiseLowerNumbersLabel[number].setForeground(Color.blue);
                    removeAll();
                    add(selectedLowerNumberPanel, BorderLayout.CENTER);
                    repaint();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (NumberModel.upperCase) {
                        controller.setValue(number);
                        choiseLowerNumbersLabel[number].setForeground(Color.blue);
                    } else {
                        if (model.getHypothesis()[number - 1]) {
                            controller.removeHypothesis(number);
                        } else {
                            controller.addHypothesis(number);
                        }
                    }
                    repaint();
                }
            });
            JPanel container = new JPanel();
            container.setOpaque(false);
            container.add(choiseLowerNumbersLabel[i], BorderLayout.CENTER);
            choiseNumberPanel.add(container, BorderLayout.CENTER);

            selectedLowerNumberLabel[i] = new JLabel(String.valueOf(i));
            selectedLowerNumberLabel[i].setForeground(Color.gray);
            selectedLowerNumberLabel[i].setFont(new Font("Serif", Font.BOLD, 15));

            container = new JPanel();
            container.setOpaque(false);
            container.add(emptyLabel, BorderLayout.CENTER);
            selectedLowerNumberPanel.add(container, BorderLayout.CENTER);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (model.getValue() == 0) {
                    removeAll();
                    add(choiseNumberPanel, BorderLayout.CENTER);
                }
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (model.getValue() == 0) {
                    Point p = e.getPoint();
                    SwingUtilities.convertPointToScreen(p, NumberView.this);
                    Rectangle bounds = getBounds();
                    bounds.setLocation(getLocationOnScreen());
                    if (!bounds.contains(p)) {
                        removeAll();
                        add(selectedLowerNumberPanel, BorderLayout.CENTER);
                    }
                }
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (model.getValue() != 0) {
                    removeAll();
                    add(emptyLabel, BorderLayout.CENTER);
                    controller.removeValue();
                }
                repaint();
            }
        });
    }

    /**
     * Ajoute une hypothèse pour un numéro donné.
     * 
     * @param i Le numéro de l'hypothèse à ajouter.
     */
    public void addHypothesis(int i) {
        JPanel component = (JPanel) selectedLowerNumberPanel.getComponent(i - 1);
        component.removeAll();
        component.add(selectedLowerNumberLabel[i], BorderLayout.CENTER);
        repaint();
    }

    /**
     * Supprime une hypothèse pour un numéro donné.
     * 
     * @param i Le numéro de l'hypothèse à supprimer.
     */
    public void removeHypothesis(int i) {
        JPanel component = (JPanel) selectedLowerNumberPanel.getComponent(i - 1);
        component.removeAll();
        component.add(emptyLabel, BorderLayout.CENTER);
        repaint();
    }

    /**
     * Séléctionne un numéro pour cette case et supprime toutes les hypothèses.
     * 
     * @param val La valeur à définir.
     */
    public void setValue(int val) {
        for (int i = 1; i < 10; i++) {
            removeHypothesis(i);
        }

        removeAll();
        add(upperNumbersLabel[val], BorderLayout.CENTER);
        repaint();
    }

    /**
     * Supprime la valeur actuelle de la case et affiche le panneau de choix.
     */
    public void removeValue() {
        removeAll();
        add(choiseNumberPanel, BorderLayout.CENTER);
        repaint();
    }

    // Getter

    public NumberModel getModel() {
        return model;
    }

    public NumberController getController() {
        return controller;
    }
}
