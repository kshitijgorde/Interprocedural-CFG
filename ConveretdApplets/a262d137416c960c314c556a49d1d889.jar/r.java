import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class r
{
    private break[][] B2;
    private Rectangle C2;
    private int D2;
    private int E2;
    private int F2;
    private int G2;
    private int H2;
    private Rectangle[][] I2;
    private Image J2;
    private static String z = "\u7048\u7061\u707b\u707c\u705c\u7067\u7078\u706b\u707c\u707d\u7049\u707c\u7067\u706a\u7034\u702e\u706f\u707a\u707a\u706b\u7063\u707e\u707a\u707d\u7034\u702e";
    
    r(final Rectangle c2, final int d2, final int e2, final int f2, final int g2, final Image j2) {
        this.C2 = c2;
        this.D2 = d2;
        this.E2 = e2;
        this.F2 = f2;
        this.G2 = g2;
        this.J2 = j2;
        this.I2 = new Rectangle[this.F2][this.G2];
        for (int i = 0; i < this.F2; ++i) {
            for (int k = 0; k < this.G2; ++k) {
                this.I2[i][k] = new Rectangle(this.C2.x + i * this.D2, this.C2.y + k * this.E2, this.D2, this.E2);
            }
        }
        this.B2 = new break[this.F2 + 2][this.G2 + 2];
        for (int l = 0; l < this.F2 + 2; ++l) {
            for (int n = 0; n < this.G2 + 2; ++n) {
                if (l == 0 || n == 0 || l == this.F2 + 1 || n == this.G2 + 1) {
                    this.B2[l][n] = new break(this, l, n, -1, null);
                }
                else {
                    this.B2[l][n] = new break(this, l, n, -1, this.I2[l - 1][n - 1]);
                }
            }
        }
    }
    
    boolean g() {
        this.H2 = 0;
        System.out.println(r.z + this.b(0));
        for (int i = 1; i < this.F2 + 1; ++i) {
            for (int j = 1; j < this.G2 + 1; ++j) {
                this.B2[i][j]._2 = false;
            }
        }
        return true;
    }
    
    int b() {
        int n = 0;
        for (int i = 1; i < this.F2 + 1; ++i) {
            for (int j = 1; j < this.G2 + 1; ++j) {
                final break break1 = this.B2[i][j];
                if (break1._2) {
                    for (int k = 1; k < this.F2 + 1; ++k) {
                        for (int l = 1; l < this.G2 + 1; ++l) {
                            final break break2 = this.B2[k][l];
                            if (break2._2 && break1.id == break2.id && this.a(break1, break2) != null) {
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
        for (int j = 1; j < this.F2 + 1; ++j) {
            for (int k = 1; k < this.G2 + 1; ++k) {
                this.B2[j][k]._2 = false;
            }
        }
        final Vector vector = new Vector<break>();
        for (int l = 1; l < this.F2 + 1; ++l) {
            for (int n5 = 1; n5 < this.G2 + 1; ++n5) {
                vector.addElement(this.B2[l][n5]);
            }
        }
        this._(vector);
        for (int n6 = 0; n6 < array.length; ++n6) {
            final break break1 = vector.elementAt(0);
            final int size = vector.size();
            int n7 = 1;
            while (n7 < size) {
                final break break2 = vector.elementAt(n7);
                if (this.a(break1, break2) != null && (!this.a(break1, break2) || n2 != 0)) {
                    break1._2 = true;
                    break2._2 = true;
                    break1.id = array[n6];
                    break2.id = array[n6];
                    vector.removeElement(break1);
                    vector.removeElement(break2);
                    if (this.a(break1, break2) && ++n3 >= n4) {
                        n2 = 0;
                        break;
                    }
                    break;
                }
                else {
                    ++n7;
                }
            }
            if (!break1._2) {
                int n8 = 1;
                while (n8 < size) {
                    final break break3 = vector.elementAt(n8);
                    if (!this.a(break1, break3) || n2 != 0 || n8 == size - 1) {
                        break1._2 = true;
                        break3._2 = true;
                        break1.id = array[n6];
                        break3.id = array[n6];
                        vector.removeElement(break1);
                        vector.removeElement(break3);
                        if (this.a(break1, break3) && ++n3 >= n4) {
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
        if (this.h()) {
            return n;
        }
        if (n > 50) {
            return this.e(n);
        }
        return this.b(n);
    }
    
    private boolean a(final break break1, final break break2) {
        return (break1.Z1 == break2.Z1 && Math.abs(break1.Y1 - break2.Y1) == 1) || (break1.Y1 == break2.Y1 && Math.abs(break1.Z1 - break2.Z1) == 1) || ((break1.Z1 == break2.Z1 && (break1.Z1 == 1 || break1.Z1 == this.G2)) || (break1.Y1 == break2.Y1 && (break1.Y1 == 1 || break1.Y1 == this.F2)));
    }
    
    private void b(final int[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            final int _ = catch._(i);
            final int n = array[_];
            array[_] = array[i];
            array[i] = n;
        }
    }
    
    private void _(final Vector vector) {
        for (int i = vector.size() - 1; i > 0; --i) {
            final int _ = catch._(i);
            final Object element = vector.elementAt(_);
            vector.setElementAt(vector.elementAt(i), _);
            vector.setElementAt(element, i);
        }
    }
    
    private boolean h() {
        final Vector vector = new Vector<break>(this.F2 * this.G2);
        final boolean[][] array = new boolean[this.F2 + 2][this.G2 + 2];
        for (int i = 1; i < this.F2 + 1; ++i) {
            for (int j = 1; j < this.G2 + 1; ++j) {
                array[i][j] = this.B2[i][j]._2;
            }
        }
        for (int k = 1; k < this.F2 + 1; ++k) {
        Label_0235:
            for (int l = 1; l < this.G2 + 1; ++l) {
                final break break1 = this.B2[k][l];
                if (break1._2) {
                    for (int n = 1; n < this.F2 + 1; ++n) {
                        for (int n2 = 1; n2 < this.G2 + 1; ++n2) {
                            final break break2 = this.B2[n][n2];
                            if (break2._2 && break1 != break2 && break1.id == break2.id) {
                                break1._2 = false;
                                break2._2 = false;
                                vector.addElement(break1);
                                vector.addElement(break2);
                                continue Label_0235;
                            }
                        }
                    }
                }
            }
        }
        for (int n3 = 1; n3 < this.F2 + 1; ++n3) {
            for (int n4 = 1; n4 < this.G2 + 1; ++n4) {
                this.B2[n3][n4]._2 = array[n3][n4];
            }
        }
        int size = vector.size();
        for (int n5 = 0; n5 < size; n5 += 2) {
            final break break3 = vector.elementAt(n5);
            final break break4 = vector.elementAt(n5 + 1);
            if (this.a(break3, break4) != null) {
                break3._2 = false;
                break4._2 = false;
                vector.removeElementAt(n5);
                vector.removeElementAt(n5);
                size -= 2;
                n5 = -2;
            }
        }
        for (int n6 = 1; n6 < this.F2 + 1; ++n6) {
            for (int n7 = 1; n7 < this.G2 + 1; ++n7) {
                this.B2[n6][n7]._2 = array[n6][n7];
            }
        }
        return size == 0;
    }
    
    void _(final int n) {
        this.H2 += n;
    }
    
    boolean i() {
        return this.H2 == this.F2 * this.G2;
    }
    
    void a(final int n, final int n2, final boolean 2) {
        this.B2[n][n2]._2 = 2;
    }
    
    break a(final int n, final int n2) {
        for (int i = 0; i < this.F2; ++i) {
            for (int j = 0; j < this.G2; ++j) {
                if (this.I2[i][j].contains(n, n2) && this.B2[i + 1][j + 1]._2) {
                    return this.B2[i + 1][j + 1];
                }
            }
        }
        return null;
    }
    
    void a(final Graphics graphics, final Image[] array) {
        for (int i = 0; i < this.F2; ++i) {
            for (int j = 0; j < this.G2; ++j) {
                if (this.B2[i + 1][j + 1]._2) {
                    graphics.drawImage(this.J2, this.I2[i][j].x, this.I2[i][j].y, null);
                    graphics.drawImage(array[this.B2[i + 1][j + 1].id - 1], this.I2[i][j].x + 1, this.I2[i][j].y + 1, null);
                }
            }
        }
    }
    
    boolean contains(final int n, final int n2) {
        return this.C2.contains(n, n2);
    }
    
    break b() {
        for (int i = 0; i < this.F2; ++i) {
            for (int j = 0; j < this.G2; ++j) {
                final break break1 = this.B2[i + 1][j + 1];
                if (break1._2) {
                    for (int k = 0; k < this.F2; ++k) {
                        for (int l = 0; l < this.G2; ++l) {
                            final break break2 = this.B2[k + 1][l + 1];
                            if (break2._2 && break1.id == break2.id && this.a(break1, break2) != null) {
                                return break1;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private String l() {
        return continue.b(this.B2, this.F2, this.G2);
    }
    
    private int e(final int n) {
        final int[][] a = continue.a(this.F2, this.G2);
        final int[] array = new int[42];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i + 1;
        }
        this.b(array);
        for (int j = 1; j < this.F2 + 1; ++j) {
            for (int k = 1; k < this.G2 + 1; ++k) {
                this.B2[j][k].id = array[a[j][k] % 42];
            }
        }
        return n;
    }
    
    class a(final break break1, final break break2) {
        final int y1 = break1.Y1;
        final int z1 = break1.Z1;
        final int y2 = break2.Y1;
        final int z2 = break2.Z1;
        if (y1 == y2 && z1 == z2) {
            return null;
        }
        if (y1 == y2 && (Math.abs(z1 - z2) == 1 || this.a(z1, z2, y1))) {
            return new class(this, y1, z1, y2, z2, Math.abs(z1 - z2) == 1);
        }
        if (z1 == z2 && (Math.abs(y1 - y2) == 1 || this.b(y1, y2, z1))) {
            return new class(this, y1, z1, y2, z2, Math.abs(y1 - y2) == 1);
        }
        if (!this.B2[y1][z2]._2 && this.a(z1, z2, y1) && this.b(y1, y2, z2)) {
            return new class(this, y1, z1, y1, z2, y2, z2);
        }
        if (!this.B2[y2][z1]._2 && this.a(z1, z2, y2) && this.b(y1, y2, z1)) {
            return new class(this, y1, z1, y2, z1, y2, z2);
        }
        final Vector a = this.a(y1, z1);
        final Vector a2 = this.a(y2, z2);
        final int size = a.size();
        final int size2 = a2.size();
        for (int i = 0; i < size; ++i) {
            final int[] array = a.elementAt(i);
            for (int j = 0; j < size2; ++j) {
                final int[] array2 = a2.elementAt(j);
                if ((array[0] == array2[0] && this.a(array[1], array2[1], array[0])) || (array[1] == array2[1] && this.b(array[0], array2[0], array[1]))) {
                    return new class(this, y1, z1, array[0], array[1], array2[0], array2[1], y2, z2);
                }
            }
        }
        return null;
    }
    
    private Vector a(final int n, final int n2) {
        final Vector<int[]> vector = new Vector<int[]>();
        for (int n3 = n + 1; n3 < this.F2 + 2 && !this.B2[n3][n2]._2; ++n3) {
            vector.addElement(new int[] { n3, n2 });
        }
        for (int n4 = n2 + 1; n4 < this.G2 + 2 && !this.B2[n][n4]._2; ++n4) {
            vector.addElement(new int[] { n, n4 });
        }
        for (int n5 = n - 1; n5 >= 0 && !this.B2[n5][n2]._2; --n5) {
            vector.addElement(new int[] { n5, n2 });
        }
        for (int n6 = n2 - 1; n6 >= 0 && !this.B2[n][n6]._2; --n6) {
            vector.addElement(new int[] { n, n6 });
        }
        return vector;
    }
    
    private boolean a(final int n, final int n2, final int n3) {
        final int min = Math.min(n, n2);
        for (int max = Math.max(n, n2), i = min + 1; i < max; ++i) {
            if (this.B2[n3][i]._2) {
                return false;
            }
        }
        return true;
    }
    
    private boolean b(final int n, final int n2, final int n3) {
        final int min = Math.min(n, n2);
        for (int max = Math.max(n, n2), i = min + 1; i < max; ++i) {
            if (this.B2[i][n3]._2) {
                return false;
            }
        }
        return true;
    }
    
    static int b(final r r) {
        return r.D2;
    }
    
    static int _(final r r) {
        return r.E2;
    }
    
    static int a(final r r) {
        return r.F2;
    }
    
    static int m(final r r) {
        return r.G2;
    }
    
    static Rectangle a(final r r) {
        return r.C2;
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF700E);
        }
        return new String(array);
    }
    
    static {
        r.z = _(r.z);
    }
}
