// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.BranchHandle;
import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.GOTO_W;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.IF_ICMPEQ;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.ISTORE;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.Util;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

final class AbsolutePathPattern extends LocationPathPattern
{
    private final RelativePathPattern _left;
    
    public AbsolutePathPattern(final RelativePathPattern left) {
        this._left = left;
        if (left != null) {
            left.setParent(this);
        }
    }
    
    public void setParser(final Parser parser) {
        super.setParser(parser);
        if (this._left != null) {
            this._left.setParser(parser);
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return (this._left == null) ? Type.Root : this._left.typeCheck(stable);
    }
    
    public boolean isWildcard() {
        return false;
    }
    
    public StepPattern getKernelPattern() {
        return (this._left != null) ? this._left.getKernelPattern() : null;
    }
    
    public void reduceKernelPattern() {
        this._left.reduceKernelPattern();
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (this._left != null) {
            if (this._left instanceof StepPattern) {
                final LocalVariableGen local = methodGen.addLocalVariable2("apptmp", Util.getJCRefType("I"), il.getEnd());
                il.append(InstructionConstants.DUP);
                il.append(new ISTORE(local.getIndex()));
                this._left.translate(classGen, methodGen);
                il.append(methodGen.loadDOM());
                local.setEnd(il.append(new ILOAD(local.getIndex())));
                methodGen.removeLocalVariable(local);
            }
            else {
                this._left.translate(classGen, methodGen);
            }
        }
        final int getParent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getParent", "(I)I");
        final int getType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
        final InstructionHandle begin = il.append(methodGen.loadDOM());
        il.append(InstructionConstants.SWAP);
        il.append(new INVOKEINTERFACE(getParent, 2));
        if (this._left instanceof AncestorPattern) {
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
        }
        il.append(new INVOKEINTERFACE(getType, 2));
        il.append(new PUSH(cpg, 9));
        final BranchHandle skip = il.append(new IF_ICMPEQ(null));
        super._falseList.add(il.append(new GOTO_W(null)));
        skip.setTarget(il.append(InstructionConstants.NOP));
        if (this._left != null) {
            this._left.backPatchTrueList(begin);
            if (this._left instanceof AncestorPattern) {
                final AncestorPattern ancestor = (AncestorPattern)this._left;
                super._falseList.backPatch(ancestor.getLoopHandle());
            }
            super._falseList.append(this._left._falseList);
        }
    }
    
    public String toString() {
        return "absolutePathPattern(" + ((this._left != null) ? this._left.toString() : ")");
    }
}
