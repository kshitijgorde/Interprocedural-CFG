import java.awt.Event;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ErrDialog extends Dialog implements Runnable
{
    Button okbutton;
    
    public ErrDialog(final String s, final String s2, final Frame frame) {
        super(frame, s, true);
        this.okbutton = new Button("Ok");
        this.setLayout(new FlowLayout());
        this.add(new Label(s2));
        this.add(this.okbutton);
        this.resize(500, 500);
        this.pack();
        j_util.centerDialog(this, frame);
    }
    
    public void run() {
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.okbutton) {
                    SpellEngine.killErrorDialog(this);
                    break;
                }
            }
            case 201: {
                this.dispose();
                break;
            }
        }
        return super.handleEvent(event);
    }
}
