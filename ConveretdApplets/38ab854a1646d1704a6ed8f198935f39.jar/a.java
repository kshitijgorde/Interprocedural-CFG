// 
// Decompiled by Procyon v0.5.30
// 

public abstract class a
{
    static a goto;
    static a int;
    static a a;
    static a do;
    static a char;
    static a try;
    static a else;
    static a byte;
    static a new;
    static a case;
    static a for;
    static a if;
    
    static {
        a.goto = new l();
        a.int = new e();
        a.a = new a();
        a.do = new c();
        a.char = new j();
        a.try = new g();
        a.else = new k();
        a.byte = new h();
        a.new = a.byte;
        a.case = new i();
        a.for = new f();
        a.if = new b();
    }
    
    static a a(final double n) {
        return new d(n);
    }
    
    public abstract double a(final u p0, final int p1, final int p2) throws s;
    
    public abstract int a(final int p0, final int p1);
    
    public static class l extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = 0.0;
            for (int i = 0; i < n; ++i) {
                n3 += u.a(i + n2);
            }
            return n3 / n;
        }
        
        public int a(final int n, final int n2) {
            return n + n2 + 1;
        }
        
        public String toString() {
            return "MoveAve";
        }
    }
    
    public static class e extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = 0.0;
            for (int i = 0; i < n; ++i) {
                n3 += u.a(i + n2) * (n - i);
            }
            return 2.0 * n3 / (n * (n + 1));
        }
        
        public int a(final int n, final int n2) {
            return n + n2 + 1;
        }
        
        public String toString() {
            return "Ave";
        }
    }
    
    public static class a extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = 0.0;
            double n4 = 0.0;
            for (int i = 0; i < n; ++i) {
                final double a = u.a(i + n2);
                n3 += a;
                n4 += a * a;
            }
            return Math.sqrt((n4 - n3 * n3 / n) / (n - 1));
        }
        
        public int a(final int n, final int n2) {
            return n + n2 + 1;
        }
        
        public String toString() {
            return "STDEV";
        }
    }
    
    public static class c extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = 0.0;
            double n4 = 0.0;
            for (int i = 0; i < n; ++i) {
                n3 += u.a(i + n2);
                n4 += u.a(i + n2) * u.a(i + n2);
            }
            return Math.sqrt((n4 - n3 * n3 / n) / n);
        }
        
        public int a(final int n, final int n2) {
            return n + n2 + 1;
        }
        
        public String toString() {
            return "STDEVPA";
        }
    }
    
    public static class j extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = 0.0;
            double n4 = 0.0;
            for (int i = 0; i < n; ++i) {
                n3 += u.a(i + n2);
                n4 += u.a(i + n2) * u.a(i + n2);
            }
            return (n4 - n3 * n3 / n) / (n - 1);
        }
        
        public int a(final int n, final int n2) {
            return n + n2 + 1;
        }
        
        public String toString() {
            return "Variance";
        }
    }
    
    public static class g extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = 0.0;
            for (int i = 0; i < n; ++i) {
                n3 += u.a(i + n2);
            }
            return n3;
        }
        
        public int a(final int n, final int n2) {
            return n + n2;
        }
        
        public String toString() {
            return "Sum";
        }
    }
    
    public static class k extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = 0.0;
            for (int i = 0; i < n; ++i) {
                n3 += Math.abs(u.a(i + n2));
            }
            return n3;
        }
        
        public int a(final int n, final int n2) {
            return n + n2;
        }
        
        public String toString() {
            return "AbsSum";
        }
    }
    
    public static class h extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            return u.a(n2);
        }
        
        public int a(final int n, final int n2) {
            return n2 + 1;
        }
        
        public String toString() {
            return "Value";
        }
    }
    
    public static class i extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = u.a(n2);
            for (int i = 1; i < n; ++i) {
                n3 = Math.max(n3, u.a(i + n2));
            }
            return n3;
        }
        
        public int a(final int n, final int n2) {
            return n + n2 + 1;
        }
        
        public String toString() {
            return "Max";
        }
    }
    
    public static class f extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            double n3 = u.a(n2);
            for (int i = 1; i < n; ++i) {
                n3 = Math.min(n3, u.a(i + n2));
            }
            return n3;
        }
        
        public int a(final int n, final int n2) {
            return n + n2 + 1;
        }
        
        public String toString() {
            return "Min";
        }
    }
    
    public static class b extends a
    {
        public double a(final u u, final int n, final int n2) throws s {
            int n3 = 0;
            for (int i = 0; i < n; ++i) {
                if (u.a(n2 + i) - u.a(n2 + i + 1) > 0.0) {
                    ++n3;
                }
            }
            double n4 = 0.0;
            for (int j = 1; j <= n - n3; ++j) {
                n4 += Math.log(j);
            }
            for (int k = 1; k <= n3; ++k) {
                n4 += Math.log(k);
            }
            for (int l = 1; l <= n; ++l) {
                n4 -= Math.log(l);
            }
            return n + n4 / Math.log(2.0);
        }
        
        public int a(final int n, final int n2) {
            return n + n2 + 2;
        }
        
        public String toString() {
            return "INF";
        }
    }
    
    public static class d extends a
    {
        double long;
        
        public d(final double long1) {
            this.long = long1;
        }
        
        public double a(final u u, final int n, final int n2) {
            return this.long;
        }
        
        public int a(final int n, final int n2) {
            return 0;
        }
        
        public String toString() {
            return "" + this.long;
        }
    }
}
