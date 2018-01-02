// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SCRIPT_ITEM
{
    public int iCharPos;
    public SCRIPT_ANALYSIS a;
    public static final int sizeof;
    
    static {
        sizeof = OS.SCRIPT_ITEM_sizeof();
    }
    
    public SCRIPT_ITEM() {
        this.a = new SCRIPT_ANALYSIS();
    }
}
