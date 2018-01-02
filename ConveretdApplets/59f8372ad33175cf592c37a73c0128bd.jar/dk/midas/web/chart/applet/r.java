// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

public class r
{
    public static final int try = 0;
    public static final int int = 1;
    private int do;
    private int new;
    private float if;
    private float a;
    private int for;
    
    public r(final u u) {
        final int a = u.a();
        final boolean for1 = if(u.for(), a) <= a(u.for(), a);
        float for2 = for(u.for(), a);
        float do1 = do(u.for(), a);
        if (for1) {
            do1 -= u.new();
        }
        else {
            for2 += u.new();
        }
        this.do = a;
        this.new = a;
        this.if = for2;
        this.a = do1;
        this.for = (for1 ? 1 : 0);
    }
    
    protected r(final int n, final float if1, final float a, final int for1) {
        this.do = n;
        this.new = n;
        this.if = if1;
        this.a = a;
        this.for = for1;
    }
    
    public r a(final u u, final int new1) {
        if (new1 >= u.for().b5.case()) {
            return this;
        }
        final float new2 = u.new();
        final float n = new2 * u.do();
        final float for1 = for(u.for(), new1);
        final float do1 = do(u.for(), new1);
        this.new = new1;
        if (for1 == Float.MIN_VALUE || do1 == Float.MIN_VALUE) {
            return this;
        }
        if (this.a() == 1) {
            if (this.if - for1 >= new2) {
                this.if = for1;
            }
        }
        else if (this.a() == 0 && do1 - this.a >= new2) {
            this.a = do1;
        }
        if (this.a() == 1) {
            if (do1 - this.if >= n) {
                return new r(new1, this.if + new2, do1, 0);
            }
        }
        else if (this.a() == 0 && this.a - for1 >= n) {
            return new r(new1, for1, this.a - new2, 1);
        }
        return this;
    }
    
    public boolean a(final int n) {
        return this.do <= n && n <= this.new;
    }
    
    public int int() {
        return this.new;
    }
    
    public float for() {
        return this.if;
    }
    
    public int a() {
        return this.for;
    }
    
    public float if() {
        return this.a;
    }
    
    public int do() {
        return this.do;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        String s;
        for (s = Integer.toHexString(this.hashCode()); s.length() < 8; s = "0" + s) {}
        sb.append("PFColumn@").append(s).append("[");
        sb.append("trend = ").append((this.for == 1) ? "O" : "X").append(", ");
        sb.append("low = ").append(this.if).append(", ");
        sb.append("high = ").append(this.a);
        sb.append("]");
        return sb.toString();
    }
    
    private static float for(final DataSource dataSource, final int n) {
        return dataSource.b5.if(n);
    }
    
    private static float do(final DataSource dataSource, final int n) {
        return dataSource.bL.if(n);
    }
    
    private static float a(final DataSource dataSource, final int n) {
        return dataSource.bX.if(n);
    }
    
    private static float if(final DataSource dataSource, final int n) {
        return dataSource.bW.if(n);
    }
}
