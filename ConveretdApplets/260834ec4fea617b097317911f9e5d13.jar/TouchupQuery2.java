import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.AWTEvent;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class TouchupQuery2 extends Dialog
{
    boolean result;
    
    public void processEvent(final AWTEvent awtEvent) {
    }
    
    TouchupQuery2(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
        this.result = false;
        this.setLayout(new FlowLayout());
        this.add(new Label("hit OK to Continue"));
        this.add(new Button("OK"));
        this.pack();
        this.show();
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if (windowEvent.getWindow().equals(this)) {
            this.dispose();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("OK")) {
            this.hide();
            this.dispose();
            this.result = true;
        }
        if (actionCommand.equals("OK") || actionCommand.equals("Cancel")) {
            this.processEvent(new WindowEvent(this, 201));
        }
    }
}
