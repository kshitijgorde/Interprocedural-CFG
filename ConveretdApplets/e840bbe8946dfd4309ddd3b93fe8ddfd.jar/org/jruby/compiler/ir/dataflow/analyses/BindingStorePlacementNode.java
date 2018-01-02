// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow.analyses;

import java.util.ListIterator;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.instructions.BREAK_Instr;
import org.jruby.compiler.ir.instructions.ClosureReturnInstr;
import org.jruby.compiler.ir.instructions.StoreToBindingInstr;
import org.jruby.compiler.ir.instructions.AllocateBindingInstr;
import org.jruby.compiler.ir.dataflow.DataFlowConstants;
import org.jruby.compiler.ir.operands.Operand;
import java.util.Iterator;
import org.jruby.compiler.ir.operands.LocalVariable;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.instructions.CallInstr;
import org.jruby.compiler.ir.Operation;
import java.util.Collection;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.IRClosure;
import java.util.HashSet;
import org.jruby.compiler.ir.representations.BasicBlock;
import org.jruby.compiler.ir.dataflow.DataFlowProblem;
import org.jruby.compiler.ir.operands.Variable;
import java.util.Set;
import org.jruby.compiler.ir.dataflow.FlowGraphNode;

public class BindingStorePlacementNode extends FlowGraphNode
{
    Set<Variable> _inDirtyVars;
    Set<Variable> _outDirtyVars;
    boolean _inBindingAllocated;
    boolean _outBindingAllocated;
    
    public BindingStorePlacementNode(final DataFlowProblem prob, final BasicBlock n) {
        super(prob, n);
    }
    
    public void init() {
        this._inDirtyVars = new HashSet<Variable>();
        this._outDirtyVars = new HashSet<Variable>();
        if (this._prob.getCFG().getScope() instanceof IRClosure) {
            final boolean b = true;
            this._outBindingAllocated = b;
            this._inBindingAllocated = b;
        }
        else {
            final boolean b2 = false;
            this._outBindingAllocated = b2;
            this._inBindingAllocated = b2;
        }
    }
    
    public void buildDataFlowVars(final Instr i) {
    }
    
    public void initSolnForNode() {
    }
    
    public void compute_MEET(final CFG.CFG_Edge edge, final FlowGraphNode pred) {
        final BindingStorePlacementNode n = (BindingStorePlacementNode)pred;
        this._inDirtyVars.addAll(n._outDirtyVars);
        this._inBindingAllocated = (this._inBindingAllocated && n._outBindingAllocated);
    }
    
    public boolean applyTransferFunction() {
        boolean bindingAllocated = this._inBindingAllocated;
        final BindingStorePlacementProblem bsp = (BindingStorePlacementProblem)this._prob;
        Set<Variable> dirtyVars = new HashSet<Variable>(this._inDirtyVars);
        for (final Instr i : this._bb.getInstrs()) {
            if (i.operation == Operation.BINDING_LOAD) {
                continue;
            }
            if (i instanceof CallInstr) {
                final CallInstr call = (CallInstr)i;
                final Operand o = call.getClosureArg();
                if (o != null && o instanceof MetaObject) {
                    bindingAllocated = true;
                    final IRClosure cl = (IRClosure)((MetaObject)o).scope;
                    final CFG cl_cfg = cl.getCFG();
                    final BindingStorePlacementProblem cl_bsp = new BindingStorePlacementProblem();
                    cl_bsp.setup(cl_cfg);
                    cl_bsp.compute_MOP_Solution();
                    cl_cfg.setDataFlowSolution(cl_bsp.getName(), cl_bsp);
                    final boolean spillAllVars = call.canBeEval() || call.canCaptureCallersBinding();
                    final Set<Variable> newDirtyVars = new HashSet<Variable>(dirtyVars);
                    for (final Variable v : dirtyVars) {
                        if (spillAllVars || cl_bsp.scopeUsesVariable(v) || cl_bsp.scopeDefinesVariable(v)) {
                            newDirtyVars.remove(v);
                        }
                    }
                    dirtyVars = newDirtyVars;
                }
                else if (call.requiresBinding()) {
                    dirtyVars.clear();
                    bindingAllocated = true;
                }
            }
            final Variable v2 = i.getResult();
            if (v2 != null && v2 instanceof LocalVariable) {
                dirtyVars.add(v2);
            }
            if (!i.operation.isReturn()) {
                continue;
            }
            dirtyVars.clear();
        }
        if (this._outDirtyVars.equals(dirtyVars) && this._outBindingAllocated == bindingAllocated) {
            return false;
        }
        this._outDirtyVars = dirtyVars;
        this._outBindingAllocated = bindingAllocated;
        return true;
    }
    
