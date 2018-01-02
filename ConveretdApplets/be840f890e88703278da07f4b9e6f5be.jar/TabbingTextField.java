import java.awt.Event;
import java.awt.event.FocusEvent;
import java.awt.Component;
import java.awt.event.FocusListener;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

public class TabbingTextField extends TextField implements FocusListener
{
    private Component NextField;
    private Component PrevField;
    
    public TabbingTextField(final String s, final int n) {
        super(s, n);
        this.addFocusListener(this);
    }
    
    public void SetTabOrder(final Component prevField, final Component nextField) {
        this.NextField = nextField;
        this.PrevField = prevField;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.selectAll();
        System.out.println("in tabbingtextfield focusGained()");
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.select(0, 0);
        System.out.println("in tabbingtextfield focusGained()");
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
        return false;
    }
}
