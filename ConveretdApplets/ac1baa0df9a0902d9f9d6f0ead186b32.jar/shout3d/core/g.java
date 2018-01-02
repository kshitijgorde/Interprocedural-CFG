// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.image.ImageProducer;
import java.awt.Panel;
import java.awt.image.PixelGrabber;
import shout3d.math.MatUtil;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.util.Vector;

public class g implements Renderer
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    private w g;
    x h;
    private boolean i;
    private boolean j;
    float[] k;
    float l;
    protected Vector m;
    protected Vector n;
    protected boolean o;
    protected boolean p;
    protected boolean q;
    private short r;
    private short s;
    private float t;
    protected short u;
    protected short v;
    protected short[][] w;
    private Color x;
    Component y;
    NavigationInfo z;
    DirectionalLight A;
    Background B;
    boolean C;
    int D;
    int E;
    int[] F;
    int G;
    Graphics H;
    Image I;
    Graphics J;
    Image K;
    Graphics L;
    Image M;
    MemoryImageSource N;
    int[] O;
    int[] P;
    float[] Q;
    float[] R;
    int S;
    int T;
    int U;
    int V;
    Transform W;
    Viewpoint X;
    Viewpoint Y;
    Node[] Z;
    private float[] ba;
    private float[] bb;
    Appearance bc;
    Vector bd;
    Vector be;
    boolean bf;
    float bg;
    float bh;
    float bi;
    float bj;
    int bk;
    int bl;
    int bm;
    int bn;
    int bo;
    int bp;
    int bq;
    float br;
    float bs;
    float bt;
    float bu;
    float bv;
    float bw;
    int bx;
    int[] by;
    float[] bz;
    float[] bA;
    float bB;
    float bC;
    int bD;
    int bE;
    int bF;
    int bG;
    int bH;
    float[] bI;
    private int bJ;
    private int bK;
    private Component bL;
    boolean bM;
    int bN;
    int bO;
    int bP;
    int bQ;
    int bR;
    int bS;
    int bT;
    int bU;
    private j[] bV;
    private int bW;
    private static final int bX = 100;
    private float[] bY;
    private Node[] bZ;
    private int ca;
    boolean cb;
    Node[] cc;
    int cd;
    int ce;
    
    public void a(final boolean c) {
        this.C = c;
    }
    
    public Viewpoint a() {
        return this.Y;
    }
    
    public void a(final float[] ba) {
        this.ba = ba;
    }
    
    public float[] b() {
        return this.ba;
    }
    
    void b(final boolean i) {
        this.i = i;
    }
    
    public void c() {
        if (this.ca > 0) {
            --this.ca;
        }
    }
    
    public Appearance d() {
        return this.bc;
    }
    
    public void a(final Appearance bc) {
        this.bc = bc;
    }
    
    public void a(final Group group) {
        final h h = new h();
        for (int i = this.m.size() - 1; i >= 0; --i) {
            if (this.m.elementAt(i) instanceof Node) {
                h.setNode((Node)this.m.elementAt(i));
                if (h.searchFirst(group) == null) {
                    this.removeRenderObserver((RenderObserver)this.m.elementAt(i));
                }
            }
        }
    }
    
    public boolean e() {
        return this.q;
    }
    
    public void a(final Background b) {
        this.D();
        this.B = b;
    }
    
    public Component f() {
        return this.y;
    }
    
    void c(final boolean j) {
        this.j = j;
    }
    
    boolean g() {
        return this.i;
    }
    
    public float[] h() {
        return this.k;
    }
    
    protected void i() {
        if (this.B == null) {
            this.C = false;
            this.G = -16777216;
            for (int i = 0; i < this.bq; ++i) {
                this.F[i] = this.G;
            }
            this.L.setColor(Color.black);
            this.L.fillRect(0, 0, this.bk, this.bl);
            System.arraycopy(this.F, 0, this.O, 0, this.bq);
            return;
        }
        this.B.b(this);
    }
    
    int a(final float[] array, final float[] array2) {
        if (array[2] > 0.0f) {
            return 0;
        }
        if (array[2] <= 0.0f && array2[2] >= 0.0f && array[0] <= 0.0f && array2[0] >= 0.0f && array[1] <= 0.0f && array2[1] >= 0.0f) {
            return 2;
        }
        this.bM = false;
        if (this.bg * array2[0] - this.bi * array[2] < -1.0E-4f) {
            return 0;
        }
        if (this.bg * array[0] - this.bi * array2[2] > 1.0E-4f) {
            if (-this.bg * array[0] - this.bi * array[2] < -1.0E-4f) {
                return 0;
            }
            if (-this.bg * array2[0] - this.bi * array2[2] <= 1.0E-4f) {
                this.bM = true;
            }
        }
        else {
            this.bM = true;
        }
        if (this.bh * array2[1] - this.bj * array[2] < -1.0E-4f) {
            return 0;
        }
        if (this.bh * array[1] - this.bj * array2[2] <= 1.0E-4f) {
            return 2;
        }
        if (-this.bh * array[1] - this.bj * array[2] < -1.0E-4f) {
            return 0;
        }
        if (-this.bh * array2[1] - this.bj * array2[2] <= 1.0E-4f) {
            return 2;
        }
        if (this.bM) {
            return 2;
        }
        return 1;
    }
    
    public void removeRenderObserver(final RenderObserver renderObserver) {
        final int index = this.m.indexOf(renderObserver);
        this.m.removeElement(renderObserver);
        this.n.removeElementAt(index);
    }
    
    public void a(final Group group, final NavigationInfo z) {
        this.z = z;
        if (this.A != null) {
            group.removeChildren(new Node[] { this.A });
        }
        if (z != null) {
            if (this.A == null) {
                this.A = new DirectionalLight();
                this.A.isHeadlight = true;
            }
            group.addChildren(new Node[] { this.A });
            this.A.on.setValue(z.headlight.getValue());
        }
    }
    
    void j() {
        if (this.bW == 0) {
            return;
        }
        MatUtil.b(this.bY, this.bV, 0, this.bW);
        for (int i = 0; i < this.bW; ++i) {
            if (this.bV[i] != null) {
                final IndexedFaceSet a = this.bV[i].a;
                if (a != null) {
                    a.a(this.bV[i].b, this.bV[i].c, this.bV[i].d, this.bV[i].e);
                }
            }
        }
        this.bW = 0;
    }
    
    public int l() {
        return this.bq;
    }
    
    public void addRenderObserver(final RenderObserver renderObserver, final Object o) {
        this.m.addElement(renderObserver);
        this.n.addElement(o);
    }
    
    void m() {
        int n = 0;
        int bm = this.bm;
        int n2 = 2 * this.bm;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        final float n6 = 0.5f;
        final float n7 = 0.25f;
        final float n8 = 0.25f;
        final float n9 = 0.75f;
        final float n10 = 0.25f;
        for (int i = 0; i < this.bn - 2; ++i) {
            for (int j = 0; j < this.bm - 1; ++j) {
                boolean b = false;
                boolean b2 = false;
                int n11 = 0;
                if (this.Q[n] != 0.0f) {
                    n11 |= 0xF0000000;
                }
                if (this.Q[n + 1] != 0.0f) {
                    n11 |= 0xF000000;
                }
                if (this.Q[bm] != 0.0f) {
                    n11 |= 0xF00000;
                }
                if (this.Q[bm + 1] != 0.0f) {
                    n11 |= 0xF0000;
                }
                if (this.Q[n + 2] != 0.0f) {
                    n11 |= 0xF000;
                }
                if (this.Q[bm + 2] != 0.0f) {
                    n11 |= 0xF00;
                }
                if (this.Q[n2] != 0.0f) {
                    n11 |= 0xF0;
                }
                if (this.Q[n2 + 1] != 0.0f) {
                    n11 |= 0xF;
                }
                if (n11 >> 16 == -1 || n11 >> 16 == 0) {
                    ++n;
                    ++bm;
                    ++n2;
                }
                else {
                    switch (n11 >> 16) {
                        case -4096:
                        case 4095: {
                            n5 = n;
                            n3 = n + 1;
                            n4 = bm;
                            b = true;
                            break;
                        }
                        case -3841:
                        case 3840: {
                            n5 = n + 1;
                            n3 = n;
                            n4 = bm + 1;
                            b = true;
                            break;
                        }
                        case -16:
                        case 15: {
                            n5 = bm + 1;
                            n3 = n + 1;
                            n4 = bm;
                            b = true;
                            break;
                        }
                        case -241:
                        case 240: {
                            n5 = bm;
                            n3 = n;
                            n4 = bm + 1;
                            b = true;
                            break;
                        }
                    }
                    if (b) {
                        final int n12 = this.O[n5];
                        final int n13 = this.O[n3];
                        final int n14 = this.O[n4];
                        this.O[n5] = -16777216 + ((int)((n12 >> 16 & 0xFF) * n6 + (n13 >> 16 & 0xFF) * n7 + (n14 >> 16 & 0xFF) * n8) << 16) + ((int)((n12 >> 8 & 0xFF) * n6 + (n13 >> 8 & 0xFF) * n7 + (n14 >> 8 & 0xFF) * n8) << 8) + (int)((n12 & 0xFF) * n6 + (n13 & 0xFF) * n7 + (n14 & 0xFF) * n8);
                    }
                    switch (n11 >> 8) {
                        case -3856:
                        case 3855: {
                            n5 = bm + 2;
                            n3 = n + 2;
                            b2 = true;
                            break;
                        }
                        case -983281:
                        case 983280: {
                            n5 = n + 2;
                            n3 = bm + 2;
                            b2 = true;
                            break;
                        }
                        case -65536:
                        case 65535: {
                            n5 = n;
                            n3 = bm;
                            b2 = true;
                            break;
                        }
                        case -65281:
                        case 65280: {
                            n5 = bm;
                            n3 = n;
                            b2 = true;
                            break;
                        }
                    }
                    switch ((n11 & 0xFF) | (n11 >> 8 & 0xFFFF00)) {
                        case 3855:
                        case 16773360: {
                            n5 = n2 + 1;
                            n3 = n2;
                            b2 = true;
                            break;
                        }
                        case 61680:
                        case 16715535: {
                            n5 = n2;
                            n3 = n2 + 1;
                            b2 = true;
                            break;
                        }
                        case 987135:
                        case 15790080: {
                            n5 = n;
                            n3 = n + 1;
                            b2 = true;
                            break;
                        }
                        case 986880:
                        case 15790335: {
                            n5 = n + 1;
                            n3 = n;
                            b2 = true;
                            break;
                        }
                    }
                    if (b2) {
                        final int n15 = this.O[n5];
                        final int n16 = this.O[n3];
                        this.O[n5] = -16777216 + ((int)((n15 >> 16 & 0xFF) * n9 + (n16 >> 16 & 0xFF) * n10) << 16) + ((int)((n15 >> 8 & 0xFF) * n9 + (n16 >> 8 & 0xFF) * n10) << 8) + (int)((n15 & 0xFF) * n9 + (n16 & 0xFF) * n10);
                    }
                    ++n;
                    ++bm;
                    ++n2;
                }
            }
            ++n;
            ++bm;
            ++n2;
        }
    }
    
    public Image n() {
        return this.I;
    }
    
    void o() {
        for (int i = 0; i < this.bW; ++i) {
            if (this.bV[i] != null) {
                this.bV[i].a = null;
                this.bV[i] = null;
            }
        }
    }
    
    protected int[] a(final Image image, final int n, final int n2, final int n3, final int n4) {
        final int[] array = new int[n3 * n4];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n3);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println(ex);
        }
        return array;
    }
    
    public boolean p() {
        return this.o;
    }
    
    public void a(final Node node) {
        if (this.bZ == null) {
            this.ca = 0;
            this.bZ = new Node[10];
        }
        else if (this.ca == this.bZ.length) {
            final Node[] bz = new Node[this.bZ.length + 10];
            System.arraycopy(this.bZ, 0, bz, 0, this.bZ.length);
            this.bZ = bz;
        }
        this.bZ[this.ca] = node;
        ++this.ca;
    }
    
    public int q() {
        return this.bm;
    }
    
    public Node a(final int n) {
        return this.bZ[n];
    }
    
    protected void r() {
        for (short n = 0; n < this.s; ++n) {
            int n2 = 0;
            do {
                this.w[n][n2] = (short)Math.round((n2 - 255) * n / this.t);
            } while (++n2 < 512);
        }
        this.bI = new float[256];
        int n3 = 0;
        do {
            this.bI[n3] = n3 / 255.0f;
        } while (++n3 < 256);
        int n4 = 1;
        do {
            this.bz[n4] = 65536 / n4;
        } while (++n4 < 8);
        this.by = new int[this.bn];
        for (int i = 0; i < this.bn; ++i) {
            this.by[i] = (this.bn - 1 - i) * this.bm;
        }
        int n5 = 1;
        do {
            this.bz[n5] = 65536 / n5;
            this.bA[n5] = 65536 / n5;
        } while (++n5 < 8);
    }
    
    public boolean s() {
        return this.p;
    }
    
    public g(final Panel y, final Graphics h, final int n, final int n2) {
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = false;
        this.l = -1.0f;
        this.m = new Vector();
        this.n = new Vector();
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 4;
        this.s = (short)(1 << this.r);
        this.t = this.s - 1;
        this.u = this.r;
        this.v = (short)(16 - this.r);
        this.w = new short[this.s][512];
        this.x = new Color(255, 151, 15);
        this.z = null;
        this.A = null;
        this.C = false;
        this.G = -16777216;
        this.S = Integer.MAX_VALUE;
        this.T = -2147483647;
        this.U = Integer.MAX_VALUE;
        this.V = -2147483647;
        this.X = new Viewpoint(null);
        this.Y = this.X;
        this.bb = new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        this.bd = new Vector();
        this.be = new Vector();
        this.bf = false;
        this.bo = 0;
        this.bp = 0;
        this.bz = new float[8];
        this.bA = new float[8];
        this.bE = Integer.MAX_VALUE;
        this.bF = this.bE;
        this.bG = -this.bE;
        this.bH = this.bG;
        this.bJ = -1;
        this.bK = -1;
        this.bL = null;
        this.bU = 0;
        this.bV = new j[0];
        this.bW = 0;
        this.bY = new float[0];
        this.cb = false;
        this.cc = null;
        this.H = h;
        this.y = y;
        this.bm = n;
        this.bn = n2;
        this.bk = n;
        this.bl = n2;
        this.bo = 0;
        this.bp = 0;
        this.u();
        this.r();
        if (y instanceof Shout3DViewer) {
            this.X = new Viewpoint((Shout3DViewer)y);
            this.Y = this.X;
        }
    }
    
    public void d(final boolean q) {
        this.q = q;
    }
    
    public int[] t() {
        return this.F;
    }
    
    protected void u() {
        int n = 0;
        do {
            this.I = this.y.createImage(this.bk, this.bl);
            if (this.I == null) {
                ++n;
                try {
                    Thread.sleep(100L);
                }
                catch (Exception ex) {}
            }
        } while (this.I == null);
        this.J = this.I.getGraphics();
        this.bq = this.bm * this.bn;
        this.br = (this.bm - 1.0f) / 2.0f;
        this.bs = (this.bn - 1.0f) / 2.0f;
        this.bt = (this.bk - 1.0f) / 2.0f;
        this.bu = (this.bl - 1.0f) / 2.0f;
        this.bv = this.br * 16.0f + 0.5f;
        this.bw = this.bs * 16.0f + 0.5f;
        this.bx = this.bn - 1;
        this.bD = this.bm - 1;
        this.O = new int[this.bq];
        this.F = new int[this.bq];
        this.Q = new float[this.bq];
        this.R = new float[this.bq];
        for (int i = 0; i < this.Q.length; ++i) {
            this.Q[i] = 0.0f;
        }
        System.arraycopy(this.Q, 0, this.R, 0, this.bq);
        if (this.C) {
            System.arraycopy(this.F, 0, this.O, 0, this.bq);
        }
        else {
            for (int j = 0; j < this.bq; ++j) {
                this.O[j] = this.G;
            }
            System.arraycopy(this.O, 0, this.F, 0, this.bq);
        }
        this.S = Integer.MAX_VALUE;
        this.T = -2147483647;
        this.U = Integer.MAX_VALUE;
        this.V = -2147483647;
        this.N = new MemoryImageSource(this.bm, this.bn, this.O, 0, this.bm);
        this.M = this.y.createImage(this.N);
        this.g = null;
        this.h = null;
    }
    
    public void e(final boolean o) {
        this.o = o;
    }
    
    private void v() {
        this.bB = Math.min(this.bk / 2.0f, this.bl / 2.0f) / (float)Math.tan(this.Y.fieldOfView.a / 2.0f);
        final float n = (float)Math.atan(this.bm / 2.0f / this.bB);
        this.bg = (float)Math.cos(n);
        this.bi = (float)Math.sin(n);
        final float n2 = (float)Math.atan(this.bn / 2.0f / this.bB);
        this.bh = (float)Math.cos(n2);
        this.bj = (float)Math.sin(n2);
        this.bB = -this.bB;
        this.bC = this.bB * 16.0f;
        this.l = this.Y.fieldOfView.getValue();
    }
    
    public int w() {
        return this.ca;
    }
    
    public void render(final Node node) {
        final boolean b = this.g == null;
        if (this.M != null) {
            if (b) {
                this.g = new w();
                this.g.q[0] = this.bm * this.bn;
                this.g.q[1] = this.bn;
                this.g.q[2] = this.bm;
                this.g.j = this.M;
                this.g.d = this.I;
                this.g.a = this.J;
                this.g.p = this.N;
                this.g.h = this.y;
                this.g.e = this.O;
            }
            if (this.h == null) {
                this.h = new x();
                this.h.e = this.g;
            }
            if (b) {
                final Component b2 = ((Shout3DViewer)this.y).b();
                if (b2 instanceof Applet) {
                    try {
                        this.g.t[0] = ((Applet)b2).getParameter("regcode");
                    }
                    catch (Exception ex) {}
                    try {
                        this.g.t[1] = ((Applet)b2).getParameter("regname");
                    }
                    catch (Exception ex2) {}
                    try {
                        this.g.t[2] = ((Applet)b2).getDocumentBase().getHost();
                    }
                    catch (Exception ex3) {}
                    try {
                        this.g.t[3] = ((Applet)b2).getDocumentBase().getProtocol();
                    }
                    catch (Exception ex4) {
                        this.g.t[3] = "";
                    }
                }
            }
        }
        this.cb = false;
        if (node == null) {
            return;
        }
        if (node instanceof Mmm) {
            this.h.e.q[3] = 1;
            this.h.e.z = ((Mmm)node).m.getValue();
            this.h.e.m = ((Mmm)node).f.getValue();
            this.h.k();
            this.h.e.q[3] = 0;
            return;
        }
        if (this.H == null) {
            if (this.y != null) {
                this.H = this.y.getGraphics();
            }
            if (this.H == null) {
                return;
            }
        }
        if (!(node instanceof Transform)) {
            return;
        }
        this.W = (Transform)node;
        for (int i = 0; i < this.m.size(); ++i) {
            ((RenderObserver)this.m.elementAt(i)).onPreRender(this, this.n.elementAt(i));
        }
        if (this.Y != null && this.Y.fieldOfView.getValue() != this.l) {
            this.v();
        }
        final float[] a = this.Y.a();
        if (this.Z != null && this.Z.length > 1) {
            float[] h = new float[12];
            MatUtil.b(h);
            for (int j = 0; j < this.Z.length - 1; ++j) {
                if (this.Z[j] instanceof Group) {
                    h = MatUtil.h(h, ((Group)this.Z[j]).c());
                }
            }
            this.k = MatUtil.h(h, a);
        }
        else {
            this.k = a;
        }
        this.ba = this.k;
        this.i = false;
        this.j = false;
        this.i();
        this.bf = true;
        this.ca = 0;
        this.W.d(this);
        this.ca = 0;
        System.arraycopy(this.R, 0, this.Q, 0, this.Q.length);
        System.arraycopy(this.F, 0, this.O, 0, this.O.length);
        this.bd.removeAllElements();
        this.be.removeAllElements();
        this.ca = 0;
        this.W.a(this);
        this.ca = 0;
        this.W.b(this);
        this.j();
        if (this.o) {
            this.m();
        }
        for (int k = 0; k < this.m.size(); ++k) {
            ((RenderObserver)this.m.elementAt(k)).onPostRender(this, this.n.elementAt(k));
        }
        if (this.M != null) {
            this.h.k();
        }
    }
    
    public int[] x() {
        return this.O;
    }
    
    public int y() {
        return this.bn;
    }
    
    public void a(final Viewpoint viewpoint) {
        this.a(viewpoint, null);
    }
    
    public void f(final boolean p) {
        this.p = p;
    }
    
    public void a(final Viewpoint y, final Node[] z) {
        if (y == null) {
            this.Y = this.X;
        }
        else {
            this.Y = y;
        }
        this.Z = z;
        this.v();
    }
    
    public Background z() {
        return this.B;
    }
    
    boolean A() {
        return this.j;
    }
    
    public Viewpoint B() {
        return this.X;
    }
    
    public NavigationInfo C() {
        return this.z;
    }
    
    protected void D() {
        if (this.bJ == this.bk && this.bK == this.bl && this.bL == this.y) {
            return;
        }
        this.bJ = this.bk;
        this.bK = this.bl;
        this.bL = this.y;
        this.K = this.y.createImage(this.bk, this.bl);
        if (this.K != null) {
            final MediaTracker mediaTracker = new MediaTracker(this.y);
            if (mediaTracker != null) {
                try {
                    mediaTracker.addImage(this.K, 0);
                    mediaTracker.waitForAll();
                }
                catch (InterruptedException ex) {}
            }
        }
        this.L = this.K.getGraphics();
    }
    
    void a(final IndexedFaceSet a, final int[] array, final float[] array2, final float[] array3, final int[] array4) {
        if (this.bW >= this.bV.length) {
            final j[] bv = new j[this.bW + 100];
            System.arraycopy(this.bV, 0, bv, 0, this.bV.length);
            this.bV = bv;
            final float[] by = new float[this.bW + 100];
            System.arraycopy(this.bY, 0, by, 0, this.bY.length);
            this.bY = by;
        }
        if (this.bV[this.bW] == null) {
            this.bV[this.bW] = new j();
        }
        this.bV[this.bW].a = a;
        System.arraycopy(array, 0, this.bV[this.bW].b, 0, 9);
        System.arraycopy(array2, 0, this.bV[this.bW].c, 0, 3);
        System.arraycopy(array3, 0, this.bV[this.bW].d, 0, 6);
        System.arraycopy(array4, 0, this.bV[this.bW].e, 0, 9);
        this.bY[this.bW] = ((array2[0] < array2[1]) ? array2[0] : array2[1]);
        this.bY[this.bW] = ((this.bY[this.bW] < array2[2]) ? this.bY[this.bW] : array2[2]);
        ++this.bW;
    }
}
