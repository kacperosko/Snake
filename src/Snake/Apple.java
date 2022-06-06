package Snake;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Apple extends JPanel {

    Random rand = new Random();

    ImageIcon i = new ImageIcon("src/Snake/apple.png"); //tworze nowa ikone jablka
    Image image = i.getImage(); //transformuje typ zmiennej zeby zmienic rozmiar
    Image ni = image.getScaledInstance(Plansza.BORDER, Plansza.BORDER, Image.SCALE_SMOOTH);
    //zmienia rozmiar ikony w trybie smooth zeby nie zdeformowac obrazka

    ImageIcon icon = new ImageIcon(ni); //transormuje z powrotem na ikone zeby ja narysowac

    public static int APPLE_X = 60;
    public static int APPLE_Y = 60;

    @Override
    protected void paintComponent(Graphics g) {
        icon.paintIcon(this, g, APPLE_X, APPLE_Y);
    }

    public void generateApple(){ //generuje nowe kordynaty jablka
        do {
            APPLE_X = (rand.nextInt((Plansza.PLANSZA_SIZE - 1)) * Plansza.BORDER);
            APPLE_Y = (rand.nextInt((Plansza.PLANSZA_SIZE - 4)) * Plansza.BORDER) + Plansza.MENU;
        }while (isAppleOnBody(APPLE_X, APPLE_Y));

    }
    public boolean isAppleOnBody(int x, int y){
        for (Point point : Panel.body.getBody()){
            if (point.x*Plansza.BORDER == APPLE_X && point.y*Plansza.BORDER  == APPLE_Y) {
                return true;
            }
        }
        return false;
    }


    public static int getAppleX() {
        return APPLE_X;
    }

    public static int getAppleY() {
        return APPLE_Y;
    }
}
