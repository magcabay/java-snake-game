import javax.swing.JLabel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class Yilan extends JLabel {
    public Kutu bas = new Kutu();
    public Timer timer = null;
    public Yem yem = new Yem();
    public Random rastgele = new Random();

    public ArrayList<Kutu> liste = new ArrayList<>();

    private int skor = 0;
    private boolean oyunBitti = false;

    public Yilan() {
        liste.add(bas);
        add(bas);
        add(yem);

        setFocusable(true);
        setOpaque(true);
        setLayout(null);
        setBackground(new Color(18, 18, 28));

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (oyunBitti) {
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        oyunuBaslat();
                    }
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && bas.yon != Yon.SAG) {
                    bas.yon = Yon.SOL;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && bas.yon != Yon.SOL) {
                    bas.yon = Yon.SAG;
                } else if (e.getKeyCode() == KeyEvent.VK_UP && bas.yon != Yon.ASAGI) {
                    bas.yon = Yon.YUKARI;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN && bas.yon != Yon.YUKARI) {
                    bas.yon = Yon.ASAGI;
                }
            }
        });

        timer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (carpismaVarMi()) {
                    timer.stop();
                    oyunBitti = true;
                    repaint();
                    return;
                }

                if (yem.getX() == bas.getX() && yem.getY() == bas.getY()) {
                    kuyrukEkle();
                    yemEkle();
                    skor += 10;
                }

                hepsiniYurut();
                repaint();
            }
        });
        timer.start();
    }

    private void oyunuBaslat() {
        for (int i = 1; i < liste.size(); i++) {
            remove(liste.get(i));
        }
        liste.clear();
        bas.setBounds(100, 100, 20, 20);
        bas.yon = Yon.SAG;
        liste.add(bas);
        skor = 0;
        oyunBitti = false;
        yemEkle();
        revalidate();
        repaint();
        timer.start();
    }

    public void hepsiniYurut() {
        for (int i = liste.size() - 1; i > 0; i--) {
            Kutu onceki = liste.get(i - 1);
            Kutu sonraki = liste.get(i);

            sonraki.setBounds(onceki.getX(), onceki.getY(), 20, 20);
            sonraki.yon = onceki.yon;
        }
        bas.hareket();
    }

    public void kuyrukEkle() {
        Kutu k = new Kutu();
        Kutu son = liste.get(liste.size() - 1);
        k.setBounds(son.getX(), son.getY(), 20, 20);
        k.yon = son.yon;
        liste.add(k);
        add(k);
        revalidate();
        repaint();
    }

    public void yemEkle() {
        int with = getWidth() - 20 - yem.getWidth();
        int height = getHeight() - 20 - yem.getHeight();

        int posX = Math.abs(rastgele.nextInt()) % with;
        int posY = Math.abs(rastgele.nextInt()) % height;

        posX = posX + 10;
        posY = posY + 10;

        posX = posX - (posX % 20);
        posY = posY - (posY % 20);

        for (int i = 0; i < liste.size(); i++) {
            if ((posX == liste.get(i).getX()) && (posY == liste.get(i).getY())) {
                yemEkle();
                return;
            }
        }

        yem.setPosition(posX, posY);
    }

    public boolean carpismaVarMi() {
        int kalem = 10;
        int genislik = getWidth();
        int yukseklik = getHeight();

        if (bas.getX() <= 10) return true;
        if (bas.getX() + bas.getWidth() >= genislik - kalem) return true;
        if (bas.getY() <= 10) return true;
        if (bas.getY() + bas.getHeight() >= yukseklik - kalem) return true;

        for (int i = 1; i < liste.size(); i++) {
            int posX = liste.get(i).getX();
            int posY = liste.get(i).getY();

            if (posX == bas.getX() && posY == bas.getY()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;


        Rectangle2D rectangle = new Rectangle2D.Double(5, 5, getWidth() - 10, getHeight() - 10);
        g2.setColor(new Color(0, 200, 100));
        g2.setStroke(new BasicStroke(3));
        g2.draw(rectangle);

        if (oyunBitti) {

            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, getWidth(), getHeight());

            int cx = getWidth() / 2;
            int cy = getHeight() / 2;


            g2.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 28));
            g2.setColor(new Color(255, 80, 80));
            String baslik = "OYUN BİTTİ";
            int bw = g2.getFontMetrics().stringWidth(baslik);
            g2.drawString(baslik, cx - bw / 2, cy - 30);


            g2.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 18));
            g2.setColor(new Color(0, 255, 130));
            String skorYazi = "Skor: " + skor;
            int sw = g2.getFontMetrics().stringWidth(skorYazi);
            g2.drawString(skorYazi, cx - sw / 2, cy + 5);


            g2.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 13));
            g2.setColor(new Color(180, 180, 180));
            String tekrar = "[ R ] Tekrar Oyna";
            int tw = g2.getFontMetrics().stringWidth(tekrar);
            g2.drawString(tekrar, cx - tw / 2, cy + 35);

        } else {

            g2.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 14));
            g2.setColor(new Color(0, 255, 130));
            g2.drawString("SKOR  " + skor, 18, 22);
        }
    }
}