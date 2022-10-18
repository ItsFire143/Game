import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameField extends JPanel implements ActionListener {
    private static final int allDots = 100; //Всего ячеек
    //private int size = 320; // Размер поля
    private int Xuz;
    private int Yuz;
    private Image point;
    private Image stone;
    private Image dot;
    private Image player;
    private Timer timer;
    boolean right;
    boolean left;
    boolean up;
    boolean down;
    boolean p;
    public GameField() {
        generateNumb();
        loadImage();
        timer();
        addKeyListener(new Controls());
        setFocusable(true);
    }

    public void timer() {
        timer = new Timer(1000/60, this);
        timer.setRepeats(true);
       if (p = true) {timer.start();}
    }
    static String[][] panel = new String[allDots][allDots];

    private void loadImage() { // Загрузка картинок
        ImageIcon iiPoint = new ImageIcon("Point.png");
        point = iiPoint.getImage();
        ImageIcon iiStone = new ImageIcon("Stone.png");
        stone = iiStone.getImage();
        ImageIcon iiDot = new ImageIcon("Dot.png");
        dot = iiDot.getImage();
        ImageIcon iiPlayer = new ImageIcon("Player.png");
        player = iiPlayer.getImage();
    }

    public void generateNumb() { // Генерация значений игрового поля
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int rnd = ((int) (Math.random() * 6));
                if (rnd < 4) {
                    panel[i][j] = "Null";
                } else if (rnd == 4) {
                    panel[i][j] = "Point";
                } else {
                    panel[i][j] = "Stone";
                }
               // System.out.println(panel[i][j]);
            }
        }
        int randomX;
        int randomY;
        for (int i = 0; i < 100;i++){
            randomX = ((int) (Math.random() * 10));
            randomY = ((int) (Math.random() * 10));
                if (panel[randomX][randomY].equals("Null")){
                    panel[randomX][randomY] = "Player";
                    Xuz = randomX;
                    Yuz = randomY;
                    break;
                }
        }
    }
    public void actionPerformed(ActionEvent e) {
        repaint();
        //move();
    }

    public void paint(Graphics g) { //Закраска объектов по значениям
        super.paint(g);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Одна ячейка
                int dotSize = 32;
                int dotX = i * dotSize;
                int dotY = j * dotSize;
                switch (panel[i][j]) {
                    case "Point" -> g.drawImage(point, dotX, dotY, this);
                    case "Stone" -> g.drawImage(stone, dotX, dotY, this);
                    case "Null" -> g.drawImage(dot, dotX, dotY, this);
                    default -> g.drawImage(player, dotX, dotY, this);
                }
            }
        }
    }
    public void move(){
        int offset;
        if (left){
            System.out.println("Влево");
            offset = Xuz - 1;
            if (offset<10 && -1<offset && panel[offset][Yuz].equals("Null")) {
                panel[Xuz][Yuz] = "Null";
                panel[offset][Yuz] = "Player";
                Xuz = offset;
            }
        }
        if (right){
            offset = Xuz + 1;
            if (offset<10 && -1<offset && panel[offset][Yuz].equals("Null")) {
                panel[Xuz][Yuz] = "Null";
                panel[offset][Yuz] = "Player";
                Xuz = offset;
            }
        }
        if (up){
            offset = Yuz - 1;
            if (offset<10 && -1<offset && panel[Xuz][offset].equals("Null")) {
                p = true;
                panel[Xuz][Yuz] = "Null";
                panel[Xuz][offset] = "Player";
                Yuz = offset;
            }
        }
        if (down){
            offset = Yuz + 1;
            if (offset<10 && -1<offset && panel[Xuz][offset].equals("Null")) {
                p = true;
                panel[Xuz][Yuz] = "Null";
                panel[Xuz][offset] = "Player";
                Yuz = offset;
            }
        }
    }
    class Controls extends KeyAdapter { //Управление
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                System.out.println("Влево");
                left = true;
                up = false;
                down = false;
                right = false;
                move();
            }
            if (key == KeyEvent.VK_DOWN) {
                System.out.println("Вниз");
                down = true;
                right = false;
                left = false;
                up = false;
                move();
            }
            if (key == KeyEvent.VK_UP) {
                System.out.println("Вверх");
                left = false;
                up = true;
                right = false;
                down = false;
                move();
            }
            if (key == KeyEvent.VK_RIGHT) {
                System.out.println("Вправо");
                right = true;
                up = false;
                down = false;
                left = false;
                move();
            }
        }
    }
}
//