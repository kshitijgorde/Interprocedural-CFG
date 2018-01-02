// 
// Decompiled by Procyon v0.5.30
// 

class SequentialSearchAlgorithm extends SearchAlgorithm
{
    public int search(final double[] a, final double numToFind) throws Exception {
        for (int i = 0; i < a.length; ++i) {
            if (this.stopRequested) {
                return -1;
            }
            if (a[i] == numToFind) {
                this.pause(i);
                return i + 1;
            }
            this.pause(i, i + 1, a.length - 1);
        }
        return -1;
    }
}
