// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.feature;

import javax.swing.Icon;
import javax.swing.JLabel;
import org.xidget.ifeature.IIconFeature;

public class JLabelIconFeature implements IIconFeature
{
    private JLabel jlabel;
    
    public JLabelIconFeature(final JLabel jlabel) {
        this.jlabel = jlabel;
    }
    
    @Override
    public void setIcon(final Object o) {
        this.jlabel.setIcon((Icon)o);
    }
}
