import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class x extends Panel implements ActionListener, ComponentListener
{
    private final esChat a;
    public y b;
    public String c;
    public String d;
    TextArea e;
    eb f;
    boolean g;
    String h;
    public boolean i;
    public boolean j;
    private static String[] z;
    
    x(final esChat a, final String s) {
        this.a = a;
        this.d = "";
        this.e = new TextArea("", 0, 0, 1);
        this.g = false;
        this.i = false;
        this.j = false;
        this.addComponentListener(this);
        this.c = s;
        this.h = s;
        this.setLayout(new BorderLayout());
        this.setBackground(a.g);
        a.getClass();
        this.f = new eb(a, s);
        this.f.c.setFont(new Font(a.Y, 0, a.bb));
        this.f.c.setBackground(a.g);
        a.getClass();
        this.b = new y(a, this.f, false);
        this.e.setFont(new Font(a.Y, 0, a.bb));
        this.e.setBackground(a.g);
        this.add(this.b, x.z[5]);
        final Panel panel = new Panel(new BorderLayout());
        final Panel panel2 = new Panel(new BorderLayout());
        panel2.add(this.f, x.z[5]);
        panel.add(panel2, x.z[5]);
        this.add(panel, x.z[4]);
        this.b.b.addMouseListener(new l(this));
    }
    
    static esChat a(final x x) {
        return x.a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void a() {
        this.i = false;
        this.j = false;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.u = this.getSize();
        this.f.a();
        this.a.k = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void a(String substring) {
        final int m = fb.m;
        final int index = substring.indexOf(" ");
        Label_0233: {
            final String s;
            Label_0232: {
                if (m == 0) {
                    if (index > 0) {
                        this.i = true;
                        final boolean equalsIgnoreCase = this.a.Q.equalsIgnoreCase(x.z[2]);
                        x x = null;
                        Label_0210: {
                            if (m == 0) {
                                if (equalsIgnoreCase) {
                                    this.a.a(String.valueOf(this.a.gb) + " " + this.c + x.z[3] + this.a.G + " ", 1);
                                }
                                x = this;
                                if (m != 0) {
                                    break Label_0210;
                                }
                                this.a.R.equalsIgnoreCase(x.z[2]);
                            }
                            if (equalsIgnoreCase) {
                                x = this;
                                if (m != 0) {
                                    break Label_0210;
                                }
                                if (this.a.qb.length() > 0) {
                                    this.a.a(String.valueOf(this.a.gb) + " " + this.c + x.z[1] + this.a.qb + " ", 1);
                                }
                            }
                            x = this;
                        }
                        x.j = true;
                    }
                    s = substring;
                    if (m != 0) {
                        break Label_0232;
                    }
                    s.startsWith("\n");
                }
                if (index == 0) {
                    break Label_0233;
                }
                substring.substring(1);
            }
            substring = s;
        }
        final db b = this.a.Ob.b;
        Label_0287: {
            if (m == 0) {
                if (b.e() == this) {
                    break Label_0287;
                }
                final db b2 = this.a.Ob.b;
            }
            b.a(this.a.Ob.b.a(this.c), Color.red);
        }
        final String s2 = substring;
        Label_0343: {
            if (m == 0) {
                if (s2.indexOf(x.z[0]) <= -1) {
                    break Label_0343;
                }
                substring.substring(0, substring.indexOf(x.z[0]));
            }
            this.b.a(s2);
            substring = substring.substring(substring.indexOf(x.z[0]) + 3);
        }
        this.b.a(substring);
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "`v".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '<';
                    break;
                }
                case 1: {
                    c2 = '\u0018';
                    break;
                }
                case 2: {
                    c2 = 'J';
                    break;
                }
                case 3: {
                    c2 = 'j';
                    break;
                }
                default: {
                    c2 = '\u0001';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u001c\"\u00ea+Rp8".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '<';
                    break;
                }
                case 1: {
                    c4 = '\u0018';
                    break;
                }
                case 2: {
                    c4 = 'J';
                    break;
                }
                case 3: {
                    c4 = 'j';
                    break;
                }
                default: {
                    c4 = '\u0001';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "sv".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '<';
                    break;
                }
                case 1: {
                    c6 = '\u0018';
                    break;
                }
                case 2: {
                    c6 = 'J';
                    break;
                }
                case 3: {
                    c6 = 'j';
                    break;
                }
                default: {
                    c6 = '\u0001';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u001c\"\u00ea#L{8".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '<';
                    break;
                }
                case 1: {
                    c8 = '\u0018';
                    break;
                }
                case 2: {
                    c8 = 'J';
                    break;
                }
                case 3: {
                    c8 = 'j';
                    break;
                }
                default: {
                    c8 = '\u0001';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "ow?\u001ei".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '<';
                    break;
                }
                case 1: {
                    c10 = '\u0018';
                    break;
                }
                case 2: {
                    c10 = 'J';
                    break;
                }
                case 3: {
                    c10 = 'j';
                    break;
                }
                default: {
                    c10 = '\u0001';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\u007f}$\u001edN".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '<';
                    break;
                }
                case 1: {
                    c12 = '\u0018';
                    break;
                }
                case 2: {
                    c12 = 'J';
                    break;
                }
                case 3: {
                    c12 = 'j';
                    break;
                }
                default: {
                    c12 = '\u0001';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        x.z = z;
    }
}
