// 
// Decompiled by Procyon v0.5.30
// 

public class t extends a
{
    protected j bm;
    boolean aB;
    protected float au;
    protected float aK;
    protected float bp;
    protected float bC;
    protected float aL;
    protected float av;
    boolean a2;
    boolean a8;
    boolean a7;
    int aF;
    int aD;
    int bb;
    String aN;
    public static final int as = 0;
    public static final int bu = 1;
    public static final int bk = 2;
    public static final int a0 = 3;
    public static final int a3 = 4;
    private int a4;
    int aP;
    int bD;
    int be;
    a[] aH;
    a[] ax;
    float[][] at;
    u[] ay;
    int bl;
    static final char[] aV;
    boolean bE;
    al bh;
    al bw;
    float aY;
    float bv;
    float bf;
    int bz;
    boolean aA;
    int bx;
    boolean bg;
    al[][] bo;
    float[] bA;
    float[] aJ;
    float ar;
    float aM;
    double[] ba;
    double[] a9;
    private boolean bt;
    public float bc;
    public float aI;
    public float bi;
    public float bB;
    public float aZ;
    private float aU;
    private float bj;
    private float aT;
    an a5;
    float bd;
    boolean az;
    boolean aC;
    int bs;
    int bq;
    int aG;
    int aE;
    long br;
    boolean a6;
    float aQ;
    float aX;
    float aR;
    float by;
    float aw;
    float bF;
    boolean aO;
    int a1;
    int aS;
    int bn;
    int aW;
    
    static {
        aV = new char[1];
    }
    
    public void a(final l int1, final float e, final aa byte1, final t g, final v f, final int a4) {
        this.a4 = a4;
        super.F = f;
        super.try = super.F.f;
        super.int = int1;
        super.E = e;
        super.H = 1.0f / super.E;
        super.byte = byte1;
        super.G = g;
        switch (this.a4) {
            case 0: {
                this.bx = 6;
                break;
            }
            case 3: {
                this.ba = new double[4];
                this.a9 = new double[4];
                break;
            }
            case 2: {
                this.bp = 0.7853982f;
                this.bC = -this.bp;
                this.au = 3.1415927f;
                this.aK = -this.au;
                break;
            }
            case 1: {
                this.bp = 1.5707964f;
                this.bC = -this.bp;
                this.au = 3.1415927f;
                this.aK = -this.au;
                break;
            }
            case 4: {
                super.case = true;
                this.a5 = new an();
                this.a2 = false;
                this.a8 = false;
                this.aB = false;
                break;
            }
        }
    }
    
