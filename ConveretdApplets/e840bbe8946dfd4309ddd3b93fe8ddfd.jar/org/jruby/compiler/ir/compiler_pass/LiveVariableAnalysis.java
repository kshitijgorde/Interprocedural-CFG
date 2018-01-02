// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.compiler_pass;

import java.util.Iterator;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.IRClosure;
import org.jruby.compiler.ir.dataflow.DataFlowProblem;
import org.jruby.compiler.ir.dataflow.analyses.LiveVariablesProblem;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.IRScope;

public class LiveVariableAnalysis implements CompilerPass
{
    public boolean isPreOrder() {
        return false;
    }
    
    public void run(final IRScope s) {
        if (!(s instanceof IRMethod)) {
            return;
        }
        final CFG c = ((IRMethod)s).getCFG();
        LiveVariablesProblem lvp = new LiveVariablesProblem();
        final String lvpName = lvp.getName();
        lvp.setup(c);
        lvp.compute_MOP_Solution();
        c.setDataFlowSolution(lvp.getName(), lvp);
        for (final IRClosure x : ((IRMethod)s).getClosures()) {
            final CFG xc = x.getCFG();
            if (xc != null) {
                lvp = (LiveVariablesProblem)xc.getDataFlowSolution(lvpName);
            }
            else {
                System.out.println("Null cfg for: " + x);
            }
        }
    }
}
