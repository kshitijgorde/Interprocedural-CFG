// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.interpreter;

import org.jruby.RubyException;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.Frame;
import org.jruby.Ruby;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.Block;
import org.jruby.compiler.ir.IRMethod;
import org.jruby.runtime.DynamicScope;

public interface InterpreterContext
{
    Object getParameter(final int p0);
    
    int getParameterCount();
    
    Object getReturnValue();
    
    void setReturnValue(final Object p0);
    
    Object getTemporaryVariable(final int p0);
    
    Object setTemporaryVariable(final int p0, final Object p1);
    
    Object getLocalVariable(final int p0);
    
    Object setLocalVariable(final int p0, final Object p1);
    
    void updateRenamedVariablesCount(final int p0);
    
    Object getRenamedVariable(final int p0);
    
    Object setRenamedVariable(final int p0, final Object p1);
    
    void setDynamicScope(final DynamicScope p0);
    
    void allocateSharedBindingScope(final IRMethod p0);
    
    DynamicScope getSharedBindingScope();
    
    boolean hasAllocatedDynamicScope();
    
    Object getSharedBindingVariable(final int p0);
    
    void setSharedBindingVariable(final int p0, final Object p1);
    
    Block getBlock();
    
    void setBlock(final Block p0);
    
    Object getSelf();
    
    ThreadContext getContext();
    
    Ruby getRuntime();
    
    void setFrame(final Frame p0);
    
    Frame getFrame();
    
    IRubyObject[] getParametersFrom(final int p0);
    
    void setMethodExitLabel(final Label p0);
    
    Label getMethodExitLabel();
    
    void setException(final RubyException p0);
    
    RubyException getException();
}
