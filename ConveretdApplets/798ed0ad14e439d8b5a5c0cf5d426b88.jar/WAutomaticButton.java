import java.awt.event.MouseEvent;

// 
// Decompiled by Procyon v0.5.30
// 

public class WAutomaticButton extends WButton
{
    private ButtonTimer bt;
    public long waitTime;
    public long repeatTime;
    
    public WAutomaticButton(final WImage wImage, final String s) {
        super(wImage, s, 1);
        this.waitTime = 400L;
        this.repeatTime = 100L;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.bt.fired) {
            super.mouseClicked(mouseEvent);
        }
        else {
            this.setSelected(false, false);
        }
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        if (this.bt != null) {
            this.bt.pressed = false;
        }
        super.mouseExited(mouseEvent);
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.bt = new ButtonTimer(this);
        this.bt.pressed = true;
        super.mousePressed(mouseEvent);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.bt != null) {
            this.bt.pressed = false;
        }
        super.mouseReleased(mouseEvent);
    }
}
