import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class ak extends b8
{
    public InputStream g_;
    public ay gz;
    
    public ak(final InputStream g_, final int n) {
        super(n);
        this.g_ = g_;
    }
    
    public final void fi(final ay gz) {
        this.gz = gz;
    }
    
    public final void d_() throws Exception {
        ca.md("Starting rx-chan: " + super.fw);
        while (true) {
            final ay eg = super.cp.eg(this.gz.h0());
            eg.h_(this.g_);
            super.cp.ef(eg);
        }
    }
    
    public final void fh() {
        try {
            this.g_.close();
        }
        catch (IOException ex) {}
    }
}
