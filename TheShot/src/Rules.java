import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Rules {
    public Button returnB; // Przycisk wyjścia z danej pozycji
    public Image background;
    //Tutaj wpisuje się zasady. Kolejny element tablicy to kolejna linia
    public String rules[]= {"Twoim zadaniem jest zestrzelnie poprawnych odpowiedzi w ",
            "jak najkrótszym czasie. Do wykorzystania masz 3 życia.", "Powodzenia!"};
    Rules()
    {
        this.returnB = new Button(462, 600,100,40,"Cofnij");
        this.background = new ImageIcon("images/tlo2.jpg").getImage();
    }
    public int click(int x, int y)
    {
        if(x> this.returnB.x&& x< this.returnB.x+this.returnB.width&& y> this.returnB.y&& y <this.returnB.y+this.returnB.height)
        {
            return 0;
        }

        return 2;
    }
    public void draw(Graphics2D g2D)
    {
        g2D.drawImage(background, 0,0,1024,768,null);
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Arial",Font.BOLD,22));
        //Wypisanie kolejnych linijek zasad
        for(int i=0; i<rules.length;i++)
        {
            g2D.drawString(rules[i],200,200+20*i);
        }
        returnB.draw(g2D);

    }
}
