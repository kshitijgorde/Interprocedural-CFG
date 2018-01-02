// 
// Decompiled by Procyon v0.5.30
// 

final class Class240_Sub2 extends Class240
{
    private long aLong5055;
    private long aLong5056;
    private long[] aLongArray5057;
    private int anInt5058;
    private int anInt5059;
    private long aLong5060;
    
    @Override
    final void method2923(final boolean b) {
        try {
            this.aLong5060 = 0L;
            if (this.aLong5055 > this.aLong5056) {
                this.aLong5056 += this.aLong5055 + -this.aLong5056;
            }
            if (b) {
                this.method2924((byte)(-114));
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    @Override
    final long method2922(final byte b) {
        long n;
        try {
            this.aLong5056 += this.method2929(-123);
            if (this.aLong5056 < this.aLong5055) {
                return (-this.aLong5056 + this.aLong5055) / 1000000L;
            }
            if (b >= -80) {
                this.method2926(73, -115L);
            }
            n = 0L;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n;
    }
    
    @Override
    final int method2926(final int n, final long n2) {
        int n4;
        try {
            if (n <= 117) {
                this.aLong5056 = -7L;
            }
            if (this.aLong5056 < this.aLong5055) {
                this.aLong5060 += this.aLong5055 + -this.aLong5056;
                this.aLong5056 += this.aLong5055 - this.aLong5056;
                this.aLong5055 += n2;
                return 1;
            }
            int n3 = 0;
            do {
                ++n3;
                this.aLong5055 += n2;
            } while (10 > n3 && ~this.aLong5055 > ~this.aLong5056);
            if (~this.aLong5056 < ~this.aLong5055) {
                this.aLong5055 = this.aLong5056;
            }
            n4 = n3;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n4;
    }
    
    @Override
    final long method2924(final byte b) {
        long aLong5056;
        try {
            if (b <= 12) {
                return 126L;
            }
            aLong5056 = this.aLong5056;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return aLong5056;
    }
    
    private final long method2929(final int n) {
        long n5;
        try {
            final long nanoTime = System.nanoTime();
            final long n2 = -this.aLong5060 + nanoTime;
            this.aLong5060 = nanoTime;
            if (4999999999L > ~n2 && 5000000000L > n2) {
                this.aLongArray5057[this.anInt5058] = n2;
                this.anInt5058 = (1 + this.anInt5058) % 10;
                if (~this.anInt5059 > -2) {
                    ++this.anInt5059;
                }
            }
            long n3 = 0L;
            for (int n4 = 1; ~this.anInt5059 <= ~n4; ++n4) {
                n3 += this.aLongArray5057[(10 + this.anInt5058 + -n4) % 10];
            }
            n5 = n3 / this.anInt5059;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return n5;
    }
    
    Class240_Sub2() {
        this.aLong5055 = 0L;
        this.aLong5056 = 0L;
        this.aLongArray5057 = new long[10];
        this.anInt5059 = 1;
        this.anInt5058 = 0;
        this.aLong5060 = 0L;
        try {
            this.aLong5056 = System.nanoTime();
            this.aLong5055 = System.nanoTime();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
