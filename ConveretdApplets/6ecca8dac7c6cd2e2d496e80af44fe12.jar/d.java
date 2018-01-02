import java.net.MalformedURLException;
import java.net.URL;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.awt.Event;
import java.applet.AppletContext;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class d extends Canvas
{
    private String a;
    private boolean b;
    private String[] c;
    private Color[] d;
    private int[] e;
    private boolean[] f;
    private int g;
    private int h;
    private int i;
    private int j;
    private boolean k;
    private char[] l;
    private Scrollbar m;
    private int n;
    private int o;
    private Image p;
    private Graphics q;
    private Dimension r;
    private Font s;
    private int t;
    private int u;
    private int v;
    private String[] w;
    private Cursor x;
    private Cursor y;
    private AppletContext z;
    private String A;
    private String B;
    private String C;
    private Image D;
    private y E;
    private br F;
    private w G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    String N;
    int O;
    int P;
    
    static int a(final String s, int n, final int n2) {
        final boolean dx = bm.dX;
        ++n;
        int char1 = 0;
        int n4 = 0;
        Label_0045: {
            while (true) {
                Label_0014: {
                    if (!dx) {
                        break Label_0014;
                    }
                    ++n;
                }
                if (n < n2) {
                    final char c = (char)(char1 = s.charAt(n));
                    final int n3 = n4 = 48;
                    if (dx) {
                        break Label_0045;
                    }
                    if (c >= n3 && s.charAt(n) <= '9') {
                        continue;
                    }
                }
                break;
            }
            char1 = n;
            n4 = n2;
        }
        if (char1 != n4 && s.charAt(n) == ',') {
            ++n;
            while (true) {
                Label_0068: {
                    if (!dx) {
                        break Label_0068;
                    }
                    ++n;
                }
                if (n < n2) {
                    final int char2;
                    final char c2 = (char)(char2 = s.charAt(n));
                    if (dx) {
                        return char2;
                    }
                    if (c2 >= '0' && s.charAt(n) <= '9') {
                        continue;
                    }
                }
                break;
            }
        }
        return --n;
    }
    
    void a(final Scrollbar m) {
        this.m = m;
    }
    
    public boolean mouseMove(final Event event, final int o, final int p3) {
        final boolean dx = bm.dX;
        if ((!irc.bq && !irc.bp && !irc.br) || this.z == null || this.j == -1) {
            return false;
        }
        int n = 5;
        final int n2 = p3 / this.t + this.j;
        if (n2 >= this.h) {
            this.A = null;
            this.B = null;
            if (this.N != null) {
                this.repaint();
            }
            this.N = null;
            this.C = null;
            this.setCursor(this.y);
            return false;
        }
        final String a = a(this.c[n2]);
        int n3 = 0;
        int n4;
        int n5 = 0;
        int h = 0;
        int n6;
        int n7 = 0;
        int n8;
        int n9 = 0;
        String substring = null;
        int n10;
        int a2;
        int index;
        Label_0212_Outer:Label_0237_Outer:Label_0248_Outer:
        while (true) {
        Label_0168:
            while (true) {
                Label_0158: {
                    if (!dx) {
                        break Label_0158;
                    }
                    n = (n4 = n + this.getFontMetrics(this.s).charWidth(a.charAt(n3)));
                    if (n5 > h) {
                        break Label_0168;
                    }
                    ++n3;
                }
                if (n3 != a.length()) {
                    continue;
                }
                break;
            }
            n5 = n2;
            h = this.h;
            if (!dx) {
                if (n5 < h && n3 < a.length()) {
                    n6 = n3;
                    while (true) {
                    Label_0223:
                        while (true) {
                            Label_0218: {
                                if (!dx) {
                                    break Label_0218;
                                }
                                this.a(a.charAt(n6));
                                if (n7 == 0) {
                                    break Label_0223;
                                }
                                --n6;
                            }
                            if (n6 != 0) {
                                continue Label_0212_Outer;
                            }
                            break;
                        }
                        n7 = n3;
                        if (dx) {
                            continue Label_0237_Outer;
                        }
                        break;
                    }
                    n8 = n7;
                    while (true) {
                    Label_0264:
                        while (true) {
                            Label_0254: {
                                if (!dx) {
                                    break Label_0254;
                                }
                                this.a(a.charAt(n8));
                                if (n9 == 0) {
                                    break Label_0264;
                                }
                                ++n8;
                            }
                            if (n8 != a.length()) {
                                continue Label_0248_Outer;
                            }
                            break;
                        }
                        n9 = n6;
                        if (dx) {
                            continue;
                        }
                        break;
                    }
                    Label_0298: {
                        if (n9 < n8) {
                            substring = a.substring(n6 + 1, n8);
                            if (!dx) {
                                break Label_0298;
                            }
                        }
                        substring = "";
                    }
                    Label_0410: {
                        if (n6 == 0 && a.charAt(0) == '<' && !substring.equals("")) {
                            this.B = substring.substring(0, substring.length() - 1);
                            if (!dx) {
                                break Label_0410;
                            }
                        }
                        if (substring.length() > 1 && substring.charAt(0) == '<' && substring.charAt(substring.length() - 1) == '>') {
                            this.B = substring.substring(1, substring.length() - 1);
                            if (!dx) {
                                break Label_0410;
                            }
                        }
                        this.B = null;
                    }
                    if (irc.bq && this.B != null && this.E == null) {
                        this.setCursor(this.x);
                        if (this.G == null || !irc.cA) {
                            return false;
                        }
                        this.N = this.B;
                        this.O = o;
                        this.P = p3;
                        this.repaint();
                        if (!dx) {
                            return false;
                        }
                    }
                    if (this.N != null) {
                        this.repaint();
                    }
                    this.N = null;
                    n10 = -1;
                    a2 = this.a(substring, '.');
                    if (a2 >= 1) {
                        n10 = substring.toLowerCase().indexOf(b("l6_[6+m"));
                        if (n10 < 0) {
                            n10 = substring.toLowerCase().indexOf(b("b6[\u0011#+"));
                        }
                        if (n10 < 0 && a2 >= 2) {
                            n10 = substring.toLowerCase().indexOf(b("s5\\"));
                        }
                    }
                    if (irc.bp && n10 >= 0) {
                        this.setCursor(this.x);
                        this.A = substring.substring(n10);
                        if (!dx) {
                            return false;
                        }
                    }
                    index = substring.indexOf("#");
                    if (index >= 0) {
                        this.setCursor(this.x);
                        this.C = substring.substring(index);
                        if (!dx) {
                            return false;
                        }
                    }
                    this.setCursor(this.y);
                    this.A = null;
                    if (!dx) {
                        return false;
                    }
                }
                this.A = null;
                this.B = null;
                this.C = null;
                if (this.N != null) {
                    this.repaint();
                }
                this.N = null;
                this.setCursor(this.y);
                return false;
            }
            continue;
        }
    }
    
    private void a(final Dimension dimension) {
        this.i = dimension.height / this.t;
        this.n = dimension.width / this.u;
        if (this.i <= 0) {
            this.i = 20;
        }
        if (this.n == 0) {
            this.n = 80;
        }
        if (this.h - this.j > this.i) {
            this.j = this.h - this.i;
        }
    }
    
    public d(final int g, final AppletContext z, final Image d, final y e, final br f, final w g2) {
        this.a = b("*\u0002\u0011\u0004![\u007f\u0014\r/zc\u0017\u0015");
        this.b = true;
        this.h = 0;
        this.j = -1;
        this.k = false;
        this.l = new char[1024];
        this.o = 0;
        this.r = null;
        this.s = null;
        this.t = 12;
        this.u = 5;
        this.w = Toolkit.getDefaultToolkit().getFontList();
        this.x = new Cursor(12);
        this.y = new Cursor(0);
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.H = -1;
        this.I = -1;
        this.J = -1;
        this.K = -1;
        this.L = -1;
        this.M = -1;
        this.N = null;
        this.D = d;
        this.c = new String[g];
        this.d = new Color[g];
        this.e = new int[g];
        this.f = new boolean[g];
        this.g = g;
        this.z = z;
        this.E = e;
        this.F = f;
        this.G = g2;
        this.b(this.preferredSize());
    }
    
    public synchronized void paint(final Graphics graphics) {
        final boolean dx = bm.dX;
        final Dimension preferredSize = this.preferredSize();
        this.b(preferredSize);
        this.m.setValues(this.m.getValue(), this.m.getVisible(), 0, (this.g < this.i) ? this.g : (this.g - this.i));
        if (this.k) {
            this.k = false;
            graphics.clearRect(0, 0, preferredSize.width, preferredSize.height);
            if (this.D != null) {
                this.a(graphics, this.D, preferredSize);
            }
        }
        this.q.clearRect(0, 0, preferredSize.width, preferredSize.height);
        this.q.setColor(Color.black);
        this.q.drawLine(0, 0, preferredSize.width, 0);
        if (this.k) {
            return;
        }
        Label_0303: {
            if (irc.bo || irc.bs) {
                this.c(preferredSize);
                if (!dx) {
                    break Label_0303;
                }
            }
            if (this.s != null) {
                this.q.setFont(this.s);
            }
            int i = this.j;
            int n = 1;
            Label_0246: {
                if (this.D != null) {
                    if (i == -1) {
                        this.a(graphics, this.D, preferredSize);
                        if (!dx) {
                            break Label_0246;
                        }
                    }
                    this.a(this.q, this.D, preferredSize);
                }
            }
            if (i == -1) {
                return;
            }
            do {
                this.q.setColor(this.d[i]);
                this.q.drawString(a(this.c[i++]), 5, this.t * n++);
            } while (i != this.h);
        }
        graphics.drawImage(this.p, 0, 0, this);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final boolean dx = bm.dX;
        int k = this.K;
        if (this.I == -1 || k == -1) {
            return false;
        }
        if (this.I > k) {
            final int i = this.I;
            this.I = k;
            k = i;
        }
        StringBuffer sb = null;
        int j = this.I;
        StringBuffer sb2;
        while (true) {
            Label_0126: {
                if (j > k) {
                    sb2 = sb;
                    if (!dx) {
                        break;
                    }
                }
                else {
                    if (j == this.I) {
                        sb = new StringBuffer(this.c[j]);
                        if (!dx) {
                            break Label_0126;
                        }
                    }
                    sb.append("\n" + this.c[j]);
                }
            }
            ++j;
        }
        if (sb2 != null) {
            irc.i.setContents(new StringSelection(new String(sb)), null);
        }
        final int n3 = -1;
        this.K = n3;
        this.I = n3;
        this.repaint();
        return false;
    }
    
    private boolean a(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || this.a.indexOf(c) >= 0;
    }
    
    void a(final int j) {
        if (j >= this.h) {
            this.m.setValue(this.h);
            return;
        }
        if (this.j != -1) {
            this.j = j;
            this.repaint();
        }
    }
    
    public void a(final Font s, final int n) {
        this.s = s;
        this.v = n;
        Label_0037: {
            if (n < s.getSize()) {
                this.t = s.getSize();
                if (!bm.dX) {
                    break Label_0037;
                }
            }
            this.t = n;
        }
        this.u = this.getFontMetrics(s).charWidth('n');
        this.a(this.preferredSize());
        this.repaint();
    }
    
    private void a() {
        int n = 0;
        while (true) {
            Label_0067: {
                if (!bm.dX) {
                    break Label_0067;
                }
                this.c[n] = this.c[n + 1];
                this.d[n] = this.d[n + 1];
                this.e[n] = this.e[n + 1];
                this.f[n] = this.f[n + 1];
                ++n;
            }
            if (n == this.g - 1) {
                return;
            }
            continue;
        }
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    static String a(final String s) {
        final boolean dx = bm.dX;
        String concat = "";
        final int length = s.length();
        int a = 0;
        while (true) {
            Label_0065: {
                if (!dx) {
                    break Label_0065;
                }
                final String s2;
                final char char1 = s2.charAt(a);
                Label_0062: {
                    if (char1 == '\u0003') {
                        a = a(s, a, length);
                        if (!dx) {
                            break Label_0062;
                        }
                    }
                    if (char1 >= ' ') {
                        concat = concat.concat(String.valueOf(char1));
                    }
                }
                ++a;
            }
            if (a != length) {
                continue;
            }
            final String s2 = concat;
            if (!dx) {
                return s2;
            }
            continue;
        }
    }
    
    private int a(final String s, final char c) {
        final boolean dx = bm.dX;
        char c2 = '\0';
        int n = 0;
        while (true) {
            while (true) {
                Label_0031: {
                    if (!dx) {
                        break Label_0031;
                    }
                    s.charAt(n);
                    final char c3;
                    if (c3 == c) {
                        ++c2;
                    }
                    ++n;
                }
                if (n != s.length()) {
                    continue;
                }
                break;
            }
            final char c3 = c2;
            if (!dx) {
                return c3;
            }
            continue;
        }
    }
    
    synchronized void a(String s, final Color color, final boolean b) {
        final boolean dx = bm.dX;
        if (!this.b) {
            return;
        }
        int n = 0;
        int n2 = this.s.getSize();
        int n3 = s.indexOf(32) + 1;
        this.b(this.preferredSize());
        int n4 = 21;
        int n5 = s.length();
        int a = 0;
        while (true) {
            Label_0209: {
                if (!dx) {
                    break Label_0209;
                }
                final char char1 = s.charAt(a);
                Label_0206: {
                    if (char1 == '\u0003') {
                        a = a(s, a, n5);
                        if (!dx) {
                            break Label_0206;
                        }
                    }
                    if (char1 < ' ') {
                        if (char1 != '\u0002') {
                            break Label_0206;
                        }
                        if (n == 0) {
                            n = 1;
                            n2 = this.s.getSize();
                            this.a(new Font(this.s.getName(), 1, this.s.getSize()), this.v);
                            if (!dx) {
                                break Label_0206;
                            }
                        }
                        n = 0;
                        this.a(new Font(this.s.getName(), 0, n2), this.v);
                        if (!dx) {
                            break Label_0206;
                        }
                    }
                    n4 += this.getFontMetrics(this.s).charWidth(char1);
                }
                ++a;
            }
            if (a >= n5) {
                int n7 = 0;
                int n6 = 0;
                int n8;
                int n9;
                int a2;
                char char2;
                int n10 = 0;
                int n11 = 0;
                String substring;
                int n12;
                int a3;
                int n13;
                char c;
                int n14;
                Label_0505_Outer:Label_0511_Outer:
                while (true) {
                    if (n4 <= this.r.width) {
                        n6 = (n7 = s.length() - 1);
                        if (!dx) {
                            break;
                        }
                    }
                    else {
                        n5 = s.length();
                    }
                    n8 = n7;
                    n9 = -1;
                    a2 = 0;
                    Label_0428: {
                        while (true) {
                            Label_0398: {
                                if (!dx) {
                                    break Label_0398;
                                }
                                char2 = s.charAt(a2);
                                Label_0395: {
                                    if (char2 == '\u0003') {
                                        a2 = a(s, a2, n5);
                                        if (!dx) {
                                            break Label_0395;
                                        }
                                    }
                                    Label_0349: {
                                        if (char2 == '\u0002') {
                                            if (n == 0) {
                                                n = 1;
                                                n2 = this.s.getSize();
                                                this.s = new Font(this.s.getName(), 1, this.s.getSize() - 2);
                                                if (!dx) {
                                                    break Label_0349;
                                                }
                                            }
                                            n = 0;
                                            this.s = new Font(this.s.getName(), 0, n2);
                                        }
                                    }
                                    if (char2 < ' ') {
                                        ++a2;
                                        if (!dx) {
                                            break Label_0395;
                                        }
                                    }
                                    n8 += this.getFontMetrics(this.s).charWidth(char2);
                                    if (char2 == ' ') {
                                        n9 = a2 + 1;
                                    }
                                }
                                ++a2;
                            }
                            if (n8 <= this.r.width) {
                                n10 = a2;
                                n11 = n5;
                                if (dx) {
                                    break Label_0428;
                                }
                                if (n10 < n11) {
                                    continue Label_0505_Outer;
                                }
                            }
                            break;
                        }
                        s.length();
                    }
                    if (n10 < n11 && s.charAt(a2) == ' ') {
                        --n9;
                    }
                    if (n9 < 0) {
                        n9 = a2;
                    }
                    if (n9 == n3) {
                        n9 = a2;
                        n3 = -1;
                    }
                    substring = s.substring(0, n9);
                    this.a(substring, color, n9, b);
                    n5 = substring.length();
                    n12 = 0;
                    a3 = 0;
                    while (true) {
                        while (true) {
                            Label_0570: {
                                if (!dx) {
                                    break Label_0570;
                                }
                                n13 = s.charAt(a3);
                                c = (char)n13;
                                Label_0567: {
                                    if (c == '\u0003') {
                                        a3 = a(s, a3, n5);
                                        if (!dx) {
                                            break Label_0567;
                                        }
                                    }
                                    if (c < ' ') {
                                        ++a3;
                                        if (!dx) {
                                            break Label_0567;
                                        }
                                    }
                                    n12 += this.getFontMetrics(this.s).charWidth(c);
                                }
                                ++a3;
                            }
                            if (a3 < n5) {
                                continue Label_0511_Outer;
                            }
                            break;
                        }
                        n14 = (n13 = s.length());
                        if (dx) {
                            continue;
                        }
                        break;
                    }
                    if (n14 > n9) {
                        s = s.substring(n9);
                    }
                    n4 -= n12;
                }
                if (n7 >= 0 && s.charAt(n6) == '\r') {
                    s = s.substring(0, n6);
                }
                this.a(s, color, 0, b);
                this.a(new Font(this.s.getName(), 0, n2), this.v);
                return;
            }
            continue;
        }
    }
    
    private void b(final Dimension r) {
        if (this.r == null || this.r.width != r.width || this.r.height != r.height) {
            this.a(this.r = r);
            this.p = this.createImage(r.width, r.height);
            if (this.p != null) {
                this.q = this.p.getGraphics();
            }
        }
    }
    
    public void b() {
        this.j = -1;
        this.h = 0;
        this.m.setValue(0);
        this.k = true;
        this.repaint();
    }
    
    String[] c() {
        return this.w;
    }
    
    private void a(final Graphics graphics, final Image image, final Dimension dimension) {
        int n = 0;
        int n2 = 1;
        final int width = image.getWidth(this);
        if (dimension.width > width) {
            n = (dimension.width - width) / 2;
        }
        final int height = image.getHeight(this);
        if (dimension.height > height) {
            n2 = (dimension.height - height) / 2;
        }
        graphics.drawImage(image, n, n2, this);
    }
    
    private synchronized void c(final Dimension dimension) {
        final boolean dx = bm.dX;
        Color color = null;
        Color color2 = null;
        int n = 1;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        Image a = null;
        if (this.D != null) {
            this.a(this.q, this.D, dimension);
        }
        if (this.s != null) {
            this.q.setFont(this.s);
        }
        int i = this.j;
        int n5 = 1;
        if (i == -1) {
            return;
        }
        final Font s = this.s;
    Label_0137_Outer:
        do {
            final int length = this.c[i].length();
            this.c[i].getChars(0, length, this.l, 0);
            int n6 = 5;
            Font s2 = this.s;
            int n7 = 0;
            int n8 = 0;
            int n9 = 0;
            while (true) {
                while (true) {
                    Label_1163: {
                        if (!dx) {
                            break Label_1163;
                        }
                        final boolean bo = irc.bo;
                        Label_1160: {
                            if (n9 != 0) {
                                if (this.l[n8] == '\u0002') {
                                    if (n4 != 0) {
                                        n4 = 0;
                                        this.q.setFont(s2 = new Font(this.s.getName(), 0, this.s.getSize()));
                                        if (!dx) {
                                            break Label_1160;
                                        }
                                    }
                                    n4 = 1;
                                    this.q.setFont(s2 = new Font(this.s.getName(), 1, this.s.getSize()));
                                    if (!dx) {
                                        break Label_1160;
                                    }
                                }
                                if (this.l[n8] == '\u001f') {
                                    if (n2 != 0) {
                                        n2 = 0;
                                        if (!dx) {
                                            break Label_1160;
                                        }
                                    }
                                    n2 = 1;
                                    if (!dx) {
                                        break Label_1160;
                                    }
                                }
                                if (this.l[n8] == '\u0016') {
                                    if (n3 != 0) {
                                        n3 = 0;
                                        if (!dx) {
                                            break Label_1160;
                                        }
                                    }
                                    n3 = 1;
                                    if (!dx) {
                                        break Label_1160;
                                    }
                                }
                                if (this.l[n8] == '\u000f') {
                                    this.q.setFont(s2 = new Font(this.s.getName(), 0, this.t));
                                    n4 = 0;
                                    n2 = 0;
                                    if (!dx) {
                                        break Label_1160;
                                    }
                                }
                                if (this.l[n8] == '\u0003' && n8 != length) {
                                    String s3 = "";
                                    int n10 = n8 + 1;
                                    if (n10 < length && this.l[n10] >= '0' && this.l[n10] <= '9') {
                                        s3 += this.l[n10];
                                        ++n10;
                                    }
                                    if (n10 < length && this.l[n10] >= '0' && this.l[n10] <= '9') {
                                        s3 += this.l[n10];
                                        ++n10;
                                    }
                                    if (!s3.equals("")) {
                                        color = bn.a(s3);
                                    }
                                    if (n10 < length && this.l[n10] == ',') {
                                        String s4 = "";
                                        if (++n10 < length && this.l[n10] >= '0' && this.l[n10] <= '9') {
                                            s4 += this.l[n10];
                                            ++n10;
                                        }
                                        if (n10 < length && this.l[n10] >= '0' && this.l[n10] <= '9') {
                                            s4 += this.l[n10];
                                            ++n10;
                                        }
                                        if (!s4.equals("")) {
                                            color2 = bn.a(s4);
                                        }
                                    }
                                    n8 = n10 - 1;
                                    if (!dx) {
                                        break Label_1160;
                                    }
                                }
                            }
                            int j = this.I;
                            int k = this.K;
                            if (j > k) {
                                final int n11 = j;
                                j = k;
                                k = n11;
                            }
                            if (j != -1 && k != -1 && i >= j && i <= k) {
                                color = bn.F;
                                color2 = bn.E;
                            }
                            if (irc.bs && this.f[i]) {
                                final int a2 = irc.cz.a(this.l, n8, length);
                                n7 += a2 - n8;
                                n8 = a2;
                                a = irc.cz.a();
                            }
                            if (color == null) {
                                color = this.d[i];
                            }
                            Label_0950: {
                                if (color2 != null) {
                                    Label_0870: {
                                        if (n3 != 0) {
                                            this.q.setColor(color);
                                            if (!dx) {
                                                break Label_0870;
                                            }
                                        }
                                        this.q.setColor(color2);
                                    }
                                    if (a == null) {
                                        this.q.fillRect(n6, 2 + this.t * (n5 - 1), this.getFontMetrics(s2).charWidth(this.l[n8]), this.t);
                                        if (!dx) {
                                            break Label_0950;
                                        }
                                    }
                                    this.q.fillRect(n6, 2 + this.t * (n5 - 1), a.getWidth(this), this.t);
                                }
                            }
                            Label_0980: {
                                if (n3 != 0 && color2 != null) {
                                    this.q.setColor(color2);
                                    if (!dx) {
                                        break Label_0980;
                                    }
                                }
                                this.q.setColor(color);
                            }
                            if (this.e[i] != 0 && n8 >= this.e[i] && n == 1) {
                                n6 = 5;
                                ++n5;
                                n = 0;
                            }
                            if (n2 != 0) {
                                this.q.drawString("_", n6, this.t * n5);
                            }
                            if (a == null) {
                                this.q.drawChars(this.l, n8, 1, n6, this.t * n5);
                                n6 += this.getFontMetrics(s2).charWidth(this.l[n8]);
                                if (!dx) {
                                    break Label_1160;
                                }
                            }
                            final int width = a.getWidth(this);
                            int n12 = this.t * (n5 - 1);
                            if (width < this.t) {
                                n12 += this.t - width;
                            }
                            this.q.drawImage(a, n6, n12, this);
                            n6 += width;
                            a = null;
                        }
                        ++n8;
                    }
                    if (n8 < length) {
                        continue Label_0137_Outer;
                    }
                    break;
                }
                n9 = this.e[i];
                if (dx) {
                    continue;
                }
                break;
            }
            if (n9 == 0) {
                this.q.setFont(this.s);
                color = null;
                color2 = null;
                n2 = 0;
                n4 = 0;
            }
            if (this.N != null) {
                this.G.a(this.q, this.N, this.O, this.P, this.getBounds());
            }
            ++i;
            ++n5;
            n = 1;
        } while (i != this.h);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.A == null && this.B == null && this.C == null) {
            return false;
        }
        if (irc.bp && this.A != null) {
            if (this.A.toLowerCase().startsWith(b("s5\\"))) {
                this.A = b("l6_[6+m") + this.A;
            }
            URL url;
            try {
                url = new URL(this.A);
            }
            catch (MalformedURLException ex) {
                return false;
            }
            this.z.showDocument(url, b("[ GJbo"));
            return false;
        }
        if (irc.bq && this.B != null && this.E == null) {
            this.F.c(this.B, irc.cg);
            irc.cy.b(this.B);
            return true;
        }
        if (irc.br && this.C != null) {
            this.F.e.a(b("N\rbe,") + this.C + "\n");
            return true;
        }
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (!irc.bv || !irc.h) {
            return false;
        }
        final int i = n2 / this.t + this.j;
        if (i >= this.h || i < 0) {
            return false;
        }
        Label_0096: {
            if (this.I == -1 && i < this.h) {
                this.I = i;
                if (!bm.dX) {
                    break Label_0096;
                }
            }
            this.K = ((i >= this.h) ? (this.h - 1) : i);
        }
        this.repaint();
        return false;
    }
    
    private void a(final String s, final Color color, final int n, final boolean b) {
        Label_0148: {
            if (this.h == this.g) {
                this.a();
                this.d[this.h - 1] = color;
                this.c[this.h - 1] = new String(s);
                this.e[this.h - 1] = n;
                this.f[this.h - 1] = b;
                if (!bm.dX) {
                    break Label_0148;
                }
            }
            if (this.j == -1) {
                this.j = 0;
            }
            this.d[this.h] = color;
            this.c[this.h] = new String(s);
            this.e[this.h] = n;
            this.f[this.h] = b;
            ++this.h;
        }
        if (this.h - this.j > this.i && this.j < this.g - this.i) {
            ++this.j;
            this.m.setValue(this.j);
        }
        this.repaint();
    }
    
    void a(final boolean b) {
        this.b = b;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u0004';
                    break;
                }
                case 1: {
                    c2 = 'B';
                    break;
                }
                case 2: {
                    c2 = '+';
                    break;
                }
                case 3: {
                    c2 = '+';
                    break;
                }
                default: {
                    c2 = '\f';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
