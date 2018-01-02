// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;

public interface RubyObjectAdapter
{
    boolean isKindOf(final IRubyObject p0, final RubyModule p1);
    
    IRubyObject setInstanceVariable(final IRubyObject p0, final String p1, final IRubyObject p2);
    
    IRubyObject[] convertToJavaArray(final IRubyObject p0);
    
    RubyInteger convertToRubyInteger(final IRubyObject p0);
    
    IRubyObject getInstanceVariable(final IRubyObject p0, final String p1);
    
    RubyString convertToRubyString(final IRubyObject p0);
    
    IRubyObject callMethod(final IRubyObject p0, final String p1);
    
    IRubyObject callMethod(final IRubyObject p0, final String p1, final IRubyObject p2);
    
    IRubyObject callMethod(final IRubyObject p0, final String p1, final IRubyObject[] p2);
    
    IRubyObject callMethod(final IRubyObject p0, final String p1, final IRubyObject[] p2, final Block p3);
    
    IRubyObject callSuper(final IRubyObject p0, final IRubyObject[] p1);
    
    IRubyObject callSuper(final IRubyObject p0, final IRubyObject[] p1, final Block p2);
}
