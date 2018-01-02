// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.gdip;

public class GdiplusStartupInput
{
    public int GdiplusVersion;
    public int DebugEventCallback;
    public boolean SuppressBackgroundThread;
    public boolean SuppressExternalCodecs;
    public static final int sizeof;
    
    static {
        sizeof = Gdip.GdiplusStartupInput_sizeof();
    }
}
