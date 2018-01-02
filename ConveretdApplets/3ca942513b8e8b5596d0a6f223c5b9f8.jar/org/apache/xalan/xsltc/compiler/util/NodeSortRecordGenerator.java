// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import com.ibm.xslt4j.bcel.generic.ALOAD;
import org.apache.xalan.xsltc.compiler.Stylesheet;
import com.ibm.xslt4j.bcel.generic.Instruction;

public final class NodeSortRecordGenerator extends ClassGenerator
{
    private static final int TRANSLET_INDEX = 4;
    private final Instruction _aloadTranslet;
    
    public NodeSortRecordGenerator(final String className, final String superClassName, final String fileName, final int accessFlags, final String[] interfaces, final Stylesheet stylesheet) {
        super(className, superClassName, fileName, accessFlags, interfaces, stylesheet);
        this._aloadTranslet = new ALOAD(4);
    }
    
    public Instruction loadTranslet() {
        return this._aloadTranslet;
    }
    
    public boolean isExternal() {
        return true;
    }
}
