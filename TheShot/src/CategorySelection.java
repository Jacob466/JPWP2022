import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class CategorySelection {
    public Image background;
    public ArrayList<Button> buttons = new ArrayList<Button>();
    CategorySelection()
    {

        int i =0;
        buttons.add(new Button(282+120*i,200,100,40, "matematyka"));
        i++;
        buttons.add(new Button(282+120*i,200,100,40, "gramatyka"));

        buttons.add(new Button(462,600,100,40, "Cofnij"));
        this.background = new ImageIcon("images/tlo2.jpg").getImage();
    }
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
