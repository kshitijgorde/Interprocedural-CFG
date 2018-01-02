// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
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
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import org.apache.xalan.xsltc.compiler.util.Type;

final class FilterParentPath extends Expression
{
    private Expression _filterExpr;
    private Expression _path;
    private boolean _hasDescendantAxis;
    
    public FilterParentPath(final Expression filterExpr, final Expression path) {
        this._hasDescendantAxis = false;
        (this._path = path).setParent(this);
        (this._filterExpr = filterExpr).setParent(this);
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._filterExpr.setParser(parser);
        this._path.setParser(parser);
    }
    
    public String toString() {
        return "FilterParentPath(" + this._filterExpr + ", " + this._path + ')';
    }
    
    public void setDescendantAxis() {
        this._hasDescendantAxis = true;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type ftype = this._filterExpr.typeCheck(stable);
        if (!(ftype instanceof NodeSetType)) {
            if (ftype instanceof ReferenceType) {
                this._filterExpr = new CastExpr(this._filterExpr, Type.NodeSet);
            }
            else {
                if (!(ftype instanceof NodeType)) {
                    throw new TypeCheckError(this);
                }
                this._filterExpr = new CastExpr(this._filterExpr, Type.NodeSet);
            }
        }
        final Type ptype = this._path.typeCheck(stable);
        if (!(ptype instanceof NodeSetType)) {
            this._path = new CastExpr(this._path, Type.NodeSet);
        }
        return super._type = Type.NodeSet;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int initSI = cpg.addMethodref("org.apache.xalan.xsltc.dom.StepIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/dtm/DTMAxisIterator;)V");
        this._filterExpr.translate(classGen, methodGen);
        final LocalVariableGen filterTemp = methodGen.addLocalVariable("filter_parent_path_tmp1", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
        il.append(new ASTORE(filterTemp.getIndex()));
        this._path.translate(classGen, methodGen);
        final LocalVariableGen pathTemp = methodGen.addLocalVariable("filter_parent_path_tmp2", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
        il.append(new ASTORE(pathTemp.getIndex()));
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.StepIterator")));
        il.append(InstructionConstants.DUP);
        il.append(new ALOAD(filterTemp.getIndex()));
        il.append(new ALOAD(pathTemp.getIndex()));
        il.append(new INVOKESPECIAL(initSI));
        if (this._hasDescendantAxis) {
            final int incl = cpg.addMethodref("org.apache.xml.dtm.ref.DTMAxisIteratorBase", "includeSelf", "()Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(new INVOKEVIRTUAL(incl));
        }
        if (!(this.getParent() instanceof RelativeLocationPath) && !(this.getParent() instanceof FilterParentPath)) {
            final int order = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "orderNodes", "(Lorg/apache/xml/dtm/DTMAxisIterator;I)Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
            il.append(methodGen.loadContextNode());
            il.append(new INVOKEINTERFACE(order, 3));
        }
    }
}
