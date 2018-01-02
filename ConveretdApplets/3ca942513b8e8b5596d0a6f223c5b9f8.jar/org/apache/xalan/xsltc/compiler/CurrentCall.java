// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;

final class CurrentCall extends FunctionCall
{
    public CurrentCall(final QName fname) {
        super(fname);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        methodGen.getInstructionList().append(methodGen.loadCurrentNode());
    }
}
