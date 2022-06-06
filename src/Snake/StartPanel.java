package Snake;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartPanel extends JPanel {

    public static int LAST_REKORD = -1;

    public static void readRekord() {
        try {
            File f = new File("src/Snake/Score.txt");
            Scanner read = new Scanner(f);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                LAST_REKORD= Integer.parseInt(line);
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie mozna odczytac rekordu");
            LAST_REKORD = -2;
        }
    }

    public StartPanel() {
        setPreferredSize(new Dimension(Plansza.WINDOW_X, Plansza.WINDOW_Y));
    }

    public static String getRekord(){
        if (LAST_REKORD == -2)
            return "Nie masz jeszcze rekordu";
        return "Twoj ostatni rekord: " + LAST_REKORD; }

    @Override
    protected void paintComponent(Graphics g) {
        if (LAST_REKORD == -1)
            readRekord();
        g.setColor(Plansza.color);
        g.fillRect(0,0,Plansza.WINDOW_X, Plansza.WINDOW_Y);
        Font fontTitle = new Font("Arial", Font.PLAIN, 50);
        g.setFont(fontTitle);
        g.setColor(Color.WHITE);
        g.drawString("Witaj w Snake!", 140,150);

        Font fontNormal = new Font("Arial", Font.PLAIN, 24);
        g.setFont(fontNormal);
        g.drawString( getRekord(),180,200);

        g.drawString( "Nacisnij ENTER aby zaczac",160,500);

        g.drawString( "Nacisnij 'I' aby zaczac w trybie Infinity",120,550);


    }
}
