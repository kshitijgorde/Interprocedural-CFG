// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.log;

public abstract class F implements A
{
    protected jmaster.util.C.A A;
    static /* synthetic */ Class class$jmaster$util$log$E;
    
    public F() {
        this.A = new jmaster.util.C.A((F.class$jmaster$util$log$E == null) ? (F.class$jmaster$util$log$E = class$("jmaster.util.log.E")) : F.class$jmaster$util$log$E);
    }
    
    public void A(final E e) {
        this.A.C(e);
    }
    
    public void B(final E e) {
        this.A.A(e);
    }
    
    protected void A(final int n, final Object o, final Throwable t) {
        for (int i = this.A.C() - 1; i >= 0; --i) {
            ((E)this.A.A(i)).A(this, n, o, t);
        }
    }
    
    public void D(final Object o) {
        this.A(1, o, null);
    }
    
    public void C(final Object o, final Throwable t) {
        this.A(1, o, t);
    }
    
    public void E(final Object o) {
        this.A(3, o, null);
    }
    
    public void E(final Object o, final Throwable t) {
        this.A(3, o, t);
    }
    
    public void A(final Object o) {
        this.A(4, o, null);
    }
    
    public void D(final Object o, final Throwable t) {
        this.A(4, o, t);
    }
    
    public void B(final Object o) {
        this.A(0, o, null);
    }
    
    public void B(final Object o, final Throwable t) {
        this.A(0, o, t);
    }
    
    public void C(final Object o) {
        this.A(2, o, null);
    }
    
    public void A(final Object o, final Throwable t) {
        this.A(2, o, t);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
