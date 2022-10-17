import javax.swing.*;
public class Main extends JFrame {
    public Main(){
        setTitle("Game");
        setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
        setSize(334,358);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        Main mg = new Main();
    }
}
//