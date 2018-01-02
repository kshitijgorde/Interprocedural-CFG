// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.xml.dtm.Axis;
import org.apache.bcel.generic.CHECKCAST;
import org.apache.bcel.generic.ICONST;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.NEW;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Collection;
import java.util.Vector;

final class Step extends RelativeLocationPath
{
    private int _axis;
    private Vector _predicates;
    private boolean _hadPredicates;
    private int _nodeType;
    
    public Step(final int axis, final int nodeType, final Vector predicates) {
        this._hadPredicates = false;
        this._axis = axis;
        this._nodeType = nodeType;
        this._predicates = predicates;
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        if (this._predicates != null) {
            for (int n = this._predicates.size(), i = 0; i < n; ++i) {
                final Predicate exp = this._predicates.elementAt(i);
                exp.setParser(parser);
                exp.setParent(this);
            }
        }
    }
    
    public int getAxis() {
        return this._axis;
    }
    
    public void setAxis(final int axis) {
        this._axis = axis;
    }
    
    public int getNodeType() {
        return this._nodeType;
    }
    
    public Vector getPredicates() {
        return this._predicates;
    }
    
    public void addPredicates(final Vector predicates) {
        if (this._predicates == null) {
            this._predicates = predicates;
        }
        else {
            this._predicates.addAll(predicates);
        }
    }
    
    private boolean hasParentPattern() {
        final SyntaxTreeNode parent = this.getParent();
        return parent instanceof ParentPattern || parent instanceof ParentLocationPath || parent instanceof UnionPathExpr || parent instanceof FilterParentPath;
    }
    
    private boolean hasPredicates() {
        return this._predicates != null && this._predicates.size() > 0;
    }
    
