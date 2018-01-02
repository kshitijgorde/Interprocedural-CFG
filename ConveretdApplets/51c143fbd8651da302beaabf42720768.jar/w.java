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

class w extends Panel implements ComponentListener
{
    private final esChat a;
    public x b;
    public List c;
    public StringBuffer d;
    public String e;
    public String f;
    public boolean g;
    z h;
    TextArea i;
    t j;
    int k;
    boolean l;
    int m;
    fb n;
    e o;
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
    
    w(final esChat a, final String s) {
        final boolean r = d.r;
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
        this.o = new e(a, 20, false);
        this.p = new Label();
        this.w = new Panel(new BorderLayout());
        a.getClass();
        this.n = new fb(a, s);
        this.n.c.setFont(new Font(a.eb, a.d, a.hb));
        this.n.c.setBackground(a.h);
        a.getClass();
        (this.h = new z(a, this.n, false)).setBackground(a.h);
        this.h.setFont(new Font(a.eb, a.d, a.hb));
        this.i.setFont(new Font(a.eb, a.d, a.hb));
        this.i.setBackground(a.h);
        this.w.add(this.h, w.z[9]);
        this.add(this.w, w.z[9]);
        a.getClass();
        (this.b = new x(a)).setFont(new Font(a.eb, a.d, a.hb));
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
        Label_0660: {
            Label_0638: {
                final boolean equalsIgnoreCase;
                Label_0621: {
                    Label_0604: {
                        if (!r) {
                            if (!a.V.equalsIgnoreCase(w.z[6])) {
                                equalsIgnoreCase = a.V.equalsIgnoreCase(w.z[3]);
                                if (r) {
                                    break Label_0621;
                                }
                                if (!equalsIgnoreCase) {
                                    break Label_0604;
                                }
                            }
                            this.add(this.x, w.z[8]);
                        }
                        if (!r) {
                            break Label_0638;
                        }
                    }
                    v = a;
                    if (r) {
                        break Label_0660;
                    }
                    a.V.equalsIgnoreCase(w.z[4]);
                }
                if (equalsIgnoreCase) {
                    this.add(this.x, w.z[7]);
                }
            }
            this.v = new Panel(new BorderLayout());
            v = this.v;
        }
        v.add(this.n, w.z[9]);
        this.add(this.v, w.z[5]);
        this.h.b.addMouseListener(new l(this));
    }
    
    static esChat a(final w w) {
        return w.a;
    }
    
    public void a() {
        w w = this;
        if (!d.r) {
            if (this.u) {
                return;
            }
            w = this;
        }
        w.a.a(w.z[2] + this.r, 1);
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.w = this.getSize();
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
        (this.j = new t(this, w.z[1])).setSize(600, 450);
        this.j.pack();
        this.j.setVisible(true);
        this.j.toFront();
    }
    
    public void a(String substring) {
        final boolean r = d.r;
        final String s = substring;
        Label_0026: {
            if (!r) {
                if (!s.startsWith(w.z[0])) {
                    break Label_0026;
                }
                substring.substring(1);
            }
            substring = s;
        }
        final eb b = this.a.Zb.b;
        Label_0080: {
            if (!r) {
                if (b.e() == this) {
                    break Label_0080;
                }
                final eb b2 = this.a.Zb.b;
            }
            b.a(this.a.Zb.b.a(this.r), Color.red);
        }
        final String s2 = substring;
        Label_0136: {
            if (!r) {
                if (s2.indexOf(w.z[0]) <= -1) {
                    break Label_0136;
                }
                substring.substring(0, substring.indexOf(w.z[0]));
            }
            this.h.a(s2);
            substring = substring.substring(substring.indexOf(w.z[0]) + 3);
        }
        this.h.a(substring);
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "[\u0005".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = 'k';
                    break;
                }
                case 2: {
                    c2 = 'n';
                    break;
                }
                case 3: {
                    c2 = 'G';
                    break;
                }
                default: {
                    c2 = '\u0019';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "E\n\u0000gUn\u0018\u001a".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0007';
                    break;
                }
                case 1: {
                    c4 = 'k';
                    break;
                }
                case 2: {
                    c4 = 'n';
                    break;
                }
                case 3: {
                    c4 = 'G';
                    break;
                }
                default: {
                    c4 = '\u0019';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "W*<\u00139".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0007';
                    break;
                }
                case 1: {
                    c6 = 'k';
                    break;
                }
                case 2: {
                    c6 = 'n';
                    break;
                }
                case 3: {
                    c6 = 'G';
                    break;
                }
                default: {
                    c6 = '\u0019';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "U\u0002\t/m".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0007';
                    break;
                }
                case 1: {
                    c8 = 'k';
                    break;
                }
                case 2: {
                    c8 = 'n';
                    break;
                }
                case 3: {
                    c8 = 'G';
                    break;
                }
                default: {
                    c8 = '\u0019';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "K\u000e\b3".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0007';
                    break;
                }
                case 1: {
                    c10 = 'k';
                    break;
                }
                case 2: {
                    c10 = 'n';
                    break;
                }
                case 3: {
                    c10 = 'G';
                    break;
                }
                default: {
                    c10 = '\u0019';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "T\u0004\u001b3q".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0007';
                    break;
                }
                case 1: {
                    c12 = 'k';
                    break;
                }
                case 2: {
                    c12 = 'n';
                    break;
                }
                case 3: {
                    c12 = 'G';
                    break;
                }
                default: {
                    c12 = '\u0019';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "H\u0005".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0007';
                    break;
                }
                case 1: {
                    c14 = 'k';
                    break;
                }
                case 2: {
                    c14 = 'n';
                    break;
                }
                case 3: {
                    c14 = 'G';
                    break;
                }
                default: {
                    c14 = '\u0019';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "P\u000e\u001d3".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0007';
                    break;
                }
                case 1: {
                    c16 = 'k';
                    break;
                }
                case 2: {
                    c16 = 'n';
                    break;
                }
                case 3: {
                    c16 = 'G';
                    break;
                }
                default: {
                    c16 = '\u0019';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "B\n\u001d3".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0007';
                    break;
                }
                case 1: {
                    c18 = 'k';
                    break;
                }
                case 2: {
                    c18 = 'n';
                    break;
                }
                case 3: {
                    c18 = 'G';
                    break;
                }
                default: {
                    c18 = '\u0019';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "D\u000e\u00003|u".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0007';
                    break;
                }
                case 1: {
                    c20 = 'k';
                    break;
                }
                case 2: {
                    c20 = 'n';
                    break;
                }
                case 3: {
                    c20 = 'G';
                    break;
                }
                default: {
                    c20 = '\u0019';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        w.z = z;
    }
}
