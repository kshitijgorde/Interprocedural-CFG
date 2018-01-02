// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler;

import org.jruby.org.objectweb.asm.Label;
import org.jruby.parser.StaticScope;
import org.jruby.compiler.impl.SkinnyMethodAdapter;

public interface VariableCompiler
{
    SkinnyMethodAdapter getMethodAdapter();
    
    void setMethodAdapter(final SkinnyMethodAdapter p0);
    
    void beginMethod(final CompilerCallback p0, final StaticScope p1);
    
    void beginClass(final CompilerCallback p0, final StaticScope p1);
    
    void beginClosure(final CompilerCallback p0, final StaticScope p1);
    
    void beginFlatClosure(final CompilerCallback p0, final StaticScope p1);
    
    void assignLocalVariable(final int p0, final boolean p1);
    
    void assignLocalVariable(final int p0, final int p1, final boolean p2);
    
    void assignLocalVariable(final int p0, final int p1, final CompilerCallback p2, final boolean p3);
    
    void retrieveLocalVariable(final int p0);
    
    void assignLastLine();
    
    void assignLastLine(final CompilerCallback p0);
    
    void retrieveLastLine();
    
    void assignBackRef();
    
    void assignBackRef(final CompilerCallback p0);
    
    void retrieveBackRef();
    
    void retrieveLocalVariable(final int p0, final int p1);
    
    void checkMethodArity(final int p0, final int p1, final int p2);
    
    void assignMethodArguments(final Object p0, final int p1, final Object p2, final int p3, final ArrayCallback p4, final ArrayCallback p5, final ArrayCallback p6, final CompilerCallback p7, final CompilerCallback p8);
    
    void assignMethodArguments19(final Object p0, final int p1, final Object p2, final int p3, final int p4, final Object p5, final int p6, final ArrayCallback p7, final ArrayCallback p8, final ArrayCallback p9, final CompilerCallback p10, final CompilerCallback p11);
    
    void assignClosureArguments(final CompilerCallback p0, final CompilerCallback p1);
    
    int grabTempLocal();
    
    void setTempLocal(final int p0);
    
    void getTempLocal(final int p0);
    
    void releaseTempLocal();
    
    void declareLocals(final StaticScope p0, final Label p1, final Label p2);
    
    boolean isHeap();
}
