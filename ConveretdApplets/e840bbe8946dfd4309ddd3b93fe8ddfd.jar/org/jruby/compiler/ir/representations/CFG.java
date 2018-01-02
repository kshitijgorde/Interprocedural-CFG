// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.representations;

import org.jruby.compiler.ir.operands.LocalVariable;
import org.jruby.compiler.ir.operands.Nil;
import java.util.ListIterator;
import java.util.BitSet;
import org.jruby.compiler.ir.Tuple;
import org.jruby.compiler.ir.IRMethod;
import java.util.Collection;
import org.jruby.compiler.ir.instructions.YieldInstr;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.IRClosure;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.instructions.CallInstr;
import java.util.HashSet;
import org.jruby.compiler.ir.instructions.SET_RETADDR_Instr;
import org.jruby.compiler.ir.instructions.JUMP_INDIRECT_Instr;
import org.jruby.compiler.ir.instructions.THROW_EXCEPTION_Instr;
import org.jruby.compiler.ir.instructions.ReturnInstr;
import org.jruby.compiler.ir.instructions.BREAK_Instr;
import org.jruby.compiler.ir.instructions.CaseInstr;
import org.jruby.compiler.ir.instructions.JumpInstr;
import org.jruby.compiler.ir.instructions.BranchInstr;
import org.jruby.compiler.ir.instructions.ExceptionRegionEndMarkerInstr;
import org.jruby.compiler.ir.instructions.ExceptionRegionStartMarkerInstr;
import org.jruby.compiler.ir.instructions.LABEL_Instr;
import org.jruby.compiler.ir.Operation;
import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.DefaultDirectedGraph;
import java.util.Stack;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import org.jruby.compiler.ir.operands.Variable;
import java.util.Set;
import org.jruby.compiler.ir.instructions.Instr;
import java.util.List;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.dataflow.DataFlowProblem;
import java.util.Map;
import java.util.LinkedList;
import org.jgrapht.DirectedGraph;
import org.jruby.compiler.ir.IRExecutionScope;

public class CFG
{
    IRExecutionScope _scope;
    BasicBlock _entryBB;
    BasicBlock _exitBB;
    int _nextBBId;
    DirectedGraph<BasicBlock, CFG_Edge> _cfg;
    LinkedList<BasicBlock> _postOrderList;
    Map<String, DataFlowProblem> _dfProbs;
    Map<Label, BasicBlock> _bbMap;
    BasicBlock[] _fallThruMap;
    List<BasicBlock> _linearizedBBList;
    Map<BasicBlock, BasicBlock> _bbRescuerMap;
    List<ExceptionRegion> _outermostERs;
    private Instr[] _instrs;
    private Set<Variable> _definedLocalVars;
    private Set<Variable> _usedLocalVars;
    
    public CFG(final IRExecutionScope s) {
        this._nextBBId = 0;
        this._scope = s;
        this._postOrderList = null;
        this._dfProbs = new HashMap<String, DataFlowProblem>();
        this._bbMap = new HashMap<Label, BasicBlock>();
        this._outermostERs = new ArrayList<ExceptionRegion>();
        this._bbRescuerMap = new HashMap<BasicBlock, BasicBlock>();
        this._instrs = null;
    }
    
    public DirectedGraph getGraph() {
        return this._cfg;
    }
    
    public IRExecutionScope getScope() {
        return this._scope;
    }
    
    public BasicBlock getEntryBB() {
        return this._entryBB;
    }
    
    public BasicBlock getExitBB() {
        return this._exitBB;
    }
    
    public int getNextBBID() {
        return ++this._nextBBId;
    }
    
    public int getMaxNodeID() {
        return this._nextBBId;
    }
    
    public int numNodes() {
        return this._cfg.vertexSet().size();
    }
    
    public Set<CFG_Edge> incomingEdgesOf(final BasicBlock bb) {
        return (Set<CFG_Edge>)this._cfg.incomingEdgesOf((Object)bb);
    }
    
    public Set<CFG_Edge> outgoingEdgesOf(final BasicBlock bb) {
        return (Set<CFG_Edge>)this._cfg.outgoingEdgesOf((Object)bb);
    }
    
    public Set<BasicBlock> getNodes() {
        return (Set<BasicBlock>)this._cfg.vertexSet();
    }
    
    public BasicBlock getTargetBB(final Label l) {
        return this._bbMap.get(l);
    }
    
    public int getRescuerPC(final Instr excInstr) {
        for (final BasicBlock b : this._linearizedBBList) {
            for (final Instr i : b.getInstrs()) {
                if (i == excInstr) {
                    final BasicBlock rescuerBB = this._bbRescuerMap.get(b);
                    if (rescuerBB == null) {
                        return -1;
                    }
                    return rescuerBB.getLabel().getTargetPC();
                }
            }
        }
        System.err.println("Fell through looking for rescuer ipc for " + excInstr);
        return -1;
    }
    
    private Label getNewLabel() {
        return this._scope.getNewLabel();
    }
    
