import java.io.EOFException;
import java.io.File;
import java.io.IOException;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class225
{
    private byte[] aByteArray1687;
    private long aLong1688;
    private long aLong1689;
    private long aLong1690;
    private byte[] aByteArray1691;
    private Class356 aClass356_1692;
    private long aLong1693;
    private long aLong1694;
    static Class148 aClass148_1695;
    private int anInt1696;
    private long aLong1697;
    private int anInt1698;
    
    final void method2842(final byte[] array, final int n) throws IOException {
        try {
            this.method2849(n, array, n - 12913, array.length);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.K(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method2843(final byte b) throws IOException {
        try {
            this.method2845(-1);
            this.aClass356_1692.method3880(true);
            if (b >= -28) {
                this.aLong1688 = -117L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.I(" + b + ')');
        }
    }
    
    private final File method2844(final byte b) {
        try {
            if (b > -121) {
                this.method2847(true);
            }
            return this.aClass356_1692.method3876((byte)3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.A(" + b + ')');
        }
    }
    
    private final void method2845(final int n) throws IOException {
        try {
            if (this.aLong1693 != n) {
                if (~this.aLong1693 != ~this.aLong1688) {
                    this.aClass356_1692.method3877((byte)97, this.aLong1693);
                    this.aLong1688 = this.aLong1693;
                }
                this.aClass356_1692.method3882(this.aByteArray1691, 4657, 0, this.anInt1698);
                this.aLong1688 += this.anInt1698;
                if (this.aLong1688 > this.aLong1690) {
                    this.aLong1690 = this.aLong1688;
                }
                long n2 = -1L;
                if (this.aLong1694 > this.aLong1693 || this.aLong1693 >= this.anInt1696 + this.aLong1694) {
                    if (~this.aLong1693 >= ~this.aLong1694 && ~this.aLong1694 > ~(this.anInt1698 + this.aLong1693)) {
                        n2 = this.aLong1694;
                    }
                }
                else {
                    n2 = this.aLong1693;
                }
                long n3 = -1L;
                if (~(this.anInt1698 + this.aLong1693) >= ~this.aLong1694 || this.aLong1693 + this.anInt1698 > this.aLong1694 + this.anInt1696) {
                    if (~this.aLong1693 > ~(this.aLong1694 + this.anInt1696) && ~(this.anInt1698 + this.aLong1693) <= ~(this.aLong1694 + this.anInt1696)) {
                        n3 = this.anInt1696 + this.aLong1694;
                    }
                }
                else {
                    n3 = this.anInt1698 + this.aLong1693;
                }
                if (~n2 < 0L && n2 < n3) {
                    Class236.method2894(this.aByteArray1691, (int)(n2 - this.aLong1693), this.aByteArray1687, (int)(n2 + -this.aLong1694), (int)(-n2 + n3));
                }
                this.anInt1698 = 0;
                this.aLong1693 = -1L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.B(" + n + ')');
        }
    }
    
    final void method2846(final long aLong1697, final int n) throws IOException {
        try {
            if (~aLong1697 > -1L) {
                throw new IOException("Invalid seek to " + aLong1697 + " in file " + this.method2844((byte)(-127)));
            }
            this.aLong1697 = aLong1697;
            if (n != 0) {
                this.aLong1693 = 78L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.G(" + aLong1697 + ',' + n + ')');
        }
    }
    
    final long method2847(final boolean b) {
        try {
            if (!b) {
                method2851(true);
            }
            return this.aLong1689;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.F(" + b + ')');
        }
    }
    
    static final void method2848(final int n, final int n2, final int anInt6054) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -113, n2);
            method2628.method1626((byte)(-103));
            method2628.anInt6054 = anInt6054;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.J(" + n + ',' + n2 + ',' + anInt6054 + ')');
        }
    }
    
    final void method2849(int i, final byte[] array, final int n, int n2) throws IOException {
        try {
            try {
                if (~array.length > ~(n2 + i)) {
                    throw new ArrayIndexOutOfBoundsException(n2 + i - array.length);
                }
                if (n != -12913) {
                    return;
                }
                if (this.aLong1693 != -1L && ~this.aLong1693 >= ~this.aLong1697 && ~(n2 + this.aLong1697) >= ~(this.aLong1693 - -this.anInt1698)) {
                    Class236.method2894(this.aByteArray1691, (int)(this.aLong1697 - this.aLong1693), array, i, n2);
                    this.aLong1697 += n2;
                    return;
                }
                final long aLong1697 = this.aLong1697;
                final int n3 = i;
                final int n4 = n2;
                if (~this.aLong1697 <= ~this.aLong1694 && ~(this.anInt1696 + this.aLong1694) < ~this.aLong1697) {
                    int n5 = (int)(this.anInt1696 + -this.aLong1697 + this.aLong1694);
                    if (~n2 > ~n5) {
                        n5 = n2;
                    }
                    Class236.method2894(this.aByteArray1687, (int)(this.aLong1697 - this.aLong1694), array, i, n5);
                    i += n5;
                    this.aLong1697 += n5;
                    n2 -= n5;
                }
                if (n2 <= this.aByteArray1687.length) {
                    if (n2 > 0) {
                        this.method2850((byte)113);
                        int anInt1696 = n2;
                        if (~this.anInt1696 > ~anInt1696) {
                            anInt1696 = this.anInt1696;
                        }
                        Class236.method2894(this.aByteArray1687, 0, array, i, anInt1696);
                        i += anInt1696;
                        n2 -= anInt1696;
                        this.aLong1697 += anInt1696;
                    }
                }
                else {
                    this.aClass356_1692.method3877((byte)(-121), this.aLong1697);
                    this.aLong1688 = this.aLong1697;
                    while (~n2 < -1) {
                        final int method3879 = this.aClass356_1692.method3879(n2, (byte)(-26), i, array);
                        if (method3879 == -1) {
                            break;
                        }
                        i += method3879;
                        this.aLong1688 += method3879;
                        n2 -= method3879;
                        this.aLong1697 += method3879;
                    }
                }
                if (this.aLong1693 != -1L) {
                    if (~this.aLong1697 > ~this.aLong1693 && n2 > 0) {
                        int n6 = (int)(this.aLong1693 - this.aLong1697) + i;
                        if (n6 > n2 + i) {
                            n6 = n2 + i;
                        }
                        while (i < n6) {
                            array[i++] = 0;
                            --n2;
                            ++this.aLong1697;
                        }
                    }
                    long aLong1698 = -1L;
                    if (aLong1697 <= this.aLong1693 && aLong1697 + n4 > this.aLong1693) {
                        aLong1698 = this.aLong1693;
                    }
                    else if (~aLong1697 <= ~this.aLong1693 && aLong1697 < this.anInt1698 + this.aLong1693) {
                        aLong1698 = aLong1697;
                    }
                    long aLong1699 = -1L;
                    if (aLong1697 < this.aLong1693 + this.anInt1698 && this.anInt1698 + this.aLong1693 <= aLong1697 + n4) {
                        aLong1699 = this.aLong1693 - -this.anInt1698;
                    }
                    else if (~(aLong1697 + n4) < ~this.aLong1693 && ~(aLong1697 - -n4) >= ~(this.anInt1698 + this.aLong1693)) {
                        aLong1699 = n4 + aLong1697;
                    }
                    if (aLong1698 > -1L && aLong1698 < aLong1699) {
                        Class236.method2894(this.aByteArray1691, (int)(aLong1698 + -this.aLong1693), array, n3 + (int)(aLong1698 + -aLong1697), (int)(aLong1699 - aLong1698));
                        if (aLong1699 > this.aLong1697) {
                            n2 -= (int)(aLong1699 - this.aLong1697);
                            this.aLong1697 = aLong1699;
                        }
                    }
                }
            }
            catch (IOException ex) {
                this.aLong1688 = -1L;
                throw ex;
            }
            if (~n2 < -1) {
                throw new EOFException();
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "ofa.C(" + i + ',' + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    private final void method2850(final byte b) throws IOException {
        try {
            this.anInt1696 = 0;
            if (~this.aLong1688 != ~this.aLong1697) {
                this.aClass356_1692.method3877((byte)(-74), this.aLong1697);
                this.aLong1688 = this.aLong1697;
            }
            this.aLong1694 = this.aLong1697;
            if (b != 113) {
                this.method2844((byte)120);
            }
            while (this.anInt1696 < this.aByteArray1687.length) {
                int n = -this.anInt1696 + this.aByteArray1687.length;
                if (n > 200000000) {
                    n = 200000000;
                }
                final int method3879 = this.aClass356_1692.method3879(n, (byte)(-26), this.anInt1696, this.aByteArray1687);
                if (method3879 == -1) {
                    break;
                }
                this.aLong1688 += method3879;
                this.anInt1696 += method3879;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.H(" + b + ')');
        }
    }
    
    public static void method2851(final boolean b) {
        try {
            if (b) {
                Class225.aClass148_1695 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.E(" + b + ')');
        }
    }
    
    final void method2852(int n, int n2, final int n3, final byte[] array) throws IOException {
        try {
            try {
                if (~(n + this.aLong1697) < ~this.aLong1689) {
                    this.aLong1689 = this.aLong1697 - -n;
                }
                if (~this.aLong1693 != 0x0L && (this.aLong1697 < this.aLong1693 || ~this.aLong1697 < ~(this.anInt1698 + this.aLong1693))) {
                    this.method2845(-1);
                }
                if (this.aLong1693 != n3 && ~(this.aLong1693 + this.aByteArray1691.length) > ~(this.aLong1697 + n)) {
                    final int n4 = (int)(this.aByteArray1691.length - (this.aLong1697 + -this.aLong1693));
                    Class236.method2894(array, n2, this.aByteArray1691, (int)(this.aLong1697 - this.aLong1693), n4);
                    this.aLong1697 += n4;
                    n2 += n4;
                    n -= n4;
                    this.anInt1698 = this.aByteArray1691.length;
                    this.method2845(-1);
                }
                if (~n < ~this.aByteArray1691.length) {
                    if (~this.aLong1697 != ~this.aLong1688) {
                        this.aClass356_1692.method3877((byte)(-23), this.aLong1697);
                        this.aLong1688 = this.aLong1697;
                    }
                    this.aClass356_1692.method3882(array, n3 + 4658, n2, n);
                    this.aLong1688 += n;
                    if (this.aLong1690 < this.aLong1688) {
                        this.aLong1690 = this.aLong1688;
                    }
                    long n5 = -1L;
                    long n6 = -1L;
                    if (~this.aLong1694 >= ~this.aLong1697 && this.aLong1694 - -this.anInt1696 > this.aLong1697) {
                        n5 = this.aLong1697;
                    }
                    else if (~this.aLong1694 <= ~this.aLong1697 && n + this.aLong1697 > this.aLong1694) {
                        n5 = this.aLong1694;
                    }
                    if (this.aLong1694 < this.aLong1697 - -n && ~(this.anInt1696 + this.aLong1694) <= ~(this.aLong1697 + n)) {
                        n6 = n + this.aLong1697;
                    }
                    else if (this.aLong1697 < this.anInt1696 + this.aLong1694 && ~(this.aLong1694 + this.anInt1696) >= ~(this.aLong1697 + n)) {
                        n6 = this.aLong1694 - -this.anInt1696;
                    }
                    if (n5 > -1L && n6 > n5) {
                        Class236.method2894(array, (int)(-this.aLong1697 + n5 + n2), this.aByteArray1687, (int)(n5 + -this.aLong1694), (int)(-n5 + n6));
                    }
                    this.aLong1697 += n;
                }
                else if (n > 0) {
                    if (~this.aLong1693 == 0x0L) {
                        this.aLong1693 = this.aLong1697;
                    }
                    Class236.method2894(array, n2, this.aByteArray1691, (int)(this.aLong1697 - this.aLong1693), n);
                    this.aLong1697 += n;
                    if (~this.anInt1698 > ~(this.aLong1697 - this.aLong1693)) {
                        this.anInt1698 = (int)(this.aLong1697 + -this.aLong1693);
                    }
                }
            }
            catch (IOException ex) {
                this.aLong1688 = -1L;
                throw ex;
            }
        }
        catch (RuntimeException ex2) {
            throw Class64_Sub27.method667(ex2, "ofa.D(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class225(final Class356 aClass356_1692, final int n, final int n2) throws IOException {
        this.aLong1694 = -1L;
        this.aLong1693 = -1L;
        this.anInt1698 = 0;
        try {
            this.aClass356_1692 = aClass356_1692;
            final long method3878 = aClass356_1692.method3878((byte)(-65));
            this.aLong1690 = method3878;
            this.aLong1689 = method3878;
            this.aLong1697 = 0L;
            this.aByteArray1687 = new byte[n];
            this.aByteArray1691 = new byte[n2];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ofa.<init>(" + ((aClass356_1692 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class225.aClass148_1695 = new Class148();
    }
}
