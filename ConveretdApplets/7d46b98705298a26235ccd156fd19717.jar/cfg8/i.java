// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Image;

class i
{
    protected Image[] a;
    protected Rectangle b;
    protected int[] c;
    protected int[] d;
    protected int e;
    private static String[] z;
    
    i() {
        this.a = new Image[12];
        this.b = null;
        this.c = new int[12];
        this.d = new int[12];
        this.e = 0;
    }
    
    protected Rectangle a() {
        return this.a(this.b);
    }
    
    protected Rectangle a(final Rectangle rectangle) {
        final Rectangle rectangle2 = new Rectangle(rectangle);
        if (RotationImageFilter.a == 0) {
            if (this.e == 0) {
                return rectangle2;
            }
            final Rectangle rectangle3 = rectangle2;
            rectangle3.x += this.b();
            final Rectangle rectangle4 = rectangle2;
            rectangle4.y += this.c();
            final Rectangle rectangle5 = rectangle2;
            rectangle5.width -= this.d();
        }
        final Rectangle rectangle6 = rectangle2;
        rectangle6.height -= this.e();
        return rectangle2;
    }
    
    protected int b() {
        return this.c[0];
    }
    
    protected int c() {
        return this.d[0];
    }
    
    protected int d() {
        return this.c[3] + this.c[4];
    }
    
    protected int e() {
        return this.d[0] + this.d[5];
    }
    
    protected void f() {
        final int a = RotationImageFilter.a;
        this.e = 1;
        int i = 0;
        while (i < 12) {
            this.c[i] = (this.d[i] = 1);
            ++i;
            if (a != 0) {
                break;
            }
        }
    }
    
    protected void g() {
        final int a = RotationImageFilter.a;
        this.e = 3;
        int i = 0;
        while (i < 12) {
            this.c[i] = 0;
            this.d[i] = 0;
            ++i;
            if (a != 0) {
                return;
            }
            if (a != 0) {
                break;
            }
        }
        final int[] d = this.d;
        final int n = 5;
        final int[] d2 = this.d;
        final int n2 = 6;
        final int[] d3 = this.d;
        final int n3 = 7;
        final int n4 = 2;
        d3[n3] = n4;
        d[n] = (d2[n2] = n4);
    }
    
    protected void a(final Component component) {
        final int a = RotationImageFilter.a;
        final int e = this.e;
        if (a != 0 || e == 2) {
            int i = e;
            while (i < 12) {
                i j = this;
                Label_0070: {
                    if (a == 0) {
                        if (this.a[i] == null) {
                            break Label_0070;
                        }
                        this.c[i] = this.a[i].getWidth(component);
                        j = this;
                    }
                    j.d[i] = this.a[i].getHeight(component);
                }
                ++i;
                if (a != 0) {
                    break;
                }
            }
        }
    }
    
