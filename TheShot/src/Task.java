
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;

public class Task{
    public int x;
    public int y;
    public int width;
    public int height;
    public String text;
    public int category;
    public Image sprite;
    public int level;
    public ArrayList<String> mathAnswers = new ArrayList<String>();
    public ArrayList<String> mathProperAnswers = new ArrayList<String>();
    public ArrayList<String> grammarAnswers = new ArrayList<String>();
    public ArrayList<String> grammarProperAnswers = new ArrayList<String>();
    public ArrayList<Answer> answers = new ArrayList<Answer>();
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
            //generowanie liczb losowych
            for(int i =0;i<10;i++) {
                arr[i]= (int)((Math.random() * (max - min)) + min) ;
                if(arr[i]%3==0)
                {
                    countProperAnswers++;
                }
            }
            //jeśli ilość liczb podzielnych przez trzy jest równa zero
            //wymuszamy aby pierwszy element był liczbą podzielną przez trzy
            if(countProperAnswers==0)
            {
                arr[0] = (int)((Math.random() * (max - min)) + min) *3;

            }

            //przydzielamy liczby do odpowiednich list
            for(int i =0;i<10;i++) {
                if(arr[i]%3==0)
                {
                    this.mathProperAnswers.add("" + arr[i]);
                }
                this.mathAnswers.add("" + arr[i]);
            }


            //tworzymy odpowiedzi
            this.text = "Ustrzel wszystkie liczby podzielne przez 3";
            for(int i =0;i<10;i++)
            {
                this.answers.add(new Answer(i*95+40,768,70,70,this.mathAnswers.get(i)));
            }
        }
        else if (level ==2)
        {
            int max=20;
            int min=1;
            int d =0;
            //tworzymy liczby pierwsze z przedziału
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
            //generujemy liczby i sprawdzamy czy wśród nich są liczby pierwsze
            for(int i =0;i<10;i++)
            {
                arr[i]= (int)((Math.random() * (max - min)) + min);
                for(int j =0;j<firstArr.size();j++)
                {
                    if(arr[i]== firstArr.get(j))
                    {
                        countProperAnswers++;
                    }
                }
            }
            //jeśli nie ma liczb pierwszych, wymuszamy aby pierwszy element
            //był losową liczbą pierwszą
            if(countProperAnswers==0)
            {
                arr[0] = firstArr.get((int)((Math.random() * firstArr.size())));
            }
            //przydzielamy elementy do odpowiednich list
            for(int i =0;i< firstArr.size();i++)
            {
                this.mathProperAnswers.add("" + firstArr.get(i));

            }

            for(int i =0;i<10;i++) {

                this.mathAnswers.add("" + arr[i]);
            }
            //tworzymy listy
            this.text = "Ustrzel wszystkie liczby pierwsze";
            for(int i =0;i<10;i++)
            {
                this.answers.add(new Answer(i*95+40,768,70,70,this.mathAnswers.get(i)));
            }

        }
    }

    public boolean checkAnswer(int index)
    {
        //sprawdzamy czy dana odpowiedź jest poprawna
        if(category == 0)
        {
            for(int i=0;i<this.mathProperAnswers.size();i++) {
                if (this.answers.get(index).text.equals(this.mathProperAnswers.get(i)))
                {
                    return true;
                }
            }
        }
        else if(category == 1)
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
                this.answers.add(new Answer(i*95+40,768,70,70,this.grammarAnswers.get(i)));
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
            for(int i =0;i<10;i++)
            {
                this.answers.add(new Answer(i*95+40,768,70,70,this.grammarAnswers.get(i)));
            }


        }
    }
    public void changeTask()
    {
        //zmiana tasków
        for(int i = 0;i < answers.size();i++)
        {
            answers.remove(i);
            i--;
        }

        level ++;
        if(level > 2)
        {
            level = 1;
        }
        if(this.category == 0)
        {
            generateMathTask();
        }
        if(this.category == 1)
        {
            generateGrammarTask();
        }

    }
    public boolean checkAnswers()
    {
        //sprawdzamy czy spośród wszystkich odpowiedzi,znajdzie się przynajmniej jedna prawidłowa
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
    public void gameOverDraw(Graphics2D g2D, int time, int level)
    {

        //ustawienie koloru
        g2D.setColor(Color.BLACK);
        //Ustawienie czcionki
        g2D.setFont(new Font("Arial",Font.BOLD,20));
        //Wypisanie Tekstu
        g2D.drawString("ukończono "+level+" poziomów w "+time+" sekund",x+5,y+50);
    }
}
