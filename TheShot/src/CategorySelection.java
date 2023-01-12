import java.awt.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** klasa odpowiedziala za wybór kategorii */
public class CategorySelection {
    /** tło kategorii */
    public Image background;
    /** lista z przyciskami */
    public ArrayList<Button> buttons = new ArrayList<Button>();

    /**
     * wypisanie przycisków z kategoriami
     */
    CategorySelection()
    {

        int i =0;
        buttons.add(new Button(282+120*i,200,100,40, "Matematyka"));
        i++;
        buttons.add(new Button(282+120*i,200,100,40, "Gramatyka"));

        buttons.add(new Button(462,600,100,40, "Cofnij"));
        this.background = new ImageIcon("images/tlo2.jpg").getImage();
    }

    /**
     * sprawdzenie którą opcję naciśnięto
     * @param x współrzędna x
     * @param y współrzędna y
     * @return  int
     */
    public int click(int x, int y)
    {
        for(int i = 0; i<this.buttons.size()-1;i++)
        {
            if(x> 282+120*i&& x< 382+120*i&& y> 200&& y <240)
            {
                return i + 3;
            }
        }
        if(x> 462&& x< 562&& y> 600&& y <640)
        {
            return 0;
        }
        return 1;
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
