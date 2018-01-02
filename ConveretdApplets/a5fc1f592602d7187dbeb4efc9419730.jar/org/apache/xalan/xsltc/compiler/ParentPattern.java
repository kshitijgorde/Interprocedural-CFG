// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.ILOAD;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class ParentPattern extends RelativePathPattern
{
    private final Pattern _left;
    private final RelativePathPattern _right;
    
    public ParentPattern(final Pattern left, final RelativePathPattern right) {
        (this._left = left).setParent(this);
        (this._right = right).setParent(this);
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        this._left.setParser(parser);
        this._right.setParser(parser);
    }
    
    public boolean isWildcard() {
        return false;
    }
    
    public StepPattern getKernelPattern() {
        return this._right.getKernelPattern();
    }
    
    public void reduceKernelPattern() {
        this._right.reduceKernelPattern();
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        this._left.typeCheck(stable);
        return this._right.typeCheck(stable);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final LocalVariableGen local = methodGen.addLocalVariable2("ppt", Util.getJCRefType("I"), il.getEnd());
        final Instruction loadLocal = new ILOAD(local.getIndex());
        final Instruction storeLocal = new ISTORE(local.getIndex());
        if (this._right.isWildcard()) {
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
        }
        else if (this._right instanceof StepPattern) {
            il.append(InstructionConstants.DUP);
            il.append(storeLocal);
            this._right.translate(classGen, methodGen);
            il.append(methodGen.loadDOM());
            local.setEnd(il.append(loadLocal));
        }
        else {
            this._right.translate(classGen, methodGen);
            if (this._right instanceof AncestorPattern) {
                il.append(methodGen.loadDOM());
                il.append(InstructionConstants.SWAP);
            }
        }
        final int getParent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
        il.append(new INVOKEINTERFACE(getParent, 2));
        final SyntaxTreeNode p = this.getParent();
        if (p == null || p instanceof org.apache.xalan.xsltc.compiler.Instruction || p instanceof TopLevelElement) {
            this._left.translate(classGen, methodGen);
        }
        else {
            il.append(InstructionConstants.DUP);
            il.append(storeLocal);
            this._left.translate(classGen, methodGen);
            il.append(methodGen.loadDOM());
            local.setEnd(il.append(loadLocal));
        }
        methodGen.removeLocalVariable(local);
        if (this._right instanceof AncestorPattern) {
            final AncestorPattern ancestor = (AncestorPattern)this._right;
            this._left.backPatchFalseList(ancestor.getLoopHandle());
        }
        super._trueList.append(this._right._trueList.append(this._left._trueList));
        super._falseList.append(this._right._falseList.append(this._left._falseList));
    }
    
    public String toString() {
        return "Parent(" + this._left + ", " + this._right + ')';
    }
}
