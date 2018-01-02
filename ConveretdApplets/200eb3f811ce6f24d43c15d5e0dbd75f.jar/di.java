import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class di implements e
{
    private final /* synthetic */ String a;
    private final /* synthetic */ Vector b;
    private final /* synthetic */ x c;
    private final /* synthetic */ bf d;
    private final /* synthetic */ bb e;
    private final /* synthetic */ int f;
    private final /* synthetic */ ba g;
    
    public di(final ba g, final String a, final Vector b, final x c, final bf d, final bb e, final int f) {
        this.g = g;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public void produce() {
        if (ak.a.k()) {
            ak.a.i("HttpURLRequestHandler.produce()");
        }
        try {
            new dm().a(this.g.a(this.a, this.b), this.c, this.d, this.e, this.g.b, this.f);
        }
        catch (Exception ex) {
            if (ak.a.i()) {
                ak.a.e("error while producing nonblocking get ", ex);
            }
            dj.a(this.d, this.g.b, ex, this.f);
        }
    }
}
