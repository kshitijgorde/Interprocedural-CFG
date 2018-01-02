// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.event.ComponentEvent;
import java.awt.Component;
import javax.swing.JComponent;

public class StubRenderer extends JComponent implements Renderer
{
    private JComponent comp;
    
    public StubRenderer() {
        this.comp = new EmptyComponent();
    }
    
    public Component getComponent() {
        return this.comp;
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void configure(final ModelPanel modelPanel) {
    }
}
