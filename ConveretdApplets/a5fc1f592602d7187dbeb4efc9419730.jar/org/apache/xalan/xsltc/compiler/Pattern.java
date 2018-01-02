// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

public abstract class Pattern extends Expression
{
    public abstract Type typeCheck(final SymbolTable p0) throws TypeCheckError;
    
    public abstract void translate(final ClassGenerator p0, final MethodGenerator p1);
    
    public abstract double getPriority();
}
