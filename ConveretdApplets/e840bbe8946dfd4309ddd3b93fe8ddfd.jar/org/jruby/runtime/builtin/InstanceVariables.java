// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.builtin;

import java.util.List;

public interface InstanceVariables
{
    boolean hasInstanceVariable(final String p0);
    
    boolean fastHasInstanceVariable(final String p0);
    
    IRubyObject getInstanceVariable(final String p0);
    
    IRubyObject fastGetInstanceVariable(final String p0);
    
    IRubyObject setInstanceVariable(final String p0, final IRubyObject p1);
    
    IRubyObject fastSetInstanceVariable(final String p0, final IRubyObject p1);
    
    IRubyObject removeInstanceVariable(final String p0);
    
    List<Variable<IRubyObject>> getInstanceVariableList();
    
    List<String> getInstanceVariableNameList();
    
    void copyInstanceVariablesInto(final InstanceVariables p0);
}
