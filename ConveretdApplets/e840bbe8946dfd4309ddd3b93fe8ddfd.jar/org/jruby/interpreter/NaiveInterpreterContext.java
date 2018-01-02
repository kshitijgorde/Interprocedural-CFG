// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.interpreter;

import org.jruby.runtime.scope.SharedBindingDynamicScope;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.RubyModule;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.RubyException;
import org.jruby.runtime.DynamicScope;
import org.jruby.runtime.Block;
import org.jruby.runtime.Frame;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.ThreadContext;
import org.jruby.Ruby;

public class NaiveInterpreterContext implements InterpreterContext
{
    private final Ruby runtime;
    private final ThreadContext context;
    protected Object returnValue;
    protected Object self;
    protected IRubyObject[] parameters;
    protected Object[] temporaryVariables;
    protected Object[] renamedVariables;
    protected Object[] localVariables;
    protected Frame frame;
    protected Block block;
    protected DynamicScope currDynScope;
    protected boolean allocatedDynScope;
    protected RubyException currException;
    private Label methodExitLabel;
    private IRubyObject[] NO_PARAMS;
    
    public NaiveInterpreterContext(final ThreadContext context, final IRubyObject self, final int localVariablesSize, final int temporaryVariablesSize, final int renamedVariablesSize, final IRubyObject[] parameters, final Block block) {
        this.currDynScope = null;
        this.allocatedDynScope = false;
        this.currException = null;
        this.methodExitLabel = null;
        this.NO_PARAMS = new IRubyObject[0];
        context.preMethodFrameOnly(self.getMetaClass(), null, self, block);
        this.frame = context.getCurrentFrame();
        this.context = context;
        this.runtime = context.getRuntime();
        this.self = self;
        this.parameters = parameters;
        this.localVariables = (Object[])((localVariablesSize > 0) ? new Object[localVariablesSize] : null);
        this.temporaryVariables = (Object[])((temporaryVariablesSize > 0) ? new Object[temporaryVariablesSize] : null);
        this.renamedVariables = (Object[])((renamedVariablesSize > 0) ? new Object[renamedVariablesSize] : null);
        this.block = block;
    }
    
    public Ruby getRuntime() {
        return this.runtime;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public void setBlock(final Block block) {
        this.block = block;
    }
    
    public void setDynamicScope(final DynamicScope s) {
        this.currDynScope = s;
    }
    
    public void allocateSharedBindingScope(final IRMethod method) {
        this.allocatedDynScope = true;
        this.currDynScope = new SharedBindingDynamicScope(method.getStaticScope(), method);
        this.context.pushScope(this.currDynScope);
    }
    
    public DynamicScope getSharedBindingScope() {
        return this.currDynScope;
    }
    
    public boolean hasAllocatedDynamicScope() {
        return this.allocatedDynScope;
    }
    
    public Object getReturnValue() {
        return (this.returnValue == null) ? this.context.getRuntime().getNil() : this.returnValue;
    }
    
    public void setReturnValue(final Object returnValue) {
        this.returnValue = returnValue;
    }
    
    public Object getTemporaryVariable(final int offset) {
        return this.temporaryVariables[offset];
    }
    
    public Object setTemporaryVariable(final int offset, final Object value) {
        final Object oldValue = this.temporaryVariables[offset];
        this.temporaryVariables[offset] = value;
        return oldValue;
    }
    
    public void updateRenamedVariablesCount(final int n) {
        final Object[] oldRenamedVars = this.renamedVariables;
        this.renamedVariables = new Object[n];
        for (int i = 0; i < oldRenamedVars.length; ++i) {
            this.renamedVariables[i] = oldRenamedVars[i];
        }
    }
    
    public void updateLocalVariablesCount(final int n) {
        final Object[] oldLocalVars = this.localVariables;
        this.localVariables = new Object[n];
        for (int i = 0; i < oldLocalVars.length; ++i) {
            this.localVariables[i] = oldLocalVars[i];
        }
    }
    
    public Object getRenamedVariable(final int offset) {
        return this.renamedVariables[offset];
    }
    
    public Object setRenamedVariable(final int offset, final Object value) {
        final Object oldValue = this.renamedVariables[offset];
        this.renamedVariables[offset] = value;
        return oldValue;
    }
    
    public Object getSharedBindingVariable(final int bindingSlot) {
        Object value = this.currDynScope.getValue(bindingSlot, 0);
        if (value == null) {
            value = this.getRuntime().getNil();
        }
        return value;
    }
    
    public void setSharedBindingVariable(final int bindingSlot, final Object value) {
        this.currDynScope.setValueDepthZero((IRubyObject)value, bindingSlot);
    }
    
    public Object getLocalVariable(final int offset) {
        return this.localVariables[offset];
    }
    
    public Object setLocalVariable(final int offset, final Object value) {
        final Object oldValue = this.localVariables[offset];
        this.localVariables[offset] = value;
        return oldValue;
    }
    
    public ThreadContext getContext() {
        return this.context;
    }
    
    public Object getParameter(final int offset) {
        return this.parameters[offset];
    }
    
    public int getParameterCount() {
        return this.parameters.length;
    }
    
    public Object getSelf() {
        return this.self;
    }
    
    public Frame getFrame() {
        return this.frame;
    }
    
    public void setFrame(final Frame frame) {
        this.frame = frame;
    }
    
    public IRubyObject[] getParametersFrom(final int argIndex) {
        final int length = this.parameters.length - argIndex;
        if (length <= 0) {
            return this.NO_PARAMS;
        }
        final IRubyObject[] args = new IRubyObject[length];
        System.arraycopy(this.parameters, argIndex, args, 0, length);
        return args;
    }
    
    public void setMethodExitLabel(final Label l) {
        this.methodExitLabel = l;
    }
    
    public Label getMethodExitLabel() {
        return this.methodExitLabel;
    }
    
    public void setException(final RubyException e) {
        this.currException = e;
    }
    
    public RubyException getException() {
        return this.currException;
    }
}