    private boolean isPredicate() {
        SyntaxTreeNode parent = this;
        while (parent != null) {
            parent = parent.getParent();
            if (parent instanceof Predicate) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isAbbreviatedDot() {
        return this._nodeType == -1 && this._axis == 13;
    }
    
    public boolean isAbbreviatedDDot() {
        return this._nodeType == -1 && this._axis == 10;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this._hadPredicates = this.hasPredicates();
        if (this.isAbbreviatedDot()) {
            super._type = ((this.hasParentPattern() || this.hasPredicates()) ? Type.NodeSet : Type.Node);
        }
        else {
            super._type = Type.NodeSet;
        }
        if (this._predicates != null) {
            for (int n = this._predicates.size(), i = 0; i < n; ++i) {
                final Expression pred = this._predicates.elementAt(i);
                pred.typeCheck(stable);
            }
        }
        return super._type;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this.hasPredicates()) {
            this.translatePredicates(classGen, methodGen);
        }
        else {
            int star = 0;
            String name = null;
            final XSLTC xsltc = this.getParser().getXSLTC();
            if (this._nodeType >= 14) {
                final Vector ni = xsltc.getNamesIndex();
                name = ni.elementAt(this._nodeType - 14);
                star = name.lastIndexOf(42);
            }
            if (this._axis == 2 && this._nodeType != 2 && this._nodeType != -1 && !this.hasParentPattern() && star == 0) {
                final int iter = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getTypedAxisIterator", "(II)Lorg/apache/xml/dtm/DTMAxisIterator;");
                il.append(methodGen.loadDOM());
                il.append(new PUSH(cpg, 2));
                il.append(new PUSH(cpg, this._nodeType));
                il.append(new INVOKEINTERFACE(iter, 3));
                return;
            }
            if (this.isAbbreviatedDot()) {
                if (super._type == Type.Node) {
                    il.append(methodGen.loadContextNode());
                }
                else {
                    final int init = cpg.addMethodref("org.apache.xalan.xsltc.dom.SingletonIterator", "<init>", "(I)V");
                    il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.SingletonIterator")));
                    il.append(InstructionConstants.DUP);
                    il.append(methodGen.loadContextNode());
                    il.append(new INVOKESPECIAL(init));
                }
                return;
            }
            final SyntaxTreeNode parent = this.getParent();
            if (parent instanceof ParentLocationPath && parent.getParent() instanceof ParentLocationPath && this._nodeType == 1 && !this._hadPredicates) {
                this._nodeType = -1;
            }
            switch (this._nodeType) {
                case 2: {
                    this._axis = 2;
                }
                case -1: {
                    final int git = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getAxisIterator", "(I)Lorg/apache/xml/dtm/DTMAxisIterator;");
                    il.append(methodGen.loadDOM());
                    il.append(new PUSH(cpg, this._axis));
                    il.append(new INVOKEINTERFACE(git, 2));
                    break;
                }
                default: {
                    if (star > 1) {
                        String namespace;
                        if (this._axis == 2) {
                            namespace = name.substring(0, star - 2);
                        }
                        else {
                            namespace = name.substring(0, star - 1);
                        }
                        final int nsType = xsltc.registerNamespace(namespace);
                        final int ns = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNamespaceAxisIterator", "(II)Lorg/apache/xml/dtm/DTMAxisIterator;");
                        il.append(methodGen.loadDOM());
                        il.append(new PUSH(cpg, this._axis));
                        il.append(new PUSH(cpg, nsType));
                        il.append(new INVOKEINTERFACE(ns, 3));
                        break;
                    }
                }
                case 1: {
                    final int ty = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getTypedAxisIterator", "(II)Lorg/apache/xml/dtm/DTMAxisIterator;");
                    il.append(methodGen.loadDOM());
                    il.append(new PUSH(cpg, this._axis));
                    il.append(new PUSH(cpg, this._nodeType));
                    il.append(new INVOKEINTERFACE(ty, 3));
                    break;
                }
            }
        }
    }
    
    public void translatePredicates(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        int idx = 0;
        if (this._predicates.size() == 0) {
            this.translate(classGen, methodGen);
        }
        else {
            final Predicate predicate = this._predicates.lastElement();
            this._predicates.remove(predicate);
            if (predicate.isNodeValueTest()) {
                final Step step = predicate.getStep();
                il.append(methodGen.loadDOM());
                if (step.isAbbreviatedDot()) {
                    this.translate(classGen, methodGen);
                    il.append(new ICONST(0));
                }
                else {
                    final ParentLocationPath path = new ParentLocationPath(this, step);
                    try {
                        path.typeCheck(this.getParser().getSymbolTable());
                    }
                    catch (TypeCheckError typeCheckError) {}
                    path.translate(classGen, methodGen);
                    il.append(new ICONST(1));
                }
                predicate.translate(classGen, methodGen);
                idx = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeValueIterator", "(Lorg/apache/xml/dtm/DTMAxisIterator;ILjava/lang/String;Z)Lorg/apache/xml/dtm/DTMAxisIterator;");
                il.append(new INVOKEINTERFACE(idx, 5));
            }
            else if (predicate.isNthDescendant()) {
                il.append(methodGen.loadDOM());
                il.append(new ICONST(predicate.getPosType()));
                predicate.translate(classGen, methodGen);
                il.append(new ICONST(0));
                idx = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNthDescendant", "(IIZ)Lorg/apache/xml/dtm/DTMAxisIterator;");
                il.append(new INVOKEINTERFACE(idx, 4));
            }
            else if (predicate.isNthPositionFilter()) {
                idx = cpg.addMethodref("org.apache.xalan.xsltc.dom.NthIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;I)V");
                il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.NthIterator")));
                il.append(InstructionConstants.DUP);
                this.translatePredicates(classGen, methodGen);
                predicate.translate(classGen, methodGen);
                il.append(new INVOKESPECIAL(idx));
            }
            else {
                idx = cpg.addMethodref("org.apache.xalan.xsltc.dom.CurrentNodeListIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xalan/xsltc/dom/CurrentNodeListFilter;ILorg/apache/xalan/xsltc/runtime/AbstractTranslet;)V");
                il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.CurrentNodeListIterator")));
                il.append(InstructionConstants.DUP);
                this.translatePredicates(classGen, methodGen);
                predicate.translateFilter(classGen, methodGen);
                il.append(methodGen.loadCurrentNode());
                il.append(classGen.loadTranslet());
                if (classGen.isExternal()) {
                    final String className = classGen.getClassName();
                    il.append(new CHECKCAST(cpg.addClass(className)));
                }
                il.append(new INVOKESPECIAL(idx));
            }
        }
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer("step(\"");
        buffer.append(Axis.names[this._axis]).append("\", ").append(this._nodeType);
        if (this._predicates != null) {
            for (int n = this._predicates.size(), i = 0; i < n; ++i) {
                final Predicate pred = this._predicates.elementAt(i);
                buffer.append(", ").append(pred.toString());
            }
        }
        return buffer.append(')').toString();
    }
}
