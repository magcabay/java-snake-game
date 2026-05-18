import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.Color;

public class Yem extends JLabel {
    private int genislik = 20;

    public Yem() {
        setBounds(20, 20, genislik, genislik);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D elips = new Ellipse2D.Double(1, 1, genislik - 2, genislik - 2);

        g2.setColor(new Color(255, 80, 80));
        g2.fill(elips);
    }

    public void setPosition(int posX, int posY) {
        setBounds(posX, posY, genislik, genislik);
    }
}