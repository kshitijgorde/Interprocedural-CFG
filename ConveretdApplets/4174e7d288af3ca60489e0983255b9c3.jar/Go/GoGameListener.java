// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.util.EventListener;

public interface GoGameListener extends EventListener
{
    void positionChanged(final GoGameEvent p0);
    
    void gameOver(final GoGameEvent p0);
}