    protected boolean a(final String s, final String s2, final a a) {
        final int a2 = RotationImageFilter.a;
        int length;
        final int n = length = s2.length();
        if (a2 == 0) {
            if (n != 0) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",", false);
                String s3 = String.valueOf(s).concat(String.valueOf('/'));
                String nextToken = null;
                Label_0116: {
                    if (a2 == 0) {
                        if (stringTokenizer.countTokens() > 1) {
                            s3 = String.valueOf(s3).concat(String.valueOf(stringTokenizer.nextToken()));
                            nextToken = stringTokenizer.nextToken();
                            if (a2 == 0) {
                                break Label_0116;
                            }
                        }
                        s3 = String.valueOf(s3).concat(String.valueOf(s2));
                    }
                    nextToken = i.z[3];
                }
                final MediaTracker mediaTracker = new MediaTracker(a);
                try {
                    this.a[0] = a.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(i.z[5]))).concat(String.valueOf(nextToken)));
                    this.a[1] = a.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(i.z[9]))).concat(String.valueOf(nextToken)));
                    this.a[2] = a.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(i.z[2]))).concat(String.valueOf(nextToken)));
                    this.a[3] = a.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(i.z[1]))).concat(String.valueOf(nextToken)));
                    this.a[4] = a.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(i.z[0]))).concat(String.valueOf(nextToken)));
                    this.a[5] = a.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(i.z[6]))).concat(String.valueOf(nextToken)));
                    this.a[6] = a.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(i.z[8]))).concat(String.valueOf(nextToken)));
                    this.a[7] = a.a(String.valueOf(String.valueOf(s3).concat(String.valueOf(i.z[4]))).concat(String.valueOf(nextToken)));
                    mediaTracker.addImage(this.a[0], 0);
                    mediaTracker.addImage(this.a[1], 1);
                    mediaTracker.addImage(this.a[2], 2);
                    mediaTracker.addImage(this.a[3], 3);
                    mediaTracker.addImage(this.a[4], 4);
                    mediaTracker.addImage(this.a[5], 5);
                    mediaTracker.addImage(this.a[6], 6);
                    mediaTracker.addImage(this.a[7], 7);
                }
                catch (Exception ex) {
                    a.c(i.z[7], ex.toString());
                }
                try {
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex2) {}
                this.e = 2;
                return true;
            }
            length = (false ? 1 : 0);
        }
        return length != 0;
    }
    
    protected void b(final Rectangle b) {
        this.b = b;
    }
    
    protected void a(final Graphics graphics, final Component component) {
        this.a(graphics, component, this.b.x, this.b.y, this.b.width, this.b.height);
    }
    
    protected void a(final Graphics graphics, final Component component, final int n, final int n2, final int n3, final int n4) {
        final int a = RotationImageFilter.a;
        int n6;
        final int n5 = n6 = this.e;
        int n8;
        final int n7 = n8 = 0;
        if (a == 0) {
            if (n5 == n7) {
                return;
            }
            final int e;
            n6 = (e = this.e);
            final boolean b;
            n8 = ((b = true) ? 1 : 0);
        }
        if (a == 0) {
            if (n5 == n7) {
                graphics.setColor(Color.black);
                graphics.drawRect(n, n2, n3 - 1, n4 - 1);
                return;
            }
            n6 = this.e;
            if (a != 0) {
                return;
            }
            n8 = 3;
        }
        if (n6 == n8) {
            graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
            return;
        }
        graphics.drawImage(this.a[0], n, n2, component);
        graphics.drawImage(this.a[1], n + this.c[0], n2, n3 - (this.c[0] + this.c[2]), this.d[1], component);
        graphics.drawImage(this.a[2], n + (n3 - this.c[2]), n2, component);
        graphics.drawImage(this.a[3], n, n2 + this.d[0], this.c[3], n4 - (this.d[0] + this.d[5]), component);
        graphics.drawImage(this.a[4], n + n3 - this.c[4], n2 + this.d[2], this.c[4], n4 - (this.d[2] + this.d[7]), component);
        graphics.drawImage(this.a[5], n, n2 + n4 - this.d[5], component);
        graphics.drawImage(this.a[6], n + this.c[5], n2 + n4 - this.d[6], n3 - (this.c[5] + this.c[7]), this.d[6], component);
        graphics.drawImage(this.a[7], n + n3 - this.c[7], n2 + n4 - this.d[7], component);
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "|s\u001f".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'S';
                            break;
                        }
                        case 1: {
                            c2 = '\u0001';
                            break;
                        }
                        case 2: {
                            c2 = '1';
                            break;
                        }
                        case 3: {
                            c2 = 'h';
                            break;
                        }
                        default: {
                            c2 = 'Q';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "|m\u001f".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'S';
                            break;
                        }
                        case 1: {
                            c4 = '\u0001';
                            break;
                        }
                        case 2: {
                            c4 = '1';
                            break;
                        }
                        case 3: {
                            c4 = 'h';
                            break;
                        }
                        default: {
                            c4 = 'Q';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "|uCF".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'S';
                            break;
                        }
                        case 1: {
                            c6 = '\u0001';
                            break;
                        }
                        case 2: {
                            c6 = '1';
                            break;
                        }
                        case 3: {
                            c6 = 'h';
                            break;
                        }
                        default: {
                            c6 = 'Q';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "4hW".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'S';
                            break;
                        }
                        case 1: {
                            c8 = '\u0001';
                            break;
                        }
                        case 2: {
                            c8 = '1';
                            break;
                        }
                        case 3: {
                            c8 = 'h';
                            break;
                        }
                        default: {
                            c8 = 'Q';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "|cCF".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'S';
                            break;
                        }
                        case 1: {
                            c10 = '\u0001';
                            break;
                        }
                        case 2: {
                            c10 = '1';
                            break;
                        }
                        case 3: {
                            c10 = 'h';
                            break;
                        }
                        default: {
                            c10 = 'Q';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "|u]F".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0677: {
                if (n22 > 1) {
                    break Label_0677;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'S';
                            break;
                        }
                        case 1: {
                            c12 = '\u0001';
                            break;
                        }
                        case 2: {
                            c12 = '1';
                            break;
                        }
                        case 3: {
                            c12 = 'h';
                            break;
                        }
                        default: {
                            c12 = 'Q';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "|c]F".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0793: {
                if (n26 > 1) {
                    break Label_0793;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'S';
                            break;
                        }
                        case 1: {
                            c14 = '\u0001';
                            break;
                        }
                        case 2: {
                            c14 = '1';
                            break;
                        }
                        case 3: {
                            c14 = 'h';
                            break;
                        }
                        default: {
                            c14 = 'Q';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u0003s^\n=6l".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0909: {
                if (n30 > 1) {
                    break Label_0909;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'S';
                            break;
                        }
                        case 1: {
                            c16 = '\u0001';
                            break;
                        }
                        case 2: {
                            c16 = '1';
                            break;
                        }
                        case 3: {
                            c16 = 'h';
                            break;
                        }
                        default: {
                            c16 = 'Q';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "|c\u001f".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1025: {
                if (n34 > 1) {
                    break Label_1025;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'S';
                            break;
                        }
                        case 1: {
                            c18 = '\u0001';
                            break;
                        }
                        case 2: {
                            c18 = '1';
                            break;
                        }
                        case 3: {
                            c18 = 'h';
                            break;
                        }
                        default: {
                            c18 = 'Q';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "|u\u001f".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1141: {
                if (n38 > 1) {
                    break Label_1141;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'S';
                            break;
                        }
                        case 1: {
                            c20 = '\u0001';
                            break;
                        }
                        case 2: {
                            c20 = '1';
                            break;
                        }
                        case 3: {
                            c20 = 'h';
                            break;
                        }
                        default: {
                            c20 = 'Q';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 <= n40) {
                z[n37] = new String(charArray10).intern();
                i.z = z;
                return;
            }
            continue;
        }
    }
}
