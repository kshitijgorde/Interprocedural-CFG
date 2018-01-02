// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import java.beans.PropertyVetoException;

public interface Orientation
{
    public static final int ORIENTATION_VERTICAL = 0;
    public static final int ORIENTATION_HORIZONTAL = 1;
    
    void setOrientation(final int p0) throws PropertyVetoException;
    
    int getOrientation();
}
