import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class BubbleSort implements SortInterface
{
    private int _intIndex;
    private SortingPanel _sortPanel;
    private Vector _vecItems;
    
    public BubbleSort(final SortingPanel sortPanel) {
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
    
    void sort(final int[] a) {
        for (int i = a.length - 1; i > 0; --i) {
            boolean flipped = false;
            for (int j = 0; j < i; ++j) {
                if (a[j] > a[j + 1]) {
                    final int intTemp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = intTemp;
                    flipped = true;
                    this._vecItems.add(new SortItem(j, i, j, j + 1, a[j], a[j + 1]));
                    for (int k = 0; k < UI_SortingAlgorithms.getSwapPenalty(); ++k) {
                        this._vecItems.add(null);
                    }
                }
                for (int l = 0; l < UI_SortingAlgorithms.getCompPenalty(); ++l) {
                    this._vecItems.add(new SortItem(j, i));
                }
            }
            if (!flipped) {
                return;
            }
        }
    }
}
