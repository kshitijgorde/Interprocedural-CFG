// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class ACTCTX
{
    public int cbSize;
    public int dwFlags;
    public int lpSource;
    public short wProcessorArchitecture;
    public short wLangId;
    public int lpAssemblyDirectory;
    public int lpResourceName;
    public int lpApplicationName;
    public int hModule;
    public static final int sizeof;
    
    static {
        sizeof = OS.ACTCTX_sizeof();
    }
}
