import java.math.BigInteger;
import mindbright.security.RSAKey;

// 
// Decompiled by Procyon v0.5.30
// 

public class cf extends RSAKey
{
    public BigInteger m_;
    public BigInteger mz;
    public BigInteger my;
    public BigInteger mx;
    
    public cf(final BigInteger bigInteger, final BigInteger bigInteger2, final BigInteger m_, final BigInteger mz, final BigInteger my, final BigInteger mx) {
        super(bigInteger, bigInteger2);
        this.m_ = m_;
        this.mz = mz;
        this.my = my;
        this.mx = mx;
    }
    
    public final BigInteger m1() {
        return this.m_;
    }
    
    public final BigInteger m0() {
        return this.mz;
    }
    
    public final BigInteger m_() {
        return this.my;
    }
    
    public final BigInteger mz() {
        return this.mx;
    }
}
