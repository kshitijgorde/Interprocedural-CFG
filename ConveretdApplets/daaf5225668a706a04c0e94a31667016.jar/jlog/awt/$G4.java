// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt;

import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.GridBagConstraints;
import jlog.$H4;
import java.awt.GridBagLayout;

public class $G4 extends GridBagLayout implements $H4
{
    GridBagConstraints c;
    Container $TR;
    
    public $G4(final Container $tr) {
        this.c = new GridBagConstraints();
        (this.$TR = $tr).setLayout(this);
    }
    
    public void add(final Component component) {
        this.setConstraints(component, this.c);
        this.$TR.add(component);
    }
    
    public void add(final Component component, final int gridx, final int gridy) {
        this.c.gridx = gridx;
        this.c.gridy = gridy;
        this.setConstraints(component, this.c);
        this.$TR.add(component);
    }
    
    public void add(final Component component, final int gridx, final int gridy, final int gridwidth, final int gridheight) {
        this.c.gridx = gridx;
        this.c.gridy = gridy;
        this.c.gridwidth = gridwidth;
        this.c.gridheight = gridheight;
        this.setConstraints(component, this.c);
        this.$TR.add(component);
    }
    
    public GridBagConstraints getConstraints() {
        return this.c;
    }
    
    public void setInsets(final Insets insets) {
        this.c.insets = insets;
    }
}
