import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class Kutu extends JLabel {
    public int yon = Yon.SAG;
    private int genislik = 20;

    public Kutu() {
        setBounds(100, 100, genislik, genislik);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rectangle = new Rectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2);

        g2.setColor(new Color(0, 200, 100));
        g2.fill(rectangle);

        g2.setColor(new Color(0, 255, 130));
        g2.setStroke(new BasicStroke(1));
        g2.draw(rectangle);
    }

    public void hareket() {
        if (yon == Yon.SOL) {
            solaGit();
        } else if (yon == Yon.SAG) {
            sagaGit();
        } else if (yon == Yon.YUKARI) {
            yukariGit();
        } else if (yon == Yon.ASAGI) {
            asagiGit();
        }
    }

    public void solaGit() {
        setBounds(getX() - getWidth(), getY(), getWidth(), getHeight());
    }

    public void sagaGit() {
        setBounds(getX() + getWidth(), getY(), getWidth(), getHeight());
    }

    public void yukariGit() {
        setBounds(getX(), getY() - getHeight(), getWidth(), getHeight());
    }

    public void asagiGit() {
        setBounds(getX(), getY() + getHeight(), getWidth(), getHeight());
    }
}