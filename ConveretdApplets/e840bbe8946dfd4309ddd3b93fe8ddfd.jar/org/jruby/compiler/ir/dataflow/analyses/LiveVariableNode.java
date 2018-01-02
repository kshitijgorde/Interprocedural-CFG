// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow.analyses;

import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.dataflow.DataFlowVar;
import java.util.ListIterator;
import java.util.List;
import java.util.ArrayList;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.instructions.CallInstr;
import org.jruby.compiler.ir.IRClosure;
import org.jruby.compiler.ir.representations.CFG;
import java.util.Collection;
import java.util.Iterator;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.representations.BasicBlock;
import org.jruby.compiler.ir.dataflow.DataFlowProblem;
import java.util.BitSet;
import org.jruby.compiler.ir.dataflow.FlowGraphNode;

public class LiveVariableNode extends FlowGraphNode
{
    private BitSet _in;
    private BitSet _out;
    private BitSet _tmp;
    private int _setSize;
    
    public LiveVariableNode(final DataFlowProblem prob, final BasicBlock n) {
        super(prob, n);
    }
    
    public void init() {
        this._setSize = this._prob.getDFVarsCount();
        this._in = new BitSet(this._setSize);
    }
    
    private void addDFVar(final Variable v) {
        final LiveVariablesProblem lvp = (LiveVariablesProblem)this._prob;
        if (v != null && lvp.getDFVar(v) == null) {
            lvp.addDFVar(v);
        }
    }
    
    public void buildDataFlowVars(final Instr i) {
        this.addDFVar(i.getResult());
        for (final Variable x : i.getUsedVariables()) {
            this.addDFVar(x);
        }
    }
    
    public void initSolnForNode() {
        final LiveVariablesProblem p = (LiveVariablesProblem)this._prob;
        this._tmp = new BitSet(this._setSize);
        if (this._bb == p.getCFG().getExitBB()) {
            final Collection<Variable> lv = p.getVarsLiveOnExit();
            if (lv != null && !lv.isEmpty()) {
                for (final Variable v : lv) {
                    this._tmp.set(p.getDFVar(v)._id);
                }
            }
        }
    }
    
    public void compute_MEET(final CFG.CFG_Edge edge, final FlowGraphNode pred) {
        this._tmp.or(((LiveVariableNode)pred)._in);
    }
    
    private LiveVariablesProblem processClosure(final IRClosure cl, final Collection<Variable> liveOnEntry) {
        final CFG c = cl.getCFG();
        final LiveVariablesProblem lvp = new LiveVariablesProblem();
        lvp.initVarsLiveOnExit(liveOnEntry);
        lvp.setup(c);
        lvp.compute_MOP_Solution();
        c.setDataFlowSolution(lvp.getName(), lvp);
        return lvp;
    }
    
