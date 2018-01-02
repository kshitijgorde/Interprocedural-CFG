// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import ji.v1event.ar;
import java.awt.TextField;

public class ek extends TextField implements ar
{
    private boolean a;
    private boolean b;
    private String c;
    private String d;
    
    public ek(final String d, final String s) {
        super(s);
        this.a = true;
        this.b = false;
        this.c = null;
        this.d = null;
        this.d = d;
    }
    
    public ek(final String d, final String s, final int n) {
        super(s, n);
        this.a = true;
        this.b = false;
        this.c = null;
        this.d = null;
        this.d = d;
    }
    
    public ek(final String d, final int n) {
        super(n);
        this.a = true;
        this.b = false;
        this.c = null;
        this.d = null;
        this.d = d;
    }
    
    public ek(final String d) {
        this.a = true;
        this.b = false;
        this.c = null;
        this.d = null;
        this.d = d;
    }
    
    public void setEchoChar(final char echoChar) {
        super.setEchoChar(echoChar);
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
    
    public void transferFocus() {
    }
}
