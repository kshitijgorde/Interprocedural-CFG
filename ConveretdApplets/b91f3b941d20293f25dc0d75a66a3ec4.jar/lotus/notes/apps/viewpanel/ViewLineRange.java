// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.util.Vector;
import java.util.StringTokenizer;
import lotus.notes.util.TreeListEntry;
import lotus.notes.components.LineEntry;
import lotus.notes.util.TreeList;

class ViewLineRange
{
    private String startPos;
    private String endPos;
    private TreeList tree;
    private int ExpandedCount;
    public boolean wasJoined;
    public boolean allEntriesRead;
    public boolean isEndLineRange;
    
    public ViewLineRange() {
        this.startPos = null;
        this.endPos = null;
        this.tree = null;
        this.ExpandedCount = 0;
        this.wasJoined = false;
        this.allEntriesRead = false;
        this.isEndLineRange = false;
        this.tree = new TreeList();
    }
    
    public void setStartPos(final String startPos) {
        this.startPos = startPos;
    }
    
    public String getStartPos() {
        return this.startPos;
    }
    
    public void setEndPos(final String endPos) {
        this.endPos = endPos;
    }
    
    public String getEndPos() {
        return this.endPos;
    }
    
    public TreeList getTreeList() {
        return this.tree;
    }
    
    public void setTreeList(final TreeList tree) {
        this.tree = tree;
    }
    
    public int getExpandedCount() {
        int expandedCount = this.ExpandedCount;
        if (!this.startsOnTopLevelEntry()) {
            expandedCount += this.countUnparentedLines();
        }
        return expandedCount;
    }
    
    public void setExpandedCount(final int expandedCount) {
        this.ExpandedCount = expandedCount;
    }
    
    public int getTotalEntries() {
        if (this.tree != null) {
            return this.tree.getNumberOfEntries();
        }
        return 0;
    }
    
    public LineEntry findEntryByPos(final String s) {
        if (null == this.tree) {
            return null;
        }
        final String posAtLevel = this.getPosAtLevel(s, 1);
        ViewLine viewLine = (ViewLine)this.tree.getFirst();
        boolean b = false;
        while (null != viewLine) {
            if (posAtLevel.equals(this.getPosAtLevel(viewLine.GetCollectionPos(), 1))) {
                b = true;
                break;
            }
            final TreeList tree = this.tree;
            viewLine = (ViewLine)TreeList.Traverse(viewLine, 0, TreeList.CURRENT);
        }
        if (!b) {
            return null;
        }
        int i = 0;
        ViewLine viewLine2 = viewLine;
        while (i == 0) {
            if (null == viewLine2) {
                break;
            }
            if (s.equals(viewLine2.GetCollectionPos())) {
                i = 1;
                break;
            }
            final TreeList tree2 = this.tree;
            viewLine2 = (ViewLine)TreeList.Traverse(viewLine2, 0, TreeList.FULL);
        }
        if (i != 0) {
            return viewLine2;
        }
        return null;
    }
    
