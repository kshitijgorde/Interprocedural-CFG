// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler;

import com.ibm.xslt4j.bcel.generic.BranchHandle;
import com.ibm.xslt4j.bcel.generic.IFGT;
import com.ibm.xslt4j.bcel.generic.INVOKEINTERFACE;
import com.ibm.xslt4j.bcel.generic.BranchInstruction;
import com.ibm.xslt4j.bcel.generic.GOTO;
import com.ibm.xslt4j.bcel.generic.CompoundInstruction;
import com.ibm.xslt4j.bcel.generic.PUSH;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.INVOKEVIRTUAL;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import org.apache.xalan.xsltc.compiler.util.Util;
import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.INVOKESPECIAL;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.InstructionConstants;
import com.ibm.xslt4j.bcel.generic.Instruction;
import com.ibm.xslt4j.bcel.generic.NEW;
import org.apache.xalan.xsltc.compiler.util.MethodGenerator;
import org.apache.xalan.xsltc.compiler.util.ClassGenerator;
import org.apache.xalan.xsltc.compiler.util.TypeCheckError;
import org.apache.xalan.xsltc.compiler.util.StringType;
import java.util.Vector;
import org.apache.xalan.xsltc.compiler.util.Type;

final class KeyCall extends FunctionCall
{
    private Expression _name;
    private Expression _value;
    private Type _valueType;
    private QName _resolvedQName;
    
    public KeyCall(final QName fname, final Vector arguments) {
        super(fname, arguments);
        this._resolvedQName = null;
        switch (this.argumentCount()) {
            case 1: {
                this._name = null;
                this._value = this.argument(0);
                break;
            }
            case 2: {
                this._name = this.argument(0);
                this._value = this.argument(1);
                break;
            }
            default: {
                final Expression expression = null;
                this._value = expression;
                this._name = expression;
                break;
            }
        }
    }
    
    public Type typeCheck(final SymbolTable stable) throws TypeCheckError {
        final Type returnType = super.typeCheck(stable);
        if (this._name != null) {
            final Type nameType = this._name.typeCheck(stable);
            if (this._name instanceof LiteralExpr) {
                final LiteralExpr literal = (LiteralExpr)this._name;
                this._resolvedQName = this.getParser().getQNameIgnoreDefaultNs(literal.getValue());
            }
            else if (!(nameType instanceof StringType)) {
                this._name = new CastExpr(this._name, Type.String);
            }
        }
        this._valueType = this._value.typeCheck(stable);
        if (this._valueType != Type.NodeSet && this._valueType != Type.String) {
            this._value = new CastExpr(this._value, Type.String);
        }
        return returnType;
    }
    
    public void translate(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int getNodeHandle = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getNodeHandle", "(I)I");
        final int dupInit = cpg.addMethodref("org.apache.xalan.xsltc.dom.DupFilterIterator", "<init>", "(Lorg/apache/xml/dtm/DTMAxisIterator;)V");
        final LocalVariableGen keyIterator = this.translateCall(classGen, methodGen);
        il.append(new NEW(cpg.addClass("org.apache.xalan.xsltc.dom.DupFilterIterator")));
        il.append(InstructionConstants.DUP);
        il.append(new ALOAD(keyIterator.getIndex()));
        il.append(new INVOKESPECIAL(dupInit));
    }
    
