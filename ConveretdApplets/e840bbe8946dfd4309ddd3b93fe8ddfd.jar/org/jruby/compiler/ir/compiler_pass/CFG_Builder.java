// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass;

import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.IRScope;

public class CFG_Builder implements CompilerPass
{
    public boolean isPreOrder() {
        return true;
    }
    
    public void run(final IRScope s) {
        if (s instanceof IRExecutionScope) {
            ((IRExecutionScope)s).buildCFG();
        }
    }
}
