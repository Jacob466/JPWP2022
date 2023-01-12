
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/** klasa odpowiedzialna z poruszanie celownikiem */
public class ViewFinder
{
    /** współrzędna x celownika  */
    public int x;
    /** współrzędna y celownika */
    public int y;
    /** szerokość celownika */
    public int width;
    /** wysokość celownika */
    public int height;
    /** obraz celownika */
    public Image sprite;

    /**
     * inicjalizacja celownika
     * @param x      współrzędna x
     * @param y      współrzędna y
     * @param width  szerokość celownika
     * @param height wysokośc celownika
     */
    ViewFinder(int x, int y, int width, int height)
    {
        this.sprite = new ImageIcon("images/viewfinder.png").getImage();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * rysowanie celownika
     * @param g2D grafika
     */
    public void draw(Graphics2D g2D)
    {
        g2D.drawImage(this.sprite, this.x, this.y, this.width, this.height,null);
    }
}
