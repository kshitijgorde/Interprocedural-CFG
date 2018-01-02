// 
// Decompiled by Procyon v0.5.30
// 

package JAVACharter.a;

import JAVACharter.util.a;
import java.net.URLEncoder;
import java.io.DataInputStream;
import JAVACharter.b.e;
import JAVACharter.b.h;
import java.net.URL;
import java.util.Hashtable;
import java.util.Date;

public class b extends d
{
    Date[] aT;
    long[] aH;
    long[] ax;
    long[] aF;
    long[] aM;
    float[] aA;
    float[] aK;
    Date[] aC;
    long[] aS;
    long[] aI;
    long[] aE;
    long[] aw;
    float[] aP;
    float[] aO;
    long az;
    long aJ;
    long aN;
    long aG;
    float aD;
    float aL;
    private Hashtable aR;
    private Hashtable ay;
    private String aQ;
    private String av;
    private String aB;
    
    public b(final f f, final c c, final URL url) {
        super(f, c, url);
        this.az = 0L;
        this.aJ = 0L;
        this.aN = 0L;
        this.aG = 0L;
        this.aD = 0.0f;
        this.aL = 0.0f;
        this.aR = new Hashtable();
        this.ay = new Hashtable();
        this.aQ = "";
        this.av = "";
        this.aB = "";
    }
    
    public void a(final JAVACharter.b.b b, final int n, final int n2) {
        this.aT = new Date[n2];
        this.aH = new long[n2];
        this.ax = new long[n2];
        this.aF = new long[n2];
        this.aM = new long[n2];
        this.aA = new float[n2];
        this.aK = new float[n2];
        this.aC = new Date[n2];
        this.aS = new long[n2];
        this.aI = new long[n2];
        this.aE = new long[n2];
        this.aw = new long[n2];
        this.aP = new float[n2];
        this.aO = new float[n2];
        this.az = ((JAVACharter.b.c)b.a("adv")).new(n);
        this.aJ = ((JAVACharter.b.c)b.a("dec")).new(n);
        this.aN = ((JAVACharter.b.c)b.a("unch")).new(n);
        this.aG = ((JAVACharter.b.c)b.a("countactive")).new(n);
        this.aD = ((h)b.a("voladv")).for(n);
        this.aL = ((h)b.a("voldec")).for(n);
    }
    
    public void if(final JAVACharter.b.b b, final int n) {
        final long new1 = ((JAVACharter.b.c)b.a("adv")).new(n);
        if (new1 != 9.223372E18f || new1 != Long.MAX_VALUE) {
            if (this.az == 9.223372E18f || this.az == Long.MAX_VALUE) {
                this.az = new1;
            }
            else {
                this.az += new1;
            }
        }
        final long new2 = ((JAVACharter.b.c)b.a("dec")).new(n);
        if (new2 != 9.223372E18f || new2 != Long.MAX_VALUE) {
            if (this.aJ == 9.223372E18f || this.aJ == Long.MAX_VALUE) {
                this.aJ = new2;
            }
            else {
                this.aJ += new2;
            }
        }
        final long new3 = ((JAVACharter.b.c)b.a("unch")).new(n);
        if (new3 != 9.223372E18f || new3 != Long.MAX_VALUE) {
            if (this.aN == 9.223372E18f || this.aN == Long.MAX_VALUE) {
                this.aN = new3;
            }
            else {
                this.aN += new3;
            }
        }
        final long new4 = ((JAVACharter.b.c)b.a("countactive")).new(n);
        if (new4 != 9.223372E18f || new4 != Long.MAX_VALUE) {
            if (this.aG == 9.223372E18f || this.aG == Long.MAX_VALUE) {
                this.aG = new4;
            }
            else {
                this.aG += new4;
            }
        }
        final float for1 = ((h)b.a("voladv")).for(n);
        if (for1 != 9.223372E18f || for1 != 9.223372E18f) {
            if (this.aD == 9.223372E18f || this.aD == 9.223372E18f) {
                this.aD = for1;
            }
            else {
                this.aD += for1;
            }
        }
        final float for2 = ((h)b.a("voldec")).for(n);
        if (for2 != 9.223372E18f || for2 != 9.223372E18f) {
            if (this.aL == 9.223372E18f || this.aL == 9.223372E18f) {
                this.aL = for2;
            }
            else {
                this.aL += for2;
            }
        }
    }
    
    public void do(final JAVACharter.b.b b, final int n) {
    }
    
    public void a(final JAVACharter.b.b b, final int n) {
        b.if("date", n, super.void);
        b.if("adv", n, new Long(this.az));
        b.if("dec", n, new Long(this.aJ));
        b.if("unch", n, new Long(this.aN));
        b.if("countactive", n, new Long(this.aG));
        b.if("voladv", n, new Float(this.aD));
        b.if("voldec", n, new Float(this.aL));
    }
    
