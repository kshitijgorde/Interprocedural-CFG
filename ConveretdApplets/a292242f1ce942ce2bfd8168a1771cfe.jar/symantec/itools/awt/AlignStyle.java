// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import java.beans.PropertyVetoException;

public interface AlignStyle
{
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_CENTERED = 1;
    public static final int ALIGN_RIGHT = 2;
    
    void setAlignStyle(final int p0) throws PropertyVetoException;
    
    int getAlignStyle();
}
