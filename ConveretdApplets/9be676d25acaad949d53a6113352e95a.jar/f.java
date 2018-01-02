import java.util.Hashtable;
import java.util.StringTokenizer;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class f extends c implements a
{
    private boolean k;
    public static boolean m;
    private int b;
    private String[] u;
    private String p;
    private String[] c;
    private String d;
    float[] e;
    float[] r;
    e s;
    public Color i;
    public Color g;
    public Color h;
    public Color t;
    public Color n;
    public Color q;
    public String j;
    private String f;
    private String o;
    private int l;
    private int void;
    
    public f() {
        this.k = true;
        this.b = 5000;
        this.p = "MSFT,IBM,ORCL,LU,FON,GENXY,BSTE,DELL";
        this.i = new Color(255, 130, 130);
        this.g = new Color(130, 250, 130);
        this.h = new Color(255, 255, 200);
        this.t = new Color(255, 255, 255);
        this.n = new Color(255, 255, 255);
        this.q = new Color(155, 155, 155);
        this.j = null;
        this.o = null;
    }
    
    public void init() {
        super.init();
        System.out.println("=====================================================");
        System.out.println("LED StocksTicker 1.1 by Gigel Chiazna");
        System.out.println("chiazna@hotmail.com");
        System.out.println("http://chiazna.has.it");
        System.out.println("=====================================================");
        final String parameter = this.getParameter("feed");
        if (parameter == null) {
            System.out.println("Error: 'feed' parameter not set!");
        }
        this.o = "EFNP!WFSTJPO!.!NBJMUP!DIJB[OBAIPUNBJM/DPN!PS!WJTJU!XXX/UJDLFS/EPFT/JU!GPS!GVMM!WFSTJPO";
        if (this.getParameter("message") != null) {
            this.j = this.getParameter("message");
        }
        else {
            this.j = "";
        }
        if (this.getParameter("messageLocation") != null) {
            this.f = this.getParameter("messageLocation");
        }
        else {
            this.f = "end";
        }
        try {
            this.l = Integer.parseInt(this.getParameter("spacesBeforeMessage"));
        }
        catch (Exception ex3) {
            this.l = 0;
        }
        if (this.getParameter("field") != null) {
            this.d = this.getParameter("field");
        }
        else {
            this.d = "LAST";
        }
        try {
            this.b = Integer.parseInt(this.getParameter("refreshDelay"));
        }
        catch (Exception ex4) {}
        try {
            this.void = Integer.parseInt(this.getParameter("spacesAfterMessage"));
        }
        catch (Exception ex5) {
            this.void = 0;
        }
        for (int i = 0; i < this.l; ++i) {
            this.j = " ".concat(String.valueOf(String.valueOf(this.j)));
        }
        for (int j = 0; j < this.void; ++j) {
            this.j = String.valueOf(String.valueOf(this.j)).concat(" ");
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("gridColor"), ",");
            this.a(new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken())));
        }
        catch (Exception ex) {
            System.out.println("invalid parameter 'gridColor':".concat(String.valueOf(String.valueOf(ex))));
            this.a(this.q);
        }
        try {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("downColor"), ",");
            this.int(new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken())));
        }
        catch (Exception ex2) {
            System.out.println("invalid parameter 'gridColor':".concat(String.valueOf(String.valueOf(ex2))));
            this.int(this.i);
        }
        try {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.getParameter("upColor"), ",");
            this.if(new Color(Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken())));
        }
        catch (Exception ex6) {
            this.if(this.g);
        }
        try {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(this.getParameter("messageColor"), ",");
            this.n = new Color(Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()));
        }
        catch (Exception ex7) {}
        try {
            final StringTokenizer stringTokenizer5 = new StringTokenizer(this.getParameter("symbolColor"), ",");
            this.for(new Color(Integer.parseInt(stringTokenizer5.nextToken()), Integer.parseInt(stringTokenizer5.nextToken()), Integer.parseInt(stringTokenizer5.nextToken())));
        }
        catch (Exception ex8) {
            this.for(this.t);
        }
        try {
            final StringTokenizer stringTokenizer6 = new StringTokenizer(this.getParameter("stayColor"), ",");
            this.do(new Color(Integer.parseInt(stringTokenizer6.nextToken()), Integer.parseInt(stringTokenizer6.nextToken()), Integer.parseInt(stringTokenizer6.nextToken())));
        }
        catch (Exception ex9) {
            this.do(this.h);
        }
        int int1;
        try {
            int1 = Integer.parseInt(this.getParameter("speed"));
        }
        catch (Exception ex10) {
            int1 = 20;
        }
        this.if(int1);
        StringTokenizer stringTokenizer7;
        try {
            stringTokenizer7 = new StringTokenizer(this.getParameter("symbols"), ",");
        }
        catch (Exception ex11) {
            System.out.println("ERROR: 'symbols' parameter not set! Using defaults.");
            stringTokenizer7 = new StringTokenizer(this.p, ",");
        }
        if (this.k) {
            this.u = new String[(stringTokenizer7.countTokens() > 5) ? 5 : stringTokenizer7.countTokens()];
        }
        else {
            this.u = new String[stringTokenizer7.countTokens()];
        }
        int n = 0;
        (this.s = new b()).a(this);
        this.s.a(parameter);
        this.s.a(this.b);
        while (stringTokenizer7.hasMoreTokens() && (!this.k || n <= 4)) {
            this.u[n++] = stringTokenizer7.nextToken();
            this.s.if(this.u[n - 1]);
        }
        this.e = new float[this.u.length];
        this.r = new float[this.u.length];
        this.if();
        new a().start();
    }
    
    public String[] do() {
        return this.u;
    }
    
    public void stop() {
    }
    
    private void if() {
    }
    
    public void a(final String s, final String s2) {
        if (f.m) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("setStock stock=").append(s).append(" value=").append(s2))));
        }
        for (int i = 0; i < this.u.length; ++i) {
            if (this.u[i].equals(s)) {
                this.r[i] = this.e[i];
                try {
                    this.e[i] = Float.valueOf(s2);
                }
                catch (Exception ex) {
                    this.e[i] = -1.0f;
                }
            }
        }
    }
    
    public void int(final Color i) {
        this.i = i;
    }
    
    public void do(final Color h) {
        this.h = h;
    }
    
    public void if(final Color g) {
        this.g = g;
    }
    
    public void for(final Color t) {
        this.t = t;
    }
    
    public void a(final Hashtable hashtable) {
        this.a(hashtable.get("SYMBOL"), hashtable.get(this.d));
    }
    
    public String a(final int n) {
        return null;
    }
    
    static {
        f.m = false;
    }
    
    class a extends Thread
    {
        public void run() {
            if (f.m) {
                System.out.println("Ticker.Update.run");
            }
            Color[] array;
            if (f.this.k) {
                array = new Color[2 + f.this.u.length * 2];
            }
            else {
                array = new Color[1 + f.this.u.length * 2];
            }
            final Object o = new Object();
            while (true) {
                synchronized (o) {
                    for (int i = 0; i < f.this.u.length; ++i) {
                        if (f.this.e[i] < f.this.r[i]) {
                            array[i * 2 + 1] = f.this.i;
                        }
                        else if (f.this.e[i] > f.this.r[i]) {
                            array[i * 2 + 1] = f.this.g;
                        }
                        else {
                            array[i * 2 + 1] = f.this.h;
                        }
                        array[i * 2] = f.this.t;
                    }
                    if (f.this.k) {
                        f.this.c = new String[2 + f.this.u.length * 2];
                    }
                    else {
                        f.this.c = new String[1 + f.this.u.length * 2];
                    }
                    for (int j = 0; j < f.this.u.length; ++j) {
                        f.this.c[j * 2] = new String(f.this.u[j]);
                        if (f.this.e[j] > 0) {
                            f.this.c[j * 2 + 1] = "".concat(String.valueOf(String.valueOf(f.this.e[j])));
                        }
                        else {
                            f.this.c[j * 2 + 1] = "N/A";
                            array[j * 2 + 1] = f.this.h;
                        }
                    }
                }
                try {
                    if (f.this.k) {
                        String concat = "";
                        for (int k = 0; k < f.this.o.length(); ++k) {
                            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf((char)(f.this.o.charAt(k) - '\u0001'))));
                        }
                        f.this.c[f.this.u.length * 2 + 1] = concat;
                        array[f.this.u.length * 2 + 1] = f.this.i;
                    }
                    if (f.this.f.equals("start")) {
                        for (int l = f.this.u.length * 2; l > 0; --l) {
                            f.this.c[l] = f.this.c[l - 1];
                            array[l] = array[l - 1];
                        }
                        f.this.c[0] = f.this.j;
                        array[0] = f.this.n;
                    }
                    else {
                        f.this.c[f.this.u.length * 2] = f.this.j;
                        array[f.this.u.length * 2] = f.this.n;
                    }
                }
                catch (Exception ex) {}
                f.this.a(f.this.c, array);
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex2) {}
            }
        }
    }
}
