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

public class fb extends Panel implements ActionListener, MouseMotionListener, MouseListener
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
    public static int m;
    private static String[] z;
    
    public fb(final esChat a) {
        final int m = fb.m;
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
                    if (m == 0) {
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
            this.add(a.vb);
            this.add(a.wb);
            this.add(a.xb);
            this.add(a.yb);
            this.add(a.zb);
            this.add(a.Ab);
            a.vb.addActionListener(this);
            a.wb.addActionListener(this);
            a.xb.addActionListener(this);
            a.yb.addActionListener(this);
            a.zb.addActionListener(this);
            a.Ab.addActionListener(this);
            if (m == 0) {
                return;
            }
            continue;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final int m = fb.m;
        final eb d = this.a.d();
        final String actionCommand = actionEvent.getActionCommand();
        final int n = (actionEvent.getSource() instanceof PopupMenu) ? 1 : 0;
        Label_0148: {
            Label_0054: {
                if (m == 0) {
                    if (n == 0) {
                        final boolean b = actionEvent.getSource() instanceof Menu;
                        if (m != 0) {
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
                        if (m == 0) {
                            break Label_0129;
                        }
                        String nextToken = stringTokenizer.nextToken();
                        String s = null;
                        while (true) {
                            while (true) {
                                Label_0095: {
                                    if (m == 0) {
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
                            if (m != 0) {
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
        this.i = this.a.c(String.valueOf(this.a.I) + "/" + this.a.w + "/" + fb.z[0], fb.z[4]);
        this.j = this.a.c(String.valueOf(this.a.I) + "/" + this.a.w + "/" + fb.z[0], fb.z[4]);
        this.k = this.a.c(String.valueOf(this.a.I) + "/" + this.a.w + "/" + fb.z[0], fb.z[1]);
        this.l = this.a.c(String.valueOf(this.a.I) + "/" + this.a.w + "/" + fb.z[0], fb.z[3]);
        this.i[this.a.a(this.i)] = fb.z[2];
        this.j[this.a.a(this.j)] = fb.z[2];
        this.k[this.a.a(this.k)] = fb.z[2];
        this.l[this.a.a(this.l)] = fb.z[2];
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
        final int m = fb.m;
        this.h = false;
        this.repaint();
        int n = 0;
    Label_0075:
        while (true) {
            while (true) {
                Label_0078: {
                    if (m == 0) {
                        break Label_0078;
                    }
                    fb fb = this;
                    if (m == 0) {
                        if (!this.g[n].contains(this.d, this.e)) {
                            break Label_0075;
                        }
                        fb = this;
                    }
                    final String s = fb.i[n];
                    this.a.n(s.substring(s.indexOf(":") + 1));
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
            if (m == 0) {
                return;
            }
            continue Label_0075;
        }
    }
    
    public void paint(final Graphics graphics) {
        final int m = fb.m;
        fb fb = this;
        fb fb2 = this;
        if (m == 0) {
            if (this.a.l) {
                this.c = null;
            }
            fb = this;
            fb2 = this;
        }
        final Graphics b;
        Label_0163: {
            Label_0091: {
                if (m == 0) {
                    if (fb2.c != null) {
                        b = this.b;
                        if (m != 0) {
                            break Label_0163;
                        }
                        if (b != null) {
                            break Label_0091;
                        }
                    }
                    this.c = this.a.Db.createImage(this.getSize().width, 22);
                    this.b = this.c.getGraphics();
                    fb = this;
                }
                fb.a.k = false;
            }
            this.b.drawImage(this.a.x, 0, 0, 10, 22, 0, 65, 10, 87, this);
            this.b.drawImage(this.a.x, 10, 0, this.getSize().width - 72, 22, 10, 65, 20, 87, this);
            final Graphics b2 = this.b;
        }
        b.drawImage(this.a.x, this.getSize().width - 92, 0, this.getSize().width, 22, 20, 65, 92, 87, this);
        final Component e = this.a.Ob.b.e();
        int n2;
        final int n = n2 = ((e instanceof v) ? 1 : 0);
        Label_0554: {
            if (m == 0) {
                if (n != 0) {
                    this.i = this.k;
                    final int n3 = this.a.a(this.i) - 1;
                    this.b.drawImage(this.a.x, 0, 3, n3 * 23 + 2, 18, 0, 92, n3 * 23 + 2, 108, this);
                    this.b.drawImage(this.a.x, n3 * 23 + 2, 3, n3 * 23 + 25, 18, 140, 20, 163, 35, this);
                    if (m == 0) {
                        break Label_0554;
                    }
                }
                final boolean b3;
                n2 = ((b3 = (e instanceof x)) ? 1 : 0);
            }
            if (m == 0) {
                if (n != 0) {
                    this.i = this.l;
                    final int n4 = this.a.a(this.i) - 1;
                    this.b.drawImage(this.a.x, 0, 3, n4 * 23 + 2, 18, 0, 112, n4 * 23 + 2, 127, this);
                    this.b.drawImage(this.a.x, n4 * 23 + 2, 3, n4 * 23 + 25, 18, 140, 20, 163, 35, this);
                    if (m == 0) {
                        break Label_0554;
                    }
                }
                this.i = this.j;
                n2 = this.a.a(this.i) - 1;
            }
            final int n5 = n2;
            this.b.drawImage(this.a.x, 0, 3, n5 * 23 + 2, 18, 0, 20, n5 * 23 + 2, 35, this);
            this.b.drawImage(this.a.x, n5 * 23 + 2, 3, n5 * 23 + 25, 18, 140, 20, 163, 35, this);
        }
        final int a = this.a.a(this.i);
        int n6 = 0;
    Label_0740:
        while (true) {
            while (true) {
                Label_0743: {
                    if (m == 0) {
                        break Label_0743;
                    }
                    fb fb3 = this;
                    if (m == 0) {
                        if (!this.g[n6].contains(this.d, this.e)) {
                            break Label_0740;
                        }
                        fb3 = this;
                    }
                    final String s = fb3.i[n6];
                    final String substring = s.substring(s.indexOf("=") + 1);
                    final String substring2 = substring.substring(0, substring.indexOf(":"));
                    this.b.drawImage(this.a.x, this.g[n6].x, this.g[n6].y, this.g[n6].x + 23, this.g[n6].y + 18, 164, 20, 184, 35, this);
                    this.b.setColor(this.a.e);
                    this.b.drawString(substring2, 255, 15);
                    ++n6;
                }
                if (n6 < a) {
                    continue;
                }
                break;
            }
            this.b.drawString(this.f, 255, 15);
            graphics.drawImage(this.c, 0, 0, this);
            if (m == 0) {
                return;
            }
            continue Label_0740;
        }
    }
    
    public void b() {
        this.a.vb.removeAll();
        this.a.wb.removeAll();
        this.a.xb.removeAll();
        this.a.yb.removeAll();
        this.a.zb.removeAll();
        this.a.a(this.a.vb, fb.z[7], this);
        this.a.a(this.a.wb, fb.z[6], this);
        this.a.a(this.a.xb, fb.z[9], this);
        this.a.a(this.a.yb, fb.z[8], this);
        this.a.a(this.a.zb, fb.z[5], this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "2\u007fXE~5lE".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'A';
                    break;
                }
                case 1: {
                    c2 = '\u0014';
                    break;
                }
                case 2: {
                    c2 = '1';
                    break;
                }
                case 3: {
                    c2 = '+';
                    break;
                }
                default: {
                    c2 = 'P';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u0002|PE>$x".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'A';
                    break;
                }
                case 1: {
                    c4 = '\u0014';
                    break;
                }
                case 2: {
                    c4 = '1';
                    break;
                }
                case 3: {
                    c4 = '+';
                    break;
                }
                default: {
                    c4 = 'P';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "/)TX\u0013)uE\u0011\u007f v^^$".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'A';
                    break;
                }
                case 1: {
                    c6 = '\u0014';
                    break;
                }
                case 2: {
                    c6 = '1';
                    break;
                }
                case 3: {
                    c6 = '+';
                    break;
                }
                default: {
                    c6 = 'P';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0011fX]15q".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'A';
                    break;
                }
                case 1: {
                    c8 = '\u0014';
                    break;
                }
                case 2: {
                    c8 = '1';
                    break;
                }
                case 3: {
                    c8 = '+';
                    break;
                }
                default: {
                    c8 = 'P';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\u0012`P_%2".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'A';
                    break;
                }
                case 1: {
                    c10 = '\u0014';
                    break;
                }
                case 2: {
                    c10 = '1';
                    break;
                }
                case 3: {
                    c10 = '+';
                    break;
                }
                default: {
                    c10 = 'P';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\"d^[%1".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'A';
                    break;
                }
                case 1: {
                    c12 = '\u0014';
                    break;
                }
                case 2: {
                    c12 = '1';
                    break;
                }
                case 3: {
                    c12 = '+';
                    break;
                }
                default: {
                    c12 = 'P';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = ",d^[%1".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'A';
                    break;
                }
                case 1: {
                    c14 = '\u0014';
                    break;
                }
                case 2: {
                    c14 = '1';
                    break;
                }
                case 3: {
                    c14 = '+';
                    break;
                }
                default: {
                    c14 = 'P';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "#d^[%1".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'A';
                    break;
                }
                case 1: {
                    c16 = '\u0014';
                    break;
                }
                case 2: {
                    c16 = '1';
                    break;
                }
                case 3: {
                    c16 = '+';
                    break;
                }
                default: {
                    c16 = 'P';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "-d^[%1".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'A';
                    break;
                }
                case 1: {
                    c18 = '\u0014';
                    break;
                }
                case 2: {
                    c18 = '1';
                    break;
                }
                case 3: {
                    c18 = '+';
                    break;
                }
                default: {
                    c18 = 'P';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "0d^[%1".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'A';
                    break;
                }
                case 1: {
                    c20 = '\u0014';
                    break;
                }
                case 2: {
                    c20 = '1';
                    break;
                }
                case 3: {
                    c20 = '+';
                    break;
                }
                default: {
                    c20 = 'P';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        fb.z = z;
    }
}
