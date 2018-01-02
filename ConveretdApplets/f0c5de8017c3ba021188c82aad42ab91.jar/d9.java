import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class d9 extends Vector
{
    public String a;
    public int b;
    
    public d9(final String a) {
        this.b = Integer.MAX_VALUE;
        this.a = a;
    }
    
    public void a(final d7 d7) {
        this.addElement(d7);
        if (d7.f < this.b) {
            this.b = d7.f;
        }
    }
}
