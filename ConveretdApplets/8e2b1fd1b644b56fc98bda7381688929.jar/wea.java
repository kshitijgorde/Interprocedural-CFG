// 
// Decompiled by Procyon v0.5.30
// 

public class wea
{
    private int Ra;
    private int Sa;
    private int Ta;
    private int Ua;
    private boolean Va;
    private String Wa;
    private String Xa;
    private String Ya;
    private q[] Za;
    private static final String _b = "|";
    
    public wea(final int ra, final int sa, final int ta, final int ua, final boolean va, final String wa, final String xa, final String ya, final q[] za) {
        this.Ra = ra;
        this.Sa = sa;
        this.Ta = ta;
        this.Ua = ua;
        this.Va = va;
        this.Wa = wa;
        this.Xa = xa;
        this.Ya = ya;
        this.Za = za;
    }
    
    public wea(final String s) {
        final try try1 = new try("|");
        try1.l(s);
        if (try1.g() < 8) {
            throw new IllegalArgumentException("analysisString: " + s);
        }
        this.Ra = Integer.parseInt(try1.a(0));
        this.Sa = Integer.parseInt(try1.a(1));
        this.Ta = Integer.parseInt(try1.a(2));
        this.Ua = Integer.parseInt(try1.a(3));
        this.Va = Boolean.valueOf(try1.a(4));
        this.Wa = try1.a(5);
        this.Xa = try1.a(6);
        this.Ya = try1.a(7);
        this.Za = new q[Math.max(0, try1.g() - 8)];
        for (int i = 0; i < this.Za.length; ++i) {
            try {
                this.Za[i] = new q(try1.a(8 + i));
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.Za[i] = null;
            }
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(1024);
        sb.append(this.Ra);
        sb.append("|");
        sb.append(this.Sa);
        sb.append("|");
        sb.append(this.Ta);
        sb.append("|");
        sb.append(this.Ua);
        sb.append("|");
        sb.append(this.Va);
        sb.append("|");
        sb.append(this.Wa);
        sb.append("|");
        sb.append(this.Xa);
        sb.append("|");
        sb.append(this.Ya);
        sb.append("|");
        for (int i = 0; i < this.Za.length; ++i) {
            sb.append(this.Za[i].toString());
            sb.append("|");
        }
        return sb.toString();
    }
    
    public int i() {
        return this.Ua;
    }
    
    public String k() {
        return this.Xa;
    }
    
    public String l() {
        return this.Ya;
    }
    
    public String m() {
        return this.Wa;
    }
    
    public int j() {
        return this.Ra;
    }
    
    public int k() {
        return this.Sa;
    }
    
    public int l() {
        return this.Ta;
    }
    
    public q[] _() {
        return this.Za;
    }
    
    public boolean l() {
        return this.Va;
    }
}
