// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.NEW;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import org.apache.xml.dtm.Axis;
import java.util.Vector;

final class UnionPathExpr extends Expression
{
    private final Expression _pathExpr;
    private final Expression _rest;
    private boolean _reverse;
    private Expression[] _components;
    
    public UnionPathExpr(final Expression pathExpr, final Expression rest) {
        this._reverse = false;
        this._pathExpr = pathExpr;
        this._rest = rest;
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        final Vector components = new Vector();
        this.flatten(components);
        final int size = components.size();
        this._components = components.toArray(new Expression[size]);
        for (int i = 0; i < size; ++i) {
            this._components[i].setParser(parser);
            this._components[i].setParent(this);
            if (this._components[i] instanceof Step) {
                final Step step = (Step)this._components[i];
                final int axis = step.getAxis();
                final int type = step.getNodeType();
                if (axis == 2 || type == 2) {
                    this._components[i] = this._components[0];
                    this._components[0] = step;
                }
                if (Axis.isReverse(axis)) {
                    this._reverse = true;
                }
            }
        }
        if (this.getParent() instanceof Expression) {
            this._reverse = false;
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        for (int length = this._components.length, i = 0; i < length; ++i) {
            if (this._components[i].typeCheck(stable) != Type.NodeSet) {
                this._components[i] = new CastExpr(this._components[i], Type.NodeSet);
            }
        }
        return super._type = Type.NodeSet;
    }
    
    public String toString() {
        return "union(" + this._pathExpr + ", " + this._rest + ')';
    }
    
    private void flatten(final Vector components) {
        components.addElement(this._pathExpr);
        if (this._rest != null) {
            if (this._rest instanceof UnionPathExpr) {
                ((UnionPathExpr)this._rest).flatten(components);
            }
            else {
                components.addElement(this._rest);
            }
        }
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.UnionIterator", "<init>", "(Lorg/apache/xalan/xsltc/DOM;)V");
        final int iter = cpg.addMethodref("org.apache.xalan.xsltc.dom.UnionIterator", "addIterator", "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/apache/xalan/xsltc/dom/UnionIterator;");
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.UnionIterator")));
        il.append(InstructionConstants.DUP);
        il.append(methodGen.loadDOM());
        il.append(new INVOKESPECIAL(init));
        for (int length = this._components.length, i = 0; i < length; ++i) {
            this._components[i].translate(classGen, methodGen);
            il.append(new INVOKEVIRTUAL(iter));
        }
        if (this._reverse) {
            final int order = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "orderNodes", "(Lorg/apache/xml/dtm/DTMAxisIterator;I)Lorg/apache/xml/dtm/DTMAxisIterator;");
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
            il.append(methodGen.loadContextNode());
            il.append(new INVOKEINTERFACE(order, 3));
        }
    }
}
