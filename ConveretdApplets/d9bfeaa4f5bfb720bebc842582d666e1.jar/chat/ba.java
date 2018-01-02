// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.net.MalformedURLException;
import java.awt.Font;
import java.net.URL;

final class ba
{
    URL a;
    int a;
    int b;
    int c;
    int d;
    public String a;
    aG a;
    private Font a;
    public int[] a;
    public int e;
    
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
    
    ba(final az az, final aG a, final String a2, final int a3) {
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
        this.b = az.getFontMetrics(az.a).stringWidth(a2);
    }
}