    protected void void() {
        switch (this.a4) {
            case 0: {
                this.bo = new al[6][];
                this.aF = (int)(17.0f * super.int.g / 100.0f);
                if (this.aF / 2 * 2 == this.aF) {
                    ++this.aF;
                }
                this.aD = this.aF;
                for (int i = 0; i < 6; ++i) {
                    this.bo[i] = new al[this.aF * this.aD];
                    for (int j = 0; j < this.aF * this.aD; ++j) {
                        this.bo[i][j] = new al();
                    }
                }
                this.a(this.bo);
                break;
            }
            case 2: {
                final int n = 17;
                this.aF = (int)(n * 2 * super.int.g / 100.0f);
                this.aD = (int)(n * super.int.g / 100.0f);
                (this.bo = new al[1][])[0] = new al[this.aF * this.aD];
                final double tan = Math.tan(this.bp);
                final double tan2 = Math.tan(this.bC);
                for (int k = 0; k < this.aD; ++k) {
                    for (int l = 0; l < this.aF; ++l) {
                        final int n2 = l + k * this.aF;
                        this.bo[0][n2] = new al();
                        final float n3 = this.au + l * (-this.au + this.aK) / (this.aF - 1);
                        this.bo[0][n2].byte = (float)(tan + k * (-tan + tan2) / (this.aD - 1));
                        this.bo[0][n2].try = (float)Math.cos(n3);
                        this.bo[0][n2].if = (float)Math.sin(n3);
                        this.bo[0][n2].do = l * this.bm.long / (this.aF - 1);
                        this.bo[0][n2].a = k * this.bm.e / (this.aD - 1);
                    }
                }
                break;
            }
            case 3: {
                if (this.bt) {
                    return;
                }
                final al[] array = new al[4];
                this.ar = (this.bA[0] + this.bA[1] + this.bA[2] + this.bA[3]) / 4.0f;
                this.aM = (this.aJ[0] + this.aJ[1] + this.aJ[2] + this.aJ[3]) / 4.0f;
                for (int n4 = 0; n4 < 4; ++n4) {
                    final float n5 = (float)Math.sin(this.aJ[n4]);
                    final float n6 = (float)(Math.cos(this.bA[n4]) * Math.cos(this.aJ[n4]));
                    final float n7 = (float)(Math.sin(this.bA[n4]) * Math.cos(this.aJ[n4]));
                    final float n8 = (float)(Math.cos(this.ar) * Math.cos(this.aM));
                    final float n9 = (float)(Math.cos(this.ar) * n6 + Math.sin(this.ar) * n7);
                    final float n10 = 1.0f / (float)(Math.cos(this.aM) * n9 + Math.sin(this.aM) * n5);
                    this.ba[n4] = (-Math.cos(this.ar) * n7 + Math.sin(this.ar) * n6) * n10;
                    this.a9[n4] = -(Math.cos(this.aM) * n5 - Math.sin(this.aM) * n9) * n10;
                    final double sqrt = Math.sqrt(this.ba[n4] * this.ba[n4] + this.a9[n4] * this.a9[n4] + 1.0);
                    array[n4] = new al();
                    array[n4].byte = (float)sqrt * (float)Math.sin(this.aJ[n4]);
                    array[n4].try = (float)sqrt * (float)(Math.cos(this.bA[n4]) * Math.cos(this.aJ[n4]));
                    array[n4].if = (float)sqrt * (float)(Math.sin(this.bA[n4]) * Math.cos(this.aJ[n4]));
                }
                this.aF = (int)(17.0f * super.int.g / 100.0f);
                this.aD = (int)(17.0f * super.int.g / 100.0f);
                (this.bo = new al[1][])[0] = new al[this.aF * this.aD];
                final float n11 = (this.bm.long - 1) / (this.aF - 1);
                final float n12 = (this.bm.e - 1) / (this.aD - 1);
                for (int n13 = 0; n13 < this.aD; ++n13) {
                    final float n14 = array[0].try + n13 * (-array[0].try + array[3].try) / (this.aD - 1.0f);
                    final float n15 = array[1].try + n13 * (-array[1].try + array[2].try) / (this.aD - 1.0f);
                    final float n16 = array[0].if + n13 * (-array[0].if + array[3].if) / (this.aD - 1.0f);
                    final float n17 = array[1].if + n13 * (-array[1].if + array[2].if) / (this.aD - 1.0f);
                    final float n18 = array[0].byte + n13 * (-array[0].byte + array[3].byte) / (this.aD - 1.0f);
                    final float n19 = array[1].byte + n13 * (-array[1].byte + array[2].byte) / (this.aD - 1.0f);
                    for (int n20 = 0; n20 < this.aF; ++n20) {
                        final int n21 = n20 + n13 * this.aF;
                        this.bo[0][n21] = new al();
                        this.bo[0][n21].a = n13 * n12;
                        this.bo[0][n21].do = n20 * n11;
                        this.bo[0][n21].if = n16 + n20 * (n17 - n16) / (this.aF - 1.0f);
                        this.bo[0][n21].try = n14 + n20 * (n15 - n14) / (this.aF - 1.0f);
                        this.bo[0][n21].byte = n18 + n20 * (n19 - n18) / (this.aF - 1.0f);
                    }
                }
                break;
            }
            case 1: {
                final int ad = (int)(25.0f * super.int.g / 100.0f);
                this.aF = (int)(ad * 2.5);
                this.aD = ad;
                (this.bo = new al[1][])[0] = new al[this.aF * this.aD];
                for (int n22 = 0; n22 < this.aD; ++n22) {
                    for (int n23 = 0; n23 < this.aF; ++n23) {
                        final int n24 = n23 + n22 * this.aF;
                        this.bo[0][n24] = new al();
                        final float n25 = this.au + n23 * (-this.au + this.aK) / (this.aF - 1);
                        this.bo[0][n24].do = n23 * this.bm.long / (this.aF - 1);
                        final int n26 = (this.aD - 4) * 4;
                        int n27;
                        if (n22 == 0) {
                            n27 = 0;
                        }
                        else if (n22 == this.aD - 1) {
                            n27 = n26 - 1;
                        }
                        else if (n22 == 1) {
                            n27 = 1;
                        }
                        else if (n22 == 2) {
                            n27 = 2;
                        }
                        else if (n22 == this.aD - 2) {
                            n27 = n26 - 2;
                        }
                        else if (n22 == this.aD - 3) {
                            n27 = n26 - 4;
                        }
                        else {
                            n27 = (n22 - 2) * 4;
                        }
                        final float n28 = this.bp + n27 * (-this.bp + this.bC) / (n26 - 1);
                        this.bo[0][n24].a = n27 * this.bm.e / (n26 - 1);
                        this.bo[0][n24].byte = (float)Math.sin(n28);
                        this.bo[0][n24].try = (float)(Math.cos(n25) * Math.cos(n28));
                        this.bo[0][n24].if = (float)(Math.sin(n25) * Math.cos(n28));
                    }
                }
                break;
            }
        }
    }
    
    private void a(final al[][] array) {
        this.a(5, array[0], 0, 0, -1, 0, -1, -1, 0, 0, 0);
        this.a(3, array[1], 0, 1, 0, 1, 0, 0, 0, 0, -1);
        this.a(1, array[2], 0, -1, 0, -1, 0, 0, 0, 0, -1);
        this.a(2, array[3], -1, 0, 0, 0, 0, 1, 0, 0, -1);
        this.a(4, array[4], 0, 0, 1, 0, 1, -1, 0, 0, 0);
        this.a(0, array[5], 1, 0, 0, 0, 0, -1, 0, 0, -1);
    }
    
