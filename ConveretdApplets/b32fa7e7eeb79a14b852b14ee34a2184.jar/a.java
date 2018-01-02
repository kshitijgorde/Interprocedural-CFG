import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class a
{
    JME int;
    int null;
    int J;
    int[] else;
    int[] B;
    double[] y;
    double[] x;
    int[][] z;
    int[] v;
    String[] u;
    String[] C;
    int[] a;
    int[] F;
    int[] l;
    int[] j;
    int[] byte;
    int[] g;
    int[] A;
    int[] m;
    String[] do;
    int[][] goto;
    int[] K;
    int f;
    static int p;
    int r;
    int[] M;
    int[] char;
    int void;
    int s;
    int long;
    double new;
    double o;
    private boolean H;
    int w;
    boolean E;
    boolean t;
    static final int try = 1;
    static final int case = 2;
    static final int for = 3;
    static final int h = 5;
    static final int if = 9;
    static final int D = 1;
    static final int L = 2;
    static final int c = 3;
    static final int N = 4;
    private static final int i = 1;
    private static final int q = 2;
    private static final int b = 3;
    private static final int k = 4;
    private static final int n = 10;
    static final int e = 25;
    private static final int d = 50;
    private static final int I = 6;
    static boolean G;
    
    a(final JME int1) {
        this.null = 0;
        this.J = 0;
        this.else = new int[20];
        this.B = new int[20];
        this.y = new double[20];
        this.x = new double[20];
        this.z = new int[20][7];
        this.v = new int[20];
        this.u = new String[20];
        this.C = new String[20];
        this.a = new int[20];
        this.F = new int[20];
        this.l = new int[20];
        this.j = new int[20];
        this.byte = new int[20];
        this.g = new int[20];
        this.A = new int[20];
        this.m = new int[20];
        this.do = new String[20];
        this.goto = new int[10][2];
        this.K = new int[101];
        this.f = 0;
        this.r = 0;
        this.void = 0;
        this.s = 0;
        this.H = false;
        this.E = false;
        this.t = false;
        this.int = int1;
        this.null = 0;
        this.J = 0;
        this.f = 0;
    }
    
    a(final a a) {
        this.null = 0;
        this.J = 0;
        this.else = new int[20];
        this.B = new int[20];
        this.y = new double[20];
        this.x = new double[20];
        this.z = new int[20][7];
        this.v = new int[20];
        this.u = new String[20];
        this.C = new String[20];
        this.a = new int[20];
        this.F = new int[20];
        this.l = new int[20];
        this.j = new int[20];
        this.byte = new int[20];
        this.g = new int[20];
        this.A = new int[20];
        this.m = new int[20];
        this.do = new String[20];
        this.goto = new int[10][2];
        this.K = new int[101];
        this.f = 0;
        this.r = 0;
        this.void = 0;
        this.s = 0;
        this.H = false;
        this.E = false;
        this.t = false;
        this.int = a.int;
        this.null = a.null;
        this.J = a.J;
        this.f = a.f;
        this.else = new int[this.null + 1];
        System.arraycopy(a.else, 0, this.else, 0, this.null + 1);
        this.B = new int[this.null + 1];
        System.arraycopy(a.B, 0, this.B, 0, this.null + 1);
        this.a = new int[this.null + 1];
        System.arraycopy(a.a, 0, this.a, 0, this.null + 1);
        this.v = new int[this.null + 1];
        System.arraycopy(a.v, 0, this.v, 0, this.null + 1);
        this.u = new String[this.null + 1];
        System.arraycopy(a.u, 0, this.u, 0, this.null + 1);
        this.y = new double[this.null + 1];
        System.arraycopy(a.y, 0, this.y, 0, this.null + 1);
        this.x = new double[this.null + 1];
        System.arraycopy(a.x, 0, this.x, 0, this.null + 1);
        this.C = new String[this.null + 1];
        System.arraycopy(a.C, 0, this.C, 0, this.null + 1);
        this.l = new int[this.J + 1];
        System.arraycopy(a.l, 0, this.l, 0, this.J + 1);
        this.j = new int[this.J + 1];
        System.arraycopy(a.j, 0, this.j, 0, this.J + 1);
        this.byte = new int[this.J + 1];
        System.arraycopy(a.byte, 0, this.byte, 0, this.J + 1);
        this.do = new String[this.J + 1];
        System.arraycopy(a.do, 0, this.do, 0, this.J + 1);
        this.g = new int[this.J + 1];
        System.arraycopy(a.g, 0, this.g, 0, this.J + 1);
        this.goto = new int[this.f + 1][2];
        for (int i = 1; i <= this.f; ++i) {
            this.goto[i][0] = a.goto[i][0];
            this.goto[i][1] = a.goto[i][1];
        }
        this.r = a.r;
    }
    
    a(final JME jme, final a[] array, final int n) {
        this(jme);
        for (int i = 1; i <= n; ++i) {
            this.null += array[i].null;
            this.J += array[i].J;
            this.f += array[i].f;
        }
        this.else = new int[this.null + 1];
        this.B = new int[this.null + 1];
        this.a = new int[this.null + 1];
        this.v = new int[this.null + 1];
        this.u = new String[this.null + 1];
        this.y = new double[this.null + 1];
        this.x = new double[this.null + 1];
        this.C = new String[this.null + 1];
        this.l = new int[this.J + 1];
        this.j = new int[this.J + 1];
        this.byte = new int[this.J + 1];
        this.do = new String[this.J + 1];
        this.g = new int[this.J + 1];
        this.goto = new int[this.f + 1][2];
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        for (int j = 1; j <= n; ++j) {
            for (int k = 1; k <= array[j].null; ++k) {
                ++n2;
                this.else[n2] = array[j].else[k];
                this.y[n2] = array[j].y[k];
                this.x[n2] = array[j].x[k];
                this.B[n2] = array[j].B[k];
                this.a[n2] = array[j].a[k];
                this.v[n2] = array[j].v[k];
                this.u[n2] = array[j].u[k];
                this.C[n2] = array[j].C[k];
            }
            for (int l = 1; l <= array[j].J; ++l) {
                ++n3;
                this.byte[n3] = array[j].byte[l];
                this.g[n3] = array[j].g[l];
                this.l[n3] = array[j].l[l] + n5;
                this.j[n3] = array[j].j[l] + n5;
                this.do[n3] = array[j].do[l];
            }
            for (int n6 = 1; n6 <= array[j].f; ++n6) {
                ++n4;
                this.goto[n4][0] = array[j].goto[n6][0] + n5;
                this.goto[n4][1] = array[j].goto[n6][1];
            }
            n5 = n2;
        }
        this.a();
        this.void();
    }
    
    a(final JME jme, final a a, final int n) {
        this(jme);
        final int[] array = new int[a.null + 1];
        for (int i = 1; i <= a.null; ++i) {
            if (a.M[i] == n) {
                this.n();
                this.else[this.null] = a.else[i];
                this.y[this.null] = a.y[i];
                this.x[this.null] = a.x[i];
                this.B[this.null] = a.B[i];
                this.a[this.null] = a.a[i];
                this.v[this.null] = a.v[i];
                this.u[this.null] = a.u[i];
                this.C[this.null] = a.C[i];
                array[i] = this.null;
            }
        }
        for (int j = 1; j <= a.J; ++j) {
            final int n2 = a.l[j];
            final int n3 = a.j[j];
            if (a.M[n2] == n || a.M[n3] == n) {
                if (a.M[n2] != n || a.M[n3] != n) {
                    System.err.println("MOL multipart inconsistency - report bug !");
                }
                else {
                    this.k();
                    this.byte[this.J] = a.byte[j];
                    this.g[this.J] = a.g[j];
                    this.l[this.J] = array[n2];
                    this.j[this.J] = array[n3];
                    this.do[this.J] = a.do[j];
                }
            }
        }
        for (int k = 1; k <= a.f; ++k) {
            final int n4 = a.goto[k][0];
            if (n4 == n) {
                ++this.f;
                this.goto[this.f][0] = array[n4];
                this.goto[this.f][1] = a.goto[k][1];
            }
        }
        this.r = a.r;
        this.a();
        this.void();
    }
    
    a(final JME jme, String s, final boolean b) {
        this(jme);
        if (s.startsWith("\"")) {
            s = s.substring(1, s.length());
        }
        if (s.endsWith("\"")) {
            s = s.substring(0, s.length() - 1);
        }
        if (s.length() < 1) {
            this.null = 0;
            return;
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            final int intValue = Integer.valueOf(stringTokenizer.nextToken());
            final int intValue2 = Integer.valueOf(stringTokenizer.nextToken());
            for (int i = 1; i <= intValue; ++i) {
                this.if(stringTokenizer.nextToken());
                if (b) {
                    this.y[i] = Double.valueOf(stringTokenizer.nextToken());
                    this.x[i] = -Double.valueOf(stringTokenizer.nextToken());
                }
                if (jme.aW) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (!nextToken.equals("*")) {
                        this.u[i] = nextToken;
                    }
                }
            }
            for (int j = 1; j <= intValue2; ++j) {
                this.k();
                this.l[j] = Integer.valueOf(stringTokenizer.nextToken());
                this.j[j] = Integer.valueOf(stringTokenizer.nextToken());
                this.byte[j] = Integer.valueOf(stringTokenizer.nextToken());
                if (this.byte[j] == -1) {
                    this.byte[j] = 1;
                    this.g[j] = 1;
                }
                if (this.byte[j] == -2) {
                    this.byte[j] = 1;
                    this.g[j] = 2;
                }
                if (jme.aW) {
                    final String nextToken2 = stringTokenizer.nextToken();
                    if (!nextToken2.equals("*")) {
                        this.do[j] = nextToken2;
                    }
                }
            }
            this.char();
            if (b) {
                this.int();
                this.void();
            }
        }
        catch (Exception ex) {
            System.err.println("read mol exception - " + ex.getMessage());
            this.null = 0;
            return;
        }
        this.f();
        this.a();
    }
    
    a(final JME jme, String s, final double n) {
        this(jme);
        if (s.startsWith("\"")) {
            s = s.substring(1, s.length());
        }
        if (s.endsWith("\"")) {
            s = s.substring(0, s.length() - 1);
        }
        if (s.length() < 1) {
            this.null = 0;
            return;
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s);
            final String nextToken = stringTokenizer.nextToken();
            nextToken.toLowerCase();
            if (!nextToken.startsWith("mi")) {
                this.null = 0;
                jme.a("ERROR - not valid mi String");
                return;
            }
            boolean b = false;
            if (nextToken.indexOf("t") > -1) {
                b = true;
            }
            final int intValue = Integer.valueOf(stringTokenizer.nextToken());
            final int intValue2 = Integer.valueOf(stringTokenizer.nextToken());
            for (int i = 1; i <= intValue; ++i) {
                this.if(stringTokenizer.nextToken());
                this.y[i] = Double.valueOf(stringTokenizer.nextToken());
                this.x[i] = -Double.valueOf(stringTokenizer.nextToken());
                if (b) {
                    final String nextToken2 = stringTokenizer.nextToken();
                    if (!nextToken2.equals("*")) {
                        this.u[i] = nextToken2;
                    }
                }
            }
            for (int j = 1; j <= intValue2; ++j) {
                this.k();
                this.l[j] = Integer.valueOf(stringTokenizer.nextToken());
                this.j[j] = Integer.valueOf(stringTokenizer.nextToken());
                final String nextToken3 = stringTokenizer.nextToken();
                if (nextToken3.equals("-")) {
                    this.byte[j] = 1;
                }
                else if (nextToken3.equals("=")) {
                    this.byte[j] = 2;
                }
                else if (nextToken3.equals("#")) {
                    this.byte[j] = 3;
                }
                else if (nextToken3.equals("u")) {
                    this.byte[j] = 1;
                    this.g[j] = 1;
                }
                else if (nextToken3.equals("d")) {
                    this.byte[j] = 1;
                    this.g[j] = 2;
                }
                else {
                    this.byte[j] = 9;
                }
                if (b) {
                    final String nextToken4 = stringTokenizer.nextToken();
                    if (!nextToken4.equals("*")) {
                        this.do[j] = nextToken4;
                    }
                }
            }
            this.char();
            this.int();
            this.void();
        }
        catch (Exception ex) {
            System.err.println("read mol exception - " + ex.getMessage());
            this.null = 0;
            return;
        }
        this.a();
    }
    
    a(final JME jme, final String s) {
        this(jme);
        String a = "";
        final String do1 = do(s);
        final StringTokenizer stringTokenizer = new StringTokenizer(s, do1, true);
        for (int i = 1; i <= 4; ++i) {
            a = a(stringTokenizer, do1);
        }
        final int intValue = Integer.valueOf(a.substring(0, 3).trim());
        final int intValue2 = Integer.valueOf(a.substring(3, 6).trim());
        for (int j = 1; j <= intValue; ++j) {
            this.n();
            final String a2 = a(stringTokenizer, do1);
            this.y[j] = Double.valueOf(a2.substring(0, 10).trim());
            this.x[j] = -Double.valueOf(a2.substring(10, 20).trim());
            int length = 34;
            if (a2.length() < 34) {
                length = a2.length();
            }
            this.a(j, a2.substring(31, length).trim());
            if (a2.length() >= 62) {
                final String trim = a2.substring(60, 63).trim();
                if (trim.length() > 0) {
                    a.p = Integer.valueOf(trim) - 1;
                    if (a.p > -1) {
                        this.void = j;
                        this.b();
                        this.void = 0;
                    }
                }
            }
        }
        for (int k = 1; k <= intValue2; ++k) {
            this.k();
            final String a3 = a(stringTokenizer, do1);
            this.l[k] = Integer.valueOf(a3.substring(0, 3).trim());
            this.j[k] = Integer.valueOf(a3.substring(3, 6).trim());
            final int intValue3 = Integer.valueOf(a3.substring(6, 9).trim());
            if (intValue3 == 1) {
                this.byte[k] = 1;
            }
            else if (intValue3 == 2) {
                this.byte[k] = 2;
            }
            else if (intValue3 == 3) {
                this.byte[k] = 3;
            }
            else {
                this.byte[k] = 9;
            }
            int intValue4 = 0;
            if (a3.length() > 11) {
                intValue4 = Integer.valueOf(a3.substring(9, 12).trim());
            }
            if (intValue3 == 1 && intValue4 == 1) {
                this.byte[k] = 1;
                this.g[k] = 1;
            }
            if (intValue3 == 1 && intValue4 == 6) {
                this.byte[k] = 1;
                this.g[k] = 2;
            }
        }
        this.char();
        this.int();
        this.void();
        this.a();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken;
            if ((nextToken = stringTokenizer.nextToken()) == null) {
                break;
            }
            if (nextToken.startsWith("M  END")) {
                break;
            }
            if (!nextToken.startsWith("M  CHG")) {
                continue;
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken);
            stringTokenizer2.nextToken();
            stringTokenizer2.nextToken();
            for (int intValue5 = Integer.valueOf(stringTokenizer2.nextToken()), l = 1; l <= intValue5; ++l) {
                this.B[Integer.valueOf(stringTokenizer2.nextToken())] = Integer.valueOf(stringTokenizer2.nextToken());
            }
        }
        this.f();
        this.a();
    }
    
    public static String a(final StringTokenizer stringTokenizer, final String s) {
        if (!stringTokenizer.hasMoreTokens()) {
            return null;
        }
        String s2 = stringTokenizer.nextToken();
        if (s2.equals(s)) {
            return " ";
        }
        if (!stringTokenizer.nextToken().equals(s)) {
            System.err.println("mol file line separator problem!");
        }
        do {
            final char char1 = s2.charAt(s2.length() - 1);
            if (char1 != '|' && char1 != '\n' && char1 != '\r') {
                return s2;
            }
            s2 = s2.substring(0, s2.length() - 1);
        } while (s2.length() != 0);
        return " ";
    }
    
    public static String do(final String s) {
        if (new StringTokenizer(s, "\n", true).countTokens() > 4) {
            return "\n";
        }
        if (new StringTokenizer(s, "|", true).countTokens() > 4) {
            return "|";
        }
        System.err.println("Cannot process mol file, use | as line separator !");
        return null;
    }
    
    public int long() {
        return this.null;
    }
    
    public int if() {
        return this.J;
    }
    
    public double null(final int n) {
        return this.y[n] * 1.4 / 25.0;
    }
    
    public double try(final int n) {
        return this.x[n] * 1.4 / 25.0;
    }
    
    public void a(final double n, final double n2, final int n3, final int n4) {
        this.y[this.null] = n;
        this.x[this.null] = n2;
        this.if(this.null, n3);
        this.a(this.null, n4);
    }
    
    public int new(final int n) {
        return this.a[n];
    }
    
    public int void(final int n) {
        return this.B[n];
    }
    
    public int[] case(final int n) {
        return new int[] { this.l[n], this.j[n], this.byte[n], this.g[n] };
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.l[this.J] = n;
        this.j[this.J] = n2;
        this.byte[this.J] = n3;
        this.g[this.J] = n4;
    }
    
    public void goto() {
        this.char();
        this.int();
        this.void();
        this.a();
    }
    
    void a() {
        this.char();
        final int length = this.byte.length;
        this.A = new int[length];
        this.m = new int[length];
        this.new();
        this.c();
    }
    
    void int() {
        double n = 0.0;
        double n2 = 0.0;
        for (int i = 1; i <= this.J; ++i) {
            final double n3 = this.y[this.l[i]] - this.y[this.j[i]];
            final double n4 = this.x[this.l[i]] - this.x[this.j[i]];
            n += Math.sqrt(n3 * n3 + n4 * n4);
        }
        if (this.J > 0) {
            n2 = 25.0 / (n / this.J);
        }
        else if (this.null > 1) {
            n2 = 75.0 / Math.sqrt((this.y[1] - this.y[2]) * (this.y[1] - this.y[2]) + (this.x[1] - this.x[2]) * (this.x[1] - this.x[2]));
        }
        for (int j = 1; j <= this.null; ++j) {
            final double[] y = this.y;
            final int n5 = j;
            y[n5] *= n2;
            final double[] x = this.x;
            final int n6 = j;
            x[n6] *= n2;
        }
    }
    
    public void void() {
        final double[] array = new double[4];
        int n = 0;
        int n2 = 0;
        if (this.int != null && this.int.bA != null && this.int.bA.width > 0) {
            n = this.int.bA.width - this.int.aa;
            n2 = this.int.bA.height - this.int.aa * 3;
        }
        if (n <= 0 || n2 <= 0) {
            this.t = true;
            return;
        }
        this.a(array);
        final int n3 = n / 2 - (int)Math.round(array[0]);
        final int n4 = n2 / 2 - (int)Math.round(array[1]);
        if (!this.int.bD) {
            for (int i = 1; i <= this.null; ++i) {
                final double[] y = this.y;
                final int n5 = i;
                y[n5] += n3;
                final double[] x = this.x;
                final int n6 = i;
                x[n6] += n4;
            }
        }
        this.new();
    }
    
    int case(final int n, final int n2) {
        int n3 = 0;
        double n4 = 51.0;
        for (int i = 1; i <= this.null; ++i) {
            final double n5 = n - this.y[i];
            final double n6 = n2 - this.x[i];
            final double n7 = n5 * n5 + n6 * n6;
            if (n7 < 50.0 && n7 < n4) {
                n4 = n7;
                n3 = i;
            }
        }
        return n3;
    }
    
    int try(final int n, final int n2) {
        int n3 = 0;
        double n4 = 51.0;
        for (int i = 1; i <= this.J; ++i) {
            final double n5 = n - this.A[i];
            final double n6 = n2 - this.m[i];
            final double n7 = n5 * n5 + n6 * n6;
            if (n7 < 50.0 && n7 < n4) {
                n4 = n7;
                n3 = i;
            }
        }
        return n3;
    }
    
    void h() {
        this.d();
        this.null = 0;
        this.J = 0;
        this.f = 0;
    }
    
    void a(final Graphics graphics) {
        double n = 1.0;
        double n2 = 1.0;
        double n3 = 2.0;
        double n4 = 3.0;
        if (this.t) {
            this.void();
            this.int.a(1, this.int.long, 0);
            this.t = false;
        }
        if (this.int.r != 1.0) {
            n3 *= this.int.r;
            n4 *= this.int.r;
            double n5 = 1.0;
            if (this.int.r < 0.7) {
                n5 = 1.2;
            }
            this.int.font = new Font("Helvetica", 0, (int)(this.int.v * this.int.r * n5));
            this.int.bf = graphics.getFontMetrics(this.int.font);
        }
        if (this.int.bG) {
            graphics.setColor(Color.black);
            graphics.drawRect(0, 0, this.int.bA.width - 1, this.int.bA.height - 1);
        }
        if (this.null == 0) {
            return;
        }
        if (this.r == -1) {
            final int n6 = (int)Math.round(n3 * 12.0);
            for (int i = 1; i <= this.null; ++i) {
                if (this.v[i] > 0 && this.v[i] < 7) {
                    graphics.setColor(JME.byte[this.v[i]]);
                    graphics.fillOval((int)(this.y[i] - n6 / 2.0), (int)(this.x[i] - n6 / 2.0), n6, n6);
                }
            }
            for (int j = 1; j < this.J; ++j) {
                final int n7 = this.l[j];
                final int n8 = this.j[j];
                if (this.v[n7] != 0) {
                    if (this.v[n7] == this.v[n8]) {
                        graphics.setColor(JME.byte[this.v[n7]]);
                        final double n9 = this.y[n8] - this.y[n7];
                        final double n10 = this.x[n8] - this.x[n7];
                        double sqrt = Math.sqrt(n9 * n9 + n10 * n10);
                        if (sqrt < 1.0) {
                            sqrt = 1.0;
                        }
                        n = n10 / sqrt;
                        n2 = n9 / sqrt;
                        final double n11 = n4 * 3.0 * n;
                        final double n12 = n4 * 3.0 * n2;
                        final int[] array = new int[4];
                        final int[] array2 = new int[4];
                        array[0] = (int)(this.y[n7] + n11);
                        array2[0] = (int)(this.x[n7] - n12);
                        array[1] = (int)(this.y[n8] + n11);
                        array2[1] = (int)(this.x[n8] - n12);
                        array[2] = (int)(this.y[n8] - n11);
                        array2[2] = (int)(this.x[n8] + n12);
                        array[3] = (int)(this.y[n7] - n11);
                        array2[3] = (int)(this.x[n7] + n12);
                        graphics.fillPolygon(array, array2, 4);
                    }
                }
            }
        }
        for (int k = 1; k <= this.J; ++k) {
            graphics.setColor(Color.black);
            int n13 = this.l[k];
            int n14 = this.j[k];
            if (this.r == 1 && this.v[n13] != 0 && this.v[n13] == this.v[n14]) {
                graphics.setColor(JME.byte[this.v[n13]]);
            }
            if (this.g[k] == 3 || this.g[k] == 4) {
                final int n15 = n13;
                n13 = n14;
                n14 = n15;
            }
            final double n16 = this.y[n13];
            final double n17 = this.x[n13];
            final double n18 = this.y[n14];
            final double n19 = this.x[n14];
            if (this.byte[k] != 1 || this.g[k] != 0) {
                final double n20 = n18 - n16;
                final double n21 = n19 - n17;
                double sqrt2 = Math.sqrt(n20 * n20 + n21 * n21);
                if (sqrt2 < 1.0) {
                    sqrt2 = 1.0;
                }
                n = n21 / sqrt2;
                n2 = n20 / sqrt2;
            }
            switch (this.byte[k]) {
                case 2: {
                    if (this.g[k] >= 10) {
                        graphics.setColor(Color.magenta);
                    }
                    final double n22 = n3 * n;
                    final double n23 = n3 * n2;
                    graphics.drawLine((int)Math.round(n16 + n22), (int)Math.round(n17 - n23), (int)Math.round(n18 + n22), (int)Math.round(n19 - n23));
                    graphics.drawLine((int)Math.round(n16 - n22), (int)Math.round(n17 + n23), (int)Math.round(n18 - n22), (int)Math.round(n19 + n23));
                    graphics.setColor(Color.black);
                    break;
                }
                case 3: {
                    graphics.drawLine((int)Math.round(n16), (int)Math.round(n17), (int)Math.round(n18), (int)Math.round(n19));
                    final double n24 = n4 * n;
                    final double n25 = n4 * n2;
                    graphics.drawLine((int)Math.round(n16 + n24), (int)Math.round(n17 - n25), (int)Math.round(n18 + n24), (int)Math.round(n19 - n25));
                    graphics.drawLine((int)Math.round(n16 - n24), (int)Math.round(n17 + n25), (int)Math.round(n18 - n24), (int)Math.round(n19 + n25));
                    break;
                }
                case 0:
                case 9: {
                    for (int l = 0; l < 10; ++l) {
                        final double n26 = n16 - (n16 - n18) / 10.0 * l;
                        final double n27 = n17 - (n17 - n19) / 10.0 * l;
                        final double n28 = l / 10.0;
                        graphics.drawLine((int)Math.round(n26), (int)Math.round(n27), (int)Math.round(n26), (int)Math.round(n27));
                    }
                    graphics.setFont(this.int.font);
                    final int ascent = this.int.bf.getAscent();
                    String s = "?";
                    switch (this.g[k]) {
                        case 1: {
                            s = "~";
                            break;
                        }
                        case 2: {
                            s = ":";
                            break;
                        }
                        case 3: {
                            s = "@";
                            break;
                        }
                        case 4: {
                            s = "!@";
                            break;
                        }
                    }
                    final int n29 = (int)Math.round((n16 + n18) / 2.0 - this.int.bf.stringWidth(s) / 2.0);
                    final int n30 = (int)Math.round((n17 + n19) / 2.0 + ascent / 2 - 1.0);
                    graphics.setColor(Color.magenta);
                    graphics.drawString(s, n29, n30);
                    graphics.setColor(Color.black);
                    break;
                }
                default: {
                    if (this.g[k] == 1 || this.g[k] == 3) {
                        final double n31 = n4 * n;
                        final double n32 = n4 * n2;
                        final int[] array3 = new int[3];
                        final int[] array4 = new int[3];
                        array3[0] = (int)Math.round(n18 + n31);
                        array4[0] = (int)Math.round(n19 - n32);
                        array3[1] = (int)Math.round(n16);
                        array4[1] = (int)Math.round(n17);
                        array3[2] = (int)Math.round(n18 - n31);
                        array4[2] = (int)Math.round(n19 + n32);
                        graphics.fillPolygon(array3, array4, 3);
                        break;
                    }
                    if (this.g[k] == 2 || this.g[k] == 4) {
                        final double n33 = n4 * n;
                        final double n34 = n4 * n2;
                        for (int n35 = 0; n35 < 10; ++n35) {
                            final double n36 = n16 - (n16 - n18) / 10.0 * n35;
                            final double n37 = n17 - (n17 - n19) / 10.0 * n35;
                            final double n38 = n35 / 10.0;
                            graphics.drawLine((int)Math.round(n36 + n33 * n38), (int)Math.round(n37 - n34 * n38), (int)Math.round(n36 - n33 * n38), (int)Math.round(n37 + n34 * n38));
                        }
                        break;
                    }
                    graphics.drawLine((int)Math.round(n16), (int)Math.round(n17), (int)Math.round(n18), (int)Math.round(n19));
                    break;
                }
            }
            if (this.int.aW && this.do[k] != null && this.do[k].length() > 0) {
                graphics.setFont(this.int.font);
                final int ascent2 = this.int.bf.getAscent();
                final int n39 = (int)Math.round((n16 + n18) / 2.0 - this.int.bf.stringWidth(this.do[k]) / 2.0);
                final int n40 = (int)Math.round((n17 + n19) / 2.0 + ascent2 / 2 - 1.0);
                graphics.setColor(Color.red);
                graphics.drawString(this.do[k], n39, n40);
                graphics.setColor(Color.black);
            }
        }
        graphics.setFont(this.int.font);
        final int ascent3 = this.int.bf.getAscent();
        final String[] array5 = new String[this.null + 1];
        for (int n41 = 1; n41 <= this.null; ++n41) {
            String s2 = this.byte(n41);
            if (this.else[n41] == 3 && this.F[n41] > 0 && this.B[n41] == 0 && !a.G) {
                array5[n41] = s2;
            }
            else {
                if (this.int.if && !a.G) {
                    if (this.a[n41] > 0) {
                        s2 += "H";
                    }
                    if (this.a[n41] > 1) {
                        s2 += this.a[n41];
                    }
                }
                if (this.B[n41] != 0) {
                    if (Math.abs(this.B[n41]) > 1) {
                        s2 += Math.abs(this.B[n41]);
                    }
                    if (this.B[n41] > 0) {
                        s2 += "+";
                    }
                    else {
                        s2 += "-";
                    }
                }
                if (a.G) {
                    s2 += n41;
                }
                array5[n41] = s2;
                if (s2 == null || s2.length() < 1) {
                    s2 = "*";
                    System.err.println("Z error!");
                }
                final int stringWidth = this.int.bf.stringWidth(s2);
                final int n42 = (int)Math.round(this.y[n41] - stringWidth / 2.0);
                final int n43 = (int)Math.round(this.x[n41] + ascent3 / 2 - 1.0);
                graphics.setColor(this.int.aI);
                if (this.r == -1 && this.v[n41] != 0) {
                    graphics.setColor(JME.byte[this.v[n41]]);
                }
                graphics.fillRect(n42 - 1, n43 - ascent3 + 2, stringWidth + 1, ascent3 - 1);
                if (this.r == 1) {
                    if (this.v[n41] != 0) {
                        graphics.setColor(JME.byte[this.v[n41]]);
                    }
                    else {
                        graphics.setColor(Color.black);
                    }
                }
                else {
                    graphics.setColor(JME.bu[this.else[n41]]);
                }
                if (this.int.at) {
                    graphics.setColor(Color.black);
                }
                graphics.drawString(s2, n42, n43);
            }
        }
        for (int n44 = 1; n44 <= this.f; ++n44) {
            final int n45 = this.goto[n44][0];
            final int stringWidth2 = this.int.bf.stringWidth(array5[n45]);
            final int n46 = (int)Math.round(this.y[n45] - stringWidth2 / 2.0);
            final int n47 = (int)Math.round(this.x[n45] + ascent3 / 2 - 1.0);
            graphics.setColor(Color.magenta);
            graphics.drawString(" " + this.goto[n44][1], n46 + stringWidth2, n47);
        }
        if (this.int.aW) {
            for (int n48 = 1; n48 <= this.null; ++n48) {
                if (this.u[n48] != null) {
                    if (!this.u[n48].equals("")) {
                        final int stringWidth3 = this.int.bf.stringWidth(array5[n48]);
                        final int n49 = (int)Math.round(this.y[n48] - stringWidth3 / 2.0);
                        final int n50 = (int)Math.round(this.x[n48] + ascent3 / 2 - 1.0);
                        graphics.setColor(Color.red);
                        graphics.drawString(" " + this.u[n48], n49 + stringWidth3, n50);
                    }
                }
            }
        }
        if ((this.void > 0 || this.s > 0) && !this.int.V) {
            graphics.setColor((this.int.al == 104) ? Color.red : Color.blue);
            if (this.void > 0 && this.int.al != 106) {
                final int stringWidth4 = this.int.bf.stringWidth(array5[this.void]);
                graphics.drawRect((int)Math.round(this.y[this.void] - stringWidth4 / 2.0 - 1.0), (int)Math.round(this.x[this.void] - ascent3 / 2.0 - 1.0), stringWidth4 + 2, ascent3 + 2);
            }
            if (this.s > 0) {
                final int n51 = this.l[this.s];
                final int n52 = this.j[this.s];
                final double n53 = this.y[n52] - this.y[n51];
                final double n54 = this.x[n52] - this.x[n51];
                double sqrt3 = Math.sqrt(n53 * n53 + n54 * n54);
                if (sqrt3 < 1.0) {
                    sqrt3 = 1.0;
                }
                final double n55 = n54 / sqrt3;
                final double n56 = n53 / sqrt3;
                final double n57 = (n4 + 1.0) * n55;
                final double n58 = (n4 + 1.0) * n56;
                final int[] array6 = new int[5];
                final int[] array7 = new int[5];
                array6[0] = (int)Math.round(this.y[n51] + n57);
                array6[1] = (int)Math.round(this.y[n52] + n57);
                array7[0] = (int)Math.round(this.x[n51] - n58);
                array7[1] = (int)Math.round(this.x[n52] - n58);
                array6[3] = (int)Math.round(this.y[n51] - n57);
                array6[2] = (int)Math.round(this.y[n52] - n57);
                array7[3] = (int)Math.round(this.x[n51] + n58);
                array7[2] = (int)Math.round(this.x[n52] + n58);
                array6[4] = array6[0];
                array7[4] = array7[0];
                if (this.int.al != 106) {
                    graphics.drawPolygon(array6, array7, 5);
                }
                if (this.int.al == 106) {
                    if (this.do(this.l[this.s], this.j[this.s])) {
                        int n59 = 0;
                        for (int n60 = 1; n60 <= this.null; ++n60) {
                            if (this.M[n60] > 0) {
                                ++n59;
                            }
                        }
                        if (n59 > this.null / 2) {
                            for (int n61 = 1; n61 <= this.null; ++n61) {
                                if (this.M[n61] > 0) {
                                    this.M[n61] = 0;
                                }
                                else {
                                    this.M[n61] = 1;
                                }
                            }
                        }
                        graphics.setColor(Color.red);
                        for (int n62 = 1; n62 <= this.null; ++n62) {
                            if (this.M[n62] > 0) {
                                final int stringWidth5 = this.int.bf.stringWidth(array5[n62]);
                                graphics.drawRect((int)Math.round(this.y[n62] - stringWidth5 / 2.0 - 1.0), (int)Math.round(this.x[n62] - ascent3 / 2.0 - 1.0), stringWidth5 + 2, ascent3 + 2);
                            }
                        }
                    }
                }
            }
        }
    }
    
    void new(final int n, final int n2) {
        for (int i = 1; i <= this.null; ++i) {
            final double[] y = this.y;
            final int n3 = i;
            y[n3] += n;
            final double[] x = this.x;
            final int n4 = i;
            x[n4] += n2;
        }
        final double[] array = new double[4];
        this.a(array);
        final double n5 = array[0];
        final double n6 = array[1];
        if (n5 > 0.0 && n5 < this.int.bA.width - this.int.aa && n6 > 0.0 && n6 < this.int.bA.height - this.int.aa * 3) {
            return;
        }
        for (int j = 1; j <= this.null; ++j) {
            final double[] y2 = this.y;
            final int n7 = j;
            y2[n7] -= n;
            final double[] x2 = this.x;
            final int n8 = j;
            x2[n8] -= n2;
        }
    }
    
    void g(final int n) {
        final double[] array = new double[4];
        this.a(array);
        final double n2 = array[0];
        final double n3 = array[1];
        final double sin = Math.sin(n * 3.141592653589793 / 180.0);
        final double cos = Math.cos(n * 3.141592653589793 / 180.0);
        for (int i = 1; i <= this.null; ++i) {
            final double n4 = this.y[i] * cos + this.x[i] * sin;
            final double n5 = -this.y[i] * sin + this.x[i] * cos;
            this.y[i] = n4;
            this.x[i] = n5;
        }
        this.a(array);
        for (int j = 1; j <= this.null; ++j) {
            final double[] y = this.y;
            final int n6 = j;
            y[n6] += n2 - array[0];
            final double[] x = this.x;
            final int n7 = j;
            x[n7] += n3 - array[1];
        }
    }
    
    void a(final double[] array) {
        double n = 9999.0;
        double n2 = -9999.0;
        double n3 = 9999.0;
        double n4 = -9999.0;
        for (int i = 1; i <= this.null; ++i) {
            if (this.y[i] < n) {
                n = this.y[i];
            }
            if (this.y[i] > n2) {
                n2 = this.y[i];
            }
            if (this.x[i] < n3) {
                n3 = this.x[i];
            }
            if (this.x[i] > n4) {
                n4 = this.x[i];
            }
        }
        array[0] = n + (n2 - n) / 2.0;
        array[1] = n3 + (n4 - n3) / 2.0;
        array[2] = n2 - n;
        array[3] = n4 - n3;
        if (array[2] < 25.0) {
            array[2] = 25.0;
        }
        if (array[3] < 25.0) {
            array[3] = 25.0;
        }
    }
    
    void byte(final int n, final int n2) {
        this.void = 0;
        this.y[0] = n;
        this.x[0] = n2;
        final int do1 = this.do(0);
        if (do1 > 0 && this.int.al != 205) {
            if ((this.void = do1) != this.long) {
                this.y[this.null] = this.y[do1];
                this.x[this.null] = this.x[do1];
            }
            else {
                this.y[this.null] = this.new;
                this.x[this.null] = this.o;
            }
        }
        else if (this.int.al == 205) {
            final int n3 = this.K[this.w];
            final int n4 = this.K[this.w - 1];
            final double n5 = this.y[n3] - this.y[n4];
            final double n6 = this.x[n3] - this.x[n4];
            double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
            if (sqrt < 1.0) {
                sqrt = 1.0;
            }
            final double n7 = n6 / sqrt;
            final double n8 = n5 / sqrt;
            final double n9 = sqrt / 2.0 / Math.tan(0.5235987755982988);
            final double n10 = n - this.y[n4];
            final double n11 = n2 - this.x[n4];
            final double n12 = -sqrt / 2.0 + n10 * n8 + n11 * n7;
            final double n13 = n11 * n8 - n10 * n7;
            if (n12 < 0.0) {
                if (this.w > 1) {
                    this.c(this.null);
                    --this.w;
                    this.E = false;
                }
                else if (this.null == 2) {
                    if (this.x[2] - this.x[1] < 0.0 && n2 - this.x[1] > 0.0) {
                        this.x[2] = this.x[1] + sqrt / 2.0;
                    }
                    else if (this.x[2] - this.x[1] > 0.0 && n2 - this.x[1] < 0.0) {
                        this.x[2] = this.x[1] - sqrt / 2.0;
                    }
                    if (this.y[2] - this.y[1] < 0.0 && n - this.y[1] > 0.0) {
                        this.y[2] = this.y[1] + sqrt * 0.866;
                    }
                    else if (this.y[2] - this.y[1] > 0.0 && n - this.y[1] < 0.0) {
                        this.y[2] = this.y[1] - sqrt * 0.866;
                    }
                }
                else if (this.F[this.K[0]] == 2) {
                    int n14 = this.z[this.K[0]][1];
                    if (n14 == this.K[1]) {
                        n14 = this.z[this.K[0]][2];
                    }
                    final double n15 = this.y[this.K[0]] - this.y[n14];
                    final double n16 = this.x[this.K[0]] - this.x[n14];
                    double sqrt2 = Math.sqrt(n15 * n15 + n16 * n16);
                    if (sqrt2 < 1.0) {
                        sqrt2 = 1.0;
                    }
                    final double n17 = n16 / sqrt2;
                    final double n18 = n15 / sqrt2;
                    final double n19 = (n2 - this.x[n14]) * n18 - (n - this.y[n14]) * n17;
                    final double n20 = (this.x[this.K[1]] - this.x[n14]) * n18 - (this.y[this.K[1]] - this.y[n14]) * n17;
                    if ((n19 > 0.0 && n20 < 0.0) || (n19 < 0.0 && n20 > 0.0)) {
                        final int j = this.J;
                        this.void = this.K[0];
                        this.l();
                        this.b(j);
                        if (this.do(this.null) > 0) {
                            this.E = true;
                        }
                    }
                }
            }
            else {
                if (this.E) {
                    return;
                }
                double n21 = -1.0;
                if (n12 < sqrt * 1.5) {
                    n21 = (sqrt * 1.5 - n12) * n9 / (sqrt * 1.5);
                }
                if (Math.abs(n13) > n21) {
                    ++this.w;
                    if (this.w > 100) {
                        this.int.a("You are too focused on chains, enough of it for now !");
                        --this.w;
                        return;
                    }
                    this.void = this.null;
                    this.j((int)Math.round(n13));
                    this.K[this.w] = this.null;
                    if (this.do(this.null) > 0) {
                        this.E = true;
                    }
                }
            }
            this.void = 0;
            this.int.a(this.w + "");
        }
        else {
            final double n22 = n - this.y[this.long];
            final double n23 = n2 - this.x[this.long];
            double sqrt3 = Math.sqrt(n22 * n22 + n23 * n23);
            if (sqrt3 < 1.0) {
                sqrt3 = 1.0;
            }
            final double n24 = n23 / sqrt3;
            this.y[this.null] = this.y[this.long] + 25.0 * (n22 / sqrt3);
            this.x[this.null] = this.x[this.long] + 25.0 * n24;
        }
    }
    
    void j() {
        if (this.E) {
            final int do1 = this.do(this.null);
            if (this.F[do1] < 6) {
                this.k();
                final int n = this.K[this.w - 1];
                this.l[this.J] = do1;
                this.j[this.J] = n;
                this.z[do1][++this.F[do1]] = n;
                this.z[n][++this.F[n]] = do1;
            }
            this.c(this.null);
        }
        this.E = false;
    }
    
    private int do(final int n) {
        double n2 = 51.0;
        int n3 = 0;
        for (int i = 1; i < this.null; ++i) {
            if (n != i) {
                final double n4 = this.y[n] - this.y[i];
                final double n5 = this.x[n] - this.x[i];
                final double n6 = n4 * n4 + n5 * n5;
                if (n6 < 50.0 && n6 < n2) {
                    n2 = n6;
                    n3 = i;
                }
            }
        }
        return n3;
    }
    
    void long(int null) {
        if (null == 0) {
            null = this.null;
        }
        for (int i = this.null; i > this.null - null; --i) {
            final int do1 = this.do(i);
            if (do1 != 0) {
                final double n = this.y[i] - this.y[do1];
                final double n2 = this.x[i] - this.x[do1];
                final double[] y = this.y;
                final int n3 = i;
                y[n3] += 6.0;
                final double[] x = this.x;
                final int n4 = i;
                x[n4] += 6.0;
            }
        }
    }
    
    void c(final int n) {
        int j = 0;
        for (int i = 1; i <= this.J; ++i) {
            final int n2 = this.l[i];
            final int n3 = this.j[i];
            if (n2 != n && n3 != n) {
                ++j;
                if ((this.l[j] = n2) > n) {
                    final int[] l = this.l;
                    final int n4 = j;
                    --l[n4];
                }
                if ((this.j[j] = n3) > n) {
                    final int[] k = this.j;
                    final int n5 = j;
                    --k[n5];
                }
                this.byte[j] = this.byte[i];
                this.g[j] = this.g[i];
                this.A[j] = this.A[i];
                this.m[j] = this.m[i];
                this.do[j] = this.do[i];
            }
        }
        this.J = j;
        for (int n6 = n; n6 < this.null; ++n6) {
            this.else[n6] = this.else[n6 + 1];
            this.B[n6] = this.B[n6 + 1];
            this.y[n6] = this.y[n6 + 1];
            this.x[n6] = this.x[n6 + 1];
            this.a[n6] = this.a[n6 + 1];
            this.v[n6] = this.v[n6 + 1];
            this.u[n6] = this.u[n6 + 1];
            this.F[n6] = this.F[n6 + 1];
            this.C[n6] = this.C[n6 + 1];
            for (int n7 = 1; n7 <= this.F[n6]; ++n7) {
                this.z[n6][n7] = this.z[n6 + 1][n7];
            }
        }
        --this.null;
        if (this.null == 0) {
            this.int.for();
            return;
        }
        for (int n8 = 1; n8 <= this.null; ++n8) {
            int n9 = 0;
            for (int n10 = 1; n10 <= this.F[n8]; ++n10) {
                int n11 = this.z[n8][n10];
                if (n11 == n) {
                    final int[] a = this.a;
                    final int n12 = n8;
                    ++a[n12];
                }
                else {
                    if (n11 > n) {
                        --n11;
                    }
                    this.z[n8][++n9] = n11;
                }
            }
            this.F[n8] = n9;
        }
        for (int n13 = 1; n13 <= this.f; ++n13) {
            if (this.goto[n13][0] == n) {
                for (int n14 = n13; n14 < this.f; ++n14) {
                    this.goto[n14][0] = this.goto[n14 + 1][0];
                    this.goto[n14][1] = this.goto[n14 + 1][1];
                }
                --this.f;
                break;
            }
        }
        for (int n15 = 1; n15 <= this.f; ++n15) {
            if (this.goto[n15][0] > n) {
                final int[] array = this.goto[n15];
                final int n16 = 0;
                --array[n16];
            }
        }
    }
    
    void b(final int n) {
        int n2 = this.l[n];
        int n3 = this.j[n];
        for (int i = n; i < this.J; ++i) {
            this.l[i] = this.l[i + 1];
            this.j[i] = this.j[i + 1];
            this.byte[i] = this.byte[i + 1];
            this.g[i] = this.g[i + 1];
            this.A[i] = this.A[i + 1];
            this.m[i] = this.m[i + 1];
            this.do[i] = this.do[i + 1];
        }
        --this.J;
        int n4 = 0;
        for (int j = 1; j <= this.F[n2]; ++j) {
            if (this.z[n2][j] != n3) {
                this.z[n2][++n4] = this.z[n2][j];
            }
        }
        this.F[n2] = n4;
        int n5 = 0;
        for (int k = 1; k <= this.F[n3]; ++k) {
            if (this.z[n3][k] != n2) {
                this.z[n3][++n5] = this.z[n3][k];
            }
        }
        this.F[n3] = n5;
        if (n2 < n3) {
            final int n6 = n2;
            n2 = n3;
            n3 = n6;
        }
        if (this.F[n2] == 0) {
            this.c(n2);
        }
        if (this.F[n3] == 0) {
            this.c(n3);
        }
    }
    
    void e(final int n) {
        if (this.M[this.l[n]] > 0 && this.M[this.j[n]] > 0) {
            this.int.a("Removal of substituent not possible.");
            return;
        }
        while (true) {
            int n2 = 0;
            for (int i = this.null; i >= 1; --i) {
                if (this.M[i] > 0 && i > n2) {
                    n2 = i;
                }
            }
            if (n2 == 0) {
                break;
            }
            this.c(n2);
            this.M[n2] = 0;
        }
    }
    
    void h(final int n) {
        for (int i = 1; i <= this.F[n]; ++i) {
            final int n2 = this.z[n][i];
            if (this.B[n2] > 0) {
                final int[] b = this.B;
                final int n3 = n2;
                --b[n3];
            }
        }
    }
    
    void goto(final int n, final int n2) {
        if (this.B[n] > 0) {
            final int[] b = this.B;
            --b[n];
        }
        if (this.B[n2] > 0) {
            final int[] b2 = this.B;
            --b2[n2];
        }
    }
    
    void char(final int n) {
        if (this.F[n] < 2) {
            return;
        }
    }
    
    void for(final int n) {
        if (this.byte[n] == 1) {
            final int n2 = this.l[n];
            final int n3 = this.j[n];
            if (this.F[n2] < 2 && this.F[n3] < 2) {
                this.g[n] = 0;
                this.int.a("Stereomarking meaningless on this bond !");
                return;
            }
            switch (this.g[n]) {
                case 0: {
                    if (this.F[n3] <= this.F[n2]) {
                        this.g[n] = 1;
                        break;
                    }
                    this.g[n] = 3;
                    break;
                }
                case 1: {
                    this.g[n] = 2;
                    break;
                }
                case 2: {
                    if (this.F[n3] > 2) {
                        this.g[n] = 3;
                        break;
                    }
                    this.g[n] = 1;
                    break;
                }
                case 3: {
                    this.g[n] = 4;
                    break;
                }
                case 4: {
                    if (this.F[n2] > 2) {
                        this.g[n] = 1;
                        break;
                    }
                    this.g[n] = 3;
                    break;
                }
            }
        }
        else if (this.byte[n] == 2) {
            if (this.g[n] == 10) {
                this.g[n] = 0;
            }
            else {
                this.g[n] = 10;
            }
        }
        else {
            this.int.a("Stereomarking allowed only on single and double bonds!");
        }
    }
    
    void l() {
        this.j(0);
    }
    
    void j(final int n) {
        this.n();
        switch (this.F[this.void]) {
            case 0: {
                this.y[this.null] = this.y[this.void] + 21.65;
                this.x[this.null] = this.x[this.void] + 12.5;
                break;
            }
            case 1: {
                final int n2 = this.z[this.void][1];
                int n3 = 0;
                if (this.F[n2] == 2) {
                    if (this.z[n2][1] == this.void) {
                        n3 = this.z[n2][2];
                    }
                    else {
                        n3 = this.z[n2][1];
                    }
                }
                final double n4 = this.y[this.void] - this.y[n2];
                final double n5 = this.x[this.void] - this.x[n2];
                double sqrt = Math.sqrt(n4 * n4 + n5 * n5);
                if (sqrt < 0.001) {
                    sqrt = 0.001;
                }
                final double n6 = n5 / sqrt;
                final double n7 = n4 / sqrt;
                double n8 = sqrt + 25.0 * Math.cos(1.0471975511965976);
                double n9 = 25.0 * Math.sin(1.0471975511965976);
                final int for1 = this.for(this.void, n2);
                if (this.byte[for1] == 3 || this.int.al == 204 || (!this.a(for1) && (this.int.al == 203 || this.int.al == 204)) || this.H) {
                    n8 = sqrt + 25.0;
                    n9 = 0.0;
                }
                if (n3 > 0 && (this.x[n3] - this.x[n2]) * n7 - (this.y[n3] - this.y[n2]) * n6 > 0.0) {
                    n9 = -n9;
                }
                if (n > 0 && n9 < 0.0) {
                    n9 = -n9;
                }
                else if (n < 0 && n9 > 0.0) {
                    n9 = -n9;
                }
                this.y[this.null] = this.y[n2] + n8 * n7 - n9 * n6;
                this.x[this.null] = this.x[n2] + n9 * n7 + n8 * n6;
                break;
            }
            case 2: {
                final double[] array = new double[2];
                this.a(this.void, 25.0, array);
                this.y[this.null] = array[0];
                this.x[this.null] = array[1];
                break;
            }
            case 3:
            case 4:
            case 5: {
                for (int i = 1; i <= this.F[this.void]; ++i) {
                    final int n10 = this.z[this.void][i];
                    final double n11 = this.y[this.void] - this.y[n10];
                    final double n12 = this.x[this.void] - this.x[n10];
                    double sqrt2 = Math.sqrt(n11 * n11 + n12 * n12);
                    if (sqrt2 < 0.001) {
                        sqrt2 = 0.001;
                    }
                    this.y[this.null] = this.y[this.void] + 25.0 * n11 / sqrt2;
                    this.x[this.null] = this.x[this.void] + 25.0 * n12 / sqrt2;
                    if (this.do(this.null) == 0) {
                        break;
                    }
                    if (i == this.F[this.void]) {
                        break;
                    }
                }
                break;
            }
            default: {
                --this.null;
                this.int.a("Are you trying to draw an hedgehog ?");
                this.int.aM = 9;
                return;
            }
        }
        this.try();
        this.new = this.y[this.null];
        this.o = this.x[this.null];
    }
    
    void try() {
        this.F[this.null] = 1;
        final int[] f = this.F;
        final int void1 = this.void;
        ++f[void1];
        this.k();
        this.byte[this.J] = 1;
        if (this.int.al == 203) {
            this.byte[this.J] = 2;
        }
        if (this.int.al == 204) {
            this.byte[this.J] = 3;
        }
        this.l[this.J] = this.void;
        this.j[this.J] = this.null;
        if (this.int.al == 201) {
            this.for(this.J);
        }
        this.z[this.null][1] = this.void;
        this.z[this.void][this.F[this.void]] = this.null;
        this.A[this.J] = (int)Math.round((this.y[this.void] + this.y[this.null]) / 2.0);
        this.m[this.J] = (int)Math.round((this.x[this.void] + this.x[this.null]) / 2.0);
    }
    
    void byte() {
        final int do1 = this.do(this.null);
        if (do1 == 0) {
            return;
        }
        --this.null;
        for (int i = 1; i < this.J; ++i) {
            final int n = this.l[i];
            final int n2 = this.j[i];
            if ((n == do1 && n2 == this.long) || (n == this.long && n2 == do1)) {
                --this.J;
                final int[] f = this.F;
                final int long1 = this.long;
                --f[long1];
                if (this.byte[i] < 3) {
                    final int[] byte1 = this.byte;
                    final int n3 = i;
                    ++byte1[n3];
                    this.g[i] = 0;
                }
                else {
                    this.int.a("Maximum allowed bond order is 3 !");
                }
                return;
            }
        }
        if (this.F[do1] == 6) {
            --this.J;
            final int[] f2 = this.F;
            final int long2 = this.long;
            --f2[long2];
            this.int.a("Not possible connection !");
            return;
        }
        this.j[this.J] = do1;
        this.z[do1][++this.F[do1]] = this.long;
        this.z[this.long][this.F[this.long]] = do1;
        this.A[this.J] = (int)Math.round((this.y[this.long] + this.y[do1]) / 2.0);
        this.m[this.J] = (int)Math.round((this.x[this.long] + this.x[do1]) / 2.0);
    }
    
    void e() {
        this.long = this.void;
        if (this.int.al == 233 || this.int.al == 237 || this.int.al == 236 || this.int.al == 239) {
            this.l();
            this.void = this.null;
            this.H = true;
            this.l();
            this.H = false;
            this.void = this.null - 1;
            this.l();
            this.void = this.null - 2;
            this.l();
            if (this.int.al == 237) {
                this.else[this.null] = 10;
                this.else[this.null - 1] = 10;
                this.else[this.null - 2] = 10;
            }
            if (this.int.al == 236) {
                this.else[this.null] = 9;
                this.else[this.null - 1] = 9;
                this.else[this.null - 2] = 9;
            }
            if (this.int.al == 239) {
                this.else[this.null] = 5;
                this.else[this.null - 1] = 5;
                this.else[this.null - 2] = 4;
                this.else[this.null - 3] = 8;
                this.byte[this.J] = 2;
                this.byte[this.J - 1] = 2;
            }
        }
        else if (this.int.al == 244) {
            this.l();
            this.else[this.null] = 4;
            this.void = this.null;
            this.l();
            this.else[this.null] = 8;
            this.void = this.null;
            this.H = true;
            this.l();
            this.H = false;
            this.void = this.null - 1;
            this.l();
            this.else[this.null] = 5;
            this.byte[this.J] = 2;
            this.void = this.null - 2;
            this.l();
            this.else[this.null] = 5;
            this.byte[this.J] = 2;
        }
        else if (this.int.al == 234) {
            this.l();
            this.else[this.null] = 4;
            this.void = this.null;
            this.l();
            this.else[this.null] = 5;
            this.byte[this.J] = 2;
            this.void = this.null - 1;
            this.l();
            this.else[this.null] = 5;
            this.byte[this.J] = 2;
        }
        else if (this.int.al == 235) {
            this.l();
            this.void = this.null;
            this.l();
            this.else[this.null] = 5;
            this.void = this.null - 1;
            this.l();
            this.else[this.null] = 5;
            this.byte[this.J] = 2;
        }
        else if (this.int.al == 240) {
            this.l();
            this.void = this.null;
            this.l();
            this.else[this.null] = 5;
            this.void = this.null;
            this.l();
            this.void = this.null - 2;
            this.l();
            this.else[this.null] = 5;
            this.byte[this.J] = 2;
        }
        else if (this.int.al == 241) {
            this.l();
            this.else[this.null] = 5;
            this.void = this.null;
            this.l();
            this.void = this.null;
            this.l();
            this.void = this.null - 1;
            this.l();
            this.byte[this.J] = 2;
            this.else[this.null] = 5;
        }
        else if (this.int.al == 243) {
            this.l();
            this.else[this.null] = 4;
            this.void = this.null;
            this.l();
            this.void = this.null - 1;
            this.l();
        }
        else if (this.int.al == 238) {
            this.l();
            this.void = this.null;
            this.H = true;
            this.l();
            this.byte[this.J] = 3;
            this.H = false;
        }
        else if (this.int.al == 245) {
            this.l();
            this.void = this.null;
            this.H = true;
            this.l();
            this.void = this.null;
            this.byte[this.J] = 3;
            this.l();
            this.H = false;
        }
        else if (this.int.al == 242) {
            this.l();
            this.void = this.null;
            this.H = true;
            this.l();
            this.byte[this.J] = 3;
            this.else[this.null] = 4;
            this.H = false;
        }
        this.long(4);
        this.void = this.long;
    }
    
    void do() {
        int void1 = -1;
        int n = 6;
        switch (this.int.al) {
            case 206: {
                n = 3;
                break;
            }
            case 207: {
                n = 4;
                break;
            }
            case 208:
            case 221: {
                n = 5;
                break;
            }
            case 209:
            case 210: {
                n = 6;
                break;
            }
            case 211: {
                n = 7;
                break;
            }
            case 212: {
                n = 8;
                break;
            }
            case 222: {
                n = 9;
                break;
            }
        }
        final double n2 = 6.283185307179586 / n;
        final double sqrt = Math.sqrt(312.5 / (1.0 - Math.cos(n2)));
        if (this.void > 0) {
            if (this.F[this.void] < 2) {
                this.a(n, n2, sqrt);
            }
            else if (!this.int.ba) {
                void1 = this.void;
                this.l();
                this.void = this.null;
                this.a(n, n2, sqrt);
            }
            else {
                if (this.int.al == 209 || this.int.al == 221) {
                    this.int.a("ERROR - cannot add aromatic spiro ring !");
                    this.int.aM = 9;
                    return;
                }
                for (int i = 1; i <= this.F[this.void]; ++i) {
                    final int n3 = this.byte[this.for(this.void, this.z[this.void][i])];
                    if (i > 2 || n3 != 1) {
                        this.int.a("ERROR - spiro ring not possible here !");
                        this.int.aM = 9;
                        return;
                    }
                }
                final double[] array = new double[2];
                this.a(this.void, sqrt, array);
                final double n4 = this.y[this.void] - array[0];
                final double n5 = this.x[this.void] - array[1];
                double sqrt2 = Math.sqrt(n4 * n4 + n5 * n5);
                if (sqrt2 < 0.001) {
                    sqrt2 = 0.001;
                }
                final double n6 = n5 / sqrt2;
                final double n7 = n4 / sqrt2;
                for (int j = 1; j <= n; ++j) {
                    this.n();
                    final double n8 = n2 * j + 1.5707963267948966;
                    this.y[this.null] = array[0] + sqrt * (Math.sin(n8) * n7 - Math.cos(n8) * n6);
                    this.x[this.null] = array[1] + sqrt * (Math.cos(n8) * n7 + Math.sin(n8) * n6);
                }
            }
        }
        else if (this.s > 0) {
            int n9 = this.l[this.s];
            int n10 = this.j[this.s];
            int n11 = 0;
            if (this.F[n9] == 2) {
                if (this.z[n9][1] != n10) {
                    n11 = this.z[n9][1];
                }
                else {
                    n11 = this.z[n9][2];
                }
            }
            else if (this.F[n10] == 2) {
                if (this.z[n10][1] != n9) {
                    n11 = this.z[n10][1];
                }
                else {
                    n11 = this.z[n10][2];
                }
                final int n12 = n9;
                n9 = n10;
                n10 = n12;
            }
            if (n11 == 0) {
                if (this.z[n9][1] != n10) {
                    n11 = this.z[n9][1];
                }
                else {
                    n11 = this.z[n9][2];
                }
            }
            final double n13 = this.y[n10] - this.y[n9];
            final double n14 = this.x[n10] - this.x[n9];
            double sqrt3 = Math.sqrt(n13 * n13 + n14 * n14);
            if (sqrt3 < 0.001) {
                sqrt3 = 0.001;
            }
            final double n15 = n14 / sqrt3;
            final double n16 = n13 / sqrt3;
            final double n17 = sqrt3 / 2.0;
            double n18 = sqrt * Math.sin((3.141592653589793 - n2) * 0.5);
            boolean b = true;
            if ((this.x[n11] - this.x[n9]) * n16 - (this.y[n11] - this.y[n9]) * n15 > 0.0) {
                n18 = -n18;
                b = false;
            }
            final double n19 = this.y[n9] + n17 * n16 - n18 * n15;
            final double n20 = this.x[n9] + n18 * n16 + n17 * n15;
            for (int k = 1; k <= n; ++k) {
                this.n();
                final double n21 = n2 * (k + 0.5) + 3.141592653589793 * (b ? 1 : 0);
                this.y[this.null] = n19 + sqrt * (Math.sin(n21) * n16 - Math.cos(n21) * n15);
                this.x[this.null] = n20 + sqrt * (Math.cos(n21) * n16 + Math.sin(n21) * n15);
                if (b) {
                    if (k == n) {
                        this.y[this.null] = this.y[n9];
                        this.x[this.null] = this.x[n9];
                    }
                    if (k == n - 1) {
                        this.y[this.null] = this.y[n10];
                        this.x[this.null] = this.x[n10];
                    }
                }
                else {
                    if (k == n - 1) {
                        this.y[this.null] = this.y[n9];
                        this.x[this.null] = this.x[n9];
                    }
                    if (k == n) {
                        this.y[this.null] = this.y[n10];
                        this.x[this.null] = this.x[n10];
                    }
                }
            }
        }
        else {
            double n22 = 0.5;
            if (n == 6) {
                n22 = 0.0;
            }
            for (int l = 1; l <= n; ++l) {
                this.n();
                final double n23 = n2 * (l - n22);
                this.y[this.null] = this.new + sqrt * Math.sin(n23);
                this.x[this.null] = this.o + sqrt * Math.cos(n23);
            }
        }
        this.d(n);
        this.int(n);
        if (void1 > -1) {
            this.void = void1;
        }
    }
    
    void a(final int n, final double n2, final double n3) {
        double n4;
        double n5;
        if (this.F[this.void] == 0) {
            n4 = 0.0;
            n5 = 1.0;
        }
        else {
            final int n6 = this.z[this.void][1];
            final double n7 = this.y[this.void] - this.y[n6];
            final double n8 = this.x[this.void] - this.x[n6];
            double sqrt = Math.sqrt(n7 * n7 + n8 * n8);
            if (sqrt < 0.001) {
                sqrt = 0.001;
            }
            n4 = n8 / sqrt;
            n5 = n7 / sqrt;
        }
        final double n9 = this.y[this.void] + n3 * n5;
        final double n10 = this.x[this.void] + n3 * n4;
        for (int i = 1; i <= n; ++i) {
            this.n();
            final double n11 = n2 * i - 1.5707963267948966;
            this.y[this.null] = n9 + n3 * (Math.sin(n11) * n5 - Math.cos(n11) * n4);
            this.x[this.null] = n10 + n3 * (Math.cos(n11) * n5 + Math.sin(n11) * n4);
        }
    }
    
    void d(final int n) {
        int n2 = 0;
        for (int i = 1; i <= n; ++i) {
            this.k();
            this.byte[this.J] = 1;
            n2 = this.null - n + i;
            this.F[n2] = 2;
            this.l[this.J] = n2;
            this.j[this.J] = n2 + 1;
        }
        this.j[this.J] = this.null - n + 1;
        if (this.int.al == 209) {
            this.byte[this.J - 4] = 2;
            this.byte[this.J - 2] = 2;
            this.byte[this.J - 0] = 2;
            if (this.s > 0) {
                if (this.a(this.s)) {
                    int n3 = 0;
                    if (this.F[this.l[this.s]] > 1) {
                        n3 = this.z[this.l[this.s]][1];
                        n2 = this.l[this.s];
                        if (n3 == this.j[this.s]) {
                            n3 = this.z[this.l[this.s]][2];
                        }
                    }
                    if (n3 == 0 && this.F[this.j[this.s]] > 1) {
                        n3 = this.z[this.j[this.s]][1];
                        n2 = this.j[this.s];
                        if (n3 == this.j[this.s]) {
                            n3 = this.z[this.j[this.s]][2];
                        }
                    }
                    if (n3 > 0) {
                        int j = 1;
                        while (j <= this.J) {
                            if ((this.l[j] == n3 && this.j[j] == n2) || (this.l[j] == n2 && this.j[j] == n3)) {
                                if (!this.a(j)) {
                                    this.byte[this.J - 4] = 1;
                                    this.byte[this.J - 2] = 1;
                                    this.byte[this.J - 0] = 1;
                                    this.byte[this.J - 5] = 2;
                                    this.byte[this.J - 3] = 2;
                                    this.byte[this.J - 1] = 3;
                                    break;
                                }
                                break;
                            }
                            else {
                                ++j;
                            }
                        }
                    }
                }
                else {
                    this.byte[this.J - 4] = 1;
                    this.byte[this.J - 2] = 1;
                    this.byte[this.J - 0] = 1;
                    this.byte[this.J - 5] = 2;
                    this.byte[this.J - 3] = 2;
                    this.byte[this.J - 1] = 2;
                }
            }
        }
        else if (this.int.al == 221) {
            if (this.s > 0) {
                this.byte[this.s] = 2;
                this.byte[this.J - 4] = 2;
                this.else[this.null - 2] = 5;
            }
            else if (this.void > 0) {
                this.byte[this.J - 4] = 1;
                this.byte[this.J - 2] = 1;
                this.byte[this.J - 1] = 1;
                this.byte[this.J - 3] = 2;
                this.byte[this.J - 0] = 2;
                this.else[this.null - 1] = 5;
            }
            else {
                this.byte[this.J - 3] = 1;
                this.byte[this.J - 2] = 1;
                this.byte[this.J - 0] = 1;
                this.byte[this.J - 4] = 2;
                this.byte[this.J - 1] = 2;
                this.else[this.null - 2] = 5;
            }
        }
    }
    
    void int(final int n) {
        final int[] array = new int[this.null + 1];
        for (int i = 1; i <= n; ++i) {
            final int n2 = this.null - n + i;
            final int n3 = this.J - n + i;
            this.z[n2][1] = n2 - 1;
            this.z[n2][2] = n2 + 1;
            final int n4 = this.l[n3];
            final int n5 = this.j[n3];
            this.A[n3] = (int)Math.round((this.y[n4] + this.y[n5]) / 2.0);
            this.m[n3] = (int)Math.round((this.x[n4] + this.x[n5]) / 2.0);
        }
        this.z[this.null - n + 1][1] = this.null;
        this.z[this.null][2] = this.null - n + 1;
        for (int j = this.null - n + 1; j <= this.null; ++j) {
            array[j] = 0;
            double n6 = 51.0;
            int n7 = 0;
            for (int k = 1; k <= this.null - n; ++k) {
                final double n8 = this.y[j] - this.y[k];
                final double n9 = this.x[j] - this.x[k];
                final double n10 = n8 * n8 + n9 * n9;
                if (n10 < 50.0 && n10 < n6) {
                    n6 = n10;
                    n7 = k;
                }
            }
            if (n7 > 0 && (this.void == 0 || n7 == this.void)) {
                array[j] = n7;
            }
        }
    Label_1061:
        for (int n11 = this.J - n, l = n11 + 1; l <= n11 + n; ++l) {
            final int n12 = this.l[l];
            final int n13 = this.j[l];
            if (array[n12] > 0 && array[n13] > 0) {
                for (int n14 = 1; n14 <= n11; ++n14) {
                    if (this.l[n14] == array[n12] && this.j[n14] == array[n13]) {
                        continue Label_1061;
                    }
                    if (this.j[n14] == array[n12] && this.l[n14] == array[n13]) {
                        continue Label_1061;
                    }
                }
                this.k();
                this.byte[this.J] = this.byte[l];
                this.l[this.J] = array[n12];
                this.z[array[n12]][++this.F[array[n12]]] = array[n13];
                this.j[this.J] = array[n13];
                this.z[array[n13]][++this.F[array[n13]]] = array[n12];
                this.A[this.J] = (int)Math.round((this.y[this.l[this.J]] + this.y[this.j[this.J]]) / 2.0);
                this.m[this.J] = (int)Math.round((this.x[this.l[this.J]] + this.x[this.j[this.J]]) / 2.0);
            }
            else if (array[n12] > 0) {
                this.k();
                this.byte[this.J] = this.byte[l];
                this.l[this.J] = array[n12];
                this.z[array[n12]][++this.F[array[n12]]] = n13;
                this.j[this.J] = n13;
                this.z[n13][++this.F[n13]] = array[n12];
                this.A[this.J] = (int)Math.round((this.y[this.l[this.J]] + this.y[this.j[this.J]]) / 2.0);
                this.m[this.J] = (int)Math.round((this.x[this.l[this.J]] + this.x[this.j[this.J]]) / 2.0);
            }
            else if (array[n13] > 0) {
                this.k();
                this.byte[this.J] = this.byte[l];
                this.l[this.J] = array[n13];
                this.z[array[n13]][++this.F[array[n13]]] = n12;
                this.j[this.J] = n12;
                this.z[n12][++this.F[n12]] = array[n13];
                this.A[this.J] = (int)Math.round((this.y[this.l[this.J]] + this.y[this.j[this.J]]) / 2.0);
                this.m[this.J] = (int)Math.round((this.x[this.l[this.J]] + this.x[this.j[this.J]]) / 2.0);
            }
        }
        for (int n15 = this.null - n, null = this.null; null > n15; --null) {
            if (array[null] > 0) {
                this.c(null);
            }
        }
        if (this.void > 0) {
            this.long(n);
        }
    }
    
    private void a(final int n, final double n2, final double[] array) {
        final int n3 = this.z[n][1];
        final int n4 = this.z[n][2];
        final double n5 = this.y[n4] - this.y[n3];
        final double n6 = -(this.x[n4] - this.x[n3]);
        double sqrt = Math.sqrt(n5 * n5 + n6 * n6);
        if (sqrt < 0.001) {
            sqrt = 0.001;
        }
        if (Math.abs((this.x[n] - this.x[n3]) * (n5 / sqrt) + (this.y[n] - this.y[n3]) * (n6 / sqrt)) < 1.0) {
            final double n7 = this.y[n] - this.y[n3];
            final double n8 = this.x[n] - this.x[n3];
            double sqrt2 = Math.sqrt(n7 * n7 + n8 * n8);
            if (sqrt2 < 0.001) {
                sqrt2 = 0.001;
            }
            final double n9 = sqrt2;
            final double n10 = n8 / sqrt2;
            final double n11 = n7 / sqrt2;
            array[0] = this.y[n3] + n9 * n11 - n2 * n10;
            array[1] = this.x[n3] + n2 * n11 + n9 * n10;
        }
        else {
            final double n12 = (this.y[n3] + this.y[n4]) / 2.0;
            final double n13 = (this.x[n3] + this.x[n4]) / 2.0;
            final double n14 = this.y[n] - n12;
            final double n15 = this.x[n] - n13;
            double sqrt3 = Math.sqrt(n14 * n14 + n15 * n15);
            if (sqrt3 < 0.001) {
                sqrt3 = 0.001;
            }
            array[0] = this.y[n] + n2 * n14 / sqrt3;
            array[1] = this.x[n] + n2 * n15 / sqrt3;
        }
    }
    
    void n() {
        ++this.null;
        if (this.null > this.else.length - 1) {
            final int n = this.else.length + 10;
            final int[] else1 = new int[n];
            System.arraycopy(this.else, 0, else1, 0, this.else.length);
            this.else = else1;
            final int[] b = new int[n];
            System.arraycopy(this.B, 0, b, 0, this.B.length);
            this.B = b;
            final int[] a = new int[n];
            System.arraycopy(this.a, 0, a, 0, this.a.length);
            this.a = a;
            final int[] v = new int[n];
            System.arraycopy(this.v, 0, v, 0, this.v.length);
            this.v = v;
            final String[] u = new String[n];
            System.arraycopy(this.u, 0, u, 0, this.u.length);
            this.u = u;
            final String[] c = new String[n];
            System.arraycopy(this.C, 0, c, 0, this.C.length);
            this.C = c;
            final double[] y = new double[n];
            System.arraycopy(this.y, 0, y, 0, this.y.length);
            this.y = y;
            final double[] x = new double[n];
            System.arraycopy(this.x, 0, x, 0, this.x.length);
            this.x = x;
            final int[][] z = new int[n][7];
            System.arraycopy(this.z, 0, z, 0, this.z.length);
            this.z = z;
            final int[] f = new int[n];
            System.arraycopy(this.F, 0, f, 0, this.F.length);
            this.F = f;
        }
        this.else[this.null] = 3;
        this.B[this.null] = 0;
        this.v[this.null] = 0;
        this.u[this.null] = null;
        this.a[this.null] = 0;
    }
    
    void if(final String s) {
        this.n();
        this.a(this.null, s);
    }
    
    void a(final int void1, String s) {
        if (s.startsWith("[") && s.endsWith("]")) {
            s = s.substring(1, s.length() - 1);
            this.else[void1] = 18;
            this.C[void1] = s;
            this.a[void1] = 0;
            return;
        }
        if (s.length() < 1) {
            System.err.println("Error - null atom !");
        }
        boolean b = false;
        if (s.indexOf(",") > -1) {
            b = true;
        }
        if (s.indexOf(";") > -1) {
            b = true;
        }
        if (s.indexOf("#") > -1) {
            b = true;
        }
        if (s.indexOf("!") > -1) {
            b = true;
        }
        final int index = s.indexOf(":");
        int index2 = s.indexOf("H");
        int max = Math.max(s.indexOf("+"), s.indexOf("-"));
        if (index > -1) {
            final String substring = s.substring(index + 1);
            this.void = void1;
            a.p = Integer.valueOf(substring) - 1;
            this.b();
            s = s.substring(0, index);
            this.void = 0;
        }
        if (b) {
            this.C[void1] = s;
            this.else[void1] = 18;
            this.a[void1] = 0;
        }
        else {
            String s2 = s;
            if (index2 > 0) {
                s2 = s.substring(0, index2);
            }
            else if (max > 0) {
                s2 = s.substring(0, max);
            }
            this.else[void1] = for(s2);
            if (this.else[void1] == 18) {
                this.C[void1] = s2;
            }
            s += " ";
            int n = 0;
            if (index2 > 0) {
                n = 1;
                final char char1 = s.charAt(++index2);
                if (char1 >= '0' && char1 <= '9') {
                    n = char1 - '0';
                }
            }
            if (this.else[void1] == 18) {
                this.a[void1] = n;
            }
            int n2 = 0;
            if (max > 0) {
                final char char2 = s.charAt(max++);
                if (char2 == '+') {
                    n2 = 1;
                }
                else if (char2 == '-') {
                    n2 = -1;
                }
                if (n2 != 0) {
                    char c = s.charAt(max++);
                    if (c >= '0' && c <= '9') {
                        final char c2 = (char)(c * (c - '0'));
                    }
                    else {
                        while (c == '+') {
                            ++n2;
                            c = s.charAt(max++);
                        }
                        while (c == '-') {
                            --n2;
                            c = s.charAt(max++);
                        }
                    }
                }
            }
            this.B[void1] = n2;
        }
    }
    
    void if(final int n, final int n2) {
        if (this.else[n] == 18) {
            final StringBuffer sb = new StringBuffer();
            final String[] c = this.C;
            c[n] = sb.append(c[n]).append("H").toString();
            if (n2 > 1) {
                final StringBuffer sb2 = new StringBuffer();
                final String[] c2 = this.C;
                c2[n] = sb2.append(c2[n]).append(n2).toString();
            }
        }
    }
    
    void a(final int n, final int n2) {
        this.B[n] = n2;
    }
    
    void a(final String s, final boolean b) {
        this.r = 1;
        if (b) {
            this.r = -1;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        try {
            while (stringTokenizer.hasMoreTokens()) {
                this.char(Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()));
            }
        }
        catch (Exception ex) {
            System.err.println("Error in atom coloring");
            ex.printStackTrace();
        }
    }
    
    public void char(final int n, int n2) {
        if (n2 < 0 || n2 > 6) {
            n2 = 0;
        }
        this.v[n] = n2;
    }
    
    void k() {
        ++this.J;
        if (this.J > this.byte.length - 1) {
            final int n = this.byte.length + 10;
            final int[] l = new int[n];
            System.arraycopy(this.l, 0, l, 0, this.l.length);
            this.l = l;
            final int[] j = new int[n];
            System.arraycopy(this.j, 0, j, 0, this.j.length);
            this.j = j;
            final int[] byte1 = new int[n];
            System.arraycopy(this.byte, 0, byte1, 0, this.byte.length);
            this.byte = byte1;
            final int[] g = new int[n];
            System.arraycopy(this.g, 0, g, 0, this.g.length);
            this.g = g;
            final int[] a = new int[n];
            System.arraycopy(this.A, 0, a, 0, this.A.length);
            this.A = a;
            final int[] m = new int[n];
            System.arraycopy(this.m, 0, m, 0, this.m.length);
            this.m = m;
            final String[] do1 = new String[n];
            System.arraycopy(this.do, 0, do1, 0, this.do.length);
            this.do = do1;
        }
        this.byte[this.J] = 1;
        this.g[this.J] = 0;
        this.do[this.J] = null;
    }
    
    void new() {
        for (int i = 1; i <= this.J; ++i) {
            final int n = this.l[i];
            final int n2 = this.j[i];
            this.A[i] = (int)Math.round((this.y[n] + this.y[n2]) / 2.0);
            this.m[i] = (int)Math.round((this.x[n] + this.x[n2]) / 2.0);
        }
    }
    
    void d() {
        (this.int.s = new a(this)).a();
        this.int.h = this.int.a0;
    }
    
    boolean do(final int n, final int n2) {
        int n3 = 1;
        this.M = new int[this.null + 1];
        for (int i = 1; i <= this.null; ++i) {
            this.M[i] = 0;
        }
        this.M[n] = n3;
        for (int j = 1; j <= this.F[n]; ++j) {
            if (this.z[n][j] != n2) {
                this.M[this.z[n][j]] = ++n3;
            }
        }
        boolean b = false;
        do {
            for (int k = 1; k <= this.null; ++k) {
                b = false;
                if (this.M[k] > 0 && k != n) {
                    for (int l = 1; l <= this.F[k]; ++l) {
                        if (this.M[this.z[k][l]] == 0) {
                            this.M[this.z[k][l]] = ++n3;
                            b = true;
                        }
                    }
                }
                if (b) {
                    break;
                }
            }
        } while (b);
        return this.M[n2] == 0;
    }
    
    void a(final boolean[] array) {
        for (int i = 1; i <= this.J; ++i) {
            if (this.do(this.l[i], this.j[i])) {
                array[i] = false;
            }
            else {
                array[i] = true;
            }
        }
    }
    
    boolean a(final int n, final boolean[] array) {
        for (int i = 1; i <= this.F[n]; ++i) {
            if (array[this.for(n, this.z[n][i])]) {
                return true;
            }
        }
        return false;
    }
    
    void a(final boolean[] array, final boolean[] array2) {
        this.char = new int[this.J + 1];
        final boolean[] array3 = new boolean[this.null + 1];
        for (int i = 1; i <= this.null; ++i) {
            array[i] = (array3[i] = false);
            if (this.a(i, array2)) {
                if (this.F[i] + this.a[i] <= 3) {
                    switch (this.else[i]) {
                        case 3:
                        case 4:
                        case 5:
                        case 7:
                        case 8:
                        case 13: {
                            array3[i] = true;
                            break;
                        }
                        case 18: {
                            array3[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        for (int j = 1; j <= this.J; ++j) {
            if (this.a(j)) {
                this.char[j] = 1;
            }
            else if (this.if(j)) {
                this.char[j] = 2;
            }
            else if (this.byte[j] == 3) {
                this.char[j] = 3;
            }
            else {
                System.err.println("problems in findAromatic");
            }
        }
    Label_0534:
        for (int k = 1; k <= this.J; ++k) {
            if (array2[k]) {
                final int n = this.l[k];
                final int n2 = this.j[k];
                if (array3[n]) {
                    if (array3[n2]) {
                        final boolean[] array4 = new boolean[this.null + 1];
                        for (int l = 1; l <= this.F[n]; ++l) {
                            final int n3 = this.z[n][l];
                            if (n3 != n2 && array3[n3]) {
                                array4[n3] = true;
                            }
                        }
                        boolean b = false;
                        do {
                            for (int n4 = 1; n4 <= this.null; ++n4) {
                                b = false;
                                if (array4[n4] && array3[n4] && n4 != n) {
                                    for (int n5 = 1; n5 <= this.F[n4]; ++n5) {
                                        final int n6 = this.z[n4][n5];
                                        if (n6 == n2) {
                                            array[n2] = (array[n] = true);
                                            this.char[k] = 5;
                                            continue Label_0534;
                                        }
                                        if (!array4[n6] && array3[n6]) {
                                            array4[n6] = true;
                                            b = true;
                                        }
                                    }
                                }
                                if (b) {
                                    break;
                                }
                            }
                        } while (b);
                    }
                }
            }
        }
    }
    
    void i() {
        final int[] array = new int[this.null + 1];
        final int[] array2 = new int[this.null + 1];
        final long[] array3 = new long[this.null + 1];
        final long[] array4 = new long[this.null + 2];
        final long[] f = f(this.null);
        for (int i = 1; i <= this.null; ++i) {
            int n = 1;
            for (int j = 1; j <= this.J; ++j) {
                if (this.l[j] == i || this.j[j] == i) {
                    n *= this.char[j];
                }
            }
            int n2 = this.else[i];
            if (n2 == 18) {
                final String s = this.C[i];
                int n3 = s.charAt(0) - 'A' + '\u0001';
                int n4 = 0;
                if (s.length() > 1) {
                    n4 = s.charAt(1) - 'a';
                }
                if (n3 < 0) {
                    n3 = 0;
                }
                if (n4 < 0) {
                    n4 = 0;
                }
                n2 = n3 * 28 + n4;
            }
            int n5 = 0;
            if (this.B[i] < -2) {
                n5 = 1;
            }
            else if (this.B[i] == -2) {
                n5 = 2;
            }
            else if (this.B[i] == -1) {
                n5 = 3;
            }
            else if (this.B[i] == 1) {
                n5 = 4;
            }
            else if (this.B[i] == 2) {
                n5 = 5;
            }
            else if (this.B[i] > 2) {
                n5 = 6;
            }
            final int n6 = 1;
            array3[i] = n;
            final int n7 = n6 * 126;
            final long[] array5 = array3;
            final int n8 = i;
            array5[n8] += this.a[i] * n7;
            final int n9 = n7 * 7;
            final long[] array6 = array3;
            final int n10 = i;
            array6[n10] += n5 * n9;
            final int n11 = n9 * 7;
            final long[] array7 = array3;
            final int n12 = i;
            array7[n12] += n2 * n11;
            final int n13 = n11 * 783;
            final long[] array8 = array3;
            final int n14 = i;
            array8[n14] += this.F[i] * n13;
        }
        int n15 = 0;
        while (!this.a(array, array3)) {
            boolean b = false;
            for (int k = 1; k <= this.null; ++k) {
                if (array[k] != array2[k]) {
                    array2[k] = array[k];
                    b = true;
                }
            }
            Label_0743: {
                if (b) {
                    for (int l = 1; l <= this.null; ++l) {
                        array3[l] = 1L;
                        for (int n16 = 1; n16 <= this.F[l]; ++n16) {
                            final long[] array9 = array3;
                            final int n17 = l;
                            array9[n17] *= f[array[this.z[l][n16]]];
                        }
                    }
                    n15 = 0;
                }
                else if (n15 > 0) {
                    for (int n18 = 1; n18 <= this.null; ++n18) {
                        array3[n18] = 1L;
                    }
                    for (int n19 = 1; n19 <= this.null - 1; ++n19) {
                        for (int n20 = n19 + 1; n20 <= this.null; ++n20) {
                            if (array[n19] == array[n20]) {
                                array3[n19] = 2L;
                                break Label_0743;
                            }
                        }
                    }
                }
                else {
                    for (int n21 = 1; n21 <= this.null; ++n21) {
                        array3[n21] = 1L;
                        for (int n22 = 1; n22 <= this.F[n21]; ++n22) {
                            final int n23 = this.z[n21][n22];
                            final long[] array10 = array3;
                            final int n24 = n21;
                            array10[n24] *= this.else[n23] * this.char[this.for(n21, n23)];
                        }
                    }
                    n15 = 1;
                }
            }
            this.a(array, array3);
            for (int n25 = 1; n25 <= this.null; ++n25) {
                array3[n25] = array2[n25] * this.null + array[n25];
            }
        }
        for (int n26 = 1; n26 <= this.null; ++n26) {
            array2[n26] = array[n26];
        }
        for (int n27 = 1; n27 <= this.null; ++n27) {
            for (int n28 = 1; n28 <= this.null; ++n28) {
                if (array2[n28] == n27) {
                    this.else[0] = this.else[n28];
                    this.B[0] = this.B[n28];
                    this.y[0] = this.y[n28];
                    this.x[0] = this.x[n28];
                    this.F[0] = this.F[n28];
                    this.else[n28] = this.else[n27];
                    this.B[n28] = this.B[n27];
                    this.y[n28] = this.y[n27];
                    this.x[n28] = this.x[n27];
                    this.F[n28] = this.F[n27];
                    this.else[n27] = this.else[0];
                    this.B[n27] = this.B[0];
                    this.y[n27] = this.y[0];
                    this.x[n27] = this.x[0];
                    this.F[n27] = this.F[0];
                    array2[n28] = array2[n27];
                    array2[n27] = n27;
                    this.C[0] = this.C[n28];
                    this.C[n28] = this.C[n27];
                    this.C[n27] = this.C[0];
                    this.v[0] = this.v[n28];
                    this.v[n28] = this.v[n27];
                    this.v[n27] = this.v[0];
                    this.u[0] = this.u[n28];
                    this.u[n28] = this.u[n27];
                    this.u[n27] = this.u[0];
                    this.a[0] = this.a[n28];
                    this.a[n28] = this.a[n27];
                    this.a[n27] = this.a[0];
                    break;
                }
            }
        }
        for (int n29 = 1; n29 <= this.f; ++n29) {
            this.goto[n29][0] = array[this.goto[n29][0]];
        }
        for (int n30 = 1; n30 <= this.J; ++n30) {
            this.l[n30] = array[this.l[n30]];
            this.j[n30] = array[this.j[n30]];
            if (this.l[n30] > this.j[n30]) {
                final int n31 = this.l[n30];
                this.l[n30] = this.j[n30];
                this.j[n30] = n31;
                if (this.g[n30] == 1) {
                    this.g[n30] = 3;
                }
                else if (this.g[n30] == 2) {
                    this.g[n30] = 4;
                }
                else if (this.g[n30] == 3) {
                    this.g[n30] = 1;
                }
                else if (this.g[n30] == 4) {
                    this.g[n30] = 2;
                }
            }
        }
        for (int n32 = 1; n32 < this.J; ++n32) {
            int null = this.null;
            int null2 = this.null;
            int n33 = 0;
            for (int n34 = n32; n34 <= this.J; ++n34) {
                if (this.l[n34] < null) {
                    null = this.l[n34];
                    null2 = this.j[n34];
                    n33 = n34;
                }
                else if (this.l[n34] == null && this.j[n34] < null2) {
                    null2 = this.j[n34];
                    n33 = n34;
                }
            }
            final int n35 = this.l[n32];
            this.l[n32] = this.l[n33];
            this.l[n33] = n35;
            final int n36 = this.j[n32];
            this.j[n32] = this.j[n33];
            this.j[n33] = n36;
            final int n37 = this.byte[n32];
            this.byte[n32] = this.byte[n33];
            this.byte[n33] = n37;
            final int n38 = this.g[n32];
            this.g[n32] = this.g[n33];
            this.g[n33] = n38;
            final String s2 = this.do[n32];
            this.do[n32] = this.do[n33];
            this.do[n33] = s2;
        }
        this.a();
    }
    
    boolean a(final int[] array, final long[] array2) {
        long n = 0L;
        int n2 = 0;
        int i = 0;
        do {
            ++n2;
            for (int j = 1; j <= this.null; ++j) {
                if (array2[j] > 0L) {
                    n = array2[j];
                    break;
                }
            }
            for (int k = 1; k <= this.null; ++k) {
                if (array2[k] > 0L && array2[k] < n) {
                    n = array2[k];
                }
            }
            for (int l = 1; l <= this.null; ++l) {
                if (array2[l] == n) {
                    array[l] = n2;
                    array2[l] = 0L;
                    ++i;
                }
            }
        } while (i != this.null);
        return n2 == this.null;
    }
    
    void case() {
        for (int i = 1; i <= this.J; ++i) {
            final int n = this.l[i];
            final int n2 = this.j[i];
            if (this.else[n] == 3 || this.else[n2] == 3 || !this.int.n) {
                if (((this.B[n] == 1 && this.B[n2] == -1) || (this.B[n] == -1 && this.B[n2] == 1)) && (this.byte[i] == 1 || this.byte[i] == 2)) {
                    this.B[n] = 0;
                    this.B[n2] = 0;
                    final int[] byte1 = this.byte;
                    final int n3 = i;
                    ++byte1[n3];
                    this.c();
                }
                if (this.B[n] == 1 && this.B[n2] == 1) {
                    if (this.byte[i] == 2) {
                        this.byte[i] = 1;
                    }
                    else if (this.byte[i] == 3) {
                        this.byte[i] = 2;
                    }
                    this.c();
                }
            }
        }
    }
    
    void char() {
        final int length = this.else.length;
        this.z = new int[length][7];
        this.F = new int[length];
        for (int i = 1; i <= this.null; ++i) {
            this.F[i] = 0;
        }
        for (int j = 1; j <= this.J; ++j) {
            if (this.F[this.l[j]] < 6) {
                this.z[this.l[j]][++this.F[this.l[j]]] = this.j[j];
            }
            if (this.F[this.j[j]] < 6) {
                this.z[this.j[j]][++this.F[this.j[j]]] = this.l[j];
            }
        }
    }
    
    int a(final boolean b) {
        int n = 0;
        int i = 0;
        this.M = new int[this.null + 1];
        while (true) {
            for (int j = 1; j <= this.null; ++j) {
                if (this.M[j] == 0) {
                    this.M[j] = ++n;
                    i = 1;
                    break;
                }
            }
            if (i == 0) {
                break;
            }
            while (i != 0) {
                i = 0;
                for (int k = 1; k <= this.J; ++k) {
                    final int n2 = this.l[k];
                    final int n3 = this.j[k];
                    if (this.M[n2] > 0 && this.M[n3] == 0) {
                        this.M[n3] = n;
                        i = 1;
                    }
                    else if (this.M[n3] > 0 && this.M[n2] == 0) {
                        this.M[n2] = n;
                        i = 1;
                    }
                }
            }
        }
        if (n < 2 || !b) {
            return n;
        }
        final int[] array = new int[n + 1];
        for (int l = 1; l <= this.null; ++l) {
            final int[] array2 = array;
            final int n4 = this.M[l];
            ++array2[n4];
        }
        int n5 = 0;
        int n6 = 1;
        for (int n7 = 1; n7 <= n; ++n7) {
            if (array[n7] > n5) {
                n5 = array[n7];
                n6 = n7;
            }
        }
        for (int null = this.null; null >= 1; --null) {
            if (this.M[null] != n6) {
                this.c(null);
            }
        }
        this.void();
        this.int.a("Smaller part(s) removed !");
        return 1;
    }
    
    String else() {
        final int[] array = new int[this.null + 10];
        final int[] array2 = new int[this.null + 10];
        final int[] array3 = new int[this.null + 1];
        final int[] array4 = new int[7];
        final int[] array5 = new int[this.null + 1];
        final boolean[] array6 = new boolean[this.null + 1];
        final boolean[] array7 = new boolean[this.J + 1];
        final boolean[] array8 = new boolean[this.null + 1];
        int n = 0;
        if (this.null == 0) {
            return "";
        }
        this.a(true);
        boolean b = true;
        for (int i = 1; i <= this.J; ++i) {
            if (this.byte[i] == 9) {
                b = false;
                break;
            }
        }
        if (this.int.bC && b) {
            this.f();
            this.case();
            this.a(array7);
            this.a(array6, array7);
            this.i();
            this.c();
            this.a(array7);
            this.a(array6, array7);
        }
        else {
            this.a(array7);
            this.char = new int[this.J + 1];
            for (int j = 1; j <= this.J; ++j) {
                this.char[j] = this.byte[j];
            }
        }
        int n2 = 1;
        this.M = new int[this.null + 1];
        int n3 = 1;
        this.M[n2] = n3;
        int n4 = 0;
        while (true) {
            int n5 = 0;
            for (int k = 1; k <= this.F[n2]; ++k) {
                final int n6 = this.z[n2][k];
                if (this.M[n6] > 0) {
                    if (this.M[n6] <= this.M[n2]) {
                        if (n6 != array5[n2]) {
                            boolean b2 = true;
                            for (int l = 1; l <= n; ++l) {
                                if ((array[l] == n2 && array2[l] == n6) || (array[l] == n6 && array2[l] == n2)) {
                                    b2 = false;
                                    break;
                                }
                            }
                            if (b2) {
                                ++n;
                                array[n] = n2;
                                array2[n] = n6;
                            }
                        }
                    }
                }
                else {
                    array4[++n5] = n6;
                }
            }
            if (n5 == 0) {
                if (n3 == this.null) {
                    break;
                }
                n2 = array3[n4--];
            }
            else if (n5 == 1) {
                array5[array4[1]] = n2;
                n2 = array4[1];
                this.M[n2] = ++n3;
            }
            else {
                array3[++n4] = n2;
                int n7 = 0;
                for (int n8 = 1; n8 <= n5; ++n8) {
                    if (!array7[this.for(array4[n8], n2)]) {
                        n7 = array4[n8];
                        break;
                    }
                }
                if (n7 == 0) {
                    for (int n9 = 1; n9 <= n5; ++n9) {
                        final int for1 = this.for(array4[n9], n2);
                        if (this.char[for1] == 2 || this.char[for1] == 3) {
                            n7 = array4[n9];
                            break;
                        }
                    }
                }
                if (n7 == 0) {
                    n7 = array4[1];
                }
                array5[n7] = n2;
                n2 = n7;
                this.M[n2] = ++n3;
            }
        }
        final int[] array9 = new int[this.null + 1];
        final int[] array10 = new int[this.null + 1];
        final boolean[] array11 = new boolean[this.null + 1];
        final boolean[] array12 = new boolean[this.null + 1];
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        for (int n13 = 1; n13 <= this.null; ++n13) {
            if (this.M[n13] == 1) {
                n2 = n13;
                break;
            }
        }
    Block_29:
        while (true) {
            if (n12 > 0) {
                array9[n2] = n12;
            }
            array10[++n11] = n2;
            this.M[n2] = 0;
            while (true) {
                int n14 = 0;
                int n15 = 0;
                int n16 = this.null + 1;
                int n17 = 1;
            Label_0929_Outer:
                while (n17 <= this.F[n2]) {
                    final int n18 = this.z[n2][n17];
                    int n19 = 1;
                    while (true) {
                        while (n19 <= n) {
                            if (array[n19] != n18 || array2[n19] != n2) {
                                if (array[n19] != n2 || array2[n19] != n18) {
                                    ++n19;
                                    continue Label_0929_Outer;
                                }
                            }
                            ++n17;
                            continue Label_0929_Outer;
                        }
                        if (this.M[n18] <= 0) {
                            continue;
                        }
                        ++n15;
                        if (this.M[n18] < n16) {
                            n14 = n18;
                            n16 = this.M[n18];
                        }
                        continue;
                    }
                }
                if (n14 == 0) {
                    if (n10 == 0) {
                        break Block_29;
                    }
                    array12[n2] = true;
                    n2 = array3[n10--];
                }
                else {
                    n12 = n2;
                    n2 = n14;
                    if (n15 > 1) {
                        array3[++n10] = n12;
                        array11[n2] = true;
                        break;
                    }
                    break;
                }
            }
        }
        final int[] array13 = new int[this.J + 1];
        final int[] array14 = new int[this.null + 1];
        if (this.int.T) {
            this.a(array10, array9, array13, array14, array7, array, array2, n);
        }
        final StringBuffer sb = new StringBuffer("");
        final int[] array15 = new int[this.null + 1];
        for (int n20 = 1; n20 <= this.null; ++n20) {
            array15[array10[n20]] = n20;
        }
        for (int n21 = 1; n21 <= this.null; ++n21) {
            final int n22 = array10[n21];
            if (array11[n22]) {
                sb.append("(");
            }
            if (array9[n21] > 0) {
                this.a(n22, array9[n22], sb, array13);
            }
            this.a(n22, sb, array6[n22], array14);
            for (int n23 = 1; n23 <= n; ++n23) {
                if (array[n23] == n22 || array2[n23] == n22) {
                    int n24 = array2[n23];
                    if (n24 == n22) {
                        n24 = array[n23];
                    }
                    if (array15[n22] < array15[n24]) {
                        this.a(array[n23], array2[n23], sb, array13);
                    }
                    if (n23 > 9) {
                        sb.append("%");
                    }
                    sb.append(new Integer(n23).toString());
                }
            }
            if (array12[n22]) {
                sb.append(")");
            }
        }
        return sb.toString();
    }
    
    private void a(final int n, final StringBuffer sb, final boolean b, final int[] array) {
        String string = "X";
        boolean b2 = false;
        if (this.B[n] != 0) {
            b2 = true;
        }
        if (array[n] != 0) {
            b2 = true;
        }
        int n2 = -1;
        for (int i = 1; i <= this.f; ++i) {
            if (this.goto[i][0] == n) {
                n2 = this.goto[i][1];
                break;
            }
        }
        if (n2 > -1) {
            b2 = true;
        }
        switch (this.else[n]) {
            case 2: {
                string = "B";
                break;
            }
            case 3: {
                if (b) {
                    string = "c";
                    break;
                }
                string = "C";
                break;
            }
            case 4: {
                if (!b) {
                    string = "N";
                    break;
                }
                string = "n";
                if (this.a[n] > 0) {
                    b2 = true;
                    break;
                }
                break;
            }
            case 5: {
                if (b) {
                    string = "o";
                    break;
                }
                string = "O";
                break;
            }
            case 7: {
                if (!b) {
                    string = "P";
                    break;
                }
                string = "p";
                if (this.a[n] > 0) {
                    b2 = true;
                    break;
                }
                break;
            }
            case 8: {
                if (b) {
                    string = "s";
                    break;
                }
                string = "S";
                break;
            }
            case 13: {
                if (b) {
                    string = "se";
                }
                else {
                    string = "Se";
                }
                b2 = true;
                break;
            }
            case 6: {
                string = "Si";
                b2 = true;
                break;
            }
            case 9: {
                string = "F";
                break;
            }
            case 10: {
                string = "Cl";
                break;
            }
            case 11: {
                string = "Br";
                break;
            }
            case 12: {
                string = "I";
                break;
            }
            case 1: {
                string = "H";
                b2 = true;
                break;
            }
            case 19: {
                string = "R";
                b2 = true;
                break;
            }
            case 20: {
                string = "R1";
                b2 = true;
                break;
            }
            case 21: {
                string = "R2";
                b2 = true;
                break;
            }
            case 22: {
                string = "R3";
                b2 = true;
                break;
            }
            case 18: {
                b2 = true;
                string = this.C[n];
                if (string.equals("*") || string.equals("a") || string.equals("A")) {
                    b2 = false;
                    break;
                }
                break;
            }
        }
        if (b2) {
            String s = "[" + string;
            if (array[n] == 1) {
                s += "@";
            }
            else if (array[n] == -1) {
                s += "@@";
            }
            if (this.a[n] == 1) {
                s += "H";
            }
            else if (this.a[n] > 1) {
                s = s + "H" + this.a[n];
            }
            if (this.B[n] != 0) {
                if (this.B[n] > 0) {
                    s += "+";
                }
                else {
                    s += "-";
                }
                if (Math.abs(this.B[n]) > 1) {
                    s += Math.abs(this.B[n]);
                }
            }
            if (n2 > -1) {
                s = s + ":" + n2;
            }
            string = s + "]";
        }
        sb.append(string);
    }
    
    private void a(final int n, final int n2, final StringBuffer sb, final int[] array) {
        final int for1 = this.for(n, n2);
        if (this.char[for1] != 5 && this.if(for1)) {
            sb.append("=");
        }
        else if (this.byte[for1] == 3) {
            sb.append("#");
        }
        else if (this.byte[for1] == 9) {
            String s = "?";
            switch (this.g[for1]) {
                case 1: {
                    s = "~";
                    break;
                }
                case 2: {
                    s = ":";
                    break;
                }
                case 3: {
                    s = "@";
                    break;
                }
                case 4: {
                    s = "!@";
                    break;
                }
            }
            sb.append(s);
        }
        else if (array[for1] == 1) {
            sb.append("/");
        }
        else if (array[for1] == -1) {
            sb.append("\\");
        }
    }
    
    private void a(final int[] array, final int[] array2, final int[] array3, final int[] array4, final boolean[] array5, final int[] array6, final int[] array7, final int n) {
        final int[] array8 = new int[this.null + 1];
        for (int i = 1; i <= this.null; ++i) {
            array8[array[i]] = i;
        }
        final boolean[] array9 = new boolean[this.J + 1];
        for (int j = 1; j <= this.null; ++j) {
            final int n2 = array[j];
            final int for1 = this.for(n2, array2[n2]);
            if (for1 != 0) {
                this.a(for1, array8, array3, array5);
                array9[for1] = true;
            }
        }
        for (int k = 1; k <= this.J; ++k) {
            if (!array9[k]) {
                this.a(k, array8, array3, array5);
            }
        }
    Label_0340:
        for (int l = 1; l <= this.null; ++l) {
            if (this.F[l] >= 2) {
                if (this.F[l] <= 4) {
                    int n3 = 0;
                    int n4 = 0;
                    for (int n5 = 1; n5 <= this.F[l]; ++n5) {
                        final int for2 = this.for(l, this.z[l][n5]);
                        if (this.char[for2] == 5) {
                            continue Label_0340;
                        }
                        if (this.byte[for2] == 1 && this.int(for2, l) != 0) {
                            ++n3;
                        }
                        if (this.byte[for2] == 2) {
                            n4 = this.z[l][n5];
                        }
                    }
                    if (n3 != 0) {
                        if (n4 > 0) {
                            this.a(l, array8, array4, array2, array6, array7, n);
                        }
                        else {
                            this.if(l, array2, array8, array6, array7, n, array4);
                        }
                    }
                }
            }
        }
    }
    
    private void if(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n2, final int[] array5) {
        final int[] array6 = new int[4];
        final int[] array7 = new int[4];
        this.a(n, array2, array, array3, array4, n2, array6);
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        for (int i = 0; i < 4; ++i) {
            if (array6[i] > 0) {
                array7[i] = this.int(this.for(n, array6[i]), n);
                if (array7[i] > 0) {
                    ++n3;
                    n5 = array6[i];
                    n7 = array6[i];
                }
                else if (array7[i] < 0) {
                    ++n4;
                    n6 = array6[i];
                    n7 = array6[i];
                }
                else {
                    n8 = array6[i];
                }
            }
        }
        int n9 = n3 + n4;
        final int[] array8 = new int[4];
        int n10 = 0;
        if (this.F[n] == 3) {
            if ((n3 == 1 && n4 == 1) || (n9 == 3 && n3 > 0 && n4 > 0)) {
                this.int.a("Error in C3H stereospecification !");
                return;
            }
            int n11 = array6[0];
            if (n9 == 1) {
                n11 = n7;
            }
            else if (n9 == 2) {
                n11 = n8;
            }
            final int[] a = this.a(n, n11, array6);
            array8[0] = n7;
            array8[1] = -1;
            array8[2] = a[2];
            array8[3] = a[1];
            if (n3 > 0) {
                n10 = 1;
            }
            else {
                n10 = -1;
            }
        }
        else if (this.F[n] == 4) {
            if (n9 == 1) {
                final int[] a2 = this.a(n, n7, array6);
                array8[0] = a2[0];
                array8[1] = a2[3];
                array8[2] = a2[2];
                array8[3] = a2[1];
                if (n3 > 0) {
                    n10 = 1;
                }
                else {
                    n10 = -1;
                }
            }
            else {
                int n12 = array6[0];
                if (n8 > 1) {
                    n12 = n8;
                }
                if (n3 == 1) {
                    n12 = n5;
                }
                else if (n4 == 1) {
                    n12 = n6;
                }
                final int[] a3 = this.a(n, n12, array6);
                final int[] array9 = new int[4];
                for (int j = 0; j < 4; ++j) {
                    array9[j] = this.int(this.for(n, a3[j]), n);
                }
                if (n9 == 4) {
                    if (n3 == 0 || n4 == 0) {
                        this.int.a("Error in C4 stereospecification !");
                        return;
                    }
                    if (n3 == 1 || n4 == 1) {
                        array8[0] = a3[0];
                        array8[1] = a3[3];
                        array8[2] = a3[2];
                        array8[3] = a3[1];
                        n10 = array9[0];
                    }
                    else {
                        for (int k = 0; k < 4; ++k) {
                            if (array9[k] == -1) {
                                array9[k] = 0;
                            }
                        }
                        n9 = 2;
                    }
                }
                else if (n9 == 3) {
                    if (n3 == 3 || n4 == 3) {
                        array8[0] = a3[0];
                        array8[1] = a3[3];
                        array8[2] = a3[2];
                        array8[3] = a3[1];
                        if (n3 > 0) {
                            n10 = -1;
                        }
                        else {
                            n10 = 1;
                        }
                    }
                    else {
                        int n13;
                        if (n3 == 1) {
                            n13 = 1;
                            n3 = 1;
                        }
                        else {
                            n13 = -1;
                            n4 = -1;
                        }
                        for (int l = 0; l < 4; ++l) {
                            if (array9[l] == n13) {
                                array9[l] = 0;
                            }
                        }
                        n9 = 2;
                    }
                }
                if (n9 == 2) {
                    if (n3 == 1 && n4 == 1) {
                        if (a3[1] == n6) {
                            a3[1] = a3[2];
                            a3[2] = a3[3];
                        }
                        else if (a3[2] == n6) {
                            a3[2] = a3[3];
                        }
                        array8[0] = n5;
                        array8[1] = n6;
                        array8[2] = a3[2];
                        array8[3] = a3[1];
                        n10 = 1;
                    }
                    else {
                        if (array9[0] == array9[1] || array9[1] == array9[2]) {
                            this.int.a("Error in C4 stereospecification ! 2/0r");
                            return;
                        }
                        if (array9[0] != 0) {
                            array8[0] = a3[0];
                            array8[1] = a3[2];
                            array8[2] = a3[1];
                            array8[3] = a3[3];
                        }
                        else {
                            array8[0] = a3[1];
                            array8[1] = a3[3];
                            array8[2] = a3[2];
                            array8[3] = a3[0];
                        }
                        if (n3 > 1) {
                            n10 = 1;
                        }
                        else {
                            n10 = -1;
                        }
                    }
                }
            }
        }
        this.a(array8, array6);
        if (array8[2] == array6[2]) {
            array5[n] = 1;
        }
        else if (array8[2] == array6[3]) {
            array5[n] = -1;
        }
        else {
            this.int.a("Error in stereoprocessing ! - t30");
        }
        array5[n] *= n10;
    }
    
    private void a(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int n2, final int[] array5) {
        int n3 = -1;
        if (array2[n] > 0) {
            array5[++n3] = array2[n];
        }
        for (int i = 1; i <= n2; ++i) {
            if (array3[i] == n) {
                array5[++n3] = array4[i];
            }
            if (array4[i] == n) {
                array5[++n3] = array3[i];
            }
        }
        for (int j = n3 + 1; j < this.F[n]; ++j) {
            int n4 = this.null + 1;
            int k = 1;
        Label_0168:
            while (k <= this.F[n]) {
                final int n5 = this.z[n][k];
                while (true) {
                    for (int l = 0; l < j; ++l) {
                        if (n5 == array5[l]) {
                            ++k;
                            continue Label_0168;
                        }
                    }
                    if (array[n5] < n4) {
                        n4 = array[n5];
                        array5[j] = n5;
                    }
                    continue;
                }
            }
        }
        if (array2[n] == 0 && this.a[n] > 0) {
            array5[3] = array5[2];
            array5[2] = array5[1];
            array5[1] = array5[0];
            array5[0] = -1;
            System.out.println("stereowarning #7");
        }
        else if (this.a[n] > 0) {
            array5[3] = array5[2];
            array5[2] = array5[1];
            array5[1] = -1;
        }
    }
    
    int[] a(final int n, final int n2, final int[] array) {
        final int[] array2 = new int[4];
        final double n3 = this.y[n2] - this.y[n];
        final double n4 = this.x[n2] - this.x[n];
        double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
        if (sqrt < 0.001) {
            sqrt = 0.001;
        }
        final double n5 = n4 / sqrt;
        final double n6 = n3 / sqrt;
        final int[] array3 = new int[4];
        for (int i = 0; i < 4; ++i) {
            if (array[i] != n2) {
                if (array[i] > 0) {
                    if (array3[1] == 0) {
                        array3[1] = array[i];
                    }
                    else if (array3[2] == 0) {
                        array3[2] = array[i];
                    }
                    else if (array3[3] == 0) {
                        array3[3] = array[i];
                    }
                }
            }
        }
        final double[] array4 = new double[4];
        final double[] array5 = new double[4];
        for (int j = 1; j <= 3; ++j) {
            if (j != 3 || array3[3] != 0) {
                final double n7 = (this.y[array3[j]] - this.y[n]) * n6 + (this.x[array3[j]] - this.x[n]) * n5;
                final double n8 = (this.x[array3[j]] - this.x[n]) * n6 - (this.y[array3[j]] - this.y[n]) * n5;
                double sqrt2 = Math.sqrt(n7 * n7 + n8 * n8);
                if (sqrt2 < 0.001) {
                    sqrt2 = 0.001;
                }
                array4[j] = n8 / sqrt2;
                array5[j] = n7 / sqrt2;
            }
        }
        final int a = this.a(array4[1], array5[1], array4[2], array5[2]);
        if (array3[3] > 0) {
            final int a2 = this.a(array4[2], array5[2], array4[3], array5[3]);
            final int a3 = this.a(array4[1], array5[1], array4[3], array5[3]);
            if (a > 0 && a2 > 0) {
                array2[1] = array3[1];
                array2[2] = array3[2];
                array2[3] = array3[3];
            }
            else if (a3 > 0 && a2 < 0) {
                array2[1] = array3[1];
                array2[2] = array3[3];
                array2[3] = array3[2];
            }
            else if (a < 0 && a3 > 0) {
                array2[1] = array3[2];
                array2[2] = array3[1];
                array2[3] = array3[3];
            }
            else if (a2 > 0 && a3 < 0) {
                array2[1] = array3[2];
                array2[2] = array3[3];
                array2[3] = array3[1];
            }
            else if (a3 < 0 && a > 0) {
                array2[1] = array3[3];
                array2[2] = array3[1];
                array2[3] = array3[2];
            }
            else if (a2 < 0 && a < 0) {
                array2[1] = array3[3];
                array2[2] = array3[2];
                array2[3] = array3[1];
            }
        }
        else if (a > 0) {
            array2[1] = array3[1];
            array2[2] = array3[2];
        }
        else {
            array2[1] = array3[2];
            array2[2] = array3[1];
        }
        array2[0] = n2;
        return array2;
    }
    
    private void a(final int[] array, final int[] array2) {
        if (array2[0] == array[1]) {
            final int n = array[0];
            array[0] = array[1];
            array[1] = n;
            final int n2 = array[2];
            array[2] = array[3];
            array[3] = n2;
        }
        else if (array2[0] == array[2]) {
            final int n3 = array[2];
            array[2] = array[0];
            array[0] = n3;
            final int n4 = array[1];
            array[1] = array[3];
            array[3] = n4;
        }
        else if (array2[0] == array[3]) {
            final int n5 = array[3];
            array[3] = array[0];
            array[0] = n5;
            final int n6 = array[1];
            array[1] = array[2];
            array[2] = n6;
        }
        if (array2[1] == array[2]) {
            final int n7 = array[1];
            array[1] = array[2];
            array[2] = n7;
            final int n8 = array[2];
            array[2] = array[3];
            array[3] = n8;
        }
        else if (array2[1] == array[3]) {
            final int n9 = array[1];
            array[1] = array[3];
            array[3] = n9;
            final int n10 = array[2];
            array[2] = array[3];
            array[3] = n10;
        }
    }
    
    private void a(final int n, final int[] array, final int[] array2, final boolean[] array3) {
        if (this.byte[n] != 2 || this.char[n] == 5) {
            return;
        }
        if (this.g[n] != 10 && (!this.int.m || array3[n])) {
            return;
        }
        int n2 = this.l[n];
        int n3 = this.j[n];
        if (this.F[n2] < 2 || this.F[n3] < 2 || this.F[n2] > 3 || this.F[n3] > 3) {
            return;
        }
        if (array[n2] > array[n3]) {
            final int n4 = n2;
            n2 = n3;
            n3 = n4;
        }
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        boolean b = false;
        for (int i = 1; i <= this.F[n2]; ++i) {
            final int n8 = this.z[n2][i];
            if (n8 != n3) {
                if (n6 == 0) {
                    n6 = n8;
                }
                else {
                    n7 = n8;
                }
            }
        }
        if (n7 > 0 && array[n6] > array[n7]) {
            final int n9 = n6;
            n6 = n7;
            n7 = n9;
        }
        final int for1 = this.for(n2, n6);
        if (array2[for1] != 0) {
            n5 = n6;
        }
        else if (this.byte[for1] == 1 && this.char[for1] != 5) {
            n5 = n6;
        }
        if (n5 == 0) {
            final int for2 = this.for(n2, n7);
            if (array2[for2] != 0) {
                n5 = n7;
            }
            else if (this.byte[for2] == 1 && this.char[for2] != 5) {
                n5 = n7;
            }
        }
        if (array[n5] > array[n2]) {
            b = true;
        }
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        for (int j = 1; j <= this.F[n3]; ++j) {
            final int n13 = this.z[n3][j];
            if (n13 != n2) {
                if (n11 == 0) {
                    n11 = n13;
                }
                else {
                    n12 = n13;
                }
            }
        }
        if (n12 > 0 && array[n11] < array[n12]) {
            final int n14 = n11;
            n11 = n12;
            n12 = n14;
        }
        final int for3 = this.for(n3, n11);
        if (this.byte[for3] == 1 && this.char[for3] != 5 && array2[for3] == 0) {
            n10 = n11;
        }
        if (n10 == 0) {
            final int for4 = this.for(n3, n12);
            if (this.byte[for4] == 1 && this.char[for4] != 5) {
                n10 = n12;
            }
        }
        if (n5 == 0 || n10 == 0) {
            return;
        }
        final double n15 = this.y[n3] - this.y[n2];
        final double n16 = this.x[n3] - this.x[n2];
        double sqrt = Math.sqrt(n15 * n15 + n16 * n16);
        if (sqrt < 0.001) {
            sqrt = 0.001;
        }
        final double n17 = n16 / sqrt;
        final double n18 = n15 / sqrt;
        final double n19 = (this.x[n5] - this.x[n2]) * n18 - (this.y[n5] - this.y[n2]) * n17;
        final double n20 = (this.x[n10] - this.x[n2]) * n18 - (this.y[n10] - this.y[n2]) * n17;
        if (Math.abs(n19) < 2.0 || Math.abs(n20) < 2.0) {
            this.int.a("Not unique E/Z geometry !");
            return;
        }
        final int for5 = this.for(n5, n2);
        final int for6 = this.for(n10, n3);
        int n21 = 1;
        if (array2[for5] == 0) {
            for (int k = 1; k <= this.F[n5]; ++k) {
                final int n22 = this.z[n5][k];
                if (n22 != n2) {
                    final int for7 = this.for(n5, n22);
                    if (array2[for7] != 0) {
                        if (array[n22] > array[n5]) {
                            n21 = -array2[for7];
                            break;
                        }
                        n21 = array2[for7];
                        break;
                    }
                }
            }
            array2[for5] = n21;
        }
        if (array2[for6] != 0) {
            System.err.println("E/Z internal error !");
            return;
        }
        if ((n19 > 0.0 && n20 > 0.0) || (n19 < 0.0 && n20 < 0.0)) {
            array2[for6] = -array2[for5];
        }
        else {
            array2[for6] = array2[for5];
        }
        if (b) {
            array2[for6] = -array2[for6];
        }
    }
    
    private int a(final double n, final double n2, final double n3, final double n4) {
        int n5 = 0;
        int n6 = 0;
        if (n >= 0.0 && n2 >= 0.0) {
            n5 = 1;
        }
        else if (n >= 0.0 && n2 < 0.0) {
            n5 = 2;
        }
        else if (n < 0.0 && n2 < 0.0) {
            n5 = 3;
        }
        else if (n < 0.0 && n2 >= 0.0) {
            n5 = 4;
        }
        if (n3 >= 0.0 && n4 >= 0.0) {
            n6 = 1;
        }
        else if (n3 >= 0.0 && n4 < 0.0) {
            n6 = 2;
        }
        else if (n3 < 0.0 && n4 < 0.0) {
            n6 = 3;
        }
        else if (n3 < 0.0 && n4 >= 0.0) {
            n6 = 4;
        }
        if (n5 < n6) {
            return 1;
        }
        if (n5 > n6) {
            return -1;
        }
        switch (n5) {
            case 1:
            case 4: {
                if (n < n3) {
                    return 1;
                }
                return -1;
            }
            case 2:
            case 3: {
                if (n > n3) {
                    return 1;
                }
                return -1;
            }
            default: {
                System.err.println("stereowarning #31");
                return 0;
            }
        }
    }
    
    private int int(final int n, final int n2) {
        final int n3 = this.g[n];
        if (n3 < 1 || n3 > 4) {
            return 0;
        }
        if (n3 == 1 && this.l[n] == n2) {
            return 1;
        }
        if (n3 == 2 && this.l[n] == n2) {
            return -1;
        }
        if (n3 == 3 && this.j[n] == n2) {
            return 1;
        }
        if (n3 == 4 && this.j[n] == n2) {
            return -1;
        }
        return 0;
    }
    
    private void a(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int n2) {
        int n3 = 1;
        int n4 = n;
        final int[] array6 = new int[this.null + 1];
        array6[1] = n;
        boolean b;
        do {
            b = false;
            for (int i = 1; i <= this.F[n4]; ++i) {
                final int n5 = this.z[n4][i];
                if (n5 != array6[1]) {
                    if (n5 != array6[n3 - 1]) {
                        final int for1 = this.for(n4, n5);
                        if (this.byte[for1] == 2 && this.char[for1] != 5) {
                            array6[++n3] = n5;
                            n4 = n5;
                            b = true;
                            break;
                        }
                    }
                }
            }
        } while (b);
        if (n3 % 2 == 0) {
            return;
        }
        if (this.F[array6[n3]] < 2 || this.F[array6[n3]] > 3) {
            return;
        }
        final int n6 = array6[1];
        final int n7 = array6[(n3 + 1) / 2];
        final int n8 = array6[n3];
        int n9 = 0;
        int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        boolean b2 = false;
        boolean b3 = false;
        for (int j = 1; j <= this.F[n6]; ++j) {
            final int n13 = this.z[n6][j];
            final int for2 = this.for(n6, n13);
            if (this.byte[for2] == 1) {
                if (this.char[for2] != 5) {
                    if (n9 == 0) {
                        n9 = n13;
                    }
                    else {
                        n10 = n13;
                    }
                }
            }
        }
        if (array[n10] > 0 && array[n9] > array[n10]) {
            final int n14 = n9;
            n9 = n10;
            n10 = n14;
        }
        int n15 = n9;
        if (n15 == 0) {
            n15 = n10;
            b2 = true;
        }
        for (int k = 1; k <= this.F[n8]; ++k) {
            final int n16 = this.z[n8][k];
            final int for3 = this.for(n8, n16);
            if (this.byte[for3] == 1) {
                if (this.char[for3] != 5) {
                    if (n11 == 0) {
                        n11 = n16;
                    }
                    else {
                        n12 = n16;
                    }
                }
            }
        }
        if (array[n12] > 0 && array[n11] > array[n12]) {
            final int n17 = n11;
            n11 = n12;
            n12 = n17;
        }
        int n18 = n11;
        if (n18 == 0) {
            n18 = n12;
            b3 = true;
        }
        final int int1 = this.int(this.for(n6, n9), n6);
        final int int2 = this.int(this.for(n6, n10), n6);
        final int int3 = this.int(this.for(n8, n11), n8);
        final int int4 = this.int(this.for(n8, n12), n8);
        if (Math.abs(int1 + int2) > 1 || int3 != 0 || int4 != 0) {
            this.int.a("Bad stereoinfo on allene !");
            return;
        }
        final double n19 = this.y[array6[n3 - 1]] - this.y[n8];
        final double n20 = this.x[array6[n3 - 1]] - this.x[n8];
        double sqrt = Math.sqrt(n19 * n19 + n20 * n20);
        if (sqrt < 0.001) {
            sqrt = 0.001;
        }
        if ((this.x[n18] - this.x[array6[n3 - 1]]) * (n19 / sqrt) - (this.y[n18] - this.y[array6[n3 - 1]]) * (n20 / sqrt) > 0.0) {
            array2[n7] = 1;
        }
        else {
            array2[n7] = -1;
        }
        if (b2) {
            final int n21 = n7;
            array2[n21] *= -1;
        }
        if (b3) {
            final int n22 = n7;
            array2[n22] *= -1;
        }
        if (n15 == n9 && int1 < 0) {
            final int n23 = n7;
            array2[n23] *= -1;
        }
        if (n15 == n10 && int2 < 0) {
            final int n24 = n7;
            array2[n24] *= -1;
        }
        if (array[n15] > array[n18]) {
            final int n25 = n7;
            array2[n25] *= -1;
        }
    }
    
    String g() {
        String s = "" + this.null + " " + this.J;
        final double n = 0.055999999999999994;
        for (int i = 1; i <= this.null; ++i) {
            String s2 = this.byte(i);
            if (this.int.try && this.a[i] > 0) {
                s2 += "H";
                if (this.a[i] > 1) {
                    s2 += this.a[i];
                }
            }
            if (this.B[i] != 0) {
                if (this.B[i] > 0) {
                    s2 += "+";
                }
                else {
                    s2 += "-";
                }
                if (Math.abs(this.B[i]) > 1) {
                    s2 += Math.abs(this.B[i]);
                }
            }
            int n2 = -1;
            for (int j = 1; j <= this.f; ++j) {
                if (this.goto[j][0] == i) {
                    n2 = this.goto[j][1];
                    break;
                }
            }
            if (n2 > -1) {
                s2 = s2 + ":" + n2;
            }
            s = s + " " + s2 + " " + a(this.y[i] * n, 0, 2) + " " + a(-this.x[i] * n, 0, 2);
            if (this.int.aW) {
                String s3 = "*";
                if (this.u[i] != null && this.u[i].length() > 0) {
                    s3 = this.u[i];
                }
                s = s + " " + s3;
            }
        }
        for (int k = 1; k <= this.J; ++k) {
            int n3 = this.l[k];
            int n4 = this.j[k];
            int n5 = this.byte[k];
            if (this.g[k] == 1) {
                n5 = -1;
            }
            else if (this.g[k] == 2) {
                n5 = -2;
            }
            else if (this.g[k] == 3) {
                n5 = -1;
                final int n6 = n3;
                n3 = n4;
                n4 = n6;
            }
            else if (this.g[k] == 4) {
                n5 = -2;
                final int n7 = n3;
                n3 = n4;
                n4 = n7;
            }
            s = s + " " + n3 + " " + n4 + " " + n5;
            if (this.int.aW) {
                String s4 = "*";
                if (this.do[k] != null && this.do[k].length() > 0) {
                    s4 = this.do[k];
                }
                s = s + " " + s4;
            }
        }
        return s;
    }
    
    String for() {
        String string = "mi2";
        if (this.int.aW) {
            string += "t";
        }
        String s = string + " " + this.null + " " + this.J;
        final double n = 0.055999999999999994;
        for (int i = 1; i <= this.null; ++i) {
            String s2 = this.byte(i);
            if (this.a[i] > 0) {
                s2 += "H";
                if (this.a[i] > 1) {
                    s2 += this.a[i];
                }
            }
            if (this.B[i] != 0) {
                if (this.B[i] > 0) {
                    s2 += "+";
                }
                else {
                    s2 += "-";
                }
                if (Math.abs(this.B[i]) > 1) {
                    s2 += Math.abs(this.B[i]);
                }
            }
            int n2 = -1;
            for (int j = 1; j <= this.f; ++j) {
                if (this.goto[j][0] == i) {
                    n2 = this.goto[j][1];
                    break;
                }
            }
            if (n2 > -1) {
                s2 = s2 + ":" + n2;
            }
            s = s + " " + s2 + " " + a(this.y[i] * n, 0, 2) + " " + a(-this.x[i] * n, 0, 2);
            if (this.int.aW) {
                String s3 = "*";
                if (this.u[i] != null && this.u[i].length() > 0) {
                    s3 = this.u[i];
                }
                s = s + " " + s3;
            }
        }
        for (int k = 1; k <= this.J; ++k) {
            int n3 = this.l[k];
            int n4 = this.j[k];
            String s4 = "~";
            if (this.byte[k] == 1) {
                s4 = "-";
            }
            else if (this.byte[k] == 2) {
                s4 = "=";
            }
            else if (this.byte[k] == 3) {
                s4 = "#";
            }
            if (this.g[k] == 1) {
                s4 = "u";
            }
            else if (this.g[k] == 2) {
                s4 = "d";
            }
            else if (this.g[k] == 3) {
                s4 = "u";
                final int n5 = n3;
                n3 = n4;
                n4 = n5;
            }
            else if (this.g[k] == 4) {
                s4 = "d";
                final int n6 = n3;
                n3 = n4;
                n4 = n6;
            }
            s = s + " " + n3 + " " + n4 + " " + s4;
            if (this.int.aW) {
                String s5 = "*";
                if (this.do[k] != null && this.do[k].length() > 0) {
                    s5 = this.do[k];
                }
                s = s + " " + s5;
            }
        }
        return s;
    }
    
    String a(final String s) {
        final int[] array = new int[this.null + 1];
        String string = s;
        if (string.length() > 79) {
            string = string.substring(0, 76) + "...";
        }
        String s2 = string + JME.bv + "JME 2004.10 " + new Date() + JME.bv + JME.bv + else(this.null, 3) + else(this.J, 3) + "  0  0  0  0  0  0  0  0999 V2000" + JME.bv;
        final double n = 0.055999999999999994;
        double n2 = -1.7976931348623157E308;
        double n3 = Double.MAX_VALUE;
        for (int i = 1; i <= this.null; ++i) {
            if (this.x[i] > n2) {
                n2 = this.x[i];
            }
            if (this.y[i] < n3) {
                n3 = this.y[i];
            }
        }
        for (int j = 1; j <= this.null; ++j) {
            final String string2 = s2 + a((this.y[j] - n3) * n, 10, 4) + a((n2 - this.x[j]) * n, 10, 4) + a(0.0, 10, 4);
            String s3 = this.byte(j);
            if (s3.length() == 1) {
                s3 += "  ";
            }
            else if (s3.length() == 2) {
                s3 += " ";
            }
            else if (s3.length() > 3) {
                s3 = "Q  ";
            }
            final String string3 = string2 + " " + s3;
            int n4 = 0;
            if (this.B[j] > 0 && this.B[j] < 4) {
                n4 = 4 - this.B[j];
            }
            else if (this.B[j] < 0 && this.B[j] > -4) {
                n4 = 4 - this.B[j];
            }
            final String string4 = " 0" + else(n4, 3) + "  0  0  0  0  0  0  0  0  0";
            int n5 = -1;
            for (int k = 1; k <= this.f; ++k) {
                if (this.goto[k][0] == j) {
                    n5 = this.goto[k][1];
                    break;
                }
            }
            String s4;
            if (n5 > -1) {
                s4 = string4 + else(n5, 3);
            }
            else {
                s4 = string4 + "  0";
            }
            s2 = string3 + s4 + JME.bv;
        }
        for (int l = 1; l <= this.J; ++l) {
            int n6 = this.byte[l];
            if (this.a(l)) {
                n6 = 1;
            }
            else if (this.if(l)) {
                n6 = 2;
            }
            String s5 = else(this.l[l], 3) + else(this.j[l], 3);
            int n7 = 0;
            if (this.byte[l] == 1 && this.g[l] == 1) {
                n7 = 1;
            }
            else if (this.byte[l] == 1 && this.g[l] == 2) {
                n7 = 6;
            }
            if (this.byte[l] == 1 && this.g[l] == 3) {
                n7 = 1;
                s5 = else(this.j[l], 3) + else(this.l[l], 3);
            }
            if (this.byte[l] == 1 && this.g[l] == 4) {
                n7 = 6;
                s5 = else(this.j[l], 3) + else(this.l[l], 3);
            }
            s2 = s2 + s5 + else(n6, 3) + else(n7, 3) + "  0  0  0" + JME.bv;
        }
        for (int n8 = 1; n8 <= this.null; ++n8) {
            if (this.B[n8] != 0) {
                s2 = s2 + "M  CHG  1" + else(n8, 4) + else(this.B[n8], 4) + JME.bv;
            }
        }
        return s2 + "M  END" + JME.bv;
    }
    
    String byte(final int n) {
        String s = JME.a1[this.else[n]];
        if (this.else[n] == 18) {
            s = this.C[n];
        }
        return s;
    }
    
    static String else(final int n, final int n2) {
        String string = new Integer(n).toString();
        if (string.length() > n2) {
            string = "?";
        }
        String string2 = "";
        for (int i = 1; i <= n2 - string.length(); ++i) {
            string2 += " ";
        }
        return string2 + string;
    }
    
    static String a(double n, final int n2, final int n3) {
        if (n3 == 0) {
            return else((int)n, n2);
        }
        n = (int)Math.round(n * Math.pow(10.0, n3)) / Math.pow(10.0, n3);
        String s = new Double(n).toString();
        int n4 = s.indexOf(46);
        if (n4 < 0) {
            s += ".";
            n4 = s.indexOf(46);
        }
        for (int length = s.length(), i = 1; i <= n3 - length + n4 + 1; ++i) {
            s += "0";
        }
        if (n2 == 0) {
            return s;
        }
        if (s.length() > n2) {
            s = "?";
        }
        String string = "";
        for (int j = 1; j <= n2 - s.length(); ++j) {
            string += " ";
        }
        return string + s;
    }
    
    static int for(final String s) {
        if (s.equals("B")) {
            return 2;
        }
        if (s.equals("C")) {
            return 3;
        }
        if (s.equals("N")) {
            return 4;
        }
        if (s.equals("O")) {
            return 5;
        }
        if (s.equals("P")) {
            return 7;
        }
        if (s.equals("S")) {
            return 8;
        }
        if (s.equals("F")) {
            return 9;
        }
        if (s.equals("Cl")) {
            return 10;
        }
        if (s.equals("Br")) {
            return 11;
        }
        if (s.equals("I")) {
            return 12;
        }
        if (s.equals("H")) {
            return 1;
        }
        if (s.equals("Se")) {
            return 13;
        }
        if (s.equals("R")) {
            return 19;
        }
        if (s.equals("R1")) {
            return 20;
        }
        if (s.equals("R2")) {
            return 21;
        }
        if (s.equals("R3")) {
            return 22;
        }
        return 18;
    }
    
    void f() {
        if (this.int.bz) {
            return;
        }
    Label_0153:
        for (int i = this.null; i >= 1; --i) {
            final int n = this.z[i][1];
            if (this.else[i] == 1 && this.F[i] == 1 && this.B[i] == 0 && this.else[n] != 1 && this.else[n] < 18) {
                for (int j = 1; j <= this.f; ++j) {
                    if (this.goto[j][0] == i) {
                        continue Label_0153;
                    }
                }
                final int for1 = this.for(i, n);
                if (this.byte[for1] == 1 && (this.g[for1] == 0 || !this.int.T)) {
                    this.c(i);
                }
            }
        }
    }
    
    int for(final int n, final int n2) {
        for (int i = 1; i <= this.J; ++i) {
            if (this.l[i] == n && this.j[i] == n2) {
                return i;
            }
            if (this.l[i] == n2 && this.j[i] == n) {
                return i;
            }
        }
        return 0;
    }
    
    boolean a(final int n) {
        return this.byte[n] == 1;
    }
    
    private boolean if(final int n) {
        return this.byte[n] == 2;
    }
    
    void c() {
        for (int i = 1; i <= this.null; ++i) {
            this.else(i);
        }
    }
    
    void else(final int n) {
        final int i = this.i(n);
        if (i == -1) {
            this.a[n] = 0;
            return;
        }
        switch (this.else[n]) {
            case 2: {
                if (i >= 3) {
                    this.B[n] = i - 3;
                }
                this.a[n] = 3 - i + this.B[n];
                break;
            }
            case 3: {
                if (i >= 4) {
                    this.B[n] = i - 4;
                    this.a[n] = 4 - i + this.B[n];
                    break;
                }
                if (this.B[n] > 0) {
                    this.a[n] = 2 - i + this.B[n];
                    break;
                }
                if (this.B[n] < 0) {
                    this.a[n] = 2 - i - this.B[n];
                    break;
                }
                this.a[n] = 4 - i;
                break;
            }
            case 4:
            case 7: {
                if (i < 3) {
                    this.a[n] = 3 - i + this.B[n];
                    break;
                }
                if (i == 3) {
                    if (this.B[n] < 0) {
                        this.B[n] = 0;
                        this.a[n] = 0;
                        break;
                    }
                    if (this.B[n] > 0) {
                        this.a[n] = this.B[n];
                        break;
                    }
                    this.a[n] = 3 - i;
                    break;
                }
                else {
                    if (i == 4) {
                        this.B[n] = 1;
                        this.a[n] = 0;
                        break;
                    }
                    this.B[n] = i - 5 - this.a[n];
                    this.a[n] = 0;
                    break;
                }
                break;
            }
            case 5: {
                if (i == 2) {
                    if (this.B[n] < 0) {
                        this.B[n] = 0;
                        this.a[n] = 0;
                    }
                    else if (this.B[n] > 0) {
                        this.a[n] = this.B[n];
                    }
                    else {
                        this.a[n] = 2 - i;
                    }
                }
                if (i > 2) {
                    this.B[n] = i - 2;
                }
                this.a[n] = 2 - i + this.B[n];
                break;
            }
            case 8:
            case 13: {
                if (i < 2) {
                    this.a[n] = 2 - i + this.B[n];
                    break;
                }
                if (i == 2) {
                    if (this.B[n] < 0) {
                        this.B[n] = 0;
                        this.a[n] = 0;
                        break;
                    }
                    if (this.B[n] > 0) {
                        this.a[n] = this.B[n];
                        break;
                    }
                    this.a[n] = 2 - i;
                    break;
                }
                else if (i == 3) {
                    if (this.F[n] == 2) {
                        this.B[n] = 0;
                        this.a[n] = 1;
                        break;
                    }
                    this.B[n] = 1;
                    this.a[n] = 0;
                    break;
                }
                else {
                    if (i == 4) {
                        this.B[n] = 0;
                        this.a[n] = 0;
                        break;
                    }
                    if (i == 5) {
                        this.B[n] = 0;
                        this.a[n] = 1;
                        break;
                    }
                    this.B[n] = i - 6;
                    this.a[n] = 0;
                    break;
                }
                break;
            }
            case 9:
            case 10:
            case 11:
            case 12: {
                if (i >= 1) {
                    this.B[n] = i - 1;
                }
                this.a[n] = 1 - i + this.B[n];
                if (i > 2) {
                    this.B[n] = 0;
                    this.a[n] = 0;
                    break;
                }
                break;
            }
            case 19: {
                this.a[n] = 0;
                break;
            }
        }
        if (this.a[n] < 0) {
            this.a[n] = 0;
        }
    }
    
    void goto(final int n) {
        final String s = "Charge change not possible on ";
        final int i = this.i(n);
        if (i == -1) {
            if (this.B[n] == 0) {
                this.B[n] = 1;
            }
            else if (this.B[n] == 1) {
                this.B[n] = -1;
            }
            else if (this.B[n] == -1) {
                this.B[n] = 0;
            }
            return;
        }
        switch (this.else[n]) {
            case 3: {
                if (i > 3) {
                    this.int.a(s + "this carbon !");
                    break;
                }
                if (i >= 4) {
                    break;
                }
                if (this.B[n] == 0) {
                    this.B[n] = -1;
                    break;
                }
                if (this.B[n] == -1) {
                    this.B[n] = 1;
                    break;
                }
                if (this.B[n] == 1) {
                    this.B[n] = 0;
                    break;
                }
                break;
            }
            case 4:
            case 7: {
                if (i > 3) {
                    this.int.a(s + "multibonded N or P !");
                    break;
                }
                if (i == 3 && this.B[n] == 0) {
                    this.B[n] = 1;
                    break;
                }
                if (i == 3 && this.B[n] == 1) {
                    this.B[n] = 0;
                    break;
                }
                if (i < 3 && this.B[n] == 0) {
                    this.B[n] = 1;
                    break;
                }
                if (i < 3 && this.B[n] == 1) {
                    this.B[n] = -1;
                    break;
                }
                if (i < 3 && this.B[n] == -1) {
                    this.B[n] = 0;
                    break;
                }
                break;
            }
            case 5:
            case 8:
            case 13: {
                if (i > 2) {
                    this.int.a(s + "multibonded O or S !");
                    break;
                }
                if (i == 2 && this.B[n] == 0) {
                    this.B[n] = 1;
                    break;
                }
                if (i == 2 && this.B[n] == 1) {
                    this.B[n] = 0;
                    break;
                }
                if (i < 2 && this.B[n] == 0) {
                    this.B[n] = -1;
                    break;
                }
                if (i < 2 && this.B[n] == -1) {
                    this.B[n] = 1;
                    break;
                }
                if (i < 2 && this.B[n] == 1) {
                    this.B[n] = 0;
                    break;
                }
                break;
            }
            case 9:
            case 10:
            case 11:
            case 12: {
                if (i == 0 && this.B[n] == 0) {
                    this.B[n] = -1;
                    break;
                }
                if (i == 0 && this.B[n] == -1) {
                    this.B[n] = 0;
                    break;
                }
                this.int.a(s + "the halogen !");
                break;
            }
            case 18: {
                this.int.a("Use X button to change charge on the X atom !");
                break;
            }
        }
    }
    
    int i(final int n) {
        int n2 = 0;
        for (int i = 1; i <= this.F[n]; ++i) {
            final int for1 = this.for(n, this.z[n][i]);
            if (this.a(for1)) {
                ++n2;
            }
            else if (this.if(for1)) {
                n2 += 2;
            }
            else if (this.byte[for1] == 3) {
                n2 += 3;
            }
            else if (this.byte[for1] == 9) {
                return -1;
            }
        }
        return n2;
    }
    
    public void b() {
        for (int i = 1; i <= this.f; ++i) {
            if (this.void == this.goto[i][0]) {
                for (int j = i; j < this.f; ++j) {
                    this.goto[j][0] = this.goto[j + 1][0];
                    this.goto[j][1] = this.goto[j + 1][1];
                }
                --this.f;
                return;
            }
        }
        final int length = this.goto.length;
        if (++this.f > length - 1) {
            final int[][] goto1 = new int[length + 5][2];
            System.arraycopy(this.goto, 0, goto1, 0, this.goto.length);
            this.goto = goto1;
        }
        if (!this.int.ba) {
            ++a.p;
        }
        this.goto[this.f][0] = this.void;
        this.goto[this.f][1] = a.p;
    }
    
    public void null() {
        this.f = 0;
        a.p = 0;
        this.else();
        for (int i = 1; i <= this.null; ++i) {
            this.void = i;
            this.b();
        }
        this.void = 0;
    }
    
    public void if(final int n, final String s) {
        if (this.C == null || this.C.length < this.null + 1) {
            this.C = new String[this.null + 1];
        }
        this.C[n] = s;
    }
    
    public int m() {
        final double[] array = new double[4];
        this.a(array);
        int width = this.int.bA.width;
        if (!this.int.bh) {
            width -= this.int.aa;
        }
        if (array[0] < width / 2 - this.int.bq / 2) {
            return 1;
        }
        if (array[0] > width / 2 + this.int.bq / 2) {
            return 3;
        }
        return 2;
    }
    
    private static long[] f(final int n) {
        final long[] array = new long[n + 2];
        final int[] array2 = new int[100];
        int i = 5;
        int n2 = 0;
        array2[0] = 3;
        array[1] = 2L;
        array[2] = 3L;
        int n3 = 2;
        if (n < 3) {
            return array;
        }
        while (i < array2[n2] * array2[n2]) {
            int n4 = 0;
            int n5 = 1;
            while (n5 == 1 && n4 <= n2 && i >= array2[n4] * array2[n4]) {
                if (i % array2[n4] == 0) {
                    n5 = 0;
                }
                else {
                    ++n4;
                }
            }
            if (n5 == 1) {
                array[++n3] = i;
                if (n3 >= n) {
                    return array;
                }
                if (n2 < array2.length - 1) {
                    ++n2;
                    array2[n2] = i;
                }
            }
            i += 2;
        }
        System.err.println("ERROR - Prime Number generator failed !");
        return array;
    }
    
    static {
        a.p = 0;
        a.G = false;
    }
}
