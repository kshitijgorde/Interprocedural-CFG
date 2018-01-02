// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.BranchHandle;
import java.util.Enumeration;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.IFGT;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ResultTreeType;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import org.apache.xalan.xsltc.compiler.util.NodeType;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.Type;

final class ForEach extends Instruction
{
    private Expression _select;
    private Type _type;
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("ForEach");
        this.indent(indent + 4);
        Util.println("select " + this._select.toString());
        this.displayContents(indent + 4);
    }
    
    public void parseContents(final Parser parser) {
        this._select = parser.parseExpression(this, "select", null);
        this.parseChildren(parser);
        if (this._select.isDummy()) {
            this.reportError(this, parser, "REQUIRED_ATTR_ERR", "select");
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this._type = this._select.typeCheck(stable);
        if (this._type instanceof ReferenceType || this._type instanceof NodeType) {
            this._select = new CastExpr(this._select, Type.NodeSet);
            this.typeCheckContents(stable);
            return Type.Void;
        }
        if (this._type instanceof NodeSetType || this._type instanceof ResultTreeType) {
            this.typeCheckContents(stable);
            return Type.Void;
        }
        throw new TypeCheckError(this);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        il.append(methodGen.loadCurrentNode());
        il.append(methodGen.loadIterator());
        final Vector sortObjects = new Vector();
        final Enumeration children = this.elements();
        while (children.hasMoreElements()) {
            final Object child = children.nextElement();
            if (child instanceof Sort) {
                sortObjects.addElement(child);
            }
        }
        if (this._type != null && this._type instanceof ResultTreeType) {
            il.append(methodGen.loadDOM());
            if (sortObjects.size() > 0) {
                final ErrorMsg msg = new ErrorMsg("RESULT_TREE_SORT_ERR", this);
                this.getParser().reportError(4, msg);
            }
            this._select.translate(classGen, methodGen);
            this._type.translateTo(classGen, methodGen, Type.NodeSet);
            il.append(InstructionConstants.SWAP);
            il.append(methodGen.storeDOM());
        }
        else {
            if (sortObjects.size() > 0) {
                Sort.translateSortIterator(classGen, methodGen, this._select, sortObjects);
            }
            else {
                this._select.translate(classGen, methodGen);
            }
            if (!(this._type instanceof ReferenceType)) {
                il.append(methodGen.loadContextNode());
                il.append(methodGen.setStartNode());
            }
        }
        il.append(methodGen.storeIterator());
        this.initializeVariables(classGen, methodGen);
        final BranchHandle nextNode = il.append(new GOTO(null));
        final InstructionHandle loop = il.append(InstructionConstants.NOP);
        this.translateContents(classGen, methodGen);
        nextNode.setTarget(il.append(methodGen.loadIterator()));
        il.append(methodGen.nextNode());
        il.append(InstructionConstants.DUP);
        il.append(methodGen.storeCurrentNode());
        il.append(new IFGT(loop));
        if (this._type != null && this._type instanceof ResultTreeType) {
            il.append(methodGen.storeDOM());
        }
        il.append(methodGen.storeIterator());
        il.append(methodGen.storeCurrentNode());
    }
    
    public void initializeVariables(final ClassGenerator classGen, final MethodGenerator methodGen) {
        for (int n = this.elementCount(), i = 0; i < n; ++i) {
            final Object child = this.getContents().elementAt(i);
            if (child instanceof Variable) {
                final Variable var = (Variable)child;
                var.initialize(classGen, methodGen);
            }
        }
    }
}
