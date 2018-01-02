// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.StringTokenizer;
import java.io.InputStream;
import java.io.DataInputStream;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import java.util.Vector;

public class aI
{
    private static Vector h;
    private static Vector i;
    private static Vector j;
    private static String L;
    private static String M;
    private static String N;
    
    public static void f(final String s) {
        try {
            DataInputStream dataInputStream;
            try {
                dataInputStream = new DataInputStream(new GZIPInputStream(new URL(s + "z").openStream()));
            }
            catch (Exception ex) {
                dataInputStream = new DataInputStream(new URL(s).openStream());
            }
            aI.L = decode(dataInputStream.readUTF());
            aI.M = decode(dataInputStream.readUTF());
            aI.N = decode(dataInputStream.readUTF());
            final String[] array = new String[dataInputStream.readInt()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = decode(dataInputStream.readUTF());
            }
            final String[] array2 = new String[dataInputStream.readInt()];
            for (int j = 0; j < array2.length; ++j) {
                array2[j] = decode(dataInputStream.readUTF());
            }
            final String[] array3 = new String[dataInputStream.readInt()];
            for (int k = 0; k < array3.length; ++k) {
                array3[k] = decode(dataInputStream.readUTF());
            }
            dataInputStream.close();
            a(array3, array, array2);
        }
        catch (Exception ex2) {}
    }
    
