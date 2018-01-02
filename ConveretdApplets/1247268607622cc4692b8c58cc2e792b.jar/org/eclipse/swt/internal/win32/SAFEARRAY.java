// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class SAFEARRAY
{
    public short cDims;
    public short fFeatures;
    public int cbElements;
    public int cLocks;
    public int pvData;
    public SAFEARRAYBOUND rgsabound;
    public static final int sizeof;
    
    static {
        sizeof = OS.SAFEARRAY_sizeof();
    }
}
