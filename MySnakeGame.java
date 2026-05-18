public class MySnakeGame {
    public static void main(String[] args) {
        AnaPencere pencere = AnaPencere.pencereGetir();
        Yilan yilan = new Yilan();

        yilan.setBounds(0, 0, pencere.getWidth(), pencere.getHeight());
        pencere.setLayout(null);
        pencere.add(yilan);
        pencere.setVisible(true);
    }
}