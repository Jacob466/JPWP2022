import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * klasa odpowiedzialna za okno z zasadami
 */
public class Rules {
    /** przycisk powrotu */
    public Button returnB; // Przycisk wyjścia z danej pozycji
    /** tło */
    public Image background;
    /** zasady gry */
    public String rules[]= {"Twoim zadaniem jest zestrzelnie poprawnych odpowiedzi w ",
            "jak najkrótszym czasie. Do wykorzystania masz 3 życia.", "Powodzenia!"};

    /** wyświetlenie zasad i przycisku cofnij*/
    Rules()
    {
        this.returnB = new Button(460, 600,100,40,"Cofnij");
        this.background = new ImageIcon("images/tlo2.jpg").getImage();
    }

    /**
     * sprawdzenie czy wciśnięto przycisk cofnij
     * @param x współrzędna x
     * @param y współrzędna y
     * @return  int
     */
    public int click(int x, int y)
    {
        if(x> this.returnB.x&& x< this.returnB.x+this.returnB.width&& y> this.returnB.y&& y <this.returnB.y+this.returnB.height)
        {
            return 0;
        }

        return 2;
    }

    /**
     * rysowanie okienka i wypisanie zasad
     * @param g2D grafika
     */
    public void draw(Graphics2D g2D)
    {
        g2D.setColor(Color.white);
        g2D.drawImage(background, 0,0,1024,768,null);
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Arial",Font.BOLD,20));
        //Wypisanie kolejnych linijek zasad
        for(int i=0; i<rules.length;i++)
        {
            g2D.drawString(rules[i],200,200+20*i);
        }
        returnB.draw(g2D);

    }
}
