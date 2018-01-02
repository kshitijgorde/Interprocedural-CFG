import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class abljemfs
{
    private abljema a;
    private Component b;
    private boolean c;
    private Boolean d;
    private Boolean e;
    private boolean f;
    
    public abljemfs(final abljema a, final Component b, final boolean c) {
        this.f = false;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public boolean a(final boolean b) {
        return this.a(b, false);
    }
    
    public boolean b(final boolean b) {
        return this.a(b, true);
    }
    
    private boolean a(final boolean b, final boolean b2) {
        if (this.d != null) {
            final boolean booleanValue = this.d;
            this.d = null;
            if (!booleanValue) {
                if (this.f && this.b.isEnabled()) {
                    this.b.requestFocus();
                    this.f = false;
                }
                return false;
            }
        }
        else if (this.a != null && b) {
            this.d = Boolean.TRUE;
            if (this.c) {
                this.f = true;
            }
            this.a.fb.a(this.b, b2);
            return false;
        }
        return true;
    }
    
    public void a() {
        if (this.d != null) {
            this.d = Boolean.FALSE;
        }
        else if (this.f && this.b.isEnabled()) {
            this.b.requestFocus();
            this.f = false;
        }
    }
    
    public boolean c(final boolean b) {
        if (this.e != null) {
            final boolean booleanValue = this.e;
            this.e = null;
            if (!booleanValue) {
                if (this.f && this.b.isVisible()) {
                    this.b.requestFocus();
                    this.f = false;
                }
                return false;
            }
        }
        else if (this.a != null && b) {
            this.e = Boolean.TRUE;
            if (this.c) {
                this.f = true;
            }
            this.a.fb.c(this.b);
            return false;
        }
        return true;
    }
    
    public void b() {
        if (this.e != null) {
            this.e = Boolean.FALSE;
        }
        else if (this.f && this.b.isVisible()) {
            this.b.requestFocus();
            this.f = false;
        }
    }
}
