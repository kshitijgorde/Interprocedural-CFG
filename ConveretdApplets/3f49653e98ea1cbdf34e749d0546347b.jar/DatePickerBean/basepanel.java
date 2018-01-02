// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

final class basepanel extends Panel
{
    calendarpanel parent;
    monthyearleftrightpanel mylrp;
    
    basepanel(final calendarpanel parent) {
        this.mylrp = new monthyearleftrightpanel(this);
        this.parent = parent;
        this.setLayout(new BorderLayout());
        this.add(this.mylrp);
    }
}
