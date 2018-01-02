// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a.a;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
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
import a.a.a.a.e.a;

public abstract class d extends b
{
    protected static final boolean aO = true;
    public static final int aI = 0;
    public static final int a4 = 1;
    public static final int aX = 2;
    public static final float aM = 5.0f;
    protected static final float bk = 1.5707964f;
    protected static final float aT = 3.1415927f;
    protected static final float bi = 6.2831855f;
    private static final float bn = 1.0E-4f;
    private static final int a5 = 0;
    private static final int bs = 1;
    private static final int a1 = 2;
    private static final int bb = 3;
    private static final int a3 = 4;
    protected volatile boolean bu;
    protected int aR;
    protected float br;
    protected float aJ;
    protected float aS;
    protected float a6;
    protected float aU;
    protected float aW;
    protected float bo;
    protected float a2;
    private float aY;
    private float be;
    private float aV;
    protected float bm;
    public float aH;
    public float bg;
    protected float a8;
    protected float aP;
    protected float bp;
    protected int aQ;
    protected int bq;
    protected float aN;
    protected float aL;
    protected float a0;
    protected float bl;
    public a.a.b.b ba;
    protected boolean bh;
    protected float bc;
    protected float[] aG;
    protected float[] bt;
    protected float[] bd;
    protected float[] a7;
    protected float[] aF;
    protected float[] aK;
    private float bv;
    private float bf;
    private long bj;
    private boolean a9;
    private float aZ;
    
    public d(final a a, final String s) {
        super(a, s);
        this.bu = false;
        this.aQ = 0;
        this.bq = 0;
        this.ba = null;
        this.bh = true;
        this.bc = 20.0f;
        this.bj = 50L;
        this.a9 = false;
    }
    
