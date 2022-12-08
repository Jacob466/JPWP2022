import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import static java.lang.Math.abs;
import java.util.Random;

public class TheShotAction {
    public boolean action;
    public Button returnB;
    public Image background;

    public ViewFinder viewFinder;

    public Task task;
    public int frame;

    public boolean gameOver;
    public int lives;
    public int sumTime;
    public int sumLevel;
    TheShotAction()
    {
        this.task = new Task(0,0,0,0,0);
        this.returnB = new Button(900, 50,100,40,"Cofnij");
        this.background = new ImageIcon("images/ActionBackground2.jpg").getImage();
        this.viewFinder = new ViewFinder(0,0,70,70);
    }

    //wykonuje się 60 razy na sekunde
    public void update()
    {
        //koniec gry jeśli brak żyć
        if(lives <=0 && !gameOver)
        {
            this.sumTime +=frame/60;
            this.task.text = "Koniec gry";
            gameOver=true;
        }
        //jeśli koniec gry to rozgrywka się nie aktualizuje
        if(gameOver)
        {
            return;
        }
        //task się zmienia jeśli gracz ustrzeli wszystkie poprawne odpowiedzi lub jeśli
        //na ekranie nie będzie żadnych odpowiedzi do ustrzelania
        if(!this.task.checkAnswers() || this.task.answers.size()==0)
        {
            this.sumLevel++;
            this.sumTime +=frame/60;
            this.frame = 0;
            this.task.changeTask();
        }

        //przesuwa odpowiedzi do góry
        for(int i =0;i<task.answers.size();i++)
        {
            this.task.answers.get(i).move();
        }

        //sprawdzenie czy któraś z odpowiedzi znalazła się na samej górze
        for(int i =0;i<task.answers.size();i++)
        {
            if(this.task.answers.get(i).y+this.task.answers.get(i).height<0)
            {
                //jeśli ta odpowiedź była poprawna, gracz traci życie
                if(this.task.checkAnswer(i))
                {

                    if(this.lives>0) {
                        this.lives--;
                    }
                }
                this.task.answers.remove(i);
                i--;
            }
        }


        frame +=1;
    }

    //funkcja inicjująca rozgrywkę
    public void level(int cat)
    {
        this.task = new Task(150,20,700,70,cat);
        this.sumTime = 0;
        this.frame = 0;
        this.lives = 3;
        this.sumLevel=0;
        gameOver =false;
        this.action = true;


    }
    //funkcja poruszająca celownikiem
    public void move(int x, int y)
    {
        this.viewFinder.x = x-this.viewFinder.width/2;
        this.viewFinder.y = y-this.viewFinder.height/2;
    }
    //funkcja obsługująca klinkięcia
    public boolean click(int x, int y)
    {
        //kolizja z przyciskiem wyjścia
        if(x> returnB.x && x< returnB.x+returnB.width&& y> returnB.y&& y <returnB.y+returnB.height)
        {
            quit();
            return false;
        }
        if(!gameOver) {
            for (int i = 0; i < this.task.answers.size(); i++)          //dla kazdej odpowiedzi sprawdzamy czy była
            {                                                           // kolizja miedzy odp a myszka
                if (this.task.answers.get(i).shot(x, y))
                {
                    if (!this.task.checkAnswer(i))
                    {
                        this.lives--;
                    }
                    this.task.answers.remove(i);
                }

            }
        }
        return true;
    }


    public void quit()
    {
        this.action = false;


    }

    public void draw(Graphics2D g2D)
    {
        g2D.drawImage(background, 0,0,1024,768,null);


        for(int i =0;i<task.answers.size();i++)
        {
            task.answers.get(i).draw(g2D);
        }

        task.draw(g2D);

        if(gameOver)
        {
            task.gameOverDraw(g2D, sumTime, sumLevel-1);
        }

        returnB.draw(g2D);

        g2D.setColor(Color.BLACK);
        //Ustawienie czcionki
        g2D.setFont(new Font("Arial",Font.BOLD,15));
        //Wypisanie Tekstu
        g2D.drawString("czas: "+(frame/60),30,30);
        g2D.drawString("życia:"+lives,30,50);



        viewFinder.draw(g2D);

    }
}

