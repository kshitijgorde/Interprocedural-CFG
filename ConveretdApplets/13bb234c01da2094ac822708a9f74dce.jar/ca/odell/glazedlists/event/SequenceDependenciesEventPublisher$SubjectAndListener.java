// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.event;

class SequenceDependenciesEventPublisher$SubjectAndListener
{
    private final Object b;
    private final Object c;
    private final SequenceDependenciesEventPublisher$EventFormat d;
    private Object e;
    static final /* synthetic */ boolean a;
    
    public SequenceDependenciesEventPublisher$SubjectAndListener(final Object b, final Object c, final SequenceDependenciesEventPublisher$EventFormat d) {
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public boolean a() {
        return this.e != null;
    }
    
    public void a(final Object e) {
        if (this.e != null) {
            throw new IllegalStateException();
        }
        if (e == null) {
            throw new IllegalStateException();
        }
        this.e = e;
    }
    
    public void b() {
        if (!SequenceDependenciesEventPublisher$SubjectAndListener.a && this.e == null) {
            throw new AssertionError();
        }
        try {
            this.d.a(this.b, this.e, this.c);
        }
        finally {
            this.e = null;
        }
    }
    
    public String toString() {
        return this.b + (this.a() ? ">>>" : "-->") + this.c;
    }
    
    static {
        a = !SequenceDependenciesEventPublisher.class.desiredAssertionStatus();
    }
}
