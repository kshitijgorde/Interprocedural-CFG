// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class TEXTMETRICA extends TEXTMETRIC
{
    public byte tmFirstChar;
    public byte tmLastChar;
    public byte tmDefaultChar;
    public byte tmBreakChar;
    public static final int sizeof;
    
    static {
        sizeof = OS.TEXTMETRICA_sizeof();
    }
}
