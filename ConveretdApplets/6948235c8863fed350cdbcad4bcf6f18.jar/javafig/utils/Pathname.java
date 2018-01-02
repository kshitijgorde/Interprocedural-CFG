// 
// Decompiled by Procyon v0.5.30
// 

package javafig.utils;

import java.io.File;
import java.util.Vector;
import java.util.StringTokenizer;

public class Pathname
{
    static boolean debug;
    static String sep;
    static String doubleDot;
    
    public static String removeDoubleDots(final String s) {
        if (s.indexOf(Pathname.doubleDot) < 0) {
            return s;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, Pathname.sep);
        final Vector<String> vector = new Vector<String>();
        while (stringTokenizer.hasMoreTokens()) {
            vector.addElement(stringTokenizer.nextToken());
        }
        if (Pathname.debug) {
            dumpTokens(vector);
        }
        for (int i = 0; i < vector.size(); ++i) {
            if (Pathname.doubleDot.equals(vector.elementAt(i)) && i >= 1) {
                vector.removeElementAt(i);
                vector.removeElementAt(i - 1);
                i -= 2;
            }
        }
        if (Pathname.debug) {
            dumpTokens(vector);
        }
        final StringBuffer sb = new StringBuffer();
        if (s.startsWith(Pathname.sep)) {
            sb.append(Pathname.sep);
        }
        for (int j = 0; j < vector.size(); ++j) {
            if (j > 0) {
                sb.append(Pathname.sep);
            }
            sb.append((Object)vector.elementAt(j));
        }
        return sb.toString();
    }
    
    private static void dumpTokens(final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            System.out.print(" " + vector.elementAt(i));
        }
        System.out.println();
    }
    
    private static void test(final String s) {
        System.out.println(s);
        System.out.println(removeDoubleDots(s));
    }
    
    public static void main(final String[] array) {
        if (array.length > 0) {
            test(array[0]);
        }
        Pathname.sep = "/";
        test("/home/tech_1/hendrich/java/hades/../../cluj/cluj.jpt");
        Pathname.sep = "\\";
        test("C:\\users\\hendrich\\my documents\\..\\java\\..\\..\\hope.txt");
    }
    
    static {
        Pathname.debug = false;
        Pathname.sep = File.separator;
        Pathname.doubleDot = "..";
    }
}