    private BasicBlock createNewBB(final Label l, final DirectedGraph<BasicBlock, CFG_Edge> g, final Map<Label, BasicBlock> bbMap, final Stack<ExceptionRegion> nestedExceptionRegions) {
        final BasicBlock b = new BasicBlock(this, l);
        bbMap.put(b._label, b);
        g.addVertex((Object)b);
        if (!nestedExceptionRegions.empty()) {
            nestedExceptionRegions.peek().addBB(b);
        }
        return b;
    }
    
    private BasicBlock createNewBB(final DirectedGraph<BasicBlock, CFG_Edge> g, final Map<Label, BasicBlock> bbMap, final Stack<ExceptionRegion> nestedExceptionRegions) {
        return this.createNewBB(this.getNewLabel(), g, bbMap, nestedExceptionRegions);
    }
    
    private void removeBB(final BasicBlock b) {
        this._cfg.removeVertex((Object)b);
        this._bbMap.remove(b._label);
        this._bbRescuerMap.remove(b);
    }
    
    private void addEdge(final DirectedGraph<BasicBlock, CFG_Edge> g, final BasicBlock src, final Label tgt, final Map<Label, BasicBlock> bbMap, final Map<Label, List<BasicBlock>> forwardRefs) {
        final BasicBlock tgtBB = bbMap.get(tgt);
        if (tgtBB != null) {
            g.addEdge((Object)src, (Object)tgtBB);
        }
        else {
            List<BasicBlock> frefs = forwardRefs.get(tgt);
            if (frefs == null) {
                frefs = new ArrayList<BasicBlock>();
                forwardRefs.put(tgt, frefs);
            }
            frefs.add(src);
        }
    }
    
    private DefaultDirectedGraph<BasicBlock, CFG_Edge> getNewCFG() {
        return (DefaultDirectedGraph<BasicBlock, CFG_Edge>)new DefaultDirectedGraph((EdgeFactory)new EdgeFactory<BasicBlock, CFG_Edge>() {
            public CFG_Edge createEdge(final BasicBlock s, final BasicBlock d) {
                return new CFG_Edge(s, d);
            }
        });
    }
    
    public Instr[] prepareForInterpretation() {
        if (this._instrs != null) {
            return this._instrs;
        }
        final List<Instr> instrs = new ArrayList<Instr>();
        final List<BasicBlock> bbs = this.linearize();
        this.setupFallThruMap();
        final HashMap<Label, Integer> labelIPCMap = new HashMap<Label, Integer>();
        final List<Label> labelsToFixup = new ArrayList<Label>();
        int ipc = 0;
        for (final BasicBlock b : bbs) {
            labelIPCMap.put(b.getLabel(), ipc);
            labelsToFixup.add(b.getLabel());
            for (final Instr i : b.getInstrs()) {
                instrs.add(i);
                ++ipc;
            }
        }
        for (final Label l : labelsToFixup) {
            l.setTargetPC(labelIPCMap.get(l));
        }
        this.getExitBB().getLabel().setTargetPC(ipc + 1);
        return this._instrs = instrs.toArray(new Instr[instrs.size()]);
    }
    
    public Instr[] getInstrArray() {
        return this._instrs;
    }
    
