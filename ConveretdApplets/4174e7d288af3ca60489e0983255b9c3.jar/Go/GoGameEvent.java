// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import java.util.EventObject;

public class GoGameEvent extends EventObject
{
    private boolean isGameOver;
    private GoPosition oldPosition;
    private GoPosition newPosition;
    
    public GoGameEvent(final Object source, final GoPosition oldPosition, final GoPosition newPosition) {
        super(source);
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }
    
    public boolean getIsGameOver() {
        return this.isGameOver;
    }
    
    public void setIsGameOver(final boolean value) {
        this.isGameOver = value;
    }
    
    public GoPosition getOldPosition() {
        return this.oldPosition;
    }
    
    public void setOldPosition(final GoPosition value) {
        this.oldPosition = value;
    }
    
    public GoPosition getNewPosition() {
        return this.newPosition;
    }
    
    public void setNewPosition(final GoPosition value) {
        this.newPosition = value;
    }
}
