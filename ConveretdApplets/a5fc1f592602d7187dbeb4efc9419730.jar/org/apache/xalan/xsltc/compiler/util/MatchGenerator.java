// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.ISTORE;
import org.apache.bcel.generic.ILOAD;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.Type;
import org.apache.bcel.generic.Instruction;

public final class MatchGenerator extends MethodGenerator
{
    private static int CURRENT_INDEX;
    private int _iteratorIndex;
    private final Instruction _iloadCurrent;
    private final Instruction _istoreCurrent;
    private Instruction _aloadDom;
    
    public MatchGenerator(final int access_flags, final Type return_type, final Type[] arg_types, final String[] arg_names, final String method_name, final String class_name, final InstructionList il, final ConstantPoolGen cp) {
        super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
        this._iteratorIndex = -1;
        this._iloadCurrent = new ILOAD(MatchGenerator.CURRENT_INDEX);
        this._istoreCurrent = new ISTORE(MatchGenerator.CURRENT_INDEX);
    }
    
    public Instruction loadCurrentNode() {
        return this._iloadCurrent;
    }
    
    public Instruction storeCurrentNode() {
        return this._istoreCurrent;
    }
    
    public int getHandlerIndex() {
        return -1;
    }
    
    public Instruction loadDOM() {
        return this._aloadDom;
    }
    
    public void setDomIndex(final int domIndex) {
        this._aloadDom = new ALOAD(domIndex);
    }
    
    public int getIteratorIndex() {
        return this._iteratorIndex;
    }
    
    public void setIteratorIndex(final int iteratorIndex) {
        this._iteratorIndex = iteratorIndex;
    }
    
    public int getLocalIndex(final String name) {
        if (name.equals("current")) {
            return MatchGenerator.CURRENT_INDEX;
        }
        return super.getLocalIndex(name);
    }
    
    static {
        MatchGenerator.CURRENT_INDEX = 1;
    }
}