    public String getPosAtLevel(final String s, final int n) {
        if (0 == n) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
        String nextToken = null;
        int n2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            nextToken = stringTokenizer.nextToken();
            if (++n2 == n) {
                break;
            }
        }
        if (n2 < n) {
            return null;
        }
        return nextToken;
    }
    
    public String computeParentPos(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
        String concat = null;
        int n = 0;
        for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens - 1; ++i) {
            final String nextToken = stringTokenizer.nextToken();
            if (n == 0) {
                concat = nextToken;
            }
            else {
                concat = concat.concat("." + nextToken);
            }
            ++n;
        }
        return concat;
    }
    
    public String computeFirstChildPos(final String s) {
        return new String(s.concat(".1"));
    }
    
    public int computeLevelAtPos(final String s) {
        return new StringTokenizer(s, ".").countTokens();
    }
    
    public int computeEstimatedScrollPosition(final String s) {
        int n = 0;
        if (s != null) {
            for (int computeLevelAtPos = this.computeLevelAtPos(s), i = 1; i <= computeLevelAtPos; ++i) {
                n += Integer.parseInt(this.getPosAtLevel(s, i));
            }
            --n;
        }
        return n;
    }
    
    public boolean startsOnTopLevelEntry() {
        final ViewLine viewLine = (ViewLine)this.tree.getFirst();
        return viewLine != null && this.computeLevelAtPos(viewLine.GetCollectionPos()) == 1;
    }
    
    public String incrementPos(final String s, final int n, final int n2) {
        if (0 == n2) {
            return new String(s);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ".");
        int n3 = 1;
        String s2 = new String();
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (n3 > 1) {
                s2 = s2.concat(".");
            }
            if (n3 == n) {
                int n4 = Integer.parseInt(nextToken) + n2;
                if (n4 < 1) {
                    n4 = 1;
                }
                s2 = s2.concat(String.valueOf(n4));
            }
            else {
                s2 = s2.concat(nextToken);
            }
            ++n3;
        }
        return s2;
    }
    
    public int getLineChildCount(final LineEntry lineEntry) {
        if (null == lineEntry) {
            return 0;
        }
        int n = 0;
        for (LineEntry lineEntry2 = (LineEntry)lineEntry.getChild(); null != lineEntry2; lineEntry2 = (ViewLine)TreeList.Traverse(lineEntry2, 0, TreeList.CURRENT)) {
            ++n;
            final TreeList tree = this.tree;
        }
        return n;
    }
    
    LineEntry getLastExpandedDescendant(final LineEntry lineEntry) {
        if (null == lineEntry || lineEntry.IsCollapsed() || lineEntry.getChild() == null) {
            return null;
        }
        LineEntry lineEntry3;
        LineEntry lineEntry2 = lineEntry3 = (LineEntry)lineEntry.getChild();
        while (lineEntry3 != null) {
            lineEntry3 = (ViewLine)TreeList.Traverse(lineEntry3, 0, TreeList.CURRENT);
            if (lineEntry3 != null) {
                lineEntry2 = lineEntry3;
            }
        }
        if (lineEntry2.IsCollapsed() || lineEntry2.getChild() == null) {
            return null;
        }
        final LineEntry lastExpandedDescendant;
        if ((lastExpandedDescendant = this.getLastExpandedDescendant(lineEntry2)) == null) {
            return lineEntry2;
        }
        return lastExpandedDescendant;
    }
    
    public int comparePos(final String s, final String s2) {
        int n = 0;
        if (s != null && s2 != null) {
            final int computeLevelAtPos = this.computeLevelAtPos(s);
            final int computeLevelAtPos2 = this.computeLevelAtPos(s2);
            for (int min = Math.min(computeLevelAtPos, computeLevelAtPos2), i = 1; i <= min; ++i) {
                final int intValue = Integer.valueOf(this.getPosAtLevel(s, i));
                final int intValue2 = Integer.valueOf(this.getPosAtLevel(s2, i));
                if (intValue > intValue2) {
                    n = 1;
                    break;
                }
                if (intValue < intValue2) {
                    n = -1;
                    break;
                }
                n = 0;
            }
            if (n == 0) {
                if (computeLevelAtPos > computeLevelAtPos2) {
                    n = 1;
                }
                else if (computeLevelAtPos < computeLevelAtPos2) {
                    n = -1;
                }
            }
        }
        return n;
    }
    
    public int countLinesBetweenLines(final ViewLine viewLine, final ViewLine viewLine2) {
        int n = TreeList.CURRENTANDUP;
        int n2 = 0;
        if (viewLine == viewLine2) {
            return 0;
        }
        if (viewLine != null && viewLine2 != null) {
            if (!viewLine.IsCollapsed() && viewLine.getChild() != null) {
                n = TreeList.FULL;
            }
            final TreeList tree = this.tree;
            ViewLine viewLine3;
            int n3;
            for (viewLine3 = (ViewLine)TreeList.Traverse(viewLine, 0, n); viewLine3 != null && !viewLine3.GetCollectionPos().equals(viewLine2.GetCollectionPos()); viewLine3 = (ViewLine)TreeList.Traverse(viewLine3, 0, n3)) {
                ++n2;
                if (!viewLine3.IsCollapsed() && viewLine3.getChild() != null) {
                    n3 = TreeList.FULL;
                }
                else {
                    n3 = TreeList.CURRENTANDUP;
                }
                final TreeList tree2 = this.tree;
            }
            if (viewLine3 == null) {
                n2 = 0;
            }
        }
        if (n2 < 0) {
            n2 = 0;
        }
        return ++n2;
    }
    
    public Vector getAllDescendantParents(final ViewLine viewLine) {
        final Vector<String> vector = new Vector<String>();
        if (viewLine != null) {
            for (ViewLine viewLine2 = (ViewLine)TreeList.TraverseCurrentAndUp(viewLine), viewLine3 = (ViewLine)TreeList.Traverse(viewLine, 0, TreeList.FULL); viewLine3 != null && viewLine3 != viewLine2; viewLine3 = (ViewLine)TreeList.Traverse(viewLine3, 0, TreeList.FULL)) {
                if (viewLine3.children > 0) {
                    vector.addElement(viewLine3.UNID);
                }
            }
        }
        return vector;
    }
    
    public int countUnparentedLines() {
        int n = 0;
        final ViewLine viewLine = (ViewLine)this.tree.getFirst();
        if (viewLine != null) {
            ++n;
            for (ViewLine viewLine2 = (ViewLine)viewLine.getNext(); viewLine2 != null; viewLine2 = (ViewLine)viewLine2.getNext()) {
                if (this.computeLevelAtPos(viewLine2.GetCollectionPos()) == 1) {
                    break;
                }
                ++n;
            }
        }
        return n;
    }
}