    private static String decode(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] ^= '\u0835';
        }
        return new String(charArray);
    }
    
    private static void a(final String[] array, final String[] array2, final String[] array3) {
        aI.h.removeAllElements();
        aI.i.removeAllElements();
        aI.j.removeAllElements();
        for (int i = 0; i < array.length; ++i) {
            boolean b = false;
            boolean b2 = false;
            String s = array[i];
            if (!s.startsWith("#")) {
                if (s.startsWith("@")) {
                    aI.i.addElement(s.substring(1));
                }
                if (s.indexOf("+") != -1) {
                    a(s, array2, array3);
                }
                else {
                    if (s.startsWith("*") || s.startsWith("%")) {
                        b = true;
                        s = s.substring(1);
                    }
                    if (s.endsWith("*") || s.endsWith("%")) {
                        b2 = true;
                        s = s.substring(0, s.length() - 1);
                    }
                    if (array[i].indexOf("%") == -1) {
                        aI.h.addElement(s);
                    }
                    if (b) {
                        for (int j = 0; j < array2.length; ++j) {
                            aI.h.addElement(array2[j] + s);
                        }
                    }
                    if (b2) {
                        for (int k = 0; k < array3.length; ++k) {
                            aI.h.addElement(s + array3[k]);
                        }
                    }
                    if (b && b2) {
                        for (int l = 0; l < array2.length; ++l) {
                            for (int n = 0; n < array3.length; ++n) {
                                aI.h.addElement(array2[l] + s + array3[n]);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private static void a(final String s, final String[] array, final String[] array2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "+");
        final Vector[] array3 = new Vector[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String s3;
            final String s2 = s3 = stringTokenizer.nextToken();
            boolean b = false;
            boolean b2 = false;
            final Vector<String> vector = new Vector<String>();
            if (s3.startsWith("*") || s3.startsWith("%")) {
                b = true;
                s3 = s3.substring(1);
            }
            if (s3.endsWith("*") || s3.endsWith("%")) {
                b2 = true;
                s3 = s3.substring(0, s3.length() - 1);
            }
            if (s2.indexOf("%") == -1) {
                vector.addElement(s3);
            }
            if (b) {
                for (int i = 0; i < array.length; ++i) {
                    vector.addElement(array[i] + s3);
                }
            }
            if (b2) {
                for (int j = 0; j < array2.length; ++j) {
                    vector.addElement(s3 + array2[j]);
                }
            }
            if (b && b2) {
                for (int k = 0; k < array.length; ++k) {
                    for (int l = 0; l < array2.length; ++l) {
                        vector.addElement(array[k] + s3 + array2[l]);
                    }
                }
            }
            array3[n] = vector;
            ++n;
        }
        aI.j.addElement(array3);
    }
    
    private static String f(final String s) {
        String s2 = "";
        String s3 = "";
        int n = 0;
        int n2 = 0;
        final char c = '\u0640';
        char c2 = '\0';
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (n != 0 && n2 == 0 && charArray[i] == c) {
                n2 = 1;
                s2 += charArray[i];
                c2 = charArray[i];
            }
            else if (n != 0 && n2 == 0) {
                s3 += charArray[i];
            }
            else if (n != 0 && n2 != 0 && charArray[i] != ' ') {
                s2 += charArray[i];
                c2 = charArray[i];
            }
            else if (n != 0 && n2 != 0 && c2 == c) {
                n2 = 0;
                s3 += charArray[i];
            }
            else if (n != 0 && n2 != 0) {
                n2 = 0;
                n = 0;
                s2 += charArray[i];
                c2 = charArray[i];
            }
            else if (charArray[i] == ' ' && c2 == c) {
                n = 1;
                s3 += charArray[i];
            }
            else {
                n = 0;
                s2 += charArray[i];
                c2 = charArray[i];
            }
        }
        return (s2 + s3).trim();
    }
    
    private static String g(final String s) {
        final int n = 2;
        String s2 = "";
        String string = "";
        int n2 = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.length() < n) {
                if (n2 != 0) {
                    string += nextToken;
                }
                else {
                    n2 = 1;
                    string = nextToken;
                }
            }
            else {
                n2 = 0;
                s2 = s2 + string + " " + nextToken + " ";
                string = "";
            }
        }
        if (string.length() > 0) {
            s2 = s2 + " " + string;
        }
        return s2.trim();
    }
    
    private static String h(final String s) {
        String s2 = "";
        String string = "";
        for (int i = 0; i < s.length(); ++i) {
            String s3 = s.substring(i, i + 1);
            final int index = aI.M.indexOf(s3);
            if (index != -1) {
                s3 = aI.N.substring(index, index + 1);
            }
            if (s3.compareTo(" ") == 0 && string.length() > 0) {
                s2 = s2 + string + " ";
                string = "";
            }
            else if (string.length() <= 0 || !s3.equalsIgnoreCase(string.substring(string.length() - 1))) {
                if (aI.L.indexOf(s3) != -1) {
                    string += s3;
                }
            }
        }
        if (string.length() > 0) {
            s2 += string;
        }
        return s2.trim();
    }
    
    public static int c(final String s) {
        int n = 0;
        for (int i = 1; i <= 3; ++i) {
            if (c(s, i)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    private static boolean c(String s, final int n) {
        if (n == 0 || aI.h.isEmpty()) {
            return false;
        }
        if (n >= 2) {
            s = f(s);
        }
        if (n >= 2) {
            s = g(s);
        }
        s = h(s);
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < aI.j.size(); ++i) {
            final Vector[] array = aI.j.elementAt(i);
            final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
            boolean b = false;
            for (int j = 0; j < array.length; ++j) {
                boolean b2 = false;
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (array[j].contains(nextToken)) {
                        b2 = true;
                        break;
                    }
                    if (n <= 1 || aI.i.size() <= 0) {
                        continue;
                    }
                    for (int k = 0; k < aI.i.size(); ++k) {
                        if (nextToken.indexOf(aI.i.elementAt(k).toString()) != -1) {
                            return true;
                        }
                    }
                }
                if (!b2) {
                    b = false;
                    break;
                }
                b = true;
            }
            if (b) {
                return true;
            }
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s, " ");
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken2 = stringTokenizer2.nextToken();
            if (nextToken2.length() == 1 && n < 3) {
                continue;
            }
            if (aI.h.contains(nextToken2)) {
                return true;
            }
            if (n <= 1 || aI.i.size() <= 0) {
                continue;
            }
            for (int l = 0; l < aI.i.size(); ++l) {
                if (nextToken2.indexOf(aI.i.elementAt(l).toString()) != -1) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        aI.h = new Vector();
        aI.i = new Vector();
        aI.j = new Vector();
    }
}