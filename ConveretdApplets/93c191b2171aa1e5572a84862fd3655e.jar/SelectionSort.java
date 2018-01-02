import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class SelectionSort implements SortInterface
{
    private int _intIndex;
    private SortingPanel _sortPanel;
    private Vector _vecItems;
    
    public SelectionSort(final SortingPanel sortPanel) {
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
        this.sort(a);
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
    
    void sort(final int[] a) {
        for (int i = 0; i < a.length; ++i) {
            int min = i;
            for (int j = i + 1; j < a.length; ++j) {
                if (a[j] < a[min]) {
                    min = j;
                }
                this._vecItems.add(new SortItem(i, j));
            }
            final int intTemp = a[min];
            a[min] = a[i];
            a[i] = intTemp;
            this._vecItems.add(new SortItem(i, min, a[i], a[min]));
            for (int k = 0; k < UI_SortingAlgorithms.getSwapPenalty(); ++k) {
                this._vecItems.add(null);
            }
        }
    }
}
