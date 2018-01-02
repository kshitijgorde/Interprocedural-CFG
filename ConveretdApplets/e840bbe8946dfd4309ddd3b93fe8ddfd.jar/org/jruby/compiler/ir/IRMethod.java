// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir;

import org.jruby.parser.LocalStaticScope;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.instructions.ReceiveSelfInstruction;
import org.jruby.compiler.ir.instructions.ReceiveArgumentInstruction;
import org.jruby.compiler.ir.instructions.Instr;
import java.util.HashMap;
import java.util.ArrayList;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.ir.operands.LocalVariable;
import java.util.Map;
import org.jruby.compiler.ir.operands.Operand;
import java.util.List;
import org.jruby.compiler.ir.operands.Label;

public class IRMethod extends IRExecutionScope
{
    public final boolean isInstanceMethod;
    public final Label startLabel;
    public final Label endLabel;
    private CodeVersion version;
    private List<Operand> callArgs;
    private int nextLocalVariableSlot;
    private Map<String, LocalVariable> localVariables;
    private int nextAvailableBindingSlot;
    private Map<String, Integer> bindingSlotMap;
    
    public IRMethod(final IRScope lexicalParent, final Operand container, final String name, final boolean isInstanceMethod, final StaticScope staticScope) {
        super(lexicalParent, container, name, staticScope);
        this.isInstanceMethod = isInstanceMethod;
        this.startLabel = this.getNewLabel("_METH_START");
        this.endLabel = this.getNewLabel("_METH_END");
        this.callArgs = new ArrayList<Operand>();
        this.bindingSlotMap = new HashMap<String, Integer>();
        this.nextAvailableBindingSlot = 0;
        this.localVariables = new HashMap<String, LocalVariable>();
        this.nextLocalVariableSlot = 0;
        this.updateVersion();
    }
    
    public final void updateVersion() {
        this.version = CodeVersion.getClassVersionToken();
    }
    
    public String getScopeName() {
        return "Method";
    }
    
    public CodeVersion getVersion() {
        return this.version;
    }
    
    public void addInstr(final Instr i) {
        if (i instanceof ReceiveArgumentInstruction || i instanceof ReceiveSelfInstruction) {
            this.callArgs.add(i.result);
        }
        super.addInstr(i);
    }
    
    public Operand[] getCallArgs() {
        return this.callArgs.toArray(new Operand[this.callArgs.size()]);
    }
    
    public boolean isAClassRootMethod() {
        return IRModule.isAClassRootMethod(this);
    }
    
    public String getFullyQualifiedName() {
        final IRModule m = this.getDefiningIRModule();
        return (m == null) ? null : (m.getName() + ":" + this.getName());
    }
    
    public IRModule getDefiningIRModule() {
        if (!(this.container instanceof MetaObject)) {
            return null;
        }
        IRScope scope = ((MetaObject)this.container).scope;
        if (scope instanceof IRMethod) {
            scope = ((MetaObject)((IRMethod)scope).container).scope;
        }
        return (IRModule)scope;
    }
    
    protected StaticScope constructStaticScope(final StaticScope unused) {
        final LocalStaticScope newScope = new LocalStaticScope(null);
        this.requiredArgs = 0;
        this.optionalArgs = 0;
        this.restArg = -1;
        return newScope;
    }
    
    public LocalVariable getLocalVariable(final String name) {
        LocalVariable variable = this.localVariables.get(name);
        if (variable == null) {
            variable = new LocalVariable(name, this.nextLocalVariableSlot);
            this.localVariables.put(name, variable);
            ++this.nextLocalVariableSlot;
        }
        return variable;
    }
    
    public int assignBindingSlot(final String varName) {
        Integer slot = this.bindingSlotMap.get(varName);
        if (slot == null) {
            slot = this.nextAvailableBindingSlot;
            this.bindingSlotMap.put(varName, this.nextAvailableBindingSlot);
            ++this.nextAvailableBindingSlot;
        }
        return slot;
    }
    
    public Integer getBindingSlot(final String varName) {
        return this.bindingSlotMap.get(varName);
    }
    
    public int getBindingSlotsCount() {
        return this.nextAvailableBindingSlot;
    }
    
    public int getLocalVariablesCount() {
        return this.nextLocalVariableSlot;
    }
}