    public void build(final List<Instr> instrs) {
        final Map<Label, List<BasicBlock>> forwardRefs = new HashMap<Label, List<BasicBlock>>();
        final Map<Variable, Set<Label>> retAddrMap = new HashMap<Variable, Set<Label>>();
        final Map<Variable, BasicBlock> retAddrTargetMap = new HashMap<Variable, BasicBlock>();
        final List<BasicBlock> retBBs = new ArrayList<BasicBlock>();
        final List<BasicBlock> excBBs = new ArrayList<BasicBlock>();
        final Stack<ExceptionRegion> nestedExceptionRegions = new Stack<ExceptionRegion>();
        final List<ExceptionRegion> allExceptionRegions = new ArrayList<ExceptionRegion>();
        final DirectedGraph<BasicBlock, CFG_Edge> g = (DirectedGraph<BasicBlock, CFG_Edge>)this.getNewCFG();
        this._entryBB = this.createNewBB(g, this._bbMap, nestedExceptionRegions);
        final BasicBlock firstBB = this.createNewBB(g, this._bbMap, nestedExceptionRegions);
        BasicBlock prevBB = null;
        BasicBlock currBB = firstBB;
        BasicBlock newBB = null;
        boolean bbEnded = false;
        boolean bbEndedWithControlXfer = false;
        for (final Instr i : instrs) {
            final Operation iop = i.operation;
            if (iop == Operation.LABEL) {
                final Label l = ((LABEL_Instr)i)._lbl;
                prevBB = currBB;
                newBB = this.createNewBB(l, g, this._bbMap, nestedExceptionRegions);
                if (!bbEndedWithControlXfer) {
                    g.addEdge((Object)currBB, (Object)newBB);
                }
                currBB = newBB;
                final List<BasicBlock> frefs = forwardRefs.get(l);
                if (frefs != null) {
                    for (final BasicBlock b : frefs) {
                        g.addEdge((Object)b, (Object)newBB);
                    }
                }
                bbEnded = false;
                bbEndedWithControlXfer = false;
            }
            else if (bbEnded && iop != Operation.EXC_REGION_END) {
                prevBB = currBB;
                newBB = this.createNewBB(g, this._bbMap, nestedExceptionRegions);
                if (!bbEndedWithControlXfer) {
                    g.addEdge((Object)currBB, (Object)newBB);
                }
                currBB = newBB;
                bbEnded = false;
                bbEndedWithControlXfer = false;
            }
            if (i instanceof ExceptionRegionStartMarkerInstr) {
                final ExceptionRegionStartMarkerInstr rbsmi = (ExceptionRegionStartMarkerInstr)i;
                final ExceptionRegion rr = new ExceptionRegion(rbsmi._rescueBlockLabels);
                rr.addBB(currBB);
                allExceptionRegions.add(rr);
                if (nestedExceptionRegions.empty()) {
                    this._outermostERs.add(rr);
                }
                else {
                    nestedExceptionRegions.peek().addNestedRegion(rr);
                }
                nestedExceptionRegions.push(rr);
            }
            else if (i instanceof ExceptionRegionEndMarkerInstr) {
                nestedExceptionRegions.pop().setEndBB(currBB);
            }
            else if (iop.endsBasicBlock()) {
                bbEnded = true;
                currBB.addInstr(i);
                Label tgt;
                if (i instanceof BranchInstr) {
                    tgt = ((BranchInstr)i).getJumpTarget();
                }
                else if (i instanceof JumpInstr) {
                    tgt = ((JumpInstr)i).getJumpTarget();
                    bbEndedWithControlXfer = true;
                }
                else if (i instanceof CaseInstr) {
                    tgt = null;
                }
                else if (i instanceof BREAK_Instr) {
                    tgt = null;
                    retBBs.add(currBB);
                    bbEndedWithControlXfer = true;
                }
                else if (i instanceof ReturnInstr) {
                    tgt = null;
                    retBBs.add(currBB);
                    bbEndedWithControlXfer = true;
                }
                else if (i instanceof THROW_EXCEPTION_Instr) {
                    tgt = null;
                    excBBs.add(currBB);
                    bbEndedWithControlXfer = true;
                }
                else if (i instanceof JUMP_INDIRECT_Instr) {
                    tgt = null;
                    bbEndedWithControlXfer = true;
                    final Set<Label> retAddrs = retAddrMap.get(((JUMP_INDIRECT_Instr)i).getJumpTarget());
                    for (final Label j : retAddrs) {
                        this.addEdge(g, currBB, j, this._bbMap, forwardRefs);
                    }
                    retAddrTargetMap.put(((JUMP_INDIRECT_Instr)i).getJumpTarget(), currBB);
                }
                else {
                    tgt = null;
                }
                if (tgt != null) {
                    this.addEdge(g, currBB, tgt, this._bbMap, forwardRefs);
                }
            }
            else if (iop != Operation.LABEL) {
                currBB.addInstr(i);
            }
            if (i instanceof SET_RETADDR_Instr) {
                final Variable v = i.getResult();
                final Label tgtLbl = ((SET_RETADDR_Instr)i).getReturnAddr();
                final BasicBlock tgtBB = retAddrTargetMap.get(v);
                if (tgtBB != null) {
                    this.addEdge(g, tgtBB, tgtLbl, this._bbMap, forwardRefs);
                }
                else {
                    Set<Label> addrs = retAddrMap.get(v);
                    if (addrs == null) {
                        addrs = new HashSet<Label>();
                        retAddrMap.put(v, addrs);
                    }
                    addrs.add(tgtLbl);
                }
            }
            else {
                if (!(i instanceof CallInstr)) {
                    continue;
                }
                final Operand closureArg = ((CallInstr)i).getClosureArg();
                if (!(closureArg instanceof MetaObject)) {
                    continue;
                }
                ((IRClosure)((MetaObject)closureArg).scope).buildCFG();
            }
        }
        for (final ExceptionRegion rr2 : allExceptionRegions) {
            final BasicBlock firstRescueBB = this.getTargetBB(rr2.getFirstRescueBlockLabel());
            rr2.setFirstRescueBB(firstRescueBB);
            for (final BasicBlock b2 : rr2._exclusiveBBs) {
                this._bbRescuerMap.put(b2, firstRescueBB);
                ((CFG_Edge)g.addEdge((Object)b2, (Object)firstRescueBB))._type = CFG_Edge_Type.EXCEPTION_EDGE;
            }
        }
        this._exitBB = this.createNewBB(g, this._bbMap, nestedExceptionRegions);
        ((CFG_Edge)g.addEdge((Object)this._entryBB, (Object)this._exitBB))._type = CFG_Edge_Type.DUMMY_EDGE;
        ((CFG_Edge)g.addEdge((Object)this._entryBB, (Object)firstBB))._type = CFG_Edge_Type.DUMMY_EDGE;
        for (final BasicBlock rb : retBBs) {
            ((CFG_Edge)g.addEdge((Object)rb, (Object)this._exitBB))._type = CFG_Edge_Type.DUMMY_EDGE;
        }
        for (final BasicBlock rb : excBBs) {
            ((CFG_Edge)g.addEdge((Object)rb, (Object)this._exitBB))._type = CFG_Edge_Type.EXCEPTION_EDGE;
        }
        if (!bbEndedWithControlXfer) {
            ((CFG_Edge)g.addEdge((Object)currBB, (Object)this._exitBB))._type = CFG_Edge_Type.DUMMY_EDGE;
        }
        this._cfg = g;
        this.optimizeCFG();
    }
    
