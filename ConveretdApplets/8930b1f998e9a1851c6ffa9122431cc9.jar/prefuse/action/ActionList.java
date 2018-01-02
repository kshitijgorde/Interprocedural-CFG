// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action;

import prefuse.util.StringLib;
import prefuse.Visualization;
import java.util.logging.Logger;

public class ActionList extends CompositeAction
{
    private static final Logger s_logger;
    
    public ActionList() {
        super(0L);
    }
    
    public ActionList(final Visualization visualization) {
        super(visualization);
    }
    
    public ActionList(final long n) {
        super(n, 15L);
    }
    
    public ActionList(final Visualization visualization, final long n) {
        super(visualization, n);
    }
    
    public ActionList(final long n, final long n2) {
        super(n, n2);
    }
    
    public void run(final double n) {
        final Object[] array = this.m_actions.getArray();
        for (int i = 0; i < array.length; ++i) {
            final Action action = (Action)array[i];
            try {
                if (action.isEnabled()) {
                    action.run(n);
                }
            }
            catch (Exception ex) {
                ActionList.s_logger.warning(ex.getMessage() + '\n' + StringLib.getStackTrace(ex));
            }
        }
    }
    
    static {
        s_logger = Logger.getLogger(ActionList.class.getName());
    }
}
