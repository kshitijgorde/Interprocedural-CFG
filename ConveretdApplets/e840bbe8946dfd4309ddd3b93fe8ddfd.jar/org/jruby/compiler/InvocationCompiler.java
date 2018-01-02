// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.internal.runtime.methods.DynamicMethod;
import org.jruby.runtime.CallType;
import org.jruby.compiler.impl.SkinnyMethodAdapter;

public interface InvocationCompiler
{
    SkinnyMethodAdapter getMethodAdapter();
    
    void setMethodAdapter(final SkinnyMethodAdapter p0);
    
    void invokeDynamic(final String p0, final CompilerCallback p1, final ArgumentsCallback p2, final CallType p3, final CompilerCallback p4, final boolean p5);
    
    void invokeOpAsgnWithOr(final String p0, final String p1, final CompilerCallback p2, final ArgumentsCallback p3);
    
    void invokeOpAsgnWithAnd(final String p0, final String p1, final CompilerCallback p2, final ArgumentsCallback p3);
    
    void invokeOpAsgnWithMethod(final String p0, final String p1, final String p2, final CompilerCallback p3, final ArgumentsCallback p4);
    
    void invokeAttrAssignMasgn(final String p0, final CompilerCallback p1, final ArgumentsCallback p2);
    
    void invokeAttrAssign(final String p0, final CompilerCallback p1, final ArgumentsCallback p2);
    
    void opElementAsgnWithOr(final CompilerCallback p0, final ArgumentsCallback p1, final CompilerCallback p2);
    
    void opElementAsgnWithAnd(final CompilerCallback p0, final ArgumentsCallback p1, final CompilerCallback p2);
    
    void opElementAsgnWithMethod(final CompilerCallback p0, final ArgumentsCallback p1, final CompilerCallback p2, final String p3);
    
    void yield(final CompilerCallback p0, final boolean p1);
    
    void yieldSpecific(final ArgumentsCallback p0);
    
    void invokeEqq(final ArgumentsCallback p0, final CompilerCallback p1);
    
    void invokeBinaryFixnumRHS(final String p0, final CompilerCallback p1, final long p2);
    
    void invokeBinaryFloatRHS(final String p0, final CompilerCallback p1, final double p2);
    
    void invokeFixnumLong(final String p0, final int p1, final CompilerCallback p2, final String p3, final long p4);
    
    void invokeFloatDouble(final String p0, final int p1, final CompilerCallback p2, final String p3, final double p4);
    
    void invokeRecursive(final String p0, final int p1, final ArgumentsCallback p2, final CompilerCallback p3, final CallType p4, final boolean p5);
    
    void invokeNative(final String p0, final DynamicMethod.NativeCall p1, final int p2, final CompilerCallback p3, final ArgumentsCallback p4, final CompilerCallback p5, final CallType p6, final boolean p7);
    
    void invokeTrivial(final String p0, final int p1, final CompilerCallback p2);
}