    public boolean applyTransferFunction() {
        final LiveVariablesProblem lvp = (LiveVariablesProblem)this._prob;
        this._out = (BitSet)this._tmp.clone();
        final List<Instr> instrs = this._bb.getInstrs();
        final ListIterator<Instr> it = instrs.listIterator(instrs.size());
        while (it.hasPrevious()) {
            final Instr i = it.previous();
            final Variable v = i.getResult();
            if (v != null) {
                final DataFlowVar dv = lvp.getDFVar(v);
                this._tmp.clear(dv._id);
            }
            if (i instanceof CallInstr) {
                final CallInstr c = (CallInstr)i;
                final Operand o = c.getClosureArg();
                if (o != null && o instanceof MetaObject) {
                    final IRClosure cl = (IRClosure)((MetaObject)o).scope;
                    if (c.isLVADataflowBarrier()) {
                        this.processClosure(cl, lvp.getAllVars());
                        for (int j = 0; j < this._setSize; ++j) {
                            this._tmp.set(j);
                        }
                    }
                    else {
                        final List<Variable> liveVars = new ArrayList<Variable>();
                        for (int k = 0; k < this._tmp.size(); ++k) {
                            if (this._tmp.get(k)) {
                                liveVars.add(lvp.getVariable(k));
                            }
                        }
                        final LiveVariablesProblem xlvp = this.processClosure(cl, liveVars);
                        for (final Variable y : xlvp.getVarsLiveOnEntry()) {
                            final DataFlowVar dv2 = lvp.getDFVar(y);
                            if (dv2 != null) {
                                this._tmp.set(dv2._id);
                            }
                        }
                    }
                }
                else if (c.isLVADataflowBarrier()) {
                    for (int l = 0; l < this._setSize; ++l) {
                        this._tmp.set(l);
                    }
                }
            }
            for (final Variable x : i.getUsedVariables()) {
                final DataFlowVar dv3 = lvp.getDFVar(x);
                if (dv3 != null) {
                    this._tmp.set(dv3._id);
                }
            }
        }
        if (this._tmp.equals(this._in)) {
            return false;
        }
        this._in = this._tmp;
        return true;
    }
    
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        buf.append("\tVars Live on Entry: ");
        int count = 0;
        for (int i = 0; i < this._in.size(); ++i) {
            if (this._in.get(i)) {
                ++count;
                buf.append(' ').append(i);
                if (count % 10 == 0) {
                    buf.append("\t\n");
                }
            }
        }
        if (count % 10 != 0) {
            buf.append("\t\t");
        }
        buf.append("\n\tVars Live on Exit: ");
        count = 0;
        for (int i = 0; i < this._out.size(); ++i) {
            if (this._out.get(i)) {
                ++count;
                buf.append(' ').append(i);
                if (count % 10 == 0) {
                    buf.append("\t\n");
                }
            }
        }
        if (count % 10 != 0) {
            buf.append("\t\t");
        }
        return buf.append('\n').toString();
    }
    
    void markDeadInstructions() {
        final LiveVariablesProblem lvp = (LiveVariablesProblem)this._prob;
        if (this._out == null) {
            for (final Instr i : this._bb.getInstrs()) {
                i.markDead();
            }
            return;
        }
        this._tmp = (BitSet)this._out.clone();
        final List<Instr> instrs = this._bb.getInstrs();
        final ListIterator<Instr> it = instrs.listIterator(instrs.size());
        while (it.hasPrevious()) {
            final Instr j = it.previous();
            final Variable v = j.getResult();
            if (v != null) {
                final DataFlowVar dv = lvp.getDFVar(v);
                if (!this._tmp.get(dv._id) && !j.hasSideEffects()) {
                    j.markDead();
                    it.remove();
                }
                else if (this._tmp.get(dv._id)) {
                    this._tmp.clear(dv._id);
                }
            }
            if (j instanceof CallInstr) {
                final CallInstr c = (CallInstr)j;
                if (c.isLVADataflowBarrier()) {
                    for (int k = 0; k < this._setSize; ++k) {
                        this._tmp.set(k);
                    }
                }
                else {
                    final Operand o = c.getClosureArg();
                    if (o != null && o instanceof MetaObject) {
                        final IRClosure cl = (IRClosure)((MetaObject)o).scope;
                        final CFG x = cl.getCFG();
                        final LiveVariablesProblem xlvp = (LiveVariablesProblem)x.getDataFlowSolution(lvp.getName());
                        for (final Variable y : xlvp.getVarsLiveOnEntry()) {
                            final DataFlowVar dv2 = lvp.getDFVar(y);
                            if (dv2 != null) {
                                this._tmp.set(dv2._id);
                            }
                        }
                    }
                }
            }
            if (!j.isDead()) {
                for (final Variable x2 : j.getUsedVariables()) {
                    final DataFlowVar dv3 = lvp.getDFVar(x2);
                    if (dv3 != null) {
                        this._tmp.set(dv3._id);
                    }
                }
            }
        }
    }
    
    BitSet getLiveInBitSet() {
        return this._in;
    }
    
    BitSet getLiveOutBitSet() {
        return this._out;
    }
}
