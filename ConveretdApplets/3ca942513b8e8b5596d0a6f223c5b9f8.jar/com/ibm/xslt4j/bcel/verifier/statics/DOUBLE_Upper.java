// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.statics;

import com.ibm.xslt4j.bcel.generic.Type;

public final class DOUBLE_Upper extends Type
{
    private static DOUBLE_Upper singleInstance;
    
    static {
        DOUBLE_Upper.singleInstance = new DOUBLE_Upper();
    }
    
    private DOUBLE_Upper() {
        super((byte)15, "Long_Upper");
    }
    
    public static DOUBLE_Upper theInstance() {
        return DOUBLE_Upper.singleInstance;
    }
}
