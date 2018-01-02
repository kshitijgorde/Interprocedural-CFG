// 
// Decompiled by Procyon v0.5.30
// 

public class a7 extends bx
{
    public static final int ac = 5;
    public static final int ad = 20;
    public int speriod;
    public int lperiod;
    
    public a7(final bk bk) {
        this(bk, 5, 20);
    }
    
    public a7(final bk bk, final int speriod, final int lperiod) {
        super(new n(bk, speriod), new n(bk, lperiod));
        this.speriod = speriod;
        this.lperiod = lperiod;
    }
    
    public void a() {
        super.a();
        super.ab.a("period", new Integer(this.speriod));
        super.aa.a("period", new Integer(this.lperiod));
    }
}
