// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.event;

class SequenceDependenciesEventPublisher$NoOpEventFormat implements SequenceDependenciesEventPublisher$EventFormat
{
    public static final SequenceDependenciesEventPublisher$EventFormat a;
    
    public void a(final Object o, final Object o2, final Object o3) {
        throw new UnsupportedOperationException();
    }
    
    public void a(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    public boolean a(final Object o, final Object o2) {
        return false;
    }
    
    static {
        a = new SequenceDependenciesEventPublisher$NoOpEventFormat();
    }
}
