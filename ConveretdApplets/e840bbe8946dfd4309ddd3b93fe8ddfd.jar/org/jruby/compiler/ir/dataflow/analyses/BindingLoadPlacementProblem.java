// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow.analyses;

import java.util.Iterator;
import org.jruby.compiler.ir.dataflow.FlowGraphNode;
import org.jruby.compiler.ir.representations.BasicBlock;
import java.util.HashSet;
import org.jruby.compiler.ir.operands.Variable;
import java.util.Set;
import org.jruby.compiler.ir.dataflow.DataFlowProblem;

public class BindingLoadPlacementProblem extends DataFlowProblem
{
    private Set<Variable> _initLoadsOnExit;
    private boolean _bindingHasEscaped;
    
    public BindingLoadPlacementProblem() {
        super(DF_Direction.BACKWARD);
        this._initLoadsOnExit = new HashSet<Variable>();
        this._bindingHasEscaped = false;
    }
    
    public String getName() {
        return "Binding Loads Placement Analysis";
    }
    
    public FlowGraphNode buildFlowGraphNode(final BasicBlock bb) {
        return new BindingLoadPlacementNode(this, bb);
    }
    
    public String getDataFlowVarsForOutput() {
        return "";
    }
    
    public void initLoadsOnScopeExit(final Set<Variable> loads) {
        this._initLoadsOnExit = loads;
    }
    
    public Set<Variable> getLoadsOnScopeExit() {
        return this._initLoadsOnExit;
    }
    
    public boolean bindingHasEscaped() {
        return this._bindingHasEscaped;
    }
    
    public void setBindingHasEscaped(final boolean flag) {
        this._bindingHasEscaped = flag;
    }
    
    public boolean scopeDefinesVariable(final Variable v) {
        return this.getCFG().definesLocalVariable(v);
    }
    
    public boolean scopeUsesVariable(final Variable v) {
        return this.getCFG().usesLocalVariable(v);
    }
    
    public void addLoads() {
        for (final FlowGraphNode n : this._fgNodes) {
            final BindingLoadPlacementNode blpn = (BindingLoadPlacementNode)n;
            blpn.addLoads();
        }
    }
}
