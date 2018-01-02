// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.visual.sort;

import prefuse.Visualization;
import prefuse.visual.DecoratorItem;
import prefuse.visual.AggregateItem;
import prefuse.visual.EdgeItem;
import prefuse.visual.VisualItem;
import java.util.Comparator;

public class ItemSorter implements Comparator
{
    protected static final int AGGREGATE = 0;
    protected static final int EDGE = 1;
    protected static final int ITEM = 2;
    protected static final int DECORATOR = 3;
    
    public int score(final VisualItem visualItem) {
        int n = 2;
        if (visualItem instanceof EdgeItem) {
            n = 1;
        }
        else if (visualItem instanceof AggregateItem) {
            n = 0;
        }
        else if (visualItem instanceof DecoratorItem) {
            n = 3;
        }
        int n2 = 1 << 26 + n;
        if (visualItem.isHover()) {
            n2 += 33554432;
        }
        if (visualItem.isHighlighted()) {
            n2 += 16777216;
        }
        if (visualItem.isInGroup(Visualization.FOCUS_ITEMS)) {
            n2 += 8388608;
        }
        if (visualItem.isInGroup(Visualization.SEARCH_ITEMS)) {
            n2 += 4194304;
        }
        return n2;
    }
    
    public int compare(final VisualItem visualItem, final VisualItem visualItem2) {
        final int score = this.score(visualItem);
        final int score2 = this.score(visualItem2);
        return (score < score2) ? -1 : ((score == score2) ? false : true);
    }
    
    public int compare(final Object o, final Object o2) {
        if (!(o instanceof VisualItem) || !(o2 instanceof VisualItem)) {
            throw new IllegalArgumentException();
        }
        return this.compare((VisualItem)o, (VisualItem)o2);
    }
}
