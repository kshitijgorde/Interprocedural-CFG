// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.ISTORE;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.Type;
import com.ibm.xslt4j.bcel.generic.Instruction;

public final class TestGenerator extends MethodGenerator
{
    private static int CONTEXT_NODE_INDEX;
    private static int CURRENT_NODE_INDEX;
    private static int ITERATOR_INDEX;
    private Instruction _aloadDom;
    private final Instruction _iloadCurrent;
    private final Instruction _iloadContext;
    private final Instruction _istoreCurrent;
    private final Instruction _istoreContext;
    private final Instruction _astoreIterator;
    private final Instruction _aloadIterator;
    
    public TestGenerator(final int access_flags, final Type return_type, final Type[] arg_types, final String[] arg_names, final String method_name, final String class_name, final InstructionList il, final ConstantPoolGen cp) {
        super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
        this._iloadCurrent = new ILOAD(TestGenerator.CURRENT_NODE_INDEX);
        this._istoreCurrent = new ISTORE(TestGenerator.CURRENT_NODE_INDEX);
        this._iloadContext = new ILOAD(TestGenerator.CONTEXT_NODE_INDEX);
        this._istoreContext = new ILOAD(TestGenerator.CONTEXT_NODE_INDEX);
        this._astoreIterator = new ASTORE(TestGenerator.ITERATOR_INDEX);
        this._aloadIterator = new ALOAD(TestGenerator.ITERATOR_INDEX);
    }
    
    public int getHandlerIndex() {
        return -1;
    }
    
    public int getIteratorIndex() {
        return TestGenerator.ITERATOR_INDEX;
    }
    
    public void setDomIndex(final int domIndex) {
        this._aloadDom = new ALOAD(domIndex);
    }
    
    public Instruction loadDOM() {
        return this._aloadDom;
    }
    
    public Instruction loadCurrentNode() {
        return this._iloadCurrent;
    }
    
    public Instruction loadContextNode() {
        return this._iloadContext;
    }
    
    public Instruction storeContextNode() {
        return this._istoreContext;
    }
    
    public Instruction storeCurrentNode() {
        return this._istoreCurrent;
    }
    
    public Instruction storeIterator() {
        return this._astoreIterator;
    }
    
    public Instruction loadIterator() {
        return this._aloadIterator;
    }
    
    public int getLocalIndex(final String name) {
        if (name.equals("current")) {
            return TestGenerator.CURRENT_NODE_INDEX;
        }
        return super.getLocalIndex(name);
    }
    
    static {
        TestGenerator.CONTEXT_NODE_INDEX = 1;
        TestGenerator.CURRENT_NODE_INDEX = 4;
        TestGenerator.ITERATOR_INDEX = 6;
    }
}
