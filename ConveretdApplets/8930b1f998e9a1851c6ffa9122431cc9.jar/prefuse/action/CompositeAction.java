// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action;

import prefuse.Visualization;
import prefuse.util.collections.CopyOnWriteArrayList;

public abstract class CompositeAction extends Action
{
    protected CopyOnWriteArrayList m_actions;
    
    public CompositeAction() {
        super(null, 0L);
        this.m_actions = new CopyOnWriteArrayList();
    }
    
    public CompositeAction(final Visualization visualization) {
        super(visualization, 0L);
        this.m_actions = new CopyOnWriteArrayList();
    }
    
    public CompositeAction(final long n) {
        super(null, n, 15L);
        this.m_actions = new CopyOnWriteArrayList();
    }
    
    public CompositeAction(final Visualization visualization, final long n) {
        super(visualization, n, 15L);
        this.m_actions = new CopyOnWriteArrayList();
    }
    
    public CompositeAction(final long n, final long n2) {
        super(null, n, n2);
        this.m_actions = new CopyOnWriteArrayList();
    }
    
    public void setVisualization(final Visualization visualization) {
        super.setVisualization(visualization);
        for (int i = 0; i < this.m_actions.size(); ++i) {
            this.get(i).setVisualization(visualization);
        }
    }
    
    public int size() {
        return this.m_actions.size();
    }
    
    public void add(final Action action) {
        this.m_actions.add(action);
    }
    
    public void add(final int n, final Action action) {
        this.m_actions.add(n, action);
    }
    
    public Action get(final int n) {
        return (Action)this.m_actions.get(n);
    }
    
    public boolean remove(final Action action) {
        return this.m_actions.remove(action);
    }
    
    public Action remove(final int n) {
        return (Action)this.m_actions.remove(n);
    }
}
