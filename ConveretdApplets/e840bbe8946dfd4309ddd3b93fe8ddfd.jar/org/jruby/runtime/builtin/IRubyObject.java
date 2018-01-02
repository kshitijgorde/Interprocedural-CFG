// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.builtin;

import java.util.List;
import org.jruby.RubyInteger;
import org.jruby.RubyFloat;
import org.jruby.RubyHash;
import org.jruby.RubyArray;
import org.jruby.RubyString;
import org.jruby.Ruby;
import org.jruby.RubyClass;
import org.jruby.runtime.Block;
import org.jruby.runtime.ThreadContext;

public interface IRubyObject
{
    public static final IRubyObject[] NULL_ARRAY = new IRubyObject[0];
    
    @Deprecated
    IRubyObject callSuper(final ThreadContext p0, final IRubyObject[] p1, final Block p2);
    
    IRubyObject callMethod(final ThreadContext p0, final String p1);
    
    IRubyObject callMethod(final ThreadContext p0, final String p1, final IRubyObject p2);
    
    IRubyObject callMethod(final ThreadContext p0, final String p1, final IRubyObject[] p2);
    
    IRubyObject callMethod(final ThreadContext p0, final String p1, final IRubyObject[] p2, final Block p3);
    
    @Deprecated
    IRubyObject callMethod(final ThreadContext p0, final int p1, final String p2);
    
    @Deprecated
    IRubyObject callMethod(final ThreadContext p0, final int p1, final String p2, final IRubyObject p3);
    
    IRubyObject checkCallMethod(final ThreadContext p0, final String p1);
    
    boolean isNil();
    
    boolean isTrue();
    
    boolean isTaint();
    
    void setTaint(final boolean p0);
    
    IRubyObject infectBy(final IRubyObject p0);
    
    boolean isFrozen();
    
    void setFrozen(final boolean p0);
    
    boolean isUntrusted();
    
    void setUntrusted(final boolean p0);
    
    boolean isImmediate();
    
    RubyClass getMetaClass();
    
    RubyClass getSingletonClass();
    
    RubyClass getType();
    
    boolean respondsTo(final String p0);
    
    boolean respondsToMissing(final String p0);
    
    boolean respondsToMissing(final String p0, final boolean p1);
    
    Ruby getRuntime();
    
    Class getJavaClass();
    
    String asJavaString();
    
    RubyString asString();
    
    RubyArray convertToArray();
    
    RubyHash convertToHash();
    
    RubyFloat convertToFloat();
    
    RubyInteger convertToInteger();
    
    @Deprecated
    RubyInteger convertToInteger(final int p0, final String p1);
    
    RubyInteger convertToInteger(final String p0);
    
    RubyString convertToString();
    
    IRubyObject anyToString();
    
    IRubyObject checkStringType();
    
    IRubyObject checkStringType19();
    
    IRubyObject checkArrayType();
    
    Object toJava(final Class p0);
    
    IRubyObject dup();
    
    IRubyObject inspect();
    
    IRubyObject rbClone();
    
    boolean isModule();
    
    boolean isClass();
    
    void dataWrapStruct(final Object p0);
    
    Object dataGetStruct();
    
    Object dataGetStructChecked();
    
    IRubyObject id();
    
    IRubyObject op_equal(final ThreadContext p0, final IRubyObject p1);
    
    IRubyObject op_eqq(final ThreadContext p0, final IRubyObject p1);
    
    boolean eql(final IRubyObject p0);
    
    void addFinalizer(final IRubyObject p0);
    
    void removeFinalizers();
    
    boolean hasVariables();
    
    int getVariableCount();
    
    @Deprecated
    void syncVariables(final List<Variable<Object>> p0);
    
    void syncVariables(final IRubyObject p0);
    
    List<Variable<Object>> getVariableList();
    
    InstanceVariables getInstanceVariables();
    
    InternalVariables getInternalVariables();
    
    List<String> getVariableNameList();
    
    void copySpecialInstanceVariables(final IRubyObject p0);
    
    Object getVariable(final int p0);
    
    void setVariable(final int p0, final Object p1);
}
