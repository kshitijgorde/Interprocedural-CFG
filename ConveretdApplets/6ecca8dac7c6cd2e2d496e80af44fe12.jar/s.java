import java.awt.Component;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Event;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends Panel
{
    final int a = 50;
    int b;
    Font c;
    Font d;
    FontMetrics e;
    Color f;
    Color g;
    Color h;
    Color i;
    Color j;
    Color k;
    Color l;
    static final int m = 2;
    static final int n = 2;
    static final int o = 2;
    static final int p = 4;
    static final int q = 2;
    int r;
    int s;
    int t;
    int u;
    int v;
    int w;
    Panel[] x;
    String[] y;
    String[] z;
    int[] A;
    int[] B;
    Color[] C;
    boolean[] D;
    TextField[] E;
    int[] F;
    int[] G;
    String[] H;
    int I;
    int J;
    int K;
    PopupMenu L;
    MenuItem M;
    be N;
    br O;
    u P;
    irc Q;
    
    private void a(final int n) {
        final boolean dx = bm.dX;
        this.d(n);
        int n2 = 0;
        while (true) {
            Label_0154: {
                if (!dx) {
                    break Label_0154;
                }
                this.y[n2] = this.z[n2];
                this.B[n2] = ((n2 == 0) ? 0 : ((n2 == 1) ? (this.A[n2 - 1] - this.s) : this.A[n2 - 1]));
                this.A[n2] = ((n2 == 0) ? (8 + this.e.stringWidth(this.y[n2]) + 4 * this.s) : (this.B[n2] + 8 + this.e.stringWidth(this.y[n2]) + 2 * this.s));
                ++n2;
            }
            if (n2 == n + 1) {
                int length;
                int n3;
                int n4;
                int n5 = 0;
                int n6 = 0;
                int n7;
                int[] a;
                int n8;
                int n9;
                int[] b;
                int n10;
                int[] a2;
                int n11;
                Label_0177_Outer:Label_0188_Outer:Label_0368_Outer:
                while (this.A[n] > this.t) {
                    n3 = (length = 0);
                    n4 = 0;
                    while (true) {
                        while (true) {
                            Label_0208: {
                                if (!dx) {
                                    break Label_0208;
                                }
                                this.y[n4].length();
                                if (n5 > n6) {
                                    length = this.y[n4].length();
                                    n3 = n4;
                                }
                                ++n4;
                            }
                            if (n4 != n) {
                                continue Label_0188_Outer;
                            }
                            break;
                        }
                        n5 = length;
                        n6 = 4;
                        if (dx) {
                            continue Label_0368_Outer;
                        }
                        break;
                    }
                    if (n5 <= n6) {
                        this.b(n);
                        return;
                    }
                    this.y[n3] = this.y[n3].substring(0, this.y[n3].length() - 4) + f("\u001eu");
                    n7 = this.A[n3] - ((n3 == 0) ? (8 + this.e.stringWidth(this.y[n3]) + 4 * this.s) : (this.B[n3] + 8 + this.e.stringWidth(this.y[n3]) + 2 * this.s));
                    a = this.A;
                    n8 = n3;
                    a[n8] -= n7;
                    n9 = n3 + 1;
                    while (true) {
                        Label_0395: {
                            if (!dx) {
                                break Label_0395;
                            }
                            b = this.B;
                            n10 = n9;
                            b[n10] -= n7;
                            a2 = this.A;
                            n11 = n9;
                            a2[n11] -= n7;
                            ++n9;
                        }
                        if (n9 == n + 1) {
                            continue Label_0177_Outer;
                        }
                        continue;
                    }
                }
                return;
            }
            continue;
        }
    }
    
    public void a(final String s, final Color color) {
        int n = 0;
        while (true) {
            Label_0036: {
                if (!bm.dX) {
                    break Label_0036;
                }
                if (this.z[n].equals(s)) {
                    this.C[n] = color;
                    this.repaint();
                    return;
                }
                ++n;
            }
            if (n >= this.I) {
                return;
            }
            continue;
        }
    }
    
    public void a(final String s) {
        int n = 0;
        while (true) {
            Label_0045: {
                if (!bm.dX) {
                    break Label_0045;
                }
                if (this.y[n].equals(s)) {
                    if (n >= this.b) {
                        return;
                    }
                    this.D[n] = true;
                    this.repaint();
                    return;
                }
                else {
                    ++n;
                }
            }
            if (n >= this.I) {
                return;
            }
            continue;
        }
    }
    
    public void b(final String s) {
        int j = 0;
        while (true) {
            Label_0161: {
                if (!bm.dX) {
                    break Label_0161;
                }
                if (this.z[j].equals(s)) {
                    if (j == this.J || !this.D[j]) {
                        return;
                    }
                    final int[] b = this.B;
                    final int i = this.J;
                    b[i] += this.s;
                    final int[] a = this.A;
                    final int k = this.J;
                    a[k] -= this.s;
                    this.x[this.J].hide();
                    this.J = j;
                    this.x[this.J].show();
                    this.C[j] = bn.r;
                    if (this.E[j] != null) {
                        this.E[j].requestFocus();
                    }
                    final int[] b2 = this.B;
                    final int n = j;
                    b2[n] -= this.s;
                    final int[] a2 = this.A;
                    final int n2 = j;
                    a2[n2] += this.s;
                    this.repaint();
                    return;
                }
                else {
                    ++j;
                }
            }
            if (j >= this.I) {
                return;
            }
            continue;
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        final int k = this.K;
        if (k != this.I) {
            this.K = this.I;
            this.c(k);
        }
        return true;
    }
    
    public s(final int n, final int n2, int t, final int u, final int s) {
        this.b = 50;
        this.c = new Font(f("x> mXD2/z"), 0, 12);
        this.d = new Font(f("x> mXD2/z"), 1, 12);
        this.e = this.getFontMetrics(this.d);
        this.f = Color.white;
        this.g = new Color(223, 223, 223);
        this.h = new Color(127, 127, 127);
        this.i = Color.black;
        this.l = this.h;
        this.x = new Panel[50];
        this.y = new String[50];
        this.z = new String[50];
        this.A = new int[50];
        this.B = new int[50];
        this.C = new Color[50];
        this.D = new boolean[50];
        this.E = new TextField[50];
        this.F = new int[50];
        this.G = new int[50];
        this.H = new String[50];
        this.I = 0;
        this.J = 0;
        this.K = -1;
        t += 4;
        this.reshape(n, n2, this.t = t, this.u = u);
        this.j = bn.v;
        this.k = bn.u;
        this.setLayout(null);
        this.s = s;
        this.r = this.e.getHeight() + 2 * this.s + 4 + 1;
        this.b();
    }
    
    private void b(final int n) {
        int n2 = 0;
        while (true) {
            Label_0047: {
                if (!bm.dX) {
                    break Label_0047;
                }
                this.y[n2] = this.H[n2];
                this.B[n2] = this.F[n2];
                this.A[n2] = this.G[n2];
                ++n2;
            }
            if (n2 == n + 1) {
                return;
            }
            continue;
        }
    }
    
    public int c(final String s) {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
            while (true) {
                Label_0028: {
                    if (!dx) {
                        break Label_0028;
                    }
                    this.z[n].equals(s);
                    final int n2;
                    if (n2 != 0) {
                        return n;
                    }
                    ++n;
                }
                if (n < this.I) {
                    continue;
                }
                break;
            }
            final int n2 = -1;
            if (!dx) {
                return n2;
            }
            continue;
        }
    }
    
    public String a() {
        return this.y[this.J];
    }
    
    private void c(final int n) {
        final boolean dx = bm.dX;
        if (n < 0 || n >= this.I) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        graphics.setColor(bn.r);
        final int n2 = (n != this.J) ? 1 : 0;
        graphics.setFont((n == this.J) ? this.d : this.c);
        graphics.setColor(this.C[n]);
        Label_0184: {
            if (n == this.J) {
                graphics.fillRect(this.B[n] + this.s, this.s + n2 * 2, this.A[n] - this.B[n] - 2 * this.s, this.r);
                if (!dx) {
                    break Label_0184;
                }
            }
            graphics.fillRect(this.B[n] + this.s, this.s + n2 * 2, this.A[n] - this.B[n] - 2 * this.s, this.r);
        }
        graphics.setColor(this.h);
        int s = this.s;
        int n3 = 0;
        Label_0342: {
            while (true) {
                Label_0316: {
                    if (!dx) {
                        break Label_0316;
                    }
                    if (s == 1) {
                        graphics.setColor(this.i);
                    }
                    graphics.drawLine(this.A[n] - s, n2 * 2 + 2 + this.s - s - 1, this.A[n] - s, this.r - this.s + s - s * n2);
                    if (s == 1) {
                        graphics.drawLine(this.A[n] - this.s, n2 * 2 + 1, this.A[n] - 1, n2 * 2 + this.s);
                    }
                    --s;
                }
                if (n != this.J - 1) {
                    n3 = s;
                    if (dx) {
                        break Label_0342;
                    }
                    if (n3 > 0) {
                        continue;
                    }
                }
                break;
            }
            final boolean b = this.D[n];
        }
        Label_0387: {
            if (n3 != 0) {
                if (n == this.K) {
                    graphics.setColor(this.j);
                    if (!dx) {
                        break Label_0387;
                    }
                }
                graphics.setColor(this.k);
                if (!dx) {
                    break Label_0387;
                }
            }
            graphics.setColor(this.l);
        }
        graphics.drawString(this.y[n], this.B[n] + this.s * (2 - n2) + 4, n2 * 2 + 2 + this.s + this.e.getAscent());
        graphics.setColor(this.f);
        int n4 = 0;
        while (true) {
            Label_0622: {
                if (!dx) {
                    break Label_0622;
                }
                if (n4 == 1) {
                    graphics.setColor(this.g);
                }
                graphics.drawLine(this.B[n] + 2 + n4 + ((n4 == this.s - 1 && n4 != 0) ? -1 : 0), n2 * 2 + n4, this.A[n] - this.s - 2 + 1, n2 * 2 + n4);
                if (n != this.J + 1) {
                    graphics.drawLine(this.B[n] + n4, n2 * 2 + 2, this.B[n] + n4, this.r - this.s + n4 - n4 * n2);
                    if (n4 == 0) {
                        graphics.drawLine(this.B[n] + 1, n2 * 2 + 1, this.B[n] + 1, n2 * 2 + 1);
                    }
                }
                ++n4;
            }
            if (n4 >= this.s) {
                return;
            }
            continue;
        }
    }
    
    public void paint(final Graphics graphics) {
        final boolean dx = bm.dX;
        if (irc.g) {
            this.v = this.Q.getSize().width;
            this.w = this.Q.getSize().height;
            if (this.t != this.v || this.u != this.w) {
                this.t = this.v;
                this.u = this.w;
                this.reshape(0, 0, this.t, this.u);
                int n = 0;
                int i = 0;
                while (true) {
                    while (true) {
                        Label_0155: {
                            if (!dx) {
                                break Label_0155;
                            }
                            final Panel panel = this.x[n];
                            final int s = this.s;
                            panel.reshape(i, this.r + 1, this.t - 2 * this.s, this.u - this.r - this.s - 1);
                            this.x[n].validate();
                            ++n;
                        }
                        if (n < this.I) {
                            continue;
                        }
                        break;
                    }
                    final Panel panel = this;
                    i = this.I;
                    if (dx) {
                        continue;
                    }
                    break;
                }
                this.a(i);
            }
        }
        graphics.setColor(bn.s);
        graphics.fillRect(0, 0, this.t, this.r + 1);
        int n2 = 0;
        while (true) {
            Label_0212: {
                if (!dx) {
                    break Label_0212;
                }
                this.c(n2);
                ++n2;
            }
            if (n2 >= this.I) {
                return;
            }
            continue;
        }
    }
    
    public void a(final irc q) {
        this.Q = q;
    }
    
    private void b() {
        this.L = new PopupMenu();
        this.M = new MenuItem(bm.bs);
        this.L.add(this.M);
        this.add(this.L);
    }
    
    public void c() {
        this.repaint();
    }
    
    public void d(final String s) {
        int n = 0;
        while (true) {
            Label_0036: {
                if (!bm.dX) {
                    break Label_0036;
                }
                if (this.y[n].equals(s)) {
                    this.D[n] = false;
                    this.repaint();
                    return;
                }
                ++n;
            }
            if (n >= this.I) {
                return;
            }
            continue;
        }
    }
    
    public void a(final String s, final String s2) {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
        Label_0165:
            while (true) {
                Label_0157: {
                    if (!dx) {
                        break Label_0157;
                    }
                    if (this.z[n].equals(s)) {
                        final int n2 = this.e.stringWidth(s2) - this.e.stringWidth(s);
                        if (this.A[this.I - 1] + n2 > this.t) {
                            return;
                        }
                        this.z[n] = new String(s2);
                        this.y[n] = new String(s2);
                        final int[] a = this.A;
                        final int n3 = n;
                        a[n3] += n2;
                        int n4 = n + 1;
                        while (true) {
                            Label_0142: {
                                if (!dx) {
                                    break Label_0142;
                                }
                                final int[] a2 = this.A;
                                final int n5 = n4;
                                a2[n5] += n2;
                                final int[] b = this.B;
                                final int n6 = n4;
                                b[n6] += n2;
                                ++n4;
                            }
                            if (n4 >= this.I) {
                                break Label_0165;
                            }
                            continue;
                        }
                    }
                    ++n;
                }
                if (n < this.I) {
                    continue;
                }
                break;
            }
            this.repaint();
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    public void a(final be n, final br o, final u p3) {
        this.N = n;
        this.O = o;
        this.P = p3;
    }
    
    private void d(final int n) {
        int n2 = 0;
        while (true) {
            Label_0047: {
                if (!bm.dX) {
                    break Label_0047;
                }
                this.H[n2] = this.y[n2];
                this.F[n2] = this.B[n2];
                this.G[n2] = this.A[n2];
                ++n2;
            }
            if (n2 == n + 1) {
                return;
            }
            continue;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final boolean dx = bm.dX;
        if (n2 < this.r) {
            if (event.modifiers == 2 || event.modifiers == 4) {
                int n3 = 0;
                while (true) {
                    Label_0107: {
                        Label_0098: {
                            if (!dx) {
                                break Label_0098;
                            }
                            final int n4;
                            if (n4 > this.B[n3] && n < this.A[n3] && n3 == this.J) {
                                this.L.show(this.x[n3], n - 10, n2 - 15);
                                if (!dx) {
                                    break Label_0107;
                                }
                            }
                            ++n3;
                        }
                        if (n3 < this.I) {
                            continue;
                        }
                    }
                    final int n4 = 0;
                    if (!dx) {
                        return n4 != 0;
                    }
                    continue;
                }
            }
            else {
                int n5 = 0;
                while (true) {
                    Label_0193: {
                        if (!dx) {
                            break Label_0193;
                        }
                        if (n > this.B[n5] && n < this.A[n5]) {
                            this.C[n5] = bn.r;
                            if (n5 != this.J && this.D[n5]) {
                                this.b(this.z[n5]);
                                this.c();
                            }
                            return true;
                        }
                        ++n5;
                    }
                    if (n5 < this.I) {
                        continue;
                    }
                    break;
                }
            }
        }
        else {
            Label_0291: {
                if ((event.modifiers & 0x2) != 0x0 || (event.modifiers & 0x4) != 0x0) {
                    final t h = this.N.h(this.a());
                    if (h != null) {
                        h.d();
                        if (!dx) {
                            break Label_0291;
                        }
                    }
                    final y d = this.O.d(this.a());
                    if (d != null) {
                        d.a(this.x[this.J], n - 10, n2 - 10);
                    }
                }
            }
            if (this.E[this.J] != null) {
                this.E[this.J].requestFocus();
            }
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        final boolean dx = bm.dX;
        if (this.y[this.J].equals(bm.cW)) {
            this.P.a();
            if (!dx) {
                return false;
            }
        }
        if (this.y[this.J].charAt(0) == '#') {
            this.N.b(this.z[this.J], false);
            if (!dx) {
                return false;
            }
        }
        this.O.a(this.z[this.J]);
        return false;
    }
    
    public void a(final String s, final Panel panel, final TextField textField) {
        final boolean dx = bm.dX;
        if (this.I >= this.B.length || panel == this) {
            return;
        }
        this.y[this.I] = s;
        this.z[this.I] = s;
        this.B[this.I] = ((this.I == 0) ? 0 : ((this.I == 1) ? (this.A[this.I - 1] - this.s) : this.A[this.I - 1]));
        this.A[this.I] = ((this.I == 0) ? (8 + this.e.stringWidth(s) + 4 * this.s) : (this.B[this.I] + 8 + this.e.stringWidth(s) + 2 * this.s));
        this.C[this.I] = bn.r;
        this.D[this.I] = true;
        this.E[this.I] = textField;
        if (this.I >= this.b) {
            this.D[this.I] = false;
        }
        int n = 0;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0256: {
                    if (!dx) {
                        break Label_0256;
                    }
                    if (this.x[n] == panel) {
                        return;
                    }
                    this.z[n].equals(s);
                    if (n2 == 0) {
                        ++n;
                        break Label_0256;
                    }
                    return;
                }
                if (n < this.I) {
                    continue;
                }
                break;
            }
            this.a(this.I);
            n2 = this.A[this.I];
            if (dx) {
                continue;
            }
            break;
        }
        if (n2 > this.t) {
            return;
        }
        panel.reshape(this.s, this.r + 1, this.t - 2 * this.s, this.u - this.r - this.s - 1);
        this.add(panel);
        if (this.I != this.J) {
            panel.hide();
        }
        this.x[this.I] = panel;
        ++this.I;
        this.repaint();
    }
    
    public void e(final String s) {
        final boolean dx = bm.dX;
        int n = 0;
    Label_0125_Outer:
        while (true) {
            Label_0286: {
                if (!dx) {
                    break Label_0286;
                }
                if (this.z[n].equals(s)) {
                    this.remove(this.x[n]);
                    this.b(this.z[n - 1]);
                    final int n2 = this.A[n] - this.B[n];
                    int n3 = n;
                    while (true) {
                        while (true) {
                            Label_0242: {
                                if (!dx) {
                                    break Label_0242;
                                }
                                Label_0143: {
                                    if (n3 == 0) {
                                        final int[] a = this.A;
                                        final int n4 = n3;
                                        a[n4] += this.s;
                                        final int[] b = this.B;
                                        final int n5 = n3;
                                        b[n5] += this.s;
                                        if (!dx) {
                                            break Label_0143;
                                        }
                                    }
                                    this.A[n3] = this.A[n3 + 1] - n2;
                                    this.B[n3] = this.B[n3 + 1] - n2;
                                }
                                this.y[n3] = this.y[n3 + 1];
                                this.z[n3] = this.z[n3 + 1];
                                this.C[n3] = this.C[n3 + 1];
                                this.D[n3] = this.D[n3 + 1];
                                this.x[n3] = this.x[n3 + 1];
                                this.E[n3] = this.E[n3 + 1];
                                ++n3;
                            }
                            if (n3 < this.I - 1) {
                                continue Label_0125_Outer;
                            }
                            break;
                        }
                        --this.I;
                        this.a(this.I - 1);
                        this.repaint();
                        if (!dx) {
                            return;
                        }
                        continue;
                    }
                }
                else {
                    ++n;
                }
            }
            if (n >= this.I) {
                return;
            }
            continue;
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int k = this.K;
        if (n2 < this.r) {
            int i = 0;
            while (true) {
                Label_0116: {
                    if (!bm.dX) {
                        break Label_0116;
                    }
                    if (n < this.A[i] && n > this.B[i]) {
                        if (i == this.K) {
                            return true;
                        }
                        if (!this.D[i]) {
                            if (k != this.I) {
                                this.K = this.I;
                                this.c(k);
                            }
                            return true;
                        }
                        this.K = i;
                        this.c(k);
                        this.c(this.K);
                        return true;
                    }
                    else {
                        ++i;
                    }
                }
                if (i < this.I) {
                    continue;
                }
                break;
            }
        }
        else if (k != this.I) {
            this.K = this.I;
            this.c(k);
        }
        return false;
    }
    
    private static String f(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '0';
                    break;
                }
                case 1: {
                    c2 = '[';
                    break;
                }
                case 2: {
                    c2 = 'L';
                    break;
                }
                case 3: {
                    c2 = '\u001b';
                    break;
                }
                default: {
                    c2 = '=';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
