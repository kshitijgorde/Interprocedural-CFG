import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class e
{
    private k[][] jKc;
    private Rectangle kKc;
    private int lKc;
    private int mKc;
    private int nKc;
    private int oKc;
    private int pKc;
    private Rectangle[][] qKc;
    private Image rKc;
    private static String ta = "\uf865\uf84c\uf856\uf851\uf871\uf84a\uf855\uf846\uf851\uf850\uf864\uf851\uf84a\uf847\uf819\uf803\uf842\uf857\uf857\uf846\uf84e\uf853\uf857\uf850\uf819\uf803";
    
    e(final Rectangle kKc, final int lKc, final int mKc, final int nKc, final int oKc, final Image rKc) {
        this.kKc = kKc;
        this.lKc = lKc;
        this.mKc = mKc;
        this.nKc = nKc;
        this.oKc = oKc;
        this.rKc = rKc;
        this.qKc = new Rectangle[this.nKc][this.oKc];
        for (int i = 0; i < this.nKc; ++i) {
            for (int j = 0; j < this.oKc; ++j) {
                this.qKc[i][j] = new Rectangle(this.kKc.x + i * this.lKc, this.kKc.y + j * this.mKc, this.lKc, this.mKc);
            }
        }
        this.jKc = new k[this.nKc + 2][this.oKc + 2];
        for (int k = 0; k < this.nKc + 2; ++k) {
            for (int l = 0; l < this.oKc + 2; ++l) {
                if (k == 0 || l == 0 || k == this.nKc + 1 || l == this.oKc + 1) {
                    this.jKc[k][l] = new k(this, k, l, -1, null);
                }
                else {
                    this.jKc[k][l] = new k(this, k, l, -1, this.qKc[k - 1][l - 1]);
                }
            }
        }
    }
    
    boolean f() {
        this.pKc = 0;
        System.out.println(e.ta + this.b(0));
        for (int i = 1; i < this.nKc + 1; ++i) {
            for (int j = 1; j < this.oKc + 1; ++j) {
                this.jKc[i][j].IJc = false;
            }
        }
        return true;
    }
    
    int _() {
        int n = 0;
        for (int i = 1; i < this.nKc + 1; ++i) {
            for (int j = 1; j < this.oKc + 1; ++j) {
                final k k = this.jKc[i][j];
                if (k.IJc) {
                    for (int l = 1; l < this.nKc + 1; ++l) {
                        for (int n2 = 1; n2 < this.oKc + 1; ++n2) {
                            final k m = this.jKc[l][n2];
                            if (m.IJc && k.id == m.id && this._(k, m) != null) {
                                ++n;
                            }
                        }
                    }
                }
            }
        }
        return n / 2;
    }
    
    private int b(int n) {
        int n2 = 1;
        int n3 = 0;
        final int n4 = 4 + ++n / 5;
        final int[] array = new int[42];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i + 1;
        }
        for (int j = 1; j < this.nKc + 1; ++j) {
            for (int k = 1; k < this.oKc + 1; ++k) {
                this.jKc[j][k].IJc = false;
            }
        }
        final Vector vector = new Vector<k>();
        for (int l = 1; l < this.nKc + 1; ++l) {
            for (int n5 = 1; n5 < this.oKc + 1; ++n5) {
                vector.addElement(this.jKc[l][n5]);
            }
        }
        this._(vector);
        for (int n6 = 0; n6 < array.length; ++n6) {
            final k m = vector.elementAt(0);
            final int size = vector.size();
            int n7 = 1;
            while (n7 < size) {
                final k k2 = vector.elementAt(n7);
                if (this._(m, k2) != null && (!this.b(m, k2) || n2 != 0)) {
                    m.IJc = true;
                    k2.IJc = true;
                    m.id = array[n6];
                    k2.id = array[n6];
                    vector.removeElement(m);
                    vector.removeElement(k2);
                    if (this.b(m, k2) && ++n3 >= n4) {
                        n2 = 0;
                        break;
                    }
                    break;
                }
                else {
                    ++n7;
                }
            }
            if (!m.IJc) {
                int n8 = 1;
                while (n8 < size) {
                    final k k3 = vector.elementAt(n8);
                    if (!this.b(m, k3) || n2 != 0 || n8 == size - 1) {
                        m.IJc = true;
                        k3.IJc = true;
                        m.id = array[n6];
                        k3.id = array[n6];
                        vector.removeElement(m);
                        vector.removeElement(k3);
                        if (this.b(m, k3) && ++n3 >= n4) {
                            n2 = 0;
                            break;
                        }
                        break;
                    }
                    else {
                        ++n8;
                    }
                }
            }
        }
        if (this.g()) {
            return n;
        }
        if (n > 50) {
            return this.l(n);
        }
        return this.b(n);
    }
    
    private boolean b(final k k, final k i) {
        return (k.HJc == i.HJc && Math.abs(k.GJc - i.GJc) == 1) || (k.GJc == i.GJc && Math.abs(k.HJc - i.HJc) == 1) || ((k.HJc == i.HJc && (k.HJc == 1 || k.HJc == this.oKc)) || (k.GJc == i.GJc && (k.GJc == 1 || k.GJc == this.nKc)));
    }
    
    private void b(final int[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            final int _ = m._(i);
            final int n = array[_];
            array[_] = array[i];
            array[i] = n;
        }
    }
    
    private void _(final Vector vector) {
        for (int i = vector.size() - 1; i > 0; --i) {
            final int _ = m._(i);
            final Object element = vector.elementAt(_);
            vector.setElementAt(vector.elementAt(i), _);
            vector.setElementAt(element, i);
        }
    }
    
    private boolean g() {
        final Vector vector = new Vector<k>(this.nKc * this.oKc);
        final boolean[][] array = new boolean[this.nKc + 2][this.oKc + 2];
        for (int i = 1; i < this.nKc + 1; ++i) {
            for (int j = 1; j < this.oKc + 1; ++j) {
                array[i][j] = this.jKc[i][j].IJc;
            }
        }
        for (int k = 1; k < this.nKc + 1; ++k) {
        Label_0235:
            for (int l = 1; l < this.oKc + 1; ++l) {
                final k m = this.jKc[k][l];
                if (m.IJc) {
                    for (int n = 1; n < this.nKc + 1; ++n) {
                        for (int n2 = 1; n2 < this.oKc + 1; ++n2) {
                            final k k2 = this.jKc[n][n2];
                            if (k2.IJc && m != k2 && m.id == k2.id) {
                                m.IJc = false;
                                k2.IJc = false;
                                vector.addElement(m);
                                vector.addElement(k2);
                                continue Label_0235;
                            }
                        }
                    }
                }
            }
        }
        for (int n3 = 1; n3 < this.nKc + 1; ++n3) {
            for (int n4 = 1; n4 < this.oKc + 1; ++n4) {
                this.jKc[n3][n4].IJc = array[n3][n4];
            }
        }
        int size = vector.size();
        for (int n5 = 0; n5 < size; n5 += 2) {
            final k k3 = vector.elementAt(n5);
            final k k4 = vector.elementAt(n5 + 1);
            if (this._(k3, k4) != null) {
                k3.IJc = false;
                k4.IJc = false;
                vector.removeElementAt(n5);
                vector.removeElementAt(n5);
                size -= 2;
                n5 = -2;
            }
        }
        for (int n6 = 1; n6 < this.nKc + 1; ++n6) {
            for (int n7 = 1; n7 < this.oKc + 1; ++n7) {
                this.jKc[n6][n7].IJc = array[n6][n7];
            }
        }
        return size == 0;
    }
    
    void b(final int n) {
        this.pKc += n;
    }
    
    boolean h() {
        return this.pKc == this.nKc * this.oKc;
    }
    
    void b(final int n, final int n2, final boolean iJc) {
        this.jKc[n][n2].IJc = iJc;
    }
    
    k b(final int n, final int n2) {
        for (int i = 0; i < this.nKc; ++i) {
            for (int j = 0; j < this.oKc; ++j) {
                if (this.qKc[i][j].contains(n, n2) && this.jKc[i + 1][j + 1].IJc) {
                    return this.jKc[i + 1][j + 1];
                }
            }
        }
        return null;
    }
    
    void a(final Graphics graphics, final Image[] array) {
        for (int i = 0; i < this.nKc; ++i) {
            for (int j = 0; j < this.oKc; ++j) {
                if (this.jKc[i + 1][j + 1].IJc) {
                    graphics.drawImage(this.rKc, this.qKc[i][j].x, this.qKc[i][j].y, null);
                    graphics.drawImage(array[this.jKc[i + 1][j + 1].id - 1], this.qKc[i][j].x + 1, this.qKc[i][j].y + 1, null);
                }
            }
        }
    }
    
    boolean contains(final int n, final int n2) {
        return this.kKc.contains(n, n2);
    }
    
    k a() {
        for (int i = 0; i < this.nKc; ++i) {
            for (int j = 0; j < this.oKc; ++j) {
                final k k = this.jKc[i + 1][j + 1];
                if (k.IJc) {
                    for (int l = 0; l < this.nKc; ++l) {
                        for (int n = 0; n < this.oKc; ++n) {
                            final k m = this.jKc[l + 1][n + 1];
                            if (m.IJc && k.id == m.id && this._(k, m) != null) {
                                return k;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private String f() {
        return p._(this.jKc, this.nKc, this.oKc);
    }
    
    private int l(final int n) {
        final int[][] a = p.a(this.nKc, this.oKc);
        final int[] array = new int[42];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i + 1;
        }
        this.b(array);
        for (int j = 1; j < this.nKc + 1; ++j) {
            for (int k = 1; k < this.oKc + 1; ++k) {
                this.jKc[j][k].id = array[a[j][k] % 42];
            }
        }
        return n;
    }
    
    n _(final k k, final k i) {
        final int gJc = k.GJc;
        final int hJc = k.HJc;
        final int gJc2 = i.GJc;
        final int hJc2 = i.HJc;
        if (gJc == gJc2 && hJc == hJc2) {
            return null;
        }
        if (gJc == gJc2 && (Math.abs(hJc - hJc2) == 1 || this.b(hJc, hJc2, gJc))) {
            return new n(this, gJc, hJc, gJc2, hJc2, Math.abs(hJc - hJc2) == 1);
        }
        if (hJc == hJc2 && (Math.abs(gJc - gJc2) == 1 || this._(gJc, gJc2, hJc))) {
            return new n(this, gJc, hJc, gJc2, hJc2, Math.abs(gJc - gJc2) == 1);
        }
        if (!this.jKc[gJc][hJc2].IJc && this.b(hJc, hJc2, gJc) && this._(gJc, gJc2, hJc2)) {
            return new n(this, gJc, hJc, gJc, hJc2, gJc2, hJc2);
        }
        if (!this.jKc[gJc2][hJc].IJc && this.b(hJc, hJc2, gJc2) && this._(gJc, gJc2, hJc)) {
            return new n(this, gJc, hJc, gJc2, hJc, gJc2, hJc2);
        }
        final Vector b = this.b(gJc, hJc);
        final Vector b2 = this.b(gJc2, hJc2);
        final int size = b.size();
        final int size2 = b2.size();
        for (int j = 0; j < size; ++j) {
            final int[] array = b.elementAt(j);
            for (int l = 0; l < size2; ++l) {
                final int[] array2 = b2.elementAt(l);
                if ((array[0] == array2[0] && this.b(array[1], array2[1], array[0])) || (array[1] == array2[1] && this._(array[0], array2[0], array[1]))) {
                    return new n(this, gJc, hJc, array[0], array[1], array2[0], array2[1], gJc2, hJc2);
                }
            }
        }
        return null;
    }
    
    private Vector b(final int n, final int n2) {
        final Vector<int[]> vector = new Vector<int[]>();
        for (int n3 = n + 1; n3 < this.nKc + 2 && !this.jKc[n3][n2].IJc; ++n3) {
            vector.addElement(new int[] { n3, n2 });
        }
        for (int n4 = n2 + 1; n4 < this.oKc + 2 && !this.jKc[n][n4].IJc; ++n4) {
            vector.addElement(new int[] { n, n4 });
        }
        for (int n5 = n - 1; n5 >= 0 && !this.jKc[n5][n2].IJc; --n5) {
            vector.addElement(new int[] { n5, n2 });
        }
        for (int n6 = n2 - 1; n6 >= 0 && !this.jKc[n][n6].IJc; --n6) {
            vector.addElement(new int[] { n, n6 });
        }
        return vector;
    }
    
    private boolean b(final int n, final int n2, final int n3) {
        final int min = Math.min(n, n2);
        for (int max = Math.max(n, n2), i = min + 1; i < max; ++i) {
            if (this.jKc[n3][i].IJc) {
                return false;
            }
        }
        return true;
    }
    
    private boolean _(final int n, final int n2, final int n3) {
        final int min = Math.min(n, n2);
        for (int max = Math.max(n, n2), i = min + 1; i < max; ++i) {
            if (this.jKc[i][n3].IJc) {
                return false;
            }
        }
        return true;
    }
    
    static int a(final e e) {
        return e.lKc;
    }
    
    static int b(final e e) {
        return e.mKc;
    }
    
    static int _(final e e) {
        return e.nKc;
    }
    
    static int n(final e e) {
        return e.oKc;
    }
    
    static Rectangle _(final e e) {
        return e.kKc;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFF823);
        }
        return new String(array);
    }
    
    static {
        e.ta = a(e.ta);
    }
}
