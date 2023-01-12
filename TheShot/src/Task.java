
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;

/** klasa odpowiedzialana za polecenia */
public class Task
{
    /** współrzędna x */
    public int x;
    /** współrzędna y */
    public int y;
    /** szerokość */
    public int width;
    /** wysokość */
    public int height;
    /** tekst poleceń */
    public String text;
    /** kategoria */
    public int category;
    /** obraz polecenia */
    public Image sprite;
    /** level gry*/
    public int level;
    /** lista z wszystkimi odpowiedziami z kategorii matematyka */
    public ArrayList<String> mathAnswers = new ArrayList<String>();
    /** lista z poprawnymi odpowiedziami z kategorii matematyka */
    public ArrayList<String> mathProperAnswers = new ArrayList<String>();
    /** lista z wszystkimi odpowiedziami z kategori gramatyka */
    public ArrayList<String> grammarAnswers = new ArrayList<String>();
    /** lista z poprawnymi odpowiedziami z kategori gramatyka */
    public ArrayList<String> grammarProperAnswers = new ArrayList<String>();
    /** lista z wszystkimi odpowiedziami */
    public ArrayList<Answer> answers = new ArrayList<Answer>();

    /**
     * konstruktor odpowiedzialny za polożenie i rozmiar polecenia
     * @param x        współrzędna x
     * @param y        współrzędna y
     * @param width    szerkość polecenia
     * @param height   wysokość polecenia
     * @param category kategoria
     */
    Task(int x, int y, int width, int height, int category)
    {

        this.sprite = new ImageIcon("images/guzik.png").getImage();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.category = category;
        this.level = 0;
        this.text = "";
    }

    /**
     * generowanie poleceń i odpowiedzi z kategorii matematyka
     */
    public void generateMathTask()
    {
        for(int i=0;i< mathAnswers.size();i++)
        {
            mathAnswers.remove(i);
            i--;
        }
        for(int i=0;i< mathProperAnswers.size();i++)
        {
            mathProperAnswers.remove(i);
            i--;
        }

        if(level ==1)
        {

            int max=20;
            int min=1;

            int[] arr = new int[10];
            int countProperAnswers=0;
            // generowanie liczb losowych
            for(int i =0;i<10;i++) {
                arr[i]= (int)((Math.random() * (max - min)) + min) ;
                if(arr[i]%3==0)
                {
                    countProperAnswers++;
                }
            }
            // jeśli ilość liczb podzielnych przez trzy jest równa zero
            // wymuszamy aby pierwszy element był liczbą podzielną przez trzy
            if(countProperAnswers==0)
            {
                arr[0] = (int)((Math.random() * (max - min)) + min) *3;

            }

            // przydzielamy liczby do odpowiednich list
            for(int i =0;i<10;i++) {
                if(arr[i]%3==0)
                {
                    this.mathProperAnswers.add("" + arr[i]);
                }
                this.mathAnswers.add("" + arr[i]);
            }


            // tworzenie odpowiedzi
            this.text = "Ustrzel wszystkie liczby podzielne przez 3";
            for(int i =0;i<10;i++)
            {
                this.answers.add(new Answer(i*95+40,768,70,70,this.mathAnswers.get(i),Math.random() * (45)));
            }
        }
        else if (level ==2)
        {
            int max=20;
            int min=1;
            int d =0;
            // tworzenie liczb pierwszych z przedziału
            ArrayList<Integer> firstArr = new ArrayList<Integer>();
            for(int i =min;i<=max;i++)
            {
                d=0;
                for(int j =1;j<=i;j++)
                {
                    if(i%j==0)
                    {
                        d++;
                    }
                }
                if(d==2)
                {
                    firstArr.add(i);
                }
            }
            int[] arr = new int[10];
            int countProperAnswers=0;
            // generujemy liczby i sprawdzamy czy wśród nich są liczby pierwsze
            for(int i =0;i<10;i++) {
                arr[i]= (int)((Math.random() * (max - min)) + min);
                for(int j =0;j<firstArr.size();j++)
                {
                    if(arr[i]== firstArr.get(j))
                    {
                        countProperAnswers++;
                    }
                }
            }
            // jeśli nie ma liczb pierwszych, wymuszamy aby pierwszy element
            // był losową liczbą pierwszą
            if(countProperAnswers==0)
            {
                arr[0] = firstArr.get((int)((Math.random() * firstArr.size())));
            }
            // przydzielamy elementy do odpowiednich list
            for(int i =0;i< firstArr.size();i++)
            {
                this.mathProperAnswers.add("" + firstArr.get(i));

            }

            for(int i =0;i<10;i++) {

                this.mathAnswers.add("" + arr[i]);
            }
            // tworzymy listy
            this.text = "Ustrzel wszystkie liczby pierwsze";
            for(int i =0;i<10;i++)
            {
                this.answers.add(new Answer(i*95+40,768,70,70,this.mathAnswers.get(i),Math.random() * (45)));
            }

        }
    }

