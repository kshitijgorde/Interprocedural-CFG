// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.awt.Dimension;
import javax.swing.JTextField;

class o extends JTextField
{
    private final j a;
    
    o(final j a) {
        this.a = a;
    }
    
    public Dimension getPreferredSize() {
        final Dimension dimension = new Dimension(super.getPreferredSize());
        dimension.height = Math.max(dimension.height, j.a(this.a).getPreferredSize().height);
        return dimension;
    }
}
