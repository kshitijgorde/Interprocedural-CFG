import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class a extends Canvas
{
    public int a;
    public int b;
    private int[] c;
    private int d;
    public boolean e;
    private Image f;
    private int g;
    private int h;
    private jz i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int[] n;
    private Rectangle o;
    private Color p;
    private int q;
    private int r;
    
    public a(final jz i, final int a, final int n) {
        this.b = -1;
        this.d = 0;
        this.j = 10000;
        this.k = -1;
        this.l = 10000;
        this.m = -1;
        this.p = null;
        this.i = i;
        this.a = a;
        this.e = ((n & 0xF0F0F0) == 0x0);
        this.o = new Rectangle();
    }
    
    public void a(final Graphics graphics) {
        final Rectangle ab = this.i.ab;
        graphics.drawImage(this.f, this.o.x - ab.x, this.o.y - ab.y, this.i);
        if (this.p != null) {
            graphics.setColor(this.p);
            graphics.drawLine(this.o.x - ab.x, this.o.y - ab.y, this.o.x - ab.x, this.o.y - ab.y);
        }
    }
    
    public void a(final Graphics graphics, final Rectangle rectangle) {
        if (this.o.intersects(rectangle)) {
            this.a(graphics);
        }
    }
    
    public int a() {
        return this.o.width * this.o.height;
    }
    
    public boolean a(final int n, final int n2) {
        if (this.o.contains(n, n2) && this.n[n - this.o.x + (n2 - this.o.y) * this.o.width] != 0) {
            this.g = n - this.o.x;
            this.h = n2 - this.o.y;
            return true;
        }
        return false;
    }
    
    public final void b() {
        this.i.repaint(this.o.x, this.o.y, this.o.width, this.o.height);
    }
    
    public void a(final int x, final int y, final boolean b) {
        final Rectangle rectangle = new Rectangle(this.o.x, this.o.y, this.o.width, this.o.height);
        this.o.x = x;
        this.o.y = y;
        final Rectangle union = this.o.union(rectangle);
        if (b) {
            this.i.repaint(union.x, union.y, union.width, union.height);
        }
    }
    
    public final void b(final int j, final int l) {
        if (j < this.j) {
            this.j = j;
        }
        if (l < this.l) {
            this.l = l;
        }
    }
    
    public final void c(final int k, final int m) {
        if (k > this.k) {
            this.k = k;
        }
        if (m > this.m) {
            this.m = m;
        }
    }
    
    public final void a(final a a) {
        if (a.j < this.j) {
            this.j = a.j;
        }
        if (a.m > this.m) {
            this.m = a.m;
        }
        if (a.l < this.l) {
            this.l = a.l;
        }
        if (a.k > this.k) {
            this.k = a.k;
        }
    }
    
    public static void a(final Vector vector) {
        final boolean a = jzBeanInfo.a;
        int i = 0;
        while (true) {
            while (i < vector.size()) {
                final a a2 = vector.elementAt(i);
                final int b = a2.b;
                final int n = -1;
                if (a) {
                    int j = b - n;
                    while (j >= 0) {
                        final a a3 = vector.elementAt(j);
                        if (a3.b != -1 || a3.e) {
                            vector.removeElementAt(j);
                        }
                        --j;
                        if (a) {
                            break;
                        }
                    }
                    return;
                }
                Label_0085: {
                    if (b == n || a) {
                        int k = 0;
                        while (k < a2.d) {
                            a2.a(vector.elementAt(a2.c[k]));
                            ++k;
                            if (a) {
                                break Label_0085;
                            }
                            if (a) {
                                break;
                            }
                        }
                    }
                    ++i;
                }
                if (a) {
                    break;
                }
            }
            vector.size();
            continue;
        }
    }
    
    public final void d(final int n, final int n2) {
        final Rectangle ab = this.i.ab;
        int x = n - this.g;
        final int n3 = ab.x + ab.width - this.o.width;
        if (x < ab.x) {
            x = ab.x;
        }
        if (x > n3) {
            x = n3;
        }
        int y = n2 - this.h;
        final int n4 = ab.y + ab.height - this.o.height;
        if (y < ab.y) {
            y = ab.y;
        }
        if (y > n4) {
            y = n4;
        }
        this.a(x, y, true);
    }
    
    public void a(final a a, final Vector vector) {
        if (a.b == this.a || a.a == this.a) {
            return;
        }
        if (this.b != -1) {
            vector.elementAt(this.b).a(a, vector);
            return;
        }
        if (this.c == null) {
            this.c = new int[50];
        }
        Label_0100: {
            if (a.b == -1) {
                this.b(a, vector);
                if (!jzBeanInfo.a) {
                    break Label_0100;
                }
            }
            this.b(vector.elementAt(a.b), vector);
        }
        a.b = this.a;
    }
    
    private void b(final a a, final Vector vector) {
        final boolean a2 = jzBeanInfo.a;
        while (a.d-- > 0) {
            final int n = a.c[a.d];
            this.c[this.d++] = n;
            vector.elementAt(n).b = this.a;
            if (a2) {
                return;
            }
            if (a2) {
                break;
            }
        }
        this.c[this.d++] = a.a;
        a.b = this.a;
    }
    
    private final boolean a(final int n) {
        final boolean a = jzBeanInfo.a;
        int n2 = n >>> 24;
        if ((n & 0x1) == 0x1) {
            n2 += 256;
        }
        if ((n & 0x100) == 0x100) {
            n2 += 512;
        }
        if (this.a == n2) {
            return true;
        }
        int i = 0;
        int n3 = 0;
        while (i < this.d) {
            n3 = this.c[i];
            if (a) {
                return n3 != 0;
            }
            if (n3 == n2) {
                return true;
            }
            ++i;
            if (a) {
                break;
            }
        }
        return n3 != 0;
    }
    
    private final boolean a(final a a, final int n, final int n2) {
        final int n3 = a.j - this.j;
        final int n4 = a.l - this.l;
        if (n >= n3 && n2 >= n4 && n < n3 + a.o.width && n2 < n4 + a.o.height) {
            if (n == n3 && n2 == n4 && a.p != null) {
                return true;
            }
            if (a.n[n - n3 + (n2 - n4) * a.o.width] != 0) {
                return true;
            }
        }
        return false;
    }
    
    public final void a(final int[] array, final int n, final int[] array2, final a a, final a a2) {
        final boolean a3 = jzBeanInfo.a;
        int n2 = 0;
        this.o.width = this.k - this.j + 1;
        this.o.height = this.m - this.l + 1;
        this.n = new int[this.o.width * this.o.height];
        int i = 0;
        while (true) {
            while (i < this.o.height) {
                final int n3 = 0;
                int n6 = 0;
                Label_0531: {
                    int[] n5 = null;
                    Label_0529: {
                        if (!a3) {
                            int j = n3;
                            while (j < this.o.width) {
                                final int n4 = (this.l + i) * n + (this.j + j);
                                n5 = array2;
                                if (a3) {
                                    break Label_0529;
                                }
                                Label_0207: {
                                    if ((array2 != null) ? this.a(array2[n4]) : (this.a(a, j, i) || this.a(a2, j, i))) {
                                        this.n[j + n2] = array[n4];
                                        if (!a3) {
                                            break Label_0207;
                                        }
                                    }
                                    this.n[j + n2] = 0;
                                }
                                ++j;
                                if (a3) {
                                    break;
                                }
                            }
                            ++i;
                            n2 += this.o.width;
                            if (a3) {
                                break;
                            }
                            continue;
                        }
                        else {
                            int k = n3;
                        Label_0517_Outer:
                            while (k < this.o.height) {
                                n6 = 0;
                                if (!a3) {
                                    int l = n6;
                                    while (true) {
                                        while (l < this.o.width) {
                                            final int n7 = l + n2;
                                            final int n8 = this.n[n7];
                                            final int n9 = 0;
                                            if (!a3) {
                                                if (n8 != n9 && (k == 0 || l == 0 || k == this.o.height - 1 || l == this.o.width - 1 || this.n[n7 - 1] == 0 || this.n[n7 + 1] == 0 || this.n[n7 - this.o.width] == 0 || this.n[n7 + this.o.width] == 0)) {
                                                    final int n10 = this.n[n7];
                                                    int n11 = (n10 & 0xFF0000) >> 16;
                                                    int n12 = (n10 & 0xFF00) >> 8;
                                                    int n13 = n10 & 0xFF;
                                                    n11 -= 102;
                                                    if (n11 < 0) {
                                                        n11 = 0;
                                                    }
                                                    n12 -= 102;
                                                    if (n12 < 0) {
                                                        n12 = 0;
                                                    }
                                                    n13 -= 102;
                                                    if (n13 < 0) {
                                                        n13 = 0;
                                                    }
                                                    this.n[n7] = -16777216 + (n11 << 16) + (n12 << 8) + n13;
                                                }
                                                ++l;
                                                if (a3) {
                                                    break;
                                                }
                                                continue Label_0517_Outer;
                                            }
                                            else {
                                                n2 = n8 + n9;
                                                if (a3) {
                                                    break Label_0517_Outer;
                                                }
                                                continue Label_0517_Outer;
                                            }
                                        }
                                        ++k;
                                        final int width = this.o.width;
                                        continue;
                                    }
                                }
                                break Label_0531;
                            }
                            n5 = this.n;
                        }
                    }
                    final int n14 = n5[0];
                }
                if (n6 != 0) {
                    this.p = new Color(this.n[0]);
                    this.n[0] = 0;
                }
                this.f = this.i.createImage(new MemoryImageSource(this.o.width, this.o.height, this.n, 0, this.o.width));
                return;
            }
            n2 = 0;
            continue;
        }
    }
    
    public boolean b(final Vector vector) {
        final boolean a = jzBeanInfo.a;
        int i = 1;
        boolean b = false;
        while (i < vector.size()) {
            final a a2 = vector.elementAt(i);
            b = this.b(a2);
            if (a) {
                return b;
            }
            if (b) {
                this.c(a2, vector);
                return vector.size() == 1;
            }
            ++i;
            if (a) {
                break;
            }
        }
        return b;
    }
    
    private final boolean b(final a a) {
        final int n = (this.i.pb <= 16) ? 16 : ((this.i.pb <= 22) ? 9 : 5);
        final int n2 = this.o.x - this.j;
        final int n3 = a.o.x - a.j;
        if (n3 >= n2 + n || n3 <= n2 - n) {
            return false;
        }
        final int n4 = a.o.y - a.l;
        final int n5 = this.o.y - this.l;
        if (n4 >= n5 + n || n4 <= n5 - n) {
            return false;
        }
        if (!a.o.intersects(new Rectangle(this.o.x - 2, this.o.y - 2, this.o.width + 4, this.o.height + 4))) {
            return false;
        }
        this.a(this.o.x + n3 - n2, this.o.y + n4 - n5, true);
        return true;
    }
    
    public final a c(final a a, final Vector vector) {
        final a a2 = new a(this.i, 0, 0);
        a2.j = ((this.j < a.j) ? this.j : a.j);
        a2.k = ((this.k > a.k) ? this.k : a.k);
        a2.l = ((this.l < a.l) ? this.l : a.l);
        a2.m = ((this.m > a.m) ? this.m : a.m);
        a2.o.x = this.o.x - (this.j - a2.j);
        a2.o.y = this.o.y - (this.l - a2.l);
        a2.a(this.i.W, 400, null, this, a);
        vector.removeElement(a);
        vector.setElementAt(a2, vector.indexOf(this));
        this.i.f();
        a2.b();
        return a2;
    }
    
    public final a c(final Vector vector) {
        final boolean a = jzBeanInfo.a;
        int n = 0;
        int i = 0;
        a a3 = null;
        while (i < vector.size()) {
            final a a2 = a3 = vector.elementAt(i);
            if (a) {
                return a3;
            }
            if (a3 != this || a) {
                final int e = this.e(a2);
                if (e > n) {
                    n = e;
                }
            }
            ++i;
            if (a) {
                break;
            }
        }
        return a3;
    }
    
    private final void c(final a a) {
        this.q = a.q + this.j - a.j;
        this.r = a.r + this.l - a.l;
    }
    
    public final void d(final a a) {
        final Rectangle ab = this.i.ab;
        this.q = this.o.x;
        this.r = this.o.y;
        a.c(this);
        if (a.q < ab.x || a.q + a.o.width > ab.x + ab.width || a.r < ab.y || a.r + a.o.height > ab.y + ab.height) {
            final int n = this.o.width / 2;
            final int n2 = this.o.height / 2;
            this.q = ab.x - n + (this.j + n) * ab.width / 400;
            this.r = ab.y - n2 + (this.l + n2) * ab.height / 300;
            a.c(this);
        }
    }
    
    private final int e(final a a) {
        final Rectangle rectangle = new Rectangle(this.j - 2, this.l - 2, this.k - this.j + 4, this.m - this.l + 4);
        final Rectangle rectangle2 = new Rectangle(a.j, a.l, a.k - a.j, a.m - a.l);
        if (!rectangle.intersects(rectangle2)) {
            return 0;
        }
        final Rectangle intersection = rectangle.intersection(rectangle2);
        return intersection.width * intersection.height;
    }
    
    public final boolean a(final boolean b) {
        final int n = this.q - this.o.x;
        final int n2 = this.r - this.o.y;
        if (n == 0 && n2 == 0) {
            return true;
        }
        final int n3 = (int)Math.sqrt(n * n + n2 * n2);
        if (n3 < 5) {
            this.a(this.q, this.r, b);
            if (!jzBeanInfo.a) {
                return false;
            }
        }
        final int n4 = 5 + n3 / 10;
        this.a(this.o.x + n * n4 / n3, this.o.y + n2 * n4 / n3, b);
        return false;
    }
    
    public void a(final boolean[] array) {
        final boolean a = jzBeanInfo.a;
        final int x = this.i.X;
        final int n = (this.o.width + x - 1) / x;
        final int n2 = (this.o.height + x - 1) / x;
        final boolean[] array2 = new boolean[n * n2];
        int n3 = 0;
        while (true) {
            do {
                int i = 0;
            Label_0476_Outer:
                while (i < n) {
                    final int n4 = 0;
                    if (a) {
                        int n5 = n4;
                        int n6 = 0;
                        int n7 = 0;
                        final int n8 = this.i.Y - n + 1;
                        final int n9 = this.i.Z - n2 + 1;
                        final int n10 = (int)(Math.random() * n8);
                        final int n11 = (int)(Math.random() * n9);
                        int n12 = 0;
                        while (true) {
                        Label_0475:
                            do {
                                int j = 0;
                            Label_0608_Outer:
                                while (j < n8) {
                                    final int n13 = 0;
                                    if (a) {
                                        int k = n13;
                                    Label_0478:
                                        while (true) {
                                        Label_0551:
                                            while (k < n) {
                                                if (!a) {
                                                    int l = 0;
                                                    while (true) {
                                                        while (l < n2) {
                                                            array[n6 + k + (n7 + l) * this.i.Y] = array2[k + l * n];
                                                            ++l;
                                                            if (!a) {
                                                                if (a) {
                                                                    break;
                                                                }
                                                                continue Label_0476_Outer;
                                                            }
                                                            else {
                                                                if (a) {
                                                                    break Label_0551;
                                                                }
                                                                continue Label_0478;
                                                            }
                                                        }
                                                        ++k;
                                                        continue Label_0608_Outer;
                                                    }
                                                }
                                                if (jz.Lb != 0) {
                                                    jzBeanInfo.a = !a;
                                                }
                                                return;
                                            }
                                            this.q = n6 * x;
                                            this.r = n7 * x;
                                            this.o.x = x * (this.i.Y - n) / 2;
                                            this.o.y = x * (this.i.Z - n2) / 2;
                                            continue;
                                        }
                                    }
                                    int n14 = n13;
                                Label_0467:
                                    while (n14 < n9) {
                                        int n15 = 0;
                                        final int n16 = (n10 + n12) % n8;
                                        final int n17 = (n11 + n14) % n9;
                                        j = 0;
                                        if (!a) {
                                            int n18 = j;
                                            while (true) {
                                            Label_0429:
                                                do {
                                                    int n19 = 0;
                                                Label_0337:
                                                    while (n19 < n) {
                                                        final int n20 = 0;
                                                        if (!a) {
                                                            int n21 = n20;
                                                            while (n21 < n2) {
                                                                n19 = (array[n16 + n18 + (n17 + n21) * this.i.Y] ? 1 : 0);
                                                                if (a) {
                                                                    continue Label_0337;
                                                                }
                                                                if (n19 != 0 && array2[n18 + n21 * n] && ++n15 >= n5 && !a) {
                                                                    break Label_0429;
                                                                }
                                                                ++n21;
                                                                if (a) {
                                                                    break;
                                                                }
                                                            }
                                                            ++n18;
                                                        }
                                                        else {
                                                            if (n20 < n5) {
                                                                n5 = n15;
                                                                n6 = n16;
                                                                n7 = n17;
                                                                if (n5 == 0 && !a) {
                                                                    break Label_0475;
                                                                }
                                                            }
                                                            ++n14;
                                                            if (a) {
                                                                break Label_0467;
                                                            }
                                                            continue Label_0467;
                                                        }
                                                    }
                                                    break;
                                                } while (!a);
                                                continue;
                                            }
                                        }
                                        continue Label_0608_Outer;
                                    }
                                    ++n12;
                                }
                                break;
                            } while (!a);
                            continue;
                        }
                    }
                    int n22 = n4;
                    do {
                        int n23 = 0;
                        int width = 0;
                    Label_0071:
                        while (n23 < width) {
                            i = n3 * x;
                            if (a) {
                                continue Label_0476_Outer;
                            }
                            int n24 = i;
                        Label_0205:
                            do {
                                int n25 = 0;
                                int height = 0;
                            Label_0095:
                                while (n25 < height) {
                                    n23 = n24;
                                    width = this.o.width;
                                    if (a) {
                                        continue Label_0071;
                                    }
                                    if (n23 >= width) {
                                        break;
                                    }
                                    int n26 = 0;
                                    while (n26 < (n26 = n22 * x) + x) {
                                        n25 = n26;
                                        height = this.o.height;
                                        if (a) {
                                            continue Label_0095;
                                        }
                                        if (n25 >= height) {
                                            break;
                                        }
                                        if (this.n[n24 + n26 * this.o.width] != 0) {
                                            array2[n3 + n22 * n] = true;
                                            if (!a) {
                                                break Label_0205;
                                            }
                                        }
                                        ++n26;
                                        if (a) {
                                            break;
                                        }
                                    }
                                    ++n24;
                                }
                                break;
                            } while (!a);
                            ++n22;
                        }
                        break;
                    } while (!a);
                    ++n3;
                }
                break;
            } while (!a);
            continue;
        }
    }
    
    public void a(final Rectangle rectangle, final Rectangle rectangle2) {
        int width = this.o.width / 2;
        Label_0068: {
            if (this.o.x < rectangle2.width / 3) {
                width = 0;
                if (!jzBeanInfo.a) {
                    break Label_0068;
                }
            }
            if (this.o.x + this.o.width > rectangle2.width / 3 * 2) {
                width = this.o.width;
            }
        }
        this.o.x = (this.o.x + width) * rectangle.width / rectangle2.width - width;
    }
}
