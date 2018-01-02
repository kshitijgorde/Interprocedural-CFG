// 
// Decompiled by Procyon v0.5.30
// 

package Lb2_5;

import java.util.ArrayList;

public class arrayCanvas extends ArrayList
{
    pageCanvas pC;
    int pageCount;
    int thisPage;
    int record;
    int pageTotal;
    int currentPage;
    boolean bAdded;
    
    public arrayCanvas(final int i) {
        super(i);
        this.pageCount = -1;
        this.currentPage = -1;
    }
    
    public int addItem(final pageCanvas pC, final int thisPage) {
        if (thisPage >= this.pageCount) {
            ++this.pageCount;
            this.bAdded = super.add(pC);
        }
        return this.pageCount;
    }
    
    public pageCanvas getItem(final int record) {
        final pageCanvas pC = super.get(record);
        return pC;
    }
    
    public void putPageTotal(final int j) {
        this.pageTotal = j;
    }
    
    public int getPageTotal() {
        return this.pageTotal;
    }
    
    public void putCurrentPage(final int j) {
        this.currentPage = j;
    }
    
    public int getCurrentPage() {
        return this.currentPage;
    }
    
    public int getPageCount() {
        return this.pageCount;
    }
}
