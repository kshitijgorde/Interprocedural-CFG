import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Component;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    public static void a(final Vector vector, final k k, final int case1, final int int1, int n, final Component component) {
        final l if1 = k.if;
        final Dimension a = a(vector, if1, if1.null, if1.new, n - if1.null - if1.c, component);
        final int n2 = a.height + if1.new + if1.goto;
        if (if1.a < 0) {
            n = a.width + if1.null + if1.c;
        }
        k.case = case1;
        k.int = int1;
        k.byte = case1 + n - 1;
        k.for = int1 + n2 - 1;
        a(if1, n - if1.null - if1.c, if1.null);
    }
    
    private static int a(final l l, final Vector vector, final int n, final FontMetrics fontMetrics) {
        int max = -10000;
        int n2 = 0;
        if (vector != null) {
            for (int i = vector.size() - 1; i >= 0; --i) {
                final Vector<q> vector2 = vector.elementAt(i);
                final int try1 = l.if[i].try;
                for (int j = vector2.size() - 1; j >= 0; --j) {
                    final q q = vector2.elementAt(j);
                    if (q.for == n) {
                        n2 = Math.max(n2, q.do - q.for + 1);
                        max = Math.max(max, q.if - try1);
                    }
                }
            }
            for (int k = vector.size() - 1; k >= 0; --k) {
                final Vector<q> vector3 = vector.elementAt(k);
                final int try2 = l.if[k].try;
                for (int n3 = vector3.size() - 1; n3 >= 0; --n3) {
                    final q q2 = vector3.elementAt(n3);
                    if (q2.for != n) {
                        return n2;
                    }
                    if (q2.if - try2 != max) {
                        final int n4 = max - (q2.if - try2);
                        final q q3 = q2;
                        q3.do += n4;
                        final q q4 = q2;
                        q4.int += n4;
                        final q q5 = q2;
                        q5.byte += n4;
                        final q q6 = q2;
                        q6.if += n4;
                        n2 = Math.max(n2, q2.do - q2.for + 1);
                    }
                }
            }
        }
        return n2;
    }
    
    private static Dimension a(final l l, final Vector vector, final String try1, final int new1, final int for1, final FontMetrics fontMetrics, final int n) {
        final Vector<q> vector2 = vector.elementAt(vector.size() - 1);
        final q q = new q();
        final int try2 = l.if[vector.size() - 1].try;
        final int stringWidth = fontMetrics.stringWidth(try1);
        int n2 = (n - fontMetrics.getHeight()) / 2;
        if (n2 < 0) {
            n2 = 0;
        }
        q.try = try1;
        q.for = for1;
        q.do = q.for + n - 1;
        q.new = new1;
        q.int = for1 + try2 + n2;
        q.a = new1 + stringWidth - 1;
        q.byte = q.int + fontMetrics.getHeight() - 1;
        q.if = q.int + fontMetrics.getAscent() - 1;
        if (q.int < q.for) {
            final int n3 = q.for - q.int;
            final q q2 = q;
            q2.do += n3;
            final q q3 = q;
            q3.int += n3;
            final q q4 = q;
            q4.byte += n3;
            final q q5 = q;
            q5.if += n3;
        }
        if (q.byte > q.do) {
            q.do = q.byte;
        }
        if (try1.length() > 0) {
            vector2.addElement(q);
        }
        final int n4 = q.a - l.null + 1;
        int a = a(l, vector, for1, fontMetrics);
        if (a == 0) {
            a = q.do - q.for + 1;
        }
        return new Dimension(n4, a);
    }
    
    private static void a(final m[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final q q = array[n4].a[n5];
        int n8;
        if (n == 30) {
            n8 = (n6 - (q.a + 1 - n7)) / 2;
        }
        else {
            n8 = n6 - (q.a + 1 - n7);
        }
        for (int i = n2; i <= n4; ++i) {
            final q[] a = array[i].a;
            int n9;
            if (i == n2) {
                n9 = n3;
            }
            else {
                n9 = 0;
            }
            int n10;
            if (i == n4) {
                n10 = n5;
            }
            else {
                n10 = a.length - 1;
            }
            for (int j = n9; j <= n10; ++j) {
                final q q3;
                final q q2 = q3 = a[j];
                q3.new += n8;
                final q q4 = q2;
                q4.a += n8;
            }
        }
    }
    
    private static void a(final l l, final int n, final int n2) {
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        int n6 = -1;
        int for1 = -1000;
        if (l == null || l.if == null) {
            return;
        }
        final m[] if1 = l.if;
        final int case1 = l.case;
        if (case1 != 30 && case1 != 50) {
            return;
        }
        for (int i = 0; i < if1.length; ++i) {
            final q[] a = if1[i].a;
            if (a != null) {
                for (int j = 0; j < a.length; ++j) {
                    final q q = a[j];
                    if (q.for > for1) {
                        if (for1 >= 0) {
                            a(if1, case1, n3, n5, n4, n6, n, n2);
                        }
                        for1 = q.for;
                        n5 = j;
                        n3 = i;
                    }
                    n4 = i;
                    n6 = j;
                }
            }
        }
        if (for1 >= 0) {
            a(if1, case1, n3, n5, n4, n6, n, n2);
        }
    }
    
    private static String a(final String s) {
        if (s != null) {
            int n;
            for (n = s.length() - 1; n >= 0 && s.charAt(n) == ' '; --n) {}
            if (n > 0) {
                return s.substring(0, n + 1);
            }
        }
        return s;
    }
    
    private static void a(final l l, final Vector vector) {
        for (int i = 0; i < vector.size(); ++i) {
            final Vector<q> vector2 = vector.elementAt(i);
            final m m = l.if[i];
            if (vector2.size() > 0) {
                m.a = new q[vector2.size()];
                for (int j = 0; j < vector2.size(); ++j) {
                    m.a[j] = vector2.elementAt(j);
                }
            }
        }
    }
    
    private static int a(final m m, int n, final int n2, final Component component) {
        if (m.char != null) {
            final FontMetrics fontMetrics = component.getFontMetrics(m.char);
            if (n2 == 0) {
                n = fontMetrics.getHeight();
            }
            else {
                n = Math.max(n, fontMetrics.getHeight());
            }
        }
        else if (n2 == 0) {
            n = m.case;
        }
        else {
            n = Math.max(n, m.case);
        }
        return n;
    }
    
    private static Dimension a(final Vector vector, final l l, final int n, final int n2, final int n3, final Component component) {
        Font byte1 = null;
        FontMetrics fontMetrics = null;
        final Dimension dimension = new Dimension(-1, -1);
        int n4 = -1;
        int n5 = 0;
        int n6 = n2;
        int n7 = 0;
        final Vector<Vector> vector2 = new Vector<Vector>();
        String s = "";
        String string = "";
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        if (l.if == null || n3 <= 0) {
            return new Dimension(0, n2 - l.new);
        }
        for (int i = 0; i < l.if.length; ++i) {
            final m m = l.if[i];
            if (m.else != null && m.else.goto != null) {
                vector.addElement(m.else);
            }
            vector2.addElement(new Vector());
            final String do1 = m.do;
            if (byte1 != m.byte) {
                byte1 = m.byte;
                fontMetrics = component.getFontMetrics(byte1);
                if (n7 == 0) {
                    n10 = a(m, n10, n7, component);
                }
            }
            for (int j = 0; j < do1.length(); ++j) {
                final char char1 = do1.charAt(j);
                boolean b = true;
                if (n9 != 0) {
                    if (char1 == '-') {
                        if (n7 + fontMetrics.stringWidth(String.valueOf(s) + string + "-") <= n3) {
                            s = String.valueOf(s) + string;
                        }
                        else {
                            String s2;
                            if (n8 != 0) {
                                s2 = String.valueOf(s) + "-";
                            }
                            else {
                                s2 = a(s);
                            }
                            final int a = a(m, n10, n7, component);
                            final Dimension a2 = a(l, vector2, s2, n + n7, n6, fontMetrics, a);
                            n5 = a2.height;
                            n4 = Math.max(n4, a2.width);
                            if (s2.length() > 0 || n7 > 0) {
                                n6 += n5;
                            }
                            n7 = 0;
                            n10 = a(m, a, n7, component);
                            s = string;
                        }
                        string = "";
                        n8 = 1;
                        b = false;
                    }
                    n9 = 0;
                }
                else if (char1 == '\\' && j + 1 < do1.length()) {
                    final char char2 = do1.charAt(j + 1);
                    if (char2 == '\\' || char2 == '-') {
                        n9 = 1;
                        b = false;
                    }
                }
                if (b) {
                    if (char1 == '\n') {
                        final String a3 = a(String.valueOf(s) + string);
                        int n11;
                        if (n7 + fontMetrics.stringWidth(a3) <= n3) {
                            n11 = a(m, n10, n7, component);
                            final Dimension a4 = a(l, vector2, a3, n + n7, n6, fontMetrics, n11);
                            n5 = a4.height;
                            n4 = Math.max(n4, a4.width);
                            n6 += n5;
                        }
                        else {
                            String s3;
                            if (n8 != 0) {
                                s3 = String.valueOf(s) + "-";
                            }
                            else {
                                s3 = a(s);
                            }
                            final int a5 = a(m, n10, n7, component);
                            final Dimension a6 = a(l, vector2, s3, n + n7, n6, fontMetrics, a5);
                            final int height = a6.height;
                            final int max = Math.max(n4, a6.width);
                            if (s3.length() > 0 || n7 > 0) {
                                n6 += height;
                            }
                            final int n12 = 0;
                            n11 = a(m, a5, n12, component);
                            final String a7 = a(string);
                            final Dimension a8 = a(l, vector2, a7, n + n12, n6, fontMetrics, n11);
                            n5 = a8.height;
                            n4 = Math.max(max, a8.width);
                            if (a7.length() > 0) {
                                n6 += n5;
                            }
                        }
                        n7 = 0;
                        n10 = a(m, n11, n7, component);
                        s = "";
                        string = "";
                        n8 = 0;
                    }
                    else if (char1 == ' ' || char1 == '-') {
                        final String string2 = String.valueOf(string) + char1;
                        if (n7 + fontMetrics.stringWidth(a(String.valueOf(s) + string2)) > n3) {
                            String s4;
                            if (n8 != 0) {
                                s4 = String.valueOf(s) + "-";
                            }
                            else {
                                s4 = a(s);
                            }
                            final int a9 = a(m, n10, n7, component);
                            final Dimension a10 = a(l, vector2, s4, n + n7, n6, fontMetrics, a9);
                            n5 = a10.height;
                            n4 = Math.max(n4, a10.width);
                            if (s4.length() > 0 || n7 > 0) {
                                n6 += n5;
                            }
                            n7 = 0;
                            n10 = a(m, a9, n7, component);
                            s = "";
                        }
                        s = String.valueOf(s) + string2;
                        string = "";
                        n8 = 0;
                    }
                    else if (char1 != '\t' && char1 != '\f' && char1 != '\r') {
                        string = String.valueOf(string) + char1;
                    }
                }
            }
            final String string3 = String.valueOf(s) + string;
            if (n7 + fontMetrics.stringWidth(string3) <= n3) {
                n10 = a(m, n10, n7, component);
                final Dimension a11 = a(l, vector2, string3, n + n7, n6, fontMetrics, n10);
                n5 = a11.height;
                n4 = Math.max(n4, a11.width);
                n7 += fontMetrics.stringWidth(string3);
                s = "";
                string = "";
                n8 = 0;
            }
            else {
                final String a12 = a(String.valueOf(s) + string);
                if (n7 + fontMetrics.stringWidth(a12) <= n3) {
                    final int a13 = a(m, n10, n7, component);
                    final Dimension a14 = a(l, vector2, a12, n + n7, n6, fontMetrics, a13);
                    n5 = a14.height;
                    n4 = Math.max(n4, a14.width);
                    final int n13 = n7 + fontMetrics.stringWidth(a12);
                    n6 += n5;
                    n7 = 0;
                    n10 = a(m, a13, n7, component);
                    s = "";
                    string = "";
                }
                else {
                    String s5;
                    if (n8 != 0) {
                        s5 = String.valueOf(s) + "-";
                    }
                    else {
                        s5 = a(s);
                    }
                    final int a15 = a(m, n10, n7, component);
                    if (s5.length() > 0) {
                        final Dimension a16 = a(l, vector2, s5, n + n7, n6, fontMetrics, a15);
                        n5 = a16.height;
                        n4 = Math.max(n4, a16.width);
                    }
                    if (s5.length() > 0 || n7 > 0) {
                        n6 += n5;
                    }
                    final int n14 = 0;
                    n10 = a(m, a15, n14, component);
                    s = "";
                    final Dimension a17 = a(l, vector2, string, n + n14, n6, fontMetrics, n10);
                    n5 = a17.height;
                    n4 = Math.max(n4, a17.width);
                    n7 = n14 + fontMetrics.stringWidth(string);
                    string = "";
                }
                n8 = 0;
            }
        }
        if (n7 != 0) {
            n6 += n5;
        }
        a(l, vector2);
        return new Dimension(n4, n6 - l.new);
    }
}
