// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Hashtable;
import flaxchat.f.g;

public class q
{
    private static int a;
    private static String[] z;
    
    public static Hashtable a(final g g, String s, String substring) {
        final boolean a = h.a;
        if (s == null) {
            s = "";
        }
        String g2 = null;
        Label_0029: {
            if (g == null) {
                g2 = "";
                if (!a) {
                    break Label_0029;
                }
            }
            g2 = g.g();
        }
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        final int index = substring.indexOf(":");
        if (index == -1) {
            return hashtable;
        }
        final String substring2 = substring.substring(0, index);
        substring = substring.substring(index + 1);
        final int length = substring.length();
        int a2 = -1;
        String s2 = null;
        String a3 = null;
        String a4 = null;
        final j j = new j();
        for (int i = 0; i < length; ++i) {
            final char char1 = substring.charAt(i);
            if (char1 == '/') {
                a2 = q.a;
                if (!a) {
                    continue;
                }
            }
            if (char1 == '$') {
                if (i + 1 >= length) {
                    j.a(char1);
                    if (!a) {
                        continue;
                    }
                }
                final char char2 = substring.charAt(i + 1);
                if (char2 == 'i') {
                    if (i + 1 >= length) {
                        j.a(char1);
                        if (!a) {
                            continue;
                        }
                    }
                    if (substring.charAt(i + 2) != '=') {
                        j.a(char1);
                        if (!a) {
                            continue;
                        }
                    }
                    if (a2 == q.a) {
                        s2 = j.toString();
                        j.c();
                        a2 = -1;
                    }
                    a3 = a(i + 3, substring);
                    i += a3.length() + 3;
                    if (!a) {
                        continue;
                    }
                }
                if (char2 == '?') {
                    if (i + 1 >= length) {
                        j.a(char1);
                        if (!a) {
                            continue;
                        }
                    }
                    if (substring.charAt(i + 2) != '=') {
                        j.a(char1);
                        if (!a) {
                            continue;
                        }
                    }
                    if (a2 == q.a) {
                        s2 = j.toString();
                        j.c();
                        a2 = -1;
                    }
                    a4 = a(i + 3, substring);
                    i += a4.length() + 3;
                    if (!a) {
                        continue;
                    }
                }
                if (char2 == '#') {
                    j.a(s);
                    ++i;
                    if (!a) {
                        continue;
                    }
                }
                if (char2 == '1') {
                    j.a(g2);
                    ++i;
                    if (!a) {
                        continue;
                    }
                }
                j.a(char1);
                if (!a) {
                    continue;
                }
            }
            j.a(char1);
        }
        if (substring2 != null) {
            hashtable.put(q.z[1], substring2);
        }
        if (s2 != null) {
            hashtable.put(q.z[0], s2);
        }
        if (a4 != null) {
            hashtable.put(q.z[2], a4);
        }
        if (a3 != null) {
            hashtable.put(q.z[3], a3);
        }
        return hashtable;
    }
    
    private static String a(final int n, final String s) {
        final boolean a = h.a;
        final j j = new j();
        final int length = s.length();
        int n2 = n + 1;
        while (true) {
            Label_0058: {
                if (!a) {
                    break Label_0058;
                }
                final char char1 = s.charAt(n2);
                if (char1 == '\"' && !a) {
                    return j.toString();
                }
                j.a(char1);
                ++n2;
            }
            if (n2 < length) {
                continue;
            }
            break;
        }
        return j.toString();
    }
    
    public static String a(final String s, final Object[] array) {
        final boolean a = h.a;
        final j j = new j();
        final j i = new j();
        final int length = s.length();
        int n = 0;
        while (true) {
            Label_0153: {
                if (!a) {
                    break Label_0153;
                }
                final char char1 = s.charAt(n);
                Label_0150: {
                    if (char1 == '{') {
                        int n2 = n + 1;
                        Label_0104: {
                            while (true) {
                                Label_0097: {
                                    if (!a) {
                                        break Label_0097;
                                    }
                                    final char char2 = s.charAt(n2);
                                    if (char2 == '}') {
                                        ++n;
                                        if (!a) {
                                            break Label_0104;
                                        }
                                    }
                                    i.a(char2);
                                    ++n2;
                                    ++n;
                                }
                                if (n2 < length) {
                                    continue;
                                }
                                break;
                            }
                            try {
                                final int int1 = Integer.parseInt(i.toString());
                                i.c();
                                if (int1 < array.length) {
                                    j.a(array[int1]);
                                }
                                break Label_0150;
                            }
                            catch (NumberFormatException ex) {
                                if (!a) {
                                    break Label_0150;
                                }
                            }
                        }
                    }
                    j.a(char1);
                }
                ++n;
            }
            if (n >= length) {
                return j.toString();
            }
            continue;
        }
    }
    
    public static Vector a(final String s, final char c) {
        final boolean a = h.a;
        final Vector<String> vector = new Vector<String>();
        if (s == null) {
            return vector;
        }
        final int length = s.length();
        StringBuffer sb = new StringBuffer(length / 2);
        boolean b = false;
        int n = 0;
        while (true) {
            Label_0107: {
                if (!a) {
                    break Label_0107;
                }
                final char char1 = s.charAt(n);
                Label_0104: {
                    if (char1 == c) {
                        vector.addElement(sb.toString().trim());
                        sb = new StringBuffer(length / 2);
                        b = true;
                        if (!a) {
                            break Label_0104;
                        }
                    }
                    b = false;
                    sb.append(char1);
                }
                ++n;
            }
            if (n >= length) {
                if (sb.length() > 0 || b) {
                    vector.addElement(sb.toString().trim());
                }
                return vector;
            }
            continue;
        }
    }
    
    public static String a(final StringTokenizer stringTokenizer) {
        final boolean a = h.a;
        String string = "";
        while (true) {
            Label_0038: {
                if (!a) {
                    break Label_0038;
                }
                string = String.valueOf(string) + " " + stringTokenizer.nextToken();
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return string;
            }
            continue;
        }
    }
    
    public static String[] a(final String s, final String s2) {
        final boolean a = h.a;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (true) {
            Label_0046: {
                if (!a) {
                    break Label_0046;
                }
                array[n] = stringTokenizer.nextToken();
                ++n;
            }
            if (n >= array.length) {
                return array;
            }
            continue;
        }
    }
    
    static {
        q.z = new String[] { z(z("\u001djm")), z(z("\u0010fd")), z(z("\u0017iy")), z(z("\u0017jn")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u001e';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '^';
                    break;
                }
                case 1: {
                    c2 = '\'';
                    break;
                }
                case 2: {
                    c2 = ')';
                    break;
                }
                case 3: {
                    c2 = '-';
                    break;
                }
                default: {
                    c2 = '\u001e';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