    public String toString() {
        return "";
    }
    
    public void addStoreAndBindingAllocInstructions() {
        final BindingStorePlacementProblem bsp = (BindingStorePlacementProblem)this._prob;
        final CFG cfg = bsp.getCFG();
        final IRExecutionScope s = cfg.getScope();
        final ListIterator<Instr> instrs = this._bb.getInstrs().listIterator();
        Set<Variable> dirtyVars = new HashSet<Variable>(this._inDirtyVars);
        boolean bindingAllocated = this._inBindingAllocated;
        final boolean amExitBB = this._bb == cfg.getExitBB();
        if (amExitBB) {
            final LiveVariablesProblem lvp = (LiveVariablesProblem)cfg.getDataFlowSolution(DataFlowConstants.LVP_NAME);
            final Collection<Variable> liveVars = lvp.getVarsLiveOnExit();
            if (liveVars != null) {
                dirtyVars.retainAll(liveVars);
            }
            else {
                dirtyVars.clear();
            }
        }
        while (instrs.hasNext()) {
            final Instr i = instrs.next();
            if (i.operation == Operation.BINDING_LOAD) {
                continue;
            }
            if (i instanceof CallInstr) {
                final CallInstr call = (CallInstr)i;
                final Operand o = call.getClosureArg();
                if (o != null && o instanceof MetaObject) {
                    final CFG cl_cfg = ((IRClosure)((MetaObject)o).scope).getCFG();
                    final BindingStorePlacementProblem cl_bsp = (BindingStorePlacementProblem)cl_cfg.getDataFlowSolution(bsp.getName());
                    instrs.previous();
                    if (!bindingAllocated) {
                        instrs.add(new AllocateBindingInstr(s));
                        bindingAllocated = true;
                    }
                    final boolean spillAllVars = call.canBeEval() || call.canCaptureCallersBinding();
                    final Set<Variable> newDirtyVars = new HashSet<Variable>(dirtyVars);
                    for (final Variable v : dirtyVars) {
                        if (spillAllVars || cl_bsp.scopeUsesVariable(v)) {
                            instrs.add(new StoreToBindingInstr(s, v.getName(), v));
                            newDirtyVars.remove(v);
                        }
                        else {
                            if (!cl_bsp.scopeDefinesVariable(v)) {
                                continue;
                            }
                            newDirtyVars.remove(v);
                        }
                    }
                    dirtyVars = newDirtyVars;
                    instrs.next();
                    ((BindingStorePlacementProblem)cl_cfg.getDataFlowSolution(bsp.getName())).addStoreAndBindingAllocInstructions();
                }
                else if (call.requiresBinding()) {
                    instrs.previous();
                    if (!bindingAllocated) {
                        instrs.add(new AllocateBindingInstr(s));
                        bindingAllocated = true;
                    }
                    for (final Variable v2 : dirtyVars) {
                        instrs.add(new StoreToBindingInstr(s, v2.getName(), v2));
                    }
                    instrs.next();
                    dirtyVars.clear();
                }
            }
            else if (i instanceof ClosureReturnInstr || i instanceof BREAK_Instr) {
                if (!amExitBB) {
                    final LiveVariablesProblem lvp2 = (LiveVariablesProblem)cfg.getDataFlowSolution(DataFlowConstants.LVP_NAME);
                    final Collection<Variable> liveVars2 = lvp2.getVarsLiveOnExit();
                    if (liveVars2 != null) {
                        dirtyVars.retainAll(liveVars2);
                    }
                    else {
                        dirtyVars.clear();
                    }
                }
                instrs.previous();
                for (final Variable v3 : dirtyVars) {
                    instrs.add(new StoreToBindingInstr(s, v3.getName(), v3));
                }
                instrs.next();
                dirtyVars.clear();
            }
            final Variable v4 = i.getResult();
            if (v4 == null || !(v4 instanceof LocalVariable)) {
                continue;
            }
            dirtyVars.add(v4);
        }
        if (amExitBB) {
            for (final Variable v4 : dirtyVars) {
                instrs.add(new StoreToBindingInstr(s, v4.getName(), v4));
            }
        }
    }
}
