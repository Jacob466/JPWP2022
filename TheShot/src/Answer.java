
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Answer {
    public int x;
    public int y;
    public int width;
    public int height;
    public String text;
    public Image sprite;
    Answer(int x, int y, int width, int height, String text)
    {
        this.sprite = new ImageIcon("images/guzik.png").getImage();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void move()
    {
        this.y -=1;
    }

    public boolean shot(int x, int y)
    {
        return x >=this.x && x<=this.x+this.width && y>=this.y && y<=this.y+this.height;
    }
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
