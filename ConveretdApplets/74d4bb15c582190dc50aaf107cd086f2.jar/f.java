import java.awt.FontMetrics;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Toolkit;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    private static String Ma = "";
    private static String Na = "\ud56a";
    private static String Oa = "\ud56a\ud523\ud539\ud56a\ud53d\ud523\ud52e\ud52f\ud538\ud56a\ud53e\ud522\ud52b\ud524\ud56a\ud53e\ud522\ud52f\ud56a";
    private static String Pa = "\ud527\ud52b\ud532\ud523\ud527\ud53f\ud527\ud567\ud53d\ud523\ud52e\ud53e\ud522\ud570\ud56a";
    
    public static String[] b(final String s, final int n, final Font font) {
        final FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final int countTokens = stringTokenizer.countTokens();
        final String[] array = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        final Vector vector = new Vector<String>();
        int n2 = 0;
        String s2 = f.Ma;
        for (int j = 0; j < countTokens; ++j) {
            if (fontMetrics.stringWidth(String.valueOf(s2) + array[j]) < n) {
                s2 = String.valueOf(s2) + array[j] + f.Na;
                if (++n2 == countTokens) {
                    vector.addElement(s2.trim());
                }
            }
            else {
                if (fontMetrics.stringWidth(array[j]) >= n) {
                    throw new IllegalArgumentException(String.valueOf(array[j]) + f.Oa + f.Pa + n);
                }
                vector.addElement(s2.trim());
                s2 = f.Ma;
                --j;
            }
        }
        final int size = vector.size();
        final String[] array2 = new String[size];
        for (int k = 0; k < size; ++k) {
            array2[k] = vector.elementAt(k).toString();
        }
        return array2;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\ud54a');
        }
        return new String(array);
    }
    
    static {
        f.Ma = a(f.Ma);
        f.Na = a(f.Na);
        f.Oa = a(f.Oa);
        f.Pa = a(f.Pa);
    }
}
