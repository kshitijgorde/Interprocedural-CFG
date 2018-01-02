// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.MethodType;
import com.ibm.xslt4j.bcel.generic.IFEQ;
import com.ibm.xslt4j.bcel.generic.BranchHandle;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.GOTO_W;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.NodeSetType;
import org.apache.xalan.xsltc.compiler.util.BooleanType;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

abstract class Expression extends SyntaxTreeNode
{
    protected Type _type;
    protected FlowList _trueList;
    protected FlowList _falseList;
    
    Expression() {
        this._trueList = new FlowList();
        this._falseList = new FlowList();
    }
    
    public Type getType() {
        return this._type;
    }
    
    public abstract String toString();
    
    public boolean hasPositionCall() {
        return false;
    }
    
    public boolean hasLastCall() {
        return false;
    }
    
    public Object evaluateAtCompileTime() {
        return null;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return this.typeCheckContents(stable);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ErrorMsg msg = new ErrorMsg("NOT_IMPLEMENTED_ERR", this.getClass(), this);
        this.getParser().reportError(2, msg);
    }
    
    public final InstructionList compile(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList save = methodGen.getInstructionList();
        final InstructionList result;
        methodGen.setInstructionList(result = new InstructionList());
        this.translate(classGen, methodGen);
        methodGen.setInstructionList(save);
        return result;
    }
    
    public void translateDesynthesized(final ClassGenerator classGen, final MethodGenerator methodGen) {
        this.translate(classGen, methodGen);
        if (this._type instanceof BooleanType) {
            this.desynthesize(classGen, methodGen);
        }
    }
    
    public void startIterator(final ClassGenerator classGen, final MethodGenerator methodGen) {
        if (!(this._type instanceof NodeSetType)) {
            return;
        }
        Expression expr = this;
        if (expr instanceof CastExpr) {
            expr = ((CastExpr)expr).getExpr();
        }
        if (!(expr instanceof VariableRefBase)) {
            final InstructionList il = methodGen.getInstructionList();
            il.append(methodGen.loadContextNode());
            il.append(methodGen.setStartNode());
        }
    }
    
    public void synthesize(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        this._trueList.backPatch(il.append(InstructionConstants.ICONST_1));
        final BranchHandle truec = il.append(new GOTO_W(null));
        this._falseList.backPatch(il.append(InstructionConstants.ICONST_0));
        truec.setTarget(il.append(InstructionConstants.NOP));
    }
    
    public void desynthesize(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();
        this._falseList.add(il.append(new IFEQ(null)));
    }
    
    public FlowList getFalseList() {
        return this._falseList;
    }
    
    public FlowList getTrueList() {
        return this._trueList;
    }
    
    public void backPatchFalseList(final InstructionHandle ih) {
        this._falseList.backPatch(ih);
    }
    
    public void backPatchTrueList(final InstructionHandle ih) {
        this._trueList.backPatch(ih);
    }
    
    public MethodType lookupPrimop(final SymbolTable stable, final String op, final MethodType ctype) {
        MethodType result = null;
        final Vector primop = stable.lookupPrimop(op);
        if (primop != null) {
            final int n = primop.size();
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < n; ++i) {
                final MethodType ptype = primop.elementAt(i);
                if (ptype.argsCount() == ctype.argsCount()) {
                    if (result == null) {
                        result = ptype;
                    }
                    final int distance = ctype.distanceTo(ptype);
                    if (distance < minDistance) {
                        minDistance = distance;
                        result = ptype;
                    }
                }
            }
        }
        return result;
    }
}
