// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class BeanPanel_WindowAdapter extends WindowAdapter
{
    private BeanPanel bean;
    
    public BeanPanel_WindowAdapter(final BeanPanel bean) {
        this.bean = bean;
    }
    
    public void windowActivated(final WindowEvent e) {
        this.bean.windowActiveChanged(true);
    }
    
    public void windowDeactivated(final WindowEvent e) {
        this.bean.windowActiveChanged(false);
    }
}
