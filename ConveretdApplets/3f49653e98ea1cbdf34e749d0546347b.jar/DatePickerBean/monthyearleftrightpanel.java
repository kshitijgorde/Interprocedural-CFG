// 
// Decompiled by Procyon v0.5.30
// 

package DatePickerBean;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

final class monthyearleftrightpanel extends Panel
{
    basepanel parent;
    monthyearpanel myp;
    mybuttonleft mybl;
    mybuttonright mybr;
    
    monthyearleftrightpanel(final basepanel parent) {
        this.myp = new monthyearpanel(this);
        this.parent = parent;
        this.mybl = new mybuttonleft(this);
        this.mybr = new mybuttonright(this);
        this.setLayout(new BorderLayout());
        this.add(this.myp);
        this.add(this.mybl, "West");
        this.add(this.mybr, "East");
    }
}
