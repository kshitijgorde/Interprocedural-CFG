// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class Fallback extends Instruction
{
    private boolean _active;
    
    Fallback() {
        this._active = false;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._active) {
            return this.typeCheckContents(stable);
        }
        return Type.Void;
    }
    
    public void activate() {
        this._active = true;
    }
    
    public String toString() {
        return "fallback";
    }
    
    public void parseContents(final Parser parser) {
        if (this._active) {
            this.parseChildren(parser);
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._active) {
            this.translateContents(classGen, methodGen);
        }
    }
}
