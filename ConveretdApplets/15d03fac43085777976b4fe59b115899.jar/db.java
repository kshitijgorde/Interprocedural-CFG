import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class db extends Panel implements ActionListener, ComponentListener
{
    private final esChat a;
    fb b;
    d c;
    z d;
    String e;
    boolean f;
    private static String[] z;
    
    db(final esChat a, final String e) {
        this.a = a;
        this.c = new d(this.a, "", 30);
        this.f = false;
        this.setLayout(new BorderLayout());
        this.addComponentListener(this);
        this.e = e;
        this.setBackground(a.h);
        a.getClass();
        this.b = new fb(a, db.z[1]);
        this.b.c.setFont(new Font(a.eb, a.d, a.hb));
        this.b.c.setBackground(a.h);
        a.getClass();
        this.add(this.d = new z(a, this.b, false), db.z[2]);
        this.add(this.b, db.z[0]);
        this.d.b.addMouseListener(new n(this));
    }
    
    static esChat a(final db db) {
        return db.a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.w = this.getSize();
        this.b.a();
        this.a.l = true;
        this.a.m = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "2\u0019VA\u0016".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'a';
                    break;
                }
                case 1: {
                    c2 = 'v';
                    break;
                }
                case 2: {
                    c2 = '#';
                    break;
                }
                case 3: {
                    c2 = '5';
                    break;
                }
                default: {
                    c2 = '~';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "2\u0002BA\u000b\u0012".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'a';
                    break;
                }
                case 1: {
                    c4 = 'v';
                    break;
                }
                case 2: {
                    c4 = '#';
                    break;
                }
                case 3: {
                    c4 = '5';
                    break;
                }
                default: {
                    c4 = '~';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\"\u0013MA\u001b\u0013".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'a';
                    break;
                }
                case 1: {
                    c6 = 'v';
                    break;
                }
                case 2: {
                    c6 = '#';
                    break;
                }
                case 3: {
                    c6 = '5';
                    break;
                }
                default: {
                    c6 = '~';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        db.z = z;
    }
}
