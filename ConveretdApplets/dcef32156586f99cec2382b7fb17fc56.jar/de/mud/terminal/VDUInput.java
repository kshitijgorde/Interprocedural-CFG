// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.terminal;

import java.util.Properties;

public interface VDUInput
{
    public static final int KEY_CONTROL = 1;
    public static final int KEY_SHIFT = 2;
    public static final int KEY_ALT = 4;
    public static final int KEY_ACTION = 8;
    
    void write(final byte[] p0);
    
    void mousePressed(final int p0, final int p1, final int p2);
    
    void mouseReleased(final int p0, final int p1, final int p2);
    
    void setKeyCodes(final Properties p0);
    
    void keyPressed(final int p0, final char p1, final int p2);
    
    void keyTyped(final int p0, final char p1, final int p2);
}
