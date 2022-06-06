package Snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainSnake {
    public static Window window;
    public static boolean IS_RUNNING = false;

    public static void main(String[] args) {
        window = new Window();


        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT) {
                    Panel.body.changeMove(1);
                }
                else if (keyCode == KeyEvent.VK_UP) {
                    Panel.body.changeMove(2);
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    Panel.body.changeMove(3);
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    Panel.body.changeMove(4);
                } else if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_I && !IS_RUNNING){
                    IS_RUNNING = true;
                    Panel.IS_ALIVE = true;
                    if (keyCode == KeyEvent.VK_I) Body.IS_INFINITY = true;

                    Panel panel = new Panel();
                    window.add(panel);
                    panel.metronome.start();
                    window.pack();
                }else if (keyCode == KeyEvent.VK_ESCAPE && IS_RUNNING){
                    Panel.IS_ALIVE = false;
                    Body.IS_INFINITY = false;
                }
            }
            });
        }
}
