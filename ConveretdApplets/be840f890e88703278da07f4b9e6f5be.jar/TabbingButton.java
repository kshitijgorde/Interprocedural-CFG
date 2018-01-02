import java.awt.AWTEvent;
import java.awt.event.ActionEvent;
import java.awt.Event;
import java.awt.Component;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

public class TabbingButton extends Button
{
    private Component NextField;
    private Component PrevField;
    
    public TabbingButton(final String s) {
        super(s);
    }
    
    public void SetTabOrder(final Component prevField, final Component nextField) {
        this.NextField = nextField;
        this.PrevField = prevField;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (9 == n) {
            if ((event.modifiers & 0x1) == 0x0) {
                this.PrevField.requestFocus();
            }
            else {
                this.NextField.requestFocus();
            }
            return true;
        }
        if (10 == n || 13 == n) {
            this.dispatchEvent(new ActionEvent(this, 1001, this.getLabel()));
            return true;
        }
        return false;
    }
}