    public void a(final int n, final boolean b) {
        if (b) {
            this.aT[n] = super.void;
            this.aH[n] = this.az;
            this.ax[n] = this.aJ;
            this.aF[n] = this.aN;
            this.aM[n] = this.aG;
            this.aA[n] = this.aD;
            this.aK[n] = this.aL;
        }
        else {
            this.aC[n] = super.void;
            this.aS[n] = this.az;
            this.aI[n] = this.aJ;
            this.aE[n] = this.aN;
            this.aw[n] = this.aG;
            this.aP[n] = this.aD;
            this.aO[n] = this.aL;
        }
    }
    
    public void a(final JAVACharter.b.f f, final JAVACharter.b.b b, final int n, final int n2) {
        super.void = this.a(f.do(n), n2);
        this.az = ((JAVACharter.b.c)b.a("adv")).new(n);
        this.aJ = ((JAVACharter.b.c)b.a("dec")).new(n);
        this.aN = ((JAVACharter.b.c)b.a("unch")).new(n);
        this.aG = ((JAVACharter.b.c)b.a("countactive")).new(n);
        this.aD = ((h)b.a("voladv")).for(n);
        this.aL = ((h)b.a("voldec")).for(n);
    }
    
    public void a(final JAVACharter.b.b b, final boolean b2, final int n) {
        if (b2) {
            b.if("date", this.aT, n);
            b.if("adv", this.aH, n);
            b.if("dec", this.ax, n);
            b.if("unch", this.aF, n);
            b.if("countactive", this.aM, n);
            b.if("voladv", this.aA, n);
            b.if("voldec", this.aK, n);
        }
        else {
            b.a("date", this.aC, n);
            b.a("adv", this.aS, n);
            b.a("dec", this.aI, n);
            b.a("unch", this.aE, n);
            b.a("countactive", this.aw, n);
            b.a("voladv", this.aP, n);
            b.a("voldec", this.aO, n);
        }
    }
    
    public void a(final JAVACharter.b.d[] array, final String s) {
        for (int i = 0; i < array.length; ++i) {
            final JAVACharter.b.d d = array[i];
            if (!d.do(s)) {
                final JAVACharter.b.b a = d.a(s);
                a.a("date", new JAVACharter.b.f());
                a.a("adv", new JAVACharter.b.c());
                a.a("dec", new JAVACharter.b.c());
                a.a("unch", new JAVACharter.b.c());
                a.a("countactive", new JAVACharter.b.c());
                a.a("voladv", new h());
                a.a("voldec", new h());
                a.if("date");
            }
        }
    }
    
    public Hashtable a(final String s, final String s2, final int n, final int n2, final Date date, final Date date2) {
        return this.do(this.do(s, s2, n, n2, date, date2));
    }
    
    public String do(final String s) {
        if (this.aR.containsKey(s)) {
            return this.aR.get(s);
        }
        final String if1 = this.if(this.do("symb", s, 1, 0, new Date(5000000L), new Date(5000000L)));
        if (if1 != null && if1 != "") {
            this.aR.put(s, if1);
        }
        return if1;
    }
    
    public String for(final String s) {
        if (this.ay.containsKey(s)) {
            return this.ay.get(s);
        }
        final String if1 = this.if(this.do("sid", s, 1, 0, new Date(5000000L), new Date(5000000L)));
        if (if1 != null && if1 != "") {
            this.ay.put(s, if1);
        }
        return if1;
    }
    
    public DataInputStream do(final String s, final String s2, final int n, final int n2, final Date date, final Date date2) {
        final String string = "" + super.try.toString() + "stuff.javadatafeed?";
        if (this.aQ == "") {
            this.aQ = super.if.void();
        }
        if (this.av == "") {
            this.av = super.if.long();
        }
        if (this.aB == "") {
            this.aB = super.if.k();
        }
        String s3;
        if (n == 1) {
            s3 = string + "ver=2freq=" + n + "&" + s + "=" + s2 + "&countries=" + URLEncoder.encode(this.aQ) + "&partnerid=" + this.av + "&clientid=" + this.aB + "&StartExtra=" + n2 + "&LeftFill=1&RightFill=1&MockTick=1&StartDate=" + URLEncoder.encode(JAVACharter.util.b.new(date)) + "&enddate=" + URLEncoder.encode(JAVACharter.util.b.new(JAVACharter.util.b.else(date2))) + "&datatype=" + 1;
        }
        else {
            s3 = string + "ver=2freq=" + n + "&" + s + "=" + s2 + "&countries=" + URLEncoder.encode(this.aQ) + "&partnerid=" + this.av + "&clientid=" + this.aB + "&StartExtra=" + n2 + "&LeftFill=1&RightFill=1&MockTick=1&StartDate=" + URLEncoder.encode(JAVACharter.util.b.new(date)) + "&enddate=" + URLEncoder.encode(JAVACharter.util.b.new(JAVACharter.util.b.for(date2, 1))) + "&datatype=" + 1;
        }
        return new DataInputStream(JAVACharter.util.a.a(s3));
    }
    
