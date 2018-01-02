import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.List;
import java.awt.event.ComponentListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class v extends Panel implements ComponentListener
{
    private final esChat a;
    public w b;
    public List c;
    public StringBuffer d;
    public String e;
    public String f;
    public boolean g;
    y h;
    TextArea i;
    s j;
    int k;
    boolean l;
    int m;
    eb n;
    d o;
    Label p;
    Label q;
    String r;
    String s;
    String t;
    boolean u;
    Panel v;
    Panel w;
    public Panel x;
    private static String[] z;
    
    v(final esChat a, final String s) {
        final int m = fb.m;
        this.a = a;
        this.i = new TextArea("", 0, 0, 1);
        this.l = false;
        this.addComponentListener(this);
        this.g = false;
        this.m = -1;
        this.c = null;
        this.k = 0;
        this.r = s;
        this.t = s;
        this.u = false;
        this.setLayout(new BorderLayout());
        this.setBackground(a.h);
        final Panel panel = new Panel(new BorderLayout(4, 4));
        a.getClass();
        this.o = new d(a, 20, false);
        this.p = new Label();
        this.w = new Panel(new BorderLayout());
        a.getClass();
        this.n = new eb(a, s);
        this.n.c.setFont(new Font(a.bb, a.d, a.eb));
        this.n.c.setBackground(a.h);
        a.getClass();
        (this.h = new y(a, this.n, false)).setBackground(a.h);
        this.h.setFont(new Font(a.bb, a.d, a.eb));
        this.i.setFont(new Font(a.bb, a.d, a.eb));
        this.i.setBackground(a.h);
        this.w.add(this.h, v.z[3]);
        this.add(this.w, v.z[3]);
        a.getClass();
        (this.b = new w(a)).setFont(new Font(a.bb, a.d, a.eb));
        this.x = new Panel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.x.setLayout(layout);
        gridBagConstraints.anchor = 11;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q = new Label(), gridBagConstraints);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.b, gridBagConstraints);
        this.x.add(this.b);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 15;
        Panel v = null;
        Label_0658: {
            Label_0636: {
                final boolean equalsIgnoreCase;
                Label_0619: {
                    Label_0601: {
                        if (m == 0) {
                            if (!a.R.equalsIgnoreCase(v.z[4])) {
                                equalsIgnoreCase = a.R.equalsIgnoreCase(v.z[7]);
                                if (m != 0) {
                                    break Label_0619;
                                }
                                if (!equalsIgnoreCase) {
                                    break Label_0601;
                                }
                            }
                            this.add(this.x, v.z[5]);
                        }
                        if (m == 0) {
                            break Label_0636;
                        }
                    }
                    v = a;
                    if (m != 0) {
                        break Label_0658;
                    }
                    a.R.equalsIgnoreCase(v.z[8]);
                }
                if (equalsIgnoreCase) {
                    this.add(this.x, v.z[6]);
                }
            }
            this.v = new Panel(new BorderLayout());
            v = this.v;
        }
        v.add(this.n, v.z[3]);
        this.add(this.v, v.z[9]);
        this.h.b.addMouseListener(new k(this));
    }
    
    static esChat a(final v v) {
        return v.a;
    }
    
    public void a() {
        v v = this;
        if (fb.m == 0) {
            if (this.u) {
                return;
            }
            v = this;
        }
        v.a.a(v.z[1] + this.r, 1);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.v = this.getSize();
        this.n.a();
        this.a.l = true;
        this.a.m = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void b() {
        this.u = true;
    }
    
    public void c() {
        (this.j = new s(this, v.z[0])).setSize(600, 450);
        this.j.pack();
        this.j.setVisible(true);
        this.j.toFront();
    }
    
    public void a(String substring) {
        final int m = fb.m;
        final String s = substring;
        Label_0026: {
            if (m == 0) {
                if (!s.startsWith(v.z[2])) {
                    break Label_0026;
                }
                substring.substring(1);
            }
            substring = s;
        }
        final db b = this.a.Tb.b;
        Label_0080: {
            if (m == 0) {
                if (b.e() == this) {
                    break Label_0080;
                }
                final db b2 = this.a.Tb.b;
            }
            b.a(this.a.Tb.b.a(this.r), Color.red);
        }
        final String s2 = substring;
        Label_0136: {
            if (m == 0) {
                if (s2.indexOf(v.z[2]) <= -1) {
                    break Label_0136;
                }
                substring.substring(0, substring.indexOf(v.z[2]));
            }
            this.h.a(s2);
            substring = substring.substring(substring.indexOf(v.z[2]) + 3);
        }
        this.h.a(substring);
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "G&ed(l4\u007f".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0005';
                    break;
                }
                case 1: {
                    c2 = 'G';
                    break;
                }
                case 2: {
                    c2 = '\u000b';
                    break;
                }
                case 3: {
                    c2 = 'D';
                    break;
                }
                default: {
                    c2 = 'd';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "U\u0006Y\u0010D".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0005';
                    break;
                }
                case 1: {
                    c4 = 'G';
                    break;
                }
                case 2: {
                    c4 = '\u000b';
                    break;
                }
                case 3: {
                    c4 = 'D';
                    break;
                }
                default: {
                    c4 = 'd';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "Y)".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0005';
                    break;
                }
                case 1: {
                    c6 = 'G';
                    break;
                }
                case 2: {
                    c6 = '\u000b';
                    break;
                }
                case 3: {
                    c6 = 'D';
                    break;
                }
                default: {
                    c6 = 'd';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "F\"e0\u0001w".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0005';
                    break;
                }
                case 1: {
                    c8 = 'G';
                    break;
                }
                case 2: {
                    c8 = '\u000b';
                    break;
                }
                case 3: {
                    c8 = 'D';
                    break;
                }
                default: {
                    c8 = 'd';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "J)".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0005';
                    break;
                }
                case 1: {
                    c10 = 'G';
                    break;
                }
                case 2: {
                    c10 = '\u000b';
                    break;
                }
                case 3: {
                    c10 = 'D';
                    break;
                }
                default: {
                    c10 = 'd';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "@&x0".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0005';
                    break;
                }
                case 1: {
                    c12 = 'G';
                    break;
                }
                case 2: {
                    c12 = '\u000b';
                    break;
                }
                case 3: {
                    c12 = 'D';
                    break;
                }
                default: {
                    c12 = 'd';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "R\"x0".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0005';
                    break;
                }
                case 1: {
                    c14 = 'G';
                    break;
                }
                case 2: {
                    c14 = '\u000b';
                    break;
                }
                case 3: {
                    c14 = 'D';
                    break;
                }
                default: {
                    c14 = 'd';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "W.l,\u0010".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0005';
                    break;
                }
                case 1: {
                    c16 = 'G';
                    break;
                }
                case 2: {
                    c16 = '\u000b';
                    break;
                }
                case 3: {
                    c16 = 'D';
                    break;
                }
                default: {
                    c16 = 'd';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "I\"m0".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0005';
                    break;
                }
                case 1: {
                    c18 = 'G';
                    break;
                }
                case 2: {
                    c18 = '\u000b';
                    break;
                }
                case 3: {
                    c18 = 'D';
                    break;
                }
                default: {
                    c18 = 'd';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "V(~0\f".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0005';
                    break;
                }
                case 1: {
                    c20 = 'G';
                    break;
                }
                case 2: {
                    c20 = '\u000b';
                    break;
                }
                case 3: {
                    c20 = 'D';
                    break;
                }
                default: {
                    c20 = 'd';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        v.z = z;
    }
}
