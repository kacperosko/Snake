package Snake;

import javax.swing.*;

public class Window extends JFrame {

    StartPanel startPanel = new StartPanel();

    public Window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake");
        add(startPanel);

        pack();
        setLocationRelativeTo(null); //Wysrodkowuje okno na ekranie uzytkownika
        setVisible(true);
        setResizable(false); //Uniemozliwia uzytkownikowi zmiane rozmiaru okna
    }
}
