// 
// Decompiled by Procyon v0.5.30
// 

public class j
{
    public boolean p;
    public boolean d;
    public boolean a;
    
    public final void p() {
        this.p = false;
        this.d = false;
        this.a = false;
    }
    
    public final boolean d() {
        return this.d || !this.p || this.a;
    }
    
    public final boolean p() {
        return !this.d || !this.p || this.a;
    }
    
    public j() {
        this.p();
    }
}
