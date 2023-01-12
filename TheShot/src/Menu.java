import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** klasa odpowiedzialana za Menu */
public class Menu {
    /** tło menu */
    public Image background; //Tło
    /** lista z przyciskami */
    public ArrayList<Button> buttons = new ArrayList<Button>();

    /**
     * Wyświtlenie menu z opcjami
     */
    Menu()
    {
        String[] options = {"Graj", "Zasady", "Wyjście"}; //Tablica tekstów na guzikach

        for(int i=0; i<3; i++)
        {
            buttons.add(new Button(462,200+80*(i+1),100,40, options[i]));//Dodawanie nowych guzików do listy
        }
        this.background = new ImageIcon("images/tlo2.jpg").getImage();
    }

    /**
     * Sprawdzanie czy myszka znajduje się na guziku. W zależności od wybranej opcji, funkcja zwróci inny numer definiujący pozycję
     * @param x współrzędna x
     * @param y współrzędna y
     * @return  int
     */
    public int click(int x, int y)
    {
        for(int i = 0; i<this.buttons.size();i++)
        {
            if(x> 462&& x< 562&& y> 200+80*(i+1)&& y <200+80*(i+1)+40)
            {
                if(i == this.buttons.size()-1)System.exit(0);
                return (i+1);
            }
        }
        return 0;
    }

    /**
     * rysowanie okienka
     * @param g2D grafika
     */
    public void draw(Graphics2D g2D)
    {
        g2D.setColor(Color.white);
        g2D.drawImage(background, 0,0,1024,768,null);
        for(int i =0;i < this.buttons.size();i++)
        {
            buttons.get(i).draw(g2D);
        }
    }
}
