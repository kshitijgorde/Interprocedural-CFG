// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Hashtable;
import flaxchat.h.g;

public class p
{
    private static int a;
    private static String[] z;
    
    public static Hashtable a(final g g, String s, String substring) {
        final boolean a = g.a;
        if (s == null) {
            s = "";
        }
        String d = null;
        Label_0029: {
            if (g == null) {
                d = "";
                if (!a) {
                    break Label_0029;
                }
            }
            d = g.d();
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
        final i i = new i();
        for (int j = 0; j < length; ++j) {
            final char char1 = substring.charAt(j);
            if (char1 == '/') {
                a2 = p.a;
                if (!a) {
                    continue;
                }
            }
            if (char1 == '$') {
                if (j + 1 >= length) {
                    i.a(char1);
                    if (!a) {
                        continue;
                    }
                }
                final char char2 = substring.charAt(j + 1);
                if (char2 == 'i') {
                    if (j + 1 >= length) {
                        i.a(char1);
                        if (!a) {
                            continue;
                        }
                    }
                    if (substring.charAt(j + 2) != '=') {
                        i.a(char1);
                        if (!a) {
                            continue;
                        }
                    }
                    if (a2 == p.a) {
                        s2 = i.toString();
                        i.c();
                        a2 = -1;
                    }
                    a3 = a(j + 3, substring);
                    j += a3.length() + 3;
                    if (!a) {
                        continue;
                    }
                }
                if (char2 == '?') {
                    if (j + 1 >= length) {
                        i.a(char1);
                        if (!a) {
                            continue;
                        }
                    }
                    if (substring.charAt(j + 2) != '=') {
                        i.a(char1);
                        if (!a) {
                            continue;
                        }
                    }
                    if (a2 == p.a) {
                        s2 = i.toString();
                        i.c();
                        a2 = -1;
                    }
                    a4 = a(j + 3, substring);
                    j += a4.length() + 3;
                    if (!a) {
                        continue;
                    }
                }
                if (char2 == '#') {
                    i.a(s);
                    ++j;
                    if (!a) {
                        continue;
                    }
                }
                if (char2 == '1') {
                    i.a(d);
                    ++j;
                    if (!a) {
                        continue;
                    }
                }
                i.a(char1);
                if (!a) {
                    continue;
                }
            }
            i.a(char1);
        }
        if (substring2 != null) {
            hashtable.put(p.z[1], substring2);
        }
        if (s2 != null) {
            hashtable.put(p.z[0], s2);
        }
        if (a4 != null) {
            hashtable.put(p.z[2], a4);
        }
        if (a3 != null) {
            hashtable.put(p.z[3], a3);
        }
        return hashtable;
    }
    
    private static String a(final int n, final String s) {
        final boolean a = flaxchat.e.g.a;
        final i i = new i();
        final int length = s.length();
        int n2 = n + 1;
        while (true) {
            Label_0058: {
                if (!a) {
                    break Label_0058;
                }
                final char char1 = s.charAt(n2);
                if (char1 == '\"' && !a) {
                    return i.toString();
                }
                i.a(char1);
                ++n2;
            }
            if (n2 < length) {
                continue;
            }
            break;
        }
        return i.toString();
    }
    
    public static String a(final String s, final Object[] array) {
        final boolean a = flaxchat.e.g.a;
        final i i = new i();
        final i j = new i();
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
                                    j.a(char2);
                                    ++n2;
                                    ++n;
                                }
                                if (n2 < length) {
                                    continue;
                                }
                                break;
                            }
                            try {
                                final int int1 = Integer.parseInt(j.toString());
                                j.c();
                                if (int1 < array.length) {
                                    i.a(array[int1]);
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
                    i.a(char1);
                }
                ++n;
            }
            if (n >= length) {
                return i.toString();
            }
            continue;
        }
    }
    
    public static Vector a(final String s, final char c) {
        final boolean a = flaxchat.e.g.a;
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
        final boolean a = flaxchat.e.g.a;
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
        final boolean a = flaxchat.e.g.a;
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
        p.z = new String[] { z(z("'\u0019\u0005")), z(z("*\u0015\f")), z(z("-\u001a\u0011")), z(z("-\u0019\u0006")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'N';
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
                    c2 = 'd';
                    break;
                }
                case 1: {
                    c2 = 'T';
                    break;
                }
                case 2: {
                    c2 = 'A';
                    break;
                }
                case 3: {
                    c2 = ' ';
                    break;
                }
                default: {
                    c2 = 'N';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
