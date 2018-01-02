// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.executable;

import org.jruby.runtime.Block;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;

public interface Script
{
    IRubyObject __file__(final ThreadContext p0, final IRubyObject p1, final Block p2);
    
    IRubyObject __file__(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final Block p3);
    
    IRubyObject __file__(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final Block p4);
    
    IRubyObject __file__(final ThreadContext p0, final IRubyObject p1, final IRubyObject p2, final IRubyObject p3, final IRubyObject p4, final Block p5);
    
    IRubyObject __file__(final ThreadContext p0, final IRubyObject p1, final IRubyObject[] p2, final Block p3);
    
    IRubyObject run(final ThreadContext p0, final IRubyObject p1, final IRubyObject[] p2, final Block p3);
    
    IRubyObject load(final ThreadContext p0, final IRubyObject p1, final IRubyObject[] p2, final Block p3);
    
    void setFilename(final String p0);
}
