// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.Event;

public class SpringyImageButtonController extends ImageButtonController
{
    public SpringyImageButtonController(final ImageButton ib) {
        super(ib);
    }
    
    public boolean mouseUp(final Event event, final int x, final int y) {
        if (event.modifiers == 0 && !this.getButton().isRaised()) {
            this.getButton().paintRaised();
            this.activateButton(event);
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        if (event.modifiers == 0) {
            this.getButton().paintInset();
            this.armButton(event);
        }
        return false;
    }
    
    public boolean mouseDrag(final Event event, final int x, final int y) {
        if (event.modifiers == 0) {
            if (this.getButton().isInside(x, y)) {
                if (this.getButton().isRaised()) {
                    this.getButton().paintInset();
                    this.armButton(event);
                }
            }
            else if (!this.getButton().isRaised()) {
                this.getButton().paintRaised();
                this.disarmButton(event);
            }
        }
        return false;
    }
}
