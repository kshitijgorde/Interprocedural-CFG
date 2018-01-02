// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

final class yearupdown extends Panel
{
    yearpanel parent;
    yearup yu;
    yeardown yd;
    
    yearupdown(final yearpanel parent) {
        this.parent = parent;
        this.setLayout(new GridLayout(2, 1, 2, 2));
        this.yu = new yearup(this);
        this.yd = new yeardown(this);
        this.add(this.yu);
        this.add(this.yd);
    }
    
    void setArrowColor(final Color color) {
        this.yu.setArrowColor(color);
        this.yd.setArrowColor(color);
    }
}
