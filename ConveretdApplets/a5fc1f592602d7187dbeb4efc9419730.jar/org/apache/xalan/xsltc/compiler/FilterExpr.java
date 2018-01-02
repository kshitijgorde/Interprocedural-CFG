// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKESPECIAL;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.NEW;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.ReferenceType;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

class FilterExpr extends Expression
{
    private Expression _primary;
    private final Vector _predicates;
    
    public FilterExpr(final Expression primary, final Vector predicates) {
        this._primary = primary;
        this._predicates = predicates;
        primary.setParent(this);
    }
    
    protected Expression getExpr() {
        if (this._primary instanceof CastExpr) {
            return ((CastExpr)this._primary).getExpr();
        }
        return this._primary;
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._primary.setParser(parser);
        if (this._predicates != null) {
            for (int n = this._predicates.size(), i = 0; i < n; ++i) {
                final Expression exp = this._predicates.elementAt(i);
                exp.setParser(parser);
                exp.setParent(this);
            }
        }
    }
    
    public String toString() {
        return "filter-expr(" + this._primary + ", " + this._predicates + ")";
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type ptype = this._primary.typeCheck(stable);
        if (!(ptype instanceof NodeSetType)) {
            if (!(ptype instanceof ReferenceType)) {
                throw new TypeCheckError(this);
            }
            this._primary = new CastExpr(this._primary, Type.NodeSet);
        }
        for (int n = this._predicates.size(), i = 0; i < n; ++i) {
            final Predicate pred = this._predicates.elementAt(i);
            pred.dontOptimize();
            pred.typeCheck(stable);
        }
        return super._type = Type.NodeSet;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        if (this._predicates.size() > 0) {
            this.translatePredicates(classGen, methodGen);
        }
        else {
            this._primary.translate(classGen, methodGen);
        }
    }
    
    public void translatePredicates(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._predicates.size() == 0) {
            this.translate(classGen, methodGen);
        }
        else {
            final int initCNLI = cpg.addMethodref("org.apache.xalan.xsltc.dom.CurrentNodeListIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;ZLorg/apache/xalan/xsltc/dom/CurrentNodeListFilter;ILorg/apache/xalan/xsltc/runtime/AbstractTranslet;)V");
            final Predicate predicate = this._predicates.lastElement();
            this._predicates.remove(predicate);
            il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.CurrentNodeListIterator")));
            il.append(InstructionConstants.DUP);
            this.translatePredicates(classGen, methodGen);
            il.append(InstructionConstants.ICONST_1);
            predicate.translate(classGen, methodGen);
            il.append(methodGen.loadCurrentNode());
            il.append(classGen.loadTranslet());
            il.append(new INVOKESPECIAL(initCNLI));
        }
    }
}
