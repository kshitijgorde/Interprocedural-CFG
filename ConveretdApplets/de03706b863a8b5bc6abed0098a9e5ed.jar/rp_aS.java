import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_aS implements rp_fq
{
    public InputStream a;
    
    public rp_aS(final InputStream a) {
        this.a = a;
    }
    
    public final boolean a() {
        return this.a != null;
    }
    
    public final rp_eA a() {
        if (this.a == null) {
            return null;
        }
        try {
            final rp_eA rp_eA = new rp_eA();
            rp_eA.a(new BufferedReader(new InputStreamReader(this.a)));
            return rp_eA;
        }
        catch (IOException ex) {
            rp_C.a(1, "Error reading XML: " + ex);
            ex.printStackTrace();
            return null;
        }
        catch (rp_u rp_u) {
            rp_C.a(1, "Error parsing XML on line " + rp_u.a);
            return null;
        }
        finally {
            this.a = null;
        }
    }
}
