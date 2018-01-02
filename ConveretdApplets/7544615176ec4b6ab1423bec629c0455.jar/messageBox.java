import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Button;
import java.awt.Panel;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class messageBox extends Dialog
{
    private Panel southPanel;
    private Button cb_OK;
    private Label lb_message;
    
    public messageBox(final Frame parent, final String output, final String title) {
        super(parent, title, true);
        this.southPanel = new Panel();
        this.lb_message = new Label(output);
        this.southPanel.setBackground(Color.lightGray);
        this.southPanel.setLayout(new FlowLayout());
        this.southPanel.add(this.cb_OK = new Button("OK"));
        this.add("South", this.southPanel);
        this.add("North", this.lb_message);
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this.cb_OK) {
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
}