    private void a(int n, final al[] array, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        final int n11 = this.aF * this.aF;
        final int n12 = this.aF / 2;
        final float n13 = this.bm.long / (this.aF - 1);
        final float n14 = (this.bm.e / 6 - 1) / (this.aF - 1);
        n = n * this.bm.e / 6;
        for (int i = 0; i < this.aD; ++i) {
            for (int j = 0; j < this.aF; ++j) {
                final int n15 = j + i * this.aF;
                array[n15].try = n2 * n12 + n5 * (j - n12) + n6 * (i - n12);
                array[n15].if = n3 * n12 + n7 * (j - n12) + n8 * (i - n12);
                array[n15].byte = n4 * n12 + n9 * (j - n12) + n10 * (i - n12);
                array[n15].do = j * n13;
                array[n15].a = n + 0.5f + i * n14;
            }
        }
    }
    
    void a(final an an) {
        this.a5.c = an.c;
        this.a5.d = an.long;
        if (super.F.b.do) {
            this.a5.void = an.e;
        }
        else {
            this.a5.void = an.e - 17;
        }
        this.a5.goto = 0;
        this.a5.else = 0;
        this.a5.long = an.long;
        this.a5.e = an.e;
        this.a1 = this.a5.d;
        this.aS = this.a5.void;
        this.bn = this.a1 >> 1;
        this.aW = this.aS >> 1;
    }
    
    public void a() {
        super.a();
        this.ba = null;
        this.a9 = null;
        this.bA = null;
        this.aJ = null;
        if (this.a5 != null) {
            this.a5.a();
        }
        this.a5 = null;
        if (this.at != null) {
            for (int i = 0; i < this.at.length; ++i) {
                this.at[i] = null;
            }
            this.at = null;
        }
        if (this.ay != null) {
            for (int j = 0; j < this.ay.length; ++j) {
                this.ay[j] = null;
            }
            this.ay = null;
        }
        if (this.bo != null) {
            for (int k = 0; k < this.bo.length; ++k) {
                this.bo[k] = null;
            }
            this.bo = null;
        }
        if (this.bm != null) {
            this.bm.a();
            this.bm = null;
        }
        for (int l = 0; l < this.bD; ++l) {
            if (this.ax[l] != null) {
                this.ax[l].a();
            }
            this.ax[l] = null;
        }
        this.bD = 0;
        this.ax = null;
        for (int n = 0; n < this.aP; ++n) {
            if (this.aH[n] != null) {
                this.aH[n].a();
            }
            this.aH[n] = null;
        }
        this.aP = 0;
        this.aH = null;
    }
    
    t() {
        this.bm = null;
        this.aB = true;
        this.au = 0.0f;
        this.aK = 0.0f;
        this.bp = 0.0f;
        this.bC = 0.0f;
        this.aL = 0.01745f;
        this.av = 2.5f;
        this.a2 = true;
        this.a8 = true;
        this.a7 = false;
        this.bb = -1;
        this.aN = "";
        this.a4 = 0;
        this.aP = 0;
        this.bD = 0;
        this.be = 0;
        this.aH = null;
        this.ax = null;
        this.ay = null;
        this.bl = 0;
        this.bE = false;
        this.bh = null;
        this.bw = null;
        this.aY = 0.0f;
        this.bv = 0.0f;
        this.bf = 0.0f;
        this.bz = -2;
        this.aA = false;
        this.bx = 1;
        this.bg = false;
        this.bo = null;
        this.bt = false;
        this.aU = 0.0f;
        this.bj = 0.0f;
        this.aT = -1.0f;
        this.a5 = null;
        this.bd = 0.01f;
        this.az = false;
        this.br = 0L;
        this.a6 = false;
        this.aQ = 0.0f;
        this.aX = 0.0f;
        this.aR = 0.0f;
        this.by = 0.0f;
        this.aw = 0.0f;
        this.bF = 0.0f;
        this.aO = false;
        this.bh = new al();
        this.bw = new al();
    }
    
