// 
// Decompiled by Procyon v0.5.30
// 

package com.kaon.meson;

public interface Gluon
{
    void init(final Blackboard p0) throws MesonException;
    
    void reinit(final Blackboard p0);
    
    Object builtIn(final Blackboard p0, final String p1) throws Exception;
    
    void restoring(final Blackboard p0);
    
    void destroy(final Blackboard p0);
}