    private void setupFallThruMap() {
        final List<BasicBlock> bbs = this.linearize();
        this._fallThruMap = new BasicBlock[1 + this.getMaxNodeID()];
        BasicBlock prev = null;
        for (final BasicBlock b : bbs) {
            if (prev != null) {
                this._fallThruMap[prev.getID()] = b;
            }
            prev = b;
        }
    }
    
    private void mergeBBs(final BasicBlock a, final BasicBlock b) {
        final BasicBlock aR = this._bbRescuerMap.get(a);
        final BasicBlock bR = this._bbRescuerMap.get(b);
        if (aR == bR || a.isEmpty() || b.isEmpty()) {
            a.swallowBB(b);
            this._cfg.removeEdge((Object)a, (Object)b);
            for (final CFG_Edge e : this._cfg.outgoingEdgesOf((Object)b)) {
                ((CFG_Edge)this._cfg.addEdge((Object)a, (Object)e._dst))._type = e._type;
            }
            this.removeBB(b);
            if (aR == null && bR != null) {
                this._bbRescuerMap.put(a, bR);
            }
        }
    }
    
    private void mergeStraightlineBBs(final BasicBlock callBB, final BasicBlock splitBB) {
        Set<CFG_Edge> edges = this.outgoingEdgesOf(callBB);
        assert edges.size() == 1;
        this.mergeBBs(callBB, edges.iterator().next()._dst);
        edges = this.incomingEdgesOf(splitBB);
        assert edges.size() == 1;
        this.mergeBBs(edges.iterator().next()._src, splitBB);
    }
    
    private void inlineClosureAtYieldSite(final InlinerInfo ii, final IRClosure cl, final BasicBlock yieldBB, final YieldInstr yield) {
        final BasicBlock splitBB = yieldBB.splitAtInstruction(yield, this.getNewLabel(), false);
        this._cfg.addVertex((Object)splitBB);
        this._bbMap.put(splitBB._label, splitBB);
        final List<CFG_Edge> edgesToRemove = new ArrayList<CFG_Edge>();
        for (final CFG_Edge e : this.outgoingEdgesOf(yieldBB)) {
            ((CFG_Edge)this._cfg.addEdge((Object)splitBB, (Object)e._dst))._type = e._type;
            edgesToRemove.add(e);
        }
        this._cfg.removeAllEdges((Collection)edgesToRemove);
        final CFG ccfg = cl.getCFG();
        final BasicBlock cEntry = ccfg.getEntryBB();
        final BasicBlock cExit = ccfg.getExitBB();
        for (final BasicBlock b : ccfg.getNodes()) {
            if (b != cEntry && b != cExit) {
                this._cfg.addVertex((Object)b);
                this._bbMap.put(b._label, b);
                b.updateCFG(this);
                b.processClosureArgAndReturnInstrs(ii, yield);
            }
        }
        for (final CFG_Edge e2 : ccfg.outgoingEdgesOf(cEntry)) {
            if (e2._dst != cExit) {
                ((CFG_Edge)this._cfg.addEdge((Object)yieldBB, (Object)e2._dst))._type = e2._type;
            }
        }
        for (final CFG_Edge e2 : ccfg.incomingEdgesOf(cExit)) {
            if (e2._src != cEntry) {
                if (e2._type == CFG_Edge_Type.EXCEPTION_EDGE) {
                    final BasicBlock rescuerOfSplitBB = this._bbRescuerMap.get(splitBB);
                    ((CFG_Edge)this._cfg.addEdge((Object)e2._src, (Object)((rescuerOfSplitBB != null) ? rescuerOfSplitBB : this._exitBB)))._type = CFG_Edge_Type.EXCEPTION_EDGE;
                }
                else {
                    ((CFG_Edge)this._cfg.addEdge((Object)e2._src, (Object)splitBB))._type = e2._type;
                }
            }
        }
        for (final ExceptionRegion r : ccfg._outermostERs) {
            this._outermostERs.add(r);
        }
        final BasicBlock yieldBBrescuer = this._bbRescuerMap.get(yieldBB);
        if (yieldBBrescuer != null) {
            this._bbRescuerMap.put(splitBB, yieldBBrescuer);
        }
        final Map<BasicBlock, BasicBlock> cRescuerMap = ccfg._bbRescuerMap;
        for (final BasicBlock cb : ccfg.getNodes()) {
            if (cb != cEntry && cb != cExit) {
                final BasicBlock cbProtector = cRescuerMap.get(cb);
                if (cbProtector != null) {
                    this._bbRescuerMap.put(cb, cbProtector);
                }
                else {
                    if (yieldBBrescuer == null) {
                        continue;
                    }
                    this._bbRescuerMap.put(cb, yieldBBrescuer);
                }
            }
        }
        this.mergeStraightlineBBs(yieldBB, splitBB);
    }
    
