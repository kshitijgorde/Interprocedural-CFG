import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class NFControls extends Panel
{
    int k;
    int xy;
    NFCanvas canvas;
    TextField s;
    
    public NFControls(final NFCanvas canvas) {
        this.canvas = canvas;
        final Choice choice = new Choice();
        choice.addItem("Line");
        choice.addItem("Rect");
        choice.addItem("Circle");
        this.add(choice);
        this.add(this.s = new TextField("1", 5));
        this.add(new Button("submit"));
        this.add(new Button("clear"));
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            final String s = (String)o;
            if (s.equals("Line")) {
                this.k = 0;
            }
            else if (s.equals("Rect")) {
                this.k = 1;
            }
            else {
                this.k = 2;
            }
            this.canvas.getdata(this.k);
            return true;
        }
        if (event.target instanceof Button) {
            this.canvas.redraw(((String)o).equals("submit"), Integer.parseInt(this.s.getText().trim()));
            return true;
        }
        return false;
    }
    
    public void start() {
        this.enable();
    }
    
    public void stop() {
        this.disable();
    }
}
