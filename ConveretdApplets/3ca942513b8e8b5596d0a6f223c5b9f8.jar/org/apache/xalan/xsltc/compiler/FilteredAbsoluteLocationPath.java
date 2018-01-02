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

final class FilteredAbsoluteLocationPath extends Expression
{
    private Expression _path;
    
    public FilteredAbsoluteLocationPath() {
        this._path = null;
    }
    
    public FilteredAbsoluteLocationPath(final Expression path) {
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
        return "FilteredAbsoluteLocationPath(" + ((this._path != null) ? this._path.toString() : "null") + ')';
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
            final int initDFI = cpg.addMethodref("org.apache.xalan.xsltc.dom.DupFilterIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;)V");
            final LocalVariableGen pathTemp = methodGen.addLocalVariable("filtered_absolute_location_path_tmp", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
            this._path.translate(classGen, methodGen);
            il.append(new ASTORE(pathTemp.getIndex()));
            il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.DupFilterIterator")));
            il.append(InstructionConstants.DUP);
            il.append(new ALOAD(pathTemp.getIndex()));
            il.append(new INVOKESPECIAL(initDFI));
        }
        else {
            final int git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getIterator", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(methodGen.loadDOM());
            il.append(new INVOKEINTERFACE(git, 1));
        }
    }
}
