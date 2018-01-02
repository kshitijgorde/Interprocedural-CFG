// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action;

import prefuse.Visualization;
import java.util.logging.Logger;
import prefuse.activity.Activity;

public abstract class Action extends Activity
{
    private static final Logger s_logger;
    protected Visualization m_vis;
    
    public Action() {
        this(null);
    }
    
    public Action(final long n) {
        super(n, 15L);
    }
    
    public Action(final long n, final long n2) {
        super(n, n2);
    }
    
    public Action(final Visualization visualization) {
        this(visualization, 0L);
    }
    
    public Action(final Visualization vis, final long n) {
        super(n, 15L);
        this.m_vis = vis;
    }
    
    public Action(final Visualization vis, final long n, final long n2) {
        super(n, n2);
        this.m_vis = vis;
    }
    
    public abstract void run(final double p0);
    
    protected void run(final long n) {
        final Visualization visualization = this.getVisualization();
        if (visualization != null) {
            synchronized (visualization) {
                this.run(this.getPace(n));
            }
        }
        else {
            Action.s_logger.info("Running unsynchronized Action");
            this.run(this.getPace(n));
        }
    }
    
    public Visualization getVisualization() {
        return this.m_vis;
    }
    
    public void setVisualization(final Visualization vis) {
        this.m_vis = vis;
    }
    
    static {
        s_logger = Logger.getLogger(Action.class.getName());
    }
}
