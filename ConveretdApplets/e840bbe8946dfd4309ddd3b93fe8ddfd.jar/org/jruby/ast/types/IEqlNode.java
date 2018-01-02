// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.types;

import org.jruby.runtime.Block;
import org.jruby.Ruby;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;

public interface IEqlNode
{
    boolean eql(final IRubyObject p0, final ThreadContext p1, final Ruby p2, final IRubyObject p3, final Block p4);
}
