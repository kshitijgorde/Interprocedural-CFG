// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xalan.xsltc.compiler.util.Util;

final class Otherwise extends Instruction
{
    public void display(final int indent) {
        this.indent(indent);
        Util.println("Otherwise");
        this.indent(indent + 4);
        this.displayContents(indent + 4);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this.typeCheckContents(stable);
        return Type.Void;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final Parser parser = this.getParser();
        final ErrorMsg err = new ErrorMsg("STRAY_OTHERWISE_ERR", this);
        parser.reportError(3, err);
    }
}
