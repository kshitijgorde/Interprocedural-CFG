// 
// Decompiled by Procyon v0.5.30
// 

public class BinarySearchAlgorithm extends SearchAlgorithm
{
    private int idx;
    private int low;
    private int high;
    private int comparisons;
    
    public BinarySearchAlgorithm() {
        this.low = 0;
        this.high = 0;
    }
    
    public int search(final double[] a, final double numToFind) throws Exception {
        this.high = a.length;
        this.pause(-1, this.low, this.high);
        while (this.low <= this.high) {
            this.idx = (this.high + this.low) / 2;
            if (this.stopRequested) {
                return -1;
            }
            ++this.comparisons;
            if (a[this.idx] == numToFind) {
                this.pause(this.idx, this.low, this.high);
                return this.comparisons;
            }
            if (a[this.idx] > numToFind) {
                this.high = this.idx - 1;
            }
            else {
                this.low = this.idx + 1;
            }
            this.pause(this.idx, this.low, this.high);
        }
        return -1;
    }
}
