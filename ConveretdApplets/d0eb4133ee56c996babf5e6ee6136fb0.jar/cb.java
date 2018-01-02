import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class cb extends Panel implements ActionListener, ComponentListener
{
    private final esChat a;
    eb b;
    TextField c;
    y d;
    String e;
    boolean f;
    private static String[] z;
    
    cb(final esChat a, final String e) {
        this.a = a;
        this.c = new TextField();
        this.f = false;
        this.setLayout(new BorderLayout());
        this.addComponentListener(this);
        this.e = e;
        this.setBackground(a.h);
        a.getClass();
        this.b = new eb(a, cb.z[2]);
        this.b.c.setFont(new Font(a.bb, a.d, a.eb));
        this.b.c.setBackground(a.h);
        a.getClass();
        this.add(this.d = new y(a, this.b, false), cb.z[1]);
        this.add(this.b, cb.z[0]);
        this.d.b.addMouseListener(new m(this));
    }
    
    static esChat a(final cb cb) {
        return cb.a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.a.v = this.getSize();
        this.b.a();
        this.a.l = true;
        this.a.m = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "\u0019%C\u001e)".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'J';
                    break;
                }
                case 1: {
                    c2 = 'J';
                    break;
                }
                case 2: {
                    c2 = '6';
                    break;
                }
                case 3: {
                    c2 = 'j';
                    break;
                }
                default: {
                    c2 = 'A';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\t/X\u001e$8".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'J';
                    break;
                }
                case 1: {
                    c4 = 'J';
                    break;
                }
                case 2: {
                    c4 = '6';
                    break;
                }
                case 3: {
                    c4 = 'j';
                    break;
                }
                default: {
                    c4 = 'A';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0019>W\u001e49".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'J';
                    break;
                }
                case 1: {
                    c6 = 'J';
                    break;
                }
                case 2: {
                    c6 = '6';
                    break;
                }
                case 3: {
                    c6 = 'j';
                    break;
                }
                default: {
                    c6 = 'A';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        cb.z = z;
    }
}
