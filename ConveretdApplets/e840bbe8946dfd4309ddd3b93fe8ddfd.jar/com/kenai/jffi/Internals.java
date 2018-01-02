// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public final class Internals
{
    public static final long getErrnoSaveFunction() {
        return Foreign.getInstance().getSaveErrnoFunction();
    }
}