    public void if(final n n) {
        switch (this.a4) {
            case 3: {
                this.do(n);
                n n2 = n.if;
                this.bt = true;
                while (n2 != null) {
                    if (n2.a.toLowerCase().compareTo("rectangle") == 0) {
                        this.bt = false;
                        this.bA = new float[4];
                        this.aJ = new float[4];
                        n n3 = n2.if;
                        int n4 = 0;
                        while (n3 != null) {
                            if (n3.a.toLowerCase().compareTo("vertex") == 0) {
                                for (int i = 0; i < n3.do; ++i) {
                                    if (n3.try[i].toLowerCase().compareTo("pan") == 0) {
                                        this.bA[n4] = new Float(n3.new[i]) * super.E;
                                    }
                                    else if (n3.try[i].toLowerCase().compareTo("tilt") == 0) {
                                        this.aJ[n4] = new Float(n3.new[i]) * super.E;
                                    }
                                }
                                if (++n4 == 4) {
                                    break;
                                }
                            }
                            n3 = n3.for;
                        }
                        if (n4 != 4) {
                            this.bt = true;
                        }
                    }
                    n2 = n2.for;
                }
                return;
            }
            case 4: {
                for (n n5 = n.if; n5 != null; n5 = n5.for) {
                    if (n5.a.toLowerCase().compareTo("entrypoint") == 0) {
                        for (int j = 0; j < n5.do; ++j) {
                            if (n5.try[j].toLowerCase().compareTo("pan") == 0) {
                                this.aU = new Float(n5.new[j]) * super.E;
                            }
                            else if (n5.try[j].toLowerCase().compareTo("tilt") == 0) {
                                this.bj = new Float(n5.new[j]) * super.E;
                            }
                            else if (n5.try[j].toLowerCase().compareTo("fov") == 0) {
                                this.aT = new Float(n5.new[j]) * super.E;
                            }
                        }
                    }
                    else if (n5.a.toLowerCase().compareTo("limits") == 0) {
                        for (int k = 0; k < n5.do; ++k) {
                            if (n5.try[k].toLowerCase().compareTo("maxpan") == 0) {
                                this.au = new Float(n5.new[k]) * super.E;
                                this.a2 = true;
                            }
                            else if (n5.try[k].toLowerCase().compareTo("minpan") == 0) {
                                this.aK = new Float(n5.new[k]) * super.E;
                                this.a2 = true;
                            }
                            else if (n5.try[k].toLowerCase().compareTo("maxtilt") == 0) {
                                this.bp = new Float(n5.new[k]) * super.E;
                                this.a8 = true;
                            }
                            else if (n5.try[k].toLowerCase().compareTo("mintilt") == 0) {
                                this.bC = new Float(n5.new[k]) * super.E;
                                this.a8 = true;
                            }
                            else if (n5.try[k].toLowerCase().compareTo("maxfov") == 0) {
                                this.av = new Float(n5.new[k]) * super.E;
                            }
                            else if (n5.try[k].toLowerCase().compareTo("minfov") == 0) {
                                this.aL = new Float(n5.new[k]) * super.E;
                            }
                            else if (n5.try[k].toLowerCase().compareTo("lockzenithnadir") == 0 && n5.new[k].toLowerCase().compareTo("true") == 0) {
                                this.bg = true;
                            }
                        }
                        if (this.aL > this.av) {
                            final float al = this.aL;
                            this.aL = this.av;
                            this.av = al;
                        }
                        if (this.aL < 0.01745) {
                            this.aL = 0.01745f;
                        }
                        if (this.av > 2.5) {
                            this.av = 2.5f;
                        }
                    }
                    else if (n5.a.toLowerCase().compareTo("autopath") == 0) {
                        this.aB = true;
                        for (int l = 0; l < n5.do; ++l) {
                            if (n5.try[l].toLowerCase().compareTo("play") == 0) {
                                if (n5.new[l].compareTo("false") == 0) {
                                    this.aB = false;
                                }
                            }
                            else if (n5.try[l].toLowerCase().compareTo("first") == 0) {
                                this.aN = n5.new[l];
                            }
                        }
                        for (n n6 = n5.if; n6 != null; n6 = n6.for) {
                            this.for(n6);
                        }
                    }
                }
                break;
            }
        }
        this.do(n);
    }
    
    public void do(final n n) {
        this.at = super.int.if();
        for (int i = 0; i < n.do; ++i) {
            if (n.try[i].toLowerCase().compareTo("layer") == 0) {
                super.new = new Integer(n.new[i]);
            }
            else if (n.try[i].toLowerCase().compareTo("pan") == 0) {
                super.I = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("tilt") == 0) {
                super.B = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("roll") == 0) {
                super.D = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("visible") == 0) {
                if (n.new[i].compareTo("false") == 0) {
                    super.if = false;
                }
            }
            else if (n.try[i].toLowerCase().compareTo("maxpan") == 0) {
                this.au = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("minpan") == 0) {
                this.aK = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("maxtilt") == 0) {
                this.bp = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("mintilt") == 0) {
                this.bC = new Float(n.new[i]) * super.E;
            }
        }
        for (n n2 = n.if; n2 != null; n2 = n2.for) {
            if (n2.a.toLowerCase().compareTo("image") == 0) {
                (this.bm = new j(super.int, super.byte, super.F.try, super.try)).a(n2);
                super.F.a(this.bm);
            }
            else if (n2.a.toLowerCase().compareTo("autopath") == 0) {
                for (int j = 0; j < n2.do; ++j) {
                    if (n2.try[j].toLowerCase().compareTo("play") == 0) {
                        if (n2.new[j].compareTo("false") == 0) {
                            this.aB = false;
                        }
                    }
                    else if (n2.try[j].toLowerCase().compareTo("first") == 0) {
                        this.aN = n2.new[j];
                    }
                }
                for (n n3 = n2.if; n3 != null; n3 = n3.for) {
                    this.for(n3);
                }
            }
        }
        for (n n4 = n.if; n4 != null; n4 = n4.for) {
            if (n4.a.toLowerCase().compareTo("image") != 0 && n4.a.toLowerCase().compareTo("autopath") != 0) {
                this.a(n4, super.try);
            }
        }
    }
    
