// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass;

import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.dataflow.analyses.BindingLoadPlacementProblem;
import org.jruby.compiler.ir.dataflow.analyses.BindingStorePlacementProblem;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.IRScope;

public class AddBindingInstructions implements CompilerPass
{
    public boolean isPreOrder() {
        return false;
    }
    
    public void run(final IRScope s) {
        if (!(s instanceof IRMethod)) {
            return;
        }
        final IRMethod m = (IRMethod)s;
        final CFG c = m.getCFG();
        final BindingStorePlacementProblem fsp = new BindingStorePlacementProblem();
        fsp.setup(c);
        fsp.compute_MOP_Solution();
        fsp.addStoreAndBindingAllocInstructions();
        final BindingLoadPlacementProblem frp = new BindingLoadPlacementProblem();
        frp.setup(c);
        frp.compute_MOP_Solution();
        frp.addLoads();
    }
}