    private LocalVariableGen translateCall(final ClassGenerator classGen, final MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int getNodeValue = cpg.addInterfaceMethodref("org.apache.xalan.xsltc.DOM", "getStringValueX", "(I)Ljava/lang/String;");
        final int getKeyIndex = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "getKeyIndex", "(Ljava/lang/String;)Lorg/apache/xalan/xsltc/dom/KeyIndex;");
        final int lookupId = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "lookupId", "(Ljava/lang/Object;)V");
        final int lookupKey = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "lookupKey", "(Ljava/lang/Object;)V");
        final int merge = cpg.addMethodref("org/apache/xalan/xsltc/dom/KeyIndex", "merge", "(Lorg/apache/xalan/xsltc/dom/KeyIndex;)V");
        final int indexConstructor = cpg.addMethodref("org.apache.xalan.xsltc.runtime.AbstractTranslet", "createKeyIndex", "()Lorg/apache/xalan/xsltc/dom/KeyIndex;");
        final int keyDom = cpg.addMethodref("org.apache.xalan.xsltc.dom.KeyIndex", "setDom", "(Lorg/apache/xalan/xsltc/DOM;)V");
        final LocalVariableGen returnIndex = methodGen.addLocalVariable("returnIndex", Util.getJCRefType("Lorg/apache/xalan/xsltc/dom/KeyIndex;"), il.getEnd(), null);
        final LocalVariableGen searchIndex = methodGen.addLocalVariable("searchIndex", Util.getJCRefType("Lorg/apache/xalan/xsltc/dom/KeyIndex;"), il.getEnd(), null);
        if (this._valueType == Type.NodeSet) {
            il.append(methodGen.loadCurrentNode());
            il.append(methodGen.loadIterator());
            this._value.translate(classGen, methodGen);
            this._value.startIterator(classGen, methodGen);
            il.append(methodGen.storeIterator());
            il.append(classGen.loadTranslet());
            il.append(new INVOKEVIRTUAL(indexConstructor));
            il.append(InstructionConstants.DUP);
            il.append(methodGen.loadDOM());
            il.append(new INVOKEVIRTUAL(keyDom));
            il.append(new ASTORE(returnIndex.getIndex()));
            il.append(classGen.loadTranslet());
            if (this._name == null) {
                il.append(new PUSH(cpg, "##id"));
            }
            else if (this._resolvedQName != null) {
                il.append(new PUSH(cpg, this._resolvedQName.toString()));
            }
            else {
                this._name.translate(classGen, methodGen);
            }
            il.append(new INVOKEVIRTUAL(getKeyIndex));
            il.append(new ASTORE(searchIndex.getIndex()));
            final BranchHandle nextNode = il.append(new GOTO(null));
            final InstructionHandle loop = il.append(InstructionConstants.NOP);
            il.append(new ALOAD(returnIndex.getIndex()));
            il.append(new ALOAD(searchIndex.getIndex()));
            il.append(InstructionConstants.DUP);
            il.append(methodGen.loadDOM());
            il.append(methodGen.loadCurrentNode());
            il.append(new INVOKEINTERFACE(getNodeValue, 2));
            if (this._name == null) {
                il.append(new INVOKEVIRTUAL(lookupId));
            }
            else {
                il.append(new INVOKEVIRTUAL(lookupKey));
            }
            il.append(new INVOKEVIRTUAL(merge));
            nextNode.setTarget(il.append(methodGen.loadIterator()));
            il.append(methodGen.nextNode());
            il.append(InstructionConstants.DUP);
            il.append(methodGen.storeCurrentNode());
            il.append(new IFGT(loop));
            il.append(methodGen.storeIterator());
            il.append(methodGen.storeCurrentNode());
        }
        else {
            il.append(classGen.loadTranslet());
            if (this._name == null) {
                il.append(new PUSH(cpg, "##id"));
            }
            else if (this._resolvedQName != null) {
                il.append(new PUSH(cpg, this._resolvedQName.toString()));
            }
            else {
                this._name.translate(classGen, methodGen);
            }
            il.append(new INVOKEVIRTUAL(getKeyIndex));
            il.append(InstructionConstants.DUP);
            this._value.translate(classGen, methodGen);
            if (this._name == null) {
                il.append(new INVOKEVIRTUAL(lookupId));
            }
            else {
                il.append(new INVOKEVIRTUAL(lookupKey));
            }
            il.append(new ASTORE(returnIndex.getIndex()));
        }
        return returnIndex;
    }
}
