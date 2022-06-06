package Snake;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class GameOver extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        savePoints();

        Font font = new Font("Arial", Font.PLAIN, 90);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER!", 20,300);
        Font fontNormal = new Font("Arial", Font.PLAIN, 24);
        g.setFont(fontNormal);
        g.drawString(StartPanel.getRekord(),180,200);

        g.drawString( "Nacisnij ENTER aby zagrac jeszcze raz",90,500);
        g.drawString( "Nacisnij 'I' aby zaczac w trybie Infinity",105,550);

    }

    public void savePoints(){
        if (Panel.AppleCount > StartPanel.LAST_REKORD)
            StartPanel.LAST_REKORD = Panel.AppleCount;
        try {
            File file = new File("src/Snake/Score.txt");
            if (file.createNewFile()) {
                PrintWriter writer = new PrintWriter("src/Snake/Score.txt", "UTF-8");
                writer.println(StartPanel.LAST_REKORD);
                writer.close();
            } else {
                PrintWriter writer = new PrintWriter("src/Snake/Score.txt", "UTF-8");
                writer.print(StartPanel.LAST_REKORD);
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Nie mozna zapisac wyniku");
            e.printStackTrace();
        }
    }
}
