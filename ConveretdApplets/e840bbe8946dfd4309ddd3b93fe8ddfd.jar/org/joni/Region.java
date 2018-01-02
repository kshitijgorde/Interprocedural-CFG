// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

public final class Region
{
    static final int REGION_NOTPOS = -1;
    public final int numRegs;
    public final int[] beg;
    public final int[] end;
    public CaptureTreeNode historyRoot;
    
    public Region(final int num) {
        this.numRegs = num;
        this.beg = new int[num];
        this.end = new int[num];
    }
    
    public Region(final int begin, final int end) {
        this.numRegs = 1;
        this.beg = new int[] { begin };
        this.end = new int[] { end };
    }
    
    public Region clone() {
        final Region region = new Region(this.numRegs);
        System.arraycopy(this.beg, 0, region.beg, 0, this.beg.length);
        System.arraycopy(this.end, 0, region.end, 0, this.end.length);
        if (this.historyRoot != null) {
            region.historyRoot = this.historyRoot.cloneTree();
        }
        return region;
    }
    
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Region: \n");
        for (int i = 0; i < this.beg.length; ++i) {
            sb.append(" " + i + ": (" + this.beg[i] + "-" + this.end[i] + ")");
        }
        return sb.toString();
    }
    
    CaptureTreeNode getCaptureTree() {
        return this.historyRoot;
    }
    
    void clear() {
        for (int i = 0; i < this.beg.length; ++i) {
            this.beg[i] = (this.end[i] = -1);
        }
    }
}