    private void a(final n n, final boolean b) {
        try {
            if (n.a.toLowerCase().compareTo("hotspotpoint") == 0) {
                this.long();
                (this.aH[this.aP] = new ad()).a(super.int, super.E, super.byte, super.G, super.F);
                super.F.a(this.aH[this.aP]);
                this.aH[this.aP].try = b;
                this.aH[this.aP].if(n);
                ++this.aP;
            }
            else if (n.a.toLowerCase().compareTo("hotspotrectangle") == 0) {
                this.long();
                (this.aH[this.aP] = new x()).a(super.int, super.E, super.byte, super.G, super.F);
                super.F.a(this.aH[this.aP]);
                this.aH[this.aP].try = b;
                this.aH[this.aP].if(n);
                ++this.aP;
            }
            else if (n.a.toLowerCase().compareTo("hotspotpolygonal") == 0) {
                this.long();
                (this.aH[this.aP] = new z()).a(super.int, super.E, super.byte, super.G, super.F);
                super.F.a(this.aH[this.aP]);
                this.aH[this.aP].try = b;
                this.aH[this.aP].if(n);
                ++this.aP;
            }
            else if (n.a.toLowerCase().compareTo("light") == 0) {
                this.else();
                (this.ax[this.bD] = new g()).a(super.int, super.E, super.byte, super.G, super.F);
                super.F.a(this.ax[this.bD]);
                this.ax[this.bD].try = b;
                this.ax[this.bD].if(n);
                ++this.bD;
            }
        }
        catch (Exception ex) {}
    }
    
    protected void for(final n n) {
        if (n.a.toLowerCase().compareTo("moveto") == 0 || n.a.toLowerCase().compareTo("move") == 0 || n.a.toLowerCase().compareTo("sleep") == 0 || n.a.toLowerCase().compareTo("action") == 0) {
            this.b();
            this.ay[this.bl] = new u();
            if (n.a.toLowerCase().compareTo("moveto") == 0) {
                this.ay[this.bl].if = 0;
            }
            else if (n.a.toLowerCase().compareTo("move") == 0) {
                this.ay[this.bl].if = 1;
            }
            else if (n.a.toLowerCase().compareTo("sleep") == 0) {
                this.ay[this.bl].if = 2;
            }
            else if (n.a.toLowerCase().compareTo("action") == 0 && n.case != null) {
                this.ay[this.bl].if = 3;
                this.ay[this.bl].void = n.case.do;
            }
            for (int i = 0; i < n.do; ++i) {
                if (n.try[i].toLowerCase().compareTo("pan") == 0) {
                    this.ay[this.bl].byte = new Float(n.new[i]) * super.E;
                    this.ay[this.bl].c = true;
                }
                else if (n.try[i].toLowerCase().compareTo("tilt") == 0) {
                    this.ay[this.bl].char = new Float(n.new[i]) * super.E;
                    this.ay[this.bl].goto = true;
                }
                else if (n.try[i].toLowerCase().compareTo("fov") == 0) {
                    this.ay[this.bl].long = new Float(n.new[i]) * super.E;
                    this.ay[this.bl].f = true;
                }
                else if (n.try[i].toLowerCase().compareTo("time") == 0) {
                    this.ay[this.bl].int = (long)(new Float(n.new[i]) * 1000.0f);
                    this.ay[this.bl].d = 0;
                }
                else if (n.try[i].toLowerCase().compareTo("speed") == 0) {
                    this.ay[this.bl].b = new Float(n.new[i]) * super.E;
                    this.ay[this.bl].d = 1;
                }
                else if (n.try[i].toLowerCase().compareTo("amid") == 0 || n.try[i].toLowerCase().compareTo("apid") == 0) {
                    this.ay[this.bl].try = n.new[i];
                }
                else if (n.try[i].toLowerCase().compareTo("next") == 0) {
                    this.ay[this.bl].case = n.new[i];
                }
            }
            ++this.bl;
        }
    }
    
    void a(final al[][] array, final al[][] array2, final int n) {
        if (array != null && array2 != null) {
            super.int.a(super.I, super.B, super.D, this.at);
            for (int i = 0; i < this.bx; ++i) {
                for (int j = 0; j < this.aF * this.aD; ++j) {
                    super.int.a(this.at, array[i][j], array2[i][j]);
                }
            }
        }
        this.case();
    }
    
    void case() {
        for (int i = 0; i < this.bD; ++i) {
            this.ax[i].a(this.at);
        }
        for (int j = 0; j < this.aP; ++j) {
            this.aH[j].a(this.at);
        }
    }
    
    boolean if(final boolean b) {
        if (this.a4 == 4) {
            return true;
        }
        super.for = true;
        for (int i = 0; i < this.aP; ++i) {
            if (!this.aH[i].for) {
                super.for = false;
            }
        }
        for (int j = 0; j < this.bD; ++j) {
            if (!this.ax[j].for) {
                super.for = false;
            }
        }
        return (b && this.bm != null && this.bm.b) || ((this.bm == null || this.bm.for) && super.for);
    }
    
    void do(final long n) {
        if (this.a4 == 4) {
            super.for = true;
            return;
        }
        for (int i = 0; i < this.aP; ++i) {
            this.aH[i].do(n);
        }
        for (int j = 0; j < this.bD; ++j) {
            this.ax[j].do(n);
        }
        if (this.bm != null) {
            this.bm.if(n);
        }
        super.try = true;
    }
    
