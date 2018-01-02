// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
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
            this.translatePredicates(classGen, methodGen);
            final LocalVariableGen nodeIteratorTemp = methodGen.addLocalVariable("filter_expr_tmp1", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), il.getEnd(), null);
            il.append(new ASTORE(nodeIteratorTemp.getIndex()));
            predicate.translate(classGen, methodGen);
            final LocalVariableGen filterTemp = methodGen.addLocalVariable("filter_expr_tmp2", Util.getJCRefType("Lorg/apache/xalan/xsltc/dom/CurrentNodeListFilter;"), il.getEnd(), null);
            il.append(new ASTORE(filterTemp.getIndex()));
            il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.CurrentNodeListIterator")));
            il.append(InstructionConstants.DUP);
            il.append(new ALOAD(nodeIteratorTemp.getIndex()));
            il.append(InstructionConstants.ICONST_1);
            il.append(new ALOAD(filterTemp.getIndex()));
            il.append(methodGen.loadCurrentNode());
            il.append(classGen.loadTranslet());
            il.append(new INVOKESPECIAL(initCNLI));
        }
    }
}
