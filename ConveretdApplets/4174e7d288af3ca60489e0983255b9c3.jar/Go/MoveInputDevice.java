// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.awt.Graphics;

public interface MoveInputDevice
{
    void fireMoveAttempt(final Move p0);
    
    void displayMove(final int p0, final int p1, final int p2, final Graphics p3);
}
