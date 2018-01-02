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
        this.setBackground(a.g);
        a.getClass();
        this.b = new eb(a, cb.z[2]);
        this.b.c.setFont(new Font(a.Y, 0, a.bb));
        this.b.c.setBackground(a.g);
        a.getClass();
        this.add(this.d = new y(a, this.b, false), cb.z[0]);
        this.add(this.b, cb.z[1]);
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
        this.a.u = this.getSize();
        this.b.a();
        this.a.k = true;
        this.a.l = true;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "Zi\tZ$k".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0019';
                    break;
                }
                case 1: {
                    c2 = '\f';
                    break;
                }
                case 2: {
                    c2 = 'g';
                    break;
                }
                case 3: {
                    c2 = '.';
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
        final char[] charArray2 = "Jc\u0012Z)".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0019';
                    break;
                }
                case 1: {
                    c4 = '\f';
                    break;
                }
                case 2: {
                    c4 = 'g';
                    break;
                }
                case 3: {
                    c4 = '.';
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
        final char[] charArray3 = "Jx\u0006Z4j".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0019';
                    break;
                }
                case 1: {
                    c6 = '\f';
                    break;
                }
                case 2: {
                    c6 = 'g';
                    break;
                }
                case 3: {
                    c6 = '.';
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
