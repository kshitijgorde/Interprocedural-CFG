// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow;

import java.util.BitSet;
import java.util.List;
import java.util.Iterator;
import org.jruby.compiler.ir.instructions.Instr;
import org.jruby.compiler.ir.representations.CFG;
import org.jruby.compiler.ir.representations.BasicBlock;

public abstract class FlowGraphNode
{
    protected DataFlowProblem _prob;
    protected BasicBlock _bb;
    
    public FlowGraphNode(final DataFlowProblem p, final BasicBlock n) {
        this._prob = p;
        this._bb = n;
    }
    
    public abstract void initSolnForNode();
    
    public abstract void compute_MEET(final CFG.CFG_Edge p0, final FlowGraphNode p1);
    
    public abstract boolean applyTransferFunction();
    
    public abstract void buildDataFlowVars(final Instr p0);
    
    public void init() {
    }
    
    public void finalizeSolnForNode() {
    }
    
    public BasicBlock getBB() {
        return this._bb;
    }
    
    public void buildDataFlowVars() {
        for (final Instr i : this._bb.getInstrs()) {
            this.buildDataFlowVars(i);
        }
    }
    
    private void processDestBB(final List<FlowGraphNode> workList, final BitSet bbSet, final BasicBlock d) {
        final int id = d.getID();
        if (!bbSet.get(id)) {
            bbSet.set(id);
            workList.add(this._prob.getFlowGraphNode(d));
        }
    }
    
    public void computeDataFlowInfo(final List<FlowGraphNode> workList, final BitSet bbSet) {
        bbSet.clear(this._bb.getID());
        this.initSolnForNode();
        if (this._prob.getFlowDirection() == DataFlowProblem.DF_Direction.FORWARD) {
            for (final CFG.CFG_Edge e : this._prob.incomingEdgesOf(this._bb)) {
                this.compute_MEET(e, this._prob.getFlowGraphNode(e._src));
            }
        }
        else {
            if (this._prob.getFlowDirection() != DataFlowProblem.DF_Direction.BACKWARD) {
                throw new RuntimeException("Bidirectional data flow computation not implemented yet!");
            }
            for (final CFG.CFG_Edge e : this._prob.outgoingEdgesOf(this._bb)) {
                this.compute_MEET(e, this._prob.getFlowGraphNode(e._dst));
            }
        }
        this.finalizeSolnForNode();
        final boolean changed = this.applyTransferFunction();
        if (changed) {
            if (this._prob.getFlowDirection() == DataFlowProblem.DF_Direction.FORWARD) {
                for (final CFG.CFG_Edge e2 : this._prob.outgoingEdgesOf(this._bb)) {
                    this.processDestBB(workList, bbSet, e2._dst);
                }
            }
            else if (this._prob.getFlowDirection() == DataFlowProblem.DF_Direction.BACKWARD) {
                for (final CFG.CFG_Edge e2 : this._prob.incomingEdgesOf(this._bb)) {
                    this.processDestBB(workList, bbSet, e2._src);
                }
            }
        }
    }
}
