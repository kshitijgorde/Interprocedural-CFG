// 
// Decompiled by Procyon v0.5.30
// 

package display;

import java.awt.Dimension;
import java.awt.Panel;

public abstract class Terminal extends Panel
{
    public abstract String[][] getParameterInfo();
    
    public abstract Dimension getSize();
    
    public abstract String getTerminalType();
    
    public abstract void putChar(final char p0);
    
    public abstract void putString(final String p0);
}
