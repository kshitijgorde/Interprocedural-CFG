import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class QuickSort implements SortInterface
{
    private int _intIndex;
    private SortingPanel _sortPanel;
    private Vector _vecItems;
    
    public QuickSort(final SortingPanel sortPanel) {
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
        this.sort(a, 0, this._sortPanel.getNumOfBars() - 1);
    }
    
    public boolean step() {
        if (this._intIndex >= this._vecItems.size() || ++this._intIndex >= this._vecItems.size()) {
            this._sortPanel.setColorBlack();
            return true;
        }
        final SortItem sortItem = this._vecItems.get(this._intIndex);
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
    
    private void sort(final int[] a, final int low, final int high) {
        int lo = low;
        int hi = high;
        if (this._vecItems.size() > 0) {
            for (int i = 0; i < UI_SortingAlgorithms.getFuncPenalty(); ++i) {
                this._vecItems.add(null);
            }
        }
        if (lo >= hi) {
            return;
        }
        if (lo == hi - 1) {
            if (a[lo] > a[hi]) {
                final int intTemp = a[lo];
                a[lo] = a[hi];
                a[hi] = intTemp;
            }
            this._vecItems.add(new SortItem(lo, hi, a[lo], a[hi]));
            for (int i = 0; i < UI_SortingAlgorithms.getSwapPenalty(); ++i) {
                this._vecItems.add(new SortItem(lo, hi));
            }
            return;
        }
        this._vecItems.add(new SortItem(lo, hi));
        final int pivotPos = (lo + hi) / 2;
        final int pivot = a[pivotPos];
        a[pivotPos] = a[hi];
        a[hi] = pivot;
        this._vecItems.add(new SortItem(pivotPos, hi, a[pivotPos], a[hi]));
        for (int j = 0; j < UI_SortingAlgorithms.getSwapPenalty(); ++j) {
            this._vecItems.add(new SortItem(pivotPos, hi));
        }
        while (lo < hi) {
            while (a[lo] <= pivot && lo < hi) {
                this._vecItems.add(new SortItem(lo, hi));
                ++lo;
            }
            while (pivot <= a[hi] && lo < hi) {
                this._vecItems.add(new SortItem(lo, hi));
                --hi;
            }
            if (lo < hi) {
                final int intTemp2 = a[lo];
                a[lo] = a[hi];
                a[hi] = intTemp2;
                this._vecItems.add(new SortItem(lo, hi, a[lo], a[hi]));
                for (int k = 0; k < UI_SortingAlgorithms.getSwapPenalty(); ++k) {
                    this._vecItems.add(new SortItem(lo, hi));
                }
            }
        }
        a[high] = a[hi];
        a[hi] = pivot;
        this._vecItems.add(new SortItem(hi, high, a[hi], a[high]));
        for (int j = 0; j < UI_SortingAlgorithms.getSwapPenalty(); ++j) {
            this._vecItems.add(new SortItem(hi, high));
        }
        if (low < lo - 1) {
            for (int j = 0; j < UI_SortingAlgorithms.getCompPenalty(); ++j) {
                this._vecItems.add(new SortItem(low, lo - 1));
            }
            this.sort(a, low, lo - 1);
        }
        if (hi + 1 < high) {
            for (int j = 0; j < UI_SortingAlgorithms.getCompPenalty(); ++j) {
                this._vecItems.add(new SortItem(hi + 1, high));
            }
            this.sort(a, hi + 1, high);
        }
    }
}
