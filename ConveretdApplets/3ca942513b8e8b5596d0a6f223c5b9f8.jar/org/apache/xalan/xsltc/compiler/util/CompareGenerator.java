// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import com.ibm.xslt4j.bcel.generic.LocalVariableGen;
import com.ibm.xslt4j.bcel.generic.ACONST_NULL;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.InstructionHandle;
import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.ISTORE;
import com.ibm.xslt4j.bcel.generic.ILOAD;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.Type;
import com.ibm.xslt4j.bcel.generic.Instruction;

public final class CompareGenerator extends MethodGenerator
{
    private static int DOM_INDEX;
    private static int CURRENT_INDEX;
    private static int LEVEL_INDEX;
    private static int TRANSLET_INDEX;
    private static int LAST_INDEX;
    private int ITERATOR_INDEX;
    private final Instruction _iloadCurrent;
    private final Instruction _istoreCurrent;
    private final Instruction _aloadDom;
    private final Instruction _iloadLast;
    private final Instruction _aloadIterator;
    private final Instruction _astoreIterator;
    
    public CompareGenerator(final int access_flags, final Type return_type, final Type[] arg_types, final String[] arg_names, final String method_name, final String class_name, final InstructionList il, final ConstantPoolGen cp) {
        super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
        this.ITERATOR_INDEX = 6;
        this._iloadCurrent = new ILOAD(CompareGenerator.CURRENT_INDEX);
        this._istoreCurrent = new ISTORE(CompareGenerator.CURRENT_INDEX);
        this._aloadDom = new ALOAD(CompareGenerator.DOM_INDEX);
        this._iloadLast = new ILOAD(CompareGenerator.LAST_INDEX);
        final LocalVariableGen iterator = this.addLocalVariable("iterator", Util.getJCRefType("Lorg/apache/xml/dtm/DTMAxisIterator;"), null, null);
        this.ITERATOR_INDEX = iterator.getIndex();
        this._aloadIterator = new ALOAD(this.ITERATOR_INDEX);
        this._astoreIterator = new ASTORE(this.ITERATOR_INDEX);
        il.append(new ACONST_NULL());
        il.append(this.storeIterator());
    }
    
    public Instruction loadLastNode() {
        return this._iloadLast;
    }
    
    public Instruction loadCurrentNode() {
        return this._iloadCurrent;
    }
    
    public Instruction storeCurrentNode() {
        return this._istoreCurrent;
    }
    
    public Instruction loadDOM() {
        return this._aloadDom;
    }
    
    public int getHandlerIndex() {
        return -1;
    }
    
    public int getIteratorIndex() {
        return -1;
    }
    
    public Instruction storeIterator() {
        return this._astoreIterator;
    }
    
    public Instruction loadIterator() {
        return this._aloadIterator;
    }
    
    public int getLocalIndex(final String name) {
        if (name.equals("current")) {
            return CompareGenerator.CURRENT_INDEX;
        }
        return super.getLocalIndex(name);
    }
    
    static {
        CompareGenerator.DOM_INDEX = 1;
        CompareGenerator.CURRENT_INDEX = 2;
        CompareGenerator.LEVEL_INDEX = 3;
        CompareGenerator.TRANSLET_INDEX = 4;
        CompareGenerator.LAST_INDEX = 5;
    }
}
