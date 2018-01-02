// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.a;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.io.EOFException;
import a.a.a.a.b.i;
import java.io.DataInputStream;
import java.util.Enumeration;
import java.net.URL;
import java.awt.Image;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import a.a.a.a.e.a;

public abstract class d extends b
{
    protected static final boolean a9 = true;
    public static final int a3 = 0;
    public static final int bp = 1;
    public static final int bi = 2;
    public static final float a7 = 5.0f;
    protected static final float bE = 1.5707964f;
    protected static final float be = 3.1415927f;
    protected static final float bC = 6.2831855f;
    private static final float bH = 1.0E-4f;
    private static final int bq = 0;
    private static final int bM = 1;
    private static final int bm = 2;
    private static final int bv = 3;
    private static final int bo = 4;
    protected volatile boolean bO;
    protected int bc;
    protected float bL;
    protected float a4;
    protected float bd;
    protected float br;
    protected float bf;
    protected float bh;
    protected float bI;
    protected float bn;
    private float bj;
    private float by;
    private float bg;
    protected float bG;
    public float a2;
    public float bA;
    protected float bt;
    protected float ba;
    protected float bJ;
    protected int bb;
    protected int bK;
    protected float a8;
    protected float a6;
    protected float bl;
    protected float bF;
    protected boolean bB;
    protected float bw;
    protected float[] a1;
    protected float[] bN;
    protected float[] bx;
    protected float[] bs;
    protected float[] a0;
    protected float[] a5;
    private float bP;
    private float bz;
    private long bD;
    private boolean bu;
    private float bk;
    
    public d(final a a, final String s) {
        super(a, s);
        this.bO = false;
        this.bb = 0;
        this.bK = 0;
        this.bB = true;
        this.bw = 20.0f;
        this.bD = 50L;
        this.bu = false;
    }
    
    private void if(final float n, final float n2, final float[] array) {
        if (this.bc == 0) {
            array[0] = n2;
            array[1] = n;
        }
        else if (this.bc == 1) {
            final float n3 = (float)Math.tan(this.a4);
            array[1] = n;
            if (n2 > n3) {
                array[0] = 1.5707964f - (float)Math.atan(-(n2 - n3 - 1.0f) / n3);
            }
            else if (n2 < -n3) {
                array[0] = (float)Math.atan((n2 + n3 + 1.0f) / n3) - 1.5707964f;
            }
            else {
                array[0] = (float)Math.atan(n2);
            }
        }
        else if (this.bc == 2) {
            final float n4 = 1.0f;
            final float n5 = 0.16666667f;
            float n7;
            float n8;
            float n9;
            if (n2 > 0.833f) {
                final float n6 = n2 - 0.833f;
                n7 = -1.0f;
                n8 = 2.0f * n / n4 - 1.0f;
                n9 = 2.0f * n6 / n5 - 1.0f;
            }
            else if (n2 > 0.667f) {
                final float n10 = n2 - 0.667f;
                n7 = 1.0f;
                n8 = 2.0f * n / n4 - 1.0f;
                n9 = 1.0f - 2.0f * n10 / n5;
            }
            else if (n2 > 0.5f) {
                final float n11 = n2 - 0.5f;
                n8 = -1.0f;
                n7 = 1.0f - 2.0f * n11 / n5;
                n9 = 1.0f - 2.0f * n / n4;
            }
            else if (n2 > 0.333f) {
                final float n12 = n2 - 0.333f;
                n9 = 1.0f;
                n8 = 1.0f - 2.0f * n / n4;
                n7 = 1.0f - 2.0f * n12 / n5;
            }
            else if (n2 > 0.167f) {
                final float n13 = n2 - 0.167f;
                n8 = 1.0f;
                n7 = 1.0f - 2.0f * n13 / n5;
                n9 = 2.0f * n / n4 - 1.0f;
            }
            else {
                n9 = -1.0f;
                n8 = 2.0f * n / n4 - 1.0f;
                n7 = 1.0f - 2.0f * n2 / n5;
            }
            array[0] = (float)Math.atan2(n7, (float)Math.sqrt(n8 * n8 + n9 * n9));
            array[1] = (float)Math.atan2(-n8, n9);
        }
    }
    
