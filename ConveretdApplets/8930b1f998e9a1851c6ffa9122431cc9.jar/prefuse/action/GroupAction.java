// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action;

import prefuse.Visualization;

public abstract class GroupAction extends Action
{
    protected String m_group;
    
    public GroupAction() {
        this((Visualization)null);
    }
    
    public GroupAction(final Visualization visualization) {
        this(visualization, Visualization.ALL_ITEMS);
    }
    
    public GroupAction(final Visualization visualization, final long n) {
        this(visualization, Visualization.ALL_ITEMS, n);
    }
    
    public GroupAction(final Visualization visualization, final long n, final long n2) {
        this(visualization, Visualization.ALL_ITEMS, n, n2);
    }
    
    public GroupAction(final String s) {
        this(null, s);
    }
    
    public GroupAction(final String s, final long n) {
        this(null, s, n);
    }
    
    public GroupAction(final String s, final long n, final long n2) {
        this(null, s, n, n2);
    }
    
    public GroupAction(final Visualization visualization, final String group) {
        super(visualization);
        this.m_group = group;
    }
    
    public GroupAction(final Visualization visualization, final String group, final long n) {
        super(visualization, n);
        this.m_group = group;
    }
    
    public GroupAction(final Visualization visualization, final String group, final long n, final long n2) {
        super(visualization, n, n2);
        this.m_group = group;
    }
    
    public String getGroup() {
        return this.m_group;
    }
    
    public void setGroup(final String group) {
        this.m_group = group;
    }
    
    public abstract void run(final double p0);
}
