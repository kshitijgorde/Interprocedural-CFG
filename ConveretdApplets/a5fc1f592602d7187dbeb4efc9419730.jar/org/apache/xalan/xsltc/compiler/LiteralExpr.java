// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class LiteralExpr extends Expression
{
    private final String _value;
    private final String _namespace;
    
    public LiteralExpr(final String value) {
        this._value = value;
        this._namespace = null;
    }
    
    public LiteralExpr(final String value, final String namespace) {
        this._value = value;
        this._namespace = (namespace.equals("") ? null : namespace);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return super._type = Type.String;
    }
    
    public String toString() {
        return "literal-expr(" + this._value + ')';
    }
    
    protected boolean contextDependent() {
        return false;
    }
    
    protected String getValue() {
        return this._value;
    }
    
    protected String getNamespace() {
        return this._namespace;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(new PUSH(cpg, this._value));
    }
}
