// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.ole.win32;

public final class OLEINPLACEFRAMEINFO
{
    public int cb;
    public int fMDIApp;
    public int hwndFrame;
    public int haccel;
    public int cAccelEntries;
    public static final int sizeof;
    
    static {
        sizeof = COM.OLEINPLACEFRAMEINFO_sizeof();
    }
}
