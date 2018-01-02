// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.net.MalformedURLException;
import java.awt.Font;
import java.net.URL;

public final class bm
{
    public URL a;
    public int a;
    public int b;
    public int c;
    public int d;
    public String a;
    public bu a;
    private Font a;
    public int[] a;
    public int e;
    
    public static String a(final String s, final String[] array) {
        try {
            final int length = array.length;
            int n = 0;
            int n2;
            if ((n2 = s.indexOf(37)) == -1) {
                return s;
            }
            String s2 = new String("");
            do {
                s2 = s2.concat(s.substring(n, n2));
                final char char1;
                switch (char1 = s.charAt(n2 + 1)) {
                    case '%': {
                        s2 = s2.concat("%");
                        ++n2;
                        break;
                    }
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9': {
                        final char c;
                        if ((c = (char)(char1 - '1')) < length && array[c] != null) {
                            s2 = s2.concat(array[c]);
                        }
                        ++n2;
                        break;
                    }
                    default: {
                        System.out.println("I don't know what to do with " + s);
                        return null;
                    }
                }
                n = n2 + 1;
            } while ((n2 = s.indexOf(37, n)) != -1 && n2 < s.length());
            return s2.concat(s.substring(n));
        }
        catch (Exception ex) {
            System.err.println("format = " + s);
            ex.printStackTrace();
            return null;
        }
    }
    
    public bm() {
    }
    
    public final boolean a(final int n, final int n2) {
        return this.a > n - this.b && this.a < n2;
    }
    
    private void a() {
        int n = 0;
        this.a = new int[50];
        this.e = 0;
        int index;
        while (this.e < 25 && (index = this.a.indexOf("://", n)) != -1) {
            final String a = this.a;
            final int n2 = index;
            final String s = a;
            int lastIndex;
            if ((lastIndex = a.lastIndexOf(32, n2 - 1)) == -1) {
                lastIndex = 0;
            }
            else {
                ++lastIndex;
            }
            int n3;
            if ((n3 = s.indexOf(32, n2 + 1)) == -1) {
                n3 = s.length();
            }
            final int n5;
            final int n4 = (n5 = lastIndex + (n3 << 16)) & 0xFFFF;
            final int n6 = n5 >>> 16;
            if (n4 < index && n6 > index + 3 && this.a.lastIndexOf(46, n6) > index) {
                try {
                    (this.a = new URL(this.a.substring(n4, n6)))[2 * this.e] = n4;
                    this.a[2 * this.e + 1] = n6;
                    ++this.e;
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
            }
            n = n6 + 1;
        }
        if (this.e == 0) {
            this.a = null;
        }
    }
    
    public final boolean a() {
        return this.a != null && this.a.a != null;
    }
    
    public final Font a() {
        if (this.a == null) {
            return null;
        }
        if (this.a.a == null) {
            return null;
        }
        if (this.a == null) {
            this.a = new Font(this.a.a, this.a.a, this.a.b);
        }
        return this.a;
    }
    
    public bm(final bl bl, final bu a, final String a2, final int a3) {
        this.a = a;
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.a = null;
        this.a = a3;
        this.a = a2;
        this.a = null;
        this.e = 0;
        this.a();
        this.b = bl.getFontMetrics(bl.a).stringWidth(a2);
    }
}
