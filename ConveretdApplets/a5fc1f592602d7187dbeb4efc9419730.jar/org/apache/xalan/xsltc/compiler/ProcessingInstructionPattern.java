// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.BranchHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.IFEQ;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IF_ICMPEQ;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;
import java.util.Vector;

final class ProcessingInstructionPattern extends StepPattern
{
    private String _name;
    private boolean _typeChecked;
    
    public ProcessingInstructionPattern(final String name) {
        super(3, 7, null);
        this._name = null;
        this._typeChecked = false;
        this._name = name;
    }
    
    public double getDefaultPriority() {
        return (this._name != null) ? 0.0 : -0.5;
    }
    
    public String toString() {
        if (super._predicates == null) {
            return "processing-instruction(" + this._name + ")";
        }
        return "processing-instruction(" + this._name + ")" + super._predicates;
    }
    
    public void reduceKernelPattern() {
        this._typeChecked = true;
    }
    
    public boolean isWildcard() {
        return false;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        if (this.hasPredicates()) {
            for (int n = super._predicates.size(), i = 0; i < n; ++i) {
                final Predicate pred = super._predicates.elementAt(i);
                pred.typeCheck(stable);
            }
        }
        return Type.NodeSet;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int gname = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeName", "(I)Ljava/lang/String;");
        final int cmp = cpg.addMethodref("java.lang.String", "equals", "(Ljava/lang/Object;)Z");
        il.append(methodGen.loadCurrentNode());
        il.append(InstructionConstants.SWAP);
        il.append(methodGen.storeCurrentNode());
        if (!this._typeChecked) {
            il.append(methodGen.loadCurrentNode());
            final int getType = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getExpandedTypeID", "(I)I");
            il.append(methodGen.loadDOM());
            il.append(methodGen.loadCurrentNode());
            il.append(new INVOKEINTERFACE(getType, 2));
            il.append(new PUSH(cpg, 7));
            super._falseList.add(il.append(new IF_ICMPEQ(null)));
        }
        il.append(new PUSH(cpg, this._name));
        il.append(methodGen.loadDOM());
        il.append(methodGen.loadCurrentNode());
        il.append(new INVOKEINTERFACE(gname, 2));
        il.append(new INVOKEVIRTUAL(cmp));
        super._falseList.add(il.append(new IFEQ(null)));
        if (this.hasPredicates()) {
            for (int n = super._predicates.size(), i = 0; i < n; ++i) {
                final Predicate pred = super._predicates.elementAt(i);
                final Expression exp = pred.getExpr();
                exp.translateDesynthesized(classGen, methodGen);
                super._trueList.append(exp._trueList);
                super._falseList.append(exp._falseList);
            }
        }
        InstructionHandle restore = il.append(methodGen.storeCurrentNode());
        this.backPatchTrueList(restore);
        final BranchHandle skipFalse = il.append(new GOTO(null));
        restore = il.append(methodGen.storeCurrentNode());
        this.backPatchFalseList(restore);
        super._falseList.add(il.append(new GOTO(null)));
        skipFalse.setTarget(il.append(InstructionConstants.NOP));
    }
}
