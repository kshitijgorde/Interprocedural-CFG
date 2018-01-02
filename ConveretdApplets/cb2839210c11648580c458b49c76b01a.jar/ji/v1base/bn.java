// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import ji.util.d;
import java.awt.Component;
import ji.util.e;
import ji.v1event.ar;
import java.awt.Button;

public class bn extends Button implements ar
{
    private boolean a;
    private boolean b;
    private String c;
    private String d;
    
    public bn(final String d) {
        this.a = true;
        this.b = false;
        this.c = null;
        this.d = null;
        e.a(this.d = d, this);
        ji.util.d.a(d, this);
        e.a(d, this, true);
    }
    
    public bn(final String d, final String s) {
        super(s);
        this.a = true;
        this.b = false;
        this.c = null;
        this.d = null;
        e.a(this.d = d, this);
        ji.util.d.a(d, this);
        e.a(d, this, true);
    }
    
    public boolean ignoreTAB() {
        return this.b;
    }
    
    public boolean acceptFocus() {
        return this.a;
    }
    
    public void a(final String c) {
        this.c = c;
    }
    
    public String getFocusRingId() {
        return this.c;
    }
    
    public void a() {
        e.b(this.d, this);
        ji.util.d.b(this.d, this);
        ji.util.d.d(this);
    }
    
    public void transferFocus() {
    }
}
