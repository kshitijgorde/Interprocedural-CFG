// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Dimension;
import java.awt.List;

class FindList extends List
{
    FindDlg owner;
    int width;
    
    public FindList(final int n) {
        super(n);
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public Dimension getMinimumSize() {
        final Dimension minimumSize = super.getMinimumSize();
        minimumSize.width = Math.max(minimumSize.width, this.width);
        return minimumSize;
    }
    
    public Dimension getMinimumSize(final int n) {
        final Dimension minimumSize = super.getMinimumSize(n);
        minimumSize.width = Math.max(minimumSize.width, this.width);
        return minimumSize;
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        preferredSize.width = Math.max(preferredSize.width, this.width);
        return preferredSize;
    }
    
    public Dimension getPreferredSize(final int n) {
        final Dimension preferredSize = super.getPreferredSize(n);
        preferredSize.width = Math.max(preferredSize.width, this.width);
        return preferredSize;
    }
}
