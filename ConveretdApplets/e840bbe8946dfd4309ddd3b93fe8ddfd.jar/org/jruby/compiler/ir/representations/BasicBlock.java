// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.representations;

import java.util.ListIterator;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Array;
import org.jruby.compiler.ir.instructions.ReceiveClosureArgInstr;
import org.jruby.compiler.ir.instructions.CopyInstr;
import org.jruby.compiler.ir.instructions.ClosureReturnInstr;
import org.jruby.compiler.ir.instructions.YieldInstr;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import org.jruby.compiler.ir.instructions.Instr;
import java.util.List;
import org.jruby.compiler.ir.operands.Label;

public class BasicBlock
{
    int _id;
    CFG _cfg;
    Label _label;
    List<Instr> _instrs;
    boolean _isLive;
    private Instr[] _instrsArray;
    
    public BasicBlock(final CFG c, final Label l) {
        this._instrsArray = null;
        this._instrs = new ArrayList<Instr>();
        this._label = l;
        this._isLive = true;
        this._cfg = c;
        this._id = c.getNextBBID();
    }
    
    public void updateCFG(final CFG c) {
        this._cfg = c;
        this._id = c.getNextBBID();
    }
    
    public int getID() {
        return this._id;
    }
    
    public Label getLabel() {
        return this._label;
    }
    
    public void addInstr(final Instr i) {
        this._instrs.add(i);
    }
    
    public void insertInstr(final Instr i) {
        this._instrs.add(0, i);
    }
    
    public List<Instr> getInstrs() {
        return this._instrs;
    }
    
    public Instr[] getInstrsArray() {
        if (this._instrsArray == null) {
            this._instrsArray = this._instrs.toArray(new Instr[this._instrs.size()]);
        }
        return this._instrsArray;
    }
    
    public Instr getLastInstr() {
        final int n = this._instrs.size();
        return (n == 0) ? null : this._instrs.get(n - 1);
    }
    
    public boolean removeInstr(final Instr i) {
        return i != null && this._instrs.remove(i);
    }
    
    public boolean isEmpty() {
        return this._instrs.isEmpty();
    }
    
    public BasicBlock splitAtInstruction(final Instr splitPoint, final Label newLabel, final boolean includeSplitPointInstr) {
        final BasicBlock newBB = new BasicBlock(this._cfg, newLabel);
        int idx = 0;
        final int numInstrs = this._instrs.size();
        boolean found = false;
        for (final Instr i : this._instrs) {
            if (i == splitPoint) {
                found = true;
            }
            if (found) {
                if (!includeSplitPointInstr && i == splitPoint) {
                    continue;
                }
                newBB.addInstr(i);
            }
            else {
                ++idx;
            }
        }
        for (int j = 0; j < numInstrs - idx; ++j) {
            this._instrs.remove(idx);
        }
        return newBB;
    }
    
    public void swallowBB(final BasicBlock foodBB) {
        this._instrs.addAll(foodBB._instrs);
    }
    
    public BasicBlock cloneForInlining(final InlinerInfo ii) {
        final BasicBlock clonedBB = ii.getOrCreateRenamedBB(this);
        for (final Instr i : this.getInstrs()) {
            final Instr clonedInstr = i.cloneForInlining(ii);
            clonedBB.addInstr(clonedInstr);
            if (clonedInstr instanceof YieldInstr) {
                ii.recordYieldSite(clonedBB, (YieldInstr)clonedInstr);
            }
        }
        return clonedBB;
    }
    
    public void processClosureArgAndReturnInstrs(final InlinerInfo ii, final YieldInstr yi) {
        final Variable yieldResult = ii.getRenamedVariable(yi.result);
        final Operand[] yieldArgs = yi.getOperands();
        final ListIterator<Instr> it = ((ArrayList)this._instrs).listIterator();
        while (it.hasNext()) {
            final Instr i = it.next();
            if (i instanceof ClosureReturnInstr) {
                it.set(new CopyInstr(yieldResult, ((ClosureReturnInstr)i).getArg()));
            }
            else {
                if (!(i instanceof ReceiveClosureArgInstr)) {
                    continue;
                }
                final ReceiveClosureArgInstr rcai = (ReceiveClosureArgInstr)i;
                final int argIndex = rcai.argIndex;
                final boolean restOfArgs = rcai.restOfArgArray;
                Operand closureArg;
                if (argIndex < yieldArgs.length) {
                    closureArg = yieldArgs[argIndex].cloneForInlining(ii);
                }
                else if (argIndex >= yieldArgs.length) {
                    closureArg = new Array();
                }
                else {
                    final Operand[] tmp = new Operand[yieldArgs.length - argIndex];
                    for (int j = argIndex; j < yieldArgs.length; ++j) {
                        tmp[j - argIndex] = yieldArgs[j].cloneForInlining(ii);
                    }
                    closureArg = new Array(tmp);
                }
                it.set(new CopyInstr(rcai.result, closureArg));
            }
        }
    }
    
    public String toString() {
        return "BB [" + this._id + ":" + this._label + "]";
    }
    
    public String toStringInstrs() {
        final StringBuilder buf = new StringBuilder(this.toString() + "\n");
        for (final Instr instr : this.getInstrs()) {
            if (!instr.isDead()) {
                buf.append('\t').append(instr).append('\n');
            }
        }
        return buf.toString();
    }
}
