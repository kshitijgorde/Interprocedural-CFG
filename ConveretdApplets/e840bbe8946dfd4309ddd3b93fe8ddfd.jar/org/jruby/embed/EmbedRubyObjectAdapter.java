// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed;

import org.jruby.runtime.Block;
import org.jruby.RubyObjectAdapter;

public interface EmbedRubyObjectAdapter extends RubyObjectAdapter
{
    Object callMethod(final Object p0, final String p1, final Object... p2);
    
    Object callMethod(final Object p0, final String p1, final Block p2, final Object... p3);
    
     <T> T callMethod(final Object p0, final String p1, final Class<T> p2);
    
     <T> T callMethod(final Object p0, final String p1, final Object p2, final Class<T> p3);
    
     <T> T callMethod(final Object p0, final String p1, final Object[] p2, final Class<T> p3);
    
     <T> T callMethod(final Object p0, final String p1, final Object[] p2, final Block p3, final Class<T> p4);
    
     <T> T callMethod(final Object p0, final String p1, final Class<T> p2, final EmbedEvalUnit p3);
    
     <T> T callMethod(final Object p0, final String p1, final Object[] p2, final Class<T> p3, final EmbedEvalUnit p4);
    
     <T> T callMethod(final Object p0, final String p1, final Object[] p2, final Block p3, final Class<T> p4, final EmbedEvalUnit p5);
    
     <T> T callSuper(final Object p0, final Object[] p1, final Class<T> p2);
    
     <T> T callSuper(final Object p0, final Object[] p1, final Block p2, final Class<T> p3);
}
