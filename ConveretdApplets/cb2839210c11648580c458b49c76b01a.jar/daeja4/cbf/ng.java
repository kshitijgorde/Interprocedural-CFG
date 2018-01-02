// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.cbf;

public abstract class ng
{
    public static final String a;
    private String b;
    protected boolean c;
    protected String d;
    private int e;
    private int f;
    private boolean g;
    private int h;
    private int i;
    private long j;
    private int k;
    private int l;
    private long m;
    private long n;
    private long o;
    private long p;
    private long[] q;
    
    public ng(final byte[] array) {
        if (array.length < 512) {
            throw new IllegalArgumentException(String.valueOf(String.valueOf(new StringBuffer("Invalid header array: must be at least 512 bytes, but was ").append(array.length))));
        }
        this.b = nh.d(array, 0, 8);
        this.c = ng.a.equals(this.b);
        this.d = nh.e(array, 8, 16);
        this.e = nh.a(array, 24, 2);
        this.f = nh.a(array, 26, 2);
        this.g = this.b(array);
        this.h = nh.a(array, 30, 2);
        this.k = nh.a(array, 32, 2);
        this.i = nh.a(array, 44, 4, "Fat chain length");
        this.j = nh.b(array, 48, 4);
        this.l = nh.a(array, 56, 4, "mini-stream max size");
        this.m = nh.b(array, 60, 4);
        this.n = nh.b(array, 64, 4);
        this.o = nh.b(array, 68, 4);
        this.p = nh.b(array, 72, 4);
        this.q = a(array);
    }
    
    public long[] a() {
        return this.q;
    }
    
    public String b() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.f))).append(".").append(this.e)));
    }
    
    public int c() {
        return this.i;
    }
    
    public long d() {
        return this.j;
    }
    
    public int e() {
        return this.l;
    }
    
    public long f() {
        return this.m;
    }
    
    public long g() {
        return this.o;
    }
    
    private final boolean b(final byte[] array) {
        return ((array[29] & 0xFF) << 8 | (array[28] & 0xFF)) == 0xFFFE;
    }
    
    public final int h() {
        return this.h;
    }
    
    public final int i() {
        return (int)Math.pow(2.0, this.h);
    }
    
    public final int j() {
        return this.k;
    }
    
    public final int k() {
        return (int)Math.pow(2.0, this.k);
    }
    
    public static long[] a(final byte[] array) {
        final long[] array2 = new long[109];
        int n = 76;
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = nh.b(array, n, 4);
            n += 4;
        }
        return array2;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("jiHeader:  \n\tsignature=");
        sb.append(this.b).append(";  \n\tvalidSignature=").append(this.c).append(";  \n\tCLSID=").append(this.d).append(";  \n\tversion=").append(this.b()).append(";  \n\tintelByteOrder=").append(this.g).append(";  \n\tsectorShift=").append(this.h).append(";  \n\tFAT chain length=").append(this.i).append(";  \n\tfirstDirSector=");
        nh.a(sb, this.j).append(";  \n\tminiSectorShift=").append(this.k).append(";  \n\tminiStreamMaxSize=").append(this.l).append(";  \n\tfirstMiniSector=");
        nh.a(sb, this.m).append(";  \n\tnumberOfMiniSectors=").append(this.n).append(";  \n\tfirstDifSectorIndex=");
        nh.b(sb, this.o).append(";  \n\tnumberOfDifSectors=");
        nh.a(sb, this.p).append("; \n\tFAT 109 =");
        for (int i = 0; i < 109; ++i) {
            if (i > 0) {
                sb.append("\n\t\t");
            }
            sb.append(i).append(": ");
            nh.b(sb, this.q[i]);
        }
        return sb.toString();
    }
    
    static {
        a = new String(new char[] { '\u00d0', '\u00cf', '\u0011', '\u00e0', '¡', '±', '\u001a', '\u00e1' });
    }
}
