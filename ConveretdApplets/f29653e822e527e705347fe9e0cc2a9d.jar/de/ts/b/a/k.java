// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.b.a;

import java.net.URL;

public class k extends a
{
    String af;
    String ag;
    
    public k(final String af) {
        this.af = null;
        this.ag = null;
        this.af = af;
        this.ag = "_top";
    }
    
    public k(final String af, final String ag) {
        this.af = null;
        this.ag = null;
        this.af = af;
        this.ag = ag;
    }
    
    public URL e() {
        URL url = null;
        if (this.af != null) {
            try {
                url = new URL(this.af);
            }
            catch (Exception ex) {
                url = null;
                ex.printStackTrace(System.err);
            }
        }
        return url;
    }
    
    public String d() {
        return this.ag;
    }
}