    public boolean a(final long br) {
        if (this.a4 != 4) {
            super.case = false;
            boolean b = false;
            if (this.bm != null) {
                b |= this.bm.a(br);
                super.for = this.bm.for;
            }
            else {
                super.for = true;
            }
            boolean b2;
            if (super.for && super.try) {
                if ((this.bm != null && this.bm.n) || super.char) {
                    if (this.bm != null) {
                        this.bm.n = false;
                    }
                    this.void();
                    if (super.char && (super.I != 0.0f || super.B != 0.0f || super.D != 0.0f)) {
                        this.a(this.bo, this.bo, this.aF * this.aD);
                    }
                    super.char = false;
                    b = true;
                }
                this.aY = super.I;
                this.bv = super.B;
                this.bf = super.D;
                b2 = (b | this.int(br));
            }
            else {
                b2 = (b | this.int(br));
            }
            if (this.bm != null) {
                super.case &= this.bm.case;
            }
            final boolean do1 = super.do;
            super.do = false;
            return (b2 & super.if) | do1;
        }
        boolean do2 = super.do;
        super.do = false;
        while (super.I > 6.283185307179586) {
            super.I -= 6.283185307179586;
        }
        while (super.I < -6.283185307179586) {
            super.I += 6.283185307179586;
        }
        while (super.B > 3.141592653589793) {
            super.B -= 6.283185307179586;
        }
        while (super.B < -3.141592653589793) {
            super.B += 6.283185307179586;
        }
        if (!super.for) {
            return false;
        }
        if (this.a6) {
            if (this.az) {
                this.aB = false;
                if (this.aC) {
                    final float c = super.C;
                    final float ar = (this.aE - this.bq) * this.bd * (br - this.br) * 2.0E-4f * super.F.b.int;
                    this.aR = ar;
                    super.C = c + ar;
                    do2 = true;
                }
                else {
                    final float i = super.I;
                    final float aq = (this.aG - this.bs) * (br - this.br) * this.bd * super.C * super.F.b.int * 4.0E-4f;
                    this.aQ = aq;
                    super.I = i - aq;
                    final float b3 = super.B;
                    final float ax = (this.aE - this.bq) * (br - this.br) * this.bd * super.C * super.F.b.int * 4.0E-4f;
                    this.aX = ax;
                    super.B = b3 - ax;
                    do2 = true;
                }
            }
            else if (this.bE) {
                float l = l.l * (40.0f / (br - this.br));
                if (l > 1.0f) {
                    l = l.l;
                }
                if (this.aQ != 0.0f) {
                    this.aQ *= l;
                }
                if (this.aX != 0.0f) {
                    this.aX *= l;
                }
                if (this.aR != 0.0f) {
                    this.aR *= l;
                }
                if (((this.aQ > 0.0f) ? this.aQ : (-this.aQ)) < 0.001 && ((this.aX > 0.0f) ? this.aX : (-this.aX)) < 0.001 && ((this.aR > 0.0f) ? this.aR : (-this.aR)) < 0.001) {
                    this.bE = false;
                    this.aQ = 0.0f;
                    this.aX = 0.0f;
                    this.aR = 0.0f;
                }
                super.I -= this.aQ;
                super.B -= this.aX;
                super.C += this.aR;
                do2 = true;
            }
            else {
                do2 |= this.for(br);
            }
        }
        else {
            this.a6 = true;
            if (!super.F.for) {
                super.I = this.aU;
                super.B = this.bj;
                if (this.aT > 0.0f) {
                    super.C = this.aT;
                }
            }
        }
        if (super.C < this.aL) {
            super.C = this.aL;
        }
        else if (super.C > this.av) {
            super.C = this.av;
        }
        if (this.a8 || this.a2) {
            final float n = (float)Math.sqrt(this.aW * this.aW + this.bn * this.bn);
            float n2 = n / (float)Math.tan(super.C * 0.5f);
            if (this.a8) {
                float n3 = (float)(2.0 * Math.atan2(this.aW, n2));
                if (this.bp - this.bC < n3) {
                    n2 = (float)(this.aW / Math.tan((this.bp - this.bC) * 0.5));
                    super.C = (float)(2.0 * Math.atan2(n, n2));
                    n3 = this.bp - this.bC;
                }
                if (super.B + n3 * 0.5 > this.bp) {
                    super.B = (float)(-n3 * 0.5 + this.bp);
                }
                if (super.B - n3 * 0.5 < this.bC) {
                    super.B = (float)(n3 * 0.5 + this.bC);
                }
            }
            if (this.a2) {
                float n4 = (float)(2.0 * Math.atan2(this.bn, n2));
                if (this.au - this.aK < n4) {
                    super.C = (float)(2.0 * Math.atan2(n, (float)(this.bn / Math.tan((this.au - this.aK) * 0.5))));
                    n4 = this.au - this.aK;
                }
                if (super.I + n4 * 0.5 > this.au) {
                    super.I = (float)(-n4 * 0.5 + this.au);
                }
                if (super.I - n4 * 0.5 < this.aK) {
                    super.I = (float)(n4 * 0.5 + this.aK);
                }
            }
        }
        if (super.int.p | this.bg) {
            while (super.B > 3.141592653589793) {
                super.B -= 6.283185307179586;
            }
            while (super.B < -3.141592653589793) {
                super.B += 6.283185307179586;
            }
            if (super.B > 1.5707963267948966) {
                super.B = 1.5707964f;
            }
            if (super.B < -1.5707963267948966) {
                super.B = -1.5707964f;
            }
        }
        this.br = br;
        return do2 & super.if;
    }
    
