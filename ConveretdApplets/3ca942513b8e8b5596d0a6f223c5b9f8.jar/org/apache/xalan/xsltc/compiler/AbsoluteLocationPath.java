// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.NEW;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.NodeType;
import org.apache.xalan.xsltc.compiler.util.Type;

final class AbsoluteLocationPath extends Expression
{
    private Expression _path;
    
    public AbsoluteLocationPath() {
        this._path = null;
    }
    
    public AbsoluteLocationPath(final Expression path) {
        this._path = path;
        if (path != null) {
            this._path.setParent(this);
        }
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        if (this._path != null) {
            this._path.setParser(parser);
        }
    }
    
    public Expression getPath() {
        return this._path;
    }
    
    public String toString() {
        return "AbsoluteLocationPath(" + ((this._path != null) ? this._path.toString() : "null") + ')';
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._path != null) {
            final Type ptype = this._path.typeCheck(stable);
            if (ptype instanceof NodeType) {
                this._path = new CastExpr(this._path, Type.NodeSet);
            }
        }
        return super._type = Type.NodeSet;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._path != null) {
            final int initAI = cpg.addMethodref("org.apache.xalan.xsltc.dom.AbsoluteIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;)V");
            this._path.translate(classGen, methodGen);
            final LocalVariableGen relPathIterator = methodGen.addLocalVariable("abs_location_path_tmp", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
            il.append(new ASTORE(relPathIterator.getIndex()));
            il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.AbsoluteIterator")));
            il.append(InstructionConstants.DUP);
            il.append(new ALOAD(relPathIterator.getIndex()));
            il.append(new INVOKESPECIAL(initAI));
        }
        else {
            final int gitr = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKEINTERFACE(gitr, 1));
        }
    }
}
