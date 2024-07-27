import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * La classe UtilsIcon fournit des méthodes utilitaires pour manipuler des icônes.
 */
public class UtilsIcon {
    /**
     * Méthode pour créer une icône redimensionnée.
     * 
     * @param path   Le chemin de l'image de l'icône.
     * @param width  La largeur souhaitée pour l'icône.
     * @param height La hauteur souhaitée pour l'icône.
     * @return Une ImageIcon redimensionnée.
     */
    public static ImageIcon createResizedIcon(String path, int width, int height) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        if (classLoader == null) {
            classLoader = ImageIcon.class.getClassLoader();
        }

        java.net.URL resource = classLoader.getResource(path);

        if (resource == null) {
            System.err.println("Resource not found: " + path);
            return null;
        }

        ImageIcon icon = new ImageIcon(resource);
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

}
