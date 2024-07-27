# Sudoku

Un jeu de Sudoku simple développé en Java. Ce jeu vous permet de choisir un niveau de difficulté, de jouer au Sudoku avec une interface graphique intuitive et de valider votre grille pour voir si vous avez résolu le puzzle correctement.

## Prérequis

Fonctionne avec Java 21 (ou une version ultérieure) je n'ai pas essayé avec des versions antérieures.

## Installation

Pour exécuter le jeu, vous pouvez suivre l'une des deux méthodes suivantes :

1. **Exécution directe via script :**
   - Assurez-vous que le script `launch.sh` a les permissions d'exécution (vous pouvez le faire avec `chmod +x launch.sh` si nécessaire).
   - Lancez le jeu en utilisant :
     ```sh
     sh launch.sh
     ```

2. **Création d'un fichier JAR exécutable :**
   - Compilez le projet en utilisant la commande suivante :
     ```sh
     jar cfm Sudoku.jar MANIFEST.MF -C bin .
     ```
   - Exécutez le fichier JAR avec :
     ```sh
     java -jar Sudoku.jar
     ```

## Utilisation

1. **Menu de Sélection de Difficulté :**
   - Au démarrage du jeu, un menu s'ouvre pour vous permettre de choisir le niveau de difficulté du Sudoku.

2. **Grille de Sudoku :**
   - Une fois la difficulté choisie, une grille de Sudoku est générée et affichée.
   - Vous pouvez passer votre souris sur une case pour voir les 9 numéros disponibles. Cliquez sur le numéro souhaité pour le placer dans la case.
   - En mode "momento", le numéro apparaît en petit dans la case. En mode classique, il occupe toute la case.
   - Cliquez à nouveau sur un numéro dans une case pour le faire disparaître.

3. **Barre d'Options :**
   - **Bouton de Validation :** Cliquez pour valider la grille. Si la grille est correcte, la barre d'options devient verte. Cliquez à nouveau pour revenir au menu de sélection de difficulté et recommencer. Si la grille est incorrecte, la barre devient rouge et revient à la normale après une seconde.
   - **Bouton de Suppression :** Supprime toutes les entrées faites par l'utilisateur dans la grille.
   - **Option "Momento" :** Permet de choisir entre le mode classique et le mode momento.

4. **Timer :**
   - Un timer affiche le temps écoulé en heures:minutes:secondes pendant que vous jouez.

## Fonctionnalités à Venir

- Ajout d'un historique des grilles pour faciliter le retour en arrière.

## Crédits

Développé par Thomas ARROUS.

## Contributions

Les contributions sont les bienvenues.

## Licence

Ce projet est sous la **MIT License**. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

---

# Sudoku

A simple Sudoku game developed in Java. This game allows you to choose a difficulty level, play Sudoku with an intuitive graphical interface, and validate your grid to check if you have solved the puzzle correctly.

## Prerequisites

Works with Java 21 (or later) I have not tried with previous versions.

## Installation

To run the game, you can use one of the following methods:

1. **Direct Execution via Script:**
   - Ensure that the `launch.sh` script has execution permissions (you can do this with `chmod +x launch.sh` if needed).
   - Launch the game using:
     ```sh
     sh launch.sh
     ```

2. **Creating an Executable JAR File:**
   - Compile the project using the following command:
     ```sh
     jar cfm Sudoku.jar MANIFEST.MF -C bin .
     ```
   - Run the JAR file with:
     ```sh
     java -jar Sudoku.jar
     ```

## Usage

1. **Difficulty Selection Menu:**
   - At startup, a menu opens allowing you to choose the Sudoku difficulty level.

2. **Sudoku Grid:**
   - Once the difficulty is chosen, a Sudoku grid is generated and displayed.
   - Hover over a cell to see the 9 available numbers. Click on the desired number to place it in the cell.
   - In "momento" mode, the number appears small within the cell. In classic mode, it occupies the entire cell.
   - Click a number in a cell again to make it disappear.

3. **Options Bar:**
   - **Validate Button:** Click to validate the grid. If the grid is correct, the options bar turns green. Click again to return to the difficulty selection menu and start over. If the grid is incorrect, the bar turns red and returns to normal after one second.
   - **Clear Button:** Clears all user entries from the grid.
   - **"Momento" Option:** Allows you to choose between classic mode and momento mode.

4. **Timer:**
   - A timer displays the elapsed time in hours:minutes:seconds while you play.

## Future Features

- Adding a board history to facilitate easier backtracking.

## Credits

Developed by Thomas ARROUS.

## Contributions

Contributions are welcome.

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for more details.
