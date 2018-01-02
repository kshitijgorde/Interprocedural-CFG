// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.ASTORE;
import org.apache.bcel.generic.ALOAD;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.Type;

public final class NamedMethodGenerator extends MethodGenerator
{
    protected static int CURRENT_INDEX;
    private static final int PARAM_START_INDEX = 5;
    
    public NamedMethodGenerator(final int access_flags, final Type return_type, final Type[] arg_types, final String[] arg_names, final String method_name, final String class_name, final InstructionList il, final ConstantPoolGen cp) {
        super(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cp);
    }
    
    public int getLocalIndex(final String name) {
        if (name.equals("current")) {
            return NamedMethodGenerator.CURRENT_INDEX;
        }
        return super.getLocalIndex(name);
    }
    
    public Instruction loadParameter(final int index) {
        return new ALOAD(index + 5);
    }
    
    public Instruction storeParameter(final int index) {
        return new ASTORE(index + 5);
    }
    
    static {
        NamedMethodGenerator.CURRENT_INDEX = 4;
    }
}
