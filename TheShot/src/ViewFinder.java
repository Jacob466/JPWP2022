import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ViewFinder {
    public int x;
    public int y;
    public int width;
    public int height;
    public Image sprite;
   ViewFinder(int x, int y, int width, int height)
    {
        this.sprite = new ImageIcon("images/viewFinder.png").getImage();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void draw(Graphics2D g2D)
    {
        //rysowanie obrazka
        g2D.drawImage(this.sprite, this.x, this.y, this.width, this.height,null);
    }
}
