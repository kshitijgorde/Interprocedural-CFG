// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.org.objectweb.asm.tree.analysis;

import java.util.List;
import org.jruby.org.objectweb.asm.tree.AbstractInsnNode;
import org.jruby.org.objectweb.asm.Type;

public interface Interpreter
{
    Value newValue(final Type p0);
    
    Value newOperation(final AbstractInsnNode p0) throws AnalyzerException;
    
    Value copyOperation(final AbstractInsnNode p0, final Value p1) throws AnalyzerException;
    
    Value unaryOperation(final AbstractInsnNode p0, final Value p1) throws AnalyzerException;
    
    Value binaryOperation(final AbstractInsnNode p0, final Value p1, final Value p2) throws AnalyzerException;
    
    Value ternaryOperation(final AbstractInsnNode p0, final Value p1, final Value p2, final Value p3) throws AnalyzerException;
    
    Value naryOperation(final AbstractInsnNode p0, final List p1) throws AnalyzerException;
    
    void returnOperation(final AbstractInsnNode p0, final Value p1, final Value p2) throws AnalyzerException;
    
    Value merge(final Value p0, final Value p1);
}
