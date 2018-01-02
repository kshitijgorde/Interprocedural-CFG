// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.LocalVariableGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.IFLT;
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
import org.apache.bcel.generic.InstructionHandle;

final class AncestorPattern extends RelativePathPattern
{
    private final Pattern _left;
    private final RelativePathPattern _right;
    private InstructionHandle _loop;
    
    public AncestorPattern(final RelativePathPattern right) {
        this(null, right);
    }
    
    public AncestorPattern(final Pattern left, final RelativePathPattern right) {
        this._left = left;
        (this._right = right).setParent(this);
        if (left != null) {
            left.setParent(this);
        }
    }
    
    public InstructionHandle getLoopHandle() {
        return this._loop;
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        if (this._left != null) {
            this._left.setParser(parser);
        }
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
        if (this._left != null) {
            this._left.typeCheck(stable);
        }
        return this._right.typeCheck(stable);
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final LocalVariableGen local = methodGen.addLocalVariable2("app", Util.getJCRefType("I"), il.getEnd());
        final Instruction loadLocal = new ILOAD(local.getIndex());
        final Instruction storeLocal = new ISTORE(local.getIndex());
        if (this._right instanceof StepPattern) {
            il.append(InstructionConstants.DUP);
            il.append(storeLocal);
            this._right.translate(classGen, methodGen);
            il.append(methodGen.loadDOM());
            il.append(loadLocal);
        }
        else {
            this._right.translate(classGen, methodGen);
            if (this._right instanceof AncestorPattern) {
                il.append(methodGen.loadDOM());
                il.append(InstructionConstants.SWAP);
            }
        }
        if (this._left != null) {
            final int getParent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
            final InstructionHandle parent = il.append(new INVOKEINTERFACE(getParent, 2));
            il.append(InstructionConstants.DUP);
            il.append(storeLocal);
            super._falseList.add(il.append(new IFLT(null)));
            il.append(loadLocal);
            this._left.translate(classGen, methodGen);
            final SyntaxTreeNode p = this.getParent();
            if (p != null && !(p instanceof org.apache.xalan.xsltc.compiler.Instruction)) {
                if (!(p instanceof TopLevelElement)) {
                    il.append(loadLocal);
                }
            }
            final BranchHandle exit = il.append(new GOTO(null));
            this._loop = il.append(methodGen.loadDOM());
            il.append(loadLocal);
            local.setEnd(this._loop);
            il.append(new GOTO(parent));
            exit.setTarget(il.append(InstructionConstants.NOP));
            this._left.backPatchFalseList(this._loop);
            super._trueList.append(this._left._trueList);
        }
        else {
            il.append(InstructionConstants.POP2);
        }
        if (this._right instanceof AncestorPattern) {
            final AncestorPattern ancestor = (AncestorPattern)this._right;
            super._falseList.backPatch(ancestor.getLoopHandle());
        }
        super._trueList.append(this._right._trueList);
        super._falseList.append(this._right._falseList);
    }
    
    public String toString() {
        return "AncestorPattern(" + this._left + ", " + this._right + ')';
    }
}
