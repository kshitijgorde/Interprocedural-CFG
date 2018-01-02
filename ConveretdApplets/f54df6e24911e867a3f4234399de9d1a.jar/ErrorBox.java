import java.awt.Event;
import java.awt.Component;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class ErrorBox extends JDialog
{
    public ErrorBox(final Frame f, final String str) {
        super(f, str);
        this.setTitle("Error!");
        this.setSize(super.width, super.height + 50);
        super.button.add(new Button("OK"));
    }
    
    public boolean action(final Event evt, final Object obj) {
        if ("OK".equals(obj)) {
            this.dispose();
            return true;
        }
        return false;
    }
}
