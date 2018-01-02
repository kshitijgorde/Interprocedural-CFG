import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class digitaldisplay extends Canvas
{
    int[] VerLedx;
    int[] VerLedy;
    int[] HorLedx;
    int[] HorLedy;
    int CifraXP;
    int CifraYP;
    int[] CifraVal;
    boolean BigClock;
    String[] Led;
    String[] Cifre;
    Color bgColor;
    Color fgColor;
    String strValueDisplayed;
    int NumCifre;
    static int MAXNUMCIFRE;
    
    public digitaldisplay(final boolean bigClock, final int numCifre, final Color bgColor, final Color fgColor) {
        this.BigClock = bigClock;
        this.NumCifre = numCifre;
        this.bgColor = bgColor;
        this.fgColor = fgColor;
        this.CifraVal = new int[digitaldisplay.MAXNUMCIFRE];
        if (bigClock) {
            this.VerLedx = this.getParameterIntList("2 3 5 5 3 2 0 0 2", ' ');
            this.VerLedy = this.getParameterIntList("0 0 2 17 19 19 17 2 0", ' ');
            this.HorLedx = this.getParameterIntList("2 17 19 19 17 2 0 0 2", ' ');
            this.HorLedy = this.getParameterIntList("0 0 2 3 5 5 3 2 0", ' ');
            this.Led = this.getParameterList("H 4 0,V 0 4,V 22 4,H 4 22,V 0 26,V 22 26,H 4 44", ',');
            this.CifraYP = 5;
            this.CifraXP = 37;
            this.resize(new Dimension(this.CifraXP * numCifre, 60));
        }
        else {
            this.VerLedx = this.getParameterIntList("0 1 2 3 3 2 1 0 0", ' ');
            this.VerLedy = this.getParameterIntList("1 0 0 1 8 9 9 8 1", ' ');
            this.HorLedx = this.getParameterIntList("0 1 8 9 9 8 1 0 0", ' ');
            this.HorLedy = this.getParameterIntList("1 0 0 1 2 2 3 2 1", ' ');
            this.Led = this.getParameterList("H 3 0,V 0 3,V 12 3,H 3 12,V 0 15,V 12 15,H 3 24", ',');
            this.CifraYP = 3;
            this.CifraXP = 20;
            this.resize(new Dimension(this.CifraXP * numCifre - this.CifraYP, 32));
        }
        (this.Cifre = new String[10])[0] = "1 1 1 0 1 1 1";
        this.Cifre[1] = "0 0 1 0 0 1 0";
        this.Cifre[2] = "1 0 1 1 1 0 1";
        this.Cifre[3] = "1 0 1 1 0 1 1";
        this.Cifre[4] = "0 1 1 1 0 1 0";
        this.Cifre[5] = "1 1 0 1 0 1 1";
        this.Cifre[6] = "0 1 0 1 1 1 1";
        this.Cifre[7] = "1 0 1 0 0 1 0";
        this.Cifre[8] = "1 1 1 1 1 1 1";
        this.Cifre[9] = "1 1 1 1 0 1 0";
    }
    
    public void SetValue(final String strValueDisplayed) {
        for (int i = 0; i < digitaldisplay.MAXNUMCIFRE - 1; ++i) {
            this.CifraVal[i] = 0;
        }
        for (int j = 0; j < strValueDisplayed.length(); ++j) {
            this.CifraVal[this.NumCifre - strValueDisplayed.length() + j] = Integer.parseInt(strValueDisplayed.substring(j, j + 1));
        }
        this.strValueDisplayed = strValueDisplayed;
        this.repaint();
    }
    
    String[] getParameterList(final String s, final char c) {
        String substring = s;
        String[] array = null;
        if (s != null) {
            int n;
            int index;
            for (n = 0; (index = substring.indexOf(c)) >= 0; substring = substring.substring(index + 1), ++n) {}
            if (substring.length() > 0) {
                ++n;
            }
            array = new String[n];
            String substring2 = s;
            for (int i = 0; i < array.length; ++i) {
                final int index2 = substring2.indexOf(c);
                if (index2 < 0) {
                    array[i] = substring2.substring(0, substring2.length());
                    substring2 = null;
                }
                else {
                    array[i] = substring2.substring(0, index2);
                    substring2 = substring2.substring(index2 + 1);
                }
            }
        }
        return array;
    }
    
    int[] getParameterIntList(final String s, final char c) {
        String substring = s;
        int[] array = null;
        if (s != null) {
            int n;
            int index;
            for (n = 0; (index = substring.indexOf(c)) >= 0; substring = substring.substring(index + 1), ++n) {}
            if (substring.length() > 0) {
                ++n;
            }
            array = new int[n];
            String substring2 = s;
            for (int i = 0; i < array.length; ++i) {
                final int index2 = substring2.indexOf(c);
                if (index2 < 0) {
                    array[i] = Integer.parseInt(substring2.substring(0, substring2.length()));
                    substring2 = null;
                }
                else {
                    array[i] = Integer.parseInt(substring2.substring(0, index2));
                    substring2 = substring2.substring(index2 + 1);
                }
            }
        }
        return array;
    }
    
    public void PrintLed(final Graphics graphics, final int n, final int n2, final int n3, final Color color) {
        final int[] array = new int[9];
        final int[] array2 = new int[9];
        final String[] parameterList = this.getParameterList(this.Led[n], ' ');
        if (parameterList[0].compareTo("H") == 0) {
            for (int i = 0; i <= 8; ++i) {
                array2[i] = (array[i] = 0);
                array[i] = this.HorLedx[i] + n2 + Integer.parseInt(parameterList[1]);
                array2[i] = this.HorLedy[i] + n3 + Integer.parseInt(parameterList[2]);
            }
        }
        else {
            for (int j = 0; j <= 8; ++j) {
                array2[j] = (array[j] = 0);
                array[j] = this.VerLedx[j] + n2 + Integer.parseInt(parameterList[1]);
                array2[j] = this.VerLedy[j] + n3 + Integer.parseInt(parameterList[2]);
            }
        }
        graphics.setColor(color);
        graphics.fillPolygon(array, array2, 9);
    }
    
    public void PrintCifra(final Graphics graphics, final int n, final int n2, final int n3) {
        final String[] parameterList = this.getParameterList(this.Cifre[n], ' ');
        for (int i = 0; i <= 6; ++i) {
            if (parameterList[i].compareTo("1") == 0) {
                this.PrintLed(graphics, i, n2, n3, this.fgColor);
            }
            else {
                this.PrintLed(graphics, i, n2, n3, this.bgColor);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bgColor);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(this.fgColor);
        for (int i = 0; i < this.NumCifre; ++i) {
            this.PrintCifra(graphics, this.CifraVal[i], this.CifraXP * i, this.CifraYP);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        digitaldisplay.MAXNUMCIFRE = 10;
    }
}
