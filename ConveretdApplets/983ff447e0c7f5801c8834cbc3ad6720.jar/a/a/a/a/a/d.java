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
    protected static final boolean aT = true;
    public static final int aN = 0;
    public static final int a9 = 1;
    public static final int a2 = 2;
    public static final float aR = 5.0f;
    protected static final float bp = 1.5707964f;
    protected static final float aY = 3.1415927f;
    protected static final float bn = 6.2831855f;
    private static final float bs = 1.0E-4f;
    private static final int ba = 0;
    private static final int bx = 1;
    private static final int a6 = 2;
    private static final int bg = 3;
    private static final int a8 = 4;
    protected volatile boolean bz;
    protected int aW;
    protected float bw;
    protected float aO;
    protected float aX;
    protected float bb;
    protected float aZ;
    protected float a1;
    protected float bt;
    protected float a7;
    private float a3;
    private float bj;
    private float a0;
    protected float br;
    public float aM;
    public float bl;
    protected float bd;
    protected float aU;
    protected float bu;
    protected int aV;
    protected int bv;
    protected float aS;
    protected float aQ;
    protected float a5;
    protected float bq;
    public a.a.b.b bf;
    protected boolean bm;
    protected float bh;
    protected float[] aL;
    protected float[] by;
    protected float[] bi;
    protected float[] bc;
    protected float[] aK;
    protected float[] aP;
    private float bA;
    private float bk;
    private long bo;
    private boolean be;
    private float a4;
    
    public d(final a a, final String s) {
        super(a, s);
        this.bz = false;
        this.aV = 0;
        this.bv = 0;
        this.bf = null;
        this.bm = true;
        this.bh = 20.0f;
        this.bo = 50L;
        this.be = false;
    }
    
    private void if(final float n, final float n2, final float[] array) {
        if (this.aW == 0) {
            array[0] = n2;
            array[1] = n;
        }
        else if (this.aW == 1) {
            final float n3 = (float)Math.tan(this.aO);
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
        else if (this.aW == 2) {
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
        if (super.aC == null) {
            super.aC = new Vector();
        }
        super.aC.addElement(a);
        super.aC.addElement(a);
        super.new(s);
    }
    
    public void a(float n, final float n2, final int[] array) {
        final float n3 = this.br * 0.5f;
        final float n4 = n3 * this.aS / this.aQ;
        n -= this.bl;
        final float n5 = (float)Math.tan(n3);
        final float n6 = (float)Math.tan(n2);
        final float n7 = (float)Math.cos(n);
        final float n8 = (n6 * this.aU - this.bu * n7) / ((n7 * this.aU + n6 * this.bu) * n5);
        array[0] = (int)(this.a5 * ((float)Math.tan(n) * (this.aU - n5 * n8 * this.bu) / (float)Math.tan(n4) / 2.0f + 0.5f));
        array[1] = (int)(this.bq * (0.5f - n8 / 2.0f));
    }
    
    private void do(final float n, final float n2, final float[] array) {
        if (this.aW == 0) {
            array[0] = n;
            array[1] = n2;
        }
        else if (this.aW == 1) {
            array[0] = n;
            final float n3 = (float)Math.tan(this.aO);
            if (n2 > this.aO) {
                array[1] = n3 + 1.0f - n3 * (float)Math.tan(1.5707964f - n2);
            }
            else if (n2 < -this.aO) {
                array[1] = -n3 - (1.0f - n3 * (float)Math.tan(1.5707964f + n2));
            }
            else {
                array[1] = (float)Math.tan(n2);
            }
        }
        else if (this.aW == 2) {
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
    
    protected abstract Image E();
    
    protected void a(final a.a.a.a.c.b.a a, final URL url) {
        try {
            super.appContext.showDocument(new URL(url, a.for), a.int);
        }
        catch (Exception ex) {}
    }
    
    protected boolean if(final boolean b) {
        if (super.aC == null) {
            return false;
        }
        final float[] array = new float[2];
        this.a(super.aH, super.aG, array);
        final URL url = (URL)super.X.byte("param/documentbase");
        final int[] array2 = new int[2];
        final float[] array3 = new float[3];
        final Enumeration<a.a.a.a.c.b.a> elements = (Enumeration<a.a.a.a.c.b.a>)super.aC.elements();
        while (elements.hasMoreElements()) {
            final a.a.a.a.c.b.a a = elements.nextElement();
            if (a.byte) {
                this.a((a.if + a.try) * 0.5f, (a.a + a.new) * 0.5f, array3);
                this.a(array3[1], array3[0], array2);
                if (this.a(array[0], array[1], super.aH, super.aG, array2[0], array2[1], a)) {
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
        this.k();
        return false;
    }
    
    public void B() {
        b.au = null;
        this.aW = 0;
        this.bw = -1.5707964f;
        this.aO = 1.5707964f;
        this.aX = 0.0f;
        this.bb = 0.0f;
        this.aZ = -100.0f;
        this.a1 = -100.0f;
        this.bt = -100.0f;
        this.a7 = -100.0f;
    }
    
    protected void a(final int n, final int n2, final float[] array) {
        final float n3 = n / this.a5;
        final float n4 = n2 / this.bq;
        final float n5 = this.br * 0.5f;
        final float n6 = n5 * this.aS / this.aQ;
        float n7 = (n3 - 0.5f) * 2.0f;
        if (n7 < 0.005 && n7 > -0.005) {
            n7 = 0.0f;
        }
        float n8 = (0.5f - n4) * 2.0f;
        if (n8 < 0.005 && n8 > -0.005) {
            n8 = 0.0f;
        }
        final float au = this.aU;
        final float bu = this.bu;
        final float n9 = (float)Math.tan(n5);
        final float n10 = au - n9 * n8 * bu;
        final float n11 = bu + n9 * n8 * au;
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
        float n16 = n13 + this.bl;
        if (n15 <= this.bw) {
            n15 = -n15 - 3.1415927f;
        }
        else if (n15 > this.aO) {
            n15 = 3.1415927f - n15;
        }
        if (this.bb - this.aX < 0.001f) {
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
        if (this.bb - this.aX < 0.001f) {
            this.do(0.0f, this.bw, array3);
            this.do(6.2831855f, this.aO, array4);
        }
        else {
            this.do(this.aX, this.bw, array3);
            this.do(this.bb, this.aO, array4);
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
        if (Math.abs(array2[0] - this.aM) > 1.22f) {
            return;
        }
        if (Math.abs(array2[1] - this.bl) > 1.22f && Math.abs(array2[1] - this.bl) < 5.0631857f) {
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
        if (n5 >= this.bf.new) {
            n5 = this.bf.new - 1;
        }
        if (n6 >= this.bf.do) {
            n6 = this.bf.do - 1;
        }
        final int n7 = n3 - n;
        final int n8 = n4 - n2;
        final int n9 = n5 - n;
        for (int n10 = n6 - n2, i = n8; i <= n10; ++i) {
            for (int j = n7; j <= n9; ++j) {
                final int n11 = j * j + i * i;
                if ((n11 <= 144 && n11 >= 70) || (n11 <= 36 && n11 >= 6)) {
                    this.bf.a[(n2 + i) * this.bf.new + (n + j)] = super.ah.getRGB();
                }
            }
        }
    }
    
    protected void y() {
        final Enumeration<a.a.a.a.c.b.a> elements = super.aC.elements();
        while (elements.hasMoreElements()) {
            final a.a.a.a.c.b.a a = elements.nextElement();
            if (a.byte) {
                System.out.println("drawHotspot : " + a.toString());
                this.if(a);
            }
        }
    }
    
    public boolean D() {
        return this.bm;
    }
    
    public float A() {
        return this.bA;
    }
    
    public void if(final float[] array) {
        array[0] = this.aM;
        array[1] = this.bl;
        array[2] = this.bd;
    }
    
    public float z() {
        return this.br;
    }
    
    public void s() {
        this.bh = (float)super.X.byte("param/pano.autospin");
        this.bm = (this.bh == 0.0f);
        float[] array = (float[])super.X.byte("param/pano.initialView");
        if (array == null) {
            array = new float[] { 0.0f, (this.aX == this.bb) ? 3.1415927f : ((this.bb - this.aX) * 0.5f), 1.2217306f };
        }
        this.a(array[0], array[1], 0.0f, array[2]);
        this.a((float)super.X.byte("param/minZoomAngle"), false);
        this.a(array[2]);
        this.a(new float[] { array[0], array[1], 0.0f });
        this.a(array[2]);
        this.be = (this.bh < 0.0f);
        this.bo = 20L;
        this.a4 = 0.0f;
        if (!this.bm) {
            if (this.bb == 0.0f && this.aX == 0.0f) {
                this.a4 = 6.2831855f / (Math.abs(this.bh) * 15.0f);
            }
            else {
                this.a4 = (this.bb - this.aX) / (Math.abs(this.bh) * 15.0f);
            }
        }
        if (b.au == null) {
            System.err.println("fImageName null!");
            return;
        }
        super.s();
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
        this.B();
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
            a.a.a.a.a.b.au = i.a(dataInputStream, true);
            Label_0162: {
                if (!b) {
                    break Label_0162;
                }
                a.a.a.a.a.b.au = i.a(dataInputStream, true);
                try {
                    while (true) {
                        final String a = i.a(dataInputStream, false);
                        if (a.equalsIgnoreCase("type")) {
                            final String a2 = i.a(dataInputStream, false);
                            if (a2.equalsIgnoreCase("SPHERE")) {
                                this.aW = 0;
                            }
                            else if (a2.equalsIgnoreCase("CYLINDER")) {
                                this.aW = 1;
                            }
                            else if (a2.equalsIgnoreCase("CUBE")) {
                                this.aW = 2;
                            }
                            else {
                                this.aW = 0;
                            }
                        }
                        else if (a.equalsIgnoreCase("vFov")) {
                            this.bw = Float.valueOf(i.a(dataInputStream, false));
                            this.aO = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("hFov")) {
                            this.aX = Float.valueOf(i.a(dataInputStream, false));
                            this.bb = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else if (a.equalsIgnoreCase("pitchRange")) {
                            this.aZ = Float.valueOf(i.a(dataInputStream, false));
                            this.a1 = Float.valueOf(i.a(dataInputStream, false));
                        }
                        else {
                            if (!a.equalsIgnoreCase("yawRange")) {
                                continue;
                            }
                            this.bt = Float.valueOf(i.a(dataInputStream, false));
                            this.a7 = Float.valueOf(i.a(dataInputStream, false));
                        }
                    }
                }
                catch (EOFException ex2) {}
            }
        }
        catch (Exception ex) {
            this.B();
            ex.printStackTrace();
        }
        if (this.aZ == -100.0f) {
            this.aZ = this.bw;
        }
        if (this.a1 == -100.0f) {
            this.a1 = this.aO;
        }
        if (this.bt == -100.0f) {
            this.bt = this.aX;
        }
        if (this.a7 == -100.0f) {
            this.a7 = this.bb;
        }
    }
    
    private void if(final int n) {
        this.bm = true;
        final float[] array = new float[3];
        final float n2 = 0.3f;
        final float n3 = 0.3f;
        float n5;
        float n4 = n5 = 0.0f;
        float n6 = 0.1f;
        if (super.aI) {
            n6 = 0.3f;
        }
        final float z = this.z();
        this.if(array);
        if (super.as) {
            final float n7 = super.aH - super.L;
            final float n8 = super.K - super.aG;
            final float n9 = n7 / this.aS;
            final float n10 = n8 / this.aQ;
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
            array2[n13] += n12 * n3 * z;
            final float[] array3 = array;
            final int n14 = 1;
            array3[n14] += n11 * n2 * z;
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
            array4[n15] += n4 * z;
            final float[] array5 = array;
            final int n16 = 1;
            array5[n16] += n5 * z;
        }
        array[2] = 0.0f;
        this.a(array);
        this.g();
    }
    
    protected void a(final a.a.a.a.c.b.a a) {
        super.appContext.showStatus(a.do);
    }
    
    public void r() {
        super.r();
        this.aL = new float[3];
        this.by = new float[3];
        this.bi = new float[3];
        this.bc = new float[3];
        this.aK = new float[3];
        this.aP = new float[3];
        this.aM = 0.0f;
        this.bl = 3.1415927f;
        this.bd = 0.0f;
        this.br = 1.2217306f;
        this.bw = -1.5707964f;
        this.aO = 1.5707964f;
        this.aX = 0.0f;
        this.bb = 0.0f;
        this.bA = 0.08726647f;
        try {
            this.a(new URL((URL)super.X.byte("param/documentbase"), (String)super.X.byte("param/file")));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.B();
        }
        if (b.au == null) {
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
        Label_0292: {
            switch (keyEvent.getID()) {
                case 401: {
                    switch (keyEvent.getKeyCode()) {
                        case 10: {
                            if (super.aA.byte() == 1) {
                                this.if(true);
                                break Label_0292;
                            }
                            break Label_0292;
                        }
                        case 16: {
                            super.aI = true;
                            break Label_0292;
                        }
                        case 33:
                        case 34:
                        case 65:
                        case 90: {
                            this.bm = true;
                            break Label_0292;
                        }
                        case 38: {
                            this.bm = true;
                            this.if(2);
                            break Label_0292;
                        }
                        case 40: {
                            this.bm = true;
                            this.if(3);
                            break Label_0292;
                        }
                        case 37: {
                            this.bm = true;
                            this.if(0);
                            break Label_0292;
                        }
                        case 39: {
                            this.bm = true;
                            this.if(1);
                            break Label_0292;
                        }
                        case 82: {
                            this.c();
                            break Label_0292;
                        }
                        case 76: {
                            this.x();
                            break Label_0292;
                        }
                        case 80: {
                            this.w();
                            break Label_0292;
                        }
                    }
                    break;
                }
                case 402: {
                    switch (keyEvent.getKeyCode()) {
                        case 16: {
                            super.aI = false;
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
                this.bz = true;
            }
            else if (this.bz) {
                this.bz = false;
            }
            else {
                switch (mouseEvent.getID()) {
                    case 501: {
                        this.bm = true;
                        switch (super.aA.if()) {
                            case 2: {
                                this.setCursor((Cursor)super.X.byte("cursor/zoomin"));
                                break Label_0266;
                            }
                            case 3: {
                                this.setCursor((Cursor)super.X.byte("cursor/zoomout"));
                                break Label_0266;
                            }
                            case 5: {
                                this.setCursor((Cursor)super.X.byte("cursor/rotate"));
                                break Label_0266;
                            }
                        }
                        break;
                    }
                    case 502: {
                        if (Math.abs(super.aH - super.aw) >= 3 || Math.abs(super.aG - super.av) >= 3) {
                            break;
                        }
                        if (super.aA.byte() == 1 && this.if(true)) {
                            break;
                        }
                        switch (super.aA.if()) {
                            case 2: {
                                this.q();
                                break Label_0266;
                            }
                            case 3: {
                                this.o();
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
                super.aH = mouseEvent.getX();
                super.aG = mouseEvent.getY();
                if (super.aA.byte() == 1) {
                    this.if(false);
                    break;
                }
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    protected synchronized void d() {
        final Image e = this.E();
        if (e == null) {
            this.g();
            return;
        }
        if (super.az == null) {
            super.az = this.createImage(super.al.width, super.al.height);
        }
        synchronized (super.az) {
            final Graphics graphics = super.az.getGraphics();
            graphics.drawImage(e, 0, 0, super.al.width, super.al.height, null);
            graphics.dispose();
        }
        // monitorexit(super.az)
        this.repaint();
    }
    
    public void p() {
        final float[] array = { this.a3, this.bj, 0.0f };
        this.a(this.bk);
        this.a(array);
        this.a(this.bk);
        this.be = (this.bh < 0.0f);
        this.bm = (this.bh == 0.0f);
        super.p();
    }
    
    public void x() {
        this.be = false;
        this.C();
    }
    
    public void w() {
        this.bm = true;
        this.g();
    }
    
    public void c() {
        this.be = true;
        this.C();
    }
    
    private void C() {
        this.bm = (this.bh == 0.0f);
        this.g();
    }
    
    public boolean h() {
        if (!this.bm) {
            final float[] array = new float[3];
            this.if(array);
            final float n = array[1];
            if (this.be) {
                final float[] array2 = array;
                final int n2 = 1;
                array2[n2] += this.a4;
            }
            else {
                final float[] array3 = array;
                final int n3 = 1;
                array3[n3] -= this.a4;
            }
            array[2] = this.a0;
            try {
                this.d();
            }
            catch (Exception ex) {}
            this.a(array);
            this.if(array);
            if (Math.abs(array[1] - n) < 1.0E-4f) {
                this.be ^= true;
            }
            try {
                Thread.sleep(this.bo);
            }
            catch (InterruptedException ex2) {
                this.w();
            }
            return true;
        }
        if (super.as && !this.bz) {
            switch (super.aA.if()) {
                case 2: {
                    this.q();
                    break;
                }
                case 3: {
                    this.o();
                    break;
                }
            }
            this.if(4);
        }
        if (super.U) {
            super.U = false;
            this.d();
            return true;
        }
        return false;
    }
    
    public void a(final float a3, final float bj, final float a4, final float bk) {
        this.a3 = a3;
        this.bj = bj;
        this.a0 = a4;
        this.bk = bk;
        this.bm = (this.bh == 0.0f);
        this.g();
    }
    
    public void a(final float ba, final boolean b) {
        this.bA = ba;
        if (b) {
            this.a(this.z());
        }
        this.g();
    }
    
    public void a(final float[] array) {
        this.aM = array[0];
        this.bl = array[1];
        this.bd = array[2];
        final float n = this.br * 0.5f;
        if (Math.abs(this.a7 - this.bt) > 0.001f) {
            this.bl = this.a(this.bl, this.bt, this.a7);
        }
        if (this.aM > 1.5707964f) {
            this.aM = 1.5707964f;
        }
        if (this.aM < -1.5707964f) {
            this.aM = -1.5707964f;
        }
        if (this.bb - this.aX > 0.001f) {
            final float n2 = n * (this.aS / this.aQ);
            if (this.bl + n2 > this.bb - 0.1f) {
                this.bl = this.bb - n2 - 0.1f;
            }
            if (this.bl - n2 < this.aX + 0.1f) {
                this.bl = this.aX + n2 + 0.1f;
            }
            if (this.bl > this.bb || this.bl < this.aX) {
                this.bl = (this.bb - this.aX) / 2.0f;
            }
        }
        while (this.bl < 0.0f) {
            this.bl += 6.2831855f;
        }
        while (this.bl > 6.2831855f) {
            this.bl -= 6.2831855f;
        }
        if (this.a1 < 1.5707964f && this.aM + n > this.a1 - 1.0E-4f) {
            this.aM = this.a1 - n - 1.0E-4f;
        }
        if (this.aZ > -1.5707964f && this.aM - n < this.aZ + 1.0E-4f) {
            this.aM = this.aZ + n + 1.0E-4f;
        }
        this.aU = (float)Math.cos(this.aM);
        this.bu = (float)Math.sin(this.aM);
    }
    
    public void a(final float br) {
        this.br = br;
        if (this.br < this.bA) {
            this.br = this.bA;
        }
        if (this.br > 2.094f) {
            this.br = 2.094f;
        }
        if (this.br > this.aO - this.bw - 1.0E-4f) {
            this.br = this.aO - this.bw - 1.0E-4f;
        }
        if (this.br > this.a1 - this.aZ - 1.0E-4f) {
            this.br = this.a1 - this.aZ - 1.0E-4f;
        }
        float n = this.br * this.aS / this.aQ;
        if (n > 2.094f) {
            n = 2.094f;
            this.br = n * this.aQ / this.aS;
        }
        if (this.bb - this.aX > 0.05f && n > this.bb - this.aX - 0.05f) {
            n = this.bb - this.aX - 0.05f;
            this.br = n * this.aQ / this.aS;
        }
        if (this.a7 - this.bt > 0.05f && n > this.a7 - this.bt - 0.05f) {
            this.br = (this.a7 - this.bt - 0.05f) * this.aQ / this.aS;
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
            this.bh = Float.valueOf(s);
        }
        catch (Exception ex) {
            this.bh = 20.0f;
        }
        this.bm = (this.bh == 0.0f);
        float[] array = (float[])super.X.byte("param/pano.initialView");
        if (array == null) {
            array = new float[] { 0.0f, (this.aX == this.bb) ? 3.1415927f : ((this.bb - this.aX) * 0.5f), 1.2217306f };
        }
        this.a(array[0], array[1], 0.0f, array[2]);
        this.a((float)super.X.byte("param/minZoomAngle"), false);
        this.a(array[2]);
        this.a(new float[] { array[0], array[1], 0.0f });
        this.a(array[2]);
        this.be = (this.bh < 0.0f);
        this.bo = 20L;
        this.a4 = 0.0f;
        if (!this.bm) {
            if (this.bb == 0.0f && this.aX == 0.0f) {
                this.a4 = 6.2831855f / (Math.abs(this.bh) * 15.0f);
            }
            else {
                this.a4 = (this.bb - this.aX) / (Math.abs(this.bh) * 15.0f);
            }
        }
        if (b.au == null) {
            System.err.println("fImageName null!");
            return;
        }
        super.s();
    }
    
    public void a(final float n, final float n2, final float[] array) {
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        this.do(this.aX, this.bw, array2);
        if (this.bb - this.aX < 0.001f) {
            this.do(6.2831855f, this.aO, array3);
        }
        else {
            this.do(this.bb, this.aO, array3);
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
    
    public void q() {
        final float n = -1.0f;
        final float n2 = 0.03f;
        this.bm = true;
        final float z = this.z();
        this.a(z + n * (n2 * (z * 1.5f * 1.5f)));
        this.g();
    }
    
    public void o() {
        final float n = 1.0f;
        final float n2 = 0.03f;
        this.bm = true;
        final float z = this.z();
        this.a(z + n * (n2 * (z * 1.5f * 1.5f)));
        this.g();
    }
}
