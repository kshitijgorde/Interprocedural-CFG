// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.variable;

import org.jruby.Ruby;
import org.jruby.RubyObject;
import org.jruby.runtime.builtin.IRubyObject;

public interface BiVariable
{
    Type getType();
    
    IRubyObject getReceiver();
    
    boolean isReceiverIdentical(final RubyObject p0);
    
    String getName();
    
    Object getJavaObject();
    
    void setJavaObject(final Ruby p0, final Object p1);
    
    void inject();
    
    IRubyObject getRubyObject();
    
    void setRubyObject(final IRubyObject p0);
    
    void remove();
    
    public enum Type
    {
        Argv, 
        Constant, 
        GlobalVariable, 
        LocalGlobalVariable, 
        ClassVariable, 
        InstanceVariable, 
        LocalVariable;
    }
}
