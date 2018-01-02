// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Button;
import java.awt.Component;
import java.awt.Label;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Dialog;

public class AppConfirmDialog extends Dialog
{
    String bValue;
    GridBagLayout gbl;
    Dimension d;
    
    public AppConfirmDialog(final String s) {
        super(new Frame(), "Confirm", true);
        this.gbl = new GridBagLayout();
        this.d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(this.gbl);
        final Panel panel = new Panel();
        panel.setLayout(new FlowLayout());
        panel.add(new Label(s));
        final Panel panel2 = new Panel();
        panel2.setLayout(new FlowLayout());
        panel2.add(new Button("Yes"));
        panel2.add(new Button("No"));
        panel2.add(new Button("Cancel"));
        this.addComponent(this, panel, 0, 0, 1, 1, 0, 20, 'S');
        this.addComponent(this, panel2, 0, 1, 1, 1, 0, 0, 'S');
        this.pack();
        this.move((this.d.width - this.bounds().width) / 2, (this.d.height - this.bounds().height) / 2);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.bValue = (String)event.arg;
            this.dispose();
            return true;
        }
        return false;
    }
    
    void addComponent(final Dialog dialog, final Object o, final int gridx, final int gridy, final int gridwidth, final int gridheight, final int right, final int bottom, final char c) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        switch (c) {
            case 'W': {
                gridBagConstraints.anchor = 17;
                break;
            }
            case 'E': {
                gridBagConstraints.anchor = 13;
                break;
            }
            case 'C': {
                gridBagConstraints.anchor = 10;
                break;
            }
            case 'N': {
                gridBagConstraints.anchor = 11;
                break;
            }
            case 'S': {
                gridBagConstraints.anchor = 15;
                break;
            }
        }
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        final Component component = (Component)o;
        if (o instanceof Button) {
            component.setForeground(Color.black);
            component.setBackground(Color.lightGray);
        }
        gridBagConstraints.insets.right = right;
        gridBagConstraints.insets.bottom = bottom;
        gridBagConstraints.ipadx = 10;
        this.gbl.setConstraints(component, gridBagConstraints);
        dialog.add(component);
    }
}
