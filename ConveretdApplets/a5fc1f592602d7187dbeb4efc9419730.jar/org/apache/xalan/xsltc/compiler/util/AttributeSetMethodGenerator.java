// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.ClassGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.Type;

public final class AttributeSetMethodGenerator extends MethodGenerator
{
    private static final int DOM_INDEX = 1;
    private static final int ITERATOR_INDEX = 2;
    private static final int HANDLER_INDEX = 3;
    private static final Type[] argTypes;
    private static final String[] argNames;
    private final Instruction _aloadDom;
    private final Instruction _astoreDom;
    private final Instruction _astoreIterator;
    private final Instruction _aloadIterator;
    private final Instruction _astoreHandler;
    private final Instruction _aloadHandler;
    
    public AttributeSetMethodGenerator(final String methodName, final ClassGen classGen) {
        super(2, Type.VOID, AttributeSetMethodGenerator.argTypes, AttributeSetMethodGenerator.argNames, methodName, classGen.getClassName(), new InstructionList(), classGen.getConstantPool());
        this._aloadDom = new ALOAD(1);
        this._astoreDom = new ASTORE(1);
        this._astoreIterator = new ASTORE(2);
        this._aloadIterator = new ALOAD(2);
        this._astoreHandler = new ASTORE(3);
        this._aloadHandler = new ALOAD(3);
    }
    
    public Instruction storeIterator() {
        return this._astoreIterator;
    }
    
    public Instruction loadIterator() {
        return this._aloadIterator;
    }
    
    public int getIteratorIndex() {
        return 2;
    }
    
    public Instruction storeHandler() {
        return this._astoreHandler;
    }
    
    public Instruction loadHandler() {
        return this._aloadHandler;
    }
    
    public int getLocalIndex(final String name) {
        return -1;
    }
    
    static {
        argTypes = new Type[3];
        argNames = new String[3];
        AttributeSetMethodGenerator.argTypes[0] = Util.getJCRefType("Lorg/apache/xalan/xsltc/DOM;");
        AttributeSetMethodGenerator.argNames[0] = "dom";
        AttributeSetMethodGenerator.argTypes[1] = Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;");
        AttributeSetMethodGenerator.argNames[1] = "iterator";
        AttributeSetMethodGenerator.argTypes[2] = Util.getJCRefType("Lorg/apache/xml/serializer/SerializationHandler;");
        AttributeSetMethodGenerator.argNames[2] = "handler";
    }
}
