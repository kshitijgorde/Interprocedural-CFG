// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Graphics;
import com.easypano.tourweaver.a.e;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Image;

public class w extends s
{
    private static final long serialVersionUID = 5590886995023120737L;
    public static final String y;
    public static final String z;
    Image A;
    String B;
    boolean C;
    String D;
    String E;
    String F;
    int G;
    int H;
    int I;
    int J;
    private static String[] K;
    
    public w() {
        this.B = w.y;
        this.C = false;
        this.D = "";
        this.G = 0;
        this.H = 0;
        this.I = 0;
        this.J = 0;
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        super.processMouseEvent(mouseEvent);
        int n2;
        int id;
        final int n = id = (n2 = mouseEvent.getID());
        int n5;
        int n4;
        final int n3 = n4 = (n5 = 504);
        if (!u) {
            if (n == n3) {
                w w = this;
                if (!u) {
                    if (this.E == null) {
                        return;
                    }
                    w = this;
                }
                w.setCursor(Cursor.getPredefinedCursor(12));
                if (!u) {
                    return;
                }
            }
            final int n6;
            id = (n6 = (n2 = mouseEvent.getID()));
            final int n7;
            n4 = (n7 = (n5 = 501));
        }
        if (!u) {
            if (n == n3) {
                w w2 = this;
                if (!u) {
                    if (this.E == null) {
                        return;
                    }
                    w2 = this;
                }
                w2.b(this.E);
                if (!u) {
                    return;
                }
            }
            n2 = (id = mouseEvent.getID());
            n5 = (n4 = 502);
        }
        if (!u) {
            if (id == n4) {
                w w3 = this;
                if (!u) {
                    if (this.F == null) {
                        return;
                    }
                    w3 = this;
                }
                w3.b(this.F);
                if (!u) {
                    return;
                }
            }
            n2 = mouseEvent.getID();
            n5 = 505;
        }
        if (n2 == n5) {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void a(final Image a, final String s) {
        final boolean u = f.u;
        super.a(a, s);
        int n2;
        final int n = n2 = (s.equals(this.D) ? 1 : 0);
        if (!u) {
            if (n == 0) {
                return;
            }
            this.A = a;
            n2 = a.getWidth(this);
        }
        final int i = n2;
        final int height = a.getHeight(this);
        this.b(this.I = i, this.J = height);
        int n4;
        int equals;
        final int n3 = equals = (n4 = (this.B.equals(w.z) ? 1 : 0));
        final boolean b;
        Label_0116: {
            if (!u) {
                if (n3 == 0) {
                    b = ((n4 = (this.B.equals(w.y) ? 1 : 0)) != 0);
                    if (u) {
                        break Label_0116;
                    }
                    if (!b) {
                        this.setSize(i, height);
                    }
                }
                n4 = (this.B.equals(w.y) ? 1 : 0);
            }
        }
        w w = null;
        Label_0379: {
            final boolean c;
            Label_0368: {
                Label_0356: {
                    if (!u) {
                        if (n3 != 0) {
                            c = this.C;
                            if (u) {
                                break Label_0368;
                            }
                            if (!c) {
                                break Label_0356;
                            }
                            this.G = (this.getBounds().width - i) / 2;
                            this.H = (this.getBounds().height - height) / 2;
                            if (!u) {
                                break Label_0356;
                            }
                        }
                        n4 = (equals = (this.B.equals(com.easypano.tourweaver.b.w.z) ? 1 : 0));
                    }
                    if (!u) {
                        if (b) {
                            this.G = 0;
                            this.H = 0;
                            this.I = this.getBounds().width;
                            this.J = this.getBounds().height;
                            if (!u) {
                                break Label_0356;
                            }
                        }
                        n4 = i;
                    }
                    final double n5 = n4 / height;
                    final double n6 = this.getBounds().width / this.getBounds().height;
                    Label_0320: {
                        if (!u) {
                            if (n5 > n6) {
                                this.I = this.getBounds().width;
                                this.J = (int)(this.I / n5);
                                if (!u) {
                                    break Label_0320;
                                }
                            }
                            this.J = this.getBounds().height;
                        }
                        this.I = (int)(this.J * n5);
                    }
                    this.G = (this.getBounds().width - this.I) / 2;
                    this.H = (this.getBounds().height - this.J) / 2;
                }
                w = this;
                if (u) {
                    break Label_0379;
                }
                final boolean b2 = this.getParent() instanceof r;
            }
            if (c) {
                this.getParent().doLayout();
            }
            w = this;
        }
        w.repaint();
    }
    
    public void addAttributes(final String s, final String e) {
        final boolean u = f.u;
        super.addAttributes(s, e);
        boolean b3;
        boolean equals;
        boolean b2;
        final boolean b = b2 = (equals = (b3 = s.equals(w.K[6])));
        if (!u) {
            String s2 = null;
            Label_0045: {
                if (b) {
                    s2 = e;
                    if (u) {
                        break Label_0045;
                    }
                    if (e != null) {
                        this.B = e;
                        if (!u) {
                            return;
                        }
                    }
                }
                s2 = s;
            }
            final boolean b4;
            b2 = (b4 = (equals = (b3 = s2.equals(w.K[1]))));
        }
        if (!u) {
            if (b) {
                this.C = e.e(e);
                if (!u) {
                    return;
                }
            }
            equals = (b2 = (b3 = s.equals(w.K[0])));
        }
        if (!u) {
            if (b2) {
                this.D = e;
                if (!u) {
                    return;
                }
            }
            b3 = (equals = s.equals(w.K[3]));
        }
        w w = null;
        Label_0163: {
            if (!u) {
                if (!equals) {
                    return;
                }
                this.E = e;
                w = this;
                if (u) {
                    break Label_0163;
                }
                b3 = this.E.toLowerCase().startsWith(com.easypano.tourweaver.b.w.K[2]);
            }
            if (!b3) {
                w = this;
                if (u) {
                    break Label_0163;
                }
                if (!this.E.toLowerCase().startsWith(com.easypano.tourweaver.b.w.K[5])) {
                    return;
                }
            }
            w = this;
        }
        w.F = com.easypano.tourweaver.b.w.K[4];
    }
    
    public String n() {
        return this.B;
    }
    
    public boolean o() {
        return this.C;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.A, this.G, this.H, this.I, this.J, this);
    }
    
    public void destroy() {
        this.A = null;
    }
    
    static {
        final String[] k = new String[7];
        final int n = 0;
        final char[] charArray = "_BOhPrrRgT".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u001e';
                            break;
                        }
                        case 1: {
                            c2 = '!';
                            break;
                        }
                        case 2: {
                            c2 = ';';
                            break;
                        }
                        case 3: {
                            c2 = '\u001d';
                            break;
                        }
                        default: {
                            c2 = '1';
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
        y = new String(charArray).intern();
        final char[] charArray2 = "\\DHiwwU".toCharArray();
        int length2;
        int n6;
        final int n5 = n6 = (length2 = charArray2.length);
        int n7 = 0;
        while (true) {
            Label_0214: {
                if (n5 > 1) {
                    break Label_0214;
                }
                length2 = (n6 = n7);
                do {
                    final char c3 = charArray2[n6];
                    char c4 = '\0';
                    switch (n7 % 5) {
                        case 0: {
                            c4 = '\u001e';
                            break;
                        }
                        case 1: {
                            c4 = '!';
                            break;
                        }
                        case 2: {
                            c4 = ';';
                            break;
                        }
                        case 3: {
                            c4 = '\u001d';
                            break;
                        }
                        default: {
                            c4 = '1';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n7;
                } while (n5 == 0);
            }
            if (n5 > n7) {
                continue;
            }
            break;
        }
        z = new String(charArray2).intern();
        final char[] charArray3 = "wLZzT".toCharArray();
        int length3;
        int n9;
        final int n8 = n9 = (length3 = charArray3.length);
        int n10 = 0;
        while (true) {
            Label_0330: {
                if (n8 > 1) {
                    break Label_0330;
                }
                length3 = (n9 = n10);
                do {
                    final char c5 = charArray3[n9];
                    char c6 = '\0';
                    switch (n10 % 5) {
                        case 0: {
                            c6 = '\u001e';
                            break;
                        }
                        case 1: {
                            c6 = '!';
                            break;
                        }
                        case 2: {
                            c6 = ';';
                            break;
                        }
                        case 3: {
                            c6 = '\u001d';
                            break;
                        }
                        default: {
                            c6 = '1';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n10;
                } while (n8 == 0);
            }
            if (n8 > n10) {
                continue;
            }
            break;
        }
        k[n] = new String(charArray3).intern();
        final int n11 = 1;
        final char[] charArray4 = "wRxx_jDI".toCharArray();
        int length4;
        int n13;
        final int n12 = n13 = (length4 = charArray4.length);
        int n14 = 0;
        while (true) {
            Label_0446: {
                if (n12 > 1) {
                    break Label_0446;
                }
                length4 = (n13 = n14);
                do {
                    final char c7 = charArray4[n13];
                    char c8 = '\0';
                    switch (n14 % 5) {
                        case 0: {
                            c8 = '\u001e';
                            break;
                        }
                        case 1: {
                            c8 = '!';
                            break;
                        }
                        case 2: {
                            c8 = ';';
                            break;
                        }
                        case 3: {
                            c8 = '\u001d';
                            break;
                        }
                        default: {
                            c8 = '1';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n14;
                } while (n12 == 0);
            }
            if (n12 > n14) {
                continue;
            }
            break;
        }
        k[n11] = new String(charArray4).intern();
        final int n15 = 2;
        final char[] charArray5 = "dNTpXp".toCharArray();
        int length5;
        int n17;
        final int n16 = n17 = (length5 = charArray5.length);
        int n18 = 0;
        while (true) {
            Label_0562: {
                if (n16 > 1) {
                    break Label_0562;
                }
                length5 = (n17 = n18);
                do {
                    final char c9 = charArray5[n17];
                    char c10 = '\0';
                    switch (n18 % 5) {
                        case 0: {
                            c10 = '\u001e';
                            break;
                        }
                        case 1: {
                            c10 = '!';
                            break;
                        }
                        case 2: {
                            c10 = ';';
                            break;
                        }
                        case 3: {
                            c10 = '\u001d';
                            break;
                        }
                        default: {
                            c10 = '1';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n18;
                } while (n16 == 0);
            }
            if (n16 > n18) {
                continue;
            }
            break;
        }
        k[n15] = new String(charArray5).intern();
        final int n19 = 3;
        final char[] charArray6 = "\u007fBOt^p".toCharArray();
        int length6;
        int n21;
        final int n20 = n21 = (length6 = charArray6.length);
        int n22 = 0;
        while (true) {
            Label_0678: {
                if (n20 > 1) {
                    break Label_0678;
                }
                length6 = (n21 = n22);
                do {
                    final char c11 = charArray6[n21];
                    char c12 = '\0';
                    switch (n22 % 5) {
                        case 0: {
                            c12 = '\u001e';
                            break;
                        }
                        case 1: {
                            c12 = '!';
                            break;
                        }
                        case 2: {
                            c12 = ';';
                            break;
                        }
                        case 3: {
                            c12 = '\u001d';
                            break;
                        }
                        default: {
                            c12 = '1';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n22;
                } while (n20 == 0);
            }
            if (n20 > n22) {
                continue;
            }
            break;
        }
        k[n19] = new String(charArray6).intern();
        final int n23 = 4;
        final char[] charArray7 = "mUTm\u00197".toCharArray();
        int length7;
        int n25;
        final int n24 = n25 = (length7 = charArray7.length);
        int n26 = 0;
        while (true) {
            Label_0794: {
                if (n24 > 1) {
                    break Label_0794;
                }
                length7 = (n25 = n26);
                do {
                    final char c13 = charArray7[n25];
                    char c14 = '\0';
                    switch (n26 % 5) {
                        case 0: {
                            c14 = '\u001e';
                            break;
                        }
                        case 1: {
                            c14 = '!';
                            break;
                        }
                        case 2: {
                            c14 = ';';
                            break;
                        }
                        case 3: {
                            c14 = '\u001d';
                            break;
                        }
                        default: {
                            c14 = '1';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n26;
                } while (n24 == 0);
            }
            if (n24 > n26) {
                continue;
            }
            break;
        }
        k[n23] = new String(charArray7).intern();
        final int n27 = 5;
        final char[] charArray8 = "dNTp^kU".toCharArray();
        int length8;
        int n29;
        final int n28 = n29 = (length8 = charArray8.length);
        int n30 = 0;
        while (true) {
            Label_0910: {
                if (n28 > 1) {
                    break Label_0910;
                }
                length8 = (n29 = n30);
                do {
                    final char c15 = charArray8[n29];
                    char c16 = '\0';
                    switch (n30 % 5) {
                        case 0: {
                            c16 = '\u001e';
                            break;
                        }
                        case 1: {
                            c16 = '!';
                            break;
                        }
                        case 2: {
                            c16 = ';';
                            break;
                        }
                        case 3: {
                            c16 = '\u001d';
                            break;
                        }
                        default: {
                            c16 = '1';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n30;
                } while (n28 == 0);
            }
            if (n28 > n30) {
                continue;
            }
            break;
        }
        k[n27] = new String(charArray8).intern();
        final int n31 = 6;
        final char[] charArray9 = "mHAx|qE^".toCharArray();
        int length9;
        int n33;
        final int n32 = n33 = (length9 = charArray9.length);
        int n34 = 0;
        while (true) {
            Label_1030: {
                if (n32 > 1) {
                    break Label_1030;
                }
                length9 = (n33 = n34);
                do {
                    final char c17 = charArray9[n33];
                    char c18 = '\0';
                    switch (n34 % 5) {
                        case 0: {
                            c18 = '\u001e';
                            break;
                        }
                        case 1: {
                            c18 = '!';
                            break;
                        }
                        case 2: {
                            c18 = ';';
                            break;
                        }
                        case 3: {
                            c18 = '\u001d';
                            break;
                        }
                        default: {
                            c18 = '1';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n34;
                } while (n32 == 0);
            }
            if (n32 <= n34) {
                k[n31] = new String(charArray9).intern();
                w.K = k;
                return;
            }
            continue;
        }
    }
}
