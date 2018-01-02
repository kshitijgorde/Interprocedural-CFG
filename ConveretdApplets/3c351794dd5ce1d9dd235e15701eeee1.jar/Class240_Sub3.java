// 
// Decompiled by Procyon v0.5.30
// 

final class Class240_Sub3 extends Class240
{
    private long aLong5061;
    private long[] aLongArray5062;
    private int anInt5063;
    private int anInt5064;
    private long aLong5065;
    private long aLong5066;
    
    private final long method2930(final int n) {
        try {
            final long aLong5066 = Class343.method3819(-47) * 1000000L;
            final long n2 = aLong5066 + -this.aLong5066;
            this.aLong5066 = aLong5066;
            if (~n2 < 4999999999L && n2 < 5000000000L) {
                this.aLongArray5062[this.anInt5064] = n2;
                this.anInt5064 = (1 + this.anInt5064) % 10;
                if (~this.anInt5063 > -11) {
                    ++this.anInt5063;
                }
            }
            long n3 = n;
            for (int n4 = 1; ~n4 >= ~this.anInt5063; ++n4) {
                n3 += this.aLongArray5062[(-n4 + (this.anInt5064 + 10)) % 10];
            }
            return n3 / this.anInt5063;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final long method2922(final byte b) {
        try {
            if (b >= -80) {
                this.aLong5066 = 38L;
            }
            this.aLong5065 += this.method2930(0);
            if (~this.aLong5065 > ~this.aLong5061) {
                return (-this.aLong5065 + this.aLong5061) / 1000000L;
            }
            return 0L;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final void method2923(final boolean b) {
        try {
            this.aLong5066 = 0L;
            if (~this.aLong5065 > ~this.aLong5061) {
                this.aLong5065 += -this.aLong5065 + this.aLong5061;
            }
            if (b) {
                this.method2924((byte)9);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final int method2926(final int n, final long n2) {
        try {
            if (n <= 117) {
                return 55;
            }
            if (this.aLong5065 >= this.aLong5061) {
                int n3 = 0;
                do {
                    this.aLong5061 += n2;
                } while (++n3 < 10 && ~this.aLong5065 < ~this.aLong5061);
                if (~this.aLong5065 < ~this.aLong5061) {
                    this.aLong5061 = this.aLong5065;
                }
                return n3;
            }
            this.aLong5066 += -this.aLong5065 + this.aLong5061;
            this.aLong5065 += this.aLong5061 - this.aLong5065;
            this.aLong5061 += n2;
            return 1;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final long method2924(final byte b) {
        try {
            if (b <= 12) {
                return 77L;
            }
            return this.aLong5065;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class240_Sub3() {
        this.aLong5061 = 0L;
        this.aLongArray5062 = new long[10];
        this.anInt5063 = 1;
        this.aLong5065 = 0L;
        this.aLong5066 = 0L;
        this.anInt5064 = 0;
    }
}
