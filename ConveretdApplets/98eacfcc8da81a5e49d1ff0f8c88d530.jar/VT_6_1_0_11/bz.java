// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Vector;
import java.util.Hashtable;
import java.util.BitSet;

public final class bz
{
    private static final BitSet a;
    private static final BitSet b;
    private static final BitSet c;
    private static char[] d;
    
    static final cU[] a(final cU[] array, final int n) {
        final cU[] array2 = new cU[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final String[] a(final String[] array, final int n) {
        final String[] array2 = new String[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final byte[] a(final byte[] array, final int n) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final int[] a(final int[] array, final int n) {
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, (array.length < n) ? array.length : n);
        return array2;
    }
    
    static final Hashtable a(final Hashtable hashtable, final Object o) {
        synchronized (hashtable) {
            Hashtable hashtable2;
            if ((hashtable2 = hashtable.get(o)) == null) {
                hashtable2 = new Hashtable();
                hashtable.put(o, hashtable2);
            }
            return hashtable2;
        }
    }
    
    static final int[] a(final byte[] array) {
        final int[] array2 = { 0, 1, 0, 1, 0, 1 };
        for (int i = 0; i < array.length; ++i) {
            int n;
            for (n = i + 1; n < array.length && array[i] != array[n]; ++n) {}
            if (n < array.length) {
                if (n - i > array2[1]) {
                    array2[4] = array2[2];
                    array2[5] = array2[3];
                    array2[2] = array2[0];
                    array2[3] = array2[1];
                    array2[1] = n - (array2[0] = i);
                }
                else if (n - i > array2[3]) {
                    array2[4] = array2[2];
                    array2[5] = array2[3];
                    array2[3] = n - (array2[2] = i);
                }
                else if (n - i > array2[3]) {
                    array2[5] = n - (array2[4] = i);
                }
            }
        }
        final int[] array3 = array2;
        final int n2 = 1;
        array3[n2] += array2[0];
        final int[] array4 = array2;
        final int n3 = 3;
        array4[n3] += array2[2];
        final int[] array5 = array2;
        final int n4 = 5;
        array5[n4] += array2[4];
        return array2;
    }
    
    static final int a(final byte[] array, final int[] array2, final byte[] array3, int n, final int n2) {
        final int n3 = array2[0];
        final int n5;
        final int n4 = (n5 = array2[1]) - n3;
        final int n7;
        final int n6 = (n7 = array2[3]) - array2[2];
        final int n9;
        final int n8 = (n9 = array2[5]) - array2[4];
        while (n + array.length <= n2) {
            if (array[n5] == array3[n + n5]) {
                if (array[n3] == array3[n + n3]) {
                    boolean b = true;
                    for (int i = 0; i < array.length; ++i) {
                        if (array[i] != array3[n + i]) {
                            b = false;
                            break;
                        }
                    }
                    if (b) {
                        break;
                    }
                }
                n += n4;
            }
            else if (array[n7] == array3[n + n7]) {
                n += n6;
            }
            else if (array[n9] == array3[n + n9]) {
                n += n8;
            }
            else {
                ++n;
            }
        }
        if (n + array.length > n2) {
            return -1;
        }
        return n;
    }
    
    public static final String a(final String s, final String s2) {
        char[] charArray;
        int n;
        for (charArray = s2.toCharArray(), n = 0; n < charArray.length && s.indexOf(charArray[n]) == -1; ++n) {}
        if (n == charArray.length) {
            return s;
        }
        int length;
        char[] array = new char[(length = s.length()) << 1];
        s.getChars(0, length, array, 0);
        for (int i = 0; i < length; ++i) {
            if (s2.indexOf(array[i], 0) != -1) {
                if (length == array.length) {
                    final char[] array2 = array;
                    final int n2 = length + s.length();
                    final char[] array3 = array2;
                    final char[] array4 = new char[n2];
                    System.arraycopy(array3, 0, array4, 0, (array3.length < n2) ? array3.length : n2);
                    array = array4;
                }
                System.arraycopy(array, i, array, i + 1, length - i);
                ++length;
                array[i++] = '\\';
            }
        }
        return new String(array, 0, length);
    }
    
    public static final Vector a(String s) {
        if ((s = s) == null) {
            return null;
        }
        final char[] charArray = s.toCharArray();
        final Vector<Q> vector = new Vector<Q>();
        int n = 1;
        int n2 = -1;
        int n3 = 0;
        final int length = charArray.length;
        final int[] array = { 0 };
        while (true) {
            if (n == 0) {
                if ((n2 = a(charArray, n3)) == length) {
                    break;
                }
                if (charArray[n2] != ',') {
                    throw new dh("Bad header format: '" + s + "'\nExpected \",\" at position " + n2);
                }
            }
            n = 0;
            if ((n2 = a(charArray, n2 + 1)) == length) {
                break;
            }
            if (charArray[n2] == ',') {
                n3 = n2;
            }
            else {
                if (charArray[n2] == '=' || charArray[n2] == ';' || charArray[n2] == '\"') {
                    throw new dh("Bad header format: '" + s + "'\nEmpty element name at position " + n2);
                }
                int n4;
                for (n4 = n2 + 1; n4 < length && !Character.isWhitespace(charArray[n4]) && charArray[n4] != '=' && charArray[n4] != ',' && charArray[n4] != ';'; ++n4) {}
                final String s2 = new String(charArray, n2, n4 - n2);
                final int a;
                String a2;
                if ((a = a(charArray, n4)) < length && charArray[a] == '=') {
                    array[0] = a + 1;
                    a2 = a(charArray, array, s, true);
                    n3 = array[0];
                }
                else {
                    a2 = null;
                    n3 = a;
                }
                cU[] a3 = new cU[0];
                while ((n2 = a(charArray, n3)) != length && charArray[n2] == ';') {
                    if ((n2 = a(charArray, n2 + 1)) == length || charArray[n2] == ',') {
                        n3 = n2;
                        break;
                    }
                    if (charArray[n2] == ';') {
                        n3 = n2;
                    }
                    else {
                        if (charArray[n2] == '=' || charArray[n2] == '\"') {
                            throw new dh("Bad header format: '" + s + "'\nEmpty parameter name at position " + n2);
                        }
                        int n5;
                        for (n5 = n2 + 1; n5 < length && !Character.isWhitespace(charArray[n5]) && charArray[n5] != '=' && charArray[n5] != ',' && charArray[n5] != ';'; ++n5) {}
                        final String s3 = new String(charArray, n2, n5 - n2);
                        final int a4;
                        String a5;
                        if ((a4 = a(charArray, n5)) < length && charArray[a4] == '=') {
                            array[0] = a4 + 1;
                            a5 = a(charArray, array, s, true);
                            n3 = array[0];
                        }
                        else {
                            a5 = null;
                            n3 = a4;
                        }
                        final cU[] array2 = a3 = a(a3, a3.length + 1);
                        array2[a3.length - 1] = new cU(s3, a5);
                    }
                }
                vector.addElement(new Q(s2, a2, a3));
            }
        }
        return vector;
    }
    
    private static String a(final char[] array, final int[] array2, final String s, final boolean b) {
        final int n = array2[0];
        final int length = array.length;
        int a;
        int n2;
        String s2;
        if ((a = a(array, n)) < length && array[a] == '\"') {
            n2 = ++a;
            char[] array3 = null;
            int n3 = 0;
            int n4 = a;
            while (n2 < length && array[n2] != '\"') {
                if (array[n2] == '\\') {
                    if (b) {
                        if (array3 == null) {
                            array3 = new char[array.length];
                        }
                        System.arraycopy(array, n4, array3, n3, n2 - n4);
                        n3 += n2 - n4;
                        n4 = ++n2;
                    }
                    else {
                        ++n2;
                    }
                }
                ++n2;
            }
            if (n2 == length) {
                throw new dh("Bad header format: '" + s + "'\nClosing <\"> for quoted-string" + " starting at position " + (a - 1) + " not found");
            }
            if (array3 != null) {
                System.arraycopy(array, n4, array3, n3, n2 - n4);
                s2 = new String(array3, 0, n3 + (n2 - n4));
            }
            else {
                s2 = new String(array, a, n2 - a);
            }
            ++n2;
        }
        else {
            for (n2 = a; n2 < length && !Character.isWhitespace(array[n2]) && array[n2] != ',' && array[n2] != ';'; ++n2) {}
            s2 = new String(array, a, n2 - a);
        }
        array2[0] = n2;
        return s2;
    }
    
    public static final boolean b(final String s, final String s2) {
        return s != null && a(s).contains(new Q(s2));
    }
    
    public static final Q a(final Vector vector, final String s) {
        final int index;
        if ((index = vector.indexOf(new Q(s))) == -1) {
            return null;
        }
        return vector.elementAt(index);
    }
    
    public static final String c(final String s, final String s2) {
        final cU[] c = a(s2).firstElement().c();
        for (int i = 0; i < c.length; ++i) {
            if (c[i].a().equalsIgnoreCase(s)) {
                return c[i].b();
            }
        }
        return null;
    }
    
    public static final String a(final Vector vector) {
        final StringBuffer sb = new StringBuffer(200);
        for (int size = vector.size(), i = 0; i < size; ++i) {
            vector.elementAt(i).a(sb);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }
    
    private static int a(final char[] array, int n) {
        while (n < array.length && Character.isWhitespace(array[n])) {
            ++n;
        }
        return n;
    }
    
    static final boolean b(final String s) {
        int length;
        int n;
        for (length = s.length(), n = 0; n < length && bz.b.get(s.charAt(n)); ++n) {}
        return n < length;
    }
    
    static final String c(final String s) {
        final int length = s.length();
        final char[] array = new char[length * 3];
        int n = 0;
        for (int i = 0; i < length; ++i) {
            final char char1;
            if ((char1 = s.charAt(i)) >= '\u0080' || bz.c.get(char1)) {
                array[n++] = '%';
                array[n++] = bz.d[(char1 & '\u00f0') >>> 4];
                array[n++] = bz.d[char1 & '\u000f'];
            }
            else {
                array[n++] = char1;
            }
        }
        if (n > length) {
            return new String(array, 0, n);
        }
        return s;
    }
    
    public static final String d(final String s) {
        int length = s.length();
        final int index;
        if ((index = s.indexOf(35)) != -1) {
            length = index;
        }
        final int index2;
        if ((index2 = s.indexOf(63)) != -1 && index2 < length) {
            length = index2;
        }
        final int index3;
        if ((index3 = s.indexOf(59)) != -1 && index3 < length) {
            length = index3;
        }
        return s.substring(0, length);
    }
    
    static {
        a = new BitSet(128);
        b = new BitSet(128);
        c = new BitSet(128);
        new Object();
        new Object();
        bz.a.set(40);
        bz.a.set(41);
        bz.a.set(60);
        bz.a.set(62);
        bz.a.set(64);
        bz.a.set(44);
        bz.a.set(59);
        bz.a.set(58);
        bz.a.set(92);
        bz.a.set(34);
        bz.a.set(47);
        bz.a.set(91);
        bz.a.set(93);
        bz.a.set(63);
        bz.a.set(61);
        bz.a.set(123);
        bz.a.set(125);
        bz.a.set(32);
        bz.a.set(9);
        for (int i = 32; i < 127; ++i) {
            bz.b.set(i);
        }
        bz.b.xor(bz.a);
        for (int j = 0; j < 32; ++j) {
            bz.c.set(j);
        }
        bz.c.set(32);
        bz.c.set(60);
        bz.c.set(62);
        bz.c.set(34);
        bz.c.set(123);
        bz.c.set(125);
        bz.c.set(124);
        bz.c.set(92);
        bz.c.set(94);
        bz.c.set(126);
        bz.c.set(91);
        bz.c.set(93);
        bz.c.set(96);
        bz.c.set(127);
        bz.d = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
