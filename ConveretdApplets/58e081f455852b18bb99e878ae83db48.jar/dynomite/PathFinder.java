// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import java.util.Enumeration;
import java.awt.Point;
import java.util.Vector;

public class PathFinder
{
    EggRack mRack;
    Vector mBranchVector;
    int mBranchCount;
    int mOverflow;
    
    public PathFinder(final EggRack mRack) {
        this.mBranchVector = new Vector();
        this.mRack = mRack;
    }
    
    public int Path(final int n, final int n2) {
        final boolean b = false;
        if (n < 0) {
            return 0;
        }
        if (n >= this.mRack.mWidth) {
            return 0;
        }
        if (n2 < 0) {
            return 1;
        }
        if (n2 > 39) {
            return 0;
        }
        if (++this.mOverflow > 399) {
            return 1;
        }
        if (this.mRack.mEggs[n][n2] == 0) {
            return 0;
        }
        final Enumeration<Point> elements = this.mBranchVector.elements();
        while (elements.hasMoreElements()) {
            final Point point = elements.nextElement();
            if (point.x == n && point.y == n2) {
                return 0;
            }
        }
        this.mBranchVector.addElement(new Point(n, n2));
        int n3 = (b ? 1 : 0) | this.Path(n, n2 - 1);
        if (n3 == 0) {
            n3 |= this.Path(n, n2 + 1);
        }
        if (n2 % 2 != 0) {
            if (n3 == 0) {
                n3 |= this.Path(n + 1, n2 - 1);
            }
            if (n3 == 0) {
                n3 |= this.Path(n + 1, n2 + 1);
            }
        }
        else {
            if (n3 == 0) {
                n3 |= this.Path(n - 1, n2 - 1);
            }
            if (n3 == 0) {
                n3 |= this.Path(n - 1, n2 + 1);
            }
        }
        if (n3 == 0) {
            n3 |= this.Path(n - 1, n2);
        }
        if (n3 == 0) {
            n3 |= this.Path(n + 1, n2);
        }
        return n3;
    }
    
    public int Go(final int n, final int n2) {
        this.mBranchVector.removeAllElements();
        this.mBranchCount = 0;
        this.mOverflow = 0;
        return this.Path(n, n2);
    }
}
