package Snake;

import java.awt.*;

public class Plansza {
    public static int PLANSZA_SIZE = 30;

    public static int BORDER = 20;

    public static int MENU = 40;

    public static int WINDOW_X = PLANSZA_SIZE * BORDER;
    public static int WINDOW_Y = PLANSZA_SIZE * BORDER;

    public static Color color = new Color(49, 0, 161);

    public static void Paint(Graphics g){
        g.setColor(color);
        g.fillRect(0,MENU,WINDOW_X, WINDOW_Y);
    }
}
