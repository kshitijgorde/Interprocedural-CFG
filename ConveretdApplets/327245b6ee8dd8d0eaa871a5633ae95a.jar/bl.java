// 
// Decompiled by Procyon v0.5.30
// 

public class bl
{
    c a;
    String b;
    ar c;
    au d;
    int e;
    long f;
    
    void a() {
        if (this.b != null) {
            this.a.a(this.b, this.d);
            if (!c.l) {
                return;
            }
        }
        this.c.b(this.a, this.d, null, false);
    }
    
    boolean a(final long f) {
        if (f - this.f > this.e) {
            this.a();
            this.f = f;
            return true;
        }
        return false;
    }
}
