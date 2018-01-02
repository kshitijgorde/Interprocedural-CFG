// 
// Decompiled by Procyon v0.5.30
// 

package doppler;

import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;

public class SizedButton extends Panel
{
    Button button;
    
    public SizedButton(final String s) {
        this.button = new Button(s);
        this.setLayout(new BorderLayout());
        this.add("Center", this.button);
    }
    
    public Insets insets() {
        int n = (this.size().height - 26) / 2;
        if (n < 0) {
            n = 0;
        }
        return new Insets(n, 2, n, 2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.button)) {
            event.target = this;
            return false;
        }
        return false;
    }
    
    public final void setLabel(final String label) {
        this.button.setLabel(label);
    }
}