    public void inlineMethod(final IRMethod m, final BasicBlock callBB, final CallInstr call) {
        final InlinerInfo ii = new InlinerInfo(call, this);
        final BasicBlock splitBB = callBB.splitAtInstruction(call, this.getNewLabel(), false);
        this._bbMap.put(splitBB._label, splitBB);
        this._cfg.addVertex((Object)splitBB);
        final List<CFG_Edge> edgesToRemove = new ArrayList<CFG_Edge>();
        for (final CFG_Edge e : this.outgoingEdgesOf(callBB)) {
            ((CFG_Edge)this._cfg.addEdge((Object)splitBB, (Object)e._dst))._type = e._type;
            edgesToRemove.add(e);
        }
        this._cfg.removeAllEdges((Collection)edgesToRemove);
        final CFG mcfg = m.getCFG();
        final BasicBlock mEntry = mcfg.getEntryBB();
        final BasicBlock mExit = mcfg.getExitBB();
        final DirectedGraph<BasicBlock, CFG_Edge> g = (DirectedGraph<BasicBlock, CFG_Edge>)this.getNewCFG();
        for (final BasicBlock b : mcfg.getNodes()) {
            if (b != mEntry && b != mExit) {
                final BasicBlock bCloned = b.cloneForInlining(ii);
                this._cfg.addVertex((Object)bCloned);
                this._bbMap.put(bCloned._label, bCloned);
            }
        }
        for (final BasicBlock x : mcfg.getNodes()) {
            if (x != mEntry && x != mExit) {
                final BasicBlock rx = ii.getRenamedBB(x);
                for (final CFG_Edge e2 : mcfg.outgoingEdgesOf(x)) {
                    final BasicBlock b2 = e2._dst;
                    if (b2 != mExit) {
                        ((CFG_Edge)this._cfg.addEdge((Object)rx, (Object)ii.getRenamedBB(b2)))._type = e2._type;
                    }
                }
            }
        }
        for (final CFG_Edge e3 : mcfg.outgoingEdgesOf(mEntry)) {
            if (e3._dst != mExit) {
                ((CFG_Edge)this._cfg.addEdge((Object)callBB, (Object)ii.getRenamedBB(e3._dst)))._type = e3._type;
            }
        }
        for (final CFG_Edge e3 : mcfg.incomingEdgesOf(mExit)) {
            if (e3._src != mEntry) {
                if (e3._type == CFG_Edge_Type.EXCEPTION_EDGE) {
                    final BasicBlock rescuerOfSplitBB = this._bbRescuerMap.get(splitBB);
                    ((CFG_Edge)this._cfg.addEdge((Object)ii.getRenamedBB(e3._src), (Object)((rescuerOfSplitBB != null) ? rescuerOfSplitBB : this._exitBB)))._type = CFG_Edge_Type.EXCEPTION_EDGE;
                }
                else {
                    ((CFG_Edge)this._cfg.addEdge((Object)ii.getRenamedBB(e3._src), (Object)splitBB))._type = e3._type;
                }
            }
        }
        for (final ExceptionRegion r : mcfg._outermostERs) {
            this._outermostERs.add(r.cloneForInlining(ii));
        }
        final BasicBlock callBBrescuer = this._bbRescuerMap.get(callBB);
        if (callBBrescuer != null) {
            this._bbRescuerMap.put(splitBB, callBBrescuer);
        }
        final Map<BasicBlock, BasicBlock> mRescuerMap = mcfg._bbRescuerMap;
        for (final BasicBlock x2 : mcfg.getNodes()) {
            if (x2 != mEntry && x2 != mExit) {
                final BasicBlock xRenamed = ii.getRenamedBB(x2);
                final BasicBlock xProtector = mRescuerMap.get(x2);
                if (xProtector != null) {
                    this._bbRescuerMap.put(xRenamed, ii.getRenamedBB(xProtector));
                }
                else {
                    if (callBBrescuer == null) {
                        continue;
                    }
                    this._bbRescuerMap.put(xRenamed, callBBrescuer);
                }
            }
        }
        this.mergeStraightlineBBs(callBB, splitBB);
        final Operand closureArg = call.getClosureArg();
        final List yieldSites = ii.getYieldSites();
        if (closureArg != null && !yieldSites.isEmpty()) {
            if (yieldSites.size() > 1) {
                throw new RuntimeException("Encountered " + yieldSites.size() + " yield sites.  Convert the yield to a call by converting the closure into a dummy method (have to convert all frame vars to call arguments, or at least convert the frame into a call arg");
            }
            if (!(closureArg instanceof MetaObject)) {
                throw new RuntimeException("Encountered a dynamic closure arg.  Cannot inline it here!  Convert the yield to a call by converting the closure into a dummy method (have to convert all frame vars to call arguments, or at least convert the frame into a call arg");
            }
            final Tuple t = yieldSites.get(0);
            this.inlineClosureAtYieldSite(ii, (IRClosure)((MetaObject)closureArg).scope, (BasicBlock)t.a, (YieldInstr)t.b);
        }
        this.setupFallThruMap();
    }
    
