// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.sort;

import prefuse.Visualization;
import prefuse.visual.NodeItem;
import prefuse.visual.DecoratorItem;
import prefuse.visual.AggregateItem;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;

public class TreeDepthItemSorter extends ItemSorter
{
    protected static final int AGGREGATE = 0;
    protected static final int EDGE = 1;
    protected static final int ITEM = 2;
    protected static final int NODE = 3;
    protected static final int DECORATOR = 4;
    private int m_childrenAbove;
    private int m_hover;
    private int m_highlight;
    private int m_depth;
    
    public TreeDepthItemSorter() {
        this(false);
    }
    
    public TreeDepthItemSorter(final boolean b) {
        if (b) {
            this.m_childrenAbove = 1;
            this.m_hover = 13;
            this.m_highlight = 12;
            this.m_depth = 14;
        }
        else {
            this.m_childrenAbove = -1;
            this.m_hover = 24;
            this.m_highlight = 23;
            this.m_depth = 12;
        }
    }
    
    public int score(final VisualItem visualItem) {
        int n = 2;
        if (visualItem instanceof EdgeItem) {
            n = 1;
        }
        else if (visualItem instanceof AggregateItem) {
            n = 0;
        }
        else if (visualItem instanceof DecoratorItem) {
            n = 4;
        }
        int n2 = 1 << 25 + n;
        if (visualItem instanceof NodeItem) {
            n2 += this.m_childrenAbove * (((NodeItem)visualItem).getDepth() << this.m_depth);
        }
        if (visualItem.isHover()) {
            n2 += 1 << this.m_hover;
        }
        if (visualItem.isHighlighted()) {
            n2 += 1 << this.m_highlight;
        }
        if (visualItem.isInGroup(Visualization.FOCUS_ITEMS)) {
            n2 += 2048;
        }
        if (visualItem.isInGroup(Visualization.SEARCH_ITEMS)) {
            n2 += 1024;
        }
        return n2;
    }
}
