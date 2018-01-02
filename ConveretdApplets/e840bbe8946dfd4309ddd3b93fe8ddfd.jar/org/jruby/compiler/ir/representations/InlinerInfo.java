// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.representations;

import org.jruby.compiler.ir.Tuple;
import org.jruby.compiler.ir.instructions.YieldInstr;
import org.jruby.compiler.ir.operands.Array;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.operands.LocalVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.operands.Label;
import java.util.Map;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.instructions.CallInstr;

public class InlinerInfo
{
    public final CFG callerCFG;
    public final CallInstr call;
    private Operand[] callArgs;
    private Map<Label, Label> lblRenameMap;
    private Map<Variable, Variable> varRenameMap;
    private Map<BasicBlock, BasicBlock> bbRenameMap;
    private List yieldSites;
    
    public InlinerInfo(final CallInstr call, final CFG c) {
        this.call = call;
        this.callArgs = call.getCallArgs();
        this.callerCFG = c;
        this.varRenameMap = new HashMap<Variable, Variable>();
        this.lblRenameMap = new HashMap<Label, Label>();
        this.bbRenameMap = new HashMap<BasicBlock, BasicBlock>();
        this.yieldSites = new ArrayList();
    }
    
    public Label getRenamedLabel(final Label l) {
        Label newLbl = this.lblRenameMap.get(l);
        if (newLbl == null) {
            newLbl = this.callerCFG.getScope().getNewLabel();
            this.lblRenameMap.put(l, newLbl);
        }
        return newLbl;
    }
    
    public Variable getRenamedVariable(final Variable v) {
        Variable newVar = this.varRenameMap.get(v);
        if (newVar == null) {
            final IRExecutionScope m = this.callerCFG.getScope();
            newVar = m.getNewInlineVariable();
            if (v instanceof LocalVariable) {
                newVar = m.getLocalVariable(newVar.getName());
            }
            this.varRenameMap.put(v, newVar);
        }
        return newVar;
    }
    
    public BasicBlock getRenamedBB(final BasicBlock bb) {
        return this.bbRenameMap.get(bb);
    }
    
    public BasicBlock getOrCreateRenamedBB(final BasicBlock bb) {
        BasicBlock renamedBB = this.getRenamedBB(bb);
        if (renamedBB == null) {
            renamedBB = new BasicBlock(this.callerCFG, this.getRenamedLabel(bb._label));
            this.bbRenameMap.put(bb, renamedBB);
        }
        return renamedBB;
    }
    
    public Operand getCallArg(final int index) {
        return (index < this.callArgs.length) ? this.callArgs[index] : null;
    }
    
    public Operand getCallArg(final int argIndex, final boolean restOfArgArray) {
        if (!restOfArgArray) {
            return this.getCallArg(argIndex);
        }
        if (argIndex >= this.callArgs.length) {
            return new Array();
        }
        final Operand[] tmp = new Operand[this.callArgs.length - argIndex];
        for (int j = argIndex; j < this.callArgs.length; ++j) {
            tmp[j - argIndex] = this.callArgs[j];
        }
        return new Array(tmp);
    }
    
    public Operand getCallReceiver() {
        return this.call.getReceiver();
    }
    
    public Operand getCallClosure() {
        return this.call.getClosureArg();
    }
    
    public Variable getCallResultVariable() {
        return this.call.result;
    }
    
    public void recordYieldSite(final BasicBlock bb, final YieldInstr i) {
        this.yieldSites.add(new Tuple<BasicBlock, YieldInstr>(bb, i));
    }
    
    public List getYieldSites() {
        return this.yieldSites;
    }
}
