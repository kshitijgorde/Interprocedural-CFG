// 
// Decompiled by Procyon v0.5.30
// 

package gjt;

import java.awt.Event;

public abstract class ImageButtonController implements MouseController
{
    private ImageButton button;
    
    protected void armButton(final Event event) {
        this.button.deliverEvent(new ImageButtonEvent(this.button, event, 1));
    }
    
    public boolean mouseEnter(final Event event, final int x, final int y) {
        return false;
    }
    
    protected void activateButton(final Event event) {
        this.button.deliverEvent(new ImageButtonEvent(this.button, event, 3));
    }
    
    public boolean mouseExit(final Event event, final int x, final int y) {
        return false;
    }
    
    ImageButtonController(final ImageButton button) {
        Assert.notNull(button);
        (this.button = button).setController(this);
    }
    
    public abstract boolean mouseUp(final Event p0, final int p1, final int p2);
    
    protected void disarmButton(final Event event) {
        this.button.deliverEvent(new ImageButtonEvent(this.button, event, 2));
    }
    
    public abstract boolean mouseDown(final Event p0, final int p1, final int p2);
    
    public ImageButton getButton() {
        return this.button;
    }
    
    public abstract boolean mouseDrag(final Event p0, final int p1, final int p2);
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        return false;
    }
}
