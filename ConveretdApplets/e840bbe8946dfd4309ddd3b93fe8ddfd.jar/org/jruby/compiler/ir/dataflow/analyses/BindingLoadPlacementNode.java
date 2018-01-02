// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow.analyses;

import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.instructions.LoadFromBindingInstr;
import java.util.Iterator;
import org.jruby.compiler.ir.operands.Operand;
import java.util.ListIterator;
import java.util.List;
import org.jruby.compiler.ir.operands.LocalVariable;
import org.jruby.compiler.ir.IRClosure;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.instructions.CallInstr;
import org.jruby.compiler.ir.Operation;
import java.util.Collection;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.instructions.Instr;
import java.util.HashSet;
import org.jruby.compiler.ir.representations.BasicBlock;
import org.jruby.compiler.ir.dataflow.DataFlowProblem;
import org.jruby.compiler.ir.operands.Variable;
import java.util.Set;
import org.jruby.compiler.ir.dataflow.FlowGraphNode;

public class BindingLoadPlacementNode extends FlowGraphNode
{
    Set<Variable> _inReqdLoads;
    Set<Variable> _outReqdLoads;
    
    public BindingLoadPlacementNode(final DataFlowProblem prob, final BasicBlock n) {
        super(prob, n);
    }
    
    public void init() {
        this._inReqdLoads = new HashSet<Variable>();
        this._outReqdLoads = new HashSet<Variable>();
    }
    
    public void buildDataFlowVars(final Instr i) {
    }
    
    public void initSolnForNode() {
        if (this._bb == this._prob.getCFG().getExitBB()) {
            this._inReqdLoads = ((BindingLoadPlacementProblem)this._prob).getLoadsOnScopeExit();
        }
    }
    
    public void compute_MEET(final CFG.CFG_Edge edge, final FlowGraphNode pred) {
        final BindingLoadPlacementNode n = (BindingLoadPlacementNode)pred;
        this._inReqdLoads.addAll(n._outReqdLoads);
    }
    
    public boolean applyTransferFunction() {
        final BindingLoadPlacementProblem blp = (BindingLoadPlacementProblem)this._prob;
        Set<Variable> reqdLoads = new HashSet<Variable>(this._inReqdLoads);
        final List<Instr> instrs = this._bb.getInstrs();
        final ListIterator<Instr> it = instrs.listIterator(instrs.size());
        while (it.hasPrevious()) {
            final Instr i = it.previous();
            if (i.operation == Operation.BINDING_STORE) {
                continue;
            }
            final Variable r = i.getResult();
            if (r != null) {
                reqdLoads.remove(r);
            }
            if (i instanceof CallInstr) {
                final CallInstr call = (CallInstr)i;
                final Operand o = call.getClosureArg();
                if (o != null && o instanceof MetaObject) {
                    final IRClosure cl = (IRClosure)((MetaObject)o).scope;
                    final CFG cl_cfg = cl.getCFG();
                    final BindingLoadPlacementProblem cl_blp = new BindingLoadPlacementProblem();
                    cl_blp.initLoadsOnScopeExit(reqdLoads);
                    cl_blp.setup(cl_cfg);
                    cl_blp.compute_MOP_Solution();
                    cl_cfg.setDataFlowSolution(cl_blp.getName(), cl_blp);
                    if (call.requiresBinding()) {
                        reqdLoads.clear();
                    }
                    final Set<Variable> newReqdLoads = new HashSet<Variable>(reqdLoads);
                    for (final Variable v : reqdLoads) {
                        if (cl_blp.scopeDefinesVariable(v)) {
                            newReqdLoads.remove(v);
                        }
                    }
                    reqdLoads = newReqdLoads;
                }
                else if (call.requiresBinding()) {
                    reqdLoads.clear();
                }
            }
            for (final Variable x : i.getUsedVariables()) {
                if (x instanceof LocalVariable) {
                    reqdLoads.add(x);
                }
            }
        }
        if (this._bb == this._prob.getCFG().getEntryBB()) {
            reqdLoads.clear();
        }
        if (this._outReqdLoads.equals(reqdLoads)) {
            return false;
        }
        this._outReqdLoads = reqdLoads;
        return true;
    }
    
    public String toString() {
        return "";
    }
    
    public void addLoads() {
        final BindingLoadPlacementProblem blp = (BindingLoadPlacementProblem)this._prob;
        final IRExecutionScope s = blp.getCFG().getScope();
        final List<Instr> instrs = this._bb.getInstrs();
        final ListIterator<Instr> it = instrs.listIterator(instrs.size());
        Set<Variable> reqdLoads = new HashSet<Variable>(this._inReqdLoads);
        while (it.hasPrevious()) {
            final Instr i = it.previous();
            if (i.operation == Operation.BINDING_STORE) {
                continue;
            }
            final Variable r = i.getResult();
            if (r != null) {
                reqdLoads.remove(r);
            }
            if (i instanceof CallInstr) {
                final CallInstr call = (CallInstr)i;
                final Operand o = call.getClosureArg();
                if (o != null && o instanceof MetaObject) {
                    final CFG cl_cfg = ((IRClosure)((MetaObject)o).scope).getCFG();
                    final BindingLoadPlacementProblem cl_blp = (BindingLoadPlacementProblem)cl_cfg.getDataFlowSolution(blp.getName());
                    final Set<Variable> newReqdLoads = new HashSet<Variable>(reqdLoads);
                    it.next();
                    for (final Variable v : reqdLoads) {
                        if (cl_blp.scopeDefinesVariable(v)) {
                            it.add(new LoadFromBindingInstr(v, s, v.getName()));
                            it.previous();
                            newReqdLoads.remove(v);
                        }
                    }
                    it.previous();
                    reqdLoads = newReqdLoads;
                    ((BindingLoadPlacementProblem)cl_cfg.getDataFlowSolution(blp.getName())).addLoads();
                }
                else if (call.requiresBinding()) {
                    it.next();
                    for (final Variable v2 : reqdLoads) {
                        it.add(new LoadFromBindingInstr(v2, s, v2.getName()));
                        it.previous();
                    }
                    it.previous();
                    reqdLoads.clear();
                }
            }
            for (final Variable x : i.getUsedVariables()) {
                if (x instanceof LocalVariable) {
                    reqdLoads.add(x);
                }
            }
        }
        if (s instanceof IRClosure && this._bb == this._prob.getCFG().getEntryBB()) {
            for (final Variable v3 : reqdLoads) {
                if (blp.scopeUsesVariable(v3)) {
                    it.add(new LoadFromBindingInstr(v3, s, v3.getName()));
                }
            }
        }
    }
}
