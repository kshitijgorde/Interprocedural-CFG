// 
// Decompiled by Procyon v0.5.30
// 

abstract class SearchAlgorithm
{
    private SearchItem parent;
    protected boolean stopRequested;
    
    SearchAlgorithm() {
        this.stopRequested = false;
    }
    
    public void setParent(final SearchItem p) {
        this.parent = p;
    }
    
    protected void pause() throws Exception {
        if (this.stopRequested) {
            throw new Exception("Search Algorithm");
        }
        this.parent.pause(this.parent.h1, this.parent.h2, this.parent.h3);
    }
    
    protected void pause(final int H1) throws Exception {
        if (this.stopRequested) {
            throw new Exception("Search Algorithm");
        }
        this.parent.pause(H1, this.parent.h2, this.parent.h3);
    }
    
    protected void pause(final int H1, final int H2) throws Exception {
        if (this.stopRequested) {
            throw new Exception("Search Algorithm");
        }
        this.parent.pause(H1, H2, this.parent.h3);
    }
    
    protected void pause(final int H1, final int H2, final int H3) throws Exception {
        if (this.stopRequested) {
            throw new Exception("Search Algorithm");
        }
        this.parent.pause(H1, H2, H3);
    }
    
    public void stop() {
        this.stopRequested = true;
    }
    
    public void init() {
        this.stopRequested = false;
    }
    
    public int search(final double[] a, final double numToFind) throws Exception {
        return -1;
    }
}