    boolean for(final long n) {
        boolean b = false;
        if (this.aB) {
            if ((this.bb == -1 || (this.ay[this.bb].for != 0L && this.ay[this.bb].for < n)) && (this.bb == -1 || this.ay[this.bb].if != 0 || this.ay[this.bb].for == 1L)) {
                this.a(n, false);
                if (this.bb == -1) {
                    return false;
                }
            }
            if (this.ay[this.bb].if == 1) {
                final float n2 = 1000.0f / (n - this.ay[this.bb].g);
                this.ay[this.bb].g = n;
                if (this.ay[this.bb].c) {
                    super.I += this.ay[this.bb].byte / n2;
                }
                if (this.ay[this.bb].goto) {
                    super.B += this.ay[this.bb].char / n2;
                }
                if (this.ay[this.bb].f) {
                    super.C += this.ay[this.bb].long / n2;
                }
                b = true;
            }
            else if (this.ay[this.bb].if == 0) {
                float n3;
                if (this.ay[this.bb].int == 0L || this.ay[this.bb].for < n || n - this.ay[this.bb].g >= this.ay[this.bb].for - n) {
                    n3 = 0.0f;
                    this.ay[this.bb].for = 1L;
                }
                else {
                    n3 = (this.ay[this.bb].for - n) / (n - this.ay[this.bb].g);
                }
                this.ay[this.bb].g = n;
                if (this.ay[this.bb].c) {
                    super.I = super.int.a(super.I, this.ay[this.bb].byte);
                    if (n3 == 0.0f) {
                        super.I = this.ay[this.bb].byte;
                    }
                    else {
                        super.I += (this.ay[this.bb].byte - super.I) / n3;
                    }
                }
                if (this.ay[this.bb].goto) {
                    super.B = super.int.a(super.B, this.ay[this.bb].char);
                    if (n3 == 0.0f) {
                        super.B = this.ay[this.bb].char;
                    }
                    else {
                        super.B += (this.ay[this.bb].char - super.B) / n3;
                    }
                }
                if (this.ay[this.bb].f) {
                    super.C = super.int.a(super.C, this.ay[this.bb].long);
                    if (n3 == 0.0f) {
                        super.C = this.ay[this.bb].long;
                    }
                    else {
                        super.C += (this.ay[this.bb].long - super.C) / n3;
                    }
                }
                b = true;
            }
            else if (this.ay[this.bb].if == 3) {
                super.int.try.if(this.ay[this.bb].void);
                this.ay[this.bb].for = 2L;
            }
        }
        return b;
    }
    
