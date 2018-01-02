import jagex3.jagmisc.jagmisc;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class240_Sub1 extends Class240
{
    private long aLong5049;
    private long aLong5050;
    private long aLong5051;
    private int anInt5052;
    private int anInt5053;
    private long[] aLongArray5054;
    
    @Override
    final void method2923(final boolean b) {
        try {
            this.aLong5051 = 0L;
            if (~this.aLong5049 < ~this.aLong5050) {
                this.aLong5050 += this.aLong5049 - this.aLong5050;
            }
            if (b) {
                this.aLong5049 = 111L;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final long method2924(final byte b) {
        try {
            if (b < 12) {
                this.method2928(104);
            }
            return this.aLong5050;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    private final long method2928(final int n) {
        try {
            final long nanoTime = jagmisc.nanoTime();
            final long n2 = nanoTime - this.aLong5051;
            this.aLong5051 = nanoTime;
            if (n != 27103) {
                return -94L;
            }
            if (~n2 < 4999999999L && n2 < 5000000000L) {
                this.aLongArray5054[this.anInt5052] = n2;
                if (this.anInt5053 < 1) {
                    ++this.anInt5053;
                }
                this.anInt5052 = (1 + this.anInt5052) % 10;
            }
            long n3 = 0L;
            for (int n4 = 1; ~n4 >= ~this.anInt5053; ++n4) {
                n3 += this.aLongArray5054[(10 + this.anInt5052 + -n4) % 10];
            }
            return n3 / this.anInt5053;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final long method2922(final byte b) {
        try {
            this.aLong5050 += this.method2928(27103);
            if (this.aLong5049 > this.aLong5050) {
                return (this.aLong5049 - this.aLong5050) / 1000000L;
            }
            if (b >= -80) {
                this.anInt5053 = -48;
            }
            return 0L;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final int method2926(final int n, final long n2) {
        try {
            if (n <= 117) {
                this.anInt5053 = 14;
            }
            if (~this.aLong5049 >= ~this.aLong5050) {
                int n3 = 0;
                do {
                    this.aLong5049 += n2;
                } while (++n3 < 10 && this.aLong5049 < this.aLong5050);
                if (~this.aLong5049 > ~this.aLong5050) {
                    this.aLong5049 = this.aLong5050;
                }
                return n3;
            }
            this.aLong5051 += this.aLong5049 + -this.aLong5050;
            this.aLong5050 += this.aLong5049 + -this.aLong5050;
            this.aLong5049 += n2;
            return 1;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    Class240_Sub1() {
        this.aLong5050 = 0L;
        this.aLong5049 = 0L;
        this.aLong5051 = 0L;
        this.anInt5052 = 0;
        this.anInt5053 = 1;
        this.aLongArray5054 = new long[10];
        try {
            final long nanoTime = jagmisc.nanoTime();
            this.aLong5050 = nanoTime;
            this.aLong5049 = nanoTime;
            if (this.aLong5050 == 0L) {
                throw new RuntimeException();
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