    private void if(final float n, final float n2, final float[] array) {
        if (this.aR == 0) {
            array[0] = n2;
            array[1] = n;
        }
        else if (this.aR == 1) {
            final float n3 = (float)Math.tan(this.aJ);
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
        else if (this.aR == 2) {
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
    
    public void new(final String s) {
        final a.a.a.a.c.b.a a = a.a.a.a.c.b.a.a(s);
        if (a == null) {
            return;
        }
        if (super.ax == null) {
            super.ax = new Vector();
        }
        super.ax.addElement(a);
        super.ax.addElement(a);
        super.new(s);
    }
    
    public void a(float n, final float n2, final int[] array) {
        final float n3 = this.bm * 0.5f;
        final float n4 = n3 * this.aN / this.aL;
        n -= this.bg;
        final float n5 = (float)Math.tan(n3);
        final float n6 = (float)Math.tan(n2);
        final float n7 = (float)Math.cos(n);
        final float n8 = (n6 * this.aP - this.bp * n7) / ((n7 * this.aP + n6 * this.bp) * n5);
        array[0] = (int)(this.a0 * ((float)Math.tan(n) * (this.aP - n5 * n8 * this.bp) / (float)Math.tan(n4) / 2.0f + 0.5f));
        array[1] = (int)(this.bl * (0.5f - n8 / 2.0f));
    }
    
    private void do(final float n, final float n2, final float[] array) {
        if (this.aR == 0) {
            array[0] = n;
            array[1] = n2;
        }
        else if (this.aR == 1) {
            array[0] = n;
            final float n3 = (float)Math.tan(this.aJ);
            if (n2 > this.aJ) {
                array[1] = n3 + 1.0f - n3 * (float)Math.tan(1.5707964f - n2);
            }
            else if (n2 < -this.aJ) {
                array[1] = -n3 - (1.0f - n3 * (float)Math.tan(1.5707964f + n2));
            }
            else {
                array[1] = (float)Math.tan(n2);
            }
        }
        else if (this.aR == 2) {
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
    
    protected abstract Image D();
    
    protected void a(final a.a.a.a.c.b.a a, final URL url) {
        try {
            super.appContext.showDocument(new URL(url, a.for), a.int);
        }
        catch (Exception ex) {}
    }
    
    protected boolean if(final boolean b) {
        if (super.ax == null) {
            return false;
        }
        final float[] array = new float[2];
        this.a(super.aC, super.aB, array);
        final URL url = (URL)super.W.try("param/documentbase");
        final int[] array2 = new int[2];
        final float[] array3 = new float[3];
        final Enumeration<a.a.a.a.c.b.a> elements = (Enumeration<a.a.a.a.c.b.a>)super.ax.elements();
        while (elements.hasMoreElements()) {
            final a.a.a.a.c.b.a a = elements.nextElement();
            if (a.byte) {
                this.a((a.if + a.try) * 0.5f, (a.a + a.new) * 0.5f, array3);
                this.a(array3[1], array3[0], array2);
                if (this.a(array[0], array[1], super.aC, super.aB, array2[0], array2[1], a)) {
                    if (b) {
                        this.a(a, url);
                    }
                    else {
                        this.if(a);
                    }
                    return true;
                }
                continue;
            }
        }
        this.j();
        return false;
    }
    
    public void A() {
        super.aq = null;
        this.aR = 0;
        this.br = -1.5707964f;
        this.aJ = 1.5707964f;
        this.aS = 0.0f;
        this.a6 = 0.0f;
        this.aU = -100.0f;
        this.aW = -100.0f;
        this.bo = -100.0f;
        this.a2 = -100.0f;
    }
    
    protected void a(final int n, final int n2, final float[] array) {
        final float n3 = n / this.a0;
        final float n4 = n2 / this.bl;
        final float n5 = this.bm * 0.5f;
        final float n6 = n5 * this.aN / this.aL;
        float n7 = (n3 - 0.5f) * 2.0f;
        if (n7 < 0.005 && n7 > -0.005) {
            n7 = 0.0f;
        }
        float n8 = (0.5f - n4) * 2.0f;
        if (n8 < 0.005 && n8 > -0.005) {
            n8 = 0.0f;
        }
        final float ap = this.aP;
        final float bp = this.bp;
        final float n9 = (float)Math.tan(n5);
        final float n10 = ap - n9 * n8 * bp;
        final float n11 = bp + n9 * n8 * ap;
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
        float n16 = n13 + this.bg;
        if (n15 <= this.br) {
            n15 = -n15 - 3.1415927f;
        }
        else if (n15 > this.aJ) {
            n15 = 3.1415927f - n15;
        }
        if (this.a6 - this.aS < 0.001f) {
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
        if (this.a6 - this.aS < 0.001f) {
            this.do(0.0f, this.br, array3);
            this.do(6.2831855f, this.aJ, array4);
        }
        else {
            this.do(this.aS, this.br, array3);
            this.do(this.a6, this.aJ, array4);
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
    
    public void a(final a.a.a.a.c.b.a a) {
        final int[] array = new int[2];
        final float[] array2 = new float[3];
        if (Math.abs(a.if - a.try) > 0.95f && Math.abs(a.a - a.new) > 0.95f) {
            return;
        }
        this.a((a.if + a.try) * 0.5f, (a.a + a.new) * 0.5f, array2);
        if (Math.abs(array2[0] - this.aH) > 1.22f) {
            return;
        }
        if (Math.abs(array2[1] - this.bg) > 1.22f && Math.abs(array2[1] - this.bg) < 5.0631857f) {
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
        if (n5 >= this.ba.new) {
            n5 = this.ba.new - 1;
        }
        if (n6 >= this.ba.do) {
            n6 = this.ba.do - 1;
        }
        final int n7 = n3 - n;
        final int n8 = n4 - n2;
        final int n9 = n5 - n;
        for (int n10 = n6 - n2, i = n8; i <= n10; ++i) {
            for (int j = n7; j <= n9; ++j) {
                final int n11 = j * j + i * i;
                if ((n11 <= 144 && n11 >= 70) || (n11 <= 36 && n11 >= 6)) {
                    this.ba.a[(n2 + i) * this.ba.new + (n + j)] = super.ae.getRGB();
                }
            }
        }
    }
    
    protected void x() {
        final Enumeration<a.a.a.a.c.b.a> elements = super.ax.elements();
        while (elements.hasMoreElements()) {
            final a.a.a.a.c.b.a a = elements.nextElement();
            if (a.byte) {
                System.out.println("drawHotspot : " + a.toString());
                this.a(a);
            }
        }
    }
    
    public boolean C() {
        return this.bh;
    }
    
    public float z() {
        return this.bv;
    }
    
    public void if(final float[] array) {
        array[0] = this.aH;
        array[1] = this.bg;
        array[2] = this.a8;
    }
    
    public float y() {
        return this.bm;
    }
    
    public void r() {
        this.bc = (float)super.W.try("param/pano.autospin");
        this.bh = (this.bc == 0.0f);
        float[] array = (float[])super.W.try("param/pano.initialView");
        if (array == null) {
            array = new float[] { 0.0f, (this.aS == this.a6) ? 3.1415927f : ((this.a6 - this.aS) * 0.5f), 1.2217306f };
        }
        this.a(array[0], array[1], 0.0f, array[2]);
        this.a((float)super.W.try("param/minZoomAngle"), false);
        this.a(array[2]);
        this.a(new float[] { array[0], array[1], 0.0f });
        this.a(array[2]);
        this.a9 = (this.bc < 0.0f);
        this.bj = 20L;
        this.aZ = 0.0f;
        if (!this.bh) {
            if (this.a6 == 0.0f && this.aS == 0.0f) {
                this.aZ = 6.2831855f / (Math.abs(this.bc) * 15.0f);
            }
            else {
                this.aZ = (this.a6 - this.aS) / (Math.abs(this.bc) * 15.0f);
            }
        }
        if (super.aq == null) {
            System.err.println("fImageName null!");
            return;
        }
        super.r();
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
        this.A();
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
            super.aq = i.a(dataInputStream, true);
            Label_0164: {
                if (!b) {
                    break Label_0164;
                }
                super.aq = i.a(dataInputStream, true);
                try {
                    while (true) {
                        final String a = i.a(dataInputStream, false);
                        if (a.equalsIgnoreCase("type")) {
                            final String a2 = i.a(dataInputStream, false);
                            if (a2.equalsIgnoreCase("SPHERE")) {
                                this.aR = 0;
                            }
                            else if (a2.equalsIgnoreCase("CYLINDER")) {
                                this.aR = 1;
                            }
                            else if (a2.equalsIgnoreCase("CUBE")) {
                                this.aR = 2;
                            }
                            else {
                                this.aR = 0;
                            }
                        }
                        else if (a.equalsIgnoreCase("vFov")) {
                            this.br = Float.valueOf(i.a(dataInputStream, false));
                            this.aJ = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("hFov")) {
                            this.aS = Float.valueOf(i.a(dataInputStream, false));
                            this.a6 = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("pitchRange")) {
                            this.aU = Float.valueOf(i.a(dataInputStream, false));
                            this.aW = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else {
                            if (!a.equalsIgnoreCase("yawRange")) {
                                continue;
                            }
                            this.bo = Float.valueOf(i.a(dataInputStream, false));
                            this.a2 = Float.valueOf(i.a(dataInputStream, false));
                        }
                    }
                }
                catch (EOFException ex2) {}
            }
        }
        catch (Exception ex) {
            this.A();
            ex.printStackTrace();
        }
        if (this.aU == -100.0f) {
            this.aU = this.br;
        }
        if (this.aW == -100.0f) {
            this.aW = this.aJ;
        }
        if (this.bo == -100.0f) {
            this.bo = this.aS;
        }
        if (this.a2 == -100.0f) {
            this.a2 = this.a6;
        }
    }
    
    private void if(final int n) {
        this.bh = true;
        final float[] array = new float[3];
        final float n2 = 0.3f;
        final float n3 = 0.3f;
        float n5;
        float n4 = n5 = 0.0f;
        float n6 = 0.1f;
        if (super.aD) {
            n6 = 0.3f;
        }
        final float y = this.y();
        this.if(array);
        if (super.ao) {
            final float n7 = super.aC - super.L;
            final float n8 = super.K - super.aB;
            final float n9 = n7 / this.aN;
            final float n10 = n8 / this.aL;
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
            array2[n13] += n12 * n3 * y;
            final float[] array3 = array;
            final int n14 = 1;
            array3[n14] += n11 * n2 * y;
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
            array4[n15] += n4 * y;
            final float[] array5 = array;
            final int n16 = 1;
            array5[n16] += n5 * y;
        }
        array[2] = 0.0f;
        this.a(array);
        this.f();
    }
    
    protected void if(final a.a.a.a.c.b.a a) {
        super.appContext.showStatus(a.do);
    }
    
    public void q() {
        super.q();
        this.aG = new float[3];
        this.bt = new float[3];
        this.bd = new float[3];
        this.a7 = new float[3];
        this.aF = new float[3];
        this.aK = new float[3];
        this.aH = 0.0f;
        this.bg = 3.1415927f;
        this.a8 = 0.0f;
        this.bm = 1.2217306f;
        this.br = -1.5707964f;
        this.aJ = 1.5707964f;
        this.aS = 0.0f;
        this.a6 = 0.0f;
        this.bv = 0.08726647f;
        try {
            this.a(new URL((URL)super.W.try("param/documentbase"), (String)super.W.try("param/file")));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.A();
        }
        if (super.aq == null) {
            this.a(this.int("iip01"), this.int("iip02"));
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
        Label_0296: {
            switch (keyEvent.getID()) {
                case 401: {
                    switch (keyEvent.getKeyCode()) {
                        case 10: {
                            if (super.aw.byte() == 1) {
                                this.if(true);
                                break Label_0296;
                            }
                            break Label_0296;
                        }
                        case 16: {
                            super.aD = true;
                            break Label_0296;
                        }
                        case 33:
                        case 34:
                        case 65:
                        case 90: {
                            this.bh = true;
                            break Label_0296;
                        }
                        case 38: {
                            this.bh = true;
                            this.if(2);
                            break Label_0296;
                        }
                        case 40: {
                            this.bh = true;
                            this.if(3);
                            break Label_0296;
                        }
                        case 37: {
                            this.bh = true;
                            this.if(0);
                            break Label_0296;
                        }
                        case 39: {
                            this.bh = true;
                            this.if(1);
                            break Label_0296;
                        }
                        case 82: {
                            this.b();
                            break Label_0296;
                        }
                        case 76: {
                            this.w();
                            break Label_0296;
                        }
                        case 80: {
                            this.v();
                            break Label_0296;
                        }
                    }
                    break;
                }
                case 402: {
                    this.f();
                    switch (keyEvent.getKeyCode()) {
                        case 16: {
                            super.aD = false;
                            break Label_0296;
                        }
                    }
                    break;
                }
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        Label_0277: {
            if (mouseEvent.isPopupTrigger()) {
                this.bu = true;
            }
            else if (this.bu) {
                this.bu = false;
            }
            else {
                switch (mouseEvent.getID()) {
                    case 501: {
                        this.bh = true;
                        switch (super.aw.if()) {
                            case 2: {
                                this.setCursor((Cursor)super.W.try("cursor/zoomin"));
                                break Label_0277;
                            }
                            case 3: {
                                this.setCursor((Cursor)super.W.try("cursor/zoomout"));
                                break Label_0277;
                            }
                            case 5: {
                                this.setCursor((Cursor)super.W.try("cursor/rotate"));
                                break Label_0277;
                            }
                        }
                        break;
                    }
                    case 502: {
                        this.f();
                        if (Math.abs(super.aC - super.as) >= 3 || Math.abs(super.aB - super.ar) >= 3) {
                            break;
                        }
                        if (super.aw.byte() == 1 && this.if(true)) {
                            break;
                        }
                        switch (super.aw.if()) {
                            case 2: {
                                this.p();
                                break Label_0277;
                            }
                            case 3: {
                                this.n();
                                break Label_0277;
                            }
                        }
                        break;
                    }
                    case 505: {
                        this.f();
                        break;
                    }
                }
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                this.f();
                break;
            }
            case 503: {
                super.aC = mouseEvent.getX();
                super.aB = mouseEvent.getY();
                if (super.aw.byte() == 1) {
                    this.if(false);
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    protected synchronized void c() {
        final Image d = this.D();
        if (d == null) {
            this.f();
            return;
        }
        if (super.av == null) {
            super.av = this.createImage(super.ai.width, super.ai.height);
        }
        synchronized (super.av) {
            final Graphics graphics = super.av.getGraphics();
            graphics.drawImage(d, 0, 0, super.ai.width, super.ai.height, null);
            graphics.dispose();
        }
        // monitorexit(super.av)
        this.repaint();
    }
    
    public void o() {
        final float[] array = { this.aY, this.be, 0.0f };
        this.a(this.bf);
        this.a(array);
        this.a(this.bf);
        this.a9 = (this.bc < 0.0f);
        this.bh = (this.bc == 0.0f);
        super.o();
    }
    
    public void w() {
        this.a9 = false;
        this.B();
    }
    
    public void v() {
        this.bh = true;
        this.f();
    }
    
    public void b() {
        this.a9 = true;
        this.B();
    }
    
    private void B() {
        this.bh = (this.bc == 0.0f);
        this.f();
    }
    
    public boolean g() {
        if (!this.bh) {
            final float[] array = new float[3];
            this.if(array);
            final float n = array[1];
            if (this.a9) {
                final float[] array2 = array;
                final int n2 = 1;
                array2[n2] += this.aZ;
            }
            else {
                final float[] array3 = array;
                final int n3 = 1;
                array3[n3] -= this.aZ;
            }
            array[0] = this.aY;
            array[2] = this.aV;
            try {
                this.c();
            }
            catch (Exception ex) {}
            this.a(array);
            this.if(array);
            if (Math.abs(array[1] - n) < 1.0E-4f) {
                this.a9 ^= true;
            }
            try {
                Thread.sleep(this.bj);
            }
            catch (InterruptedException ex2) {
                this.v();
            }
            return true;
        }
        if (super.ao && !this.bu) {
            switch (super.aw.if()) {
                case 2: {
                    this.p();
                    break;
                }
                case 3: {
                    this.n();
                    break;
                }
            }
            this.if(4);
        }
        if (super.T) {
            super.T = false;
            this.c();
            return true;
        }
        return false;
    }
    
    public void a(final float ay, final float be, final float av, final float bf) {
        this.aY = ay;
        this.be = be;
        this.aV = av;
        this.bf = bf;
        this.bh = (this.bc == 0.0f);
        this.f();
    }
    
    public void a(final float bv, final boolean b) {
        this.bv = bv;
        if (b) {
            this.a(this.y());
        }
        this.f();
    }
    
    public void a(final float[] array) {
        this.aH = array[0];
        this.bg = array[1];
        this.a8 = array[2];
        final float n = this.bm * 0.5f;
        if (Math.abs(this.a2 - this.bo) > 0.001f) {
            this.bg = this.a(this.bg, this.bo, this.a2);
        }
        if (this.aH > 1.5707964f) {
            this.aH = 1.5707964f;
        }
        if (this.aH < -1.5707964f) {
            this.aH = -1.5707964f;
        }
        if (this.a6 - this.aS > 0.001f) {
            final float n2 = n * (this.aN / this.aL);
            if (this.bg + n2 > this.a6 - 0.1f) {
                this.bg = this.a6 - n2 - 0.1f;
            }
            if (this.bg - n2 < this.aS + 0.1f) {
                this.bg = this.aS + n2 + 0.1f;
            }
            if (this.bg > this.a6 || this.bg < this.aS) {
                this.bg = (this.a6 - this.aS) / 2.0f;
            }
        }
        while (this.bg < 0.0f) {
            this.bg += 6.2831855f;
        }
        while (this.bg > 6.2831855f) {
            this.bg -= 6.2831855f;
        }
        if (this.aW < 1.5707964f && this.aH + n > this.aW - 1.0E-4f) {
            this.aH = this.aW - n - 1.0E-4f;
        }
        if (this.aU > -1.5707964f && this.aH - n < this.aU + 1.0E-4f) {
            this.aH = this.aU + n + 1.0E-4f;
        }
        this.aP = (float)Math.cos(this.aH);
        this.bp = (float)Math.sin(this.aH);
    }
    
    public void a(final float bm) {
        this.bm = bm;
        if (this.bm < this.bv) {
            this.bm = this.bv;
        }
        if (this.bm > 2.094f) {
            this.bm = 2.094f;
        }
        if (this.bm > this.aJ - this.br - 1.0E-4f) {
            this.bm = this.aJ - this.br - 1.0E-4f;
        }
        if (this.bm > this.aW - this.aU - 1.0E-4f) {
            this.bm = this.aW - this.aU - 1.0E-4f;
        }
        float n = this.bm * this.aN / this.aL;
        if (n > 2.094f) {
            n = 2.094f;
            this.bm = n * this.aL / this.aN;
        }
        if (this.a6 - this.aS > 0.05f && n > this.a6 - this.aS - 0.05f) {
            n = this.a6 - this.aS - 0.05f;
            this.bm = n * this.aL / this.aN;
        }
        if (this.a2 - this.bo > 0.05f && n > this.a2 - this.bo - 0.05f) {
            this.bm = (this.a2 - this.bo - 0.05f) * this.aL / this.aN;
        }
    }
    
    public void else(final String s) {
        float n;
        try {
            n = Float.valueOf(s) * 3.1415927f / 180.0f;
        }
        catch (Exception ex) {
            n = 0.08726647f;
        }
        this.a(n, false);
    }
    
    public void char(final String s) {
        try {
            this.bc = Float.valueOf(s);
        }
        catch (Exception ex) {
            this.bc = 20.0f;
        }
        this.bh = (this.bc == 0.0f);
        float[] array = (float[])super.W.try("param/pano.initialView");
        if (array == null) {
            array = new float[] { 0.0f, (this.aS == this.a6) ? 3.1415927f : ((this.a6 - this.aS) * 0.5f), 1.2217306f };
        }
        this.a(array[0], array[1], 0.0f, array[2]);
        this.a((float)super.W.try("param/minZoomAngle"), false);
        this.a(array[2]);
        this.a(new float[] { array[0], array[1], 0.0f });
        this.a(array[2]);
        this.a9 = (this.bc < 0.0f);
        this.bj = 20L;
        this.aZ = 0.0f;
        if (!this.bh) {
            if (this.a6 == 0.0f && this.aS == 0.0f) {
                this.aZ = 6.2831855f / (Math.abs(this.bc) * 15.0f);
            }
            else {
                this.aZ = (this.a6 - this.aS) / (Math.abs(this.bc) * 15.0f);
            }
        }
        if (super.aq == null) {
            System.err.println("fImageName null!");
            return;
        }
        super.r();
    }
    
    public void a(final float n, final float n2, final float[] array) {
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        this.do(this.aS, this.br, array2);
        if (this.a6 - this.aS < 0.001f) {
            this.do(6.2831855f, this.aJ, array3);
        }
        else {
            this.do(this.a6, this.aJ, array3);
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
    
    public void p() {
        final float n = -1.0f;
        final float n2 = 0.03f;
        this.bh = true;
        final float y = this.y();
        this.a(y + n * (n2 * (y * 1.5f * 1.5f)));
        this.f();
    }
    
    public void n() {
        final float n = 1.0f;
        final float n2 = 0.03f;
        this.bh = true;
        final float y = this.y();
        this.a(y + n * (n2 * (y * 1.5f * 1.5f)));
        this.f();
    }
}
