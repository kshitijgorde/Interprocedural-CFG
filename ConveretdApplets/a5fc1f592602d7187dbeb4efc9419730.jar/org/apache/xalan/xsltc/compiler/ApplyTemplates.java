// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Enumeration;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ResultTreeType;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.NodeType;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xml.utils.XMLChar;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.Type;

final class ApplyTemplates extends Instruction
{
    private Expression _select;
    private Type _type;
    private QName _modeName;
    private String _functionName;
    
    ApplyTemplates() {
        this._type = null;
    }
    
    public void display(final int indent) {
        this.indent(indent);
        Util.println("ApplyTemplates");
        this.indent(indent + 4);
        Util.println("select " + this._select.toString());
        if (this._modeName != null) {
            this.indent(indent + 4);
            Util.println("mode " + this._modeName);
        }
    }
    
    public boolean hasWithParams() {
        return this.hasContents();
    }
    
    public void parseContents(final Parser parser) {
        final String select = this.getAttribute("select");
        final String mode = this.getAttribute("mode");
        if (select.length() > 0) {
            this._select = parser.parseExpression(this, "select", null);
        }
        if (mode.length() > 0) {
            if (!XMLChar.isValidQName(mode)) {
                final ErrorMsg err = new ErrorMsg("INVALID_QNAME_ERR", mode, this);
                parser.reportError(3, err);
            }
            this._modeName = parser.getQNameIgnoreDefaultNs(mode);
        }
        this._functionName = parser.getTopLevelStylesheet().getMode(this._modeName).functionName();
        this.parseChildren(parser);
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this._select == null) {
            this.typeCheckContents(stable);
            return Type.Void;
        }
        this._type = this._select.typeCheck(stable);
        if (this._type instanceof NodeType || this._type instanceof ReferenceType) {
            this._select = new CastExpr(this._select, Type.NodeSet);
            this._type = Type.NodeSet;
        }
        if (this._type instanceof NodeSetType || this._type instanceof ResultTreeType) {
            this.typeCheckContents(stable);
            return Type.Void;
        }
        throw new TypeCheckError(this);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        boolean setStartNodeCalled = false;
        final Stylesheet stylesheet = classGen.getStylesheet();
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int current = methodGen.getLocalIndex("current");
        final Vector sortObjects = new Vector();
        final Enumeration children = this.elements();
        while (children.hasMoreElements()) {
            final Object child = children.nextElement();
            if (child instanceof Sort) {
                sortObjects.addElement(child);
            }
        }
        if (stylesheet.hasLocalParams() || this.hasContents()) {
            il.append(classGen.loadTranslet());
            final int pushFrame = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "pushParamFrame", "()V");
            il.append(new INVOKEVIRTUAL(pushFrame));
            this.translateContents(classGen, methodGen);
        }
        il.append(classGen.loadTranslet());
        if (this._type != null && this._type instanceof ResultTreeType) {
            if (sortObjects.size() > 0) {
                final ErrorMsg err = new ErrorMsg("RESULT_TREE_SORT_ERR", this);
                this.getParser().reportError(4, err);
            }
            this._select.translate(classGen, methodGen);
            this._type.translateTo(classGen, methodGen, Type.NodeSet);
        }
        else {
            il.append(methodGen.loadDOM());
            if (sortObjects.size() > 0) {
                Sort.translateSortIterator(classGen, methodGen, this._select, sortObjects);
                final int setStartNode = cpg.addInterfaceMethodref("org.apache.xml.dtm.DTMAxisIterator", "setStartNode", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
                il.append(methodGen.loadCurrentNode());
                il.append(new INVOKEINTERFACE(setStartNode, 2));
                setStartNodeCalled = true;
            }
            else if (this._select == null) {
                Mode.compileGetChildren(classGen, methodGen, current);
            }
            else {
                this._select.translate(classGen, methodGen);
            }
        }
        if (this._select != null && !setStartNodeCalled) {
            this._select.startIterator(classGen, methodGen);
        }
        final String className = classGen.getStylesheet().getClassName();
        il.append(methodGen.loadHandler());
        final String applyTemplatesSig = classGen.getApplyTemplatesSig();
        final int applyTemplates = cpg.addMethodref(className, this._functionName, applyTemplatesSig);
        il.append(new INVOKEVIRTUAL(applyTemplates));
        if (stylesheet.hasLocalParams() || this.hasContents()) {
            il.append(classGen.loadTranslet());
            final int popFrame = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "popParamFrame", "()V");
            il.append(new INVOKEVIRTUAL(popFrame));
        }
    }
}
