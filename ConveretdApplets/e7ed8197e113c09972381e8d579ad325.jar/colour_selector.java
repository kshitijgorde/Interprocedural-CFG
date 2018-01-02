import java.awt.Event;
import java.awt.Point;
import java.awt.IllegalComponentStateException;
import java.awt.Component;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class colour_selector extends Frame implements groupboard_consts
{
    private draw_panel target;
    
    colour_selector(final draw_panel target) {
        this.target = target;
        this.setTitle("Colour Palette Selector");
        this.add("Center", new colsel_canvas(target));
        try {
            final Point locationOnScreen = target.getLocationOnScreen();
            if (target.parent.new_jdk) {
                this.setLocation(Math.max(locationOnScreen.x + 10, 10), Math.max(locationOnScreen.y + 10, 10));
            }
        }
        catch (IllegalComponentStateException ex) {}
        this.pack();
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.target.parent.coloursel = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
