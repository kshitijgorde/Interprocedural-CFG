// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow.analyses;

import java.util.Iterator;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.dataflow.FlowGraphNode;
import org.jruby.compiler.ir.representations.BasicBlock;
import org.jruby.compiler.ir.dataflow.DataFlowProblem;

public class BindingStorePlacementProblem extends DataFlowProblem
{
    public BindingStorePlacementProblem() {
        super(DF_Direction.FORWARD);
    }
    
    public String getName() {
        return "Binding Stores Placement Analysis";
    }
    
    public FlowGraphNode buildFlowGraphNode(final BasicBlock bb) {
        return new BindingStorePlacementNode(this, bb);
    }
    
    public String getDataFlowVarsForOutput() {
        return "";
    }
    
    public boolean scopeDefinesVariable(final Variable v) {
        return this.getCFG().definesLocalVariable(v);
    }
    
    public boolean scopeUsesVariable(final Variable v) {
        return this.getCFG().usesLocalVariable(v);
    }
    
    public void addStoreAndBindingAllocInstructions() {
        for (final FlowGraphNode n : this._fgNodes) {
            final BindingStorePlacementNode bspn = (BindingStorePlacementNode)n;
            bspn.addStoreAndBindingAllocInstructions();
        }
    }
}
