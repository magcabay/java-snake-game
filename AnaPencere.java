import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class AnaPencere extends JFrame {
    private int genislik = 600;
    private int yukseklik = 600;

    private static AnaPencere pencere = null;

    private AnaPencere() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        if (genislik > dim.width - 100) {
            genislik = dim.width - 100;
        }
        if (yukseklik > dim.height - 100) {
            yukseklik = dim.height - 100;
        }

        int posX = (dim.width - genislik) / 2;
        int posY = (dim.height - yukseklik) / 2;

        setBounds(posX, posY, genislik, yukseklik);
    }

    public static AnaPencere pencereGetir() {
        if (pencere == null) {
            pencere = new AnaPencere();
        }
        return pencere;
    }
}