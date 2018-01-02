// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JComponent;

public class ComponentLabelPair
{
    private final JComponent vComponent;
    private final JLabel vLabel;
    
    public ComponentLabelPair(final JComponent vComponent, final JLabel vLabel) {
        this.vComponent = vComponent;
        this.vLabel = vLabel;
        this.bindPair();
    }
    
    private void bindPair() {
        this.getLabel().setLabelFor(this.getComponent());
    }
    
    public JComponent getComponent() {
        return this.vComponent;
    }
    
    public JLabel getLabel() {
        return this.vLabel;
    }
}
