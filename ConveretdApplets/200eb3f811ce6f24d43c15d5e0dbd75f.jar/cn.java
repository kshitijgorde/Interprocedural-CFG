// 
// Decompiled by Procyon v0.5.30
// 

public class cn implements e, ay
{
    public ax a;
    public ce b;
    public int c;
    public boolean d;
    
    public cn(final ax a) {
        this.a = a;
        this.b = a.c();
        this.c = a.u();
        this.d = true;
        if (this.c > 0 && a.d() == 2) {
            a.at().a(this, System.currentTimeMillis() + this.c);
        }
    }
    
    public void produce() {
        if (this.d && this.a != null && this.b == this.a.c()) {
            if (ay.a.i()) {
                ay.a.g(this.a.as() + " sending keep-alive msg (" + this.c + ")");
            }
            c0.a(this.a, a3.a(this.a, this.a.a(this.a.aj().g("URI_STATUS"), null, null, null, false).toString()));
            if (this.a.c() == this.b) {
                this.a.at().a(this, System.currentTimeMillis() + this.c);
            }
        }
        else if (ay.a.i()) {
            ay.a.g(this.a.as() + " skipping keep-alive msg because isAlive=" + this.d);
        }
    }
    
    public void a() {
        this.d = false;
        this.a = null;
    }
}
