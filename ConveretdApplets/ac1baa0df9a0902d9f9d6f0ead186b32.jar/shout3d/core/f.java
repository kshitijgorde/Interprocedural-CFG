// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import shout3d.math.MatUtil;

public class f implements Picker
{
    public static final int a = 3;
    protected boolean b;
    protected boolean c;
    protected boolean d;
    protected boolean e;
    protected float[] f;
    protected float[] g;
    protected float[] h;
    protected float[] i;
    protected float[] j;
    protected float[] k;
    protected float[] l;
    protected float[] m;
    protected float[] n;
    protected float[] o;
    protected boolean p;
    protected boolean q;
    protected float[] r;
    protected float[] s;
    protected boolean t;
    protected Node u;
    protected Node[] v;
    protected Node w;
    protected int x;
    protected int y;
    protected boolean z;
    protected CoreShout3DViewer A;
    protected g B;
    protected float[] C;
    protected Node[] D;
    protected float[] E;
    protected float[] F;
    protected float[] G;
    private Node[] H;
    private int I;
    private float[] J;
    private float[][] K;
    private int L;
    private float[] M;
    private float[] N;
    private float O;
    private float P;
    private float Q;
    int[] R;
    float[] S;
    float[] T;
    
    protected void a() {
        if (this.L > 0) {
            --this.L;
        }
    }
    
    protected void b() {
        if (!this.p) {
            this.d();
            System.arraycopy(this.j, 0, this.l, 0, 3);
            System.arraycopy(this.k, 0, this.m, 0, 3);
            this.h();
            MatUtil.multVecMatrix(this.r, this.l);
            MatUtil.multDirMatrix(this.r, this.m);
            this.p = true;
        }
    }
    
    protected boolean a(final float[] l, final float[] array) {
        this.l = l;
        this.m[0] = array[0] - l[0];
        this.m[1] = array[1] - l[1];
        this.m[2] = array[2] - l[2];
        if (MatUtil.normalize(this.m) == 0.0f) {
            this.p = false;
            this.q = false;
            this.t = false;
            return this.z = false;
        }
        this.p = true;
        this.q = false;
        this.t = false;
        this.z = false;
        return true;
    }
    
