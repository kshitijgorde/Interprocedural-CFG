import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.util.StringTokenizer;
import java.awt.Menu;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class gb extends Panel implements ActionListener, MouseMotionListener, MouseListener
{
    private final esChat a;
    Graphics b;
    Image c;
    int d;
    int e;
    public String f;
    Rectangle[] g;
    private boolean h;
    String[] i;
    String[] j;
    String[] k;
    String[] l;
    private static String[] z;
    
    public gb(final esChat a) {
        final boolean r = d.r;
        this.a = a;
        this.f = "";
        this.h = false;
        this.setLayout(null);
        this.a();
        this.g = new Rectangle[15];
        int n = 2;
        final int n2 = 2;
        int n3 = 0;
        while (true) {
            while (true) {
                Label_0081: {
                    if (!r) {
                        break Label_0081;
                    }
                    this.g[n3] = new Rectangle(n, n2, 20, 18);
                    n += 23;
                    ++n3;
                }
                if (n3 < 15) {
                    continue;
                }
                break;
            }
            this.addMouseMotionListener(this);
            this.addMouseListener(this);
            this.b();
            this.add(a.Fb);
            this.add(a.Gb);
            this.add(a.Hb);
            this.add(a.Ib);
            this.add(a.Jb);
            this.add(a.Lb);
            a.Fb.addActionListener(this);
            a.Gb.addActionListener(this);
            a.Hb.addActionListener(this);
            a.Ib.addActionListener(this);
            a.Jb.addActionListener(this);
            a.Lb.addActionListener(this);
            if (!r) {
                return;
            }
            continue;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean r = d.r;
        final fb d = this.a.d();
        final String actionCommand = actionEvent.getActionCommand();
        final int n = (actionEvent.getSource() instanceof PopupMenu) ? 1 : 0;
        Label_0148: {
            Label_0054: {
                if (!r) {
                    if (n == 0) {
                        final boolean b = actionEvent.getSource() instanceof Menu;
                        if (r) {
                            break Label_0054;
                        }
                        if (!b) {
                            break Label_0148;
                        }
                    }
                    actionCommand.indexOf("|");
                }
            }
            if (n > 0) {
                final StringTokenizer stringTokenizer = new StringTokenizer(actionCommand, "|", true);
            Label_0093_Outer:
                while (true) {
                    Label_0129: {
                        if (!r) {
                            break Label_0129;
                        }
                        String nextToken = stringTokenizer.nextToken();
                        String s = null;
                        while (true) {
                            while (true) {
                                Label_0095: {
                                    if (!r) {
                                        break Label_0095;
                                    }
                                    nextToken.substring(1);
                                    nextToken = s;
                                }
                                if (nextToken.startsWith(" ")) {
                                    continue Label_0093_Outer;
                                }
                                break;
                            }
                            s = nextToken;
                            if (r) {
                                continue;
                            }
                            break;
                        }
                        if (!s.equals("|")) {
                            this.a.n(nextToken);
                        }
                    }
                    if (stringTokenizer.hasMoreTokens()) {
                        continue;
                    }
                    break;
                }
            }
            else {
                this.a.n(actionCommand);
            }
        }
        this.a.a(false);
        d.c.requestFocus();
    }
    
    public void addNotify() {
        super.addNotify();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(460, 22);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(460, 22);
    }
    
    public void a() {
        this.i = this.a.c(String.valueOf(this.a.N) + "/" + this.a.y + gb.z[2], gb.z[1]);
        this.j = this.a.c(String.valueOf(this.a.N) + "/" + this.a.y + gb.z[2], gb.z[1]);
        this.k = this.a.c(String.valueOf(this.a.N) + "/" + this.a.y + gb.z[2], gb.z[0]);
        this.l = this.a.c(String.valueOf(this.a.N) + "/" + this.a.y + gb.z[2], gb.z[3]);
        this.i[this.a.a(this.i)] = gb.z[4];
        this.j[this.a.a(this.j)] = gb.z[4];
        this.k[this.a.a(this.k)] = gb.z[4];
        this.l[this.a.a(this.l)] = gb.z[4];
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.d = 0;
        this.e = 0;
        this.h = false;
        this.repaint();
        mouseEvent.consume();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.f = "";
        this.d = mouseEvent.getX();
        this.e = mouseEvent.getY();
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.d = mouseEvent.getX();
        this.e = mouseEvent.getY();
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final boolean r = d.r;
        this.h = false;
        this.repaint();
        int n = 0;
    Label_0084:
        while (true) {
            while (true) {
                Label_0087: {
                    if (!r) {
                        break Label_0087;
                    }
                    gb gb = this;
                    if (!r) {
                        if (!this.g[n].contains(this.d, this.e)) {
                            break Label_0084;
                        }
                        gb = this;
                    }
                    final String s2;
                    final String s = s2 = gb.i[n];
                    if (!r) {
                        if (s2 == null) {
                            return;
                        }
                        s.substring(s.indexOf(":") + 1);
                    }
                    this.a.n(s2);
                    return;
                    ++n;
                }
                if (n < 15) {
                    continue;
                }
                break;
            }
            this.d = 0;
            this.e = 0;
            mouseEvent.consume();
            if (!r) {
                return;
            }
            continue Label_0084;
        }
    }
    
    public void paint(final Graphics graphics) {
        final boolean r = d.r;
        gb gb = this;
        gb gb2 = this;
        if (!r) {
            if (this.a.m) {
                this.c = null;
            }
            gb = this;
            gb2 = this;
        }
        final Graphics b;
        Label_0163: {
            Label_0091: {
                if (!r) {
                    if (gb2.c != null) {
                        b = this.b;
                        if (r) {
                            break Label_0163;
                        }
                        if (b != null) {
                            break Label_0091;
                        }
                    }
                    this.c = this.a.Ob.createImage(this.getSize().width, 22);
                    this.b = this.c.getGraphics();
                    gb = this;
                }
                gb.a.l = false;
            }
            this.b.drawImage(this.a.z, 0, 0, 10, 22, 0, 65, 10, 87, this);
            this.b.drawImage(this.a.z, 10, 0, this.getSize().width - 72, 22, 10, 65, 20, 87, this);
            final Graphics b2 = this.b;
        }
        b.drawImage(this.a.z, this.getSize().width - 92, 0, this.getSize().width, 22, 20, 65, 92, 87, this);
        final Component e = this.a.Zb.b.e();
        final int a = this.a.a(this.i);
        int n = 0;
        while (true) {
            while (true) {
                Label_0406: {
                    if (!r) {
                        break Label_0406;
                    }
                    gb gb3 = this;
                    Label_0403: {
                        Label_0264: {
                            if (r) {
                                break Label_0264;
                            }
                            this.g[n].contains(this.d, this.e);
                            final int n2;
                            if (n2 == 0) {
                                break Label_0403;
                            }
                            gb3 = this;
                        }
                        final String s = gb3.i[n];
                        final String substring = s.substring(s.indexOf("=") + 1);
                        final String substring2 = substring.substring(0, substring.indexOf(":"));
                        this.b.drawImage(this.a.z, this.g[n].x, this.g[n].y, this.g[n].x + 23, this.g[n].y + 18, 164, 20, 184, 35, this);
                        this.b.setColor(this.a.f);
                        this.b.drawString(substring2, 255, 15);
                    }
                    ++n;
                }
                if (n < a) {
                    continue;
                }
                break;
            }
            int n3;
            boolean b3;
            final int n2 = (b3 = ((n3 = ((e instanceof w) ? 1 : 0)) != 0)) ? 1 : 0;
            if (!r) {
                Label_0769: {
                    if (!r) {
                        if (n2 != 0) {
                            this.i = this.k;
                            final int n4 = this.a.a(this.i) - 1;
                            this.b.drawImage(this.a.z, 0, 3, n4 * 23 + 2, 18, 0, 92, n4 * 23 + 2, 107, this);
                            this.b.drawImage(this.a.z, n4 * 23 + 2, 3, n4 * 23 + 25, 18, 140, 20, 163, 35, this);
                            if (!r) {
                                break Label_0769;
                            }
                        }
                        n3 = ((b3 = (e instanceof y)) ? 1 : 0);
                    }
                    if (!r) {
                        if (b3) {
                            this.i = this.l;
                            final int n5 = this.a.a(this.i) - 1;
                            this.b.drawImage(this.a.z, 0, 3, n5 * 23 + 2, 18, 0, 112, n5 * 23 + 2, 127, this);
                            this.b.drawImage(this.a.z, n5 * 23 + 2, 3, n5 * 23 + 25, 18, 140, 20, 163, 35, this);
                            if (!r) {
                                break Label_0769;
                            }
                        }
                        this.i = this.j;
                        n3 = this.a.a(this.i) - 1;
                    }
                    final int n6 = n3;
                    this.b.drawImage(this.a.z, 0, 3, n6 * 23 + 2, 18, 0, 20, n6 * 23 + 2, 35, this);
                    this.b.drawImage(this.a.z, n6 * 23 + 2, 3, n6 * 23 + 25, 18, 140, 20, 163, 35, this);
                }
                this.b.drawString(this.f, 255, 15);
                graphics.drawImage(this.c, 0, 0, this);
                return;
            }
            continue;
        }
    }
    
    public void b() {
        this.a.Fb.removeAll();
        this.a.Gb.removeAll();
        this.a.Hb.removeAll();
        this.a.Ib.removeAll();
        this.a.Jb.removeAll();
        this.a.a(this.a.Fb, gb.z[5], this);
        this.a.a(this.a.Gb, gb.z[6], this);
        this.a.a(this.a.Hb, gb.z[8], this);
        this.a.a(this.a.Ib, gb.z[7], this);
        this.a.a(this.a.Jb, gb.z[9], this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "\u001b\u001crua=\u0018".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'X';
                    break;
                }
                case 1: {
                    c2 = 't';
                    break;
                }
                case 2: {
                    c2 = '\u0013';
                    break;
                }
                case 3: {
                    c2 = '\u001b';
                    break;
                }
                default: {
                    c2 = '\u000f';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u000b\u0000roz+".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'X';
                    break;
                }
                case 1: {
                    c4 = 't';
                    break;
                }
                case 2: {
                    c4 = '\u0013';
                    break;
                }
                case 3: {
                    c4 = '\u001b';
                    break;
                }
                default: {
                    c4 = '\u000f';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "w\u0007xrav\u0000ko".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'X';
                    break;
                }
                case 1: {
                    c6 = 't';
                    break;
                }
                case 2: {
                    c6 = '\u0013';
                    break;
                }
                case 3: {
                    c6 = '\u001b';
                    break;
                }
                default: {
                    c6 = '\u000f';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\b\u0006zmn,\u0011".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'X';
                    break;
                }
                case 1: {
                    c8 = 't';
                    break;
                }
                case 2: {
                    c8 = '\u0013';
                    break;
                }
                case 3: {
                    c8 = '\u001b';
                    break;
                }
                default: {
                    c8 = '\u000f';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "6IvhL0\u0015g! 9\u0016|n{".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'X';
                    break;
                }
                case 1: {
                    c10 = 't';
                    break;
                }
                case 2: {
                    c10 = '\u0013';
                    break;
                }
                case 3: {
                    c10 = '\u001b';
                    break;
                }
                default: {
                    c10 = '\u000f';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = ":\u0004|kz(".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'X';
                    break;
                }
                case 1: {
                    c12 = 't';
                    break;
                }
                case 2: {
                    c12 = '\u0013';
                    break;
                }
                case 3: {
                    c12 = '\u001b';
                    break;
                }
                default: {
                    c12 = '\u000f';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "5\u0004|kz(".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'X';
                    break;
                }
                case 1: {
                    c14 = 't';
                    break;
                }
                case 2: {
                    c14 = '\u0013';
                    break;
                }
                case 3: {
                    c14 = '\u001b';
                    break;
                }
                default: {
                    c14 = '\u000f';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "4\u0004|kz(".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'X';
                    break;
                }
                case 1: {
                    c16 = 't';
                    break;
                }
                case 2: {
                    c16 = '\u0013';
                    break;
                }
                case 3: {
                    c16 = '\u001b';
                    break;
                }
                default: {
                    c16 = '\u000f';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = ")\u0004|kz(".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'X';
                    break;
                }
                case 1: {
                    c18 = 't';
                    break;
                }
                case 2: {
                    c18 = '\u0013';
                    break;
                }
                case 3: {
                    c18 = '\u001b';
                    break;
                }
                default: {
                    c18 = '\u000f';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = ";\u0004|kz(".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'X';
                    break;
                }
                case 1: {
                    c20 = 't';
                    break;
                }
                case 2: {
                    c20 = '\u0013';
                    break;
                }
                case 3: {
                    c20 = '\u001b';
                    break;
                }
                default: {
                    c20 = '\u000f';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        gb.z = z;
    }
}
