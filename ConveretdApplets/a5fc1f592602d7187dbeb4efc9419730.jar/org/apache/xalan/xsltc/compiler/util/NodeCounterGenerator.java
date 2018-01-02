// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.compiler.util;

import org.apache.bcel.generic.ALOAD;
import org.apache.xalan.xsltc.compiler.Stylesheet;
import org.apache.bcel.generic.Instruction;

public final class NodeCounterGenerator extends ClassGenerator
{
    private Instruction _aloadTranslet;
    
    public NodeCounterGenerator(final String className, final String superClassName, final String fileName, final int accessFlags, final String[] interfaces, final Stylesheet stylesheet) {
        super(className, superClassName, fileName, accessFlags, interfaces, stylesheet);
    }
    
    public void setTransletIndex(final int index) {
        this._aloadTranslet = new ALOAD(index);
    }
    
    public Instruction loadTranslet() {
        return this._aloadTranslet;
    }
    
    public boolean isExternal() {
        return true;
    }
}
