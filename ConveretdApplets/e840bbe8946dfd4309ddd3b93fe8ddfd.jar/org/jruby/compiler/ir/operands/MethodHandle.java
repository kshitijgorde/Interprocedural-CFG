// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.RubyClass;
import org.jruby.RubySymbol;
import org.jruby.RubyString;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.callsite.CacheEntry;

public class MethodHandle extends Operand
{
    protected Operand methodName;
    protected Operand receiver;
    private String resolvedMethodName;
    private CacheEntry cachedMethod;
    private IRubyObject receiverObj;
    
    public MethodHandle(final Operand methodName, final Operand receiver) {
        this.methodName = methodName;
        this.receiver = receiver;
    }
    
    public Operand getMethodNameOperand() {
        return this.methodName;
    }
    
    public DynamicMethod getResolvedMethod() {
        return this.cachedMethod.method;
    }
    
    public String getResolvedMethodName() {
        return this.resolvedMethodName;
    }
    
    public IRubyObject getReceiverObj() {
        return this.receiverObj;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        this.methodName = this.methodName.getSimplifiedOperand(valueMap);
        this.receiver = this.receiver.getSimplifiedOperand(valueMap);
        return this;
    }
    
    public String toString() {
        return "<" + this.receiver + "." + this.methodName + ">";
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return new MethodHandle(this.methodName.cloneForInlining(ii), this.receiver.cloneForInlining(ii));
    }
    
    public Object retrieve(final InterpreterContext interp) {
        this.receiverObj = (IRubyObject)this.receiver.retrieve(interp);
        if (this.methodName instanceof MethAddr) {
            this.resolvedMethodName = ((MethAddr)this.methodName).getName();
        }
        else {
            final IRubyObject mnameObj = (IRubyObject)this.methodName.retrieve(interp);
            assert mnameObj instanceof RubyString || mnameObj instanceof RubySymbol;
            if (!mnameObj.toString().equals(this.resolvedMethodName)) {
                this.cachedMethod = null;
                this.resolvedMethodName = mnameObj.toString();
            }
        }
        final RubyClass receiverClass = this.receiverObj.getMetaClass();
        if (this.cachedMethod == null || !this.cachedMethod.typeOk(receiverClass)) {
            this.cachedMethod = receiverClass.searchWithCache(this.resolvedMethodName);
        }
        return this;
    }
}
