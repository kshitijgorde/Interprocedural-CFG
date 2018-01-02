import java.awt.Event;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.IllegalComponentStateException;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class text_box extends Frame implements groupboard_consts
{
    groupboard parent;
    String text_string;
    TextArea text;
    Button ok_but;
    Button cancel_but;
    int x;
    int y;
    
    text_box(final groupboard parent, final int x, final int y) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 17;
        final GridBagConstraints gridBagConstraints3;
        final GridBagConstraints gridBagConstraints2 = gridBagConstraints3 = new GridBagConstraints();
        final GridBagConstraints gridBagConstraints4 = gridBagConstraints;
        final Insets insets = new Insets(5, 5, 5, 5);
        gridBagConstraints4.insets = insets;
        gridBagConstraints3.insets = insets;
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.anchor = 10;
        this.setLayout(layout);
        this.setTitle("Enter text:");
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout(1));
        this.add(this.text = new TextArea(5, 50));
        this.text.setEditable(true);
        layout.setConstraints(this.text, gridBagConstraints2);
        panel.add(this.ok_but = new Button("OK"));
        panel.add(this.cancel_but = new Button("Cancel"));
        this.add(panel);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(panel, gridBagConstraints);
        try {
            final Point locationOnScreen = parent.gui.draw_data.getLocationOnScreen();
            if (parent.new_jdk) {
                this.setLocation(x + locationOnScreen.x - 50, y + locationOnScreen.y - 20);
            }
        }
        catch (IllegalComponentStateException ex) {}
        this.pack();
        this.show();
        this.toFront();
        this.text.requestFocus();
    }
    
    private void ok_action() {
        this.parent.tb = null;
        this.dispose();
        int i = 0;
        final Graphics graphics = this.parent.gui.draw_data.getGraphics();
        while (i != -1) {
            if (i != 0) {
                ++i;
            }
            final int index = this.text.getText().indexOf(10, i);
            String s;
            if (index > 0) {
                s = this.text.getText().substring(i, index);
            }
            else {
                s = this.text.getText().substring(i);
            }
            if (null != s) {
                this.parent.gui.draw_data.add_to_array(3, this.x, this.y, 0, 0, this.parent.gui.draw_data.thickness, 0, s, this.parent.gui.draw_data.current_colour, true);
                this.y += graphics.getFontMetrics().getHeight();
                this.parent.gui.draw_data.update_panel(this.parent.gui.draw_data.off_screen_gc, true);
                this.parent.gui.draw_data.update_panel(graphics, true);
            }
            i = index;
        }
        graphics.dispose();
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.cancel_but) {
            this.dispose();
            this.parent.tb = null;
            return true;
        }
        if (event.target == this.ok_but) {
            this.ok_action();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            this.parent.tb = null;
            return true;
        }
        return super.handleEvent(event);
    }
}
