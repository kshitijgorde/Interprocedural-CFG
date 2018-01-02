// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.text;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Dictionary;
import java.awt.FontMetrics;

public class $PPB
{
    public static final String $WPB = "!A!";
    
    private static int $CQB(final String s, final int n, final FontMetrics fontMetrics) {
        int n2 = 0;
        if (fontMetrics.stringWidth(s) <= n) {
            return s.length();
        }
        while (fontMetrics.stringWidth(s.substring(0, n2 + 1)) < n) {
            ++n2;
        }
        return n2;
    }
    
    public static String[] $QPB(final String s, final int n, final FontMetrics fontMetrics, final Dictionary dictionary) {
        final Vector vector = new Vector<String>();
        if (n < 4) {
            return new String[0];
        }
        if (s == null) {
            throw new Error("no text specified");
        }
        if (n <= 0) {
            throw new Error("max_w = " + n);
        }
        if (fontMetrics == null) {
            throw new Error("no fontmetrics");
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n", false);
        while (stringTokenizer.hasMoreElements()) {
            $YPB((String)stringTokenizer.nextElement(), n, fontMetrics, vector, dictionary);
        }
        int n2 = 0;
        final String[] array = new String[vector.size()];
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            array[n2++] = elements.nextElement();
        }
        return array;
    }
    
    public static void $YPB(final String s, final int n, final FontMetrics fontMetrics, final Vector vector, final Dictionary dictionary) {
        int n2 = 0;
        final int stringWidth = fontMetrics.stringWidth(" ");
        String s2 = null;
        final Vector<String> vector2 = new Vector<String>();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " \t\n", false);
        while (stringTokenizer.hasMoreElements()) {
            vector2.addElement((String)stringTokenizer.nextElement());
        }
        if (vector2.size() == 0) {
            vector.addElement("");
        }
        final StringBuffer sb = new StringBuffer();
        while (vector2.size() != 0) {
            final Vector<$ROB> vector3 = new Vector<$ROB>();
            int length = 0;
            while (vector2.size() != 0) {
                String s3 = vector2.elementAt(0);
                if (s3.toLowerCase().startsWith("http:") && s2 == null) {
                    vector2.insertElementAt("!A!", 1);
                    vector2.insertElementAt(s3, 0);
                    s3 = "!A!";
                }
                else {
                    vector2.removeElementAt(0);
                }
                if (s3.equalsIgnoreCase("!A!")) {
                    if (s2 == null) {
                        try {
                            s2 = vector2.elementAt(0);
                            vector2.removeElementAt(0);
                            length = sb.length();
                            if (length == 0) {
                                continue;
                            }
                            ++length;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        if (length < sb.length()) {
                            vector3.addElement(new $ROB(length, sb.length(), s2));
                        }
                        s2 = null;
                    }
                }
                else {
                    final int stringWidth2 = fontMetrics.stringWidth(s3);
                    if (n2 + stringWidth2 + ((sb.length() != 0) ? stringWidth : false) <= n) {
                        if (sb.length() != 0) {
                            sb.append(' ');
                            n2 += stringWidth;
                        }
                        sb.append(s3);
                        n2 += stringWidth2;
                    }
                    else {
                        if (sb.length() != 0) {
                            vector2.insertElementAt(s3, 0);
                            break;
                        }
                        final int $cqb = $CQB(s3, n, fontMetrics);
                        if ($cqb != 0) {
                            vector2.insertElementAt(s3.substring($cqb), 0);
                            vector2.insertElementAt(s3.substring(0, $cqb), 0);
                            break;
                        }
                        return;
                    }
                }
            }
            if (sb.length() != 0) {
                if (s2 != null && length < sb.length()) {
                    vector3.addElement(new $ROB(length, sb.length(), s2));
                    goto Label_0452;
                }
                if (vector3.size() != 0) {
                    dictionary.put(new Integer(vector.size()), vector3);
                }
                vector.addElement(sb.toString());
                sb.setLength(0);
                n2 = 0;
            }
        }
    }
}
