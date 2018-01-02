import java.util.StringTokenizer;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class c
{
    private static String a(final String s, final String s2) {
        return s.substring(0, s.indexOf(s2));
    }
    
    private static String do(final String s, final String s2) {
        return s.substring(s.indexOf(s2) + 1);
    }
    
    public static Vector a(final String s, final String s2, final String s3) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Vector vector = new Vector<String>();
        while (stringTokenizer.hasMoreElements()) {
            final String s4 = (String)stringTokenizer.nextElement();
            final String a = a(s4, s3);
            if (!vector.contains(a)) {
                vector.addElement(a);
                vector.addElement(do(s4, s3));
            }
            else {
                vector.setElementAt(do(s4, s3), vector.indexOf(a) + 1);
            }
        }
        return vector;
    }
    
    public static Vector if(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final Vector<Object> vector = new Vector<Object>();
        while (stringTokenizer.hasMoreElements()) {
            vector.addElement(stringTokenizer.nextElement());
        }
        return vector;
    }
    
    public static float a(final String s) {
        final int index = s.trim().indexOf(" ");
        if (index == -1) {
            return Float.valueOf(s);
        }
        final float floatValue = Float.valueOf(s.substring(0, index));
        final String substring = s.substring(index + 1);
        final int index2 = substring.indexOf("/");
        return floatValue + Float.valueOf(substring.substring(0, index2)) / Float.valueOf(substring.substring(index2 + 1));
    }
    
    public static Vector if(String string, final String s, final String s2) {
        final Vector<String> vector = new Vector<String>();
        String s3 = "";
        boolean b = false;
        while (true) {
            final int index = string.indexOf(s);
            if (index == -1) {
                break;
            }
            final int index2 = string.indexOf(s2);
            b = true;
            s3 = s3 + "|" + string.substring(index + s.length(), index2);
            string = string.substring(0, index) + string.substring(index2 + s2.length());
        }
        if (b) {
            s3 = s3.substring(1);
        }
        vector.addElement(s3);
        vector.addElement(string);
        return vector;
    }
}
