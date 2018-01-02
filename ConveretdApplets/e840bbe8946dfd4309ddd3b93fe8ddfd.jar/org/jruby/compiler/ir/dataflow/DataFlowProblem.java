// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.dataflow;

import java.util.HashMap;
import java.util.Set;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.BitSet;
import org.jruby.compiler.ir.representations.BasicBlock;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.jruby.compiler.ir.representations.CFG;

public abstract class DataFlowProblem
{
    public final DF_Direction _direction;
    protected CFG _cfg;
    protected List<FlowGraphNode> _fgNodes;
    private int _nextDFVarId;
    private ArrayList<DataFlowVar> _dfVars;
    private Map<Integer, FlowGraphNode> _bbTofgMap;
    
    public DataFlowProblem(final DF_Direction d) {
        this._direction = d;
        this._dfVars = new ArrayList<DataFlowVar>();
        this._nextDFVarId = -1;
    }
    
    public abstract FlowGraphNode buildFlowGraphNode(final BasicBlock p0);
    
    public abstract String getName();
    
    public boolean isEmpty() {
        return false;
    }
    
    public DF_Direction getFlowDirection() {
        return this._direction;
    }
    
    public void setup(final CFG c) {
        this._cfg = c;
        this.buildFlowGraph();
    }
    
    public CFG getCFG() {
        return this._cfg;
    }
    
    public void compute_MOP_Solution() {
        if (!this.isEmpty()) {
            for (final FlowGraphNode fg : this._fgNodes) {
                fg.init();
            }
            final LinkedList<FlowGraphNode> workList = this.getInitialWorkList();
            final int numNodes = this._cfg.getMaxNodeID();
            final BitSet bbSet = new BitSet(1 + numNodes);
            bbSet.flip(0, numNodes);
            while (!workList.isEmpty()) {
                workList.removeFirst().computeDataFlowInfo(workList, bbSet);
            }
        }
    }
    
    private LinkedList<FlowGraphNode> getInitialWorkList() {
        final LinkedList<FlowGraphNode> wl = new LinkedList<FlowGraphNode>();
        if (this._direction == DF_Direction.FORWARD) {
            final ListIterator<BasicBlock> it = this._cfg.getReversePostOrderTraverser();
            while (it.hasPrevious()) {
                wl.add(this.getFlowGraphNode(it.previous()));
            }
        }
        else {
            final ListIterator<BasicBlock> it = this._cfg.getPostOrderTraverser();
            while (it.hasNext()) {
                wl.add(this.getFlowGraphNode(it.next()));
            }
        }
        return wl;
    }
    
    public int getDFVarsCount() {
        return this._dfVars.size();
    }
    
    public Set<CFG.CFG_Edge> incomingEdgesOf(final BasicBlock bb) {
        return this._cfg.incomingEdgesOf(bb);
    }
    
    public Set<CFG.CFG_Edge> outgoingEdgesOf(final BasicBlock bb) {
        return this._cfg.outgoingEdgesOf(bb);
    }
    
    public String getDataFlowVarsForOutput() {
        return "";
    }
    
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("----").append(this.getName()).append("----\n");
        buf.append("---- Data Flow Vars: ----\n");
        buf.append(this.getDataFlowVarsForOutput());
        buf.append("-------------------------\n");
        for (final FlowGraphNode n : this._fgNodes) {
            buf.append("DF State for BB ").append(n._bb.getID()).append(":\n").append(n.toString());
        }
        return buf.toString();
    }
    
    int addDataFlowVar(final DataFlowVar v) {
        ++this._nextDFVarId;
        this._dfVars.add(this._nextDFVarId, v);
        return this._nextDFVarId;
    }
    
    protected FlowGraphNode getFlowGraphNode(final BasicBlock b) {
        return this._bbTofgMap.get(b.getID());
    }
    
    private void buildFlowGraph() {
        this._fgNodes = new LinkedList<FlowGraphNode>();
        this._bbTofgMap = new HashMap<Integer, FlowGraphNode>();
        for (final BasicBlock bb : this._cfg.getNodes()) {
            final FlowGraphNode fgNode = this.buildFlowGraphNode(bb);
            fgNode.buildDataFlowVars();
            this._fgNodes.add(fgNode);
            this._bbTofgMap.put(bb.getID(), fgNode);
        }
    }
    
    public enum DF_Direction
    {
        FORWARD, 
        BACKWARD, 
        BIDIRECTIONAL;
    }
}
