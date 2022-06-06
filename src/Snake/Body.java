package Snake;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Body {

    List<Point> body; //lista koordynatow weza
    private MoveTo moveTo; //kierunek ruchu

    public static boolean IS_INFINITY = false;

    private int LAST_X;
    private int LAST_Y;

    public Body(){ //Dodaje kilka piewrszych elementow (jako Array z koordynatami) Snake'a do listy z punktami 'body'
        body = new ArrayList<>();
        moveTo = MoveTo.DOWN;

        int HALF_X =  (int)Plansza.PLANSZA_SIZE / 2;
        body.add(new Point(HALF_X,7));
        body.add(new Point(HALF_X,6));
        body.add(new Point(HALF_X,5));
    }

    public List<Point> getBody() {
        return body;
    }

    public void showBody(Graphics g){ //rysuje cale cialo weza na ekranie
        g.setColor(Color.BLACK);
        for (Point point : next()) {
            g.fillRect(point.x * Plansza.BORDER, point.y * Plansza.BORDER, Plansza.BORDER, Plansza.BORDER); //koloruje kazdy pojedynczy punkt body, oprocz glowy
        }
        g.setColor(new Color(116, 0, 129));
        g.fillRect(first().x * Plansza.BORDER, first().y * Plansza.BORDER, Plansza.BORDER, Plansza.BORDER); //koloruje kazdy pojedynczy punkt body, oprocz glowy
    }

    public boolean checkBody() { //sprawdza czy glowa nie wyszla poza ekran lub na swoje cialo
        if (first().x*Plansza.BORDER < 0 || first().x*Plansza.BORDER >= Plansza.WINDOW_X || first().y*Plansza.BORDER < Plansza.MENU  || first().y*Plansza.BORDER >= Plansza.WINDOW_Y){
            return true;
        }
        for (Point point : next()){
            if (point.x == first().x && point.y == first().y) {
                return true;
            }
        }
        return false;
    }

    public void changeMove(int x){ //zmienia kierunek po wcisnieciu jednej ze strzalek
        if (x == 1 && moveTo!=MoveTo.RIGHT) moveTo = MoveTo.LEFT;
        if (x == 2 && moveTo!=MoveTo.DOWN) moveTo = MoveTo.UP;
        if (x == 3 && moveTo!=MoveTo.LEFT) moveTo = MoveTo.RIGHT;
        if (x == 4 && moveTo!=MoveTo.UP) moveTo = MoveTo.DOWN;
    }

    public boolean checkApple(){ //sprawdza czy glowa weza nalozyla sie na jablko
        if (Apple.getAppleX() == first().x*Plansza.BORDER && Apple.getAppleY() == first().y*Plansza.BORDER) {
            Panel.ApplePlus(); //Dodaj zdobyty punkt
            Panel.apple.generateApple(); //Wygeneruj nowe jablko
            body.add(new Point(LAST_X, LAST_Y)); //dodaje kolejny punkt do Snake'a jesli zje jablko
            return true;
        }else {
            return false;
        }
    }

    private Point first(){
        return body.get(0); //zwraca pierwszy element jako glowa snake'a
    }

    private List<Point> next() {
        return body.subList(1, body.size()); //zwraca liste wszystkich elementow snake'a po glowie(po indeksie 1 wlacznie)
    }

    public void move() {
        LAST_X = body.get(body.size()-1).x;
        LAST_Y = body.get(body.size()-1).y;
        for (int i = body.size()-1; i > 0; i--){
            body.get(i).setLocation(body.get(i-1)); //ustawia od konca nowe wspolrzedne elementu nastepnego w Body z wylaczeniem glowy
        }

        switch (moveTo){ //zmienia pozycje glowy wzgledem ruchu weza
            case UP -> first().y--;
            case DOWN -> first().y++;
            case LEFT -> first().x--;
            case RIGHT -> first().x++;
        }
        if (IS_INFINITY){
            if (first().x == -1) first().x = Plansza.PLANSZA_SIZE-1;
            if (first().x*Plansza.BORDER >= Plansza.WINDOW_X) first().x = 0;
            if (first().y == 1) first().y = Plansza.PLANSZA_SIZE - 1;
            if (first().y*Plansza.BORDER >= Plansza.WINDOW_Y) first().y = 2;

            checkApple();
        }
    }

}