    /**
     * sprawdzamy czy dana odpowiedź jest poprawna
     * @param index indeks odpowiedzi
     * @return true
     */
    public boolean checkAnswer(int index)
    {
        //
        if(category ==0)
        {
            for(int i=0;i<this.mathProperAnswers.size();i++) {
                if (this.answers.get(index).text.equals(this.mathProperAnswers.get(i)))
                {
                    return true;
                }
            }
        }
        else if(category ==1)
        {
            for(int i=0;i<this.grammarProperAnswers.size();i++) {

                if (this.answers.get(index).text.equals(this.grammarProperAnswers.get(i)))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * generowanie poleceń i odpowiedzi z kategorii gramatyka
     */
    public void generateGrammarTask()
    {
        for(int i=0;i< grammarAnswers.size();i++)
        {
            grammarAnswers.remove(i);
            i--;
        }
        for(int i=0;i< grammarProperAnswers.size();i++)
        {
            grammarProperAnswers.remove(i);
            i--;
        }
        if(level ==1)
        {
            //tworzymy poprawne odpowiedzi
            grammarProperAnswers.add("a");
            grammarProperAnswers.add("ą");
            grammarProperAnswers.add("e");
            grammarProperAnswers.add("ę");
            grammarProperAnswers.add("i");
            grammarProperAnswers.add("u");
            grammarProperAnswers.add("o");
            grammarProperAnswers.add("ó");
            grammarProperAnswers.add("y");

            //tworzymy string ze wszystkimi literami
            String str = "qwertyuiopasdfghjklzxcvbnmąćęńłśóżź";
            //kroimy stringa na pojedyncze znaki
            for(int i =0;i<str.length();i++)
            {
                grammarAnswers.add(str.substring(i,i+1));
            }
            //mieszamy kolejność
            Collections.shuffle(grammarAnswers);
            //sprawdzamy czy w pierwszych 10 elementach są samogłoski
            int countProperAnswers = 0;
            for(int i =0;i<10;i++)
            {
                for(int j =0;j< grammarProperAnswers.size();j++)
                {
                    if(grammarAnswers.get(i).equals(grammarProperAnswers.get(j)))
                    {
                        countProperAnswers++;
                    }
                }

            }
            //jeśli nie to wymuszamy na pierwszym elemencie aby była losową samogłoską
            if(countProperAnswers==0)
            {
                grammarAnswers.set(0,grammarProperAnswers.get((int)(Math.random() * grammarProperAnswers.size())));
            }
            //tworzymy odpowiedzi
            this.text = "Ustrzel wszystkie samogłoski";
            for(int i =0;i<10;i++)
            {
                this.answers.add(new Answer(i*95+40,768,70,70,this.grammarAnswers.get(i),Math.random() * (45)));
            }

        }
        else if(level ==2)
        {
            this.text = "Ustrzel wszystkie rzeczowniki";

            grammarProperAnswers.add("kot");
            grammarProperAnswers.add("dom");
            grammarProperAnswers.add("dziecko");
            grammarProperAnswers.add("drabina");
            grammarProperAnswers.add("lekarz");
            grammarProperAnswers.add("pies");

            grammarAnswers.add("kot");
            grammarAnswers.add("dom");
            grammarAnswers.add("dziecko");
            grammarAnswers.add("drabina");
            grammarAnswers.add("lekarz");
            grammarAnswers.add("pies");
            grammarAnswers.add("drugi");
            grammarAnswers.add("białe");
            grammarAnswers.add("ciekawy");
            grammarAnswers.add("mrugać");
            grammarAnswers.add("biegać");
            grammarAnswers.add("odważny");

            Collections.shuffle(grammarAnswers);
            for(int i =0;i<10;i++)
            {
                this.answers.add(new Answer(i*95+40,768,70,70,this.grammarAnswers.get(i),Math.random() * (45)));
            }

        }
    }

    /**
     * zmiana polecenia
     */
    public void changeTask()
    {
        for(int i =0;i< answers.size();i++)
        {
            answers.remove(i);
            i--;
        }

        level ++;
        if(level>2)
        {
            level=1;
        }
        if(this.category ==0)
        {
            generateMathTask();
        }
        if(this.category ==1)
        {
            generateGrammarTask();
        }

    }

    /**
     * sprawdzamy czy spośród wszystkich odpowiedzi,znajdzie się przynajmniej jedna prawidłowa
     * @return  boolean
     */
    public boolean checkAnswers()
    {
        if(category==0)
        {
            for(int index=0;index <this.answers.size();index++) {
                for (int i = 0; i < this.mathProperAnswers.size(); i++) {

                    if (this.answers.get(index).text.equals(this.mathProperAnswers.get(i))) {
                        return true;
                    }
                }
            }
        }
        else if(category ==1)
        {
            for(int index=0;index <this.answers.size();index++) {

                for (int i = 0; i < this.grammarProperAnswers.size(); i++) {

                    if (this.answers.get(index).text.equals(this.grammarProperAnswers.get(i))) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    /**
     * rysowanie okna
     * @param g2D grafika
     */
    public void draw(Graphics2D g2D)
    {
        //rysowanie obrazka
        g2D.drawImage(this.sprite, this.x, this.y, this.width, this.height,null);
        //ustawienie koloru
        g2D.setColor(Color.BLACK);
        //Ustawienie czcionki
        g2D.setFont(new Font("Arial",Font.BOLD,20));
        //Wypisanie Tekstu
        g2D.drawString(text,x+5,y+30);
    }

    /**
     * Rysowanie okna po przegranej
     * @param g2D   grafika
     * @param time  czas
     * @param level level
     */
    public void gameOverDraw(Graphics2D g2D, int time, int level)
    {

        //ustawienie koloru
        g2D.setColor(Color.BLACK);
        //Ustawienie czcionki
        g2D.setFont(new Font("Arial",Font.BOLD,20));
        //Wypisanie Tekstu
        g2D.drawString("ukończono "+level+" poziomów w "+time+" sekundy",x+5,y+50);
    }
}
