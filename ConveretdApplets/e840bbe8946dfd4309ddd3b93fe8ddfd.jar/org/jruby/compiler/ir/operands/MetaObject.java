// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.Ruby;
import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.internal.runtime.methods.InterpretedIRMethod;
import org.jruby.RubyModule;
import org.jruby.runtime.ThreadContext;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.IRClosure;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRClass;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.compiler.ir.IRScope;

public class MetaObject extends Operand
{
    public final IRScope scope;
    
    protected MetaObject(final IRScope scope) {
        this.scope = scope;
    }
    
    public static MetaObject create(IRScope scope) {
        if (scope instanceof IRMethod) {
            scope = scope.getNearestModule();
        }
        if (scope instanceof IRClass) {
            return new ClassMetaObject((IRClass)scope);
        }
        if (scope instanceof IRModule) {
            return new ModuleMetaObject((IRModule)scope);
        }
        if (scope instanceof IRClosure) {
            return new ClosureMetaObject((IRClosure)scope);
        }
        assert false : "IRSCript created";
        return new MetaObject(scope);
    }
    
    public String toString() {
        return (this.scope == null) ? "<NULL SCOPE>" : this.scope.toString();
    }
    
    public boolean isConstant() {
        return true;
    }
    
    public boolean isClass() {
        return false;
    }
    
    public boolean isModule() {
        return false;
    }
    
    public boolean isClosure() {
        return false;
    }
    
    public IRScope getScope() {
        return this.scope;
    }
    
    public Operand getNearestClass() {
        if (this.isClass()) {
            return this;
        }
        Operand parent;
        for (parent = this.getContainer(); parent instanceof MetaObject && !((MetaObject)parent).isClass(); parent = ((MetaObject)parent).getContainer()) {}
        return parent;
    }
    
    public Operand getContainer() {
        return this.scope.getContainer();
    }
    
    public IRClass getTargetClass() {
        return (this.scope instanceof IRModule) ? IRModule.getCoreClass("Module") : null;
    }
    
    public RubyModule interpretBody(final InterpreterContext interp, final ThreadContext context, final RubyModule module) {
        this.scope.getStaticScope().setModule(module);
        final IRMethod rootMethod = ((IRModule)this.scope).getRootMethod();
        final DynamicMethod method = new InterpretedIRMethod(rootMethod, module.getMetaClass());
        method.call(context, module, module.getMetaClass(), "", new IRubyObject[0]);
        return module;
    }
    
    public RubyModule getContainer(final InterpreterContext interp, final Ruby runtime) {
        return (this.scope.getContainer() != null) ? ((RubyModule)this.scope.getContainer().retrieve(interp)) : runtime.getObject();
    }
}
