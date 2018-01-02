// 
// Decompiled by Procyon v0.5.30
// 

package ytdfriends;

import edu.berkeley.guir.prefuse.NodeItem;
import edu.berkeley.guir.prefuse.EdgeItem;
import edu.berkeley.guir.prefuse.AggregateItem;
import edu.berkeley.guir.prefuse.VisualItem;
import java.util.Comparator;

public class FriendsItemComparator implements Comparator
{
    private static int MOUSE_SCORE;
    private static int HIGHLIGHT1_SCORE;
    private static int HIGHLIGHT2_SCORE;
    private static int HIGHLIGHT3_SCORE;
    private static int FOCUS_SCORE;
    private static int NODE_SCORE;
    private static int AURA_SCORE;
    private static int AGGREGATE_SCORE;
    private static int FADED_SCORE;
    private static int EDGE_SCORE;
    
    static {
        FriendsItemComparator.MOUSE_SCORE = 1024;
        FriendsItemComparator.HIGHLIGHT1_SCORE = 512;
        FriendsItemComparator.HIGHLIGHT2_SCORE = 256;
        FriendsItemComparator.HIGHLIGHT3_SCORE = 128;
        FriendsItemComparator.FOCUS_SCORE = 64;
        FriendsItemComparator.NODE_SCORE = 32;
        FriendsItemComparator.AURA_SCORE = 16;
        FriendsItemComparator.AGGREGATE_SCORE = 8;
        FriendsItemComparator.FADED_SCORE = 4;
        FriendsItemComparator.EDGE_SCORE = 2;
    }
    
    protected int score(final VisualItem item) {
        int score = 0;
        final boolean isAggregate = item instanceof AggregateItem;
        final boolean isDecorator = item instanceof DecoratorItem;
        if (item.isHighlighted() && !isAggregate && !isDecorator) {
            int val = 0;
            if (item instanceof EdgeItem) {
                score += FriendsItemComparator.HIGHLIGHT3_SCORE;
            }
            else if ((val = FriendsLib.getHighlightValue(item)) == 0) {
                score += FriendsItemComparator.MOUSE_SCORE;
            }
            else if (val == 1) {
                score += FriendsItemComparator.HIGHLIGHT1_SCORE;
            }
            else if (val >= 2) {
                score += FriendsItemComparator.HIGHLIGHT2_SCORE;
            }
        }
        if (item.isFocus()) {
            score += FriendsItemComparator.FOCUS_SCORE;
        }
        if (isAggregate) {
            score += FriendsItemComparator.AGGREGATE_SCORE;
        }
        else if (item instanceof NodeItem) {
            if (!item.isHighlighted() && FriendsLib.getHighlightValue(item) < 0) {
                score += FriendsItemComparator.FADED_SCORE;
            }
            else {
                score += FriendsItemComparator.NODE_SCORE;
            }
        }
        else if (item instanceof EdgeItem) {
            score += FriendsItemComparator.EDGE_SCORE;
        }
        return score;
    }
    
    public int compare(final Object o1, final Object o2) {
        if (!(o1 instanceof VisualItem) || !(o2 instanceof VisualItem)) {
            throw new IllegalArgumentException();
        }
        final VisualItem item1 = (VisualItem)o1;
        final VisualItem item2 = (VisualItem)o2;
        int score1 = this.score(item1);
        int score2 = this.score(item2);
        if (item1 instanceof AggregateItem && item2 instanceof AggregateItem) {
            final int s1 = ((AggregateItem)item1).getAggregateSize();
            final int s2 = ((AggregateItem)item2).getAggregateSize();
            if (s1 < s2) {
                ++score1;
            }
            else if (s2 < s1) {
                ++score2;
            }
        }
        return (score1 < score2) ? -1 : ((score1 == score2) ? 0 : 1);
    }
}