    public String if(final DataInputStream dataInputStream) {
        String a = "";
        if (JAVACharter.util.a.a(dataInputStream) >= 0) {
            JAVACharter.util.a.a(dataInputStream);
            JAVACharter.util.a.a(dataInputStream);
            a = JAVACharter.util.a.a(dataInputStream, JAVACharter.util.a.a(dataInputStream));
        }
        try {
            dataInputStream.close();
        }
        catch (Exception ex) {
            System.out.println("Error closing stream.");
        }
        return a;
    }
    
    public Hashtable do(final DataInputStream dataInputStream) {
        final Hashtable<String, Date[]> hashtable = new Hashtable<String, Date[]>();
        final int a = JAVACharter.util.a.a(dataInputStream);
        if (a >= 0) {
            JAVACharter.util.a.a(dataInputStream);
            JAVACharter.util.a.a(dataInputStream);
            JAVACharter.util.a.a(dataInputStream, JAVACharter.util.a.a(dataInputStream));
            final Date[] array = new Date[a];
            final long[] array2 = new long[a];
            final long[] array3 = new long[a];
            final long[] array4 = new long[a];
            final long[] array5 = new long[a];
            final float[] array6 = new float[a];
            final float[] array7 = new float[a];
            for (int i = 0; i < a; ++i) {
                array[i] = JAVACharter.util.b.a(JAVACharter.util.a.a(dataInputStream));
            }
            for (int j = 0; j < a; ++j) {
                array2[j] = JAVACharter.util.a.a(dataInputStream);
            }
            for (int k = 0; k < a; ++k) {
                array3[k] = JAVACharter.util.a.a(dataInputStream);
            }
            for (int l = 0; l < a; ++l) {
                array4[l] = JAVACharter.util.a.a(dataInputStream);
            }
            for (int n = 0; n < a; ++n) {
                array5[n] = JAVACharter.util.a.a(dataInputStream);
            }
            for (int n2 = 0; n2 < a; ++n2) {
                array6[n2] = (float)JAVACharter.util.a.if(dataInputStream);
            }
            for (int n3 = 0; n3 < a; ++n3) {
                array7[n3] = (float)JAVACharter.util.a.if(dataInputStream);
            }
            try {
                dataInputStream.close();
            }
            catch (Exception ex) {
                System.out.println("Error closing stream.");
            }
            hashtable.put("date", array);
            hashtable.put("adv", (Date[])array2);
            hashtable.put("dec", (Date[])array3);
            hashtable.put("unch", (Date[])array4);
            hashtable.put("countactive", (Date[])array5);
            hashtable.put("voladv", (Date[])array6);
            hashtable.put("voldec", (Date[])array7);
        }
        else {
            hashtable.put("error", (Date[])new String[1]);
        }
        return hashtable;
    }
    
    public boolean a(JAVACharter.b.d[] a, final String s, final String s2, final int n) {
        super.long = false;
        int n2 = 0;
        a = super.int.a();
        for (int i = 0; i < a.length; ++i) {
            if (a[i].if() == n) {
                n2 = i;
            }
        }
        boolean a2 = false;
        JAVACharter.b.b for1 = null;
        JAVACharter.b.f f = null;
        JAVACharter.b.b b = null;
        JAVACharter.b.f f2 = null;
        if (n2 > 0) {
            this.a(a, s2);
            for1 = a[n2].for(s2);
            if (for1 != null) {
                f = (JAVACharter.b.f)for1.for();
                a2 = this.a(a[n2].if(), f, for1);
            }
            else {
                a2 = false;
            }
        }
        if (!a2 || n2 == 0) {
            b = a[0].for(s2);
            if (b != null) {
                f2 = (JAVACharter.b.f)b.for();
            }
            else {
                this.a(a, s2);
                b = a[n2].for(s2);
                f2 = (JAVACharter.b.f)b.for();
            }
            this.a("sid", s, b, f2, n, super.if.b());
        }
        if (!a2 && n2 > 0 && !super.long) {
            this.a(a[n2].if(), a[0], a[n2], f2, b, f, for1);
        }
        return super.long;
    }
}
