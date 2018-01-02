import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.IllegalComponentStateException;
import java.awt.Component;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class message_box extends Frame
{
    Button close_but;
    
    message_box(final groupboard groupboard, final String title, final String s) {
        this.setTitle(title);
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridwidth = 0;
        final Label label;
        this.add(label = new Label(s));
        layout.setConstraints(label, gridBagConstraints);
        this.add(this.close_but = new Button("Close"));
        layout.setConstraints(this.close_but, gridBagConstraints);
        try {
            final Point locationOnScreen = groupboard.getLocationOnScreen();
            if (groupboard.new_jdk) {
                this.setLocation(Math.max(locationOnScreen.x + 10, 10), Math.max(locationOnScreen.y + 10, 10));
            }
        }
        catch (IllegalComponentStateException ex) {}
        this.pack();
        this.show();
        this.toFront();
    }
    
    message_box(final groupboard groupboard, final String title, final String s, final int n, final int n2) {
        this.setTitle(title);
        this.setLayout(new BorderLayout(4, 4));
        final TextArea textArea = new TextArea(s, n, n2);
        textArea.setEditable(false);
        this.add("Center", textArea);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1, 5, 5));
        panel.add(this.close_but = new Button("Close"));
        this.add("South", panel);
        try {
            final Point locationOnScreen = groupboard.getLocationOnScreen();
            if (groupboard.new_jdk) {
                this.setLocation(Math.max(locationOnScreen.x + 10, 10), Math.max(locationOnScreen.y + 10, 10));
            }
        }
        catch (IllegalComponentStateException ex) {}
        this.pack();
        this.show();
        this.toFront();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.close_but) {
            this.dispose();
        }
        return true;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
}
