import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class s extends b8
{
    public OutputStream e8;
    public at e7;
    public boolean e6;
    
    public s(final OutputStream e8, final int n) {
        super(n);
        this.e8 = e8;
        this.e6 = false;
        this.e7 = new at();
    }
    
    public final at d1() {
        return this.e7;
    }
    
    public final void d0() {
        this.e6 = true;
        this.e7.hp();
    }
    
    public void d_() throws Exception {
        ca.md("Starting tx-chan: " + super.fw);
        while (!this.e6 || !this.e7.ho()) {
            this.e7.hm().hz(this.e8);
        }
        throw new Exception("CLOSE");
    }
}