    protected float a(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    protected int a(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    public void w() {
        final Graphics graphics = this.getGraphics();
        final Graphics graphics2 = super.ae.getGraphics();
        graphics2.drawImage(super.M.if, super.ar, super.aD, super.aF, super.G, null);
        graphics2.drawImage(super.aU, 0, 0, this.size().width, this.size().height, this);
        graphics2.dispose();
        synchronized (super.ae) {
            graphics.drawImage(super.ae, 0, 0, null);
        }
        // monitorexit(super.ae)
        graphics.dispose();
    }
    
    public void if(final Graphics graphics) {
        this.do(graphics);
        graphics.drawImage(super.M.if, super.aP.x, super.aP.y, super.aP.width, super.aP.height, null);
    }
    
    public void case(final String s) {
        final a.a.a.a.c.b.a a = a.a.a.a.c.b.a.a(s);
        if (a == null) {
            return;
        }
        if (super.aW == null) {
            super.aW = new Vector();
        }
        super.aW.addElement(a);
        super.aW.addElement(a);
        super.case(s);
    }
    
    public void a(float n, final float n2, final int[] array) {
        final float n3 = this.bG * 0.5f;
        final float n4 = n3 * this.a8 / this.a6;
        float n5;
        if (this.getSize().width / (this.getSize().height + 25) <= 2.0) {
            n5 = (n3 + n4) / this.bG;
        }
        else {
            n5 = n4 / n3 - n3 / n4;
        }
        n -= this.bA;
        final float n6 = (float)Math.tan(n3);
        final float n7 = (float)Math.tan(n2);
        final float n8 = (float)Math.cos(n);
        final float n9 = (n7 * this.ba - this.bJ * n8) / ((n8 * this.ba + n7 * this.bJ) * n6);
        array[0] = (int)(this.bl * (n5 * ((float)Math.tan(n) * (this.ba - n6 * n9 * this.bJ) / (float)Math.tan(n4)) / 2.0f + 0.5f));
        array[1] = (int)(this.bF * (0.5f - n9 / 2.0f));
    }
    
    private void do(final float n, final float n2, final float[] array) {
        if (this.bc == 0) {
            array[0] = n;
            array[1] = n2;
        }
        else if (this.bc == 1) {
            array[0] = n;
            final float n3 = (float)Math.tan(this.a4);
            if (n2 > this.a4) {
                array[1] = n3 + 1.0f - n3 * (float)Math.tan(1.5707964f - n2);
            }
            else if (n2 < -this.a4) {
                array[1] = -n3 - (1.0f - n3 * (float)Math.tan(1.5707964f + n2));
            }
            else {
                array[1] = (float)Math.tan(n2);
            }
        }
        else if (this.bc == 2) {
            final float n4 = (float)Math.sin(n2);
            final float n5 = (float)Math.cos(n2);
            final float n6 = -n5 * (float)Math.sin(n);
            final float n7 = n5 * (float)Math.cos(n);
            float n8 = n6;
            int n9;
            if (n8 < 0.0f) {
                n9 = 3;
            }
            else {
                n9 = 1;
            }
            if (Math.abs(n4) > Math.abs(n8)) {
                n8 = n4;
                if (n8 < 0.0f) {
                    n9 = 5;
                }
                else {
                    n9 = 4;
                }
            }
            if (Math.abs(n7) > Math.abs(n8)) {
                n8 = n7;
                if (n8 < 0.0f) {
                    n9 = 0;
                }
                else {
                    n9 = 2;
                }
            }
            final float n10 = 1.0f / Math.abs(n8);
            final float n11 = n6 * n10;
            final float n12 = n4 * n10;
            final float n13 = n7 * n10;
            switch (n9) {
                case 0: {
                    array[0] = (n11 + 1.0f) * 0.5f;
                    array[1] = (-n12 + 1.0f) * 0.5f * 0.167f;
                    break;
                }
                case 1: {
                    array[0] = (n13 + 1.0f) * 0.5f;
                    array[1] = (-n12 + 1.0f) * 0.5f * 0.167f + 0.167f;
                    break;
                }
                case 2: {
                    array[0] = (-n11 + 1.0f) * 0.5f;
                    array[1] = (-n12 + 1.0f) * 0.5f * 0.167f + 0.333f;
                    break;
                }
                case 3: {
                    array[0] = (-n13 + 1.0f) * 0.5f;
                    array[1] = (-n12 + 1.0f) * 0.5f * 0.167f + 0.5f;
                    break;
                }
                case 4: {
                    array[0] = (n11 + 1.0f) * 0.5f;
                    array[1] = (-n13 + 1.0f) * 0.5f * 0.167f + 0.667f;
                    break;
                }
                case 5: {
                    array[0] = (n11 + 1.0f) * 0.5f;
                    array[1] = (n13 + 1.0f) * 0.5f * 0.167f + 0.833f;
                    break;
                }
            }
        }
    }
    
    protected static int if(final int n, final int n2, final int n3) {
        int n4 = 0;
        switch (n3 >> 13) {
            case 0: {
                return n;
            }
            case 1:
            case 2: {
                n4 = (3 * (n >> 2 & 0x3F3F3F3F) + (n2 >> 2 & 0x3F3F3F3F) | 0xFF000000);
                break;
            }
            case 3:
            case 4: {
                n4 = ((n >> 1 & 0x7F7F7F7F) + (n2 >> 1 & 0x7F7F7F7F) | 0xFF000000);
                break;
            }
            case 5:
            case 6: {
                n4 = ((n >> 2 & 0x3F3F3F3F) + 3 * (n2 >> 2 & 0x3F3F3F3F) | 0xFF000000);
                break;
            }
            default: {
                return n2;
            }
        }
        return n4;
    }
    
    protected abstract Image I();
    
    public void k() {
        super.k();
        this.bw = 20.0f;
        this.bP = 0.08726647f;
        this.bu = false;
    }
    
    protected void a(final a.a.a.a.c.b.a a, final URL url) {
        try {
            super.appContext.showDocument(new URL(url, a.for), a.int);
        }
        catch (Exception ex) {}
    }
    
    protected boolean if(final boolean b) {
        if (super.aW == null) {
            return false;
        }
        final float[] array = new float[2];
        this.a(super.aK, super.aI, array);
        final URL url = (URL)super.ad.case("param/documentbase");
        final int[] array2 = new int[2];
        final float[] array3 = new float[3];
        final Enumeration<a.a.a.a.c.b.a> elements = (Enumeration<a.a.a.a.c.b.a>)super.aW.elements();
        while (elements.hasMoreElements()) {
            final a.a.a.a.c.b.a a = elements.nextElement();
            if (a.byte) {
                this.a((a.if + a.try) * 0.5f, (a.a + a.new) * 0.5f, array3);
                this.a(array3[1], array3[0], array2);
                if (this.a(array[0], array[1], super.aK, super.aI, array2[0], array2[1], a)) {
                    if (b) {
                        this.a(a, url);
                    }
                    else {
                        this.a(a);
                    }
                    return true;
                }
                continue;
            }
        }
        this.n();
        return false;
    }
    
    public void G() {
        b.af = null;
        this.bc = 0;
        this.bL = -1.5707964f;
        this.a4 = 1.5707964f;
        this.bd = 0.0f;
        this.br = 0.0f;
        this.bf = -100.0f;
        this.bh = -100.0f;
        this.bI = -100.0f;
        this.bn = -100.0f;
    }
    
    protected void a(final int n, final int n2, final float[] array) {
        final float n3 = n / this.bl;
        final float n4 = n2 / this.bF;
        final float n5 = this.bG * 0.5f;
        final float n6 = n5 * this.a8 / this.a6;
        float n7 = (n3 - 0.5f) * 2.0f;
        if (n7 < 0.005 && n7 > -0.005) {
            n7 = 0.0f;
        }
        float n8 = (0.5f - n4) * 2.0f;
        if (n8 < 0.005 && n8 > -0.005) {
            n8 = 0.0f;
        }
        final float ba = this.ba;
        final float bj = this.bJ;
        final float n9 = (float)Math.tan(n5);
        final float n10 = ba - n9 * n8 * bj;
        final float n11 = bj + n9 * n8 * ba;
        final float n12 = (float)Math.tan(n6) * n7;
        float n13;
        if (n10 < 1.0E-4f && n10 > -1.0E-4f) {
            n13 = 0.0f;
        }
        else if (n10 < 0.0f) {
            n13 = (float)Math.atan(n12 / n10) + 3.1415927f;
        }
        else {
            n13 = (float)Math.atan(n12 / n10);
        }
        final float n14 = n10 / (float)Math.cos(n13);
        float n15;
        if (n14 < 1.0E-4f && n14 > -1.0E-4f) {
            if (n11 > 0.0f) {
                n15 = 1.5707964f;
            }
            else {
                n15 = -1.5707964f;
            }
        }
        else {
            n15 = (float)Math.atan(n11 / n14);
        }
        float n16 = n13 + this.bA;
        if (n15 <= this.bL) {
            n15 = -n15 - 3.1415927f;
        }
        else if (n15 > this.a4) {
            n15 = 3.1415927f - n15;
        }
        if (this.br - this.bd < 0.001f) {
            if (n16 < 0.0f) {
                n16 += 6.2831855f;
            }
            else if (n16 > 6.2831855f) {
                n16 -= 6.2831855f;
            }
        }
        final float[] array2 = new float[2];
        this.do(n16, n15, array2);
        final float[] array3 = new float[2];
        final float[] array4 = new float[2];
        if (this.br - this.bd < 0.001f) {
            this.do(0.0f, this.bL, array3);
            this.do(6.2831855f, this.a4, array4);
        }
        else {
            this.do(this.bd, this.bL, array3);
            this.do(this.br, this.a4, array4);
        }
        if (Math.abs(array4[0] - array3[0]) > 1.0E-6f) {
            array[0] = (array2[0] - array3[0]) / (array4[0] - array3[0]);
            array[1] = (array4[1] - array2[1]) / (array4[1] - array3[1]);
        }
        else {
            array[0] = array2[0];
            array[1] = array2[1];
        }
    }
    
    public void if(final a.a.a.a.c.b.a a) {
        final int[] array = new int[2];
        final float[] array2 = new float[3];
        if (Math.abs(a.if - a.try) > 0.95f && Math.abs(a.a - a.new) > 0.95f) {
            return;
        }
        this.a((a.if + a.try) * 0.5f, (a.a + a.new) * 0.5f, array2);
        if (Math.abs(array2[0] - this.a2) > 1.22f) {
            return;
        }
        if (Math.abs(array2[1] - this.bA) > 1.22f && Math.abs(array2[1] - this.bA) < 5.0631857f) {
            return;
        }
        this.a(array2[1], array2[0], array);
        final int n = array[0];
        final int n2 = array[1];
        int n3 = n - 12;
        int n4 = n2 - 12;
        int n5 = n + 12;
        int n6 = n2 + 12;
        if (n3 < 0) {
            n3 = 0;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n5 >= super.M.new) {
            n5 = super.M.new - 1;
        }
        if (n6 >= super.M.do) {
            n6 = super.M.do - 1;
        }
        final int n7 = n3 - n;
        final int n8 = n4 - n2;
        final int n9 = n5 - n;
        for (int n10 = n6 - n2, i = n8; i <= n10; ++i) {
            for (int j = n7; j <= n9; ++j) {
                final int n11 = j * j + i * i;
                if ((n11 <= 144 && n11 >= 70) || (n11 <= 36 && n11 >= 6)) {
                    super.M.a[(n2 + i) * super.M.new + (n + j)] = super.aA.getRGB();
                }
            }
        }
    }
    
    protected void C() {
        final Enumeration<a.a.a.a.c.b.a> elements = super.aW.elements();
        while (elements.hasMoreElements()) {
            final a.a.a.a.c.b.a a = elements.nextElement();
            if (a.byte) {
                this.if(a);
            }
        }
    }
    
    public boolean H() {
        return this.bB;
    }
    
    public float E() {
        return this.bP;
    }
    
    public void if(final float[] array) {
        array[0] = this.a2;
        array[1] = this.bA;
        array[2] = this.bt;
    }
    
    public float D() {
        return this.bG;
    }
    
    public void v() {
        this.bw = (float)super.ad.case("param/pano.autospin");
        this.bB = (this.bw == 0.0f);
        float[] array = (float[])super.ad.case("param/pano.initialView");
        if (array == null) {
            array = new float[] { 0.0f, (this.bd == this.br) ? 3.1415927f : ((this.br - this.bd) * 0.5f), 1.2217306f };
        }
        this.a(array[0], array[1], 0.0f, array[2]);
        this.a((float)super.ad.case("param/minZoomAngle"), false);
        this.a(array[2]);
        this.a(new float[] { array[0], array[1], 0.0f });
        this.a(array[2]);
        this.bu = (this.bw < 0.0f);
        this.bD = 20L;
        this.bk = 0.0f;
        if (!this.bB) {
            if (this.br == 0.0f && this.bd == 0.0f) {
                this.bk = 6.2831855f / (Math.abs(this.bw) * 15.0f);
            }
            else {
                this.bk = (this.br - this.bd) / (Math.abs(this.bw) * 15.0f);
            }
        }
        if (b.af == null) {
            System.err.println("fImageName null!");
            return;
        }
        super.v();
        super.aY = false;
    }
    
    private boolean a(final float n, final float n2, final int n3, final int n4, final int n5, final int n6, final a.a.a.a.c.b.a a) {
        final int n7 = (n3 - n5) * (n3 - n5) + (n4 - n6) * (n4 - n6);
        if (a.if > a.try) {
            if ((n > a.try && n < a.if && n2 > a.new && n2 < a.a) || n7 <= 144) {
                return true;
            }
        }
        else if (((n < a.if || n > a.try) && n2 > a.new && n2 < a.a) || n7 <= 144) {
            return true;
        }
        return false;
    }
    
    public void a(final URL url) {
        final byte[] array = new byte[256];
        boolean b = false;
        this.G();
        try {
            final DataInputStream dataInputStream = new DataInputStream(url.openStream());
            while (true) {
                if (array[0] == 117) {
                    if (array[1] == 114 && array[2] == 108 && array[3] == 32 && array[4] == 34) {
                        break;
                    }
                    if (array[1] == 114 && array[2] == 108 && array[3] == 32 && array[4] == 91) {
                        b = true;
                        break;
                    }
                }
                array[0] = array[1];
                array[1] = array[2];
                array[2] = array[3];
                array[3] = array[4];
                array[4] = dataInputStream.readByte();
            }
            a.a.a.a.a.b.af = i.a(dataInputStream, true);
            Label_0162: {
                if (!b) {
                    break Label_0162;
                }
                a.a.a.a.a.b.af = i.a(dataInputStream, true);
                try {
                    while (true) {
                        final String a = i.a(dataInputStream, false);
                        if (a.equalsIgnoreCase("type")) {
                            final String a2 = i.a(dataInputStream, false);
                            if (a2.equalsIgnoreCase("SPHERE")) {
                                this.bc = 0;
                            }
                            else if (a2.equalsIgnoreCase("CYLINDER")) {
                                this.bc = 1;
                            }
                            else if (a2.equalsIgnoreCase("CUBE")) {
                                this.bc = 2;
                            }
                            else {
                                this.bc = 0;
                            }
                        }
                        else if (a.equalsIgnoreCase("vFov")) {
                            this.bL = Float.valueOf(i.a(dataInputStream, false));
                            this.a4 = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("hFov")) {
                            this.bd = Float.valueOf(i.a(dataInputStream, false));
                            this.br = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("pitchRange")) {
                            this.bf = Float.valueOf(i.a(dataInputStream, false));
                            this.bh = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else {
                            if (!a.equalsIgnoreCase("yawRange")) {
                                continue;
                            }
                            this.bI = Float.valueOf(i.a(dataInputStream, false));
                            this.bn = Float.valueOf(i.a(dataInputStream, false));
                        }
                    }
                }
                catch (EOFException ex2) {}
            }
        }
        catch (Exception ex) {
            this.G();
            ex.printStackTrace();
        }
        if (this.bf == -100.0f) {
            this.bf = this.bL;
        }
        if (this.bh == -100.0f) {
            this.bh = this.a4;
        }
        if (this.bI == -100.0f) {
            this.bI = this.bd;
        }
        if (this.bn == -100.0f) {
            this.bn = this.br;
        }
    }
    
    public void i() {
    }
    
    private void if(final int n) {
        this.bB = true;
        final float[] array = new float[3];
        final float n2 = 0.3f;
        final float n3 = 0.3f;
        float n5;
        float n4 = n5 = 0.0f;
        float n6 = 0.1f;
        if (super.H) {
            n6 = 0.3f;
        }
        final float d = this.D();
        this.if(array);
        if (super.X) {
            final float n7 = super.aK - super.an;
            final float n8 = super.ak - super.aI;
            final float n9 = n7 / this.a8;
            final float n10 = n8 / this.a6;
            float n11;
            if (n9 < 0.0f) {
                n11 = n9 * -n9;
            }
            else {
                n11 = n9 * n9;
            }
            float n12;
            if (n10 < 0.0f) {
                n12 = n10 * -n10;
            }
            else {
                n12 = n10 * n10;
            }
            final float[] array2 = array;
            final int n13 = 0;
            array2[n13] += n12 * n3 * d;
            final float[] array3 = array;
            final int n14 = 1;
            array3[n14] += n11 * n2 * d;
        }
        else {
            switch (n) {
                case 0: {
                    n5 -= n6;
                    break;
                }
                case 1: {
                    n5 += n6;
                    break;
                }
                case 2: {
                    n4 += n6;
                    break;
                }
                case 3: {
                    n4 -= n6;
                    break;
                }
            }
            final float[] array4 = array;
            final int n15 = 0;
            array4[n15] += n4 * d;
            final float[] array5 = array;
            final int n16 = 1;
            array5[n16] += n5 * d;
        }
        array[2] = 0.0f;
        this.a(array);
        this.h();
    }
    
    protected void a(final a.a.a.a.c.b.a a) {
        super.appContext.showStatus(a.do);
    }
    
    public void u() {
        super.u();
        this.a1 = new float[3];
        this.bN = new float[3];
        this.bx = new float[3];
        this.bs = new float[3];
        this.a0 = new float[3];
        this.a5 = new float[3];
        this.a2 = 0.0f;
        this.bA = 3.1415927f;
        this.bt = 0.0f;
        this.bG = 1.2217306f;
        this.bL = -1.5707964f;
        this.a4 = 1.5707964f;
        this.bd = 0.0f;
        this.br = 0.0f;
        this.bP = 0.08726647f;
        try {
            this.a(new URL((URL)super.ad.case("param/documentbase"), (String)super.ad.case("param/file")));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.G();
        }
        if (b.af == null) {
            this.a(this.byte("iip01"), this.byte("iip02"));
        }
    }
    
    protected void a(final float[] array, final float[] array2, final float[] array3, final float[] array4, final int n) {
        float n2 = array2[1] - array[1];
        if (n2 == 0.0f) {
            n2 = 1.0f;
        }
        final float n3 = n + 0.5f - array[1];
        final float n4 = 1.0f / n2;
        array4[0] = (array2[0] - array[0]) * n4;
        array3[0] = array[0] + array4[0] * n3;
        array4[1] = (array2[2] - array[2]) * n4;
        array3[1] = array[2] + array4[1] * n3;
        array4[2] = (array2[3] - array[3]) * n4;
        array3[2] = array[3] + array4[2] * n3;
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        Label_0292: {
            switch (keyEvent.getID()) {
                case 401: {
                    switch (keyEvent.getKeyCode()) {
                        case 10: {
                            if (super.I.byte() == 1) {
                                this.if(true);
                                break Label_0292;
                            }
                            break Label_0292;
                        }
                        case 16: {
                            super.H = true;
                            break Label_0292;
                        }
                        case 33:
                        case 34:
                        case 65:
                        case 90: {
                            this.bB = true;
                            break Label_0292;
                        }
                        case 38: {
                            this.bB = true;
                            this.if(2);
                            break Label_0292;
                        }
                        case 40: {
                            this.bB = true;
                            this.if(3);
                            break Label_0292;
                        }
                        case 37: {
                            this.bB = true;
                            this.if(0);
                            break Label_0292;
                        }
                        case 39: {
                            this.bB = true;
                            this.if(1);
                            break Label_0292;
                        }
                        case 82: {
                            this.d();
                            break Label_0292;
                        }
                        case 76: {
                            this.B();
                            break Label_0292;
                        }
                        case 80: {
                            this.A();
                            break Label_0292;
                        }
                    }
                    break;
                }
                case 402: {
                    switch (keyEvent.getKeyCode()) {
                        case 16: {
                            super.H = false;
                            break Label_0292;
                        }
                    }
                    break;
                }
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        Label_0266: {
            if (mouseEvent.isPopupTrigger()) {
                this.bO = true;
            }
            else if (this.bO) {
                this.bO = false;
            }
            else {
                switch (mouseEvent.getID()) {
                    case 501: {
                        this.bB = true;
                        switch (super.I.if()) {
                            case 2: {
                                this.setCursor((Cursor)super.ad.case("cursor/zoomin"));
                                break Label_0266;
                            }
                            case 3: {
                                this.setCursor((Cursor)super.ad.case("cursor/zoomout"));
                                break Label_0266;
                            }
                            case 5: {
                                this.setCursor((Cursor)super.ad.case("cursor/rotate"));
                                break Label_0266;
                            }
                        }
                        break;
                    }
                    case 502: {
                        if (Math.abs(super.aK - super.aG) >= 3 || Math.abs(super.aI - super.aE) >= 3) {
                            break;
                        }
                        if (super.I.byte() == 1 && this.if(true)) {
                            break;
                        }
                        switch (super.I.if()) {
                            case 2: {
                                this.t();
                                break Label_0266;
                            }
                            case 3: {
                                this.r();
                                break Label_0266;
                            }
                        }
                        break;
                    }
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503: {
                super.aK = mouseEvent.getX();
                super.aI = mouseEvent.getY();
                if (super.I.byte() == 1) {
                    this.if(false);
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    protected synchronized void e() {
        final Image i = this.I();
        if (i == null) {
            this.h();
            return;
        }
        if (super.ae == null) {
            super.ae = this.createImage(super.aP.width, super.aP.height);
        }
        if (super.ah != null && super.aq) {
            this.w();
        }
        else {
            synchronized (super.ae) {
                final Graphics graphics = super.ae.getGraphics();
                graphics.drawImage(i, 0, 0, super.aP.width, super.aP.height, null);
                graphics.dispose();
            }
            // monitorexit(super.ae)
            this.repaint();
        }
    }
    
    public void s() {
        final float[] array = { this.bj, this.by, 0.0f };
        this.a(this.bz);
        this.a(array);
        this.a(this.bz);
        this.bu = (this.bw < 0.0f);
        this.bB = (this.bw == 0.0f);
        super.s();
    }
    
    public void B() {
        this.bu = false;
        this.F();
    }
    
    public void A() {
        this.bB = true;
        this.h();
    }
    
    public void d() {
        this.bu = true;
        this.F();
    }
    
    private void F() {
        this.bB = (this.bw == 0.0f);
        this.h();
    }
    
    public boolean j() {
        if (!this.bB) {
            final float[] array = new float[3];
            this.if(array);
            final float n = array[1];
            if (this.bu) {
                final float[] array2 = array;
                final int n2 = 1;
                array2[n2] += this.bk;
            }
            else {
                final float[] array3 = array;
                final int n3 = 1;
                array3[n3] -= this.bk;
            }
            array[2] = this.bg;
            try {
                this.e();
            }
            catch (Exception ex) {}
            this.a(array);
            this.if(array);
            if (Math.abs(array[1] - n) < 1.0E-4f) {
                this.bu ^= true;
            }
            try {
                Thread.sleep(this.bD);
            }
            catch (InterruptedException ex2) {
                this.A();
            }
            return true;
        }
        if (super.X && !this.bO) {
            switch (super.I.if()) {
                case 2: {
                    this.t();
                    break;
                }
                case 3: {
                    this.r();
                    break;
                }
            }
            this.if(4);
        }
        if (super.aT) {
            super.aT = false;
            this.e();
            return true;
        }
        return false;
    }
    
    public void a(final float bj, final float by, final float bg, final float bz) {
        this.bj = bj;
        this.by = by;
        this.bg = bg;
        this.bz = bz;
        this.bB = (this.bw == 0.0f);
        this.h();
    }
    
    public void a(final float bp, final boolean b) {
        this.bP = bp;
        if (b) {
            this.a(this.D());
        }
        this.h();
    }
    
    public void a(final float[] array) {
        this.a2 = array[0];
        this.bA = array[1];
        this.bt = array[2];
        final float n = this.bG * 0.5f;
        if (Math.abs(this.bn - this.bI) > 0.001f) {
            this.bA = this.a(this.bA, this.bI, this.bn);
        }
        if (this.a2 > 1.5707964f) {
            this.a2 = 1.5707964f;
        }
        if (this.a2 < -1.5707964f) {
            this.a2 = -1.5707964f;
        }
        if (this.br - this.bd > 0.001f) {
            final float n2 = n * (this.a8 / this.a6);
            if (this.bA + n2 > this.br - 0.1f) {
                this.bA = this.br - n2 - 0.1f;
            }
            if (this.bA - n2 < this.bd + 0.1f) {
                this.bA = this.bd + n2 + 0.1f;
            }
            if (this.bA > this.br || this.bA < this.bd) {
                this.bA = (this.br - this.bd) / 2.0f;
            }
        }
        while (this.bA < 0.0f) {
            this.bA += 6.2831855f;
        }
        while (this.bA > 6.2831855f) {
            this.bA -= 6.2831855f;
        }
        if (this.bh < 1.5707964f && this.a2 + n > this.bh - 1.0E-4f) {
            this.a2 = this.bh - n - 1.0E-4f;
        }
        if (this.bf > -1.5707964f && this.a2 - n < this.bf + 1.0E-4f) {
            this.a2 = this.bf + n + 1.0E-4f;
        }
        this.ba = (float)Math.cos(this.a2);
        this.bJ = (float)Math.sin(this.a2);
    }
    
    public void a(final float bg) {
        this.bG = bg;
        if (this.bG < this.bP) {
            this.bG = this.bP;
        }
        if (this.bG > 2.094f) {
            this.bG = 2.094f;
        }
        if (this.bG > this.a4 - this.bL - 1.0E-4f) {
            this.bG = this.a4 - this.bL - 1.0E-4f;
        }
        if (this.bG > this.bh - this.bf - 1.0E-4f) {
            this.bG = this.bh - this.bf - 1.0E-4f;
        }
        float n = this.bG * this.a8 / this.a6;
        if (n > 2.094f) {
            n = 2.094f;
            this.bG = n * this.a6 / this.a8;
        }
        if (this.br - this.bd > 0.05f && n > this.br - this.bd - 0.05f) {
            n = this.br - this.bd - 0.05f;
            this.bG = n * this.a6 / this.a8;
        }
        if (this.bn - this.bI > 0.05f && n > this.bn - this.bI - 0.05f) {
            this.bG = (this.bn - this.bI - 0.05f) * this.a6 / this.a8;
        }
    }
    
    public void e(final String s) {
        float n;
        try {
            n = Float.valueOf(s) * 3.1415927f / 180.0f;
        }
        catch (Exception ex) {
            n = 0.08726647f;
        }
        this.a(n, false);
    }
    
    public void d(final String s) {
        try {
            this.bw = Float.valueOf(s);
        }
        catch (Exception ex) {
            this.bw = 20.0f;
        }
        this.bB = (this.bw == 0.0f);
        float[] array = (float[])super.ad.case("param/pano.initialView");
        if (array == null) {
            array = new float[] { 0.0f, (this.bd == this.br) ? 3.1415927f : ((this.br - this.bd) * 0.5f), 1.2217306f };
        }
        this.a(array[0], array[1], 0.0f, array[2]);
        this.a((float)super.ad.case("param/minZoomAngle"), false);
        this.a(array[2]);
        this.a(new float[] { array[0], array[1], 0.0f });
        this.a(array[2]);
        this.bu = (this.bw < 0.0f);
        this.bD = 20L;
        this.bk = 0.0f;
        if (!this.bB) {
            if (this.br == 0.0f && this.bd == 0.0f) {
                this.bk = 6.2831855f / (Math.abs(this.bw) * 15.0f);
            }
            else {
                this.bk = (this.br - this.bd) / (Math.abs(this.bw) * 15.0f);
            }
        }
        if (b.af == null) {
            System.err.println("fImageName null!");
            return;
        }
        super.v();
    }
    
    public void a(final float n, final float n2, final float[] array) {
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        this.do(this.bd, this.bL, array2);
        if (this.br - this.bd < 0.001f) {
            this.do(6.2831855f, this.a4, array3);
        }
        else {
            this.do(this.br, this.a4, array3);
        }
        float n3;
        float n4;
        if (Math.abs(array3[0] - array2[0]) > 1.0E-6f) {
            n3 = n * (array3[0] - array2[0]) + array2[0];
            n4 = array3[1] - n2 * (array3[1] - array2[1]);
        }
        else {
            n3 = n;
            n4 = n2;
        }
        this.if(n3, n4, array);
    }
    
    public void t() {
        final float n = -1.0f;
        final float n2 = 0.03f;
        this.bB = true;
        final float d = this.D();
        this.a(d + n * (n2 * (d * 1.5f * 1.5f)));
        this.h();
    }
    
    public void r() {
        final float n = 1.0f;
        final float n2 = 0.03f;
        this.bB = true;
        final float d = this.D();
        this.a(d + n * (n2 * (d * 1.5f * 1.5f)));
        this.h();
    }
}