    private void buildPostOrderTraversal() {
        this._postOrderList = new LinkedList<BasicBlock>();
        final BasicBlock root = this.getEntryBB();
        final Stack<BasicBlock> stack = new Stack<BasicBlock>();
        stack.push(root);
        final BitSet bbSet = new BitSet(1 + this.getMaxNodeID());
        bbSet.set(root.getID());
        while (!stack.empty()) {
            final BasicBlock b = stack.peek();
            boolean allChildrenDone = true;
            for (final CFG_Edge e : this._cfg.outgoingEdgesOf((Object)b)) {
                final BasicBlock dst = e._dst;
                final int dstID = dst.getID();
                if (!bbSet.get(dstID)) {
                    allChildrenDone = false;
                    stack.push(dst);
                    bbSet.set(dstID);
                }
            }
            if (allChildrenDone) {
                stack.pop();
                this._postOrderList.add(b);
            }
        }
        for (final BasicBlock b2 : this.getNodes()) {
            if (!bbSet.get(b2.getID())) {
                System.out.println("BB " + b2.getID() + " missing from po list!");
                System.out.println("CFG: " + this._cfg);
                System.out.println("Instrs: " + this.toStringInstrs());
                break;
            }
        }
    }
    
    public ListIterator<BasicBlock> getPostOrderTraverser() {
        if (this._postOrderList == null) {
            this.buildPostOrderTraversal();
        }
        return this._postOrderList.listIterator();
    }
    
    public ListIterator<BasicBlock> getReversePostOrderTraverser() {
        if (this._postOrderList == null) {
            this.buildPostOrderTraversal();
        }
        return this._postOrderList.listIterator(this.numNodes());
    }
    
    private Integer intersectDomSets(final Integer[] idomMap, Integer nb1, Integer nb2) {
        while (nb1 != nb2) {
            while (nb1 < nb2) {
                nb1 = idomMap[nb1];
            }
            while (nb2 < nb1) {
                nb2 = idomMap[nb2];
            }
        }
        return nb1;
    }
    
    public void buildDominatorTree() {
        final int maxNodeId = this.getMaxNodeID();
        final Integer[] bbToPoNumbers = new Integer[maxNodeId + 1];
        final BasicBlock[] poNumbersToBB = new BasicBlock[maxNodeId + 1];
        ListIterator<BasicBlock> it = this.getPostOrderTraverser();
        int n = 0;
        while (it.hasNext()) {
            final BasicBlock b = it.next();
            bbToPoNumbers[b.getID()] = n;
            poNumbersToBB[n] = b;
            ++n;
        }
        final Integer[] idoms = new Integer[maxNodeId + 1];
        final BasicBlock root = this.getEntryBB();
        final Integer rootPoNumber = bbToPoNumbers[root.getID()];
        idoms[rootPoNumber] = rootPoNumber;
        boolean changed = true;
        while (changed) {
            changed = false;
            it = this.getReversePostOrderTraverser();
            while (it.hasPrevious()) {
                final BasicBlock b2 = it.previous();
                if (b2 == root) {
                    continue;
                }
                final Integer bPoNumber = bbToPoNumbers[b2.getID()];
                final Integer oldBIdom = idoms[bPoNumber];
                Integer newBIdom = null;
                for (final CFG_Edge e : this._cfg.incomingEdgesOf((Object)b2)) {
                    final BasicBlock src = e._src;
                    final Integer srcPoNumber = bbToPoNumbers[src.getID()];
                    if (idoms[srcPoNumber] != null) {
                        newBIdom = srcPoNumber;
                        break;
                    }
                }
                assert newBIdom != null;
                final Integer processedPred = newBIdom;
                for (final CFG_Edge e2 : this._cfg.incomingEdgesOf((Object)b2)) {
                    final BasicBlock src2 = e2._src;
                    final Integer srcPoNumber2 = bbToPoNumbers[src2.getID()];
                    final Integer srcIdom = idoms[srcPoNumber2];
                    if (srcIdom != null && srcPoNumber2 != processedPred) {
                        newBIdom = this.intersectDomSets(idoms, srcPoNumber2, newBIdom);
                    }
                }
                if (oldBIdom == newBIdom) {
                    continue;
                }
                changed = true;
                idoms[bPoNumber] = newBIdom;
            }
        }
        final Map<BasicBlock, BasicBlock> idomMap = new HashMap<BasicBlock, BasicBlock>();
        for (Integer i = 0; i < maxNodeId; ++i) {
            idomMap.put(poNumbersToBB[i], poNumbersToBB[idoms[i]]);
        }
    }
    
