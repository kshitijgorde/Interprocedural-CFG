// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.HttpMapClient.m;

class s
{
    protected final int a;
    protected final int b;
    protected final int c;
    protected final int d;
    protected final int e;
    protected final int f;
    private double g;
    
    s(int a, int b, int c, final double g, final int d, int e) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.g = g;
        this.d = d;
        this.e = e;
        final int n = a;
        final int n2 = b;
        final int n3 = c;
        final int n4 = e;
        c = d;
        b = n3;
        final int n5;
        a = (n5 = n2);
        final int n6 = b;
        a = n5;
        final int n7 = n;
        final H h;
        this.f = (int)(b * (c / n4) * (Math.abs(H.b(h, (e = n6 / 2 + a) / 6000000.0) - H.b(h, (e = a) / 6000000.0)) / Math.abs(H.a(h = new H(), (e = n6 / 2 + n7) / 6000000.0) - H.a(h, (e = n7) / 6000000.0))));
    }
    
    public String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer()).append("centerX=").append(this.a);
        sb.append(",centerY=").append(this.b);
        sb.append(",unzoomedArcLatitude=").append(this.c);
        sb.append(",zoomLevel=").append(this.g);
        sb.append(",resolutionX=").append(this.d);
        sb.append(",resolutionY=").append(this.e);
        return sb.toString();
    }
    
    final void a(final double g) {
        this.g = g;
    }
    
    s(final s s) {
        this(s.a(), s.b(), s.c, s.g, s.d, s.e);
    }
    
    int a() {
        return this.a;
    }
    
    int b() {
        return this.b;
    }
    
    final int c() {
        return (int)(this.f / this.g);
    }
    
    final int d() {
        return (int)(this.c / this.g);
    }
    
    final int e() {
        return this.d;
    }
    
    final int f() {
        return this.e;
    }
    
    final m g() {
        return new m(this.a() / 6000000.0, this.b() / 6000000.0, this.c() / 6000000.0, this.d() / 6000000.0, this.d, this.e);
    }
}
