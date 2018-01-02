// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.verifier.statics;

import com.ibm.xslt4j.bcel.generic.Type;

public final class LONG_Upper extends Type
{
    private static LONG_Upper singleInstance;
    
    static {
        LONG_Upper.singleInstance = new LONG_Upper();
    }
    
    private LONG_Upper() {
        super((byte)15, "Long_Upper");
    }
    
    public static LONG_Upper theInstance() {
        return LONG_Upper.singleInstance;
    }
}
