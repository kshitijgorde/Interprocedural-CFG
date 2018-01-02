import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

// 
// Decompiled by Procyon v0.5.30
// 

class FocusTextField extends JTextField implements FocusListener
{
    FocusTextField(final int n) {
        super(n);
        this.addFocusListener(this);
    }
    
    @Override
    public void focusGained(final FocusEvent focusEvent) {
        this.selectAll();
    }
    
    @Override
    public void focusLost(final FocusEvent focusEvent) {
        this.select(0, 0);
    }
}
