// 
// Decompiled by Procyon v0.5.30
// 

public interface WvPTZBListener
{
    void ptzbChanged(final WvPTZB p0);
    
    void ptzbChangedInternal(final Object p0, final int p1, final int p2);
    
    void ptzbChangedByAnotherClient(final WvPTZB p0);
}
