import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Scrollbar;

// 
// Decompiled by Procyon v0.5.30
// 

class SpeedControl extends Scrollbar implements AdjustmentListener
{
    private static final int MAXIMUM = 10;
    private static final int MINIMUM = 1;
    private static final int INITIAL = 5;
    private int current;
    
    public SpeedControl() {
        super(0, 5, 0, 1, 10);
        this.current = 5;
        this.addAdjustmentListener(this);
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent event) {
        this.current = event.getValue();
    }
    
    public int getSpeed() {
        return this.current;
    }
}
