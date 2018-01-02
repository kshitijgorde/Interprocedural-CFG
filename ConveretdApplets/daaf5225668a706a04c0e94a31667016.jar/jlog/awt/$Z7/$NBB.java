// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

public class $NBB
{
    String name;
    $WAB selected;
    
    public void $OBB(final $WAB selected) {
        final $WAB selected2 = this.selected;
        if (selected == selected2) {
            return;
        }
        this.selected = selected;
        if (selected2 != null) {
            selected2.setState(false);
        }
        selected.setState(true);
    }
    
    public $NBB() {
        this.name = null;
        this.selected = null;
    }
    
    public $WAB getSelected() {
        return this.selected;
    }
}
