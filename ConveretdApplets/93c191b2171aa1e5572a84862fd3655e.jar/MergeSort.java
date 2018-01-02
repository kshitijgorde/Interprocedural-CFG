import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class MergeSort implements SortInterface
{
    private int _intIndex;
    private SortingPanel _sortPanel;
    private Vector _vecItems;
    
    public MergeSort(final SortingPanel sortPanel) {
        this._sortPanel = sortPanel;
        this._vecItems = new Vector();
        this._intIndex = 0;
        final SortItem sortItem = new SortItem(0, this._sortPanel.getNumOfBars() - 1);
        sortItem.setBlue(this._sortPanel);
        sortItem.setRed(this._sortPanel);
        final int[] a = new int[this._sortPanel.getNumOfBars()];
        for (int i = 0; i < this._sortPanel.getNumOfBars(); ++i) {
            a[i] = this._sortPanel.getHeight(i);
        }
        final int[] scratch = new int[a.length];
        for (int j = 0; j < UI_SortingAlgorithms.getFuncPenalty(); ++j) {
            this._vecItems.add(null);
        }
        this.sort(a, 0, this._sortPanel.getNumOfBars() - 1, scratch);
    }
    
    public boolean step() {
        if (this._intIndex >= this._vecItems.size()) {
            this._sortPanel.setColorBlack();
            return true;
        }
        final SortItem sortItem = this._vecItems.get(this._intIndex++);
        if (sortItem == null) {
            return false;
        }
        this._sortPanel.setColorBlack();
        sortItem.setBlue(this._sortPanel);
        sortItem.setRed(this._sortPanel);
        return false;
    }
    
    public int getPercentDone() {
        return (int)(this._intIndex / this._vecItems.size() * 100.0);
    }
    
    void sort(final int[] a, final int low, final int high, final int[] scratch) {
        final int mid = (low + high) / 2;
        int hi = mid + 1;
        int lo = low;
        if (low >= high) {
            return;
        }
        if (low < mid) {
            this.sort(a, low, mid, scratch);
        }
        if (mid + 1 < high) {
            this.sort(a, mid + 1, high, scratch);
        }
        if (this._vecItems.size() > 0) {
            for (int i = 0; i < UI_SortingAlgorithms.getFuncPenalty(); ++i) {
                this._vecItems.add(null);
            }
        }
        this._vecItems.add(new SortItem(low, high));
        for (int i = low; i <= high; ++i) {
            if (lo <= mid && (hi > high || a[lo] < a[hi])) {
                scratch[i] = a[lo++];
            }
            else {
                scratch[i] = a[hi++];
            }
            for (int k = 0; k < UI_SortingAlgorithms.getCompPenalty(); ++k) {
                this._vecItems.add(new SortItem(lo, (hi > high) ? high : hi));
            }
        }
        for (int i = low; i <= high; ++i) {
            a[i] = scratch[i];
            this._vecItems.add(new SortItem(i, high, a[i], a[high]));
        }
    }
}
