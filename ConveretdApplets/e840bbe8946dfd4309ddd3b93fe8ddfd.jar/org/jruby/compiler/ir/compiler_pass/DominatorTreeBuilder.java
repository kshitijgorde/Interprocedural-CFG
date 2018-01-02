// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass;

import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.IRScope;

public class DominatorTreeBuilder implements CompilerPass
{
    public boolean isPreOrder() {
        return false;
    }
    
    public void run(final IRScope s) {
        if (s instanceof IRExecutionScope) {
            final CFG c = ((IRExecutionScope)s).getCFG();
            try {
                c.buildDominatorTree();
            }
            catch (Exception e) {
                System.out.println("Caught exception building dom tree for " + c.getGraph());
                System.out.println("\nInstructions:\n" + c.toStringInstrs());
            }
        }
    }
}
