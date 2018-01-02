import java.io.IOException;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class b9 implements bw
{
    public bw l4;
    public OutputStream l3;
    public b5 e9;
    
    public b9(final b5 e9, final OutputStream l3) {
        this.l4 = e9.lp();
        this.l3 = l3;
        (this.e9 = e9).lq(this);
    }
    
    public final h ev() {
        return this.l4.ev();
    }
    
    public final void eu(final byte[] array) {
        this.l5(new String(array));
        this.l4.eu(array);
    }
    
    public final void et(final byte[] array) {
        this.l5(new String(array));
        this.l4.et(array);
    }
    
    public final void es(final String s) {
        this.l5(s);
        this.l4.es(s);
    }
    
    public final void er(final String s) {
        this.l5(s);
        this.l4.er(s);
    }
    
    public final void l5(final String s) {
        try {
            this.l3.write(s.getBytes());
            this.l3.flush();
        }
        catch (IOException ex) {}
    }
    
    public final void im() {
        this.e9.lq(this.l4);
        try {
            this.l3.close();
        }
        catch (IOException ex) {}
    }
    
    public final void eq(final b7 b7, final ci ci) {
        this.l4.eq(b7, ci);
    }
    
    public final void ep(final String s) {
        this.l4.ep(s);
    }
}
