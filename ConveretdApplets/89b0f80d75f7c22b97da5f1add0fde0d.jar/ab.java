import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ab extends Panel implements ActionListener, MouseListener
{
    private final esChat a;
    TextField b;
    TextField c;
    Choice d;
    c e;
    int f;
    int g;
    boolean h;
    private static String[] z;
    
    ab(final esChat a) {
        final int m = fb.m;
        this.a = a;
        this.b = new TextField(10);
        this.c = new TextField(10);
        this.d = new Choice();
        this.e = new c(this.a, this.a.a(0, "", "", ""));
        this.h = false;
        this.setLayout(null);
        this.setSize(300, 400);
        this.f = 0;
        this.setBackground(a.g);
        this.b.setEchoChar('*');
        int n = 0;
        while (true) {
            while (true) {
                Label_0153: {
                    if (m == 0) {
                        break Label_0153;
                    }
                    this.d.add((String)a.Ub.elementAt(n));
                    ++n;
                }
                if (n < a.Ub.size()) {
                    continue;
                }
                break;
            }
            this.c.setBackground(a.g);
            this.b.setBackground(a.g);
            this.d.setBackground(a.g);
            this.c.setForeground(a.e);
            this.b.setForeground(a.e);
            this.d.setForeground(a.e);
            this.c.setBounds(110 + this.f, 10, 100, 20);
            this.b.setBounds(110 + this.f, 50, 100, 20);
            this.d.setBounds(110 + this.f, 90, 100, 20);
            this.e.setBounds(110 + this.f, 130, 100, 25);
            this.add(this.c);
            this.add(this.b);
            this.add(this.d);
            this.add(this.e);
            this.e.a(this);
            if (m == 0) {
                return;
            }
            continue;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int m = fb.m;
        this.c.setText(this.c.getText().trim());
        int n;
        final boolean b = (n = (this.a.f(this.c.getText()) ? 1 : 0)) != 0;
        Label_0210: {
            if (m == 0) {
                if (b) {
                    new t(this.a, this.a.a(29, "", "", ""), ab.z[1]);
                    return;
                }
                final ab ab = this;
                if (m != 0) {
                    break Label_0210;
                }
                n = this.c.getText().indexOf(" ");
            }
            if (n != -1) {
                new t(this.a, this.a.a(30, "", "", ""), ab.z[1]);
                return;
            }
            this.a.m = this.c.getText();
            this.a.n = this.b.getText();
            this.a.r = this.d.getSelectedItem();
            this.a.q = ab.z[0];
            this.a.Db.remove(this);
            this.a.Ob.show();
            this.a.Db.validate();
            try {
                final ab ab = this;
                ab.a.c();
                new ob(this.a).start();
            }
            catch (Exception ex) {
                final esChat a = this.a;
                ++a.fc;
                try {
                    Thread.sleep(2000L);
                }
                catch (Exception ex2) {}
                final esChat a2 = this.a;
                if (m == 0) {
                    if (a2.fc >= 4) {
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
            this.a.getAppletContext().showDocument(new URL(ab.z[3]), ab.z[2]);
        }
        catch (Exception ex) {
            this.a.a(ex.toString());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        final int m = fb.m;
        this.f = 0;
        final int n = 20;
        ab ab = this;
        Label_0055: {
            if (m == 0) {
                if (this.getParent() != null) {
                    ab = this;
                    if (m != 0) {
                        break Label_0055;
                    }
                    if (this.getParent().getSize() != null) {
                        this.g = this.getParent().getSize().width;
                    }
                }
                ab = this;
            }
        }
        final int g = ab.g;
        final int n2 = 100;
        int width = 0;
        Label_0206: {
            if (m == 0) {
                if (g <= n2) {
                    return;
                }
                this.f = this.g / 2 - 100;
                final int x;
                width = (x = this.d.getBounds().x);
                if (m != 0) {
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
            width = this.a.z.getWidth(this);
        }
        graphics.drawImage(this.a.z, this.g / 2 - width / 2, 0, this);
        this.invalidate();
        this.validate();
        this.doLayout();
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "_\f\u001b0~D\r\u0017vhS\nV}e[".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '6';
                    break;
                }
                case 1: {
                    c2 = '~';
                    break;
                }
                case 2: {
                    c2 = 'x';
                    break;
                }
                case 3: {
                    c2 = '\u001e';
                    break;
                }
                default: {
                    c2 = '\n';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "~\u001f\f\u007f".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '6';
                    break;
                }
                case 1: {
                    c4 = '~';
                    break;
                }
                case 2: {
                    c4 = 'x';
                    break;
                }
                case 3: {
                    c4 = '\u001e';
                    break;
                }
                default: {
                    c4 = '\n';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "i\u001c\u0014\u007fd]".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '6';
                    break;
                }
                case 1: {
                    c6 = '~';
                    break;
                }
                case 2: {
                    c6 = 'x';
                    break;
                }
                case 3: {
                    c6 = '\u001e';
                    break;
                }
                default: {
                    c6 = '\n';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "^\n\fn0\u0019Q\u000fi}\u0018\u001b\u000eloX\r\u0017x~\u0018\u001d\u0017s".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '6';
                    break;
                }
                case 1: {
                    c8 = '~';
                    break;
                }
                case 2: {
                    c8 = 'x';
                    break;
                }
                case 3: {
                    c8 = '\u001e';
                    break;
                }
                default: {
                    c8 = '\n';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        ab.z = z;
    }
}
