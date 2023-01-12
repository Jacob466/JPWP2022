
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/** klasa odpowiedzialna za tworzenie odpowiedzi */
public class Answer {
    /** współrzędna x odpowiedzi */
    public int x;
    /** współrzędna y odpowiedzi */
    public double y;
    /** szerokośc odpowiedzi */
    public int width;
    /** wysokość odpowiedzi */
    public int height;
    /** tekst w odpowiedzi */
    public String text;
    /** obraz odpowiedzi */
    public Image sprite;
    /** kąt wykorzystany w ruchu sin */
    public double alpha;
    /** Zmienna wykorzystywana do okreslenia pozycji x poprzez funkcję sinus */
    public double sinusoidal_x;

    /**
     * ładowanie odpowiedzi
     * @param x      współrzędna x
     * @param y      współrzędna y
     * @param width  szerokość odpowiedzi
     * @param height szerokość odpowiedzi
     * @param text   tekst odpowiedzi
     * @param alpha  kąt
     */
    Answer(int x, int y, int width, int height, String text, double alpha)
    {
        this.sprite = new ImageIcon("images/guzik.png").getImage();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.alpha = alpha;
        this.sinusoidal_x = 0;
    }

    /**
     * funkcja odpowiedzialna za ruch odpowiedzi
     */
    public void move()
    {
        this.sinusoidal_x = Math.sin(Math.toRadians(this.alpha))*3;
        this.x = this.x + (int)this.sinusoidal_x;
        this.alpha += 4;
        this.y -= 0.9;  //predkosc ruchu odpowiedzi
    }

    /**
     * sprawdzenie czy naciśnięto odpowiedź
     * @param x współrzędna x
     * @param y współrzędna y
     * @return  boolean
     */
    public boolean shot(int x, int y)
    {
        return x >=this.x && x<=this.x+this.width && y>=this.y && y<=this.y+this.height;
    }

    /**
     * rysowanie obrazka
     * @param g2D grafika
     */
    public void draw(Graphics2D g2D)
    {
        g2D.drawImage(this.sprite, this.x, (int)this.y, this.width, this.height,null);
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Arial",Font.BOLD,17));
        g2D.drawString(text,x+5,(int)y+30);
    }
}
