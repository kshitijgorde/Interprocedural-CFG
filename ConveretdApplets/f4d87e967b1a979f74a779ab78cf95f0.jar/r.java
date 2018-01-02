import en.JavaFX;

// 
// Decompiled by Procyon v0.5.30
// 

public class r extends t
{
    public i p;
    JavaFX d;
    boolean a;
    long n;
    long v;
    long i;
    long l;
    
    public r(final JavaFX d) {
        this.p = new i("pixels", this, 1, false);
        this.a = false;
        this.d = d;
    }
    
    public final boolean execute() {
        this.d.update(this.p.a);
        this.p();
        return true;
    }
    
    private final void p() {
        if (this.a) {
            if (this.n == 0) {
                this.n = System.currentTimeMillis();
            }
            this.v = System.currentTimeMillis();
            ++this.l;
            if (this.v - this.n > this.i) {
                System.out.println(String.valueOf(String.valueOf((int)(1000.0f * this.l / (this.v - this.n)))).concat("FPS"));
                this.n = this.v;
                this.l = 0L;
            }
        }
    }
}
