// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action;

import prefuse.Visualization;

public class RepaintAction extends Action
{
    public RepaintAction() {
    }
    
    public RepaintAction(final Visualization visualization) {
        super(visualization);
    }
    
    public void run(final double n) {
        this.getVisualization().repaint();
    }
}
