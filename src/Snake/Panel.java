package Snake;


import javax.swing.*;
import java.awt.*;


public class Panel extends JPanel {
    public static Body body;
    public Metronome metronome = new Metronome();
    public static Apple apple;
    public static GameOver gameOver = new GameOver();
    public static boolean IS_ALIVE = true;
    public static int AppleCount;
    public static double level;

    public Panel() {
        setPreferredSize(new Dimension(Plansza.WINDOW_X, Plansza.WINDOW_Y));
        body = new Body();
        AppleCount = 0;
        level = 1;
        apple = new Apple();
        apple.generateApple();
    }
    public static void ApplePlus(){
        AppleCount++;
    }

    public static String getPoints(){
        return "Punkty: " + AppleCount;
    }
    public static String getLevel(){
        if (level == 7)
            return "Speed: MAX!";
        return "Speed: " + (int)level;}

    @Override
    protected void paintComponent(Graphics g) {
        Plansza.Paint(g);
        Font font = new Font("Arial", Font.PLAIN, 25);
        g.setFont(font);
        g.drawString(getPoints(), 10, Plansza.MENU - 10);
        g.drawString(getLevel(), Plansza.WINDOW_X - 150, Plansza.MENU - 10);
        g.drawString("Press ESC to exit", Plansza.WINDOW_X - 400, Plansza.MENU - 10);
        if (IS_ALIVE) {
            body.showBody(g);
            apple.paintComponent(g);
        }else{
            metronome.stop();
            gameOver.paintComponent(g);
            MainSnake.IS_RUNNING = false;
        }
    }

    public class Metronome extends Timer {
        public static int MS = 100; //opoznienie w milisekundach

        public static int speed() {
            switch (AppleCount) {
                case 10 -> level = 1.5;
                case 20 -> level = 2;
                case 30 -> level = 3;
                case 40 -> level = 4;
                case 50 -> level = 5;
                case 60 -> level = 6;
                case 70 -> level = 7;
            }
            return MS-(int)(level*10); //zwieksza predkosc Snake'a w zaleznosci od levelu
        }

        public Metronome() {
            super(MS, e -> {
                body.move();
                if (!body.IS_INFINITY) {
                    if (body.checkBody()) {
                        IS_ALIVE = false;
                    } else {
                        if (body.checkApple()) //Sprawdza pozycje glowy i jablka
                            metronome.setDelay(speed());
                    }
                }
                repaint();
            });
        }
    }
}

