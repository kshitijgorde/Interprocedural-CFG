import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class bb extends Panel implements ActionListener, MouseListener
{
    private final esChat a;
    d b;
    d c;
    Choice d;
    c e;
    int f;
    int g;
    boolean h;
    private static String[] z;
    
    bb(final esChat a) {
        final boolean r = d.r;
        this.a = a;
        this.b = new d(this.a, "", 30);
        this.c = new d(this.a, "", 30);
        this.d = new Choice();
        this.e = new c(this.a, this.a.a(0, "", "", ""));
        this.h = false;
        this.setLayout(null);
        this.setSize(300, 400);
        this.f = 0;
        this.setBackground(a.h);
        int n = 0;
        while (true) {
            while (true) {
                Label_0156: {
                    if (!r) {
                        break Label_0156;
                    }
                    this.d.add((String)a.fc.elementAt(n));
                    ++n;
                }
                if (n < a.fc.size()) {
                    continue;
                }
                break;
            }
            this.c.setBackground(a.h);
            this.b.setBackground(a.h);
            this.d.setBackground(a.h);
            this.c.setForeground(a.f);
            this.b.setForeground(a.f);
            this.d.setForeground(a.f);
            this.c.setBounds(110 + this.f, 10, 100, 20);
            this.b.setBounds(110 + this.f, 50, 100, 20);
            this.d.setBounds(110 + this.f, 90, 100, 20);
            this.e.setBounds(110 + this.f, 130, 100, 25);
            this.add(this.c);
            this.add(this.b);
            this.add(this.d);
            this.add(this.e);
            this.e.a(this);
            if (!r) {
                return;
            }
            continue;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean r = d.r;
        this.c.a(this.c.b().trim());
        int n;
        final boolean b = (n = (this.a.f(this.c.b()) ? 1 : 0)) != 0;
        Label_0210: {
            if (!r) {
                if (b) {
                    new u(this.a, this.a.a(29, "", "", ""), bb.z[3]);
                    return;
                }
                final bb bb = this;
                if (r) {
                    break Label_0210;
                }
                n = this.c.b().indexOf(" ");
            }
            if (n != -1) {
                new u(this.a, this.a.a(30, "", "", ""), bb.z[3]);
                return;
            }
            this.a.n = this.c.b();
            this.a.o = this.b.b();
            this.a.s = this.d.getSelectedItem();
            this.a.r = bb.z[2];
            this.a.Ob.remove(this);
            this.a.Zb.show();
            this.a.Ob.validate();
            try {
                final bb bb = this;
                bb.a.c();
                new qb(this.a).start();
            }
            catch (Exception ex) {
                final esChat a = this.a;
                ++a.qc;
                try {
                    Thread.sleep(2000L);
                }
                catch (Exception ex2) {}
                final esChat a2 = this.a;
                if (!r) {
                    if (a2.qc >= 4) {
                        return;
                    }
                    final esChat a3 = this.a;
                }
                a2.c();
            }
        }
    }
    
    public void addNotify() {
        super.addNotify();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        try {
            this.a.getAppletContext().showDocument(new URL(bb.z[0]), bb.z[1]);
        }
        catch (Exception ex) {
            this.a.a(ex.toString());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        final boolean r = d.r;
        this.f = 0;
        final int n = 20;
        bb bb = this;
        Label_0055: {
            if (!r) {
                if (this.getParent() != null) {
                    bb = this;
                    if (r) {
                        break Label_0055;
                    }
                    if (this.getParent().getSize() != null) {
                        this.g = this.getParent().getSize().width;
                    }
                }
                bb = this;
            }
        }
        final int g = bb.g;
        final int n2 = 100;
        int width = 0;
        Label_0206: {
            if (!r) {
                if (g <= n2) {
                    return;
                }
                this.f = this.g / 2 - 100;
                final int x;
                width = (x = this.d.getBounds().x);
                if (r) {
                    break Label_0206;
                }
                final int n3 = 110 + this.f;
            }
            if (g != n2) {
                this.c.setBounds(110 + this.f, 10 + n, 100, 20);
                this.b.setBounds(110 + this.f, 50 + n, 100, 20);
                this.d.setBounds(110 + this.f, 90 + n, 100, 20);
                this.e.setBounds(110 + this.f, 130 + n, 100, 25);
            }
            width = this.a.E.getWidth(this);
        }
        graphics.drawImage(this.a.E, this.g / 2 - width / 2, 0, this);
        this.invalidate();
        this.validate();
        this.doLayout();
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "P'>\u0005$\u0017|=\u0002i\u00166<\u0007{V %\u0013j\u00160%\u0018".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '8';
                    break;
                }
                case 1: {
                    c2 = 'S';
                    break;
                }
                case 2: {
                    c2 = 'J';
                    break;
                }
                case 3: {
                    c2 = 'u';
                    break;
                }
                default: {
                    c2 = '\u001e';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "g1&\u0014pS".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '8';
                    break;
                }
                case 1: {
                    c4 = 'S';
                    break;
                }
                case 2: {
                    c4 = 'J';
                    break;
                }
                case 3: {
                    c4 = 'u';
                    break;
                }
                default: {
                    c4 = '\u001e';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "Q!)[s]8+\u001bgY#d\u0016qU".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '8';
                    break;
                }
                case 1: {
                    c6 = 'S';
                    break;
                }
                case 2: {
                    c6 = 'J';
                    break;
                }
                case 3: {
                    c6 = 'u';
                    break;
                }
                default: {
                    c6 = '\u001e';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "p2>\u0014".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '8';
                    break;
                }
                case 1: {
                    c8 = 'S';
                    break;
                }
                case 2: {
                    c8 = 'J';
                    break;
                }
                case 3: {
                    c8 = 'u';
                    break;
                }
                default: {
                    c8 = '\u001e';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        bb.z = z;
    }
}
