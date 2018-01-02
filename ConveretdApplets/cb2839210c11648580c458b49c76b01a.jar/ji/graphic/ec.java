// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.Color;
import java.awt.Component;

public interface ec
{
    void intialise();
    
    void setParent(final Component p0, final String p1);
    
    void setBackground(final Color p0);
    
    void setTitle(final String p0);
    
    void setMode(final int p0);
    
    void setDirectory(final String p0);
    
    void setFile(final String p0);
    
    void show();
    
    String getDirectory();
    
    String getFile();
    
    void dispose();
}
