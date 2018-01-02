// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.GOTO;
import org.apache.bcel.generic.BranchInstruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.IFNE;
import org.apache.bcel.generic.INVOKEINTERFACE;
import org.apache.bcel.generic.InstructionConstants;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.INVOKEVIRTUAL;
import org.apache.bcel.generic.CompoundInstruction;
import org.apache.bcel.generic.PUSH;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.Type;

abstract class IdKeyPattern extends LocationPathPattern
{
    protected RelativePathPattern _left;
    private String _index;
    private String _value;
    
    public IdKeyPattern(final String index, final String value) {
        this._left = null;
        this._index = null;
        this._value = null;
        this._index = index;
        this._value = value;
    }
    
    public String getIndexName() {
        return this._index;
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        return Type.NodeSet;
    }
    
    public boolean isWildcard() {
        return false;
    }
    
    public void setLeft(final RelativePathPattern left) {
        this._left = left;
    }
    
    public StepPattern getKernelPattern() {
        return null;
    }
    
    public void reduceKernelPattern() {
    }
    
    public String toString() {
        return "id/keyPattern(" + this._index + ", " + this._value + ')';
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int getKeyIndex = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "getKeyIndex", "(Ljava/lang/String;)Lorg/apache/xalan/xsltc/dom/KeyIndex;");
        final int lookupId = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "containsID", "(ILjava/lang/Object;)I");
        final int lookupKey = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "containsKey", "(ILjava/lang/Object;)I");
        final int getNodeIdent = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeIdent", "(I)I");
        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, this._index));
        il.append(new INVOKEVIRTUAL(getKeyIndex));
        il.append(InstructionConstants.SWAP);
        il.append(new PUSH(cpg, this._value));
        if (this instanceof IdPattern) {
            il.append(InstructionConstants.SWAP);
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEINTERFACE(getNodeIdent, 2));
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEVIRTUAL(lookupId));
        }
        else {
            il.append(InstructionConstants.SWAP);
            il.append(methodGen.loadDOM());
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEINTERFACE(getNodeIdent, 2));
            il.append(InstructionConstants.SWAP);
            il.append(new INVOKEVIRTUAL(lookupKey));
        }
        super._trueList.add(il.append(new IFNE(null)));
        super._falseList.add(il.append(new GOTO(null)));
    }
}
