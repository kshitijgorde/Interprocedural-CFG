// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import java.beans.PropertyVetoException;

public interface BevelStyle
{
    public static final int BEVEL_LOWERED = 0;
    public static final int BEVEL_RAISED = 1;
    public static final int BEVEL_LINE = 2;
    public static final int BEVEL_NONE = 3;
    
    void setBevelStyle(final int p0) throws PropertyVetoException;
    
    int getBevelStyle();
}
