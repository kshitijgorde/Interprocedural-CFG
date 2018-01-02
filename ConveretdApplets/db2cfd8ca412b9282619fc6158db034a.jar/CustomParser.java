import java.util.Vector;
import java.awt.FontMetrics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class CustomParser
{
    public String[] parseStrings(final String s, final String s2) {
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            final String[] array = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = stringTokenizer.nextToken();
            }
            return array;
        }
        return null;
    }
    
    public String[] wordWrap(final String s, final FontMetrics fontMetrics, final int n) {
        final Vector vector = new Vector<String>(0, 1);
        final String[] strings = this.parseStrings(s, " ");
        String string = strings[0];
        for (int i = 1; i < strings.length; ++i) {
            if (fontMetrics.stringWidth(string) + fontMetrics.stringWidth(strings[i] + " ") >= n) {
                vector.insertElementAt(string, vector.size());
                string = strings[i];
            }
            else {
                string = string + " " + strings[i];
            }
            if (i == strings.length - 1) {
                vector.insertElementAt(string, vector.size());
            }
        }
        final int size = vector.size();
        final String[] array = new String[size];
        for (int j = 0; j < size; ++j) {
            array[j] = vector.elementAt(j);
        }
        return array;
    }
}
