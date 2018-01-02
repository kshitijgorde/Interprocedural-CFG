// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby;

import java.io.InputStream;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.builtin.IRubyObject;

public interface RubyRuntimeAdapter
{
    IRubyObject eval(final Ruby p0, final String p1);
    
    JavaEmbedUtils.EvalUnit parse(final Ruby p0, final String p1, final String p2, final int p3);
    
    JavaEmbedUtils.EvalUnit parse(final Ruby p0, final InputStream p1, final String p2, final int p3);
}