    protected boolean a(final IndexedFaceSet set, final float[] array, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        set.e();
        this.R = set.v;
        this.S = set.o;
        final float[] a = set.a;
        final float[] b = set.b;
        this.T = null;
        if (set.coord.getValue() != null && set.coord.getValue() instanceof Coordinate) {
            this.T = ((Coordinate)set.coord.getValue()).point.getValue();
        }
        if (this.T == null || this.T.length == 0) {
            return false;
        }
        if (this.R == null || this.R.length == 0) {
            return false;
        }
        float n7 = array[0];
        final float[] array2 = new float[3];
        boolean b2 = false;
        int n8 = 0;
        int n9 = 0;
        int n10 = -1;
        final float[] array3 = { n4, n5, n6 };
        final float normalize = MatUtil.normalize(array3);
        final float n11 = (normalize >= 0.0f) ? (1.0f / normalize) : 1.0f;
        for (int i = 0, n12 = 1, n13 = 2; i < this.R.length; ++i, ++n12, ++n13, ++n8) {
            final float n14 = this.S[i] * array3[0] + this.S[n12] * array3[1] + this.S[n13] * array3[2];
            if (n14 <= this.Q || n14 >= this.O) {
                final float n15 = n * this.S[i] + n2 * this.S[n12] + n3 * this.S[n13] + this.S[i + 3];
                if (!set.solid.g || n15 > 0.0f) {
                    final float n16 = -n15 / n14;
                    if (n16 > 0.0f && n16 * n11 < n7) {
                        array2[0] = n + array3[0] * n16;
                        array2[1] = n2 + array3[1] * n16;
                        array2[2] = n3 + array3[2] * n16;
                        if (array2[0] >= a[0] - this.O && array2[0] <= b[0] + this.O && array2[1] >= a[1] - this.O && array2[1] <= b[1] + this.O && array2[2] >= a[2] - this.O && array2[2] <= b[2] + this.O) {
                            final int n17 = i;
                            int n18 = n12;
                            for (int n19 = n13; this.R[n19] != -1; ++n19) {
                                final int n20 = set.q[n8];
                                final int n21 = set.q[n8 + 1];
                                final float n22 = this.T[this.R[n17] + n20];
                                final float n23 = this.T[this.R[n17] + n21];
                                final float n24 = array2[n20] - n22;
                                final float n25 = array2[n21] - n23;
                                final float n26 = this.T[this.R[n18] + n20] - n22;
                                final float n27 = this.T[this.R[n18] + n21] - n23;
                                final float n28 = this.T[this.R[n19] + n20] - n22;
                                final float n29 = this.T[this.R[n19] + n21] - n23;
                                if (n26 > this.Q && n26 < this.O) {
                                    final float n30 = n24 / n28;
                                    if (n30 >= 0.0f && n30 <= 1.0f) {
                                        final float n31 = (n25 - n30 * n29) / n27;
                                        if (n31 >= 0.0f && n31 + n30 <= 1.0f) {
                                            n7 = n16 * n11;
                                            this.M[0] = array2[0];
                                            this.M[1] = array2[1];
                                            this.M[2] = array2[2];
                                            this.N[0] = this.S[i];
                                            this.N[1] = this.S[n12];
                                            this.N[2] = this.S[n13];
                                            n10 = n9;
                                            b2 = true;
                                        }
                                    }
                                }
                                else {
                                    final float n32 = (n25 * n26 - n24 * n27) / (n29 * n26 - n28 * n27);
                                    if (n32 >= 0.0f && n32 <= 1.0f) {
                                        final float n33 = (n24 - n32 * n28) / n26;
                                        if (n33 >= 0.0f && n33 + n32 <= 1.0f) {
                                            n7 = n16 * n11;
                                            this.M[0] = array2[0];
                                            this.M[1] = array2[1];
                                            this.M[2] = array2[2];
                                            this.N[0] = this.S[i];
                                            this.N[1] = this.S[n12];
                                            this.N[2] = this.S[n13];
                                            n10 = n9;
                                            b2 = true;
                                        }
                                    }
                                }
                                ++n18;
                            }
                        }
                    }
                }
            }
            do {
                ++i;
                ++n12;
                ++n13;
                ++n8;
            } while (i < this.R.length && this.R[i] != -1);
            ++n9;
        }
        if (b2) {
            array[0] = n7;
            System.arraycopy(this.M, 0, array, 1, 3);
            System.arraycopy(this.N, 0, array, 4, 3);
            array[7] = n10;
            this.a(this.a(set));
            return true;
        }
        return false;
    }
    
    public Node[] pickClosestFromTo(final float[] array, final float[] array2) {
        if (!this.a(array, array2)) {
            return null;
        }
        return this.g();
    }
    
    protected Node[] a(final Node node) {
        final Node[] array = new Node[this.I + 1];
        if (this.H != null) {
            System.arraycopy(this.H, 0, array, 0, this.I);
        }
        array[array.length - 1] = node;
        return array;
    }
    
    public f(final CoreShout3DViewer a) {
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = new float[3];
        this.g = new float[3];
        this.h = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        this.i = new float[1];
        this.j = new float[3];
        this.k = new float[] { 0.0f, 0.0f, -1.0f };
        this.l = new float[3];
        this.m = new float[] { 0.0f, 0.0f, -1.0f };
        this.n = new float[3];
        this.o = new float[] { 0.0f, 0.0f, -1.0f };
        this.p = false;
        this.q = false;
        this.s = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.t = false;
        this.u = null;
        this.v = null;
        this.x = 0;
        this.y = 0;
        this.z = false;
        this.A = null;
        this.B = null;
        this.C = new float[8];
        this.E = new float[9];
        this.F = new float[9];
        this.G = new float[9];
        this.J = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        this.M = new float[3];
        this.N = new float[3];
        this.O = 1.0E-5f;
        this.P = 1.00001f;
        this.Q = -1.0E-5f;
        this.A = a;
        if (a.getRenderer() instanceof g) {
            this.B = (g)a.getRenderer();
        }
    }
    
    protected boolean a(final Node node, final float[] array, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (node == null) {
            return false;
        }
        if (node instanceof Group) {
            return this.a((Group)node, array, n, n2, n3, n4, n5, n6);
        }
        if (node instanceof Shape) {
            return this.a((Shape)node, array, n, n2, n3, n4, n5, n6);
        }
        return node instanceof IndexedFaceSet && this.a((IndexedFaceSet)node, array, n, n2, n3, n4, n5, n6);
    }
    
    protected void c() {
        if (this.I > 0) {
            --this.I;
        }
    }
    
