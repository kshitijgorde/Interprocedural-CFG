// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.friends;

import java.util.Comparator;

public class TreeNodeComparatorFactory
{
    static TreeNodeLexicographicComparator tnlc;
    
    public static synchronized Comparator getTreeNodeLexicographicComparator() {
        if (TreeNodeComparatorFactory.tnlc == null) {
            TreeNodeComparatorFactory.tnlc = new TreeNodeLexicographicComparator();
        }
        return TreeNodeComparatorFactory.tnlc;
    }
    
    private static class TreeNodeLexicographicComparator implements Comparator
    {
        @Override
        public int compare(final Object o, final Object o2) {
            return o.toString().compareToIgnoreCase(o2.toString());
        }
    }
}
