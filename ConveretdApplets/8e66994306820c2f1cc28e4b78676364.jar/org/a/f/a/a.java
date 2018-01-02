// 
// Decompiled by Procyon v0.5.30
// 

package org.a.f.a;

import org.a.f.c;
import java.util.Hashtable;
import org.a.f.d;

public class a extends d
{
    private static String[] a;
    private static Hashtable b;
    private static String z;
    
    public static byte[] a(final byte[] array) {
        final StringBuffer sb = new StringBuffer(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            int n = array[i];
            if (n < 0) {
                n += 256;
            }
            sb.append(org.a.f.a.a.a[n]);
        }
        return sb.toString().getBytes();
    }
    
    public static byte[] b(final byte[] array) throws c {
        final byte[] array2 = new byte[array.length / 2];
        int n = 0;
        for (int i = 0; i < array.length; i += 2) {
            final StringBuffer sb = new StringBuffer(2);
            sb.append((char)array[i]);
            sb.append((char)array[i + 1]);
            final Byte b = org.a.f.a.a.b.get(sb.toString());
            if (b == null) {
                throw new c(org.a.f.a.a.z + sb.toString() + "'");
            }
            array2[n] = b;
            ++n;
        }
        return array2;
    }
    
    protected static char[] a() {
        return new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    private static void b() {
        final char[] a = a();
        int n = 0;
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                final StringBuffer sb = new StringBuffer(2);
                sb.append(a[i]);
                sb.append(a[j]);
                org.a.f.a.a.a[n] = sb.toString();
                ++n;
            }
        }
    }
    
    private static void c() {
        for (int i = 0; i < org.a.f.a.a.a.length; ++i) {
            byte b;
            if (i > 127) {
                b = (byte)(i - 256);
            }
            else {
                b = (byte)i;
            }
            org.a.f.a.a.b.put(org.a.f.a.a.a[i], new Byte(b));
        }
    }
    
    static {
        final char[] charArray = "(\f^\u0002\u000e\u0015\u0012^LI\u001e\u001dOGI\u001d\u0016IC\u0010\\\u0012ZN\u001c\u0019DRQI\u0012\u000bO\u0002\u0001\u0019\u001c\u001bG\u0007\u001f\u000b_G\rFD\u001c".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '|';
                    break;
                }
                case 1: {
                    c2 = 'd';
                    break;
                }
                case 2: {
                    c2 = ';';
                    break;
                }
                case 3: {
                    c2 = '\"';
                    break;
                }
                default: {
                    c2 = 'i';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        org.a.f.a.a.z = new String(charArray).intern();
        org.a.f.a.a.a = new String[256];
        org.a.f.a.a.b = new Hashtable();
        b();
        c();
    }
}