    public Node[] pickClosest(final int n, final int n2) {
        this.a(n, n2);
        return this.g();
    }
    
    protected void d() {
        if (!this.z) {
            throw new Shout3DException("Shout3DPicker was asked for a camera ray but never got pixels");
        }
        if (!this.q) {
            this.b(this.x, this.y, this.j, this.k);
            this.q = true;
        }
    }
    
    protected boolean a(final Shape shape, final float[] array, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (shape.geometry.a != null) {
            this.b(shape);
            final boolean a = this.a(shape.geometry.a, array, n, n2, n3, n4, n5, n6);
            this.c();
            return a;
        }
        return false;
    }
    
    protected void a(final float[] array) {
        if (this.K == null) {
            this.L = 0;
            this.K = new float[10][];
        }
        else if (this.L == this.K.length) {
            final float[][] k = new float[this.K.length + 10][];
            System.arraycopy(this.K, 0, k, 0, this.K.length);
            this.K = k;
        }
        this.K[this.L] = array;
        ++this.L;
    }
    
    protected void a(final Node[] d) {
        this.D = d;
    }
    
    public void setScene(final Node u) {
        this.u = u;
        this.v = null;
    }
    
    public boolean wasPicked(final Node node) {
        if (this.D == null) {
            return false;
        }
        for (int i = 0; i < this.D.length; ++i) {
            if (this.D[i] == node) {
                return true;
            }
        }
        return false;
    }
    
    public void a(final int n, final int n2, final float[] array, final float[] array2) {
        this.b(n, n2, array, array2);
        this.h();
        MatUtil.multVecMatrix(this.r, array);
        MatUtil.multDirMatrix(this.r, array2);
    }
    
    protected boolean e() {
        if (this.u != null) {
            this.w = this.u;
        }
        else if (this.v == null || this.v.length == 0) {
            this.w = this.A.getScene();
        }
        else {
            this.w = this.v[this.v.length - 1];
        }
        if (this.w == null) {
            return false;
        }
        this.D = null;
        return this.f();
    }
    
    protected void a(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.p = false;
        this.q = false;
        this.t = false;
        this.z = true;
    }
    
    public void setPath(final Node[] v) {
        if (v == null || v.length > 0) {
            this.v = v;
            this.u = null;
            return;
        }
        throw new Shout3DException("Shout3DPicker does not accept 0-length paths. Use null instead");
    }
    
    protected boolean f() {
        this.C[0] = Float.MAX_VALUE;
        final float[] c = this.C;
        final int n = 1;
        final float[] c2 = this.C;
        final int n2 = 2;
        final float[] c3 = this.C;
        final int n3 = 3;
        final float[] c4 = this.C;
        final int n4 = 4;
        final float[] c5 = this.C;
        final int n5 = 5;
        final float[] c6 = this.C;
        final int n6 = 6;
        final float n7 = 0.0f;
        c5[n5] = (c6[n6] = n7);
        c3[n3] = (c4[n4] = n7);
        c[n] = (c2[n2] = n7);
        this.C[7] = -1.0f;
        if (!this.p) {
            this.b();
        }
        System.arraycopy(this.l, 0, this.n, 0, 3);
        System.arraycopy(this.m, 0, this.o, 0, 3);
        this.H = null;
        this.I = 0;
        this.L = 0;
        if (!this.t) {
            this.h();
        }
        this.a(this.r);
        if (this.v != null) {
            for (int i = 0; i < this.v.length - 1; ++i) {
                this.b(this.v[i]);
                if (this.v[i] instanceof Group) {
                    final float[] inverseMatrix = ((Group)this.v[i]).getInverseMatrix();
                    MatUtil.multVecMatrix(inverseMatrix, this.n);
                    MatUtil.multDirMatrix(inverseMatrix, this.o);
                    if (!this.v[i].getTypeName().equals("Group")) {
                        this.a(inverseMatrix);
                    }
                }
            }
        }
        final boolean a = this.a(this.w, this.C, this.n[0], this.n[1], this.n[2], this.o[0], this.o[1], this.o[2]);
        if (this.v != null && this.D != null) {
            final Node[] d = new Node[this.v.length + this.D.length - 1];
            System.arraycopy(this.v, 0, d, 0, this.v.length);
            System.arraycopy(this.D, 0, d, this.v.length - 1, this.D.length);
            this.D = d;
        }
        return a;
    }
    
    public boolean pickAny(final int n, final int n2) {
        this.a(n, n2);
        return this.e();
    }
    
