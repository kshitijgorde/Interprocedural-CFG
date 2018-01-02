// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.ALOAD;
import org.apache.xalan.xsltc.compiler.Stylesheet;
import org.apache.bcel.generic.Instruction;

public final class FilterGenerator extends ClassGenerator
{
    private static int TRANSLET_INDEX;
    private final Instruction _aloadTranslet;
    
    public FilterGenerator(final String className, final String superClassName, final String fileName, final int accessFlags, final String[] interfaces, final Stylesheet stylesheet) {
        super(className, superClassName, fileName, accessFlags, interfaces, stylesheet);
        this._aloadTranslet = new ALOAD(FilterGenerator.TRANSLET_INDEX);
    }
    
    public final Instruction loadTranslet() {
        return this._aloadTranslet;
    }
    
    public boolean isExternal() {
        return true;
    }
    
    static {
        FilterGenerator.TRANSLET_INDEX = 5;
    }
}
