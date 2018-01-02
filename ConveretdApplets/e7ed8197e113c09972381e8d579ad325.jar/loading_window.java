import java.awt.Event;
import java.awt.Component;
import java.awt.Label;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class loading_window extends Frame implements groupboard_consts
{
    loading_window() {
        this.add("Center", new Label("Loading, please wait..."));
        this.setTitle("Loading");
        this.pack();
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
}