    public boolean pickAnyFromTo(final float[] array, final float[] array2) {
        return this.a(array, array2) && this.e();
    }
    
    protected boolean a(final Group group, final float[] array, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (!group.j || group.hidden.g) {
            return false;
        }
        final float[] array2 = new float[3];
        final float[] array3 = new float[3];
        array2[0] = n;
        array2[1] = n2;
        array2[2] = n3;
        array3[0] = n4;
        array3[1] = n5;
        array3[2] = n6;
        final boolean equals = group.getTypeName().equals("Group");
        if (!equals) {
            final float[] a = group.a(this.K, this.L);
            MatUtil.b(a, array2);
            MatUtil.i(a, array3);
        }
        int n7 = 0;
        final float[] localBBoxMin = group.getLocalBBoxMin();
        final float[] localBBoxMax = group.getLocalBBoxMax();
        final float[] array4 = new float[8];
        if (array3[2] != 0.0f) {
            final float n8 = (((array3[2] > 0.0f) ? localBBoxMin[2] : localBBoxMax[2]) - array2[2]) / array3[2];
            if (n8 > 0.0f) {
                final float n9 = array2[0] + n8 * array3[0];
                final float n10 = array2[1] + n8 * array3[1];
                if (localBBoxMin[0] <= n9 && n9 <= localBBoxMax[0] && localBBoxMin[1] <= n10 && n10 <= localBBoxMax[1]) {
                    n7 = 1;
                }
            }
            if (n7 == 0) {
                final float n11 = (((array3[2] > 0.0f) ? localBBoxMax[2] : localBBoxMin[2]) - array2[2]) / array3[2];
                if (n11 > 0.0f) {
                    final float n12 = array2[0] + n11 * array3[0];
                    final float n13 = array2[1] + n11 * array3[1];
                    if (localBBoxMin[0] <= n12 && n12 <= localBBoxMax[0] && localBBoxMin[1] <= n13 && n13 <= localBBoxMax[1]) {
                        n7 = 1;
                    }
                }
            }
        }
        if (array3[0] != 0.0f && n7 == 0) {
            final float n14 = (((array3[0] > 0.0f) ? localBBoxMin[0] : localBBoxMax[0]) - array2[0]) / array3[0];
            if (n14 > 0.0f) {
                final float n15 = array2[1] + n14 * array3[1];
                final float n16 = array2[2] + n14 * array3[2];
                if (localBBoxMin[1] <= n15 && n15 <= localBBoxMax[1] && localBBoxMin[2] <= n16 && n16 <= localBBoxMax[2]) {
                    n7 = 1;
                }
            }
            if (n7 == 0) {
                final float n17 = (((array3[0] > 0.0f) ? localBBoxMax[0] : localBBoxMin[0]) - array2[0]) / array3[0];
                if (n17 > 0.0f) {
                    final float n18 = array2[1] + n17 * array3[1];
                    final float n19 = array2[2] + n17 * array3[2];
                    if (localBBoxMin[1] <= n18 && n18 <= localBBoxMax[1] && localBBoxMin[2] <= n19 && n19 <= localBBoxMax[2]) {
                        n7 = 1;
                    }
                }
            }
        }
        if (array3[1] != 0.0f && n7 == 0) {
            final float n20 = (((array3[1] > 0.0f) ? localBBoxMin[1] : localBBoxMax[1]) - array2[1]) / array3[1];
            if (n20 > 0.0f) {
                final float n21 = array2[0] + n20 * array3[0];
                final float n22 = array2[2] + n20 * array3[2];
                if (localBBoxMin[0] <= n21 && n21 <= localBBoxMax[0] && localBBoxMin[2] <= n22 && n22 <= localBBoxMax[2]) {
                    n7 = 1;
                }
            }
            if (n7 == 0) {
                final float n23 = (((array3[1] > 0.0f) ? localBBoxMax[1] : localBBoxMin[1]) - array2[1]) / array3[1];
                if (n23 > 0.0f) {
                    final float n24 = array2[0] + n23 * array3[0];
                    final float n25 = array2[2] + n23 * array3[2];
                    if (localBBoxMin[0] <= n24 && n24 <= localBBoxMax[0] && localBBoxMin[2] <= n25 && n25 <= localBBoxMax[2]) {
                        n7 = 1;
                    }
                }
            }
        }
        boolean b = false;
        if (n7 != 0 && group.children.a != null) {
            this.b(group);
            if (!equals) {
                this.a(group.getInverseMatrix());
            }
            array4[0] = Float.MAX_VALUE;
            for (int i = 0; i < group.children.a.length; ++i) {
                final Node[] d = this.D;
                if (this.a(group.children.a[i], array4, array2[0], array2[1], array2[2], array3[0], array3[1], array3[2])) {
                    if (array4[0] < array[0]) {
                        array[0] = array4[0];
                        array[1] = array4[1];
                        array[2] = array4[2];
                        array[3] = array4[3];
                        array[4] = array4[4];
                        array[5] = array4[5];
                        array[6] = array4[6];
                        array[7] = array4[7];
                        b = true;
                    }
                    else {
                        this.a(d);
                    }
                    array4[0] = Float.MAX_VALUE;
                }
            }
            this.c();
            if (!equals) {
                this.a();
            }
        }
        return b;
    }
    
