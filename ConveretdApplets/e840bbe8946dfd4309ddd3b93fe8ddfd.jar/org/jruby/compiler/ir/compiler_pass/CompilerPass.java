// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass;

import org.jruby.compiler.ir.IRScope;

public interface CompilerPass
{
    boolean isPreOrder();
    
    void run(final IRScope p0);
}
