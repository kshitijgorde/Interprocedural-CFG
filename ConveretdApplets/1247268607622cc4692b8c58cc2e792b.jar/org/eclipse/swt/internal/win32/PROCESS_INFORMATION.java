// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class PROCESS_INFORMATION
{
    public int hProcess;
    public int hThread;
    public int dwProcessId;
    public int dwThreadId;
    public static int sizeof;
    
    static {
        PROCESS_INFORMATION.sizeof = OS.PROCESS_INFORMATION_sizeof();
    }
}
