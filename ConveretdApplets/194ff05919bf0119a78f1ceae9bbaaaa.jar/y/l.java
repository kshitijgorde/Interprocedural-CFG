// 
// Decompiled by Procyon v0.5.30
// 

package y;

public final class l
{
    long a;
    private long b;
    String a;
    String b;
    int a;
    String c;
    boolean a;
    cu a;
    
    public l(final String a, final String b, final long b2, final String c, final cu a2) {
        this.a = 0L;
        this.b = b2;
        this.a = 0;
        this.a = a;
        this.b = b;
        this.c = c;
        this.a = true;
        this.a = a2;
    }
    
    public l(final long b, final cu a) {
        this.a = 0L;
        this.b = b;
        this.a = 0;
        this.a = false;
        this.a = a;
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer(super.toString())).append(";adId");
        sb.append(this.b);
        sb.append(";location=");
        sb.append(this.a);
        sb.append(";rotatePeriod=");
        sb.append(this.b);
        sb.append(";pageId=");
        sb.append(this.c);
        sb.append(";timeLastShown=");
        sb.append(this.a);
        sb.append(";numRequests");
        sb.append(this.a);
        return sb.toString();
    }
    
    final long a() {
        long n;
        if ((n = this.a + this.b - System.currentTimeMillis()) < 0L) {
            n = 0L;
        }
        return n;
    }
}
