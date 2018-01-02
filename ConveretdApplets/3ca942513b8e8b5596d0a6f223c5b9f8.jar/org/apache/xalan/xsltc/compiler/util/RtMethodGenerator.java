// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import com.ibm.xslt4j.bcel.generic.ALOAD;
import com.ibm.xslt4j.bcel.generic.ASTORE;
import com.ibm.xslt4j.bcel.generic.ConstantPoolGen;
import com.ibm.xslt4j.bcel.generic.InstructionList;
import com.ibm.xslt4j.bcel.generic.Type;
import com.ibm.xslt4j.bcel.generic.Instruction;

public final class RtMethodGenerator extends MethodGenerator
{
    private static final int HANDLER_INDEX = 2;
    private final Instruction _astoreHandler;
    private final Instruction _aloadHandler;
    
    public RtMethodGenerator(final int access_flags, final Type return_type, final Type[] arg_types, final String[] arg_names, final String method_name, final String class_name, final InstructionList il, final ConstantPoolGen cp) {
        super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
        this._astoreHandler = new ASTORE(2);
        this._aloadHandler = new ALOAD(2);
    }
    
    public int getIteratorIndex() {
        return -1;
    }
    
    public final Instruction storeHandler() {
        return this._astoreHandler;
    }
    
    public final Instruction loadHandler() {
        return this._aloadHandler;
    }
    
    public int getLocalIndex(final String name) {
        return -1;
    }
}
