// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow.analyses;

import java.util.Iterator;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.representations.CFG;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.List;
import org.jruby.compiler.ir.dataflow.FlowGraphNode;
import org.jruby.compiler.ir.representations.BasicBlock;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import org.jruby.compiler.ir.dataflow.DataFlowVar;
import org.jruby.compiler.ir.operands.Variable;
import java.util.HashMap;
import org.jruby.compiler.ir.dataflow.DataFlowProblem;

public class LiveVariablesProblem extends DataFlowProblem
{
    private HashMap<Variable, DataFlowVar> _dfVarMap;
    private HashMap<Integer, Variable> _varDfVarMap;
    private Collection<Variable> _varsLiveOnExit;
    private Set<Variable> _udVars;
    
    public String getName() {
        return "Live Variables Analysis";
    }
    
    public LiveVariablesProblem() {
        super(DF_Direction.BACKWARD);
        this._dfVarMap = new HashMap<Variable, DataFlowVar>();
        this._varDfVarMap = new HashMap<Integer, Variable>();
        this._udVars = new HashSet<Variable>();
    }
    
    public DataFlowVar getDFVar(final Variable v) {
        return this._dfVarMap.get(v);
    }
    
    public Variable getVariable(final int id) {
        return this._varDfVarMap.get(id);
    }
    
    public FlowGraphNode buildFlowGraphNode(final BasicBlock bb) {
        return new LiveVariableNode(this, bb);
    }
    
    private void addDFVar(final Variable v, final boolean recordVar) {
        final DataFlowVar dfv = new DataFlowVar(this);
        this._dfVarMap.put(v, dfv);
        this._varDfVarMap.put(dfv._id, v);
        if (recordVar) {
            this._udVars.add(v);
        }
    }
    
    public void addDFVar(final Variable v) {
        this.addDFVar(v, true);
    }
    
    public void initVarsLiveOnExit(final Collection<Variable> vars) {
        this._varsLiveOnExit = vars;
    }
    
    public List<Variable> getVarsLiveOnEntry() {
        final List<Variable> liveVars = new ArrayList<Variable>();
        final BitSet liveIn = ((LiveVariableNode)this.getFlowGraphNode(this._cfg.getEntryBB())).getLiveInBitSet();
        for (int i = 0; i < liveIn.size(); ++i) {
            if (liveIn.get(i)) {
                final Variable v = this.getVariable(i);
                liveVars.add(v);
            }
        }
        return liveVars;
    }
    
    public void setup(final CFG c) {
        super.setup(c);
        if (c.getScope() instanceof IRMethod) {
            c.setUpUseDefLocalVarMaps();
            for (final Variable v : c.usedLocalVarsFromClosures()) {
                this.addDFVar(v, true);
            }
            for (final Variable v : c.definedLocalVarsFromClosures()) {
                this.addDFVar(v, true);
            }
        }
        if (this._varsLiveOnExit != null && !this._varsLiveOnExit.isEmpty()) {
            for (final Variable v : this._varsLiveOnExit) {
                if (this.getDFVar(v) == null) {
                    this.addDFVar(v, false);
                }
            }
        }
    }
    
    public String getDataFlowVarsForOutput() {
        final StringBuffer buf = new StringBuffer();
        for (final Variable v : this._dfVarMap.keySet()) {
            buf.append("DF Var ").append(this._dfVarMap.get(v)._id).append(" = ").append(v).append("\n");
        }
        return buf.toString();
    }
    
    public void markDeadInstructions() {
        for (final FlowGraphNode n : this._fgNodes) {
            ((LiveVariableNode)n).markDeadInstructions();
        }
    }
    
    public Collection<Variable> getVarsLiveOnExit() {
        return this._varsLiveOnExit;
    }
    
    public boolean isDefinedOrUsed(final Variable v) {
        return this._udVars.contains(v);
    }
    
    public Set<Variable> allDefinedOrUsedVariables() {
        return this._udVars;
    }
    
    public Set<Variable> getAllVars() {
        return this._dfVarMap.keySet();
    }
}
