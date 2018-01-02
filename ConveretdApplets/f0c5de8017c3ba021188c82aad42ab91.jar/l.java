// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    public int a;
    
    public l() {
        this.a = 0;
    }
    
    public l(final int a) {
        this.a = 0;
        this.a = a;
    }
    
    public final l a() {
        ++this.a;
        return this;
    }
    
    public final void a(final int a) {
        this.a = a;
        if (n.d()) {
            n.d("status@" + this.hashCode() + ":new value is:" + this.a);
        }
    }
    
    public final int b() {
        return this.a;
    }
    
    public final String toString() {
        return Integer.toString(this.a);
    }
}
