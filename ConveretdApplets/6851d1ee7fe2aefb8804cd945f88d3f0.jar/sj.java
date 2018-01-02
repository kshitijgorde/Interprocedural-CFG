// 
// Decompiled by Procyon v0.5.30
// 

public class sj
{
    private int Xa;
    private int Ya;
    private int Za;
    private int _b;
    private boolean ab;
    private String bb;
    private String cb;
    private String db;
    private Mh[] eb;
    private static final String fb = "|";
    
    public sj(final int xa, final int ya, final int za, final int b, final boolean ab, final String bb, final String cb, final String db, final Mh[] eb) {
        this.Xa = xa;
        this.Ya = ya;
        this.Za = za;
        this._b = b;
        this.ab = ab;
        this.bb = bb;
        this.cb = cb;
        this.db = db;
        this.eb = eb;
    }
    
    public sj(final String s) {
        final u u = new u("|");
        u.m(s);
        if (u.a() < 8) {
            throw new IllegalArgumentException("analysisString: " + s);
        }
        this.Xa = Integer.parseInt(u.b(0));
        this.Ya = Integer.parseInt(u.b(1));
        this.Za = Integer.parseInt(u.b(2));
        this._b = Integer.parseInt(u.b(3));
        this.ab = Boolean.valueOf(u.b(4));
        this.bb = u.b(5);
        this.cb = u.b(6);
        this.db = u.b(7);
        this.eb = new Mh[Math.max(0, u.a() - 8)];
        for (int i = 0; i < this.eb.length; ++i) {
            try {
                this.eb[i] = new Mh(u.b(8 + i));
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.eb[i] = null;
            }
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(1024);
        sb.append(this.Xa);
        sb.append("|");
        sb.append(this.Ya);
        sb.append("|");
        sb.append(this.Za);
        sb.append("|");
        sb.append(this._b);
        sb.append("|");
        sb.append(this.ab);
        sb.append("|");
        sb.append(this.bb);
        sb.append("|");
        sb.append(this.cb);
        sb.append("|");
        sb.append(this.db);
        sb.append("|");
        for (int i = 0; i < this.eb.length; ++i) {
            sb.append(this.eb[i].toString());
            sb.append("|");
        }
        return sb.toString();
    }
    
    public int h() {
        return this._b;
    }
    
    public String n() {
        return this.cb;
    }
    
    public String c() {
        return this.db;
    }
    
    public String d() {
        return this.bb;
    }
    
    public int i() {
        return this.Xa;
    }
    
    public int j() {
        return this.Ya;
    }
    
    public int k() {
        return this.Za;
    }
    
    public Mh[] b() {
        return this.eb;
    }
    
    public boolean k() {
        return this.ab;
    }
}
