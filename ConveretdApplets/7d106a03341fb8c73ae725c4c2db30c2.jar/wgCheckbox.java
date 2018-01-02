import java.awt.Event;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;

// 
// Decompiled by Procyon v0.5.30
// 

public class wgCheckbox extends Checkbox
{
    public static final int CB_BUTTON = 1;
    
    wgCheckbox(final String s, final CheckboxGroup checkboxGroup, final boolean b) {
        super(s, checkboxGroup, b);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            this.postEvent(new Event(this.getParent(), 1, null));
            return true;
        }
        return super.handleEvent(event);
    }
}
