// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gif;

public class gifImage
{
    public j a;
    public int b;
    private int c;
    private int d;
    private int e;
    private int f;
    public int g;
    public int h;
    public boolean i;
    
    public gifImage() {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = -1;
        this.i = false;
    }
    
    public gifImage(final j a, final int c, final int d, final int n, final int g, final int h, final boolean i) {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = -1;
        this.i = false;
        this.a = a;
        this.e = ((this.a != null) ? this.a.h() : 0);
        this.f = ((this.a != null) ? this.a.c() : 0);
        this.c = c;
        this.d = d;
        this.b = n * 10;
        this.g = g;
        this.h = h;
        this.i = i;
    }
    
    public j frame() {
        return this.a;
    }
}
