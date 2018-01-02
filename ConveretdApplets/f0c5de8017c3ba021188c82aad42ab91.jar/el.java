import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class el implements e
{
    private final /* synthetic */ l a;
    private final /* synthetic */ int b;
    private final /* synthetic */ dt c;
    
    public el(final dt c, final l a, final int b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    public void produce() {
        try {
            if (dt.f(this.c).a(new do(dt.a(this.c).a()), dt.a(this.c), dt.b(this.c), dt.c(this.c), dt.d(this.c), dt.e(this.c)) == 1) {
                this.a.a(0);
            }
        }
        catch (Exception ex) {
            if (ak.a.l()) {
                ak.a.h("inner produce: handleError with " + ex, ex);
            }
            dj.a(dt.c(this.c), dt.d(this.c).b, ex, this.b);
            this.a.a(0);
        }
    }
}