    public void a(final long g, final boolean b) {
        if (this.bz != -2 && !this.aA) {
            this.bb = this.bz;
            this.bz = -2;
        }
        if (!b || this.bb == -1) {
            if (this.bb == -1) {
                if (this.aN.length() == 0) {
                    this.bb = 0;
                }
                else {
                    for (int i = 0; i < this.bl; ++i) {
                        if (this.aN.compareTo(this.ay[i].try) == 0) {
                            this.bb = i;
                        }
                    }
                }
                if (this.bb >= this.bl || this.bb == -1) {
                    this.aB = false;
                    this.bb = -1;
                    return;
                }
            }
            else {
                if (this.ay[this.bb].case.length() == 0) {
                    this.bb = -1;
                    this.aB = false;
                    return;
                }
                boolean b2 = false;
                for (int j = 0; j < this.bl; ++j) {
                    if (this.ay[this.bb].case.compareTo(this.ay[j].try) == 0) {
                        this.bb = j;
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.bb = -1;
                    this.aB = false;
                    return;
                }
            }
        }
        this.ay[this.bb].g = g;
        if (this.ay[this.bb].if == 1) {
            if (this.ay[this.bb].int == 0L) {
                this.ay[this.bb].for = 0L;
                this.ay[this.bb].g = g - 40L;
            }
            else {
                this.ay[this.bb].for = g + this.ay[this.bb].int + 40L;
            }
        }
        else if (this.ay[this.bb].if == 0) {
            if (this.ay[this.bb].d == 1) {
                float n = 0.0f;
                if (this.ay[this.bb].c) {
                    n = Math.abs(this.ay[this.bb].byte - super.int.a(super.I, this.ay[this.bb].byte));
                }
                if (this.ay[this.bb].goto) {
                    n = Math.max(n, Math.abs(this.ay[this.bb].char - super.int.a(super.B, this.ay[this.bb].char)));
                }
                if (this.ay[this.bb].f) {
                    n = Math.max(n, Math.abs(this.ay[this.bb].long - super.int.a(super.C, this.ay[this.bb].long)));
                }
                this.ay[this.bb].int = (long)(n * 1000.0f / this.ay[this.bb].b);
            }
            if (this.ay[this.bb].int == 0L) {
                this.ay[this.bb].for = 0L;
            }
            else {
                this.ay[this.bb].for = g + this.ay[this.bb].int + 40L;
            }
        }
        else {
            if (this.ay[this.bb].if == 3) {
                this.ay[this.bb].for = 1L;
                return;
            }
            this.ay[this.bb].for = g + this.ay[this.bb].int;
        }
    }
    
    boolean int(final long n) {
        boolean b = false;
        boolean case1 = true;
        for (int i = 0; i < this.bD; ++i) {
            b |= this.ax[i].a(n);
            case1 &= this.ax[i].for;
        }
        for (int j = 0; j < this.aP; ++j) {
            b |= this.aH[j].a(n);
            case1 &= this.aH[j].for;
        }
        super.case = case1;
        return b;
    }
    
    void byte() {
        for (int i = 0; i < this.bD; ++i) {
            this.ax[i].a(false);
        }
        for (int j = 0; j < this.aP; ++j) {
            this.aH[j].a(false);
        }
    }
    
    public void a(final boolean b) {
        if (this.a4 == 4) {
            this.bc = (float)Math.cos(super.I);
            this.aI = (float)Math.sin(super.I);
            this.bi = (float)Math.cos(super.B);
            this.bB = (float)Math.sin(super.B);
            this.aZ = (float)(Math.sqrt(this.a1 * this.a1 + this.aS * this.aS >> 2) / Math.tan(super.C * 0.5));
            return;
        }
        if (super.try && super.for && super.if) {
            if (this.bm != null && this.bm.if && !super.char && !this.bt) {
                for (int i = 0; i < this.bx; ++i) {
                    super.int.k.a(this.bo[i], this.aF, this.aD, this.bm, super.G, b);
                }
            }
            this.byte();
        }
    }
    
    private void else() {
        if (this.ax == null || this.ax.length == this.bD) {
            final a[] ax = new a[this.bD + 10];
            for (int i = 0; i < this.bD; ++i) {
                ax[i] = this.ax[i];
            }
            this.ax = ax;
        }
    }
    
    private void long() {
        if (this.aH == null || this.aH.length == this.aP) {
            final a[] ah = new a[this.aP + 10];
            for (int i = 0; i < this.aP; ++i) {
                ah[i] = this.aH[i];
            }
            this.aH = ah;
        }
    }
    
    private void b() {
        if (this.ay == null || this.ay.length == this.bl) {
            final u[] ay = new u[this.bl + 10];
            for (int i = 0; i < this.bl; ++i) {
                ay[i] = this.ay[i];
            }
            this.ay = ay;
        }
    }
    
    protected final al a(final int n, final int n2) {
        final double n3 = super.G.aZ * super.G.bB;
        final double n4 = super.G.aZ * super.G.bc * super.G.bi;
        final double n5 = super.G.aZ * super.G.aI * super.G.bi;
        final int n6 = n - (super.G.a1 >> 1);
        final int n7 = n2 - (super.G.aS >> 1);
        this.bw.try = (float)(n4 + n7 * super.G.bB * super.G.bc + n6 * super.G.aI);
        this.bw.if = (float)(n5 + n7 * super.G.bB * super.G.aI - n6 * super.G.bc);
        this.bw.byte = (float)(n3 - n7 * super.G.bi);
        final float[][] if1 = super.int.if();
        super.int.a(super.I, super.B, super.D, if1);
        super.int.a(if1, this.bw, this.bh);
        return this.bh;
    }
    
    void a(final ah ah) {
        if (this.a4 == 4) {
            switch (ah.if) {
                case 0: {
                    if (ah.h || ah.goto < 0 || ah.goto >= this.a1 || ah.else < 0 || ah.else >= this.aS) {
                        break;
                    }
                    if (ah.f == 5) {
                        this.aC = true;
                    }
                    else {
                        this.aC = false;
                    }
                    this.az = true;
                    this.bs = ah.goto;
                    this.bq = ah.else;
                    this.aG = ah.goto;
                    this.aE = ah.else;
                    if (this.aC) {
                        ah.for = 2;
                        break;
                    }
                    ah.for = 1;
                    break;
                }
                case 4: {
                    if (!this.az) {
                        break;
                    }
                    this.aG = ah.goto;
                    this.aE = ah.else;
                    if (this.aC) {
                        ah.for = 2;
                        break;
                    }
                    ah.for = 1;
                    break;
                }
                case 1: {
                    this.az = false;
                    if (l.l > 0.0f) {
                        this.bE = true;
                    }
                    this.by = 0.0f;
                    this.aw = 0.0f;
                    this.bF = 0.0f;
                    break;
                }
            }
            return;
        }
        final boolean h = ah.h;
        if (!super.if) {
            ah.h = true;
        }
        for (int i = 0; i < this.aP; ++i) {
            this.aH[i].a(ah);
        }
        if (!super.if) {
            ah.h = h;
        }
    }
    
    public void try() {
        this.bz = this.bb;
        this.aA = true;
        this.a("immerin");
        this.aA = false;
    }
    
    public void goto() {
        this.bz = this.bb;
        this.aA = true;
        this.a("immerout");
        this.aA = false;
    }
    
    private void a(final String s) {
        for (int i = 0; i < this.bl; ++i) {
            if (s.compareTo(this.ay[i].try) == 0) {
                this.bb = i;
            }
        }
        this.a(System.currentTimeMillis(), true);
        this.aB = true;
        this.bE = false;
    }
    
    public void char() {
        this.aB = false;
        if (this.bz != -2) {
            this.bb = this.bz;
            this.bz = -2;
        }
    }
}
