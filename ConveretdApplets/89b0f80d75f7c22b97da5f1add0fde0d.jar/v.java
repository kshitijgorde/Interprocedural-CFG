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
        this.setBackground(a.g);
        final Panel panel = new Panel(new BorderLayout(4, 4));
        a.getClass();
        this.o = new d(a, 20, false);
        this.p = new Label();
        this.w = new Panel(new BorderLayout());
        a.getClass();
        this.n = new eb(a, s);
        this.n.c.setFont(new Font(a.Y, 0, a.bb));
        this.n.c.setBackground(a.g);
        a.getClass();
        (this.h = new y(a, this.n, false)).setBackground(a.g);
        this.h.setFont(new Font(a.Y, 0, a.bb));
        this.i.setFont(new Font(a.Y, 0, a.bb));
        this.i.setBackground(a.g);
        this.w.add(this.h, v.z[6]);
        this.add(this.w, v.z[6]);
        a.getClass();
        (this.b = new w(a)).setFont(new Font(a.Y, 0, a.bb));
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
        Label_0646: {
            Label_0624: {
                final boolean equalsIgnoreCase;
                Label_0608: {
                    Label_0591: {
                        if (m == 0) {
                            if (!a.O.equalsIgnoreCase(v.z[7])) {
                                equalsIgnoreCase = a.O.equalsIgnoreCase(v.z[3]);
                                if (m != 0) {
                                    break Label_0608;
                                }
                                if (!equalsIgnoreCase) {
                                    break Label_0591;
                                }
                            }
                            this.add(this.x, v.z[4]);
                        }
                        if (m == 0) {
                            break Label_0624;
                        }
                    }
                    v = a;
                    if (m != 0) {
                        break Label_0646;
                    }
                    a.O.equalsIgnoreCase(v.z[2]);
                }
                if (equalsIgnoreCase) {
                    this.add(this.x, v.z[5]);
                }
            }
            this.v = new Panel(new BorderLayout());
            v = this.v;
        }
        v.add(this.n, v.z[6]);
        this.add(this.v, v.z[8]);
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
        v.a.a(v.z[0] + this.r, 1);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.u = this.getSize();
        this.n.a();
        this.a.k = true;
        this.a.l = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void b() {
        this.u = true;
    }
    
    public void c() {
        (this.j = new s(this, v.z[9])).setSize(600, 450);
        this.j.pack();
        this.j.setVisible(true);
        this.j.toFront();
    }
    
    public void a(String substring) {
        final int m = fb.m;
        final String s = substring;
        Label_0026: {
            if (m == 0) {
                if (!s.startsWith(v.z[1])) {
                    break Label_0026;
                }
                substring.substring(1);
            }
            substring = s;
        }
        final db b = this.a.Ob.b;
        Label_0080: {
            if (m == 0) {
                if (b.e() == this) {
                    break Label_0080;
                }
                final db b2 = this.a.Ob.b;
            }
            b.a(this.a.Ob.b.a(this.r), Color.red);
        }
        final String s2 = substring;
        Label_0136: {
            if (m == 0) {
                if (s2.indexOf(v.z[1]) <= -1) {
                    break Label_0136;
                }
                substring.substring(0, substring.indexOf(v.z[1]));
            }
            this.h.a(s2);
            substring = substring.substring(substring.indexOf(v.z[1]) + 3);
        }
        this.h.a(substring);
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "*3C\u001b1".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'z';
                    break;
                }
                case 1: {
                    c2 = 'r';
                    break;
                }
                case 2: {
                    c2 = '\u0011';
                    break;
                }
                case 3: {
                    c2 = 'O';
                    break;
                }
                default: {
                    c2 = '\u0011';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "&\u001c".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'z';
                    break;
                }
                case 1: {
                    c4 = 'r';
                    break;
                }
                case 2: {
                    c4 = '\u0011';
                    break;
                }
                case 3: {
                    c4 = 'O';
                    break;
                }
                default: {
                    c4 = '\u0011';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "6\u0017w;".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'z';
                    break;
                }
                case 1: {
                    c6 = 'r';
                    break;
                }
                case 2: {
                    c6 = '\u0011';
                    break;
                }
                case 3: {
                    c6 = 'O';
                    break;
                }
                default: {
                    c6 = '\u0011';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "(\u001bv'e".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'z';
                    break;
                }
                case 1: {
                    c8 = 'r';
                    break;
                }
                case 2: {
                    c8 = '\u0011';
                    break;
                }
                case 3: {
                    c8 = 'O';
                    break;
                }
                default: {
                    c8 = '\u0011';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "?\u0013b;".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'z';
                    break;
                }
                case 1: {
                    c10 = 'r';
                    break;
                }
                case 2: {
                    c10 = '\u0011';
                    break;
                }
                case 3: {
                    c10 = 'O';
                    break;
                }
                default: {
                    c10 = '\u0011';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "-\u0017b;".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'z';
                    break;
                }
                case 1: {
                    c12 = 'r';
                    break;
                }
                case 2: {
                    c12 = '\u0011';
                    break;
                }
                case 3: {
                    c12 = 'O';
                    break;
                }
                default: {
                    c12 = '\u0011';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "9\u0017\u007f;t\b".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'z';
                    break;
                }
                case 1: {
                    c14 = 'r';
                    break;
                }
                case 2: {
                    c14 = '\u0011';
                    break;
                }
                case 3: {
                    c14 = 'O';
                    break;
                }
                default: {
                    c14 = '\u0011';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "5\u001c".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'z';
                    break;
                }
                case 1: {
                    c16 = 'r';
                    break;
                }
                case 2: {
                    c16 = '\u0011';
                    break;
                }
                case 3: {
                    c16 = 'O';
                    break;
                }
                default: {
                    c16 = '\u0011';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = ")\u001dd;y".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'z';
                    break;
                }
                case 1: {
                    c18 = 'r';
                    break;
                }
                case 2: {
                    c18 = '\u0011';
                    break;
                }
                case 3: {
                    c18 = 'O';
                    break;
                }
                default: {
                    c18 = '\u0011';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "8\u0013\u007fo]\u0013\u0001e".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'z';
                    break;
                }
                case 1: {
                    c20 = 'r';
                    break;
                }
                case 2: {
                    c20 = '\u0011';
                    break;
                }
                case 3: {
                    c20 = 'O';
                    break;
                }
                default: {
                    c20 = '\u0011';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        v.z = z;
    }
}