    public String toStringInstrs() {
        final StringBuffer buf = new StringBuffer();
        for (final BasicBlock b : this.getNodes()) {
            buf.append(b.toStringInstrs());
        }
        buf.append("\n\n------ Exception handling map ------\n");
        for (final BasicBlock bb : this._bbRescuerMap.keySet()) {
            buf.append("BB " + bb.getID() + " --> BB " + this._bbRescuerMap.get(bb).getID() + "\n");
        }
        final List<IRClosure> closures = this._scope.getClosures();
        if (!closures.isEmpty()) {
            buf.append("\n\n------ Closures encountered in this scope ------\n");
            for (final IRClosure c : closures) {
                buf.append(c.toStringBody());
            }
            buf.append("------------------------------------------------\n");
        }
        return buf.toString();
    }
    
    public void setDataFlowSolution(final String name, final DataFlowProblem p) {
        this._dfProbs.put(name, p);
    }
    
    public DataFlowProblem getDataFlowSolution(final String name) {
        return this._dfProbs.get(name);
    }
    
    private void pushBBOnStack(final Stack<BasicBlock> stack, final BitSet bbSet, final BasicBlock bb) {
        if (!bbSet.get(bb.getID())) {
            stack.push(bb);
            bbSet.set(bb.getID());
        }
    }
    
    public void deleteOrphanedBlocks() {
        while (true) {
            BasicBlock bbToRemove = null;
            for (final BasicBlock b : this.getNodes()) {
                if (b == this._entryBB) {
                    continue;
                }
                if (this.incomingEdgesOf(b).size() == 0) {
                    bbToRemove = b;
                    break;
                }
            }
            if (bbToRemove == null) {
                break;
            }
            this.removeBB(bbToRemove);
        }
    }
    
    public void splitCalls() {
    }
    
    public void optimizeCFG() {
        final List<CFG_Edge> toRemove = new ArrayList<CFG_Edge>();
        for (final BasicBlock b : this.getNodes()) {
            boolean noExceptions = true;
            for (final Instr i : b.getInstrs()) {
                if (i.canRaiseException()) {
                    noExceptions = false;
                    break;
                }
            }
            if (noExceptions) {
                for (final CFG_Edge e : this._cfg.outgoingEdgesOf((Object)b)) {
                    if (e._type == CFG_Edge_Type.EXCEPTION_EDGE) {
                        toRemove.add(e);
                        if (this._bbRescuerMap.get(e._src) != e._dst) {
                            continue;
                        }
                        this._bbRescuerMap.remove(e._src);
                    }
                }
            }
        }
        if (!toRemove.isEmpty()) {
            this._cfg.removeAllEdges((Collection)toRemove);
        }
        this.deleteOrphanedBlocks();
    }
    
    public List<BasicBlock> linearize() {
        if (this._linearizedBBList != null) {
            return this._linearizedBBList;
        }
        this._linearizedBBList = new ArrayList<BasicBlock>();
        final BasicBlock root = this.getEntryBB();
        final BitSet bbSet = new BitSet(1 + this.getMaxNodeID());
        bbSet.set(root.getID());
        final Stack<BasicBlock> stack = new Stack<BasicBlock>();
        for (final CFG_Edge e : this._cfg.edgeSet()) {
            if (e._type == CFG_Edge_Type.EXCEPTION_EDGE) {
                this.pushBBOnStack(stack, bbSet, e._dst);
            }
        }
        stack.push(root);
        while (!stack.empty()) {
            final BasicBlock b = stack.pop();
            this._linearizedBBList.add(b);
            if (b == this._exitBB) {
                assert stack.empty();
                continue;
            }
            else {
                final Instr lastInstr = b.getLastInstr();
                if (lastInstr == null) {
                    BasicBlock b2 = null;
                    BasicBlock b3 = null;
                    for (final CFG_Edge e2 : this._cfg.outgoingEdgesOf((Object)b)) {
                        if (b2 == null) {
                            b2 = e2._dst;
                        }
                        else {
                            if (b3 != null) {
                                throw new RuntimeException("Encountered bb: " + b.getID() + " with no instrs. and more than 2 targets!!");
                            }
                            b3 = e2._dst;
                        }
                    }
                    assert b2 != null;
                    if (b3 == null) {
                        this.pushBBOnStack(stack, bbSet, b2);
                    }
                    else if (b2.getID() < b3.getID()) {
                        this.pushBBOnStack(stack, bbSet, b3);
                        this.pushBBOnStack(stack, bbSet, b2);
                    }
                    else {
                        this.pushBBOnStack(stack, bbSet, b2);
                        this.pushBBOnStack(stack, bbSet, b3);
                    }
                }
                else {
                    BasicBlock blockToIgnore = null;
                    if (lastInstr instanceof JumpInstr) {
                        blockToIgnore = this._bbMap.get(((JumpInstr)lastInstr).target);
                        boolean allJumps = true;
                        for (final CFG_Edge e2 : this._cfg.incomingEdgesOf((Object)blockToIgnore)) {
                            if (!(e2._src.getLastInstr() instanceof JumpInstr)) {
                                allJumps = false;
                            }
                        }
                        if (allJumps) {
                            blockToIgnore = null;
                        }
                    }
                    else if (lastInstr instanceof BranchInstr) {
                        final BasicBlock takenBlock = this._bbMap.get(((BranchInstr)lastInstr).getJumpTarget());
                        this.pushBBOnStack(stack, bbSet, takenBlock);
                        blockToIgnore = takenBlock;
                    }
                    for (final CFG_Edge e3 : this._cfg.outgoingEdgesOf((Object)b)) {
                        final BasicBlock x = e3._dst;
                        if (x != blockToIgnore) {
                            this.pushBBOnStack(stack, bbSet, x);
                        }
                    }
                }
                assert !stack.empty();
                continue;
            }
        }
        for (final BasicBlock b4 : this.getNodes()) {
            if (!bbSet.get(b4.getID())) {
                throw new RuntimeException("Bad CFG linearization: BB " + b4.getID() + " has been missed!");
            }
        }
        for (int n = this._linearizedBBList.size(), i = 0; i < n; ++i) {
            final BasicBlock curr = this._linearizedBBList.get(i);
            final Instr li = curr.getLastInstr();
            if (i + 1 < n) {
                final BasicBlock next = this._linearizedBBList.get(i + 1);
                if (li instanceof JumpInstr) {
                    if (next == this._bbMap.get(((JumpInstr)li).target)) {
                        curr.removeInstr(li);
                    }
                }
                else {
                    final Set<CFG_Edge> succs = (Set<CFG_Edge>)this._cfg.outgoingEdgesOf((Object)curr);
                    if (succs.size() == 1) {
                        final BasicBlock tgt = succs.iterator().next()._dst;
                        if (tgt != next && (li == null || !li.operation.xfersControl())) {
                            curr.addInstr(new JumpInstr(tgt._label));
                        }
                    }
                }
                if (curr == this._exitBB) {
                    curr.addInstr(new ReturnInstr(Nil.NIL));
                }
            }
            else if (curr != this._exitBB) {
                final Set<CFG_Edge> succs2 = (Set<CFG_Edge>)this._cfg.outgoingEdgesOf((Object)curr);
                assert succs2.size() == 1;
                final BasicBlock tgt2 = succs2.iterator().next()._dst;
                if (li == null || !li.operation.xfersControl()) {
                    curr.addInstr(new JumpInstr(tgt2._label));
                }
            }
        }
        return this._linearizedBBList;
    }
    