    protected Node[] g() {
        this.e();
        boolean b = false;
        if (this.d || this.c || this.b) {
            b = true;
        }
        if (b) {
            MatUtil.c(this.h);
            if (this.D != null) {
                for (int i = this.D.length - 1; i >= 0; --i) {
                    if (this.D[i] instanceof Group) {
                        this.h = MatUtil.multMatrix44byMatrix44(this.h, ((Group)this.D[i]).getMatrix());
                    }
                }
            }
        }
        if (this.b) {
            this.f[0] = this.C[1];
            this.f[1] = this.C[2];
            this.f[2] = this.C[3];
            MatUtil.multVecMatrix(this.h, this.f);
        }
        if (this.c) {
            final float n = this.C[4];
            final float n2 = this.C[5];
            final float n3 = this.C[6];
            MatUtil.a(this.h, this.E);
            MatUtil.g(this.E, this.F);
            MatUtil.f(this.F, this.G);
            this.g[0] = n * this.G[0] + n2 * this.G[3] + n3 * this.G[6];
            this.g[1] = n * this.G[1] + n2 * this.G[4] + n3 * this.G[7];
            this.g[2] = n * this.G[2] + n2 * this.G[5] + n3 * this.G[8];
            MatUtil.normalize(this.g);
        }
        if (this.e) {
            this.i[0] = this.C[7];
        }
        return this.D;
    }
    
    protected void b(final int n, final int n2, final float[] array, final float[] array2) {
        final int n3 = 0;
        final int n4 = 1;
        final int n5 = 2;
        final float n6 = 0.0f;
        array[n5] = n6;
        array[n3] = (array[n4] = n6);
        array2[0] = n - this.B.bt + 0.5f;
        array2[1] = -1.0f * (n2 - this.B.bu + 0.5f);
        array2[2] = this.B.bB;
        MatUtil.normalize(array2);
    }
    
    protected void b(final Node node) {
        if (this.H == null) {
            this.I = 0;
            this.H = new Node[10];
        }
        else if (this.I == this.H.length) {
            final Node[] h = new Node[this.H.length + 10];
            System.arraycopy(this.H, 0, h, 0, this.H.length);
            this.H = h;
        }
        this.H[this.I] = node;
        ++this.I;
    }
    
    public void setPickInfo(final int n, final boolean b) {
        if (n == 0) {
            this.b = b;
        }
        if (n == 1) {
            this.c = b;
        }
        if (n == 2) {
            this.d = b;
        }
        if (n == 3) {
            this.e = b;
        }
    }
    
    public float[] getPickInfo(final int n) throws Shout3DException {
        if (n == 0) {
            if (this.b) {
                return this.f;
            }
            return null;
        }
        else if (n == 1) {
            if (this.c) {
                return this.g;
            }
            return null;
        }
        else if (n == 2) {
            if (this.d) {
                return this.h;
            }
            return null;
        }
        else {
            if (n != 3) {
                throw new Shout3DException("Shout3DPicker.getPickInfo got bad value for which");
            }
            if (this.e) {
                return this.i;
            }
            return null;
        }
    }
    
    protected void h() {
        if (!this.t) {
            this.r = this.B.Y.getCameraToParent();
            final Node[] z = this.B.Z;
            if (z != null && z.length > 1) {
                for (int i = z.length - 2; i >= 0; --i) {
                    if (z[i] instanceof Group) {
                        this.r = MatUtil.multMatrix44byMatrix44(this.r, ((Group)z[i]).getMatrix());
                    }
                }
            }
            this.t = true;
        }
    }
}
