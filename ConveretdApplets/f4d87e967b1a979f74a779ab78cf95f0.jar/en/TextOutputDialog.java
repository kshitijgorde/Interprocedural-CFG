// 
// Decompiled by Procyon v0.5.30
// 

package en;

import java.awt.event.WindowEvent;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Frame;
import java.awt.Dialog;

public class TextOutputDialog extends Dialog
{
    private static final Frame frame;
    protected Panel panel1;
    protected BorderLayout borderLayout1;
    protected TextArea textArea;
    
    public TextOutputDialog(final String s, final String text) {
        super(TextOutputDialog.frame, s, true);
        this.panel1 = new Panel();
        this.borderLayout1 = new BorderLayout();
        this.textArea = new TextArea();
        if (text != null) {
            this.textArea.setText(text);
        }
        this.enableEvents(64L);
        try {
            this.jbInit();
            this.add(this.panel1);
            this.setSize(240, 160);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    final void jbInit() throws Exception {
        this.panel1.setLayout(this.borderLayout1);
        this.textArea.setEditable(false);
        this.panel1.add(this.textArea, "Center");
    }
    
    protected final void processWindowEvent(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            this.cancel();
        }
        super.processWindowEvent(windowEvent);
    }
    
    final void cancel() {
        this.dispose();
    }
    
    static {
        frame = new Frame();
    }
}
