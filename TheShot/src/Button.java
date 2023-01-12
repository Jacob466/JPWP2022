
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/** klasa odpowiedzialna za przyciski  */
public class Button {
    /** współrzędna x przycisku */
    public int x;
    /** współrzędna y  przycisku */
    public int y;
    /** szerokośc przycisku */
    public int width;
    /** wysokość przycisku */
    public int height;
    /** zawartość przycisku */
    public String text;
    /** obraz przycisku */
    public Image sprite;

    /**
     * konstruktor opisujący przyciski
     * @param x      współrzędna x
     * @param y      współrzędna y
     * @param width  szerokość przycisku
     * @param height wysokość przycisku
     * @param text   opis przycisku
     */
    Button(int x, int y, int width, int height, String text)
    {
        this.sprite = new ImageIcon("images/guzik.png").getImage();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    /**
     * rysowanie przycisku, wypisanie tekstu
     * @param g2D grafika
     */
    public void draw(Graphics2D g2D)
    {
        //rysowanie obrazka
        g2D.drawImage(this.sprite, this.x, this.y, this.width, this.height,null);
        //ustawienie koloru
        g2D.setColor(Color.BLACK);
        //Ustawienie czcionki
        g2D.setFont(new Font("Arial",Font.BOLD,15));
        //Wypisanie Tekstu
        g2D.drawString(text,x+5,y+30);
    }
}
