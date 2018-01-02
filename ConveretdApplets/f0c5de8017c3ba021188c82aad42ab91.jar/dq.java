import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class dq
{
    private InputStream a;
    
    public dq(final InputStream a) {
        this.a = null;
        this.a = a;
    }
    
    public void a() {
        if (this.a != null) {
            try {
                this.a.close();
            }
            catch (Exception ex) {
                if (n.d()) {
                    n.d("Exception occured while closing stream ", ex);
                }
            }
        }
    }
}
