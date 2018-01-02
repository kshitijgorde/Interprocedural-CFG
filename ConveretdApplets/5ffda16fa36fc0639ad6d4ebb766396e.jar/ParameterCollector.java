import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class ParameterCollector
{
    private Applet apl;
    private int pCount;
    
    public ParameterCollector(final String s, final Applet apl) {
        this.pCount = 1;
        while (apl.getParameter(s + this.pCount++) != null) {}
        this.pCount -= 2;
        this.apl = apl;
    }
    
    public int getCount() {
        return this.pCount;
    }
    
    public String[] get(final String s) {
        final String[] array = new String[this.pCount];
        for (int i = 0; i < this.pCount; ++i) {
            array[i] = this.apl.getParameter("" + s + (i + 1));
        }
        return array;
    }
    
    public Color[] hexConvert(final String[] array) {
        final Color[] array2 = new Color[this.pCount];
        for (int i = 0; i < array.length; ++i) {
            final Color color = new Color(this.getDec(array[i].substring(0, 1)) * 16 + this.getDec(array[i].substring(1, 2)), this.getDec(array[i].substring(2, 3)) * 16 + this.getDec(array[i].substring(3, 4)), this.getDec(array[i].substring(4, 5)) * 16 + this.getDec(array[i].substring(5, 6)));
            if (color != null) {
                array2[i] = color;
            }
            else {
                array2[i] = Color.white;
            }
        }
        return array2;
    }
    
    public Color hexConvert(final String s) {
        final Color[] array = { null };
        return this.hexConvert(new String[] { s })[0];
    }
    
    private int getDec(final String s) {
        int int1;
        if (s.equalsIgnoreCase("A")) {
            int1 = 10;
        }
        else if (s.equalsIgnoreCase("B")) {
            int1 = 11;
        }
        else if (s.equalsIgnoreCase("C")) {
            int1 = 12;
        }
        else if (s.equalsIgnoreCase("D")) {
            int1 = 13;
        }
        else if (s.equalsIgnoreCase("E")) {
            int1 = 14;
        }
        else if (s.equalsIgnoreCase("F")) {
            int1 = 15;
        }
        else {
            try {
                int1 = Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                int1 = 0;
            }
        }
        return int1;
    }
    
    public int getFontStyle(final String s) {
        if (s.equalsIgnoreCase("bold")) {
            return 1;
        }
        if (s.equalsIgnoreCase("italic")) {
            return 2;
        }
        if (s.equalsIgnoreCase("bolditalic") || s.equalsIgnoreCase("bold italic")) {
            return 3;
        }
        return 0;
    }
}
