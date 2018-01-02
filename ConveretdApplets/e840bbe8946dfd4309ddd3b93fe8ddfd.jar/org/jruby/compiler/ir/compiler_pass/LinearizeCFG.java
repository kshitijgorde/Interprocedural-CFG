// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass;

import org.jruby.compiler.ir.representations.BasicBlock;
import java.util.List;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.IRScope;

public class LinearizeCFG implements CompilerPass
{
    public boolean isPreOrder() {
        return true;
    }
    
    public void run(final IRScope s) {
        if (s instanceof IRExecutionScope) {
            final CFG cfg = ((IRExecutionScope)s).getCFG();
            final List<BasicBlock> bbs = cfg.linearize();
        }
    }
}