    public String toString() {
        return "CFG[" + this._scope.getScopeName() + ":" + this._scope.getName() + "]";
    }
    
    public void setUpUseDefLocalVarMaps() {
        this._definedLocalVars = new HashSet<Variable>();
        this._usedLocalVars = new HashSet<Variable>();
        for (final BasicBlock bb : this.getNodes()) {
            for (final Instr i : bb.getInstrs()) {
                for (final Variable v : i.getUsedVariables()) {
                    if (v instanceof LocalVariable) {
                        this._usedLocalVars.add(v);
                    }
                }
                final Variable v2 = i.getResult();
                if (v2 != null && v2 instanceof LocalVariable) {
                    this._definedLocalVars.add(v2);
                }
            }
        }
        for (final IRClosure cl : this.getScope().getClosures()) {
            cl.getCFG().setUpUseDefLocalVarMaps();
        }
    }
    
    public Set<Variable> usedLocalVarsFromClosures() {
        final HashSet vs = new HashSet();
        for (final IRClosure cl : this.getScope().getClosures()) {
            final CFG c = cl.getCFG();
            vs.addAll(c._usedLocalVars);
            vs.addAll(c.usedLocalVarsFromClosures());
        }
        return (Set<Variable>)vs;
    }
    
    public Set<Variable> definedLocalVarsFromClosures() {
        final HashSet vs = new HashSet();
        for (final IRClosure cl : this.getScope().getClosures()) {
            final CFG c = cl.getCFG();
            vs.addAll(c._definedLocalVars);
            vs.addAll(c.definedLocalVarsFromClosures());
        }
        return (Set<Variable>)vs;
    }
    
    public boolean usesLocalVariable(final Variable v) {
        if (this._usedLocalVars.contains(v)) {
            return true;
        }
        for (final IRClosure cl : this.getScope().getClosures()) {
            if (cl.getCFG().usesLocalVariable(v)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean definesLocalVariable(final Variable v) {
        if (this._definedLocalVars.contains(v)) {
            return true;
        }
        for (final IRClosure cl : this.getScope().getClosures()) {
            if (cl.getCFG().definesLocalVariable(v)) {
                return true;
            }
        }
        return false;
    }
    
    public enum CFG_Edge_Type
    {
        REGULAR, 
        DUMMY_EDGE, 
        JUMP_EDGE, 
        FALLTHRU_EDGE, 
        FORWARD_EDGE, 
        BACK_EDGE, 
        EXIT_EDGE, 
        EXCEPTION_EDGE;
    }
    
    public static class CFG_Edge
    {
        public final BasicBlock _src;
        public final BasicBlock _dst;
        public CFG_Edge_Type _type;
        
        public CFG_Edge(final BasicBlock s, final BasicBlock d) {
            this._src = s;
            this._dst = d;
            this._type = CFG_Edge_Type.REGULAR;
        }
        
        public String toString() {
            return "<" + this._src.getID() + " --> " + this._dst.getID() + "> (" + this._type + ")";
        }
    }
}
