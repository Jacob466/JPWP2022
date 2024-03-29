import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 * klasa odpowiedzialna za główne okno gry
 * @author Jakub Kwaśniewski
 */
public class TheShot extends JFrame{
    private Timer timer;
    /** deklaracja pierwszego menu */
    public Menu menu;
    /** Zmienna definiująca które menu ma wyświetlić */
    public int room = 0;
    /** Deklaracja menu z zasadami */
    public Rules rules ;
    /** deklaracja wyboru kategorii */
    public CategorySelection categorySelection;
    /** deklaracja pozycji w której dzieje się akcja gry */
    public TheShotAction theShotAction;

    /** pętla rozgrywki */
    class Loop extends TimerTask {


        public void run() {
            if (theShotAction.action) {
                theShotAction.update();
                repaint();
            }
        }
    }


    /** Inicjalizacja gry */
    TheShot(){

        super("The Shot"); //Napis na okienku
        setBounds(50,50,1030,803); // Wymiary okienka
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Możliwość wyłączenia programu przez krzyżyk
        setResizable(false); //Zablokowanie możliwości rozciągania okienka
        setVisible(true);
        createBufferStrategy(2);
        this.menu = new Menu();
        this.categorySelection = new CategorySelection();
        this.rules = new Rules();
        this.theShotAction = new TheShotAction();
        timer = new Timer();
        timer.scheduleAtFixedRate(new Loop(),0,1000/60);
        getContentPane().addMouseListener(new MouseE(){ //Obsługa kliknięć myszy
            public void mousePressed(MouseEvent e)
            {
                //Odpowiednie pozycje (menu itp.) w zależności od zmiennej room
                if(room == 0)
                {
                    room = menu.click(e.getX(), e.getY());
                }
                else if(room == 1)
                {
                    room = categorySelection.click(e.getX(), e.getY());
                    if(room >2)
                    {
                        theShotAction.level(room-3);
                    }
                }
                else if(room == 2)
                {
                    room = rules.click(e.getX(), e.getY());
                }
                else if(room > 2)
                {
                    if(!theShotAction.click(e.getX(), e.getY()))room = 1;

                }
                //Wywołanie funkcji opowiedzialnej za rysowanie
                repaint();
            }

        });
        getContentPane().addMouseMotionListener(new MouseE(){
            public void mouseMoved (MouseEvent e) {
                if (room > 2) {
                    theShotAction.move(e.getX(), e.getY());

                    repaint();
                }
            }
        });
    }


    /** metoda uruchamiająca grę */
    public static void main(String[] args)
    {
        TheShot window = new TheShot();
        window.repaint();
    }
    /** rysowanie okna w zależnści od wybranej pozycji */
    public void paint(Graphics g)
    {

        BufferStrategy bstrategy = this.getBufferStrategy();
        Graphics2D g2D = (Graphics2D)bstrategy.getDrawGraphics();
        //Zmiana punktu (0,0)
        g2D.translate(3,32);
        //Rysowanie w zależności od pozycji
        if(room == 0)
        {
            this.menu.draw(g2D);
        }
        else if(room == 1)
        {
            this.categorySelection.draw(g2D);
        }
        else if (room == 2)
        {
            this.rules.draw(g2D);
        }
        else if (room > 2)
        {
            this.theShotAction.draw(g2D);
        }
        g2D.dispose();
        bstrategy.show();

    }
}


